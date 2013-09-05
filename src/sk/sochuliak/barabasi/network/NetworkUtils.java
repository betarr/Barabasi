package sk.sochuliak.barabasi.network;

import sk.sochuliak.barabasi.util.CommonUtils;


public class NetworkUtils {

	public static double[] calculateClusterRatios(int[] nodesIds, Network network) {
		double[] result = new double[nodesIds.length];
		for (int i = 0; i < nodesIds.length; i++) {
			int nodeId = nodesIds[i];
			double clusterRatio = network.getClusterRatio(nodeId);
			result[i] = clusterRatio;
		}
		return result;
	}
	
	public static double calculateAverageClusteRatios(Network network) {
		double[] clusterRatios = NetworkUtils.calculateClusterRatios(network.getNodesIds(), network);
		double sumOfClusterRatios = CommonUtils.sumOfDoubleArray(clusterRatios);
		return sumOfClusterRatios / (double) clusterRatios.length;
	}
	
	public static double calculateAverageNodeDegree(Network network) {
		int sumOfNodeDegrees = 0;
		int[] nodeIds = network.getNodesIds();
		for (int nodeId : nodeIds) {
			sumOfNodeDegrees += network.getAdjacentNodesCount(nodeId);
		}
		return (double) sumOfNodeDegrees / (double) nodeIds.length;
	}
}
