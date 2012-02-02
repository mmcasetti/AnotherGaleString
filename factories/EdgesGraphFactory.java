package factories;

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

	// we suppose that the matrix is symmetric, with even values on diagonal (ie a valid matrix)
	public EdgesGraph createEdgesGraph(int[][] matrix) {
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
	
}
