package sk.sochuliak.barabasi.network;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NodeToNodeNetwork extends NetworkBase implements Network {
	
	/**
	 * Initial size of nodesIds array.
	 */
	private static final int INITIAL_SIZE = 1;
	
	/**
	 * Ids of nodes.
	 */
	private int[] nodesIds;
	
	/**
	 * Pairs of ids of nodes representing edge.
	 */
	private List<int[]> edges;
	
	/**
	 * Number of nodes.
	 */
	private int numberOfNodes = 0;
	
	public NodeToNodeNetwork() {
		this.nodesIds = new int[NodeToNodeNetwork.INITIAL_SIZE];
		Arrays.fill(this.nodesIds, -1);
		this.edges = new ArrayList<int[]>();
	}
	
	public static Network buildNetwork(int nodesCount, int edgesCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addNode(int nodeId) {
		if (this.containsNode(nodeId)) {
			return false;
		}
		
		if (this.numberOfNodes == this.nodesIds.length) {
			this.appendDoubleSizeOfNodesIds();
		}
		this.nodesIds[this.numberOfNodes] = nodeId;
		this.numberOfNodes++;
		return true;
	}

	@Override
	public boolean addEdge(int nodeId1, int nodeId2) {
		if (nodeId1 != nodeId2 && this.containsNode(nodeId1) && this.containsNode(nodeId2)) {
			if (!this.isEdgeBetweenNodes(nodeId1, nodeId2)) {
				this.edges.add(new int[]{nodeId1, nodeId2});
				return true;
			}
		}
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
		int numberOfEdges = 0;
		List<String> countedEdges = new ArrayList<String>();
		
		for (int nodeId1 : nodesIds) {
			for (int nodeId2 : nodesIds) {
				if (nodeId1 != nodeId2) {
					
					String edgeId = "";
					if (nodeId1 < nodeId2) {
						edgeId = nodeId1 + "|" + nodeId2;
					} else {
						edgeId = nodeId2 + "|" + nodeId1;
					}
					
					if (!countedEdges.contains(edgeId)) {
						if (this.isEdgeBetweenNodes(nodeId1, nodeId2)) {
							numberOfEdges++;
							countedEdges.add(edgeId);
						}
					}
				}
			}
		}
		return numberOfEdges;
	}

	@Override
	public int getNumberOfAllPossibleEdgesBetweenNodes(int[] nodesIds) {
		return this.calculateNumberOfAllPossibleEdgesBetweenNodes(nodesIds.length);
	}

	@Override
	public int[] getAdjacentNodesIds(int nodeId) {
		int adjacentNodesCount = this.getAdjacentNodesCount(nodeId);
		int[] result = new int[adjacentNodesCount];
		if (adjacentNodesCount > 0) {
			int pointer = 0;
			for (int[] edge : this.edges) {
				if (edge[0] == nodeId) {
					result[pointer] = edge[1];
					pointer++;
				}else if (edge[1] == nodeId) {
					result[pointer] = edge[0];
					pointer++;
				}
			}
		}
		return result;
	}

	@Override
	public int getAdjacentNodesCount(int nodeId) {
		int result = 0;
		for (int[] edge : this.edges) {
			if (edge[0] == nodeId || edge[1] == nodeId) {
				result ++;
			}
		}
		return result;
	}

	@Override
	public boolean isEdgeBetweenNodes(int nodeId1, int nodeId2) {
		for (int[] edge : this.edges) {
			if (edge[0] == nodeId1 && edge[1] == nodeId2) {
				return true;
			} else if (edge[0] == nodeId2 && edge[1] == nodeId1) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int getNumberOfNodes() {
		return this.numberOfNodes;
	}

	@Override
	public boolean containsNode(int nodeId) {
		for (int i = 0; i < this.numberOfNodes; i++) {
			if (this.nodesIds[i] == nodeId) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Makes nodesIds array two times larger.
	 */
	private void appendDoubleSizeOfNodesIds() {
		int oldSize = this.nodesIds.length;
		int newSize = oldSize * 2;
		int[] newNodesIds = new int[newSize];
		for (int i = 0; i < newSize; i++) {
			newNodesIds[i] = (i < oldSize) ? this.nodesIds[i] : -1;
		}
		this.nodesIds = newNodesIds;
	}

	@Override
	public int[] getNodesIds() {
		int[] result = new int[this.getNumberOfNodes()];
		for (int i = 0; i < this.numberOfNodes; i++) {
			result[i] = this.nodesIds[i];
		}
		return result;
	}

}
