/*
 *  Assignment: Implementing Quick, Merge, and Radix sort
 *
 *  [this file]: QuickSort Implementation
 * 
 *  Copyright:
 *   For academic use only under the Creative Commons
 *   Attribution-NonCommercial-NoDerivatives 4.0 International License
 *   http://creativecommons.org/licenses/by-nc-nd/4.0
 */

import java.util.ArrayList; 
 

public class QuickSort<T extends Comparable<T>> extends Sorter<T> {
	
	
    QuickSort() {
        name = "QuickSort";
    }
    
    
    /*
     *  Through the reference of the stable quickSort
     *  https://www.geeksforgeeks.org/stable-quicksort/
     *  
     *  Steps: (Divide and Conquer Concept apply to pivot's left and right sub list.
     *  
     *  1: pick pivot from middle of list
     *  2. Add elements that smaller than pivot to the left-sub list
     *  3. Add elements that larger than pivot to the right-sub list
     *  4. Elements that maybe equivalent to the pivot, to stay at its original side.
     *  5. Recursively do steps 1-4 for each side.
     *  6. At final step(when backtracking completed): smallSub + pivot + rightSub is sorted.
     *  
     */

  	public ArrayList<T> sort(SortStats stats, ArrayList<T> data) {

        // MA todo: implement quick sort here
        // Also implement it in the stats object:
        //  Every data comparison increment stats.comparisons
        //  Every data swap increment stats.swaps
        //  See Insertion Sort for an example of this in operation
  		
  		if(data.size()>1) {
  			int mid = data.size()/2;
	  		T pivot = data.get(mid);
	  		ArrayList<T> smallSub = new ArrayList<>();
	  		ArrayList<T> largeSub = new ArrayList<>();
	  		
	  		for(int i = 0; i<data.size();i++) {
	  			if(i != mid) {
	  				stats.comparisons++;
	  				if(data.get(i).compareTo(pivot)<0) {
		  				smallSub.add(data.get(i));
		  			}else if (data.get(i).compareTo(pivot)>0) {
		  				largeSub.add(data.get(i));
		  			}else {
		  				if(i<mid) {
		  					smallSub.add(data.get(i));
		  				}else {
		  					largeSub.add(data.get(i));
		  				}
		  			}
	  			}
	  			 
	  		}
	  		if(smallSub.size()>1) {
	  			sort(stats, smallSub);
	  		}
	  		if(largeSub.size()>1) {
	  			sort(stats, largeSub);
	  		}
	  		data.removeAll(data);
	  		data.addAll(smallSub);
	  		data.add(pivot);
	  		data.addAll(largeSub);
  		}
		return data;	 
	}
}