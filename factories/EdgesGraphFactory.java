package factories;

import java.util.ArrayList;
import java.util.LinkedList;

import exceptions.ListNotValidException;
import exceptions.MatrixNotValidException;
import exceptions.VertexNotInGraphException;
import graphimplementations.Edge;
import graphimplementations.EdgesGraph;


import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class EdgesGraphFactory {
	
//	public void foo(IGraphFactory fac) {
//		fac.createGraph(1, new MultiSet())
//		fac.createGraph(int[][])
//	}

	public EdgesGraph createEdgesGraph(int v, Multiset<Edge> e) {
		return new EdgesGraph(v, e);
	}	

	public EdgesGraph createEdgesGraph(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			if (matrix.length != matrix[i].length) {
				throw new MatrixNotValidException();			
			}
			for (int j = 0; j <= i; j++) {
				if (matrix[i][j] < 0 || matrix[i][j] != matrix[j][i]) {
					throw new MatrixNotValidException();
				}
			}
		}
		
		int v = matrix.length;
		
		Multiset<Edge> edges = HashMultiset.create();
		
		for (int i = 0; i < v; i++) { 
			for (int j = 0; j <= i; j++){ // we consider only the lower half of the matrix to avoid duplicate edges
				Edge edge = new Edge(i, j);
				edges.add(edge, matrix[i][j]);
			}
		}
				
		return new EdgesGraph(v, edges);
	}

	public EdgesGraph createEdgesGraph(int v, ArrayList<LinkedList<Integer>> l) {
		for (int i = 0; i < l.size(); i++) {
			for (int j = 0; j < l.get(i).size(); j++) {
				if (l.get(i).get(j) >= v) {
					throw new VertexNotInGraphException();
				}	
			}			
		}
		
		for (int i = 0; i < l.size(); i++) {
			for (int j = 0; j < l.size(); j++) {
				if (l.get(i).contains(j) && j >= l.size() || 
						(l.get(i).contains(j) && !l.get(j).contains(i)) || 
						(!l.get(i).contains(j) && l.get(j).contains(i))) {
					throw new ListNotValidException();
				}
			}
		}
		
		Multiset<Edge> edges = HashMultiset.create();

		for (int i = 0; i < l.size(); i++) {
			for (int j = 0; j < l.get(i).size(); j++) {
				if (j <= i) {
					Edge e = new Edge(i, j);
					edges.add(e);
				}
			}
		}
		
		return new EdgesGraph(v, edges);
		
	}
	
}
