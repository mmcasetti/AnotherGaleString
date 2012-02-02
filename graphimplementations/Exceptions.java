package graphimplementations;

public class Exceptions {
	public static class VertexNotInGraphException extends RuntimeException {
		public VertexNotInGraphException() {
			super("Vertex not in graph! (v >= noOfVertices)");
		}
	}	

	public static class EdgeNotInGraphException extends RuntimeException {
		public EdgeNotInGraphException() {
			super("Edge not in graph!");
		}
	}	

}
