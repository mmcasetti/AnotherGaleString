package abstractclasses;


import interfaces.Graph;


public abstract class AbstractGraph implements Graph {
	
	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if (!(other instanceof Graph)) {
			return false;
		}
		
		AbstractGraph otherGraph = (AbstractGraph) other;
		if (this.getNoOfVertices() != otherGraph.getNoOfVertices()){
			return false;
		}
		if (!this.getEdges().equals(otherGraph.getEdges())) {
			return false;
		}
		
		return true;
	}
		
}
