package gale;
/*
 * A Gale String is identified by a string of labels in {1,...,n} = [n] and
 * a bitstring of the same length. The Gale String satisfies two properties:
 * it is *completely labeled*, that is to each label in [n] correspond exactly
 * one 1 in the bitstring; furthermore it satisfies the *Gale evennes condition*:
 * the maximal substrings of 1s in the bitstring are of even length, except
 * for the first substring of 1s in the bitstrings starting with 1 and for the
 * last substring of 1s in the bitstrings ending in 1. (For an application, see
 * Casetti, Merschen, von Stengel, "Finding Gale Strings", ENDM (Electronic Notes 
 * in Discrete Mathematics, August 2010)
 */

import java.util.HashSet;
import java.util.Set;

public class GaleString {

    private int[] labels;
    private int[] bitstring;

    public GaleString (int[] a, int[] b) {
        this.labels = a;
        this.bitstring = b;
    }
    
    public void setLabels (int[] a) {
        this.labels = a;
    }

    public void setBitstring (int[] b) {
        this.bitstring = b;
    }

	public int[] getLabels() {
		return this.labels;
	}	
	public int[] getBitstring() {
		return this.bitstring;
	}
	
	
	public int getMaxLabel(){
        int maxLabel = 0;
        
        for(int i = 0; i < this.getLabels().length; i++){
        	if (this.getLabels()[i] > maxLabel){
        		maxLabel = this.getLabels()[i];
        	}
        }
        
        return maxLabel;
	}
	
	//a method to check whether the Gale string is completely labeled (to all labels correspond a 1, no label with 1 twice)
	//we suppose that the labels are a set [n], without gaps, but not necessarily that the first n are 1 2 3 ... n.
    public boolean checkLabeled() {
        
        //all the labels with "one", without repetitions    
        Set<Integer> labelsWithOne = new HashSet<Integer>();
        //the highest label, that is the number of labels
        
        for(int i = 0; i < this.getLabels().length; i++){
            if (this.getBitstring()[i] == 1){
                //the label is repeated, return false
                if (labelsWithOne.contains(this.getLabels()[i])){
                    return false;
                }
                //otherwise, add it to the set of labels with one
                else {
                    labelsWithOne.add(this.getLabels()[i]);
                }
            }
        }
     
        //check if there less 1s than there should be
        if (labelsWithOne.size() != this.getMaxLabel()){
            return false;
        }
        
        return true;
    }

	//a method to check if the Gale string satisfies the Gale evenness condition
	public boolean checkEvenness (){

		boolean parity = true;
		//we must check all runs of 1s - except the last one and first one
		int i = 0;
		while (i < this.getBitstring().length && this.getBitstring()[i] == 1) {
			i++;
		}
		
		int j = this.getBitstring().length - 1;
		while (j >= 0 && this.getBitstring()[j] == 1) {
			j--;
		}
		//we start to check the string at i and stop at j
		while (i < j) {
			//in the runs of 1s we count as !parity every 1 - so couples 11 are true, single 1s are false
			if (this.getBitstring()[i] == 1) {
				parity = !parity;
				i++;
			}
			//when we arrive at a zero, if the parity is true we continue, else we get out with false
			else if (parity) {
				i++;
			}
			else {
				return false;
			}
		}
		return parity;		
	}

    public enum Status {

        COMPLETELY_LABELED_AND_EVEN("Completely Labeled Gale Evenness String"),
        COMPLETELY_LABELED("Completely Labeled but not Gale Evenness String"),
        EVEN("Not Completely Labeled but Gale Evenness String"),
        INVALID("Not Completely Labeled nor Gale Evenness String");

        private final String message;

        private Status (String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    public Status getStatus() {
        //we get the status of the Gale string
        
        boolean labeled = checkLabeled();
        boolean evenness = checkEvenness();
        
        if (labeled && evenness) {
            return Status.COMPLETELY_LABELED_AND_EVEN;
        }
        else if (!evenness && labeled) {
            return Status.COMPLETELY_LABELED;
        } 
        else if (!labeled && evenness) {
            return Status.EVEN;
        } 
        else {
            return Status.INVALID;
        }
    }
}
