package sk.sochuliak.barabasi.network;

import java.util.ArrayList;
import java.util.Date;
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
//		long start = new Date().getTime();
		int[] adjacentNodesDegreeDriven = this.calculateAdjacentNodesDegreeDriven(nodesCount, this);
//		long end = new Date().getTime();
//		if ((end-start > 0)) {
//			System.out.println(this.getClass().getName() + "\t calculateAdjacentNodesDegreeDriven \t " + (end-start));
//		}
		return adjacentNodesDegreeDriven;
	}

	@Override
	public int[] calculateAdjacentNodesClusterDriven(int nodesCount) {
//		long start = new Date().getTime();
		int[] adjacentNodesClusterDriven = this.calculateAdjacentNodesClusterDriven(nodesCount, this);
//		long end = new Date().getTime();
//		if ((end-start > 0)) {
//			System.out.println(this.getClass().getName() + "\t calculateAdjacentNodesClusterDriven \t " + (end-start));
//		}
		return adjacentNodesClusterDriven;
	}

	@Override
	public int getNumberOfExistingEdgesBetweenNodes(int[] nodesIds) {
//		long start = new Date().getTime();
		int numberOfEdges = 0;
		for (int nodeId1 : nodesIds) {
			for (int nodeId2 : nodesIds) {
				numberOfEdges += this.nodes.get(nodeId1).contains(nodeId2) ? 1 : 0;
			}
		}
//		long end = new Date().getTime();
//		if ((end-start > 0)) {
//			System.out.println(this.getClass().getName() + "\t getNumberOfExistingEdgesBetweenNodes \t " + (end-start));
//		}
		return numberOfEdges / 2;
	}

	@Override
	public int getNumberOfAllPossibleEdgesBetweenNodes(int[] nodesIds) {
//		long start = new Date().getTime();
		int count = this.calculateNumberOfAllPossibleEdgesBetweenNodes(nodesIds.length);
//		long end = new Date().getTime();
//		if ((end-start > 0)) {
//			System.out.println(this.getClass().getName() + "\t getNumberOfAllPossibleEdgesBetweenNodes \t " + (end-start));
//		}
		return count;
	}

	@Override
	public int[] getAdjacentNodesIds(int nodeId) {
//		long start = new Date().getTime();
		int[] result = new int[this.getAdjacentNodesCount(nodeId)];
		if (result.length > 0) {
			int pointer = 0;
			for (Integer adjacentNode : this.nodes.get(nodeId)) {
				result[pointer] = adjacentNode;
				pointer++;
			}
		}
//		long end = new Date().getTime();
//		if ((end-start > 0)) {
//			System.out.println(this.getClass().getName() + "\t getAdjacentNodesIds \t " + (end-start));
//		}
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
//		long start = new Date().getTime();
		int numberOfNodes = this.nodes.size();
//		long end = new Date().getTime();
//		if ((end-start > 0)) {
//			System.out.println(this.getClass().getName() + "\t getNumberOfNodes \t " + (end-start));
//		}
		return numberOfNodes;
	}

	@Override
	public boolean containsNode(int nodeId) {
		return this.nodes.get(nodeId) != null;
	}
	
	@Override
	public int[] getNodesIds() {
//		long start = new Date().getTime();
		int[] result = new int[this.getNumberOfNodes()];
		int pointer = 0;
		for (Integer nodeId : this.nodes.keySet()) {
			result[pointer] = nodeId;
			pointer++;
		}
//		long end = new Date().getTime();
//		if ((end-start > 0)) {
//			System.out.println(this.getClass().getName() + "\t getNodesIds \t " + (end-start));
//		}
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
}
