package sk.sochuliak.barabasi.network;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ObjectedNetworkTest {

	private Network network;
	
	@Before
	public void setUp() {
		this.network = new ObjectedNetwork();
		for (int i = 0; i < 3; i++) {
			this.network.addNode(i);
		}
	}
	
	@Test
	public void testAddNodeSuccess() {
		boolean nodeAdded = this.network.addNode(4);
		int expectedNumberOfNodes = 4;
		assertTrue(nodeAdded);
		assertEquals(expectedNumberOfNodes, this.network.getNumberOfNodes());
	}
	
	@Test
	public void testAddNodeFail() {
		boolean nodeAdded = this.network.addNode(4);
		int expectedNumberOfNodes = 3;
		assertFalse(nodeAdded);
		assertEquals(expectedNumberOfNodes, this.network.getNumberOfNodes());
	}
	
	@Test
	public void testAddEdge() {
		this.network.addEdge(1, 4);
		assertTrue(this.network.isEdgeBetweenNodes(1, 4));
	}
	
	@Test
	public void testGetNumberOfExistingEdgesBetweenNodes() {
		this.network.addEdge(2, 4);
		int expectedNumberOfExistingEdges = 2;
		int actualNumberOfExistingEdges = this.network.getNumberOfExistingEdgesBetweenNodes(new int[]{1, 2, 4});
		assertEquals(expectedNumberOfExistingEdges, actualNumberOfExistingEdges);
	}
	
	@Test
	public void testGetNumberOfAllPossibleEdgesBetweenNodes() {
		int expectedNumberOfAllEdges = 0;
		int actualNumberOfAllEdges = this.network.getNumberOfAllPossibleEdgesBetweenNodes(new int[]{1});
		assertEquals(expectedNumberOfAllEdges, actualNumberOfAllEdges);
		
		expectedNumberOfAllEdges = 1;
		actualNumberOfAllEdges = this.network.getNumberOfAllPossibleEdgesBetweenNodes(new int[]{1, 2});
		assertEquals(expectedNumberOfAllEdges, actualNumberOfAllEdges);
		
		expectedNumberOfAllEdges = 3;
		actualNumberOfAllEdges = this.network.getNumberOfAllPossibleEdgesBetweenNodes(new int[]{1, 2, 3});
		assertEquals(expectedNumberOfAllEdges, actualNumberOfAllEdges);
		
		expectedNumberOfAllEdges = 6;
		actualNumberOfAllEdges = this.network.getNumberOfAllPossibleEdgesBetweenNodes(new int[]{1, 2, 3, 4});
		assertEquals(expectedNumberOfAllEdges, actualNumberOfAllEdges);
	}
	
	@Test
	public void testGetAdjacentNodesIds() {
		int[] expectedAdjacentNodesIds = new int[]{1, 2};
		int[] actualAdjacentNodesIds = this.network.getAdjacentNodesIds(4);
		assertEquals(expectedAdjacentNodesIds, actualAdjacentNodesIds);
	}
	
	@Test
	public void testGetAdjacentNodesCount() {
		int expectedNodesCount = 2;
		int actualNodesCount = this.network.getAdjacentNodesCount(4);
		assertEquals(expectedNodesCount, actualNodesCount);
	}
	
	@Test
	public void testIsEdgeBetweenNodes() {
		assertTrue(this.network.isEdgeBetweenNodes(1, 4));
		assertFalse(this.network.isEdgeBetweenNodes(1, 2));
	}
	
	@Test
	public void testGetNumberOfNodes() {
		int expectedNumberOfNodes = 4;
		int actualNumberOfNodes = this.network.getNumberOfNodes();
		assertEquals(expectedNumberOfNodes, actualNumberOfNodes);
	}
	
	@Test
	public void testContainsNode() {
		assertTrue(this.network.containsNode(4));
		assertFalse(this.network.containsNode(5));
	}
}
