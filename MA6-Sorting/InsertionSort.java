/*
 *  Assignment: Implementing Quick, Merge, and Radix sort
 *
 *  [this file]: Insertion Sort Reference Implementation
 *   Implements Insertion Sort
 * 
 *  Copyright:
 *   For academic use only under the Creative Commons
 *   Attribution-NonCommercial-NoDerivatives 4.0 International License
 *   http://creativecommons.org/licenses/by-nc-nd/4.0
 */

import java.util.ArrayList;

public class InsertionSort<T extends Comparable<T>> extends Sorter<T> {
    InsertionSort() {
        name = "Insertion Sort";
    }

  	public ArrayList<T> sort(SortStats stats, ArrayList<T> data) {

        for (int i = 1; i < data.size(); i++) {
            T value = data.get(i);
            int j = i - 1;
            while( j >= 0 ) {
                T curr_candidate = data.get(j);
                stats.comparisons++;
                if( curr_candidate.compareTo(value) > 0 ) {
                    // System.out.println(value);
                    stats.swaps++;
                    data.set(j + 1, curr_candidate);
                    j--;
                }
                else 
                {
                    break;
                }
            }
            // System.out.println("J : " + (j + 1));
            data.set(j + 1, value);
        }
		return data;
	}
}