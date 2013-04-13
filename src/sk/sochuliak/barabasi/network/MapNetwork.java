package sk.sochuliak.barabasi.network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapNetwork extends NetworkBase implements Network {

	private Map<Integer, List<Integer>> nodes;
	
	public MapNetwork() {
		this.nodes = new HashMap<Integer, List<Integer>>();
	}
	
	public static Network buildNetwork(int nodesCount, int edgesCount) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean addNode(int nodeId) {
		if (this.nodes.get(nodeId) != null) {
			return false;
		}
		
		this.nodes.put(nodeId, new ArrayList<Integer>());
		return true;
	}

	@Override
	public boolean addEdge(int nodeId1, int nodeId2) {
		if (nodeId1 != nodeId2 && this.containsNode(nodeId1) && this.containsNode(nodeId2)) {
			if (!this.isEdgeBetweenNodes(nodeId1, nodeId2)) {
				this.nodes.get(nodeId1).add(nodeId2);
				this.nodes.get(nodeId2).add(nodeId1);
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
		int[] result = new int[this.getAdjacentNodesCount(nodeId)];
		if (result.length > 0) {
			int pointer = 0;
			for (Integer adjacentNode : this.nodes.get(nodeId)) {
				result[pointer] = adjacentNode;
				pointer++;
			}
		}
		return result;
	}

	@Override
	public int getAdjacentNodesCount(int nodeId) {
		return this.nodes.get(nodeId).size();
	}

	@Override
	public boolean isEdgeBetweenNodes(int nodeId1, int nodeId2) {
		return this.nodes.get(nodeId1).contains(nodeId2);
	}

	@Override
	public int getNumberOfNodes() {
		return this.nodes.size();
	}

	@Override
	public boolean containsNode(int nodeId) {
		return this.nodes.get(nodeId) != null;
	}
	
	@Override
	public int[] getNodesIds() {
		int[] result = new int[this.getNumberOfNodes()];
		int pointer = 0;
		for (Integer nodeId : this.nodes.keySet()) {
			result[pointer] = nodeId;
			pointer++;
		}
		return result;
	}
	
	@Override
	public int getNumberOfEdges() {
		return this.getNumberOfExistingEdgesBetweenNodes(this.getNodesIds());
	}
}
