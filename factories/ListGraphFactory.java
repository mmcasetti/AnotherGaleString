package factories;

import java.util.ArrayList;
import java.util.LinkedList;

import com.google.common.collect.Multiset;

import graphimplementations.Edge;
import graphimplementations.ListGraph;

public class ListGraphFactory {
	
	public ListGraph createListGraph(int noOfVertices, ArrayList<LinkedList<Integer>> adjacencyList) {
		
		return new ListGraph(noOfVertices, adjacencyList);
	}

	public ListGraph createListGraph(int[][] adjacencyMatrix) {
		
		int noOfVertices = adjacencyMatrix.length;
		
		ArrayList<LinkedList<Integer>> adjacencyList = new ArrayList<LinkedList<Integer>>();
		
		for (int i = 0; i < noOfVertices; i++) {
			LinkedList<Integer> list = new LinkedList<Integer>();						
			for (int j = 0; j < noOfVertices; j++) {
				for (int k = 0; k < adjacencyMatrix[i][j]; k++) {
					list.add(j);
				}
			}
			adjacencyList.add(list);
		}
		
		return new ListGraph(noOfVertices, adjacencyList);
	}

	public ListGraph createListGraph(int noOfVertices, Multiset<Edge> edges) {

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
