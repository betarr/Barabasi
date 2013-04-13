package sk.sochuliak.barabasi.network;

public abstract class NetworkBase {
	
	protected int calculateNumberOfAllPossibleEdgesBetweenNodes(int numberOfNodes) {
		return ((numberOfNodes-1)*numberOfNodes) / 2;
	}

}
