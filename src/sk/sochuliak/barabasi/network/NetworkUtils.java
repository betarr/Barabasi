package sk.sochuliak.barabasi.network;


public class NetworkUtils {

	public static double[] calculateClusterRatios(int[] nodesIds, Network network) {
		double[] result = new double[nodesIds.length];
		for (int i = 0; i < nodesIds.length; i++) {
			int nodeId = nodesIds[i];
			int[] adjacentNodes = network.getAdjacentNodesIds(nodeId);
			int numberOfExistingEdges = network.getNumberOfExistingEdgesBetweenNodes(adjacentNodes);
			int numberOfAllEdges = network.getNumberOfAllPossibleEdgesBetweenNodes(nodesIds);
			double clusterRatio = (double) numberOfExistingEdges / (double) numberOfAllEdges;
			result[i] = clusterRatio;
		}
		return result;
	}
}
