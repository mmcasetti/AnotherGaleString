package interfaces;

import com.google.common.collect.Multiset;

import graphimplementations.Edge;

public interface Graph {
	
	public int getNoOfVertices();
	public Multiset<Edge> getEdges();
	public int[][] getMatrix();

	public Multiset<Edge> getEdgesAt(int vertex);
	
	public void addVertices(int noOfNewVertices);
	public void addEdge(int start, int end);
	public void removeVertex(int v);
	public void removeEdge(int start, int end);
	
	public boolean isEulerian();

}
