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
import sk.sochuliak.barabasi.network.NetworkBase;

public class BarabasiDegreeDriven {
	
	public static final int NODES = 5000;
	public static final int EDGES = 2;
	
	Map<Integer, Double> degreeDistribution;
	
	public BarabasiDegreeDriven(int nodesCount, int edgesCount) {
		long startTime = new Date().getTime();
		Network network = MapNetwork.buildNetwork(nodesCount, edgesCount, NetworkBase.DEGREE_DRIVEN);
		long endTime = new Date().getTime();
		long time = endTime - startTime;
		System.out.println("Network builded by " + network.getClass().getName() + " in " + time + " ms.");
		this.degreeDistribution = NetworkAnalyse.getStandardizedDegreeDistribution(network);
	}
	
	private void showDegreeDistributionGraph() {
		Set<Integer> degrees = this.degreeDistribution.keySet();
		List<Integer> degreesList = new ArrayList<Integer>(degrees);
		Collections.sort(degreesList);
		
		List<double[]> points = new ArrayList<double[]>();
		for (int i = 0; i < degreesList.size(); i++) {
			double x = (double) degreesList.get(i);
			double y = this.degreeDistribution.get(degreesList.get(i));
			double logx = (x == 0) ? 0 : Math.log(x);
			double logy = (y == 0) ? 0 : Math.log(y);
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

	public static void main(String[] args) {
		BarabasiDegreeDriven barabasi = new BarabasiDegreeDriven(BarabasiDegreeDriven.NODES, BarabasiDegreeDriven.EDGES);
		barabasi.showDegreeDistributionGraph();
	}
}
