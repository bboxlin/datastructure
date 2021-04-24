/*
 *  Assignment: Comparing string duplicates detection algorithms
 *
 *  [this file]_main: Main testing class for the assignment
 *   Tests inserts and deletes to evaluate implementation
 * 
 *  Copyright:
 *   For academic use only under the Creative Commons
 *   Attribution-NonCommercial-NoDerivatives 4.0 International License
 *   http://creativecommons.org/licenses/by-nc-nd/4.0
 */

import java.util.ArrayList;


// Main object and interface for this program
public class StringDupDetection_main
{

	public static void main(String[] args) {
		small_demo();

		int testing_result = run_tests();

		if(testing_result > 0 ) {
			System.out.println("Some tests didn't pass.");
		}else{
			System.out.println("All tests passed - SUCCESS!.");
		}
		System.exit(testing_result);                    // 0 means all tests pass
	}

	// Suite of tests on our implementation
	public static int run_tests() {
		System.out.println("\n\n");
		System.out.println(" ----- Beginning String Dup Tests ----- ");
		int return_code = 0;

		TestingSets testing_vectors = new TestingSets();

		StringDupDetectorBase bruteForce = new BruteForceDupDetector();
		HashMapDupDetector cleverBigOhOne = new HashMapDupDetector();
		ArrayList<StringDupDetectorBase> detector_algorithms = new ArrayList<>();
		detector_algorithms.add(bruteForce);
		detector_algorithms.add(cleverBigOhOne);

		for( StringDupDetectorBase detector : detector_algorithms ) {
			System.out.println(" -- Running Tests on Detector algorithm: " + detector.algorithm_name);
			for( TestSetBase set : testing_vectors.allSets ) {
				return_code |= test_a_set(set, detector);
			}
			System.out.println("\n -- DONE with Detector " + detector.algorithm_name + "\n\n");
		}

		return return_code;
	}

	public static int test_a_set(TestSetBase set, StringDupDetectorBase detector) {
		int return_code = 0;

		DuplicatesResult res = new DuplicatesResult();
		res.set_data_set_name(set.name);
		res.set_ground_truth_duplicates_arraylist(set.dups);
		res.set_total_strings_checked(set.strings.size());
		res.algorithm_name = detector.algorithm_name;
		res.algorithm_description = detector.description;
		res.algorithm_complexity = detector.complexity;

		res.start_clock();
		ArrayList<String> dups_found = detector.doDupDetection(set.strings);
		res.stop_clock();

		res.set_duplicates_arraylist(dups_found);

		if( res.is_correct_dups() == false ) {
			System.out.println("\n ERR ==> Results did not pass, showing verbose output ==> ");
			res.printOut(true);
		} else {
			res.printOutCompact();
		}

		if( res.is_correct_dups() == false ) {
			return_code++;
		}

		return return_code;
	}

	// Small demo of duplicates detection operating
    public static void small_demo()
    {
		System.out.println(" ----- Small Demo of Duplicates Detection Operating ----- ");
		TestingSets testing_vectors = new TestingSets();
		TestSetBase set = testing_vectors.small;

		StringDupDetectorBase detector = new BruteForceDupDetector();

		DuplicatesResult res = new DuplicatesResult();
		res.set_data_set_name(set.name);
		res.set_ground_truth_duplicates_arraylist(set.dups);
		res.set_total_strings_checked(set.strings.size());
		res.algorithm_name = detector.algorithm_name;
		res.algorithm_description = detector.description;
		res.algorithm_complexity = detector.complexity;

		res.start_clock();
		ArrayList<String> dups_found = detector.doDupDetection(set.strings);
		res.stop_clock();

		res.set_duplicates_arraylist(dups_found);

		System.out.print(" Strings to search: \n\t");
		for( int i = 0; i < set.strings.size(); i++ ) {
			System.out.print(set.strings.get(i) + ",");
		}
		System.out.println();
		System.out.print(" dups to find: \n\t");
		for( int i = 0; i < set.dups.size(); i++ ) {
			System.out.print(set.dups.get(i) + ",");
		}
		System.out.println();

		boolean is_correct = res.is_correct_dups();

		System.out.print(" dups found: \n\t");
		for( int i = 0; i < res.duplicates.size(); i++ ) {
			System.out.print(res.duplicates.get(i) + ",");
		}
		System.out.println();
		System.out.print( "Is that correct? ");
		if( is_correct ) {
			System.out.println("It's correct");
		} else {
			System.out.println("NOPE is badness");
		}

		System.out.println("Showing results dump for small test: ");
		res.printOut(true);
    }
}