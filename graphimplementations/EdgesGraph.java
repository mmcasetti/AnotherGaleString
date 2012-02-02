package graphimplementations;


import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import abstractclasses.AbstractGraph;

public class EdgesGraph extends AbstractGraph {
	
	private int noOfVertices;
	private Multiset<Edge> edges;
	
	public EdgesGraph(int v, Multiset<Edge> e) {
		this.noOfVertices = v;
		this.edges = e;
	}
	
	@Override
	public int getNoOfVertices() {
		return noOfVertices;
	}

	@Override
	public Multiset<Edge> getEdges() {
		return edges;
	}
	
	@Override
	public int[][] getMatrix() {
		int[][] incidenceMatrix = new int[getNoOfVertices()][getNoOfVertices()];
		
		for (Edge edge : getEdges()){
			if (edge.getStart() != edge.getEnd()) {
				incidenceMatrix[edge.getStart()][edge.getEnd()]++;
				incidenceMatrix[edge.getEnd()][edge.getStart()]++;								
			} else {
				incidenceMatrix[edge.getStart()][edge.getEnd()]++;
			}
		}		
		
		return incidenceMatrix;
	}	
	
	public void setNoOfVertices(int v) {
		noOfVertices = v;
	}

	public void setEdges(Multiset<Edge> e) {
		edges = e;
	}
	
	@Override
	public Multiset<Edge> getEdgesAt(int vertex) {
		
		if (vertex >= getNoOfVertices()) {
			throw new Exceptions.VertexNotInGraphException();
		}
		
		Multiset<Edge> edgesAt = HashMultiset.create();
		
		for (Edge e : getEdges()) {
			if (e.getStart() == vertex || e.getEnd() == vertex) {
				edgesAt.add(e);
			}
		}
		
		return edgesAt;
	}

	@Override
	public void addVertices(int noOfNewVertices) {
		noOfVertices = noOfVertices + noOfNewVertices;
	}

	@Override
	public void addEdge(int start, int end) {
		if (start >= getNoOfVertices() || end >= getNoOfVertices()) {
			throw new Exceptions.VertexNotInGraphException();
		}
		
		Edge edge = new Edge(start, end);
		edges.add(edge);
	}

	@Override
	public void removeVertex(int v) {
		
		if (v >= getNoOfVertices()) {
			throw new Exceptions.VertexNotInGraphException();
		}
		
		Multiset<Edge> newEdges = HashMultiset.create();
		
		for (Edge e : getEdges()){
			if (e.getStart() < v && e.getEnd() < v){
				newEdges.add(e);
			} else if (e.getStart() > v && e.getEnd() > v) {
				Edge f = new Edge(e.getStart() - 1, e.getEnd() - 1);
				newEdges.add(f);
			} else if (e.getStart() > v && e.getEnd() < v) {
				Edge f = new Edge(e.getStart() - 1, e.getEnd());
				newEdges.add(f);				
			} else if (e.getStart() < v && e.getEnd() > v) {
				Edge f = new Edge(e.getStart(), e.getEnd() - 1);
				newEdges.add(f);
			}
		}

		setEdges(newEdges);
		
		setNoOfVertices(noOfVertices - 1);
	}

	@Override
	public void removeEdge(int start, int end) {

		if (start >= getNoOfVertices() || end >= getNoOfVertices()) {
			throw new Exceptions.VertexNotInGraphException();
		}		
		
		Edge edge = new Edge(start, end);
		if (!getEdges().remove(edge)){
			throw new Exceptions.EdgeNotInGraphException();			
		}
		getEdges().remove(edge);
	}
	
	
	@Override
	public boolean isEulerian() {
		for (int i = 0; i < noOfVertices; i++){
			boolean evenDegree = true;
			for (Edge edge : edges){
				if (!edge.isLoop()){
					if (edge.getStart() == i || edge.getEnd() == i){
						evenDegree = !evenDegree;
					}					
				} 
			}
			if (evenDegree == false){
				return false;
			}
		}
		return true;
	}

	public boolean isPerfectMatching(Multiset<Edge> subset) {
		
		if (!getEdges().containsAll(subset)) {
			throw new Exceptions.EdgeNotInGraphException();
		}
		
		// if there is an odd no. of vertices, no perfect matching
		if (noOfVertices%2 == 1){
			return false;
		}
		// else check that there are exactly noOfVertices/2 edges
		else if (subset.size() != noOfVertices/2){
			return false;
		}
		// else check Perfect Matching
		else {
			// check that each vertex is covered (this will imply that it is covered
			// once, since the n. of edges in subset is noOfVertices/2)
			boolean[] isCovered = new boolean[noOfVertices];
			
			for (int i = 0; i < noOfVertices; i++){
				isCovered[i] = false;
				for (Edge edge : subset) {
					// we use a xor to exclude case of loops
					if (edge.getStart() == i ^ edge.getEnd() == i) {
						if (isCovered[i]) {
							return false;
						}
						isCovered[i] = true;
					}
				}
		
				if (!isCovered[i]) {
					return false;
				}
			}						
			return true;
		}
	}
	
}
