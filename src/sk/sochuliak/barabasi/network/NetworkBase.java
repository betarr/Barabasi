package sk.sochuliak.barabasi.network;

import java.util.Arrays;
import java.util.Date;

import sk.sochuliak.barabasi.util.CommonUtils;

public abstract class NetworkBase {

	protected NetworkBuildStatistics buildStatistics;
	
	public static Network buildNetwork(NetworkBuildConfiguration config) {
		Network network = config.getNetwork();
		
		if (config.isUseBuildingStatistics()) {
			network.setNetworkBuildStatistics(new NetworkBuildStatistics());
			network.getNetworkBuildStatistics().setBuildStartTime(new Date().getTime());
		}
		
		for (int i = 0; i < config.getNodesCount(); i++) {
			int[] adjacentNodes;
			if (config.getMethodDriven() == NetworkBuildConfiguration.DEGREE_DRIVEN) {
				adjacentNodes = network.calculateAdjacentNodesDegreeDriven(config.getEdgesCount());
			} else if (config.getMethodDriven() == NetworkBuildConfiguration.CLUSTER_DRIVEN){
				adjacentNodes = network.calculateAdjacentNodesClusterDriven(config.getEdgesCount());
			} else {
				System.err.println("Method driven '" + config.getMethodDriven() + "' is not valid");
				return null;
			}
			network.addNode(i);
			for (int j = 0; j < adjacentNodes.length; j++) {
				network.addEdge(i, adjacentNodes[j]);
			}
			if (config.isUseBuildingStatistics() && i % NetworkBuildStatistics.BUILD_STATISTICS_ITERATION == 0) {
				if (config.getMethodDriven() == NetworkBuildConfiguration.DEGREE_DRIVEN) {
					network.getNetworkBuildStatistics().addAverageNodeDegreeValue(i, network.getAverageNodeDegree());
				} else if (config.getMethodDriven() == NetworkBuildConfiguration.CLUSTER_DRIVEN) {
					network.getNetworkBuildStatistics().addAverageClusterRatioValue(i, network.getAverageClusterRatio());
				}
			}
		}
		
		if (config.isUseBuildingStatistics()) {
			network.getNetworkBuildStatistics().setBuildEndTime(new Date().getTime());
		}
		return network;
	}
	
	protected int calculateNumberOfAllPossibleEdgesBetweenNodes(int numberOfNodes) {
		int count = ((numberOfNodes-1)*numberOfNodes) / 2;
		return count;
	}
	
	protected int[] calculateAdjacentNodesDegreeDriven(int nodesCountToCalculate, Network network) {		
		int[] allNodesIds = network.getNodesIds();
		int allNodesCount = allNodesIds.length;
		
		if (allNodesCount <= nodesCountToCalculate) {
			int[] result = new int[allNodesCount];
			for (int i = 0; i < allNodesCount; i++) {
				result[i] = allNodesIds[i];
			}
			return result;
		}
		
		int[] result = new int[nodesCountToCalculate];
		Arrays.fill(result, -1);
		int allEdgesCount = network.getNumberOfEdges();
		
		int numberOfCalculatedNodes = 0;
		double iteration = (double) allNodesCount / (double) (allEdgesCount*2);
		while (numberOfCalculatedNodes != nodesCountToCalculate) {
			double randomValue = Math.random() * allNodesCount;
			int areaCounter = 0;
			for (int i = 0; i < allNodesCount; i++) {
				int candidateNodeId = allNodesIds[i];
				int adjacentNodesCount = network.getAdjacentNodesCount(candidateNodeId);
				double rangeFrom = areaCounter * iteration;
				double rangeTo = (areaCounter + adjacentNodesCount) * iteration;
				if (randomValue >= rangeFrom && randomValue < rangeTo) {
					if (!CommonUtils.isNodeIdInNodesIdsArray(candidateNodeId, result)) {
						result[numberOfCalculatedNodes] = candidateNodeId;
						numberOfCalculatedNodes++;
					}
					break;
				}
				areaCounter += adjacentNodesCount;
			}
		}
		return result;
	}
	
	public int[] calculateAdjacentNodesClusterDriven(int nodesCountToCalculate, Network network) {
		int[] allNodesIds = network.getNodesIds();
		int allNodesCount = allNodesIds.length;
		
		if (allNodesCount <= nodesCountToCalculate) {
			int[] result = new int[allNodesCount];
			for (int i = 0; i < allNodesCount; i++) {
				result[i] = allNodesIds[i];
			}
			return result;
		}
		
		int[] result = new int[nodesCountToCalculate];
		Arrays.fill(result, -1);
		
		double[] clusterRatios = NetworkUtils.calculateClusterRatios(allNodesIds, network);
		double sumOfClusterRatios = CommonUtils.sumOfDoubleArray(clusterRatios);
		
		int numberOfCalculatedNodes = 0;
		while (numberOfCalculatedNodes != nodesCountToCalculate) {
			double randomValue = Math.random() * sumOfClusterRatios;
			double areaCounter = 0d;
			for (int i = 0; i < allNodesIds.length; i++) {
				int candidateNodeId = allNodesIds[i];
				double candidatesClusterRatio = clusterRatios[i];
				double rangeFrom = areaCounter;
				double rangeTo = areaCounter + candidatesClusterRatio;
				if (randomValue >= rangeFrom && randomValue < rangeTo) {
					if (!CommonUtils.isNodeIdInNodesIdsArray(candidateNodeId, result)) {
						result[numberOfCalculatedNodes] = candidateNodeId;
						numberOfCalculatedNodes++;
					}
					break;
				}
				areaCounter = rangeTo;
			}
		}
		return result;
	}

	protected NetworkBuildStatistics getBuildStatistics() {
		return buildStatistics;
	}

	protected void setBuildStatistics(NetworkBuildStatistics buildStatistics) {
		this.buildStatistics = buildStatistics;
	}
}
