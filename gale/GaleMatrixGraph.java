package gale;

import graphimplementations.MatrixGraph;

public class GaleMatrixGraph {

	private GaleString gale;	
	private MatrixGraph graph;
	
	public GaleMatrixGraph(GaleString g) {
		this.gale = g;
				
		int[][] incidenceMatrix = new int[g.getMaxLabel()][g.getMaxLabel()];
		
		for (int i = 0; i < g.getLabels().length - 1; i++) {
			// no loops
			if (g.getLabels()[i] != g.getLabels()[i + 1]){
				incidenceMatrix[g.getLabels()[i] - 1][g.getLabels()[i + 1] - 1]++;
				incidenceMatrix[g.getLabels()[i + 1] - 1][g.getLabels()[i] - 1]++;				
			}
		}
		if (g.getLabels()[g.getLabels().length - 1] != g.getLabels()[1]){
			incidenceMatrix[g.getLabels()[g.getLabels().length - 1] - 1][g.getLabels()[1] - 1]++;
			incidenceMatrix[g.getLabels()[1] - 1][g.getLabels()[g.getLabels().length - 1] - 1]++;
		}
		
		this.graph = new MatrixGraph(incidenceMatrix);
	}

	// TODO: constructor from Eulerian graph: follow an Eulerian loop & that is the Gale string
	
	public GaleString getGaleString() {
		return this.gale;
	}

	public MatrixGraph getGraph() {
		return this.graph;
	}
	
//	// TODO: method translating Perfect Matching of GaleGraph to a bitstring of
//	// a Completely Labeled Gale Evenness String	
//	
//	public int[] bitstringFromPerfectMatching (Multiset<Integer[]> pm){
//		
//		int[] string = new int[this.getGaleString().getLabels().length];
//		
//		
//		
//		return string;
//	}
	
	
	// method translating a Completely Labeled Gale Evenness String in 
	// a PM of its GaleGraph as incidence matrix
	// WE SUPPOSE THAT GALE STRING IS CLGES & that MaxLabel even
	public int[][] perfectMatchingFromGale(){
		
		int[][] matching = new int[getGaleString().getMaxLabel()][getGaleString().getMaxLabel()];
		
		// we separate the case in which we have an edge "across" LastLabel-FirstLabel
		
		boolean across = false;
		
		int k = 0;
		while (k < getGaleString().getLabels().length && getGaleString().getBitstring()[k] != 0) {
			across = !across;
			k++;
		}
		
		if (!across){
	 		for (int i = 0; i < getGaleString().getLabels().length; i+=2) {
				if(getGaleString().getBitstring()[i] == 1){
					matching[getGaleString().getLabels()[i] - 1][getGaleString().getLabels()[i + 1] - 1]++;
					matching[getGaleString().getLabels()[i + 1] - 1][getGaleString().getLabels()[i] - 1]++;
				}
			}
		} else {
	 		for (int i = 1; i < getGaleString().getLabels().length - 1; i+=2) {
				if(getGaleString().getBitstring()[i] == 1){
					matching[getGaleString().getLabels()[i] - 1][getGaleString().getLabels()[i + 1] - 1]++;
					matching[getGaleString().getLabels()[i + 1] - 1][getGaleString().getLabels()[i] - 1]++;
				}
			}
			matching[getGaleString().getLabels()[getGaleString().getLabels().length - 1] - 1][getGaleString().getLabels()[1] - 1]++;
			matching[getGaleString().getLabels()[1] - 1][getGaleString().getLabels()[getGaleString().getLabels().length - 1] - 1]++;
		}
				
		return matching;
	}

}