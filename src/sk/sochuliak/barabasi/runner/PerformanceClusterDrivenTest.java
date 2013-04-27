package sk.sochuliak.barabasi.runner;

import java.util.Date;

import sk.sochuliak.barabasi.network.ArrayNetwork;
import sk.sochuliak.barabasi.network.MapNetwork;
import sk.sochuliak.barabasi.network.Network;
import sk.sochuliak.barabasi.network.NetworkBase;
import sk.sochuliak.barabasi.network.NetworkBuildConfiguration;
import sk.sochuliak.barabasi.network.NodeToNodeNetwork;
import sk.sochuliak.barabasi.network.ObjectedNetwork;

public class PerformanceClusterDrivenTest {
	private static final int NODES = 25000;
	private static final int EDGES = 2;

	private static boolean useStatistics = false;

	public static void main(String[] args) {
		// PerformanceClusterDrivenTest.buildArrayNetwork();
		PerformanceClusterDrivenTest.buildMapNetwork();
		// PerformanceClusterDrivenTest.buildNodeToNodeNetwork();
		// PerformanceClusterDrivenTest.buildObjectedNetwork();
	}

	public static void buildArrayNetwork() {
		long start = new Date().getTime();
		Network network = ArrayNetwork.buildNetwork(
				PerformanceClusterDrivenTest.NODES,
				PerformanceClusterDrivenTest.EDGES,
				NetworkBuildConfiguration.CLUSTER_DRIVEN,
				PerformanceClusterDrivenTest.useStatistics);
		long end = new Date().getTime();
		long time = end - start;
		System.out.println("Network build by " + network.getClass().getName()
				+ " in " + time + " ms");
	}

	public static void buildMapNetwork() {
		long start = new Date().getTime();
		Network network = MapNetwork.buildNetwork(
				PerformanceClusterDrivenTest.NODES,
				PerformanceClusterDrivenTest.EDGES,
				NetworkBuildConfiguration.CLUSTER_DRIVEN,
				PerformanceClusterDrivenTest.useStatistics);
		long end = new Date().getTime();
		long time = end - start;
		System.out.println("Network build by " + network.getClass().getName()
				+ " in " + time + " ms");
	}

	public static void buildNodeToNodeNetwork() {
		long start = new Date().getTime();
		Network network = NodeToNodeNetwork.buildNetwork(
				PerformanceClusterDrivenTest.NODES,
				PerformanceClusterDrivenTest.EDGES,
				NetworkBuildConfiguration.CLUSTER_DRIVEN,
				PerformanceClusterDrivenTest.useStatistics);
		long end = new Date().getTime();
		long time = end - start;
		System.out.println("Network build by " + network.getClass().getName()
				+ " in " + time + " ms");
	}

	public static void buildObjectedNetwork() {
		long start = new Date().getTime();
		Network network = ObjectedNetwork.buildNetwork(
				PerformanceClusterDrivenTest.NODES,
				PerformanceClusterDrivenTest.EDGES,
				NetworkBuildConfiguration.CLUSTER_DRIVEN,
				PerformanceClusterDrivenTest.useStatistics);
		long end = new Date().getTime();
		long time = end - start;
		System.out.println("Network build by " + network.getClass().getName()
				+ " in " + time + " ms");
	}
}
