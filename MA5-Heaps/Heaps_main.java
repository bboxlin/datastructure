/*
 *  Assignment: Implementing Percolates
 *
 *  [this file]: Main driver and test file
 *   Heaps Microassignment
 * 
 *  Copyright:
 *   For academic use only under the Creative Commons
 *   Attribution-NonCommercial-NoDerivatives 4.0 International License
 *   http://creativecommons.org/licenses/by-nc-nd/4.0
 */


import java.util.ArrayList;
import java.lang.IndexOutOfBoundsException;


public class Heaps_main {
	
	public static void main(String[] args) {
		int testing_result = 0;

		testing_result |= run_tests();

		if(testing_result > 0 ) {
            System.out.println("Some tests didn't pass.");
        }else{
        	System.out.println("All tests passed - SUCCESS!.");
        }

		System.exit(testing_result);
	}


	public static int run_tests() {
		int return_code = 0;

		return_code |= test_isEmpty();
		return_code |= test_insertSingle();
		return_code |= test_insertSingle_isEmpty();
		return_code |= test_insertSingle_size();
		return_code |= test_findMin();
		return_code |= test_deleteMin();
		return_code |= test_deleteMin_isEmpty();
		return_code |= test_deleteMin_size();
		return_code |= test_singlePercolateUp();
		return_code |= test_DoublePercolateUpLeftChild();
		return_code |= test_DoublePercolateUpRightChild();
		return_code |= test_TriplePercolateUpRightToRoot();
		return_code |= test_SinglePercolateDownLeft();
		return_code |= test_SinglePercolateDownRight();
		return_code |= test_SinglePercolateDownSingleChild();
		return_code |= test_SinglePercolateDownThreeLevels();
		return_code |= test_SinglePercolateDownRightTwoLevels();
		return_code |= test_SinglePercolateDownRightNotToLeaf();
		return_code |= test_SinglePercolateDownRightNotToLeafII();

		return return_code;
	}

	public static int test_isEmpty() {
		System.out.print("Testing isEmpty() -- ");
		BinaryHeap<Integer> heap = new BinaryHeap<>();
		if( heap.isEmpty() == true ) { 
			System.out.println("PASS");
			return 0;
		} else {
			System.out.println("FAIL");
			return 1;
		}
	}

	public static int test_insertSingle() {
		System.out.print("Testing Single Insert() -- ");
		BinaryHeap<Integer> heap = new BinaryHeap<>();
		heap.insert(10);
		ArrayList<Integer> dat = heap.getData();
		if( dat.size() > 0 && dat.get(0) == 10 ) { 
			System.out.println("PASS");
			return 0;
		} else {
			System.out.print("Heap should have value 10, but doesn't -- ");
			System.out.println("FAIL");
			return 1;
		}
	}

	public static int test_insertSingle_isEmpty() {
		System.out.print("Testing Single Insert then isEmpty -- ");
		BinaryHeap<Integer> heap = new BinaryHeap<>();
		heap.insert(10);
		if( heap.isEmpty() == false ) {
			System.out.println("PASS");
			return 0;
		} else {
			System.out.println("FAIL");
			return 1;
		}
	}

	public static int test_insertSingle_size() {
		System.out.print("Testing Single Insert then size -- ");
		BinaryHeap<Integer> heap = new BinaryHeap<>();
		heap.insert(10);
		if( heap.size() == 1 ) {
			System.out.println("PASS");
			return 0;
		} else {
			System.out.println("FAIL");
			return 1;
		}
	}

	public static int test_findMin() {
		System.out.print("Testing findMin() -- ");
		BinaryHeap<Integer> heap = new BinaryHeap<>();
		heap.insert(10);
		Integer top = -1;
		try {
			top = heap.findMin();
		} catch(IndexOutOfBoundsException e) {
			// Just /dev/null the exception
			System.out.println("FAIL");
			return 1;
		}
		if( top.compareTo(10) == 0 ) { 
			System.out.println("PASS");
			return 0;
		} else {
			System.out.println("FAIL");
			return 1;
		}
	}

	public static int test_deleteMin() {
		System.out.print("Testing deleteMin() -- ");
		BinaryHeap<Integer> heap = new BinaryHeap<>();
		heap.insert(10);
		Integer top = -1;

		try {
			top = heap.deleteMin();
		} catch(IndexOutOfBoundsException e) {
			// Just /dev/null the exception
			System.out.println("FAIL");
			return 1;
		}

		if( top.compareTo(10) == 0 ) { 
			System.out.println("PASS");
			return 0;
		} else {
			System.out.println("FAIL");
			return 1;
		}
	}

	public static int test_deleteMin_isEmpty() {
		System.out.print("Testing deleteMin then isEmpty -- ");
		BinaryHeap<Integer> heap = new BinaryHeap<>();
		heap.insert(10);
		try {
			heap.deleteMin();
		} catch(IndexOutOfBoundsException e) {
			// Just /dev/null the exception
			System.out.println("FAIL");
			return 1;
		}

		if( heap.isEmpty() == true ) { 
			System.out.println("PASS");
			return 0;
		} else {
			System.out.println("FAIL");
			return 1;
		}
	}

	public static int test_deleteMin_size() {
		System.out.print("Testing deleteMin then size == 0 -- ");
		BinaryHeap<Integer> heap = new BinaryHeap<>();
		heap.insert(10);
		try {
			heap.deleteMin();
		} catch(IndexOutOfBoundsException e) {
			// Just /dev/null the exception
			System.out.println("FAIL");
			return 1;
		}

		if( heap.size() == 0 ) { 
			System.out.println("PASS");
			return 0;
		} else {
			System.out.println("FAIL");
			return 1;
		}
	}

	// Percolate up testing ***********************************************
	// General testing framework for testing percolate ups
	public static int percolate_up_test(String name, Integer[] data, Integer[] target_data) {
		int ret = 0;
		System.out.print("Testing " + name + " -- \n");
		BinaryHeap<Integer> heap = new BinaryHeap<>();

		//Integer[] target_data = {5, 10};
		//Integer[] data = {10, 5};
		for (Integer item : data) {
			heap.insert(item);
		}
		ArrayList<Integer> res_data = heap.getData();

		System.out.print("  Start:\t");
		for (Integer item: data) {
			System.out.print(item + ", ");
		}
		System.out.println();
		System.out.print("  Target:\t");
		for (Integer item: target_data) {
			System.out.print(item + ", ");
		}
		System.out.println();
		System.out.print("  Result:\t");
		for (Integer item: res_data) {
			System.out.print(item + ", ");
		}
		System.out.println();


		if( res_data.size() != target_data.length ) {
			ret = 1;
		} else {
			for(int i = 0; i < res_data.size(); i++ ) {
				if(res_data.get(i).compareTo(target_data[i]) != 0) {
					ret++;
				}
			}
		}
		if( ret == 0 ) { 
			System.out.println("  -- PASS");
			return 0;
		} else {
			System.out.println("  -- FAIL");
			return 1;
		}
	}

	public static int test_singlePercolateUp() {
		String name = "Single Percolate Up ";
		Integer[] data = {10, 5};
		Integer[] target_data = {5, 10};
		return percolate_up_test(name, data, target_data);
	}

	public static int test_DoublePercolateUpLeftChild() {
		String name = "Double Percolate Up Left Child";
		Integer[] data = {5, 10, 15, 20, 25, 30, 35, 8};
		Integer[] target_data = {5, 8, 15, 10, 25, 30, 35, 20};
		return percolate_up_test(name, data, target_data);
	}

	public static int test_DoublePercolateUpRightChild() {
		String name = "Double Percolate Up Right Child";
		Integer[] data = {5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 8};
		Integer[] target_data = {5, 8, 15, 20, 10, 30, 35, 40, 45, 50, 25};
		return percolate_up_test(name, data, target_data);
	}

	public static int test_TriplePercolateUpRightToRoot() {
		String name = "Triple Percolate Up Right to Root";
		Integer[] data = {5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 2};
		Integer[] target_data = {2, 10, 5, 20, 25, 15, 35, 40, 45, 50, 55, 30};
		return percolate_up_test(name, data, target_data);
	}

	// Percolate down testing ***********************************************
	// General testing framework for percolate down calls
	public static int percolate_down_test(String name, Integer[] data, Integer[] target_data, int deleteMinCount) {
		int ret = 0;
		System.out.print("Testing " + name + " -- ");
		BinaryHeap<Integer> heap = new BinaryHeap<>();

		for (Integer item : data) {
			heap.insert(item);
		}

		// Execute requisite number of deleteMin() calls
		for( int i = 0; i < deleteMinCount; i++ ) {
			try {
				heap.deleteMin();
			} catch(IndexOutOfBoundsException e) {
				System.out.println("FAIL on deleteMin()");
				return 1;
			}
		}

		ArrayList<Integer> res_data = heap.getData();

		System.out.println();
		System.out.print("  Start:\t");
		for (Integer item: data) {
			System.out.print(item + ", ");
		}
		System.out.println();
		System.out.print("  Target:\t");
		for (Integer item: target_data) {
			System.out.print(item + ", ");
		}
		System.out.println();
		System.out.print("  Result:\t");
		for (Integer item: res_data) {
			System.out.print(item + ", ");
		}
		System.out.println();

		if( res_data.size() != target_data.length ) {
			ret = 1;
		} else {
			for(int i = 0; i < res_data.size(); i++ ) {
				if(res_data.get(i).compareTo(target_data[i]) != 0) {
					ret++;
				}
			}
		}
		if( ret == 0 ) { 
			System.out.println("  -- PASS");
			return 0;
		} else {
			System.out.println("  -- FAIL");
			return 1;
		}
	}

	public static int test_SinglePercolateDownLeft() {
		String name = "Single Percolate Down Left";
		int deleteMinsCalled = 1;
		Integer[] data = {5, 10, 15, 20};
		Integer[] target_data = {10, 20, 15};
		return percolate_down_test(name, data, target_data, deleteMinsCalled);
	}

	public static int test_SinglePercolateDownRight() {
		String name = "Single Percolate Down Right";
		int deleteMinsCalled = 1;
		Integer[] data = {5, 15, 10, 20};
		Integer[] target_data = {10, 15, 20};
		return percolate_down_test(name, data, target_data, deleteMinsCalled);
	}

	public static int test_SinglePercolateDownSingleChild() {
		String name = "Single Percolate Down Single Child";
		int deleteMinsCalled = 1;
		Integer[] data = {5, 10, 15, 20, 25};
		Integer[] target_data = {10, 20, 15, 25};
		return percolate_down_test(name, data, target_data, deleteMinsCalled);
	}

	public static int test_SinglePercolateDownThreeLevels() {
		String name = "Single Percolate Down Three Levels";
		int deleteMinsCalled = 1;
		Integer[] data = {5, 10, 15, 20, 25, 30, 35, 40, 45};
		Integer[] target_data = {10, 20, 15, 40, 25, 30, 35, 45};
		return percolate_down_test(name, data, target_data, deleteMinsCalled);
	}

	public static int test_SinglePercolateDownRightTwoLevels() {
		String name = "Single Percolate Down Right Two Levels";
		int deleteMinsCalled = 1;
		Integer[] data = {5, 15, 10, 20, 25, 30, 35, 40, 45};
		Integer[] target_data = {10, 15, 30, 20, 25, 45, 35, 40};
		return percolate_down_test(name, data, target_data, deleteMinsCalled);
	}

	public static int test_SinglePercolateDownRightNotToLeaf() {
		String name = "Single Percolate Down Right Not To Leaf";
		int deleteMinsCalled = 1;
		Integer[] data = {5, 40, 15, 60, 70, 75, 80, 65};
		Integer[] target_data = {15, 40, 65, 60, 70, 75, 80};
		return percolate_down_test(name, data, target_data, deleteMinsCalled);
	}

	public static int test_SinglePercolateDownRightNotToLeafII() {
		String name = "Single Percolate Down Right Not To Leaf II";
		int deleteMinsCalled = 1;
		Integer[] data = {5, 40, 15, 60, 70, 75, 80, 65, 75, 76, 77, 66, 65};
		Integer[] target_data = {15, 40, 65, 60, 70, 66, 80, 65, 75, 76, 77, 75};
		return percolate_down_test(name, data, target_data, deleteMinsCalled);
	}
}