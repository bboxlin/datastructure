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

  	public ArrayList<T> sort(SortStats stats, ArrayList<T> data) {

        // MA todo: implement quick sort here
        // Also implement it in the stats object:
        //  Every data comparison increment stats.comparisons
        //  Every data swap increment stats.swaps
        //  See Insertion Sort for an example of this in operation
 
  		//quickSort(stats, data, 0, data.size()-1);
  		
  		if(data.size()>1) {
	  		T pivot = data.get(0);
	  		ArrayList<T> smallSub = new ArrayList<>();
	  		ArrayList<T> largeSub = new ArrayList<>();
	  		
	  		for(int i = 1; i<data.size();i++) {
	  			stats.comparisons++;
	  			if(data.get(i).compareTo(pivot)<=0) {
	  				smallSub.add(data.get(i));
	  			}else {
	  				largeSub.add(data.get(i));
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
  	
		//return data;
	}
  	
  	
  	private void quickSort(SortStats stats, ArrayList<T> data, int l, int r) {
  		
  		if(l<r) {
  			
  			int pivotIndex = partition(stats, data, l,r);
  			
  	  	  	//recursively do the partition to the left partition, until l = r 
  	  	  	quickSort(stats, data, l, pivotIndex-1);
  	  	  			
  	  	  	//recursively do the partition to the to the right partition until l = r
  	  	  	quickSort(stats, data, pivotIndex+1, r);
  		}

  		 
  	  				
  	}
  	
  

	/*
  	 * <<Helper Method>>
  	 * Partition method purpose, this method will be recursively called by quickSort: 
  	 * 		arrange all elements left to the pivotIndex to be smaller than pivot, all elements right to the pivotIndex to be larger than pivot;
  	 * 
  	 * Pivot = first element of the data List passing in.
  	 * Pointers = left and right.
  	 * 
  	 * 1. Traversing pointers right to the left and left to the right.
  	 * 
  	 * 2. Swap left's element & right's elements if, at data[r] < pivot, or data[l] > pivot:
  	 *
  	 * 3. Eventually l=r, where l = r = pivot new index.
  	 * 
  	 * 4. return pivotIndex for the use of partitioning.
  	 */
  	private int partition(SortStats stats, ArrayList<T> data, int l, int r) {
  	 
  		 
  		while(l<r) {
  			T pivot = data.get(l);
  			
  			while(l<r && data.get(r).compareTo(pivot)>=0) {
  				stats.comparisons++;
  				r--; //keep traversing to the left.
  			}
  			stats.comparisons++; //one more comparisons when not entering the while loop
  		
  			if(l<r) {
  				stats.swaps++;
  				swap(data, l, r); //swap the left's & right's elements
  				l++; //left index ++ since element smaller than pivot has been swap to the left.
  			}
  			
  			while(l<r && data.get(l).compareTo(pivot)<=0) {
  				stats.comparisons++;
  				l++;  //keep traversing to the right.
  			}
  			stats.comparisons++; //one more comparisons when not entering the while loop
  			
  			if(l<r) {
  				stats.swaps++;
  				swap(data, l, r);
  				r--; //right index -- since element larger than pivot has been swap to the right.
  			}
  		}
  		return l; //where l = r, either l or r is the pivotIndex, return for the partition use.
  	}

  	//swap helper method
  	private void swap (ArrayList<T> data, int l, int r) {
  		T temp = data.get(l);
  		data.set(l, data.get(r));
  		data.set(r, temp);
  	}
  	
}