package test;

/**
 *  Some tests on the Gale String class.
 *  
 *  @author mmcasetti
 */
import static org.junit.Assert.*;

import org.junit.Test;

import gale.GaleString;
import gale.GaleString.Status;

public class GaleStringTest {

	public int[] labels = { 1, 2, 3, 4, 2, 4 };
	
	@Test
	public void checkLabeled_completelyLabeled() {
		int[] bitstring = { 1, 1, 1, 1, 0, 0 };
		GaleString gale = new GaleString(labels, bitstring);
		assertTrue(gale.checkLabeled());
	}

	@Test
	public void checkLabeled_tooManyLabels() {
		int[] bitstring = { 1, 1, 1, 1, 0, 1 };
		GaleString gale = new GaleString(labels, bitstring);
		assertFalse(gale.checkLabeled());
	}
	
	@Test
	public void checkLabeled_tooFewLabels() {
		int[] bitstring = { 1, 1, 0, 1, 0, 0 };
		GaleString gale = new GaleString(labels, bitstring);
		assertFalse(gale.checkLabeled());
	}

	@Test
	public void checkLabeled_repeatedLabels() {
		int[] bitstring = { 1, 1, 0, 1, 0, 1 };
		GaleString gale = new GaleString(labels, bitstring);
		assertFalse(gale.checkLabeled());
	}
	
	@Test
	public void checkEvenness_true() {
		int[] bitstring = { 1, 1, 0, 1, 1, 0 };
		GaleString gale = new GaleString(labels, bitstring);
		assertTrue(gale.checkEvenness());
	}

	@Test
	public void checkEvenness_false() {
		int[] bistring = { 1, 1, 1, 0, 1, 0 };
		GaleString gale = new GaleString(labels, bistring);
		assertFalse(gale.checkEvenness());
	}

	@Test
	public void getStatus_completelyLabeledEven() {
		int[] bitstring = { 1, 1, 1, 1, 0, 0 };
		GaleString gale = new GaleString(labels, bitstring);
		assertTrue(gale.getStatus() == Status.COMPLETELY_LABELED_AND_EVEN);
	}

	@Test
	public void getStatus_CompletelyLabeled() {
		int[] bitstring = { 1, 0, 1, 1, 1, 0 };
		GaleString gale = new GaleString(labels, bitstring);
		assertTrue(gale.getStatus() == Status.COMPLETELY_LABELED);
	}
	
	@Test
	public void getStatus_Even() {
		int[] bitstring = { 1, 0, 0, 1, 1, 1 };
		GaleString gale = new GaleString(labels, bitstring);
		assertTrue(gale.getStatus() == Status.EVEN);
	}

	@Test
	public void getStatus_Invalid() {
		int[] bitstring = { 1, 0, 0, 1, 0, 1 };
		GaleString gale = new GaleString(labels, bitstring);
		assertTrue(gale.getStatus() == Status.INVALID);
	}
	
	@Test
	public void testGetMaxLabel() {
		int[] bitstring = { 1, 1, 1, 1, 0, 0 };
		GaleString gale = new GaleString(labels, bitstring);
		assertTrue(gale.getMaxLabel() == 4);
	}
}
