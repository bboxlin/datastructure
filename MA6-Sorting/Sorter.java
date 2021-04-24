/*
 *  Assignment: Implementing Quick, Merge, and Radix sort
 *
 *  [this file]: Sorter class Interface
 *   Implements the API for our sorting algorithms
 * 
 *  Copyright:
 *   For academic use only under the Creative Commons
 *   Attribution-NonCommercial-NoDerivatives 4.0 International License
 *   http://creativecommons.org/licenses/by-nc-nd/4.0
 */

import java.util.ArrayList;

public abstract class Sorter<T> {
	public String name = "unknown";		// Should be set in concrete constructor
	public abstract ArrayList<T> sort(SortStats stats, ArrayList<T> data);
}
