package sk.sochuliak.barabasi.network;

import java.util.Map;

public class MapNetwork extends NetworkBase implements Network {

	private Map<Integer, int[]> nodes;
	
	public static Network buildNetwork(int nodesCount, int edgesCount) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean addNode(int nodeId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addEdge(int nodeId1, int nodeId2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int[] calculateAdjacentNodesDegreeDriven(int notesCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] calculateAdjacentNodesClusterDriven(int notesCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumberOfExistingEdgesBetweenNodes(int[] nodesIds) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumberOfAllPossibleEdgesBetweenNodes(int[] nodesIds) {
		return this.calculateNumberOfAllPossibleEdgesBetweenNodes(nodesIds.length);
	}

	@Override
	public int[] getAdjacentNodesIds(int nodeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAdjacentNodesCount(int nodeId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEdgeBetweenNodes(int nodeId1, int nodeId2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getNumberOfNodes() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean containsNode(int nodeId) {
		// TODO Auto-generated method stub
		return false;
	}

}
