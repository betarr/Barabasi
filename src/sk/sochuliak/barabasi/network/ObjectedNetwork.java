package sk.sochuliak.barabasi.network;

import java.util.ArrayList;
import java.util.List;

public class ObjectedNetwork extends NetworkBase implements Network {

	private List<ObjectedNode> nodes;
	
	public ObjectedNetwork() {
		this.nodes = new ArrayList<ObjectedNode>();
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
		
		this.nodes.add(new ObjectedNode(nodeId));
		return true;
	}

	@Override
	public boolean addEdge(int nodeId1, int nodeId2) {	
		if (nodeId1 == nodeId2) {
			return false;
		}
		
		ObjectedNode node1 = this.getNodeById(nodeId1);
		ObjectedNode node2 = this.getNodeById(nodeId2);
		if (node1 != null && node2 != null) {
			if (!this.isEdgeBetweenNodes(nodeId1, nodeId2)) {
				node1.addEdge(node2);
				node2.addEdge(node1);
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
		ObjectedNode node = this.getNodeById(nodeId);
		int[] result = new int[node.getAdjacentNodesCount()];
		if (result.length > 0) {
			int pointer = 0;
			List<ObjectedNode> adjacentNodes = node.getAdjacentNodes();
			for (ObjectedNode adjacentNode : adjacentNodes) {
				result[pointer] = adjacentNode.getId();
				pointer++;
			}
		}
		return result;
	}

	@Override
	public int getAdjacentNodesCount(int nodeId) {
		return this.getNodeById(nodeId).getAdjacentNodesCount();
	}

	@Override
	public boolean isEdgeBetweenNodes(int nodeId1, int nodeId2) {
		return this.getNodeById(nodeId1).hasEdgeTo(this.getNodeById(nodeId2));
	}

	@Override
	public int getNumberOfNodes() {
		return this.nodes.size();
	}

	@Override
	public boolean containsNode(int nodeId) {
		for (ObjectedNode node : this.nodes) {
			if (node.getId() == nodeId) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns node identified by nodeId.
	 * @param nodeId Id of node
	 * @return Node if exists, null otherwise
	 */
	private ObjectedNode getNodeById(int nodeId) {
		for (ObjectedNode node : this.nodes) {
			if (node.getId() == nodeId) {
				return node;
			}
		}
		return null;
	}

}
