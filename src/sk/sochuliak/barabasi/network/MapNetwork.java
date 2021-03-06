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
	
	/**
	 * Build network with specific parameters.
	 * 
	 * @param nodesCount Number of nodes in network
	 * @param edgesCount Number of edges every node should have
	 * @param methodDriven Method of preferential selection of nodes to connect
	 * @return Network
	 */
	public static Network buildNetwork(int nodesCount, int edgesCount, int methodDriven, boolean useBuildingStatistics) {
//		long start = new Date().getTime();
		NetworkBuildConfiguration config = NetworkBuildConfiguration.getInstance()
			.setNetwork(new MapNetwork())
			.setNodesCount(nodesCount)
			.setEdgesCount(edgesCount)
			.setMethodDriven(methodDriven)
			.setUseBuildingStatistics(useBuildingStatistics);
		Network network = NetworkBase.buildNetwork(config);
//		long end = new Date().getTime();
//		if ((end - start > 0)) {
//			System.out.println("MapNetwork \t buildNetwork \t " + (end - start));
//		}
		return network;
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
	public int[] calculateAdjacentNodesDegreeDriven(int nodesCount) {
		int[] adjacentNodesDegreeDriven = this.calculateAdjacentNodesDegreeDriven(nodesCount, this);
		return adjacentNodesDegreeDriven;
	}

	@Override
	public int[] calculateAdjacentNodesClusterDriven(int nodesCount) {
		int[] adjacentNodesClusterDriven = this.calculateAdjacentNodesClusterDriven(nodesCount, this);
		return adjacentNodesClusterDriven;
	}

	@Override
	public int getNumberOfExistingEdgesBetweenNodes(int[] nodesIds) {
		int numberOfEdges = 0;
		for (int nodeId1 : nodesIds) {
			for (int nodeId2 : nodesIds) {
				numberOfEdges += this.nodes.get(nodeId1).contains(nodeId2) ? 1 : 0;
			}
		}
		return numberOfEdges / 2;
	}

	@Override
	public int getNumberOfAllPossibleEdgesBetweenNodes(int[] nodesIds) {
		int count = this.calculateNumberOfAllPossibleEdgesBetweenNodes(nodesIds.length);
		return count;
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
		int numberOfNodes = this.nodes.size();
		return numberOfNodes;
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
		int result = 0;
		for (Integer nodeId : this.nodes.keySet()) {
			result += this.nodes.get(nodeId).size();
		}
		return result / 2;
	}
	
	@Override
	public double getClusterRatio(int nodeId) {
		int[] adjacentNodesIds = this.getAdjacentNodesIds(nodeId);
		int existingEdges = this.getNumberOfExistingEdgesBetweenNodes(adjacentNodesIds);
		int allPossibleEdges = this.getNumberOfAllPossibleEdgesBetweenNodes(adjacentNodesIds);
		if (allPossibleEdges == 0) {
			return 0;
		}
		return (double)existingEdges / (double)allPossibleEdges;
	}

	@Override
	public double getAverageNodeDegree() {
		return NetworkUtils.calculateAverageNodeDegree(this);
	}

	@Override
	public double getAverageClusterRatio() {
		return NetworkUtils.calculateAverageClusteRatios(this);
	}

	@Override
	public void setNetworkBuildStatistics(
			NetworkBuildStatistics networkBuildStatistics) {
		this.setBuildStatistics(networkBuildStatistics);
	}

	@Override
	public NetworkBuildStatistics getNetworkBuildStatistics() {
		return this.getBuildStatistics();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Integer node : this.nodes.keySet()) {
			sb.append(node).append(":");
			for (Integer neighbour : this.nodes.get(node)) {
				sb.append(" ").append(neighbour).append(",");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
