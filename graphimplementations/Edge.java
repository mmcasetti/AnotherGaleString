package graphimplementations;

public class Edge {

	private int start;
	private int end;
	
	public Edge(int i, int j) {
		this.start = i;
		this.end = j;
	}
	
	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

	public void setStart(int s) {
		start = s;
	}

	public void setEnd(int e) {
		end = e;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (start + end);
		result = prime * result + (start + end);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Edge)) {
			return false;
		}
		Edge otherEdge = (Edge) obj;
		if ((otherEdge.start == this.start && otherEdge.end == this.end) || (otherEdge.start == this.end && otherEdge.end == this.start)) {
			return true;
		} else {
			return false;
		}
	}
	
	
		
	public boolean isLoop() {
		return (this.start == this.end);
	}
	
}