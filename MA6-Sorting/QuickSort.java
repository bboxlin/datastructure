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
  	 
  	   ArrayList<T> newD= quickSort(stats, data);
  	   for(int i = 0;i<newD.size();i++) {
  		   data.set(i, newD.get(i));
  	   }
  	 return data;
  	
		 
	}
  	
  	
  	private ArrayList<T> quickSort(SortStats stats, ArrayList<T> data) {
  		if(data.size()<=1) {
  			return data;
  		}
  		ArrayList<T> smaller = new ArrayList<>();
  		ArrayList<T> larger = new ArrayList<>();
  		
  		int mid = data.size()/2;
  		T pivot = data.get(mid);
  		
  		for(int i = 0; i<data.size(); i++) {
  			T val = data.get(i);
  			if(i != mid) {
  				if(val.compareTo(pivot)<0) {
  					smaller.add(val);
  				}else if(val.compareTo(pivot)>0) {
  					larger.add(val);
  				}else {
  					
  					if(i<mid) {
  						smaller.add(val);
  					}else {
  						larger.add(val);
  					}
  					
  				}
  			}
  		}
  		ArrayList<T> ans = new ArrayList<T>();
  		ArrayList<T> sa1 = quickSort(stats,smaller);
  		ArrayList<T> sa2 = quickSort(stats, larger);
  		
      for(T val1 : sa1)
          ans.add(val1);
           
     // add pivat element into ans list   
     ans.add(pivot);
      
     // add all elements of greater list into ans list
     for(T val2 : sa2)
          ans.add(val2);
      
     // return ans list
     return ans;        
  		
  		
//  		if(l<r) {
//  			
//  			int pivotIndex = partition(stats, data,tempList, l,r);
//  			
//  	  	  	 //recursively do the partition to the left partition, until l = r 
//  	  	  	 quickSort(stats, data, tempList, l, pivotIndex-1);
//  	  	  	  			
//  	  	  	 //recursively do the partition to the to the right partition until l = r
//  	  	  	 quickSort(stats, data, tempList, pivotIndex+1, r);
//  		}

  	  				
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
  	private int partition(SortStats stats, ArrayList<T> data, ArrayList<T> tempList, int l, int r) {
  			int index;
  			T pivot = data.get(l);
  			tempList.clear();
  	  		 
  	  		for(int i = l+1; i<=r;i++) {
  	  			T item = data.get(i);
  	  			if(item.compareTo(pivot)<0) {
  	  				tempList.add(item);
  	  			}
  	  		}
  	  		index = l+tempList.size();
  	  		tempList.add(pivot);
  	  	
  	  		for(int i = l+1; i<=r; i++) {
  	  			T item = data.get(i);
  	  			if(item.compareTo(pivot)>=0) {
  	  				tempList.add(item);
  	  			}
  	  		}
  	  		 
  	  		for(int i = 0; i<tempList.size();i++) {
  	  			data.set(i, tempList.get(i));
  	  			
  	  		}
  	  		 
  	
  		return index;
  	 
  		 
//  		while(l<r) {
//  			 
//  			
//  			while(l<r && data.get(r).compareTo(pivot)>=0) {
//  				stats.comparisons++;
//  				r--; //keep traversing to the left.
//  			}
//  			stats.comparisons++; //one more comparisons when not entering the while loop
//  		
//  			if(l<r) {
//  				stats.swaps++;
//  				swap(data, l, r); //swap the left's & right's elements
//  				l++; //left index ++ since element smaller than pivot has been swap to the left.
//  			}
//  			
//  			while(l<r && data.get(l).compareTo(pivot)<=0) {
//  				stats.comparisons++;
//  				l++;  //keep traversing to the right.
//  			}
//  			stats.comparisons++; //one more comparisons when not entering the while loop
//  			
//  			if(l<r) {
//  				stats.swaps++;
//  				swap(data, l, r);
//  				r--; //right index -- since element larger than pivot has been swap to the right.
//  			}
//  		}
//  		return l; //where l = r, either l or r is the pivotIndex, return for the partition use.
  	}

  	//swap helper method
  	private void swap (ArrayList<T> data, int l, int r) {
  		T temp = data.get(l);
  		data.set(l, data.get(r));
  		data.set(r, temp);
  	}
  	
}