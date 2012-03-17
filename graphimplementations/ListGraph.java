package graphimplementations;

import java.util.ArrayList;
import java.util.LinkedList;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import exceptions.EdgeNotInGraphException;
import exceptions.ListNotValidException;
import exceptions.VertexNotInGraphException;

import abstractclasses.AbstractGraph;

public class ListGraph extends AbstractGraph {

	private int noOfVertices;
	private ArrayList<LinkedList<Integer>> adjacencyList;
	
	
	public ListGraph(int v, ArrayList<LinkedList<Integer>> l) {
		
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
		
		this.noOfVertices = v;
		this.adjacencyList = l;
	}
	
	@Override
	public int getNoOfVertices() {
		return noOfVertices;
	}

	@Override
	public Multiset<Edge> getEdges() {

		Multiset<Edge> edges = HashMultiset.create();

		for (int i = 0; i < getAdjacencyList().size(); i++) {
			for (int j = 0; j < getAdjacencyList().get(i).size(); j++) {
				if (getAdjacencyList().get(i).get(j) <= i) {
					Edge e = new Edge(i, getAdjacencyList().get(i).get(j));
					edges.add(e);
				}
			}
		}
		
		return edges;
	}

	@Override
	public int[][] getMatrix() {
		
		int[][] ajdacencyMatrix = new int[getNoOfVertices()][getNoOfVertices()];
		
		for (int i = 0; i < getNoOfVertices(); i++){
			for (int j = 0; j < getNoOfVertices(); j++){
				while (getAdjacencyList().get(i).contains(j)) {
					ajdacencyMatrix[i][j]++;
					getAdjacencyList().get(i).remove(j);
				}
			}
		}
		return ajdacencyMatrix;
	}

	public ArrayList<LinkedList<Integer>> getAdjacencyList() {
		return adjacencyList;
	}
	
	public void setNoOfVertices(int v) {
		this.noOfVertices = v;
	}

	public void setAdjacencyList(ArrayList<LinkedList<Integer>> l) {
		this.adjacencyList = l;
	}
		
	@Override
	public Multiset<Edge> getEdgesAt(int vertex) {

		if (vertex >= getNoOfVertices()) {
			throw new VertexNotInGraphException();
		}
		
		Multiset<Edge> edgesAt = HashMultiset.create();
		
		for (int j = 0; j < getAdjacencyList().get(vertex).size(); j++) {
			Edge e = new Edge(vertex, j);
			edgesAt.add(e);
		}
		
		return edgesAt;
	}

	@Override
	public void addVertices(int noOfNewVertices) {
				
		int oldNoOfVertices = getNoOfVertices();
		
		setNoOfVertices(oldNoOfVertices + noOfNewVertices);

		ArrayList<LinkedList<Integer>> newList = 
				new ArrayList<LinkedList<Integer>>(getNoOfVertices());
				
		for (int i = 0; i < oldNoOfVertices; i++) {
			LinkedList<Integer> list = new LinkedList<Integer>();
			list = getAdjacencyList().get(i);
			newList.add(list);
		}
		
		setAdjacencyList(newList);
	}


	@Override
	public void addEdge(int start, int end) {				
		
		if (start >= getNoOfVertices() || end >= getNoOfVertices()) {
			throw new VertexNotInGraphException();
		}
		
		int i = 0;
		while (i < getAdjacencyList().get(start).size() && 
				getAdjacencyList().get(start).get(i) < end) {
			i++;
		}

		int j = 0;
		while (j < getAdjacencyList().get(end).size() && 
				getAdjacencyList().get(end).get(j) < start) {
			j++;
		}
		
		ArrayList<LinkedList<Integer>> newList = 
				new ArrayList<LinkedList<Integer>>(getNoOfVertices());
		
		for (int k = 0; k < getAdjacencyList().size(); k++) {
			if (k != start && k != end) {
				LinkedList<Integer> list = new LinkedList<Integer>();
				list = getAdjacencyList().get(k);
				newList.add(list);				
			} else if (k == start) {
				LinkedList<Integer> listStart = new LinkedList<Integer>();
				listStart = getAdjacencyList().get(start);
				listStart.add(i, end);				
				newList.add(listStart);			
			} else if (k == end) {
				LinkedList<Integer> listEnd = new LinkedList<Integer>();
				listEnd = getAdjacencyList().get(end);
				listEnd.add(j, start);
				newList.add(listEnd);				
			}			
		}
				
		setAdjacencyList(newList);
	}

	// WE SUPPOSE THAT VERTEX IS IN GRAPH
	@Override
	public void removeVertex(int v) {

		if (v >= getNoOfVertices()) {
			throw new VertexNotInGraphException();
		}
		ArrayList<LinkedList<Integer>> newList = 
				new ArrayList<LinkedList<Integer>>();
				
		for (int i = 0; i < v; i++) {
			LinkedList<Integer> list = new LinkedList<Integer>();
			for (Integer j : getAdjacencyList().get(i)) {
				if (j < v) {
					list.add(j);
				} else if (j > v) {		
					list.add(j - 1);
				}
			}
			newList.add(list);
		}
		for (int i = v; i < getNoOfVertices() - 1; i++) {
			LinkedList<Integer> list = new LinkedList<Integer>();
			for (Integer j : getAdjacencyList().get(i + 1)) {
				if (j < v) {
					list.add(j);
				} else if (j > v) {
					list.add(j - 1);
				}
			}			
			newList.add(list);
		}
		
		setAdjacencyList(newList);
		
		setNoOfVertices(getNoOfVertices() - 1);
	}

	// WE SUPPOSE THAT THE EDGE IS IN THE GRAPH
	@Override
	public void removeEdge(int start, int end) {
		
		if (start >= getNoOfVertices() || end >= getNoOfVertices()) {
			throw new VertexNotInGraphException();
		}
		
		if (!getAdjacencyList().get(start).contains(end) || 
				!getAdjacencyList().get(end).contains(start)) {
			throw new EdgeNotInGraphException();
		}
		
		int i = getAdjacencyList().get(start).indexOf(end);
		int j = getAdjacencyList().get(end).indexOf(start);

		ArrayList<LinkedList<Integer>> newList = 
				new ArrayList<LinkedList<Integer>>(getNoOfVertices());
		
		for (int k = 0; k < getNoOfVertices(); k++) {
			if (k != start && k != end) {
				LinkedList<Integer> list = new LinkedList<Integer>();
				list = getAdjacencyList().get(k);
				newList.add(list);				
			} else if (k == start) {
				LinkedList<Integer> listStart = new LinkedList<Integer>();
				listStart = getAdjacencyList().get(k);
				listStart.remove(i);
				newList.add(listStart);
			} else if (k == end) {
				LinkedList<Integer> listEnd = new LinkedList<Integer>();
				listEnd = getAdjacencyList().get(k);
				listEnd.remove(j);
				newList.add(listEnd);				
			}
		}
		
		setAdjacencyList(newList);		
	}

	@Override
	public boolean isEulerian() {
		
		for (int i = 0; i < getAdjacencyList().size(); i++) {
			boolean evenDegree = true;
			for (Integer j : getAdjacencyList().get(i)) {
				if (j != i) {
					evenDegree = !evenDegree;
				}
			}
			if (!evenDegree) {
				return false;
			}
		}

		return true;
	}

}
