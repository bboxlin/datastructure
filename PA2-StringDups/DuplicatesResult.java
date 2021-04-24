/*
 *  Assignment: Comparing string duplicates detection algorithms
 *
 *  [this file]: Keeps stats on a single duplicates detection algorithm test
 * 
 *  Copyright:
 *   For academic use only under the Creative Commons
 *   Attribution-NonCommercial-NoDerivatives 4.0 International License
 *   http://creativecommons.org/licenses/by-nc-nd/4.0
 */

import java.util.ArrayList;
import java.util.Collections;

public class DuplicatesResult {
    String data_set_name;            // Name for this data set of strings
    ArrayList<String> duplicates;    // Set of duplicates found
    ArrayList<String> ground_truth_duplicates; // Known correct set of duplicates to find
    Integer total_strings;           // Total strings investigated

    long algorithm_start_time = 0;  // NanoSeconds since UNIX Epoch when starting to clock algorithm
    long algorithm_end_time = -1;   // NonoSeconds since UNIX Epoch when done clocking algorithm

    public String algorithm_description;    // A paragraph describing your algorithm
    public String algorithm_name;           // The key alg / structure used for your algorithm
    public String algorithm_complexity;     // Your estimate on your algorithm's complexity

    public DuplicatesResult() {}        // Empty storage constructor

    public Integer duplicates_count() {
        return duplicates.size();
    }

    public void set_total_strings_checked(Integer count) {
        total_strings = count;
    }

    public void set_data_set_name(String name) {
        data_set_name = name;
    }

    public void set_duplicates_arraylist(ArrayList<String> new_duplicates) {
        duplicates = new_duplicates;
    }

    public void set_ground_truth_duplicates_arraylist(ArrayList<String> new_duplicates) {
        ground_truth_duplicates = new_duplicates;
    }

    public void start_clock() {
        algorithm_start_time = System.nanoTime();
    }

    public void stop_clock() {
        algorithm_end_time = System.nanoTime();
    }

    public long algorithm_runtime() {
        return algorithm_end_time - algorithm_start_time;
    }

    public boolean is_correct_dups() {
        if( ground_truth_duplicates.size() != duplicates.size() ) {
            return false;
        }

        // Sort both duplicates lists
        Collections.sort(ground_truth_duplicates);
        Collections.sort(duplicates);

        // Compare both lists element by element for equality, quit if there's a difference
        for(int i = 0; i < duplicates.size(); i++ ) {
            if( duplicates.get(i).compareTo(ground_truth_duplicates.get(i)) != 0 ) {
                return false;
            }
        }

        return true;
    }

    public void printOutCompact() {
        System.out.printf("%14s", data_set_name);
        System.out.print(" | " + algorithm_name + " | ");
        if( is_correct_dups() ) {
            System.out.print("PASS");
        } else {
            System.out.print("FAIL");
        }
        System.out.println(" | Time (ns): " + algorithm_runtime());
    }

    public void printOut() {
        printOut(false);
    }

    public void printOut(Boolean verbose) {
        System.out.println("Printing out results for a test");
        System.out.println("Data Set Name: " + data_set_name + " | Total size: " + total_strings);
        System.out.println("Algorithm Name: " + algorithm_name + " | Est Complexity: " + algorithm_complexity);
        if( verbose == true ) {
            System.out.println("Algorithm Description: " + algorithm_description);
        }
        System.out.println("Runtime (ns): " + algorithm_runtime() );
        System.out.print("Was detection correct? -- ");
        if( is_correct_dups() ) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
        System.out.println(" Expected duplicates: " + ground_truth_duplicates.size() + " | Found duplicates: " + duplicates_count());
        if( verbose == true ) {
            System.out.print(" Expected duplicates list: \n *** ");
            for( int i = 0; i < ground_truth_duplicates.size(); i++ ){
                System.out.print(ground_truth_duplicates.get(i));
                if( i < ground_truth_duplicates.size() - 1 ) {
                    System.out.print(", ");
                }
            }
            System.out.println();
            System.out.print(" Discovered duplicates list: \n *** ");
            for( int i = 0; i < duplicates.size(); i++ ){
                System.out.print(duplicates.get(i));
                if( i < duplicates.size() - 1 ) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }
}