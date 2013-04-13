package sk.sochuliak.barabasi.runner;

import java.util.Date;

import sk.sochuliak.barabasi.network.Network;
import sk.sochuliak.barabasi.network.NodeToNodeNetwork;

public class PerformanceTest {

	public static void main(String[] args) {
		long startTime = new Date().getTime();
		Network network = new NodeToNodeNetwork();
		for (int i = 0; i < 30000; i++) {
			network.addNode(i);
			network.addEdge(i, i/2);
			network.addEdge(i, i/3);
		}
		long endTime = new Date().getTime();
		long time = endTime - startTime;
		System.out.println("Network builded by " + network.getClass().getName() + " in " + time + " ms.");
	}
}
