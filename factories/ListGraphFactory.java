package factories;

import java.util.ArrayList;
import java.util.LinkedList;

import com.google.common.collect.Multiset;

import exceptions.MatrixNotValidException;
import exceptions.VertexNotInGraphException;

import graphimplementations.Edge;
import graphimplementations.ListGraph;

public class ListGraphFactory {
	
	public ListGraph createListGraph(int noOfVertices, ArrayList<LinkedList<Integer>> adjacencyList) {
		
		return new ListGraph(noOfVertices, adjacencyList);
	}

	public ListGraph createListGraph(int[][] matrix) {
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
		
		int noOfVertices = matrix.length;
		
		ArrayList<LinkedList<Integer>> adjacencyList = new ArrayList<LinkedList<Integer>>();
		
		for (int i = 0; i < noOfVertices; i++) {
			LinkedList<Integer> list = new LinkedList<Integer>();						
			for (int j = 0; j < noOfVertices; j++) {
				for (int k = 0; k < matrix[i][j]; k++) {
					list.add(j);
				}
			}
			adjacencyList.add(list);
		}
		
		return new ListGraph(noOfVertices, adjacencyList);
	}

	public ListGraph createListGraph(int noOfVertices, Multiset<Edge> edges) {

		for (Edge edge : edges) {
			if (edge.getStart() >= noOfVertices || edge.getEnd() >= noOfVertices) {
				throw new VertexNotInGraphException();
			}
		}
		
		ArrayList<LinkedList<Integer>> adjacencyList = new ArrayList<LinkedList<Integer>>(noOfVertices);

		for (int i = 0; i < noOfVertices; i++) {
			LinkedList<Integer> list = new LinkedList<Integer>();
			adjacencyList.add(list);
		}
		
		for (Edge e : edges) {
			if (!e.isLoop()) {
				adjacencyList.get(e.getStart()).add(e.getEnd());
				adjacencyList.get(e.getEnd()).add(e.getStart());				
			} else {
				adjacencyList.get(e.getStart()).add(e.getEnd());				
			}
		}
		
		return new ListGraph(noOfVertices, adjacencyList);
	}
}
