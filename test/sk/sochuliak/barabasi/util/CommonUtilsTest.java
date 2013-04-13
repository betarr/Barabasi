package sk.sochuliak.barabasi.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.NumberFormat;

import org.junit.Test;

public class CommonUtilsTest {

	@Test
	public void testSumOfDoubleArray() {
		double[] array = new double[]{0.42, 0.13, 5.28, 1.15, 3.14};
		double expectedSum = 10.12;
		double actualSum = CommonUtils.sumOfDoubleArray(array);
		
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		
		String expectedSumAsString = nf.format(expectedSum);
		String actualSumAsString = nf.format(actualSum);
		
		assertEquals(expectedSumAsString, actualSumAsString);
	}
	
	@Test
	public void testIsNodeIdInNodesIdsArraySuccess() {
		int[] nodesIdsArray = new int[]{1, 2, 3, 4, 5, 6, 7};
		int nodeId = 4;
		assertTrue(CommonUtils.isNodeIdInNodesIdsArray(nodeId, nodesIdsArray));
	}
	
	@Test
	public void testIsNodeIdInNodesIdsArrayFail() {
		int[] nodesIdsArray = new int[]{1, 2, 3, 4, 5, 6, 7};
		int nodeId = 9;
		assertFalse(CommonUtils.isNodeIdInNodesIdsArray(nodeId, nodesIdsArray));
	}
}
