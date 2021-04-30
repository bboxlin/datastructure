/*
 *  Assignment: Implementing Quick, Merge, and Radix sort
 *
 *  [this file]: MergeSort Implementation
 * 
 *  Copyright:
 *   For academic use only under the Creative Commons
 *   Attribution-NonCommercial-NoDerivatives 4.0 International License
 *   http://creativecommons.org/licenses/by-nc-nd/4.0
 */

import java.util.ArrayList;


public class MergeSort<T extends Comparable<T>> extends Sorter<T> {
    MergeSort() {
        name = "MergeSort";
    }

  	public ArrayList<T> sort(SortStats stats, ArrayList<T> data) {

        // MA todo: implement merge sort here
        // Also implement it in the stats object:
        //  Every data comparison increment stats.comparisons
        //  Every data swap increment stats.swaps
        //  See Insertion Sort for an example of this in operation
  		
  		if(data.size()>1) {
 
  			int mid = data.size()/2; 	  	
  			
  			//using the subList API to keep divide the list into left sub-list and right sub-list; 			
  	  		ArrayList<T> left = new ArrayList<T>(data.subList(0, mid));
  	  		ArrayList<T> right = new ArrayList<T>(data.subList(mid, data.size()));
  	  		
  	  		//recursively divide   
  			left = sort(stats, left);
  			right = sort(stats, right);
  			
  			//merge sublist layer by layer
  			merge(stats, data, left, right);			 
  		}
  		return data;  		 
	}
  	
	/*
		 * Procedures of layer by layer merge.
		 * 
		 * Mechanism: 
		 * 
		 * left-Sublist right-Sublist  (T-existing elements, F-empty]
		 *       T            T            ---> Initial check
		 *       T            F            ---> Secondary check
		 *       F            T            ---> Secondary check
		 *       F            F            ---> Done with the current layer of combine, keep backtracking.
		 *  
		 *  
		 *           Initial Check: When both left and right sublist have elements.
		 *                    - if left's first element <= right's first element, set data list i position to left's first element;
		 *                            - i++, to track the data list position.
		 *                            - then remove the head of the left list and keep looping through.
		 *                    - else, set data list i position to right's first element;
		 *                            - i++, to track the data list position.
		 *                            - then remove the head of the right list and keep looping through.
		 *                    
		 *           
		 *           Secondary Check: Triggered when one of the sublist is empty.
		 *                    - if right sublist is empty, we insert all of the left sublist to data's i position by looping through. 
		 *                    		   - add left's first element to the data list, then remove left' first element.
		 *                    - if left sublist is empty, we insert all of the right sublist to data's i position by looping through.
		 *                             - add right's first element to the data list, then remove right' first element.
		 * 
		 */
  	
  	private void merge(SortStats stats, ArrayList<T> data, ArrayList<T> left, ArrayList<T> right){
  		
  			int i = 0;//index of the "data" List.
			
			//Initial Check -------------------
			while(left.size()>0 && right.size()>0) { 
				stats.comparisons++;
				if(left.get(0).compareTo(right.get(0))<=0) {     
					data.set(i++, left.get(0));                                
					left.remove(0);
				}
				else {                                                       
					data.set(i++,right.get(0));                              
					right.remove(0);
				}
			}
			
			//Secondary Check -----------------
			while(left.size()>0 && !(right.size()>0)) {             
				data.set(i++, left.get(0));
				left.remove(0);
			}
			
			while(right.size()>0 && !(left.size()>0)) {
				data.set(i++,right.get(0)); 
				right.remove(0);
			}
	}
  	
  	 
}