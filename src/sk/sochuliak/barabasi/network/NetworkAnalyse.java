package sk.sochuliak.barabasi.network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NetworkAnalyse {
	
	public static Map<Integer, List<Integer>> getDegreeDistribution(Network network) {
		Map<Integer, List<Integer>> result = new HashMap<Integer, List<Integer>>();
		
		int[] nodesIds = network.getNodesIds();
		for (int nodeId : nodesIds) {
			int adjacentNodesCount = network.getAdjacentNodesCount(nodeId);
			if (result.get(adjacentNodesCount) == null) {
				result.put(adjacentNodesCount, new ArrayList<Integer>());
			}
			result.get(adjacentNodesCount).add(nodeId);
		}
		return result;
	}

	public static Map<Integer, Double> getStandardizedDegreeDistribution(Network network) {
		Map<Integer, List<Integer>> degreeDistribution = NetworkAnalyse.getDegreeDistribution(network);
		
		double nodesCountAsDouble = (double) network.getNumberOfNodes();
		
		Map<Integer, Double> result = new HashMap<Integer, Double>();
		for (Integer nodeDegree : degreeDistribution.keySet()) {
			result.put(nodeDegree, (double) degreeDistribution.get(nodeDegree).size() / nodesCountAsDouble);
		}
		return result;
	}
}
