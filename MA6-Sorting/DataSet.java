/*
 *  Assignment: Implementing Quick, Merge, and Radix sort
 *
 *  [this file]: A set of lists to sort (a Data Set)
 *   Use in Main for testing
 * 
 *  Copyright:
 *   For academic use only under the Creative Commons
 *   Attribution-NonCommercial-NoDerivatives 4.0 International License
 *   http://creativecommons.org/licenses/by-nc-nd/4.0
 */


import java.util.ArrayList;
import java.util.Random;

class DataSet {
    public ArrayList<Integer> presorted = new ArrayList<>();
    public ArrayList<Integer> reverseSorted = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> randoms = new ArrayList<ArrayList<Integer> >(5);

    DataSet(DataSet other) {                    // Copy existing set
        presorted = new ArrayList<Integer>(other.presorted);
        reverseSorted = new ArrayList<Integer>(other.reverseSorted);
        for( ArrayList<Integer> list : other.randoms ) {
            randoms.add(new ArrayList<Integer>(list));
        }
    }

    DataSet(Integer arrayLengths) {
        Random randomGenerator = new Random();
        Integer max_val = 10;
        if( arrayLengths > 10 ) {
            max_val = arrayLengths;
        }

        for( int i = 0; i < 5; i++ ) {
            randoms.add(new ArrayList<Integer>());
        }

        for( int i = 0; i < arrayLengths; i++ ) {
            presorted.add(i);
            reverseSorted.add(arrayLengths - i);
            for( int j = 0; j < 5; j++ ) {
                randoms.get(j).add(randomGenerator.nextInt(max_val));
            }
        }
    }

    public void dump() {
        dump_arr(presorted);
        System.out.println();
        dump_arr(reverseSorted);
        System.out.println();
        for( ArrayList<Integer> list : randoms ) {
            dump_arr(list);
            System.out.println();
        }
    }

    public static void dump_arr(ArrayList<Integer> list) {
        for( int i = 0; i < list.size(); i++ ) {
            System.out.print(list.get(i));
            if( i < list.size() - 1 ) {
                System.out.print(", ");
            }
        }
    }
}