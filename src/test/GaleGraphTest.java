package test;

import static org.junit.Assert.*;
import graphimplementations.Edge;
import graphimplementations.EdgesGraph;
import graphimplementations.MatrixGraph;
import factories.MatrixGraphFactory;
import factories.EdgesGraphFactory;
import gale.GaleEdgesGraph;
import gale.GaleMatrixGraph;
import gale.GaleString;

import org.junit.Test;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;


public class GaleGraphTest {

	MatrixGraphFactory matrixFactory = new MatrixGraphFactory();
	EdgesGraphFactory edgesFactory = new EdgesGraphFactory();
	
	@Test
	public void testMatrixConstructorFromGaleString_true() {

		int[] labels = { 1, 2, 2, 3, 4, 2, 4 };
		int[] bitstring = { 1, 1, 0, 1, 1, 0, 0 };
		GaleString gale = new GaleString(labels, bitstring);
		GaleMatrixGraph galeGraph = new GaleMatrixGraph(gale);
		
		int[][] testMatrix = new int[4][4];

		testMatrix[0][1] = 1;
		testMatrix[1][0] = 1;
		testMatrix[1][2] = 1;
		testMatrix[2][1] = 1;
		testMatrix[2][3] = 1;
		testMatrix[3][2] = 1;
		testMatrix[3][1] = 2;
		testMatrix[1][3] = 2;
		testMatrix[3][0] = 1;
		testMatrix[0][3] = 1;
		
		MatrixGraph testGraph = matrixFactory.createMatrixGraph(testMatrix);
		
		assertTrue(galeGraph.getGraph().equals(testGraph));
	}

	public void testEdgesConstructorFromGaleString_true() {

		int[] labels = { 1, 2, 2, 3, 4, 2, 4 };
		int[] bitstring = { 1, 1, 0, 1, 1, 0, 0 };
		GaleString gale = new GaleString(labels, bitstring);
		GaleEdgesGraph galeGraph = new GaleEdgesGraph(gale);
		
		int v = 4;
		
		Multiset<Edge> edges = HashMultiset.create();
		
		Edge e0 = new Edge(0, 1);
		edges.add(e0);
		Edge e1 = new Edge(1, 2);
		edges.add(e1);
		Edge e2 = new Edge(2, 3);
		edges.add(e2);
		Edge e3 = new Edge(3, 1);
		edges.add(e3);
		Edge e4 = new Edge(1, 3);
		edges.add(e4);
		Edge e5 = new Edge(3, 0);
		edges.add(e5);
		
		
		EdgesGraph testGraph = edgesFactory.createEdgesGraph(v, edges);
		
		assertTrue(galeGraph.getGraph().equals(testGraph));
	}

	@Test
	public void testPerfectMatchingFromGaleMatrix_true() {

		int[] labels = { 1, 2, 3, 4, 2, 4 };
		int[] bitstring = { 1, 1, 1, 1, 0, 0 };
		GaleString gale = new GaleString(labels, bitstring);
		GaleMatrixGraph galeGraph = new GaleMatrixGraph(gale);
		
		int[][] testMatching = new int[gale.getMaxLabel()][gale.getMaxLabel()];
		
		testMatching[0][1] = 1;
		testMatching[1][0] = 1;
		testMatching[2][3] = 1;
		testMatching[3][2] = 1;
		
		assertTrue(galeGraph.perfectMatchingFromGale().equals(testMatching));
	}

	@Test
	public void testPerfectMatchingFromGaleMatrixAcross_true() {

		int[] labels = { 1, 2, 3, 4, 2, 4 };
		int[] bitstring = { 1, 1, 1, 0, 0, 1 };
		GaleString gale = new GaleString(labels, bitstring);
		GaleEdgesGraph galeGraph = new GaleEdgesGraph(gale);
		
		int[][] testMatching = new int[gale.getMaxLabel()][gale.getMaxLabel()];
		
		testMatching[1][2] = 1;
		testMatching[2][1] = 1;
		testMatching[3][0] = 1;
		testMatching[0][3] = 1;
		
		assertTrue(galeGraph.perfectMatchingFromGale().equals(testMatching));
	}

	
	@Test
	public void testPerfectMatchingFromGaleEdges_true() {

		int[] labels = { 1, 2, 3, 4, 2, 4 };
		int[] bitstring = { 1, 1, 1, 1, 0, 0 };
		GaleString gale = new GaleString(labels, bitstring);
		GaleMatrixGraph galeGraph = new GaleMatrixGraph(gale);
		
		Multiset<Edge> testMatching = HashMultiset.create();
		
		Edge e0 = new Edge(0, 1);
		testMatching.add(e0);
		Edge e1 = new Edge(2, 3);
		testMatching.add(e1);
		
		assertTrue(galeGraph.perfectMatchingFromGale().equals(testMatching));
	}

	@Test
	public void testPerfectMatchingFromGaleEdgesAcross_true() {

		int[] labels = { 1, 2, 3, 4, 2, 4 };
		int[] bitstring = { 1, 1, 1, 0, 0, 1 };
		GaleString gale = new GaleString(labels, bitstring);
		GaleEdgesGraph galeGraph = new GaleEdgesGraph(gale);
		
		Multiset<Edge> testMatching = HashMultiset.create();
		
		Edge e0 = new Edge(1, 2);
		testMatching.add(e0);
		Edge e1 = new Edge(3, 0);
		testMatching.add(e1);
		
		assertTrue(galeGraph.perfectMatchingFromGale().equals(testMatching));
	}

}
