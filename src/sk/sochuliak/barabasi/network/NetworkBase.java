package sk.sochuliak.barabasi.network;

import java.util.Arrays;

import sk.sochuliak.barabasi.util.CommonUtils;

public abstract class NetworkBase {
	
	protected int calculateNumberOfAllPossibleEdgesBetweenNodes(int numberOfNodes) {
		return ((numberOfNodes-1)*numberOfNodes) / 2;
	}
	
	protected int[] calculateAdjacentNodesDegreeDriven(int nodesCountToCalculate, Network network) {		
		int[] allNodesIds = network.getNodesIds();
		int allNodesCount = allNodesIds.length;
		
		if (allNodesCount < nodesCountToCalculate) {
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
		while (numberOfCalculatedNodes != nodesCountToCalculate) {
			double randomValue = Math.random() * allNodesCount;
			double iteration = (double) allNodesCount / allEdgesCount*2;
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
						break;
					}
				}
				areaCounter += adjacentNodesCount;
			}
		}
		
		return null;
		
	}
	
	public int[] calculateAdjacentNodesClusterDriven(int nodesCountToCalculate, Network network) {
		int[] allNodesIds = network.getNodesIds();
		int allNodesCount = allNodesIds.length;
		
		if (allNodesCount < nodesCountToCalculate) {
			int[] result = new int[allNodesCount];
			for (int i = 0; i < allNodesCount; i++) {
				result[i] = allNodesIds[i];
			}
			return result;
		}
		
		int[] result = new int[nodesCountToCalculate];
		Arrays.fill(result, -1);
		
		double[] clasterRatios = this.calculateClasterRatios(allNodesIds, network);
		double sumOfClusterRatios = CommonUtils.sumOfDoubleArray(clasterRatios);
		
		int numberOfCalculatedNodes = 0;
		while (numberOfCalculatedNodes != nodesCountToCalculate) {
			double randomValue = Math.random() * sumOfClusterRatios;
			double areaCounter = 0d;
			for (int i = 0; i < allNodesIds.length; i++) {
				int candidateNodeId = allNodesIds[i];
				double candidatesClasterRatio = clasterRatios[i];
				double rangeFrom = areaCounter;
				double rangeTo = areaCounter + candidatesClasterRatio;
				if (randomValue >= rangeFrom && randomValue < rangeTo) {
					if (!CommonUtils.isNodeIdInNodesIdsArray(candidateNodeId, result)) {
						result[numberOfCalculatedNodes] = candidateNodeId;
						numberOfCalculatedNodes++;
						break;
					}
				}
				areaCounter = rangeTo;
			}
		}
		return result;
	}
	
	private double[] calculateClasterRatios(int[] nodesIds, Network network) {
		double[] result = new double[nodesIds.length];
		for (int i = 0; i < nodesIds.length; i++) {
			int nodeId = nodesIds[i];
			int[] adjacentNodes = network.getAdjacentNodesIds(nodeId);
			int numberOfExistingEdges = network.getNumberOfExistingEdgesBetweenNodes(adjacentNodes);
			int numberOfAllEdges = network.getNumberOfAllPossibleEdgesBetweenNodes(nodesIds);
			double clasterRatio = (double) numberOfExistingEdges / (double) numberOfAllEdges;
			result[i] = clasterRatio;
		}
		return result;
	}
}
