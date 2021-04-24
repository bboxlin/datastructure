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
import java.util.HashMap;

class HashMapDupDetector extends StringDupDetectorBase {

    public HashMapDupDetector() {
        // Constructor - should set your algorithms general metadata

        // Give your algorithm a name to identify it
        algorithm_name = "HashMap O(1) Hotrod!";

        // Describe the key feature(s) of your algorithm
        description = "Uses a whatawhat?";

        // Estimate your algorithm's complexity
        complexity = "I dunno!";
    }

    // The one function that does the duplicates detection for your algorithm.
    //  This function is called by the testing code
    //  It returns a list of duplicate strings found - NOTE: you CANNOT have duplicates in the results themselves!
    @Override
    public ArrayList<String> doDupDetection(ArrayList<String> strings) {
        // These two arrays are stored in the results when your algorithm is done
        // NOTE: The duplicates ArrayList *cannot* have duplicates within itself!
        ArrayList<String> duplicates = new ArrayList<>();   // Lists a set of strings that appear more than once

        HashMap<String, String> map = new HashMap<>();

        // Algorithm begins here


        // PA1 CODE GOES HERE! -- MUST use the HashMap (map) for your solution, not Crandall's Brute Force searching!


        // Should have an ArrayList of duplicates to return... right?
        return duplicates;
    }
}