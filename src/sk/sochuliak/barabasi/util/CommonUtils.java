package sk.sochuliak.barabasi.util;




public class CommonUtils {
	
	/**
	 * Returns sum of double values in array
	 * 
	 * @param array Array of double values
	 * @return Sum
	 */
	public static double sumOfDoubleArray(double[] array) {
		double sum = 0d;
		for (double value : array) {
			sum += value;
		}
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
		for (int i = 0; i < nodesIds.length; i++) {
			if (nodesIds[i] == nodeId) {
				return true;
			}
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
