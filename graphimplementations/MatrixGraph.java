package graphimplementations;


import abstractclasses.AbstractGraph;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class MatrixGraph extends AbstractGraph {
	private int[][] adjacencyMatrix;

	public MatrixGraph(int[][] matrix) {
		this.adjacencyMatrix = matrix;
	}
	
	@Override
	public int getNoOfVertices() {
		return getMatrix().length;
	}

	@Override
	public Multiset<Edge> getEdges() {
		Multiset<Edge> edges = HashMultiset.create();

		for (int i = 0; i < getNoOfVertices(); i++) {
			for (int j = 0; j <= i; j++) { // we consider only the lower half of the matrix to avoid duplicate edges
				Edge e = new Edge(i, j);
				edges.add(e, getMatrix()[i][j]);
			}
		}
		
		return edges;
	}

	@Override
	public int[][] getMatrix() {
		return adjacencyMatrix;
	}

	public void setMatrix(int[][] matrix) {
		adjacencyMatrix = matrix;
	}
	
	@Override
	public Multiset<Edge> getEdgesAt(int vertex) {

		if (vertex >= getNoOfVertices()) {
			throw new Exceptions.VertexNotInGraphException();
		}
		
		Multiset<Edge> edgesAt = HashMultiset.create();

		for (int i = 0; i < getNoOfVertices(); i++){
			Edge e = new Edge(i, vertex);
			edgesAt.add(e, getMatrix()[vertex][i]);
		}
		return edgesAt;		
	}
	
	
	@Override
	public void addVertices(int noOfNewVertices) {
		int oldNoOfVertices = getNoOfVertices();
		int[][] newMatrix = new int[getNoOfVertices() + noOfNewVertices][getNoOfVertices() + noOfNewVertices];
		for (int i = 0; i < oldNoOfVertices; i++) {
			for (int j = 0; j < oldNoOfVertices; j++) {
				newMatrix[i][j] = getMatrix()[i][j];
			}		
		}
		setMatrix(newMatrix);
	}

	@Override
	public void addEdge(int start, int end) {
		if (start >= getNoOfVertices() || end >= getNoOfVertices()) {
			throw new Exceptions.VertexNotInGraphException();
		}

		int[][] newMatrix = new int[getNoOfVertices()][getNoOfVertices()];
		
		for (int i = 0; i < getNoOfVertices(); i++) {
			for (int j = 0; j < getNoOfVertices(); j++) {
				newMatrix[i][j] = getMatrix()[i][j];
			}
		}
		
		newMatrix[start][end]++;
		newMatrix[end][start]++;
		
		setMatrix(newMatrix);
	}

	@Override
	public void removeVertex(int v) {
		if (v >= getNoOfVertices()) {
			throw new Exceptions.VertexNotInGraphException();
		}
		
		int[][] newMatrix = new int[getNoOfVertices() - 1][getNoOfVertices() - 1];
		
		for (int i = 0; i < v; i++){
			for(int j = 0; j < v; j++){
				newMatrix[i][j] = getMatrix()[i][j];
			}
		}
		for (int i = v; i < getNoOfVertices() - 1; i++) {
			for(int j = v; j < getNoOfVertices() - 1; j++) {
				newMatrix[i][j] = getMatrix()[i + 1][j + 1];
			}			
		}
		
		setMatrix(newMatrix);
	}
		
	@Override
	public void removeEdge(int start, int end) {
		
		if (start >= getNoOfVertices() || end >= getNoOfVertices()) {
			throw new Exceptions.VertexNotInGraphException();
		}
		
		if (getMatrix()[start][end] == 0 || getMatrix()[end][start] == 0) {
			throw new Exceptions.EdgeNotInGraphException();
		}
		
		int[][] newMatrix = new int[getNoOfVertices()][getNoOfVertices()];
		
		for (int i = 0; i < getNoOfVertices(); i++) {
			for (int j = 0; j < getNoOfVertices(); j++) {
				newMatrix[i][j] = getMatrix()[i][j];
			}
		}
		
		newMatrix[start][end]--;
		newMatrix[end][start]--;
		
		setMatrix(newMatrix);
	}
	
	@Override
	public boolean isEulerian() {		
		for (int i = 0; i < getNoOfVertices(); i++){
			// each vertex covered exactly once
			int rowSum = 0;
			for (int j = 0; j < getNoOfVertices(); j++){
				if (j != i) {
					rowSum = rowSum + getMatrix()[i][j];
				}
			}
			if (rowSum%2 == 1) {
				return false;
			}
		}
		return true;
	}

	public boolean isPerfectMatching(int[][] subset) {
		
		for (int i = 0; i < getNoOfVertices(); i++) {
			for (int j = 0; j < getNoOfVertices(); j++) {
				if (subset[i][j] >= 1 && getMatrix()[i][j] == 0) {
					throw new Exceptions.EdgeNotInGraphException();
				}				
			}
		}
		
		for (int i = 0; i < getNoOfVertices(); i++){
			// no loops
			if (subset[i][i] > 0){
				return false;
			}
			// each vertex covered exactly once
			else {
				int rowSum = 0;
				for (int j = 0; j < getNoOfVertices(); j++){
					rowSum = rowSum + subset[i][j];
				}
				if (rowSum != 1) {
					return false;
				}
			}
		}
		return true;
	}

}
