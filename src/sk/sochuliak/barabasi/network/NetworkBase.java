package sk.sochuliak.barabasi.network;

public abstract class NetworkBase {
	
	protected int calculateNumberOfAllPossibleEdgesBetweenNodes(int numberOfNodes) {
		return ((numberOfNodes-1)*numberOfNodes) / 2;
	}
	
	public int[] calculateAdjacentNodesDegreeDriven(int nodesCountToCalculate, Network network) {
		int[] adjacentNodesIds = new int[nodesCountToCalculate];
		
		int allNodesCount = network.getNumberOfNodes();
		if (allNodesCount < nodesCountToCalculate) {
			int pointer = 0;
			//for (network.getn)
		}
		
		int numberOfCalculatedNodes = 0;		
		
		return null;
		
	}

	public int[] calculateAdjacentNodesClusterDriven(int notesCount) {
		// TODO Auto-generated method stub
		return null;
	}
}
