package sk.sochuliak.barabasi.network;

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
		int numberOfCalculatedNodes = 0;
		while (numberOfCalculatedNodes != nodesCountToCalculate) {
			double randomValue = Math.random() * allNodesCount;
			//double iteration = (double) allNodesCount / 
		}
		
		return null;
		
	}

	public int[] calculateAdjacentNodesClusterDriven(int notesCount) {
		// TODO Auto-generated method stub
		return null;
	}
}
