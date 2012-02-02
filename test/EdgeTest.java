package test;

import static org.junit.Assert.*;

import org.junit.Test;

import graphimplementations.Edge;

public class EdgeTest {

	@Test
	public void testEqual_true() {
		Edge e = new Edge(1,2);
		Edge f = new Edge(2,1);
		
		assertTrue(e.equals(f));
	}
	
	@Test
	public void testEqual_false() {
		Edge e = new Edge(1,2);
		Edge f = new Edge(2,2);
		
		assertFalse(e.equals(f));
	}
	
	@Test
	public void testIsBoolean_true() {
		Edge e = new Edge(2,2);
		
		assertTrue(e.isLoop());
	}


	
}
