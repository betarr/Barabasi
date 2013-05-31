package sk.sochuliak.barabasi.util;

import java.util.Date;




public class CommonUtils {
	
	/**
	 * Returns sum of double values in array
	 * 
	 * @param array Array of double values
	 * @return Sum
	 */
	public static double sumOfDoubleArray(double[] array) {
//		long start = new Date().getTime();
		double sum = 0d;
		for (double value : array) {
			sum += value;
		}
//		long end = new Date().getTime();
//		if ((end-start) > 0) {
//			System.out.println("CommonUtils \t sumOfDoubleArray \t " + (end-start));
//		}
		return sum;
	}
	
	/**
	 * Checks if nodesIds array contains nodeId.
	 * 
	 * @param nodeId Id of node
	 * @param nodesIds Array of nodesIds
	 * @return true if contains, false otherwise
	 */
	public static boolean isNodeIdInNodesIdsArray(int nodeId, int[] nodesIds) {
		long start = new Date().getTime();
		for (int i = 0; i < nodesIds.length; i++) {
			if (nodesIds[i] == nodeId) {
				long end = new Date().getTime();
				if ((end-start) > 0) {
					System.out.println("CommonUtils \t isNodeIdInNodesIdsArray \t " + (end-start));
				}
				return true;
			}
		}
		long end = new Date().getTime();
		if ((end-start) > 0) {
			System.out.println("CommonUtils \t isNodeIdInNodesIdsArray \t " + (end-start));
		}
		return false;
	}
	
	public static int getIndexOfNodeIdInNodesIdsArray(int nodeId, int[] nodesIds) {
		for (int i = 0; i < nodesIds.length; i++) {
			if (nodesIds[i] == nodeId) {
				return i;
			}
		}
		return -1;
	}
}
