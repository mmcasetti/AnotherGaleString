package factories;

import graphimplementations.Edge;
import graphimplementations.MatrixGraph;

import com.google.common.collect.Multiset;

public class MatrixGraphFactory {
		
	public MatrixGraph createMatrixGraph(int[][] matrix) {
		return new MatrixGraph(matrix);
	}
	
	public MatrixGraph createMatrixGraph(int v, Multiset<Edge> edges) {
		int[][] matrix = new int[v][v];
		
		for (Edge edge : edges){
			if (edge.getStart() != edge.getEnd()) {
				matrix[edge.getStart()][edge.getEnd()]++;
				matrix[edge.getEnd()][edge.getStart()]++;								
			} else {
				matrix[edge.getStart()][edge.getEnd()]++;			
			}
		}
		
		return new MatrixGraph(matrix);
	}
}
