/*
 *  Assignment: Comparing String Duplicate Algorithms
 *
 *  BruteForceDupDetector: An N^2 "Biggest Hammer" approach to duplicate detection
 *   Duplicates Detector / Filter
 * 
 *  Copyright:
 *   For academic use only under the Creative Commons
 *   Attribution-NonCommercial-NoDerivatives 4.0 International License
 *   http://creativecommons.org/licenses/by-nc-nd/4.0
 */

import java.util.ArrayList;

class BruteForceDupDetector extends StringDupDetectorBase {

    public BruteForceDupDetector() {
        // Constructor - should set your algorithms general metadata

        // Give your algorithm a name to identify it
        algorithm_name = "Brute Force Check Alles!";

        // Describe the key feature(s) of your algorithm
        description = "Uses a brute force where all strings are checked against all strings";

        // Estimate your algorithm's complexity
        complexity = "N^2";
    }

    // The one function that does the duplicates detection for your algorithm.
    //  This function is called by the testing code
    //  It returns a list of duplicate strings found - NOTE: you CANNOT have duplicates in the results themselves!
    @Override
    public ArrayList<String> doDupDetection(ArrayList<String> strings) {
        // These two arrays are stored in the results when your algorithm is done
        // NOTE: The duplicates ArrayList *cannot* have duplicates within itself!
        ArrayList<String> duplicates = new ArrayList<>();   // Lists a set of strings that appear more than once

        // Algorithm begins here

        for( int i = 0; i < strings.size() - 1; i++ ) {                 // For each string in strings
            String curr_string = strings.get(i);                        // Grab the current string
            for( int j = i + 1; j < strings.size(); j++ ) {             // For each string *after* curr_string
                String check_string = strings.get(j);                   // Grab the string to check against
                if( curr_string.compareTo(check_string) == 0 ) {        // Is a duplicate
                    boolean is_already_dup = false;                     // Separate flag for duplicate in duplicates
                    for( int k = 0; k < duplicates.size(); k++ ) {      // Checking if duplicates already has this string
                        if( curr_string.compareTo(duplicates.get(k) ) == 0 ) {  // If a dup in dups
                            is_already_dup = true;                      // Flag that it's a dup of a dup
                        }
                    }
                    if( is_already_dup == false ) {                     // If it's a a dup, but not already in dups
                        duplicates.add(curr_string);                    // Add to duplicates array
                    }
                }
            }
        }

        return duplicates;
    }
}