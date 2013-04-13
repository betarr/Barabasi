package sk.sochuliak.barabasi.network;

import java.util.Arrays;

public abstract class NetworkBase {
	
	protected int calculateNumberOfAllPossibleEdgesBetweenNodes(int numberOfNodes) {
		return ((numberOfNodes-1)*numberOfNodes) / 2;
	}
	
	public int[] calculateAdjacentNodesDegreeDriven(int nodesCountToCalculate, Network network) {		
		int[] allNodes = network.getNodesIds();
		int allNodesCount = allNodes.length;
		
		if (allNodesCount < nodesCountToCalculate) {
			int[] result = new int[allNodesCount];
			for (int i = 0; i < allNodesCount; i++) {
				result[i] = allNodes[i];
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
				int candidateNodeId = allNodes[i];
				int adjacentNodesCount = network.getAdjacentNodesCount(candidateNodeId);
				double rangeFrom = areaCounter * iteration;
				double rangeTo = (areaCounter + adjacentNodesCount) * iteration;
				if (randomValue >= rangeFrom && randomValue < rangeTo) {
					if (!this.isNodeIdInNodesIdsArray(candidateNodeId, result)) {
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
	
	private boolean isNodeIdInNodesIdsArray(int nodeId, int[] nodesIds) {
		for (int i = 0; i < nodesIds.length; i++) {
			if (nodesIds[i] == nodeId) {
				return true;
			}
		}
		return false;
	}

	public int[] calculateAdjacentNodesClusterDriven(int nodesCount) {
		// TODO Auto-generated method stub
		return null;
	}
}
