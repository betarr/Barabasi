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
import sk.sochuliak.barabasi.network.Network;
import sk.sochuliak.barabasi.network.NetworkAnalyse;
import sk.sochuliak.barabasi.network.NetworkBase;
import sk.sochuliak.barabasi.network.ObjectedNetwork;

public class BarabasiClusterDriven {
	
	public static final int NODES = 500;
	public static final int EDGES = 2;
	
	Map<Integer, Double> clusterDistribution;

	public BarabasiClusterDriven(int nodesCount, int edgesCount) {
		long startTime = new Date().getTime();
		Network network = ObjectedNetwork.buildNetwork(nodesCount, edgesCount, NetworkBase.CLUSTER_DRIVEN);
		long endTime = new Date().getTime();
		long time = endTime - startTime;
		System.out.println("Network builded by " + network.getClass().getName() + " in " + time + " ms.");
		this.clusterDistribution = NetworkAnalyse.getClusterDistribution(network);
	}
	
	private void showClusterDistributionGraph() {
		Set<Integer> degrees = this.clusterDistribution.keySet();
		List<Integer> degreesList = new ArrayList<Integer>(degrees);
		Collections.sort(degreesList);
		
		List<double[]> points = new ArrayList<double[]>();
		for (int i = 0; i < degreesList.size(); i++) {
			double x = (double) degreesList.get(i);
			double y = this.clusterDistribution.get(degreesList.get(i));
			double logx = (x == 0) ? 0 : Math.log(x);
			double logy = (y == 0) ? 0 : Math.log(y);
			points.add(new double[]{logx, logy});
		}
		
		Map<String, List<double[]>> data = new HashMap<String, List<double[]>>();
		data.put("Distribucia klasterizacie uzlov", points);
		
		GraphConfiguration config = GraphConfiguration.createConfiguration()
			.setTitle("Distribucia stupna uzlov")
			.setxAxisLabel("Stupen vrchola")
			.setyAxisLabel("Priemerna klasterizacia uzla stupna k")
			.setData(data);
		Graph.showGraph(config);
	}
	
	public static void main(String[] args) {
		BarabasiClusterDriven barabasi = new BarabasiClusterDriven(BarabasiClusterDriven.NODES, BarabasiClusterDriven.EDGES);
		barabasi.showClusterDistributionGraph();
	}
}
