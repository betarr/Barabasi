package sk.sochuliak.barabasi.runner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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

public class BarabasiClusterDriven {
	
	private static final int NODES = 3000;
	private static final int EDGES = 2;
	
	private static final boolean useBuildStatistics = false;
	
	private Network network;
	
	public BarabasiClusterDriven(int nodesCount, int edgesCount) {
//		System.out.println("START");
		long start = new Date().getTime();
		this.network = MapNetwork.buildNetwork(nodesCount, edgesCount,
				NetworkBuildConfiguration.CLUSTER_DRIVEN,
				BarabasiClusterDriven.useBuildStatistics);
		long end = new Date().getTime();
		System.out.println(this.getClass().getName() + "\t BarabasiClusterDriven \t " + (end-start));
	}
	
	private void showClusterDistributionGraph() {
		Map<Integer, Double> clusterDistribution = NetworkAnalyse.getClusterDistribution(this.network);
		Set<Integer> degrees = clusterDistribution.keySet();
		List<Integer> degreesList = new ArrayList<Integer>(degrees);
		Collections.sort(degreesList);
		
		List<double[]> points = new ArrayList<double[]>();
		for (int i = 0; i < degreesList.size(); i++) {
			double x = (double) degreesList.get(i);
			double y = clusterDistribution.get(degreesList.get(i));
			double logx = (x == 0) ? 0 : Math.log10(x);
			double logy = (y == 0) ? 0 : Math.log10(y);
			points.add(new double[]{logx, logy});
		}
		
		Map<String, List<double[]>> data = new HashMap<String, List<double[]>>();
		data.put("Distribucia klasterizacie uzlov", points);
		
		GraphConfiguration config = GraphConfiguration.createConfiguration()
			.setTitle("Distribucia priemernych klasterizacnych keoficientov")
			.setxAxisLabel("Stupen vrchola")
			.setyAxisLabel("Priemerna klasterizacia uzla stupna k")
			.setData(data);
		Graph.showGraph(config);
	}
	
	private void showBuildStatisticsInfo() {
		System.out.println("Network builded by " + this.network.getClass().getName() + " in " + this.network.getNetworkBuildStatistics().getBuildTotalTime() + " ms.");
		Map<Integer, Double> averageClusterRatio = this.network.getNetworkBuildStatistics().getAverageClusterRatio();
		
		List<double[]> points = new ArrayList<double[]>();
		for (int nodesCount : averageClusterRatio.keySet()) {
			points.add(new double[]{
					(double) nodesCount,
					averageClusterRatio.get(nodesCount)});
		}
		
		Map<String, List<double[]>> data = new HashMap<String, List<double[]>>();
		data.put("Priemerny klasterizacny koeficient", points);
		
		GraphConfiguration config = GraphConfiguration.createConfiguration()
				.setTitle("Zmena priemerneho klasterizacneho koeficientu pocas rastu siete")
				.setxAxisLabel("Pocet uzlov")
				.setyAxisLabel("Priemerny klasterizacny koeficient")
				.setData(data);
		Graph.showGraph(config);
	}
	
	public static void main(String[] args) {
		BarabasiClusterDriven barabasi = new BarabasiClusterDriven(BarabasiClusterDriven.NODES, BarabasiClusterDriven.EDGES);
		barabasi.showClusterDistributionGraph();
		if (BarabasiClusterDriven.useBuildStatistics) {
			barabasi.showBuildStatisticsInfo();
		}
	}
}
