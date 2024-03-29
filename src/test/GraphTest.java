package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import abstractclasses.AbstractGraph;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import com.google.common.collect.HashMultiset;

import factories.EdgesGraphFactory;
import factories.ListGraphFactory;
import factories.MatrixGraphFactory;
import graphimplementations.EdgesGraph;
import graphimplementations.ListGraph;
import graphimplementations.MatrixGraph;
import graphimplementations.Edge;
import exceptions.*;

public class GraphTest {

	public MatrixGraphFactory matrixFactory = new MatrixGraphFactory();
	public EdgesGraphFactory edgesFactory = new EdgesGraphFactory();
	public ListGraphFactory listFactory = new ListGraphFactory();
		
	@Test
	public void testEquals_Matrix_true() {
		
		int[][] adjacency = {{ 0, 1, 0 },
							 { 1, 0, 1 },
							 { 0, 1, 1 }};

		AbstractGraph graph = matrixFactory.createMatrixGraph(adjacency);

		AbstractGraph otherGraph = matrixFactory.createMatrixGraph(adjacency);
		
		assertTrue(graph.equals(otherGraph));
	}	
		
	@Test
	public void testEquals_Edges_true() {
				
		int v = 3;
		
		Multiset<Edge> edges = HashMultiset.create();		
		
		Edge e0 = new Edge(0,1);
		edges.add(e0);
		Edge e1 = new Edge(1,2);
		edges.add(e1);
		Edge e2 = new Edge(2,2);
		edges.add(e2);

		AbstractGraph graph = edgesFactory.createEdgesGraph(v,edges);
		AbstractGraph otherGraph = edgesFactory.createEdgesGraph(v,edges);
		
		assertTrue(graph.equals(otherGraph));
	}
	
	
	@Test
	public void testEquals_List_true() {
		
		int v = 5;
		
		ArrayList<LinkedList<Integer>> adjacency = new ArrayList<LinkedList<Integer>>(5);
		
		LinkedList<Integer> list0 = new LinkedList<Integer>();
		list0.add(1);
		list0.add(2);
		adjacency.add(list0);
		LinkedList<Integer> list1 = new LinkedList<Integer>();
		list1.add(0);
		list1.add(1);
		list1.add(2);
		adjacency.add(list1);
		LinkedList<Integer> list2 = new LinkedList<Integer>();
		list2.add(0);
		list2.add(1);
		list2.add(3);
		adjacency.add(list2);	
		LinkedList<Integer> list3 = new LinkedList<Integer>();
		list3.add(2);
		adjacency.add(list3);
		
				
		AbstractGraph graph = listFactory.createListGraph(v, adjacency);
		AbstractGraph otherGraph = listFactory.createListGraph(v, adjacency);
		
		assertTrue(graph.equals(otherGraph));
	}
	
	
	@Test
	public void testFactoriesMatrix_MatrixEdge_true() {
		
		int[][] incidence = {{ 0, 1, 0 },
							 { 1, 0, 1 },	
							 { 0, 1, 1 }};

		AbstractGraph graph = matrixFactory.createMatrixGraph(incidence);
		
		AbstractGraph otherGraph = edgesFactory.createEdgesGraph(incidence);
		
		assertTrue(graph.equals(otherGraph));
	}
	
	@Test
	public void testFactoriesMatrix_MatrixList_true() {
		
		int[][] incidence = {{ 0, 1, 0 },
				 			 { 1, 0, 1 },
				 			 { 0, 1, 1 }};

		AbstractGraph graph = matrixFactory.createMatrixGraph(incidence);
		
		AbstractGraph otherGraph = listFactory.createListGraph(incidence);
		
		assertTrue(graph.equals(otherGraph));
	}

	@Test
	public void testFactoriesMatrix_exception() {
		
		int[][] incidence ={{ 0, 1, 0 },
				 			{ 1, 0, 0 },
				 			{ 0, 1, 1 }};

		boolean thrown = false;
		
		try {
			AbstractGraph graph = matrixFactory.createMatrixGraph(incidence);			
		} catch (MatrixNotValidException e) {
			thrown = true;
		}
				
		assertTrue(thrown);
	}
	
	
	@Test
	public void testFactoriesEdges_MatrixEdge_true() {
		
		int v = 3;
	 	
		Multiset<Edge> e = HashMultiset.create();		
		
		Edge e0 = new Edge(0,1);
		e.add(e0);
		Edge e1 = new Edge(1,2);
		e.add(e1);
		Edge e2 = new Edge(2,2);
		e.add(e2);

		AbstractGraph graph = matrixFactory.createMatrixGraph(v, e);
		
		AbstractGraph otherGraph = edgesFactory.createEdgesGraph(v, e);
		
		assertTrue(graph.equals(otherGraph));
	}

	@Test
	public void testFactories_MatrixEdge_true() {
		
		int[][] incidence = {{ 0, 1, 0 },
							 { 1, 0, 1 },
							 { 0, 1, 1 }};
		
		int v = 3;
		
		Multiset<Edge> e = HashMultiset.create();		
		
		Edge e0 = new Edge(0,1);
		e.add(e0);
		Edge e1 = new Edge(1,2);
		e.add(e1);
		Edge e2 = new Edge(2,2);
		e.add(e2);

		AbstractGraph graphIncidence = matrixFactory.createMatrixGraph(incidence);
		
		AbstractGraph graphEdges = edgesFactory.createEdgesGraph(v, e);
		
		assertTrue(graphIncidence.equals(graphEdges));
	}

	@Test
	public void testFactoriesEdges_ListEdge_true() {
		
		int v = 3;
	 	
		Multiset<Edge> e = HashMultiset.create();		
		
		Edge e0 = new Edge(0,1);
		e.add(e0);
		Edge e1 = new Edge(1,2);
		e.add(e1);
		Edge e2 = new Edge(2,2);
		e.add(e2);

		AbstractGraph graph = listFactory.createListGraph(v, e);
		
		AbstractGraph otherGraph = edgesFactory.createEdgesGraph(v, e);
		
		assertTrue(graph.equals(otherGraph));
	}

	@Test
	public void testFactoriesEdges_exception() {
		
		int v = 3;
	 	
		Multiset<Edge> e = HashMultiset.create();		
		
		Edge e0 = new Edge(0,1);
		e.add(e0);
		Edge e1 = new Edge(1,2);
		e.add(e1);
		Edge e2 = new Edge(2,3);
		e.add(e2);

		boolean thrown = false;
		
		try {
			AbstractGraph graph = matrixFactory.createMatrixGraph(v, e);			
		} catch (VertexNotInGraphException ex) {
			thrown = true;
		}
				
		assertTrue(thrown);
	}

	
	@Test
	public void testFactories_MatrixEdge_2_true() {
		int[][] incidence = {{ 0, 1, 1, 0, 0, 0 },
							 { 1, 1, 0, 1, 0, 0 },
							 { 1, 0, 0, 0, 1, 1 },
							 { 0, 1, 0, 0, 1, 1 },
							 { 0, 0, 1, 1, 0, 1 },
							 { 0, 0, 1, 1, 1, 0 }};

		AbstractGraph graphIncidence = matrixFactory.createMatrixGraph(incidence);
		
		int v = 6;

		Multiset<Edge> e = HashMultiset.create();		
		
		Edge e0 = new Edge(0,1);
		e.add(e0);
		Edge e1 = new Edge(0,2);
		e.add(e1);
		Edge e2 = new Edge(1,1);
		e.add(e2);
		Edge e3 = new Edge(1,3);
		e.add(e3);
		Edge e4 = new Edge(2,4);
		e.add(e4);
		Edge e5 = new Edge(2,5);
		e.add(e5);
		Edge e6 = new Edge(3,4);
		e.add(e6);
		Edge e7 = new Edge(3,5);
		e.add(e7);
		Edge e8 = new Edge(4,5);
		e.add(e8);

		AbstractGraph graphEdges = edgesFactory.createEdgesGraph(v,e);
		
		assertTrue(graphIncidence.equals(graphEdges));
	}

	@Test
	public void testAddVerticesMatrix_true() {
		int[][] incidence = {{ 0, 1, 0 },
							 { 1, 2, 1 },
							 { 0, 1, 0 }};

		MatrixGraph graphIncidence = matrixFactory.createMatrixGraph(incidence);
		
		graphIncidence.addVertices(2);
		
		int[][] newIncidence = {{ 0, 1, 0, 0, 0 },
				 				{ 1, 2, 1, 0, 0 },
				 				{ 0, 1, 0, 0, 0 },
				 				{ 0, 0, 0, 0, 0 },
				 				{ 0, 0, 0, 0, 0 }};
		
		MatrixGraph newGraph = matrixFactory.createMatrixGraph(newIncidence);
		
		assertTrue(graphIncidence.equals(newGraph));
	}
	
	@Test
	public void testAddEdgeMatrix_true() {
		int[][] incidence = {{ 0, 1, 0, 0, 0 },
				 			 { 1, 2, 1, 0, 0 },
				 			 { 0, 1, 0, 0, 0 },
				 			 { 0, 0, 0, 0, 0 },
				 			 { 0, 0, 0, 0, 0 }};
		
		MatrixGraph graph = matrixFactory.createMatrixGraph(incidence);
		
		graph.addEdge(2, 3);

		int[][] newIncidence = {{ 0, 1, 0, 0, 0 },
	 			 				{ 1, 2, 1, 0, 0 },
	 			 				{ 0, 1, 0, 1, 0 },
	 			 				{ 0, 0, 1, 0, 0 },
	 			 				{ 0, 0, 0, 0, 0 }};
		
		
		MatrixGraph newGraph = matrixFactory.createMatrixGraph(newIncidence);
				
		assertTrue(graph.equals(newGraph));
	}
	
	@Test
	public void testAddEdgeMatrix_exception() {
		int[][] incidence = {{ 0, 1, 0, 0, 0 },
				 			 { 1, 2, 1, 0, 0 },
				 			 { 0, 1, 0, 0, 0 },
				 			 { 0, 0, 0, 0, 0 },
				 			 { 0, 0, 0, 0, 0 }};
		
		MatrixGraph graph = matrixFactory.createMatrixGraph(incidence);
		
		boolean thrown = false;
		
		try {
			graph.addEdge(2, 5);			
		} catch (VertexNotInGraphException e) {
			thrown = true;
		}
				
		assertTrue(thrown);
	}

	
	@Test
	public void testRemoveVertex_Matrix_0_true() {
		int[][] incidence = {{ 0, 1, 0, 0, 0 },
				 			 { 1, 2, 1, 0, 0 },
				 			 { 0, 1, 0, 1, 0 },
				 			 { 0, 0, 1, 0, 0 },
				 			 { 0, 0, 0, 0, 0 }};
		
		MatrixGraph graph = matrixFactory.createMatrixGraph(incidence);
		
		graph.removeVertex(0);

		int[][] newIncidence = {{ 2, 1, 0, 0 },
	 			 				{ 1, 0, 1, 0 },
	 			 				{ 0, 1, 0, 0 },
	 			 				{ 0, 0, 0, 0 }};
		
		
		MatrixGraph newGraph = matrixFactory.createMatrixGraph(newIncidence);
				
		assertTrue(graph.equals(newGraph));
	}


	@Test
	public void testRemoveVertex_Matrix_1_true() {
		int[][] incidence = {{ 0, 1, 0, 0, 0 },
				 			 { 1, 2, 1, 0, 0 },
				 			 { 0, 1, 0, 1, 0 },
				 			 { 0, 0, 1, 0, 0 },
				 			 { 0, 0, 0, 0, 0 }};
		
		MatrixGraph graph = matrixFactory.createMatrixGraph(incidence);
		
		graph.removeVertex(1);

		int[][] newIncidence = {{ 0, 0, 0, 0 },
								{ 0, 0, 1, 0 },
								{ 0, 1, 0, 0 },
								{ 0, 0, 0, 0 }};
		
		
		MatrixGraph newGraph = matrixFactory.createMatrixGraph(newIncidence);
				
		assertTrue(graph.equals(newGraph));
	}

	@Test
	public void testRemoveVertex_Matrix_exception() {
		int[][] incidence = {{ 0, 1, 0, 0, 0 },
				 			 { 1, 2, 1, 0, 0 },
				 			 { 0, 1, 0, 1, 0 },
				 			 { 0, 0, 1, 0, 0 },
				 			 { 0, 0, 0, 0, 0 }};
		
		MatrixGraph graph = matrixFactory.createMatrixGraph(incidence);
		
		boolean thrown = false;
		
		try {
			graph.removeVertex(5);			
		} catch (VertexNotInGraphException e) {
			thrown = true;
		}
		
		assertTrue(thrown);
	}
	
	@Test
	public void testRemoveEdge_Matrix_true() {
		int[][] incidence = {{ 2, 1, 0, 0 },
	 			 			 { 1, 0, 1, 0 },
	 			 			 { 0, 1, 0, 0 },
	 			 			 { 0, 0, 0, 0 }};
		
		MatrixGraph graph = matrixFactory.createMatrixGraph(incidence);
		
		graph.removeEdge(0, 0);

		int[][] newIncidence = {{ 0, 1, 0, 0 },
								{ 1, 0, 1, 0 },
								{ 0, 1, 0, 0 },
								{ 0, 0, 0, 0 }};
		
		MatrixGraph newGraph = matrixFactory.createMatrixGraph(newIncidence);
				
		assertTrue(graph.equals(newGraph));
	}	

	@Test
	public void testRemoveEdge_Matrix_exceptionEdge() {
		int[][] incidence = {{ 2, 1, 0, 0 },
	 			 			 { 1, 0, 1, 0 },
	 			 			 { 0, 1, 0, 0 },
	 			 			 { 0, 0, 0, 0 }};
		
		MatrixGraph graph = matrixFactory.createMatrixGraph(incidence);
		
		
		boolean thrown = false;
		
		try {
			graph.removeEdge(2, 3);			
		} catch (EdgeNotInGraphException e) {
			thrown = true;
		}
		
		assertTrue(thrown);	
	}	

	@Test
	public void testRemoveEdge_Matrix_exceptionVertex() {
		int[][] incidence = {{ 2, 1, 0, 0 },
	 			 			 { 1, 0, 1, 0 },
	 			 			 { 0, 1, 0, 0 },
	 			 			 { 0, 0, 0, 0 }};
		
		MatrixGraph graph = matrixFactory.createMatrixGraph(incidence);
		
		
		boolean thrown = false;
		
		try {
			graph.removeEdge(2, 4);			
		} catch (VertexNotInGraphException e) {
			thrown = true;
		}
		
		assertTrue(thrown);	
	}	
	@Test
	public void testAddVertices_Edges_true() {
		int v = 3;
		
		Multiset<Edge> edges = HashMultiset.create();
		Edge e0 = new Edge(0, 1);
		edges.add(e0);
		Edge e1 = new Edge(1, 1);
		edges.add(e1);
		Edge e2 = new Edge(1, 2);
		edges.add(e2);
		
		EdgesGraph graph = edgesFactory.createEdgesGraph(v, edges);
		
		graph.addVertices(2);
		
		int newV = 5;
		
		EdgesGraph newGraph = edgesFactory.createEdgesGraph(newV, edges);

		assertTrue(graph.equals(newGraph));
	}
	
	@Test
	public void testAddEdge_Edges_true() {
		int v = 4;
		
		Multiset<Edge> edges = HashMultiset.create();
		Edge e0 = new Edge(0, 1);
		edges.add(e0);
		Edge e1 = new Edge(1, 1);
		edges.add(e1);
		Edge e2 = new Edge(1, 2);
		edges.add(e2);
		
		EdgesGraph graph = edgesFactory.createEdgesGraph(v, edges);
	
		graph.addEdge(2, 3);
		
		Multiset<Edge> newEdges = HashMultiset.create();
		Edge e = new Edge(0, 1);
		newEdges.add(e);
		Edge f = new Edge(1, 1);
		newEdges.add(f);
		Edge g = new Edge(1, 2);
		newEdges.add(g);
		Edge h = new Edge(2, 3);
		newEdges.add(h);
		
		EdgesGraph newGraph = edgesFactory.createEdgesGraph(v, newEdges);
		
		assertTrue(graph.equals(newGraph));
		
	}
	
	@Test
	public void testAddEdge_Edges_exception() {
		int v = 3;
		
		Multiset<Edge> edges = HashMultiset.create();
		Edge e0 = new Edge(0, 1);
		edges.add(e0);
		Edge e1 = new Edge(1, 1);
		edges.add(e1);
		Edge e2 = new Edge(1, 2);
		edges.add(e2);
		
		EdgesGraph graph = edgesFactory.createEdgesGraph(v, edges);
	
		boolean thrown = false;
		
		try {
			graph.addEdge(3, 0);
		} catch (VertexNotInGraphException e) {
			thrown = true;
		}
		
		assertTrue(thrown);
	}

	@Test
	public void testRemoveVertex_Edges_true() {
		int v = 4;
		
		Multiset<Edge> edges = HashMultiset.create();
		Edge e0 = new Edge(0, 1);
		edges.add(e0);
		Edge e1 = new Edge(1, 1);
		edges.add(e1);
		Edge e2 = new Edge(1, 2);
		edges.add(e2);
		Edge e3 = new Edge(2, 3);
		edges.add(e3);
		Edge e4 = new Edge(2, 0);
		edges.add(e4);
		Edge e5 = new Edge(3, 0);
		edges.add(e5);
		
		EdgesGraph graph = edgesFactory.createEdgesGraph(v, edges);

		graph.removeVertex(2);
				
		Multiset<Edge> newEdges = HashMultiset.create();
		Edge e = new Edge(0, 1);
		newEdges.add(e);
		Edge f = new Edge(1, 1);
		newEdges.add(f);
		Edge g = new Edge(2, 0);
		newEdges.add(g);

		EdgesGraph newGraph = edgesFactory.createEdgesGraph(3, newEdges);
		
		assertTrue(graph.equals(newGraph));
	}

	@Test
	public void testRemoveVertex_Edges_exception() {
		int v = 4;
		
		Multiset<Edge> edges = HashMultiset.create();
		Edge e0 = new Edge(0, 1);
		edges.add(e0);
		Edge e1 = new Edge(1, 1);
		edges.add(e1);
		Edge e2 = new Edge(1, 2);
		edges.add(e2);
		Edge e3 = new Edge(2, 3);
		edges.add(e3);
		Edge e4 = new Edge(2, 0);
		edges.add(e4);
		Edge e5 = new Edge(3, 0);
		edges.add(e5);
		
		EdgesGraph graph = edgesFactory.createEdgesGraph(v, edges);
				
		boolean thrown = false;
		
		try {
			graph.removeVertex(4);
		} catch (VertexNotInGraphException e) {
			thrown = true;
		}

		assertTrue(thrown);
	}	
	
	@Test
	public void testRemoveEdge_Edges_true() {

		int v = 4;
		
		Multiset<Edge> edges = HashMultiset.create();
		Edge e0 = new Edge(0, 1);
		edges.add(e0);
		Edge e1 = new Edge(1, 1);
		edges.add(e1);
		Edge e2 = new Edge(1, 2);
		edges.add(e2);
		Edge e3 = new Edge(2, 3);
		edges.add(e3);
		Edge e4 = new Edge(2, 0);
		edges.add(e4);
		
		EdgesGraph graph = edgesFactory.createEdgesGraph(v, edges);

		graph.removeEdge(2, 0);
		
		Multiset<Edge> newEdges = HashMultiset.create();
		Edge e = new Edge(0, 1);
		newEdges.add(e);
		Edge f = new Edge(1, 1);
		newEdges.add(f);
		Edge g = new Edge(1, 2);
		newEdges.add(g);
		Edge h = new Edge(2, 3);
		newEdges.add(h);
		
		EdgesGraph newGraph = edgesFactory.createEdgesGraph(v, newEdges);
		
		assertTrue(graph.equals(newGraph));		
	}

	@Test
	public void testRemoveEdge_multiedge_Edges_true() {

		int v = 4;
		
		Multiset<Edge> edges = HashMultiset.create();
		Edge e0 = new Edge(0, 1);
		edges.add(e0);
		Edge e1 = new Edge(1, 1);
		edges.add(e1);
		Edge e2 = new Edge(1, 2);
		edges.add(e2);
		Edge e3 = new Edge(2, 3);
		edges.add(e3);
		Edge e4 = new Edge(2, 0);
		edges.add(e4);
		Edge e5 = new Edge(2, 0);
		edges.add(e5);
		
		EdgesGraph graph = edgesFactory.createEdgesGraph(v, edges);

		graph.removeEdge(2, 0);
		
		Multiset<Edge> newEdges = HashMultiset.create();
		Edge e = new Edge(0, 1);
		newEdges.add(e);
		Edge f = new Edge(1, 1);
		newEdges.add(f);
		Edge g = new Edge(1, 2);
		newEdges.add(g);
		Edge h = new Edge(2, 3);
		newEdges.add(h);
		Edge i = new Edge(2, 0);
		newEdges.add(i);
		
		EdgesGraph newGraph = edgesFactory.createEdgesGraph(v, newEdges);
		
		assertTrue(graph.equals(newGraph));		
	}

	@Test
	public void testRemoveEdge_multiedge_reverse1_Edges_true() {

		int v = 4;
		
		Multiset<Edge> edges = HashMultiset.create();
		Edge e0 = new Edge(0, 1);
		edges.add(e0);
		Edge e1 = new Edge(1, 1);
		edges.add(e1);
		Edge e2 = new Edge(1, 2);
		edges.add(e2);
		Edge e3 = new Edge(2, 3);
		edges.add(e3);
		Edge e4 = new Edge(2, 0);
		edges.add(e4);
		Edge e5 = new Edge(0, 2);
		edges.add(e5);
		
		EdgesGraph graph = edgesFactory.createEdgesGraph(v, edges);

		graph.removeEdge(2, 0);
		
		Multiset<Edge> newEdges = HashMultiset.create();
		Edge e = new Edge(0, 1);
		newEdges.add(e);
		Edge f = new Edge(1, 1);
		newEdges.add(f);
		Edge g = new Edge(1, 2);
		newEdges.add(g);
		Edge h = new Edge(2, 3);
		newEdges.add(h);
		Edge i = new Edge(2, 0);
		newEdges.add(i);
		
		EdgesGraph newGraph = edgesFactory.createEdgesGraph(v, newEdges);
		
		assertTrue(graph.equals(newGraph));		
	}
	
	@Test
	public void testRemoveEdge_multiedge_reverse2_Edges_true() {

		int v = 4;
		
		Multiset<Edge> edges = HashMultiset.create();
		Edge e0 = new Edge(0, 1);
		edges.add(e0);
		Edge e1 = new Edge(1, 1);
		edges.add(e1);
		Edge e2 = new Edge(1, 2);
		edges.add(e2);
		Edge e3 = new Edge(2, 3);
		edges.add(e3);
		Edge e4 = new Edge(2, 0);
		edges.add(e4);
		Edge e5 = new Edge(0, 2);
		edges.add(e5);
		Edge e6 = new Edge(2, 0);
		edges.add(e6);
		
		EdgesGraph graph = edgesFactory.createEdgesGraph(v, edges);

		graph.removeEdge(2, 0);
		
		Multiset<Edge> newEdges = HashMultiset.create();
		Edge e = new Edge(0, 1);
		newEdges.add(e);
		Edge f = new Edge(1, 1);
		newEdges.add(f);
		Edge g = new Edge(1, 2);
		newEdges.add(g);
		Edge h = new Edge(2, 3);
		newEdges.add(h);
		Edge i = new Edge(2, 0);
		newEdges.add(i);
		Edge j = new Edge(2, 0);
		newEdges.add(j);
		
		EdgesGraph newGraph = edgesFactory.createEdgesGraph(v, newEdges);
		
		assertTrue(graph.equals(newGraph));		
	}

	@Test
	public void testRemoveEdge_Edges_exceptionEdge() {

		int v = 4;
		
		Multiset<Edge> edges = HashMultiset.create();
		Edge e0 = new Edge(0, 1);
		edges.add(e0);
		Edge e1 = new Edge(1, 1);
		edges.add(e1);
		Edge e2 = new Edge(1, 2);
		edges.add(e2);
		Edge e3 = new Edge(2, 3);
		edges.add(e3);
		Edge e4 = new Edge(2, 0);
		edges.add(e4);
		
		EdgesGraph graph = edgesFactory.createEdgesGraph(v, edges);

		boolean thrown = false;
		
		try {
			graph.removeEdge(3, 0);
		} catch (EdgeNotInGraphException e) {
			thrown = true;
		}

		assertTrue(thrown);
		
	}
	
	@Test
	public void testRemoveEdge_Edges_exceptionVertex() {

		int v = 4;
		
		Multiset<Edge> edges = HashMultiset.create();
		Edge e0 = new Edge(0, 1);
		edges.add(e0);
		Edge e1 = new Edge(1, 1);
		edges.add(e1);
		Edge e2 = new Edge(1, 2);
		edges.add(e2);
		Edge e3 = new Edge(2, 3);
		edges.add(e3);
		Edge e4 = new Edge(2, 0);
		edges.add(e4);
		
		EdgesGraph graph = edgesFactory.createEdgesGraph(v, edges);

		boolean thrown = false;
		
		try {
			graph.removeEdge(4, 0);
		} catch (VertexNotInGraphException e) {
			thrown = true;
		}

		assertTrue(thrown);
		
	}

	@Test
	public void testRemoveEdge_Edges_Inverted_true() {

		int v = 4;
		
		Multiset<Edge> edges = HashMultiset.create();
		Edge e0 = new Edge(0, 1);
		edges.add(e0);
		Edge e1 = new Edge(1, 1);
		edges.add(e1);
		Edge e2 = new Edge(1, 2);
		edges.add(e2);
		Edge e3 = new Edge(2, 3);
		edges.add(e3);
		Edge e4 = new Edge(2, 0);
		edges.add(e4);
		
		EdgesGraph graph = edgesFactory.createEdgesGraph(v, edges);

		graph.removeEdge(0, 2);
		
		Multiset<Edge> newEdges = HashMultiset.create();
		Edge e = new Edge(0, 1);
		newEdges.add(e);
		Edge f = new Edge(1, 1);
		newEdges.add(f);
		Edge g = new Edge(1, 2);
		newEdges.add(g);
		Edge h = new Edge(2, 3);
		newEdges.add(h);
		
		EdgesGraph newGraph = edgesFactory.createEdgesGraph(v, newEdges);
		
		assertTrue(graph.equals(newGraph));
		
	}
	
	
	@Test
	public void testIsEulerian_Matrix_false() {
		
		int[][] incidence = {{ 1, 0, 1 },
							 { 0, 2, 0 },
							 { 1, 0, 1 }};
		
		AbstractGraph graphIncidence = matrixFactory.createMatrixGraph(incidence);
				
		assertFalse(graphIncidence.isEulerian());
	}
	
	
	@Test
	public void testIsEulerian_Matrix_true() {
		
		int[][] incidence = new int[3][3];
		
		incidence[0][1] = 1;
		incidence[1][0] = 1;
		incidence[1][2] = 1;
		incidence[2][1] = 1;
		incidence[2][0] = 1;
		incidence[0][2] = 1;
		
		AbstractGraph graphIncidence = matrixFactory.createMatrixGraph(incidence);
				
		assertTrue(graphIncidence.isEulerian());
	}
	
	@Test
	public void testIsEulerian_Edges_false() {
				
		int v = 3;
		
		Multiset<Edge> e = HashMultiset.create();		
		
		Edge e0 = new Edge(0,1);
		e.add(e0);
		Edge e1 = new Edge(1,2);
		e.add(e1);
		Edge e2 = new Edge(2,2);
		e.add(e2);
		
		AbstractGraph graphEdges = edgesFactory.createEdgesGraph(v, e);
		
		assertFalse(graphEdges.isEulerian());
	}
	
	@Test
	public void testIsEulerian_Edges_true() {
				
		int v = 3;
		
		Multiset<Edge> e = HashMultiset.create();		
		
		Edge e0 = new Edge(0,1);
		e.add(e0);
		Edge e1 = new Edge(1,2);
		e.add(e1);
		Edge e2 = new Edge(2,0);
		e.add(e2);
		
		AbstractGraph graphEdges = edgesFactory.createEdgesGraph(v, e);
		
		assertTrue(graphEdges.isEulerian());
	}

	@Test
	public void testPerfectMatching_Edges_true() {
		int v = 6;

		Multiset<Edge> edges = HashMultiset.create();		
		
		Edge e0 = new Edge(0,1);
		edges.add(e0);
		Edge e1 = new Edge(0,2);
		edges.add(e1);
		Edge e2 = new Edge(1,1);
		edges.add(e2);
		Edge e3 = new Edge(1,3);
		edges.add(e3);
		Edge e4 = new Edge(2,4);
		edges.add(e4);
		Edge e5 = new Edge(2,5);
		edges.add(e5);
		Edge e6 = new Edge(3,4);
		edges.add(e6);
		Edge e7 = new Edge(3,5);
		edges.add(e7);
		Edge e8 = new Edge(4,5);
		edges.add(e8);

		EdgesGraph graphEdges = edgesFactory.createEdgesGraph(v,edges);
		
		Multiset<Edge> sub = HashMultiset.create();		
		
		Edge e = new Edge(0,1);
		sub.add(e);
		Edge f = new Edge(2,4);
		sub.add(f);
		Edge g = new Edge(3,5);
		sub.add(g);
				
		assertTrue(graphEdges.isPerfectMatching(sub));
	}
	
	
	@Test
	public void testPerfectMatching_Incidence_true() {
		int[][] incidence = {{ 0, 1, 1, 0, 0, 0 },
				 			 { 1, 1, 0, 1, 0, 0 },
				 			 { 1, 0, 0, 0, 1, 1 },
				 			 { 0, 1, 0, 0, 1, 1 },
				 			 { 0, 0, 1, 1, 0, 1 },
				 			 { 0, 0, 1, 1, 1, 0 }};
		MatrixGraph graphIncidence = matrixFactory.createMatrixGraph(incidence);
		
		int[][] sub = {{ 0, 1, 0, 0, 0, 0 },
					   { 1, 0, 0, 0, 0, 0 },
					   { 0, 0, 0, 0, 1, 0 },
					   { 0, 0, 0, 0, 0, 1 },
					   { 0, 0, 1, 0, 0, 0 },
					   { 0, 0, 0, 1, 0, 0 }};
		
		assertTrue(graphIncidence.isPerfectMatching(sub));
	}


	@Test
	public void testFactoryList_MatrixList_true() {
		
		int[][] matrix = {{ 0, 1, 1, 0, 0 },
						  { 1, 1, 1, 0, 0 },
						  { 1, 1, 0, 1, 0 },
						  { 0, 0, 1, 0, 0 },
						  { 0, 0, 0, 0, 0 }};
		
		int v = 5;
		
		ArrayList<LinkedList<Integer>> list = new ArrayList<LinkedList<Integer>>(5);
		
		LinkedList<Integer> list0 = new LinkedList<Integer>();
		list0.add(1);
		list0.add(2);
		list.add(list0);
		LinkedList<Integer> list1 = new LinkedList<Integer>();
		list1.add(0);
		list1.add(1);
		list1.add(2);
		list.add(list1);
		LinkedList<Integer> list2 = new LinkedList<Integer>();
		list2.add(0);
		list2.add(1);
		list2.add(3);
		list.add(list2);	
		LinkedList<Integer> list3 = new LinkedList<Integer>();
		list3.add(2);
		list.add(list3);
		
		AbstractGraph graph = listFactory.createListGraph(matrix);
		AbstractGraph otherGraph = listFactory.createListGraph(v, list);
		
		assertTrue(graph.equals(otherGraph));
	}
	
	@Test
	public void testFactoryList_EdgesList_true() {

		int v = 5;
		
		Multiset<Edge> edges = HashMultiset.create();
		
		Edge e0 = new Edge(0, 1);
		edges.add(e0);
		Edge e1 = new Edge(1, 1);
		edges.add(e1);
		Edge e2 = new Edge(1, 2);
		edges.add(e2);
		Edge e3 = new Edge(0, 2);
		edges.add(e3);
		Edge e4 = new Edge(2, 3);
		edges.add(e4);
				
		ArrayList<LinkedList<Integer>> list = new ArrayList<LinkedList<Integer>>(5);
		
		LinkedList<Integer> list0 = new LinkedList<Integer>();
		list0.add(1);
		list0.add(2);
		list.add(list0);
		LinkedList<Integer> list1 = new LinkedList<Integer>();
		list1.add(0);
		list1.add(1);
		list1.add(2);
		list.add(list1);
		LinkedList<Integer> list2 = new LinkedList<Integer>();
		list2.add(0);
		list2.add(1);
		list2.add(3);
		list.add(list2);	
		LinkedList<Integer> list3 = new LinkedList<Integer>();
		list3.add(2);
		list.add(list3);
		
		AbstractGraph graph = listFactory.createListGraph(v, edges);
		AbstractGraph otherGraph = listFactory.createListGraph(v, list);
		
		assertTrue(graph.equals(otherGraph));
	}

	@Test
	public void testFactoryList_EdgesMatrix_true() {

		int v = 5;
		
		Multiset<Edge> edges = HashMultiset.create();
		
		Edge e0 = new Edge(0, 1);
		edges.add(e0);
		Edge e1 = new Edge(1, 1);
		edges.add(e1);
		Edge e2 = new Edge(1, 2);
		edges.add(e2);
		Edge e3 = new Edge(0, 2);
		edges.add(e3);
		Edge e4 = new Edge(2, 3);
		edges.add(e4);
				
		int[][] matrix = {{ 0, 1, 1, 0, 0 },
						  { 1, 1, 1, 0, 0 },
						  { 1, 1, 0, 1, 0 },
						  { 0, 0, 1, 0, 0 },
						  { 0, 0, 0, 0, 0 }};
		
		AbstractGraph graph = listFactory.createListGraph(v, edges);
		AbstractGraph otherGraph = listFactory.createListGraph(matrix);
		
		assertTrue(graph.equals(otherGraph));
	}

	@Test
	public void testListFactory_exceptionVertex() {
		
		int v = 3;
		
		ArrayList<LinkedList<Integer>> list = new ArrayList<LinkedList<Integer>>(5);
		
		LinkedList<Integer> list0 = new LinkedList<Integer>();
		list0.add(1);
		list0.add(2);
		list.add(list0);
		LinkedList<Integer> list1 = new LinkedList<Integer>();
		list1.add(0);
		list1.add(1);
		list1.add(2);
		list.add(list1);
		LinkedList<Integer> list2 = new LinkedList<Integer>();
		list2.add(0);
		list2.add(1);
		list2.add(3);
		list.add(list2);	
		LinkedList<Integer> list3 = new LinkedList<Integer>();
		list3.add(2);
		list.add(list3);
		
		boolean thrown = false;
		
		try {
			AbstractGraph graph = listFactory.createListGraph(v, list);
		} catch (VertexNotInGraphException e) {
			thrown = true;
		}
		
		assertTrue(thrown);
	}
	
	@Test
	public void testListFactory_exceptionList() {
		
		int v = 4;
		
		ArrayList<LinkedList<Integer>> list = new ArrayList<LinkedList<Integer>>(5);
		
		LinkedList<Integer> list0 = new LinkedList<Integer>();
		list0.add(1);
		list.add(list0);
		LinkedList<Integer> list1 = new LinkedList<Integer>();
		list1.add(0);
		list1.add(1);
		list1.add(2);
		list.add(list1);
		LinkedList<Integer> list2 = new LinkedList<Integer>();
		list2.add(0);
		list2.add(1);
		list2.add(3);
		list.add(list2);	
		LinkedList<Integer> list3 = new LinkedList<Integer>();
		list3.add(2);
		list.add(list3);
		
		boolean thrown = false;
		
		try {
			AbstractGraph graph = listFactory.createListGraph(v, list);
		} catch (ListNotValidException e) {
			thrown = true;
		}
		
		assertTrue(thrown);
	}
	@Test
	public void testFactories_MatrixList_true() {
		
		int[][] matrix = {{ 0, 1, 1, 0, 0 },
						  { 1, 1, 1, 0, 0 },
						  { 1, 1, 0, 1, 0 },
						  { 0, 0, 1, 0, 0 },
						  { 0, 0, 0, 0, 0 }};
		
		int v = 5;
		
		ArrayList<LinkedList<Integer>> list = new ArrayList<LinkedList<Integer>>(5);
		
		LinkedList<Integer> list0 = new LinkedList<Integer>();
		list0.add(1);
		list0.add(2);
		list.add(list0);
		LinkedList<Integer> list1 = new LinkedList<Integer>();
		list1.add(0);
		list1.add(1);
		list1.add(2);
		list.add(list1);
		LinkedList<Integer> list2 = new LinkedList<Integer>();
		list2.add(0);
		list2.add(1);
		list2.add(3);
		list.add(list2);	
		LinkedList<Integer> list3 = new LinkedList<Integer>();
		list3.add(2);
		list.add(list3);
		
		AbstractGraph graph = matrixFactory.createMatrixGraph(matrix);
		AbstractGraph otherGraph = listFactory.createListGraph(v, list);
		
		assertTrue(graph.equals(otherGraph));
	}
	
	@Test
	public void testFactories_EdgesList_true() {

		int v = 5;
		
		Multiset<Edge> edges = HashMultiset.create();
		
		Edge e0 = new Edge(0, 1);
		edges.add(e0);
		Edge e1 = new Edge(1, 1);
		edges.add(e1);
		Edge e2 = new Edge(1, 2);
		edges.add(e2);
		Edge e3 = new Edge(0, 2);
		edges.add(e3);
		Edge e4 = new Edge(2, 3);
		edges.add(e4);
				
		ArrayList<LinkedList<Integer>> list = new ArrayList<LinkedList<Integer>>(5);
		
		LinkedList<Integer> list0 = new LinkedList<Integer>();
		list0.add(1);
		list0.add(2);
		list.add(list0);
		LinkedList<Integer> list1 = new LinkedList<Integer>();
		list1.add(0);
		list1.add(1);
		list1.add(2);
		list.add(list1);
		LinkedList<Integer> list2 = new LinkedList<Integer>();
		list2.add(0);
		list2.add(1);
		list2.add(3);
		list.add(list2);	
		LinkedList<Integer> list3 = new LinkedList<Integer>();
		list3.add(2);
		list.add(list3);
		
		AbstractGraph graph = edgesFactory.createEdgesGraph(v, edges);
		AbstractGraph otherGraph = listFactory.createListGraph(v, list);
		
		assertTrue(graph.equals(otherGraph));
	}	

	@Test
	public void testAddVertices_List_true() {

		int v0 = 4;
							
		ArrayList<LinkedList<Integer>> adjacency0 = new ArrayList<LinkedList<Integer>>(v0);
		
		LinkedList<Integer> list00 = new LinkedList<Integer>();
		list00.add(1);
		list00.add(2);
		adjacency0.add(list00);
		LinkedList<Integer> list10 = new LinkedList<Integer>();
		list10.add(0);
		list10.add(1);
		list10.add(2);
		adjacency0.add(list10);
		LinkedList<Integer> list20 = new LinkedList<Integer>();
		list20.add(0);
		list20.add(1);
		list20.add(3);
		adjacency0.add(list20);	
		LinkedList<Integer> list30 = new LinkedList<Integer>();
		list30.add(2);
		adjacency0.add(list30);

		
		ListGraph graph = listFactory.createListGraph(v0, adjacency0);
		
		graph.addVertices(2);

		int v1 = 6;
		
		ArrayList<LinkedList<Integer>> adjacency1 = new ArrayList<LinkedList<Integer>>(v1);
		
		LinkedList<Integer> list01 = new LinkedList<Integer>();
		list01.add(1);
		list01.add(2);
		adjacency1.add(list01);
		LinkedList<Integer> list11 = new LinkedList<Integer>();
		list11.add(0);
		list11.add(1);
		list11.add(2);
		adjacency1.add(list11);
		LinkedList<Integer> list21 = new LinkedList<Integer>();
		list21.add(0);
		list21.add(1);
		list21.add(3);
		adjacency1.add(list21);	
		LinkedList<Integer> list31 = new LinkedList<Integer>();
		list31.add(2);
		adjacency1.add(list31);
		
		ListGraph otherGraph = listFactory.createListGraph(v1, adjacency1);
		
		assertTrue(graph.equals(otherGraph));
	}	

	@Test
	public void testAddEdge_List_true() {
		int v0 = 5;
		
		ArrayList<LinkedList<Integer>> adjacency0 = new ArrayList<LinkedList<Integer>>(v0);
		
		LinkedList<Integer> list00 = new LinkedList<Integer>();
		list00.add(1);
		list00.add(2);
		adjacency0.add(list00);
		LinkedList<Integer> list10 = new LinkedList<Integer>();
		list10.add(0);
		list10.add(1);
		list10.add(2);
		adjacency0.add(list10);
		LinkedList<Integer> list20 = new LinkedList<Integer>();
		list20.add(0);
		list20.add(1);
		list20.add(3);
		adjacency0.add(list20);	
		LinkedList<Integer> list30 = new LinkedList<Integer>();
		list30.add(2);
		adjacency0.add(list30);

		ListGraph graph = listFactory.createListGraph(v0, adjacency0);
		
		graph.addEdge(3, 0);

		int v1 = 5;

		ArrayList<LinkedList<Integer>> adjacency1 = new ArrayList<LinkedList<Integer>>(v1);
		
		LinkedList<Integer> list01 = new LinkedList<Integer>();
		list01.add(1);
		list01.add(2);
		list01.add(3);
		adjacency1.add(list01);
		LinkedList<Integer> list11 = new LinkedList<Integer>();
		list11.add(0);
		list11.add(1);
		list11.add(2);
		adjacency1.add(list11);
		LinkedList<Integer> list21 = new LinkedList<Integer>();
		list21.add(0);
		list21.add(1);
		list21.add(3);
		adjacency1.add(list21);	
		LinkedList<Integer> list31 = new LinkedList<Integer>();
		list31.add(0);
		list31.add(2);
		adjacency1.add(list31);
		
		ListGraph otherGraph = listFactory.createListGraph(v1, adjacency1);
		
		assertTrue(graph.equals(otherGraph));
	}	
	
	@Test
	public void testAddEdge_List_exception() {
		int v0 = 4;
		
		ArrayList<LinkedList<Integer>> adjacency0 = new ArrayList<LinkedList<Integer>>(v0);
		
		LinkedList<Integer> list00 = new LinkedList<Integer>();
		list00.add(1);
		list00.add(2);
		adjacency0.add(list00);
		LinkedList<Integer> list10 = new LinkedList<Integer>();
		list10.add(0);
		list10.add(1);
		list10.add(2);
		adjacency0.add(list10);
		LinkedList<Integer> list20 = new LinkedList<Integer>();
		list20.add(0);
		list20.add(1);
		list20.add(3);
		adjacency0.add(list20);	
		LinkedList<Integer> list30 = new LinkedList<Integer>();
		list30.add(2);
		adjacency0.add(list30);

		ListGraph graph = listFactory.createListGraph(v0, adjacency0);
		
		boolean thrown = false;
		
		try {
			graph.addEdge(4, 0);			
		} catch (VertexNotInGraphException e) {
			thrown = true;
		}

		assertTrue(thrown);
	}	
	
	
	@Test
	public void testRemoveVertex_List_true() {

		int v0 = 4;
		
		ArrayList<LinkedList<Integer>> adjacency0 = new ArrayList<LinkedList<Integer>>(v0);
		
		LinkedList<Integer> list00 = new LinkedList<Integer>();
		list00.add(1);
		list00.add(2);
		list00.add(3);
		adjacency0.add(list00);
		LinkedList<Integer> list10 = new LinkedList<Integer>();
		list10.add(0);
		list10.add(1);
		list10.add(2);
		adjacency0.add(list10);
		LinkedList<Integer> list20 = new LinkedList<Integer>();
		list20.add(0);
		list20.add(1);
		list20.add(3);
		adjacency0.add(list20);	
		LinkedList<Integer> list30 = new LinkedList<Integer>();
		list30.add(0);
		list30.add(2);
		adjacency0.add(list30);

		ListGraph graph = listFactory.createListGraph(v0, adjacency0);
		
		graph.removeVertex(2);

		int v1 = 3;
		
		ArrayList<LinkedList<Integer>> adjacency1 = new ArrayList<LinkedList<Integer>>(v1);
		
		LinkedList<Integer> list01 = new LinkedList<Integer>();
		list01.add(1);
		list01.add(2);
		adjacency1.add(list01);
		LinkedList<Integer> list11 = new LinkedList<Integer>();
		list11.add(0);
		list11.add(1);
		adjacency1.add(list11);
		LinkedList<Integer> list21 = new LinkedList<Integer>();
		list21.add(0);
		adjacency1.add(list21);
		
		ListGraph otherGraph = listFactory.createListGraph(v1, adjacency1);
		
		assertTrue(graph.equals(otherGraph));
	}

	public void testRemoveVertex_List_exception() {

		int v0 = 4;
		
		ArrayList<LinkedList<Integer>> adjacency0 = new ArrayList<LinkedList<Integer>>(v0);
		
		LinkedList<Integer> list00 = new LinkedList<Integer>();
		list00.add(1);
		list00.add(2);
		list00.add(3);
		adjacency0.add(list00);
		LinkedList<Integer> list10 = new LinkedList<Integer>();
		list10.add(0);
		list10.add(1);
		list10.add(2);
		adjacency0.add(list10);
		LinkedList<Integer> list20 = new LinkedList<Integer>();
		list20.add(0);
		list20.add(1);
		list20.add(3);
		adjacency0.add(list20);	
		LinkedList<Integer> list30 = new LinkedList<Integer>();
		list30.add(0);
		list30.add(2);
		adjacency0.add(list30);

		ListGraph graph = listFactory.createListGraph(v0, adjacency0);

		boolean thrown = false;
		
		try {
			graph.removeVertex(4);			
		} catch (VertexNotInGraphException e) {
			thrown = true;
		}

		assertTrue(thrown);
	}
	
	@Test
	public void testRemoveEdge_List_true() {

		int v0 = 4;
		
		ArrayList<LinkedList<Integer>> adjacency0 = new ArrayList<LinkedList<Integer>>(v0);
		
		LinkedList<Integer> list00 = new LinkedList<Integer>();
		list00.add(1);
		list00.add(2);
		list00.add(3);
		adjacency0.add(list00);
		LinkedList<Integer> list10 = new LinkedList<Integer>();
		list10.add(0);
		list10.add(1);
		list10.add(2);
		adjacency0.add(list10);
		LinkedList<Integer> list20 = new LinkedList<Integer>();
		list20.add(0);
		list20.add(1);
		list20.add(3);
		adjacency0.add(list20);	
		LinkedList<Integer> list30 = new LinkedList<Integer>();
		list30.add(0);
		list30.add(2);
		adjacency0.add(list30);

		ListGraph graph = listFactory.createListGraph(v0, adjacency0);
		
		graph.removeEdge(0, 1);

		int v1 = 4;
		
		ArrayList<LinkedList<Integer>> adjacency1 = new ArrayList<LinkedList<Integer>>(v1);
		
		LinkedList<Integer> list01 = new LinkedList<Integer>();
		list01.add(2);
		list01.add(3);
		adjacency1.add(list01);
		LinkedList<Integer> list11 = new LinkedList<Integer>();
		list11.add(1);
		list11.add(2);
		adjacency1.add(list11);
		LinkedList<Integer> list21 = new LinkedList<Integer>();
		list21.add(0);
		list21.add(1);
		list21.add(3);
		adjacency1.add(list21);	
		LinkedList<Integer> list31 = new LinkedList<Integer>();
		list31.add(0);
		list31.add(2);
		adjacency1.add(list31);
		
		ListGraph otherGraph = listFactory.createListGraph(v1, adjacency1);
		
		assertTrue(graph.equals(otherGraph));
	}	
	
	@Test
	public void testRemoveEdge_List_exceptionEdge() {

		int v0 = 4;
		
		ArrayList<LinkedList<Integer>> adjacency0 = new ArrayList<LinkedList<Integer>>(v0);
		
		LinkedList<Integer> list00 = new LinkedList<Integer>();
		list00.add(1);
		list00.add(2);
		list00.add(3);
		adjacency0.add(list00);
		LinkedList<Integer> list10 = new LinkedList<Integer>();
		list10.add(0);
		list10.add(1);
		list10.add(2);
		adjacency0.add(list10);
		LinkedList<Integer> list20 = new LinkedList<Integer>();
		list20.add(0);
		list20.add(1);
		list20.add(3);
		adjacency0.add(list20);	
		LinkedList<Integer> list30 = new LinkedList<Integer>();
		list30.add(0);
		list30.add(2);
		adjacency0.add(list30);

		ListGraph graph = listFactory.createListGraph(v0, adjacency0);
		
		boolean thrown = false;
		
		try {
			graph.removeEdge(1, 3);
		} catch (EdgeNotInGraphException e) {
			thrown = true;
		}
		
		assertTrue(thrown);
	}
	
	@Test
	public void testRemoveEdge_List_exceptionVertex() {

		int v0 = 4;
		
		ArrayList<LinkedList<Integer>> adjacency0 = new ArrayList<LinkedList<Integer>>(v0);
		
		LinkedList<Integer> list00 = new LinkedList<Integer>();
		list00.add(1);
		list00.add(2);
		list00.add(3);
		adjacency0.add(list00);
		LinkedList<Integer> list10 = new LinkedList<Integer>();
		list10.add(0);
		list10.add(1);
		list10.add(2);
		adjacency0.add(list10);
		LinkedList<Integer> list20 = new LinkedList<Integer>();
		list20.add(0);
		list20.add(1);
		list20.add(3);
		adjacency0.add(list20);	
		LinkedList<Integer> list30 = new LinkedList<Integer>();
		list30.add(0);
		list30.add(2);
		adjacency0.add(list30);

		ListGraph graph = listFactory.createListGraph(v0, adjacency0);
		
		boolean thrown = false;
		
		try {
			graph.removeEdge(0, 4);
		} catch (VertexNotInGraphException e) {
			thrown = true;
		}
		
		assertTrue(thrown);
	}	

	
	@Test
	public void testIsEulerian_List_false() {

		int v = 4;
		
		ArrayList<LinkedList<Integer>> list = new ArrayList<LinkedList<Integer>>(5);
		
		LinkedList<Integer> list0 = new LinkedList<Integer>();
		list0.add(1);
		list0.add(2);
		list0.add(3);
		list.add(list0);
		LinkedList<Integer> list1 = new LinkedList<Integer>();
		list1.add(0);
		list1.add(1);
		list1.add(2);
		list.add(list1);
		LinkedList<Integer> list2 = new LinkedList<Integer>();
		list2.add(0);
		list2.add(1);
		list2.add(3);
		list.add(list2);	
		LinkedList<Integer> list3 = new LinkedList<Integer>();
		list3.add(0);
		list3.add(2);
		list.add(list3);
		
		ListGraph graph = listFactory.createListGraph(v, list);
				
		assertFalse(graph.isEulerian());
	}	
	
	@Test
	public void testIsEulerian_List_true() {

		int v = 5;
		
		ArrayList<LinkedList<Integer>> list = new ArrayList<LinkedList<Integer>>(5);
		
		LinkedList<Integer> list0 = new LinkedList<Integer>();
		list0.add(1);
		list0.add(3);
		list.add(list0);
		LinkedList<Integer> list1 = new LinkedList<Integer>();
		list1.add(0);
		list1.add(1);
		list1.add(2);
		list.add(list1);
		LinkedList<Integer> list2 = new LinkedList<Integer>();
		list2.add(1);
		list2.add(3);
		list.add(list2);	
		LinkedList<Integer> list3 = new LinkedList<Integer>();
		list3.add(0);
		list3.add(2);
		list.add(list3);		
		ListGraph graph = listFactory.createListGraph(v, list);
				
		assertTrue(graph.isEulerian());
	}
	
	@Test
	public void testMergeCycles_true() {
		int v = 4;
		
		Multiset<Edge> e = HashMultiset.create();		
		
		Edge e0 = new Edge(0, 1);
		e.add(e0);
		Edge e1 = new Edge(1, 2);
		e.add(e1);
		Edge e2 = new Edge(2, 0);
		e.add(e2);
		Edge e3 = new Edge(1, 3);
		e.add(e3);
		Edge e4 = new Edge(3, 2);
		e.add(e4);
		Edge e5 = new Edge (2, 1);
		e.add(e5);
		
		EdgesGraph graph = edgesFactory.createEdgesGraph(v, e);
	
		List<Integer> tour1 = Lists.newArrayList();
		
		tour1.add(0);
		tour1.add(1);
		tour1.add(2);

		List<Integer> tour2 = Lists.newArrayList();
		
		tour2.add(1);
		tour2.add(3);
		tour2.add(2);
		
		List<Integer> tour3 = Lists.newArrayList();
		
		tour3.add(0);
		tour3.add(1);
		tour3.add(3);
		tour3.add(2);
		tour3.add(1);
		tour3.add(2);

		Optional<List<Integer>> tourA = Optional.of(tour1);
		Optional<List<Integer>> tourB = Optional.of(tour2);
		Optional<List<Integer>> tourC = Optional.of(tour3);
		
		assertTrue(graph.mergeTours(tourA, tourB, 1).equals(tourC));
	}
	
	@Test
	public void testGetEulerianCycle_long1_true() {
		int v = 5;
		
		Multiset<Edge> e = HashMultiset.create();		
		
		Edge e0 = new Edge(0, 1);
		e.add(e0);
		Edge e1 = new Edge(1, 2);
		e.add(e1);
		Edge e2 = new Edge(2, 0);
		e.add(e2);
		Edge e3 = new Edge(1, 3);
		e.add(e3);
		Edge e4 = new Edge(3, 4);
		e.add(e4);
		Edge e5 = new Edge(4, 1);
		e.add(e5);
		
		EdgesGraph graph = edgesFactory.createEdgesGraph(v, e);
	
		List<Integer> tour = Lists.newArrayList();
		
		tour.add(0);
		tour.add(1);
		tour.add(3);
		tour.add(4);
		tour.add(1);
		tour.add(2);
		
		Optional<List<Integer>> tourA = Optional.of(tour);
		
		assertTrue(graph.getEulerianCycle(graph, 0).equals(tourA));
	}	
	
	@Test
	public void testGetEulerianCycle_long2_true() {
		int v = 6;
		
		Multiset<Edge> e = HashMultiset.create();		
		
		Edge e0 = new Edge(0, 1);
		e.add(e0);
		Edge e1 = new Edge(1, 2);
		e.add(e1);
		Edge e2 = new Edge(2, 0);
		e.add(e2);
		Edge e3 = new Edge(1, 3);
		e.add(e3);
		Edge e4 = new Edge(3, 4);
		e.add(e4);
		Edge e5 = new Edge(4, 1);
		e.add(e5);
		Edge e6 = new Edge(2, 4);
		e.add(e6);
		Edge e7 = new Edge(4, 5);
		e.add(e7);
		Edge e8 = new Edge(2, 5);
		e.add(e8);
		
		EdgesGraph graph = edgesFactory.createEdgesGraph(v, e);
	
		List<Integer> tour = Lists.newArrayList();
		
		tour.add(0);
		tour.add(1);
		tour.add(2);
		tour.add(4);
		tour.add(3);
		tour.add(1);
		tour.add(4);
		tour.add(5);
		tour.add(2);
		
		Optional<List<Integer>> tourA = Optional.of(tour);
		
		assertTrue(graph.getEulerianCycle(graph, 0).equals(tourA));
	}	

	@Test
	public void testGetEulerianCycle_long3_true() {
		int v = 7;
		
		Multiset<Edge> e = HashMultiset.create();		
		
		Edge e0 = new Edge(0, 1);
		e.add(e0);
		Edge e1 = new Edge(1, 2);
		e.add(e1);
		Edge e2 = new Edge(2, 0);
		e.add(e2);
		Edge e3 = new Edge(1, 3);
		e.add(e3);
		Edge e4 = new Edge(3, 4);
		e.add(e4);
		Edge e5 = new Edge(4, 1);
		e.add(e5);
		Edge e6 = new Edge(2, 5);
		e.add(e6);
		Edge e7 = new Edge(5, 6);
		e.add(e7);
		Edge e8 = new Edge(6, 2);
		e.add(e8);
		
		EdgesGraph graph = edgesFactory.createEdgesGraph(v, e);
	
		List<Integer> tour = Lists.newArrayList();
		
		tour.add(0);
		tour.add(1);
		tour.add(3);
		tour.add(4);
		tour.add(1);
		tour.add(2);
		tour.add(5);
		tour.add(6);
		tour.add(2);

		Optional<List<Integer>> tourA = Optional.of(tour);
		
		assertTrue(graph.getEulerianCycle(graph, 0).equals(tourA));
	}	

	@Test
	public void testGetEulerianCycle_long4_true() {
		int v = 7;
		
		Multiset<Edge> e = HashMultiset.create();		
		
		Edge e0 = new Edge(0, 1);
		e.add(e0);
		Edge e1 = new Edge(1, 2);
		e.add(e1);
		Edge e2 = new Edge(2, 0);
		e.add(e2);
		Edge e3 = new Edge(1, 3);
		e.add(e3);
		Edge e4 = new Edge(3, 4);
		e.add(e4);
		Edge e5 = new Edge(4, 1);
		e.add(e5);
		Edge e6 = new Edge(1, 5);
		e.add(e6);
		Edge e7 = new Edge(5, 6);
		e.add(e7);
		Edge e8 = new Edge(6, 1);
		e.add(e8);
		
		EdgesGraph graph = edgesFactory.createEdgesGraph(v, e);
	
		List<Integer> tour = Lists.newArrayList();
		
		tour.add(0);
		tour.add(1);
		tour.add(3);
		tour.add(4);
		tour.add(1);
		tour.add(5);
		tour.add(6);
		tour.add(1);
		tour.add(2);
		
		Optional<List<Integer>> tourA = Optional.of(tour);
		
		assertTrue(graph.getEulerianCycle(graph, 0).equals(tourA));
	}	

	@Test
	public void testGetEulerianCycle_long5_true() {
		int v = 7;
		
		Multiset<Edge> e = HashMultiset.create();		
		
		Edge e0 = new Edge(0, 1);
		e.add(e0);
		Edge e1 = new Edge(1, 2);
		e.add(e1);
		Edge e2 = new Edge(2, 0);
		e.add(e2);
		Edge e3 = new Edge(1, 3);
		e.add(e3);
		Edge e4 = new Edge(3, 4);
		e.add(e4);
		Edge e5 = new Edge(4, 1);
		e.add(e5);
		Edge e6 = new Edge(3, 5);
		e.add(e6);
		Edge e7 = new Edge(5, 6);
		e.add(e7);
		Edge e8 = new Edge(6, 3);
		e.add(e8);
		
		EdgesGraph graph = edgesFactory.createEdgesGraph(v, e);
	
		List<Integer> tour = Lists.newArrayList();
		
		tour.add(0);
		tour.add(1);
		tour.add(3);
		tour.add(5);
		tour.add(6);
		tour.add(3);
		tour.add(4);
		tour.add(1);
		tour.add(2);
		 
		Optional<List<Integer>> tourA = Optional.of(tour);
		assertTrue(graph.getEulerianCycle(graph, 0).equals(tourA));
	}	
	
	@Test
	public void testGetEulerianCycle_long_multiEdge_true() {
		int v = 4;
		
		Multiset<Edge> e = HashMultiset.create();		
		
		Edge e0 = new Edge(0, 1);
		e.add(e0);
		Edge e1 = new Edge(1, 2);
		e.add(e1);
		Edge e2 = new Edge(2, 0);
		e.add(e2);
		Edge e3 = new Edge(1, 2);
		e.add(e3);
		Edge e4 = new Edge(2, 3);
		e.add(e4);
		Edge e5 = new Edge (3, 1);
		e.add(e5);
		
		EdgesGraph graph = edgesFactory.createEdgesGraph(v, e);
	
		List<Integer> tour = Lists.newArrayList();
		
		tour.add(0);
		tour.add(1);
		tour.add(2);
		tour.add(3);
		tour.add(1);
		tour.add(2);
		
		Optional<List<Integer>> tourA = Optional.of(tour);
		assertTrue(graph.getEulerianCycle(graph, 0).equals(tourA));
	}	

	@Test
	public void testGetEulerianCycle_long_multiEdge2_true() {
		int v = 6;
		
		Multiset<Edge> e = HashMultiset.create();		
		
		Edge e0 = new Edge(0, 1);
		e.add(e0);
		Edge e1 = new Edge(1, 2);
		e.add(e1);
		Edge e2 = new Edge(2, 0);
		e.add(e2);
		Edge e3 = new Edge(1, 2);
		e.add(e3);
		Edge e4 = new Edge(2, 3);
		e.add(e4);
		Edge e5 = new Edge (3, 1);
		e.add(e5);
		Edge e6 = new Edge (3, 4);
		e.add(e6);
		Edge e7 = new Edge (4, 5);
		e.add(e7);
		Edge e8 = new Edge (5, 3);
		e.add(e8);
		
		EdgesGraph graph = edgesFactory.createEdgesGraph(v, e);
	
		List<Integer> tour = Lists.newArrayList();
		
		tour.add(0);
		tour.add(1);
		tour.add(2);
		tour.add(3);
		tour.add(4);
		tour.add(5);
		tour.add(3);
		tour.add(1);
		tour.add(2);
		
		Optional<List<Integer>> tourA = Optional.of(tour);
		assertTrue(graph.getEulerianCycle(graph, 0).equals(tourA));
	}	

	@Test
	public void testGetCycle_short_true() {
		int v = 3;
		
		Multiset<Edge> e = HashMultiset.create();		
		
		Edge e0 = new Edge(0, 1);
		e.add(e0);
		Edge e1 = new Edge(1, 2);
		e.add(e1);
		Edge e2 = new Edge(2, 0);
		e.add(e2);
		
		EdgesGraph graph = edgesFactory.createEdgesGraph(v, e);
	
		List<Integer> tour = Lists.newArrayList();
		
		tour.add(0);
		tour.add(1);
		tour.add(2);
		
		Optional<List<Integer>> tourA = Optional.of(tour);
		assertTrue(graph.getCycle(graph, 0).equals(tourA));
	}

	@Test
	public void testGetCycle_longer_true() {
		int v = 4;
		
		Multiset<Edge> e = HashMultiset.create();		
		
		Edge e0 = new Edge(0, 1);
		e.add(e0);
		Edge e1 = new Edge(1, 2);
		e.add(e1);
		Edge e2 = new Edge(2, 0);
		e.add(e2);
		e.add(new Edge(2, 3));		
		e.add(new Edge(3, 2));		
		
		EdgesGraph graph = edgesFactory.createEdgesGraph(v, e);
	
		List<Integer> tour = Lists.newArrayList();
		
		tour.add(0);
		tour.add(1);
		tour.add(2);
		
		Optional<List<Integer>> tourA = Optional.of(tour);
		assertTrue(graph.getCycle(graph, 0).equals(tourA));
	}


	@Test
	public void testGetCycle_multiedge_true() {
		int v = 3;
		
		Multiset<Edge> e = HashMultiset.create();		
		
		Edge e0 = new Edge(0, 1);
		e.add(e0);
		Edge e1 = new Edge(1, 2);
		e.add(e1);
		Edge e2 = new Edge(2, 0);
		e.add(e2);
		e.add(new Edge(1, 2));		
		e.add(new Edge(2, 1));		
		
		EdgesGraph graph = edgesFactory.createEdgesGraph(v, e);
	
		List<Integer> tour = Lists.newArrayList();
		
		tour.add(0);
		tour.add(1);
		tour.add(2);
		
		Optional<List<Integer>> tourA = Optional.of(tour);		
		assertTrue(graph.getCycle(graph, 0).equals(tourA));
	}

	@Test
	public void testGetEulerianCycle_short_true() {
		int v = 3;
		
		Multiset<Edge> e = HashMultiset.create();		
		
		Edge e0 = new Edge(0, 1);
		e.add(e0);
		Edge e1 = new Edge(1, 2);
		e.add(e1);
		Edge e2 = new Edge(2, 0);
		e.add(e2);
		
		EdgesGraph graph = edgesFactory.createEdgesGraph(v, e);
	
		List<Integer> tour = Lists.newArrayList();
		
		tour.add(0);
		tour.add(1);
		tour.add(2);
		
		Optional<List<Integer>> tourA = Optional.of(tour);
		assertTrue(graph.getEulerianCycle(graph, 0).equals(tourA));
	}

	
	
	@Test
	public void testGetEulerianCycle_short_loop_true() {
		int v = 3;
		
		Multiset<Edge> e = HashMultiset.create();		
		
		Edge e0 = new Edge(0, 1);
		e.add(e0);
		Edge e1 = new Edge(1, 2);
		e.add(e1);
		Edge e2 = new Edge(2, 0);
		e.add(e2);
		Edge e3 = new Edge(2, 2);
		e.add(e3);
		
		EdgesGraph graph = edgesFactory.createEdgesGraph(v, e);
	
		List<Integer> tour = Lists.newArrayList();
		
		tour.add(0);
		tour.add(1);
		tour.add(2);
		tour.add(2);
		
		Optional<List<Integer>> tourA = Optional.of(tour);
		assertTrue(graph.getEulerianCycle(graph, 0).equals(tourA));
	}
	
	@Test
	public void testGetEulerianCycle_short_multiloop_true() {
		int v = 3;
		
		Multiset<Edge> e = HashMultiset.create();		
		
		Edge e0 = new Edge(0, 1);
		e.add(e0);
		Edge e1 = new Edge(1, 2);
		e.add(e1);
		Edge e2 = new Edge(2, 0);
		e.add(e2);
		Edge e3 = new Edge(2, 2);
		e.add(e3);
		Edge e4 = new Edge(2, 2);
		e.add(e4);
		
		EdgesGraph graph = edgesFactory.createEdgesGraph(v, e);
	
		List<Integer> tour = Lists.newArrayList();
		
		tour.add(0);
		tour.add(1);
		tour.add(2);
		tour.add(2);
		tour.add(2);
		
		Optional<List<Integer>> tourA = Optional.of(tour);
		assertTrue(graph.getEulerianCycle(graph, 0).equals(tourA));
	}

	@Test
	public void testGetEulerianCycle_short_twoloops_true() {
		int v = 3;
		
		Multiset<Edge> e = HashMultiset.create();		
		
		Edge e0 = new Edge(0, 1);
		e.add(e0);
		Edge loop = new Edge(1, 1);
		e.add(loop);
		Edge e1 = new Edge(1, 2);
		e.add(e1);
		Edge e2 = new Edge(2, 0);
		e.add(e2);
		Edge e3 = new Edge(2, 2);
		e.add(e3);
		
		EdgesGraph graph = edgesFactory.createEdgesGraph(v, e);
	
		List<Integer> tour = Lists.newArrayList();
		
		tour.add(0);
		tour.add(1);
		tour.add(1);
		tour.add(2);
		tour.add(2);

		Optional<List<Integer>> tourA = Optional.of(tour);
		assertTrue(graph.getEulerianCycle(graph, 0).equals(tourA));
	}

	
	@Test
	public void testGetEulerianCycle_short_loopAt0_true() {
		int v = 3;
		
		Multiset<Edge> e = HashMultiset.create();		
		
		Edge e0 = new Edge(0, 1);
		e.add(e0);
		Edge loop = new Edge(0, 0);
		e.add(loop);
		Edge e1 = new Edge(1, 2);
		e.add(e1);
		Edge e2 = new Edge(2, 0);
		e.add(e2);
		
		EdgesGraph graph = edgesFactory.createEdgesGraph(v, e);
	
		List<Integer> tour = Lists.newArrayList();
		
		tour.add(0);
		tour.add(0);
		tour.add(1);
		tour.add(2);
		
		Optional<List<Integer>> tourA = Optional.of(tour);
		assertTrue(graph.getEulerianCycle(graph, 0).equals(tourA));
	}
	
	@Test
	public void testGetCycle_exception() {
		int v = 3;
		
		Multiset<Edge> e = HashMultiset.create();		
		
		Edge e0 = new Edge(0, 1);
		e.add(e0);
		Edge e1 = new Edge(1, 2);
		e.add(e1);
		
		EdgesGraph graph = edgesFactory.createEdgesGraph(v, e);
		
		assertTrue(graph.getCycle(graph, 0).equals(Optional.absent()));
	}


}