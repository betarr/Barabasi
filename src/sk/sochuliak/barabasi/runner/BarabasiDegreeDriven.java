package sk.sochuliak.barabasi.runner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import sk.sochuliak.barabasi.graph.Graph;
import sk.sochuliak.barabasi.graph.GraphConfiguration;
import sk.sochuliak.barabasi.network.MapNetwork;
import sk.sochuliak.barabasi.network.Network;
import sk.sochuliak.barabasi.network.NetworkAnalyse;
import sk.sochuliak.barabasi.network.NetworkBuildConfiguration;

public class BarabasiDegreeDriven {
	
	private static final int NODES = 5000;
	private static final int EDGES = 2;
	
	private static final boolean useBuildStatistics = false;
	
	private Network network;
	
	public BarabasiDegreeDriven(int nodesCount, int edgesCount) {
		this.network = MapNetwork.buildNetwork(nodesCount, edgesCount,
				NetworkBuildConfiguration.DEGREE_DRIVEN,
				BarabasiDegreeDriven.useBuildStatistics);
	}
	
	private void showDegreeDistributionGraph() {
		Map<Integer, Double> degreeDistribution = NetworkAnalyse.getStandardizedDegreeDistribution(this.network);
		Set<Integer> degrees = degreeDistribution.keySet();
		List<Integer> degreesList = new ArrayList<Integer>(degrees);
		Collections.sort(degreesList);
		
		List<double[]> points = new ArrayList<double[]>();
		for (int i = 0; i < degreesList.size(); i++) {
			double x = (double) degreesList.get(i);
			double y = degreeDistribution.get(degreesList.get(i));
			double logx = (x == 0) ? 0 : Math.log10(x);
			double logy = (y == 0) ? 0 : Math.log10(y);
			points.add(new double[]{logx, logy});
		}
		
		Map<String, List<double[]>> data = new HashMap<String, List<double[]>>();
		data.put("Distribucia stupna uzlov", points);
		
		GraphConfiguration config = GraphConfiguration.createConfiguration()
			.setTitle("Distribucia stupna uzlov")
			.setxAxisLabel("Stupen vrchola")
			.setyAxisLabel("Normovany #uzlov stupna k")
			.setData(data);
		Graph.showGraph(config);
	}
	
	private void showBuildStatisticsInfo() {
		System.out.println("Network builded by " + this.network.getClass().getName() + " in " + this.network.getNetworkBuildStatistics().getBuildTotalTime() + " ms.");
		Map<Integer, Double> averageNodeDegree = this.network.getNetworkBuildStatistics().getAverageNodeDegree();
		
		List<double[]> points = new ArrayList<double[]>();
		for (int nodesCount : averageNodeDegree.keySet()) {
			points.add(new double[]{
					(double) nodesCount,
					averageNodeDegree.get(nodesCount)});
		}
		
		Map<String, List<double[]>> data = new HashMap<String, List<double[]>>();
		data.put("Priemerny stupen uzla", points);
		
		GraphConfiguration config = GraphConfiguration.createConfiguration()
				.setTitle("Zmena priemerneho stupna uzla pocas rastu siete")
				.setxAxisLabel("Pocet uzlov")
				.setyAxisLabel("Priemerny stupen uzla")
				.setData(data);
		Graph.showGraph(config);
	}

	public static void main(String[] args) {
		BarabasiDegreeDriven barabasi = new BarabasiDegreeDriven(BarabasiDegreeDriven.NODES, BarabasiDegreeDriven.EDGES);
		barabasi.showDegreeDistributionGraph();
		if (BarabasiDegreeDriven.useBuildStatistics) {
			barabasi.showBuildStatisticsInfo();
		}
	}
}
