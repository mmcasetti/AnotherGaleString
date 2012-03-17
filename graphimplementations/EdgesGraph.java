package graphimplementations;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Optional;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;

import exceptions.EdgeNotInGraphException;
import exceptions.GraphNotEulerianException;
import exceptions.VertexNotInGraphException;

import abstractclasses.AbstractGraph;

public class EdgesGraph extends AbstractGraph {
	
	private int noOfVertices;
	private Multiset<Edge> edges;
	
	public EdgesGraph(int v, Multiset<Edge> e) {
		for (Edge edge : e) {
			if (edge.getStart() >= v || edge.getEnd() >= v) {
				throw new VertexNotInGraphException();
			}
		}
		
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
			if (!edge.isLoop()) {
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
			throw new VertexNotInGraphException();
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
			throw new VertexNotInGraphException();
		}
		
		Edge edge = new Edge(start, end);
		edges.add(edge);
	}

	@Override
	public void removeVertex(int v) {
		
		if (v >= getNoOfVertices()) {
			throw new VertexNotInGraphException();
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
			throw new VertexNotInGraphException();
		}		
		Edge edge = new Edge(start, end);
		if (!getEdges().contains(edge)){
			throw new EdgeNotInGraphException();			
		}
		
		Multiset<Edge> newEdges = HashMultiset.create(getEdges());
		
		newEdges.remove(edge);
	
		setEdges(newEdges);
	}
	
	
	@Override
	public boolean isEulerian() {
		for (int i = 0; i < getNoOfVertices(); i++){
			boolean evenDegree = true;
			for (Edge edge : getEdgesAt(i)){
				if (!edge.isLoop()){
					evenDegree = !evenDegree;
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
			throw new EdgeNotInGraphException();
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
		
	public Optional<List<Integer>> getEulerianCycle(EdgesGraph graph, int startNode) {
		if (!graph.isEulerian()) {
			throw new GraphNotEulerianException();
		}
		
		Optional<List<Integer>> tour = graph.getCycle(graph, startNode);
		EdgesGraph copyOfGraph = graph.copy();
				
		for (Integer i = 0; i < tour.get().size() - 1; i++) {
			copyOfGraph.removeEdge(tour.get().get(i), tour.get().get(i + 1));
		}
		copyOfGraph.removeEdge(tour.get().get(tour.get().size() - 1), tour.get().get(0));
		
		while (copyOfGraph.getEdges().size() != 0) {
			int junction = -1;
			for (Integer i : tour.get()) {
				if (copyOfGraph.getEdgesAt(i).size() != 0) {
					junction = i;
					break;
				}
			}
			
			Optional<List<Integer>> tour2 = copyOfGraph.getCycle(copyOfGraph, junction);
			tour = graph.mergeTours(tour, tour2, junction);

			for (int i = 0; i < tour2.get().size() - 1; i++) {
				copyOfGraph.removeEdge(tour2.get().get(i), tour2.get().get(i + 1));
			}
			copyOfGraph.removeEdge(tour2.get().get(tour2.get().size() - 1), tour2.get().get(0));
		}
		return tour;
	}


	public Optional<List<Integer>> getCycle(EdgesGraph edgesGraph, int startNode) {
		List<Integer> cycle = Lists.newArrayList();
		EdgesGraph copyOfGraph = edgesGraph.copy();
		
		int currentNode = startNode;
		cycle.add(currentNode);

		if (edgesGraph.getDegreeAt(currentNode) == 1
				&& !edgesGraph.getEdgesAt(currentNode).iterator().next().isLoop()) {
			return Optional.<List<Integer>>absent();
		}
		
		int nextNode = (copyOfGraph.getEdgesAt(currentNode).iterator().next().getStart() ==
				currentNode) ? 
					copyOfGraph.getEdgesAt(currentNode).iterator().next().getEnd() :
						copyOfGraph.getEdgesAt(currentNode).iterator().next().getStart();
		
		copyOfGraph.removeEdge(currentNode, nextNode);
		
		while (nextNode != startNode) {
			cycle.add(nextNode);
			currentNode = nextNode;
			if (edgesGraph.getDegreeAt(currentNode) <= 1
					&& !edgesGraph.getEdgesAt(currentNode).iterator().next().isLoop()) {
				return Optional.<List<Integer>>absent();
			}
			nextNode = (copyOfGraph.getEdgesAt(currentNode).iterator().next().getStart() ==
				currentNode) ? 
					copyOfGraph.getEdgesAt(currentNode).iterator().next().getEnd() :
						copyOfGraph.getEdgesAt(currentNode).iterator().next().getStart();
					
			copyOfGraph.removeEdge(currentNode, nextNode);
		}
		
		Optional<List<Integer>> c = Optional.of(cycle);
		return c;
	}
	
	public int getDegreeAt(int vertex) {
		return getEdgesAt(vertex).size();
	}
		
	public EdgesGraph copy() {
		return new EdgesGraph(getNoOfVertices(), getEdges());
	}

	public Optional<List<Integer>> mergeTours(Optional<List<Integer>> tour1, 
			Optional<List<Integer>> tour2, int junction) {
		
		if (!tour1.isPresent()) {
			return tour2;
		} else if (!tour2.isPresent()) {
			return tour1;
		}
		
		List<Integer> tour3 = Lists.newArrayList();
		
		int indexOfJunction = tour1.get().indexOf(junction);
		
		int k = 0;	
		while (k < indexOfJunction) {
			tour3.add(tour1.get().get(k));
			k++;
		}
		int i = 0;
		while (i < tour2.get().size()) {
			tour3.add(tour2.get().get(i));
			i++;
		}
		while (k < tour1.get().size()) {
			tour3.add(tour1.get().get(k));
			k++;
		}
		
		return Optional.of(tour3);
	}
	
	// a method to find a Maximal Matching  (Edmonds' algorithm)
	public Multiset<Edge> getMaximalMatching(Multiset<Edge> matching) {
		
		if (getAugmentingPath(matching).isPresent()) {
			return getMaximalMatching(augment(matching, getAugmentingPath(matching)));
		}
		
		return matching;
	}
	
	// we suppose that P is an augmenting path for the matching matching
	public Multiset<Edge> augment(Multiset<Edge> matching, 
			Optional<ArrayList<Integer>> augmentingPath) {
		if (!augmentingPath.isPresent()) {
			return matching;
		}
		
		Multiset<Edge> N = HashMultiset.create(matching);
		
		for (int i = 0; i < augmentingPath.get().size() - 2; i+=2) {
			Edge in = new Edge(augmentingPath.get().get(i), 
					augmentingPath.get().get(i + 1));
			matching.add(in);
			Edge out = new Edge(augmentingPath.get().get(i + 1), 
					augmentingPath.get().get(i + 2));
			matching.remove(out);				
		}
		Edge in = new Edge(augmentingPath.get().get(augmentingPath.get().size() - 2), 
					augmentingPath.get().get(augmentingPath.get().size() - 1)); 
		matching.add(in);
		
		return N;
	}
	
	// we suppose that matching is a matching (TODO)
	public Optional<ArrayList<Integer>> getAugmentingPath(Multiset<Edge> matching) {
		
		
		return Optional.absent();
	}
	
	// distance of two vertices (Dijkstra's algorithm) (TODO)
	public int getDistance(int source, int target) {
		
		
		return 0;
	}
	
}
