package gale;

import graphimplementations.Edge;
import graphimplementations.EdgesGraph;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class GaleEdgesGraph {

	private GaleString gale;	
	private EdgesGraph graph;
	
	public GaleEdgesGraph(GaleString g) {
		this.gale = g;
				
		Multiset<Edge> edges = HashMultiset.create();
		
		for (int i = 0; i < g.getLabels().length - 1; i++) {
			if (g.getLabels()[i] != g.getLabels()[i + 1]){
				Edge e = new Edge(g.getLabels()[i] - 1, g.getLabels()[i + 1] - 1);
				edges.add(e);
			}
		}
		if (g.getLabels()[g.getLabels().length - 1] != g.getLabels()[0]) {
			Edge e = new Edge(g.getLabels()[g.getLabels().length - 1] - 1, 
					g.getLabels()[0] - 1);
			edges.add(e);			
		}
		
		this.graph = new EdgesGraph(g.getMaxLabel(), edges);
	}

	// TODO: constructor from Eulerian graph: follow an Eulerian loop & that is the Gale string
	
	public GaleString getGaleString() {
		return this.gale;
	}

	public EdgesGraph getGraph() {
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
	public Multiset<Edge> perfectMatchingFromGale(){
		
		Multiset<Edge> matching = HashMultiset.create();
		
		// we separate the case in which we have an edge "across" LastLabel-FirstLabel
		
		boolean across = false;
		
		int k = 0;
		while (k < getGaleString().getLabels().length && getGaleString().getBitstring()[k] == 1) {
			across = !across;
			k++;
		}
		
		if (!across){
	 		for (int i = 0; i < getGaleString().getLabels().length; i+=2) {
				if(getGaleString().getBitstring()[i] == 1){
					Edge e = new Edge(getGaleString().getLabels()[i] - 1, getGaleString().getLabels()[i + 1] - 1);
					matching.add(e);
				}
			}
		} else {
	 		for (int i = 1; i < getGaleString().getLabels().length - 1; i+=2) {
				if(getGaleString().getBitstring()[i] == 1){
					Edge e = new Edge(getGaleString().getLabels()[i] - 1, getGaleString().getLabels()[i + 1] - 1);
					matching.add(e);
				}
			}
	 		Edge e = new Edge(getGaleString().getLabels()[getGaleString().getLabels().length - 1] - 1, getGaleString().getLabels()[1] - 1);
			matching.add(e);
		}
				
		return matching;
	}
	
	
}
