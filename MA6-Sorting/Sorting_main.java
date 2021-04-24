/*
 *  Assignment: Implementing Quick, Merge, and Radix sort
 *
 *  [this file]: Main driver and test file
 *   Sorting Microassignment
 * 
 *  Copyright:
 *   For academic use only under the Creative Commons
 *   Attribution-NonCommercial-NoDerivatives 4.0 International License
 *   http://creativecommons.org/licenses/by-nc-nd/4.0
 */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Sorting_main {
	
	public static void main(String[] args) {
		int testing_result = 0;

		String numSets = "tens";
		for( String arg : args ) {
			if( arg.compareTo("thousands") == 0 ) {
				numSets = "thousands";
			}
			else if( arg.compareTo("million") == 0 ) {
				numSets = "million";
			}
			else if( arg.compareTo("billion") == 0 ) {
				numSets = "billion";
			}
		}

		testing_result |= run_tests(numSets);

		if(testing_result > 0 ) {
            System.out.println("Some tests didn't pass.");
        }else{
        	System.out.println("All tests passed - SUCCESS!.");
        }

		System.exit(testing_result);
	}


	public static int run_tests(String numSets) {
		int return_code = 0;

		ArrayList<Integer> setSizes = new ArrayList<Integer>(Arrays.asList(0, 10, 20, 100));
		if( numSets == "tens" ) {
			setSizes = new ArrayList<Integer>(Arrays.asList(0, 10, 20, 100));
		} else if( numSets == "thousands" ) {
			setSizes = new ArrayList<Integer>(Arrays.asList(1000, 10000));
		} else if( numSets == "million" ) {
			setSizes = new ArrayList<Integer>(Arrays.asList(1000000));
		} else if( numSets == "billion" ) {
			setSizes = new ArrayList<Integer>(Arrays.asList(1000000000));
		}

		ArrayList<DataSet> sets = new ArrayList<>();
		for( Integer size : setSizes ) {
			sets.add(new DataSet(size));
		}

		// Start testing our sorting algorithms
		System.out.println("****************************************************************");
		System.out.println("****** TESTING INSERTION SORT                       ************");
		System.out.println("****************************************************************");
		InsertionSort<Integer> insertion = new InsertionSort<>();

		for( DataSet set : sets ) {
			if( set.presorted.size() < 30 && set.presorted.size() != 0) {
				return_code |= test_sort_alg(insertion, set, true);
			} else {
				return_code |= test_sort_alg(insertion, set, false);
			}
		}

		System.out.println("****************************************************************");
		System.out.println("****** TESTING QUICK SORT                           ************");
		System.out.println("****************************************************************");
		QuickSort<Integer> quick = new QuickSort<>();

		for( DataSet set : sets ) {
			if( set.presorted.size() < 30 && set.presorted.size() != 0) {
				return_code |= test_sort_alg(quick, set, true);
			} else {
				return_code |= test_sort_alg(quick, set, false);
			}
		}

		System.out.println("****************************************************************");
		System.out.println("****** TESTING MERGE SORT                           ************");
		System.out.println("****************************************************************");
		MergeSort<Integer> merge = new MergeSort<>();

		for( DataSet set : sets ) {
			if( set.presorted.size() < 30 && set.presorted.size() != 0) {
				return_code |= test_sort_alg(merge, set, true);
			} else {
				return_code |= test_sort_alg(merge, set, false);
			}
		}

		// Radix sort?



		return return_code;
	}

	// Test a given algorithm with a given data set
	public static int test_sort_alg(Sorter<Integer> sort_alg, DataSet dataSet, boolean verbose) {
		DataSet dataCopy = new DataSet(dataSet);		// copy dataset so we don't destroy it (by sorting it)
		int ret = 0;
		System.out.println("***** Running tests on algorithm: " + sort_alg.name + " with " + dataSet.presorted.size() + " items *****");
		ret |= run_test(sort_alg, dataCopy.presorted, "Presorted", verbose);
		ret |= run_test(sort_alg, dataCopy.reverseSorted, "Reverse Sorted", verbose);
		for( int i = 0; i < 5; i++ ) {
			ret |= run_test(sort_alg, dataCopy.randoms.get(i), "Random #" + i, verbose);
		}

		System.out.print(" Is Sorting Algorithm " + sort_alg.name + " Correct? - ");
		if( ret == 0 ) {
			System.out.println("PASS");
		}
		else 
		{
			System.out.println("FAIL");
		}
		return ret;
	}

	// Runs a test with a given algorithm a single list
	public static int run_test(Sorter<Integer> sort_alg, ArrayList<Integer> data, String data_name, boolean verbose) {
		int ret = 0;
		SortStats stats = new SortStats();
		long start_time = 0;
		long end_time = 0;

		System.out.print("Running sorts on dataset: " + data_name + " of " + data.size() + " items ... ");
		ArrayList<Integer> system_sorted = new ArrayList<Integer>(data);	// copy starting list
		Collections.sort(system_sorted);			// Get a known correct answer
		ArrayList<Integer> alg_sorted = new ArrayList<Integer>(data);	// copy starting list
		start_time = System.nanoTime();				// Grab clock for ms output
		sort_alg.sort(stats, alg_sorted);			// Run sort using user-defined sort
		end_time = System.nanoTime();				// Grab clock for ms output

		if( system_sorted.size() != data.size() ) {
			System.out.println("Two resulting lists are different sizes - data lost.. or gained?!");
			ret++;
			return ret;
		}
		for( int i = 0; i < system_sorted.size(); i++ ) {
			if(system_sorted.get(i) != alg_sorted.get(i) ) {
				ret++;
			}
		}
		if( verbose == true ) {
			System.out.println();
			System.out.print("Start:    ");
			DataSet.dump_arr(data);
			System.out.println();
			System.out.print("Correct:  ");
			DataSet.dump_arr(system_sorted);
			System.out.println();
			System.out.print("Alg Sort: ");
			DataSet.dump_arr(alg_sorted);
			System.out.println();
		}

		long runtime = end_time - start_time;
		String human_time = getReadableTime(runtime);
		System.out.print("Runtime: " + human_time + " | Compares: " + stats.comparisons + " | Swaps: " + stats.swaps + " | Correct - ");
		if( ret == 0 ) {
			System.out.println("PASS");
		} else {
			System.out.println("FAIL");
		}

		return ret;
	}

	// Converts between nanoseconds and a human readable time
	private static String getReadableTime(Long nanos){

		double mininanosecs = (double)nanos/(1000*1000*1000);
		long nanosecs = (long)((mininanosecs - (int)mininanosecs) * (1000*1000*1000));
		// System.out.println(nanosecs);
	    long tempSec = nanos/(1000*1000*1000);
    	long sec = tempSec % 60;
    	long min = (tempSec /60) % 60;
    	long hour = (tempSec /(60*60)) % 24;
    	long day = (tempSec / (24*60*60)) % 24;
    	return String.format("%dd %dh %dm %ds %dns", day,hour,min,sec,nanosecs);
	}
}
