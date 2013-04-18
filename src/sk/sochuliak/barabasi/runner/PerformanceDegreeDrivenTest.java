package sk.sochuliak.barabasi.runner;

import java.util.Date;

import sk.sochuliak.barabasi.network.ArrayNetwork;
import sk.sochuliak.barabasi.network.MapNetwork;
import sk.sochuliak.barabasi.network.Network;
import sk.sochuliak.barabasi.network.NetworkBase;
import sk.sochuliak.barabasi.network.NodeToNodeNetwork;
import sk.sochuliak.barabasi.network.ObjectedNetwork;

public class PerformanceDegreeDrivenTest {
	private static final int NODES = 5000;
	private static final int EDGES = 2;

	public static void main(String[] args) {
//		PerformanceDegreeDrivenTest.buildArrayNetwork();
		PerformanceDegreeDrivenTest.buildMapNetwork();
//		PerformanceDegreeDrivenTest.buildNodeToNodeNetwork();
//		PerformanceDegreeDrivenTest.buildObjectedNetwork();
	}
	
	public static void buildArrayNetwork() {
		long start = new Date().getTime();
		Network network = ArrayNetwork.buildNetwork(PerformanceDegreeDrivenTest.NODES, PerformanceDegreeDrivenTest.EDGES, NetworkBase.DEGREE_DRIVEN);
		long end = new Date().getTime();
		long time = end - start;
		System.out.println("Network build by " + network.getClass().getName() + " in " + time + " ms");
	}
	
	public static void buildMapNetwork() {
		long start = new Date().getTime();
		Network network = MapNetwork.buildNetwork(PerformanceDegreeDrivenTest.NODES, PerformanceDegreeDrivenTest.EDGES, NetworkBase.DEGREE_DRIVEN);
		long end = new Date().getTime();
		long time = end - start;
		System.out.println("Network build by " + network.getClass().getName() + " in " + time + " ms");
	}
	
	public static void buildNodeToNodeNetwork() {
		long start = new Date().getTime();
		Network network = NodeToNodeNetwork.buildNetwork(PerformanceDegreeDrivenTest.NODES, PerformanceDegreeDrivenTest.EDGES, NetworkBase.DEGREE_DRIVEN);
		long end = new Date().getTime();
		long time = end - start;
		System.out.println("Network build by " + network.getClass().getName() + " in " + time + " ms");
	}
	
	public static void buildObjectedNetwork() {
		long start = new Date().getTime();
		Network network = ObjectedNetwork.buildNetwork(PerformanceDegreeDrivenTest.NODES, PerformanceDegreeDrivenTest.EDGES, NetworkBase.DEGREE_DRIVEN);
		long end = new Date().getTime();
		long time = end - start;
		System.out.println("Network build by " + network.getClass().getName() + " in " + time + " ms");
	}
}
