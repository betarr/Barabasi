package sk.sochuliak.barabasi.network;

import java.util.ArrayList;
import java.util.List;

public class ObjectedNetwork extends NetworkBase implements Network {

	private List<ObjectedNode> nodes;
	
	public ObjectedNetwork() {
		this.nodes = new ArrayList<ObjectedNode>();
	}
	
	/**
	 * Build network with specific parameters.
	 * 
	 * @param nodesCount Number of nodes in network
	 * @param edgesCount Number of edges every node should have
	 * @param methodDriven Method of preferential selection of nodes to connect
	 * @return Network
	 */
	public static Network buildNetwork(int nodesCount, int edgesCount, int methodDriven, boolean useBuildingStatistics) {
		NetworkBuildConfiguration config = NetworkBuildConfiguration.getInstance()
			.setNetwork(new ObjectedNetwork())
			.setNodesCount(nodesCount)
			.setEdgesCount(edgesCount)
			.setMethodDriven(methodDriven)
			.setUseBuildingStatistics(useBuildingStatistics);
		return NetworkBase.buildNetwork(config);
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
	public int[] calculateAdjacentNodesDegreeDriven(int nodesCount) {
		return this.calculateAdjacentNodesDegreeDriven(nodesCount, this);
	}

	@Override
	public int[] calculateAdjacentNodesClusterDriven(int nodesCount) {
		return this.calculateAdjacentNodesClusterDriven(nodesCount, this);
	}

	@Override
	public int getNumberOfExistingEdgesBetweenNodes(int[] nodesIds) {
		ObjectedNode[] nodes = this.getNodesByIds(nodesIds);
		
		int numberOfEdges = 0;
		
		for (ObjectedNode node1 : nodes) {
			for (ObjectedNode node2 : nodes) {
				numberOfEdges += node1.hasEdgeTo(node2) ? 1 : 0;
			}
		}
		return numberOfEdges / 2;
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
	
	@Override
	public int[] getNodesIds() {
		int[] result = new int[this.getNumberOfNodes()];
		int pointer = 0;
		for (ObjectedNode node : this.nodes) {
			result[pointer] = node.getId();
			pointer++;
		}
		return result;
	}
	
	@Override
	public int getNumberOfEdges() {
		int result = 0;
		for (ObjectedNode node : this.nodes) {
			result += node.getAdjacentNodesCount();
		}
		return result / 2;
	}
	
	@Override
	public double getAverageNodeDegree() {
		return NetworkUtils.calculateAverageNodeDegree(this);
	}

	@Override
	public double getAverageClusterCoefficient() {
		return NetworkUtils.calculateAverageClusteRatios(this);
	}
	
	/**
	 * Returns node identified by nodeId.
	 * 
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
	
	/**
	 * Returns array of nodes by their ids.
	 * 
	 * @param nodesIds Nodes ids
	 * @return Array of nodes
	 */
	private ObjectedNode[] getNodesByIds(int[] nodesIds) {
		ObjectedNode[] result = new ObjectedNode[nodesIds.length];
		for (int i = 0; i < nodesIds.length; i++) {
			result[i] = this.getNodeById(nodesIds[i]);
		}
		return result;
	}
}
