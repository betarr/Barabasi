package sk.sochuliak.barabasi.network;

public interface Network {

	//public static Network buildNetwork(int edgeCount, int nodesCount);
	
	/**
	 * Adds node to network identified by its id.
	 * 
	 * @param nodeId Id of node
	 * @return True if node successfully added, false otherwise
	 */
	public boolean addNode(int nodeId);
	
	/**
	 * Adds edge between two nodes identified by their ids.
	 * 
	 * @param nodeId1 Id of first node
	 * @param nodeId2 Id of second node
	 * @return True if edge successfully added, false otherwise
	 */
	public boolean addEdge(int nodeId1, int nodeId2);
	
	/**
	 * Returns array of node ids calculated by probability depending on degree of nodes. 
	 * 
	 * @param nodesCount Number of calculated nodes
	 * @return array of node ids
	 */
	public int[] calculateAdjacentNodesDegreeDriven(int nodesCount);
	
	/**
	 * Returns array of node ids calculated by probability depending on cluster of nodes.
	 * 
	 * @param nodesCount Number of calculated nodes 
	 * @return array of node ids
	 */
	public int[] calculateAdjacentNodesClusterDriven(int nodesCount);
	
	/**
	 * Returns number of existing edges between nodes identified by their ids.
	 * 
	 * @param nodesIds Ids of nodes
	 * @return Number of existing edges
	 */
	public int getNumberOfExistingEdgesBetweenNodes(int[] nodesIds);
	
	/**
	 * Returns number of all possible edges between nodes identified by their ids.
	 * 
	 * @param nodesIds Ids of nodes
	 * @return Number of all possible edges
	 */
	public int getNumberOfAllPossibleEdgesBetweenNodes(int[] nodesIds);
	
	/**
	 * Returns ids of all nodes connected by edge to given node identified by its id.
	 * @param nodeId Id of node
	 * @return Array of connected nodes
	 */
	public int[] getAdjacentNodesIds(int nodeId);
	
	/**
	 * Returns number of nodes connected by edge to given node identified by its id.
	 * 
	 * @param nodeId Id of node
	 * @return Number of connected nodes
	 */
	public int getAdjacentNodesCount(int nodeId);
	
	/**
	 * Checks if there is an edge between two nodes identified by their ids.
	 * 
	 * @param nodeId1 Id of node 1.
	 * @param nodeId2 Id of node 2.
	 * @return True if edge exists, false otherwise
	 */
	public boolean isEdgeBetweenNodes(int nodeId1, int nodeId2);
	
	/**
	 * Returns number of nodes in network.
	 * 
	 * @return Number of nodes
	 */
	public int getNumberOfNodes();
	
	/**
	 * Checks if network contains node identified by its id.
	 * 
	 * @param nodeId Id of node
	 * @return True if network contains node, false otherwise
	 */
	public boolean containsNode(int nodeId);
	
	/**
	 * Returns ids of all nodes.
	 * 
	 * @return Ids of nodes
	 */
	public int[] getNodesIds();
	
	/**
	 * Returns number of edges in network.
	 */
	public int getNumberOfEdges();
	
	
	/**
	 * Returns cluster ratio of given node identified by its id.
	 * 
	 * @param nodeId nodeId Id of node
	 * @return Cluster ratio of node
	 */
	public double getClusterRatio(int nodeId);
	
	/**
	 * Returns average node degree value.
	 * @return
	 */
	public double getAverageNodeDegree();
	
	/**
	 * Returns average cluster coefficient value.
	 * @return
	 */
	public double getAverageClusterRatio();

	/**
	 * Setter for build statistics.
	 * 
	 * @param networkBuildStatistics buildStatistics
	 */
	public void setNetworkBuildStatistics(NetworkBuildStatistics networkBuildStatistics);
	
	/**
	 * Getter for build statistics.
	 * 
	 * @return Build statistics
	 */
	public NetworkBuildStatistics getNetworkBuildStatistics();
}
