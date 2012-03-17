package factories;

import exceptions.ListNotValidException;
import exceptions.VertexNotInGraphException;
import graphimplementations.Edge;
import graphimplementations.MatrixGraph;

import com.google.common.collect.Multiset;

import java.util.ArrayList;
import java.util.LinkedList;


public class MatrixGraphFactory {
		
	public MatrixGraph createMatrixGraph(int[][] matrix) {
		return new MatrixGraph(matrix);
	}

	public MatrixGraph createMatrixGraph(int v, Multiset<Edge> edges) {
		for (Edge edge : edges) {
			if (edge.getStart() >= v || edge.getEnd() >= v) {
				throw new VertexNotInGraphException();
			}
		}

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


	public MatrixGraph createMatrixGraph(int v, ArrayList<LinkedList<Integer>> list) {
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.get(i).size(); j++) {
				if (list.get(i).get(j) >= v) {
					throw new VertexNotInGraphException();
				}	
			}			
		}
		
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				if (list.get(i).contains(j) && j >= list.size() || 
						(list.get(i).contains(j) && !list.get(j).contains(i)) || 
						(!list.get(i).contains(j) && list.get(j).contains(i))) {
					throw new ListNotValidException();
				}
			}
		}

		
		int[][] matrix = new int[v][v];
		
		for (int i = 0; i < v; i++) {
			for (int j = 0; j < v; j++) {
				int k = 0;
				while (list.get(i).indexOf(j) != -1) {
					k++;
					list.get(i).remove(list.get(i).indexOf(j));
				}
				matrix[i][j] = k;
			}
		}
		
		return new MatrixGraph(matrix);
	}

}
