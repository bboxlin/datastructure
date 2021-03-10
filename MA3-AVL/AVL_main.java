/*
 *	AVL tree instantiation and test driver class
 * 
 *  Copyright:
 *   For academic use only under the Creative Commons
 *   Attribution-NonCommercial-NoDerivatives 4.0 International License
 *   http://creativecommons.org/licenses/by-nc-nd/4.0/
 */

import java.util.ArrayList;
import java.util.Arrays;


public class AVL_main {

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
	

	static void small_demo() {
		System.out.println(" ----- Small Tree Demo ----- ");
		AvlTree<Integer> tree = new AvlTree<>();
		ArrayList<Integer> node_vals = new ArrayList<>(
				Arrays.asList(10, 5, 3, 7, 15, 12, 19, 21, 1, 2) );
		node_vals.forEach((n) -> tree.addElement(n));
		System.out.print("Inorder: \t");
		tree.printInOrder();
		System.out.println();
		System.out.print("Preorder: \t");
		tree.printPreOrder();
		System.out.println();
		System.out.print("Postorder: \t");
		tree.printPostOrder();
		System.out.println();
	}

	static int run_tests() {
		System.out.println(" ----- Beginning Tree API Tests -----");
		int return_code = 0;

		return_code |= test_isEmpty();
		return_code |= test_addElement();
		return_code |= test_NotIsEmpty();
		return_code |= test_getSize();
		return_code |= test_rotations();

		return return_code;
	}

	static int test_rotations() {

		int return_code = 0;
		System.out.println("Testing Avl Tree rotations");

		return_code |= test_single_right_root_rotate();
		return_code |= test_single_left_root_rotate();
		return_code |= test_double_right_left_root_rotate();
		return_code |= test_double_left_right_root_rotate();
		return_code |= test_single_right_deep_rotate();
		return_code |= test_single_left_deep_rotate();
		return_code |= test_double_left_right_deep_rotate();
		return_code |= test_double_right_left_deep_rotate();

		return return_code;
	}

	static int test_double_right_left_deep_rotate() {
		int return_code = 0;
		ArrayList<Integer> node_vals = new ArrayList<>(
				Arrays.asList(50, 25, 75, 10, 40, 60, 85, 35, 45, 65, 43 ) );
		ArrayList<Integer> target_vals = new ArrayList<>(
				Arrays.asList(50, 40, 25, 10, 35, 45, 43, 75, 60, 65, 85) );
		return_code = test_tree_rotations("deep right at root", node_vals, target_vals);
		return return_code;
	}

	static int test_double_left_right_deep_rotate() {
		int return_code = 0;
		ArrayList<Integer> node_vals = new ArrayList<>(
				Arrays.asList(50, 25, 75, 10, 40, 60, 85, 15, 30, 55, 65, 62) );
		ArrayList<Integer> target_vals = new ArrayList<>(
				Arrays.asList(50, 25, 10, 15, 40, 30, 65, 60, 55, 62, 75, 85) );
		return_code = test_tree_rotations("deep right at root", node_vals, target_vals);
		return return_code;
	}

	static int test_single_left_deep_rotate() {
		int return_code = 0;
		ArrayList<Integer> node_vals = new ArrayList<>(
				Arrays.asList(10, 8, 20, 15, 25, 30) );
		ArrayList<Integer> target_vals = new ArrayList<>(
				Arrays.asList(20, 10, 8, 15, 25, 30) );
		return_code = test_tree_rotations("deep right at root", node_vals, target_vals);
		return return_code;
	}

	static int test_single_right_deep_rotate() {
		int return_code = 0;
		ArrayList<Integer> node_vals = new ArrayList<>(
				Arrays.asList(20, 10, 25, 8, 15, 5) );
		ArrayList<Integer> target_vals = new ArrayList<>(
				Arrays.asList(10, 8, 5, 20, 15, 25) );
		return_code = test_tree_rotations("deep right at root", node_vals, target_vals);
		return return_code;
	}

	static int test_double_left_right_root_rotate() {
		int return_code = 0;
		ArrayList<Integer> node_vals = new ArrayList<>(
				Arrays.asList(20, 10, 15) );
		ArrayList<Integer> target_vals = new ArrayList<>(
				Arrays.asList(15, 10, 20) );
		return_code = test_tree_rotations("left-right at root", node_vals, target_vals);
		return return_code;
	}

	static int test_double_right_left_root_rotate() {
		int return_code = 0;
		ArrayList<Integer> node_vals = new ArrayList<>(
				Arrays.asList(10, 20, 15) );
		ArrayList<Integer> target_vals = new ArrayList<>(
				Arrays.asList(15, 10, 20) );
		return_code = test_tree_rotations("right-left at root", node_vals, target_vals);
		return return_code;
	}

	static int test_single_right_root_rotate() {
		int return_code = 0;
		ArrayList<Integer> node_vals = new ArrayList<>(
				Arrays.asList(10, 5, 2) );
		ArrayList<Integer> target_vals = new ArrayList<>(
				Arrays.asList(5, 2, 10) );
		return_code = test_tree_rotations("right at root", node_vals, target_vals);
		return return_code;
	}

	static int test_single_left_root_rotate() {
		int return_code = 0;
		ArrayList<Integer> node_vals = new ArrayList<>(
				Arrays.asList(10, 15, 20) );
		ArrayList<Integer> target_vals = new ArrayList<>(
				Arrays.asList(15, 10, 20) );
		return_code = test_tree_rotations("left at root", node_vals, target_vals);
		return return_code;
	}

	static int test_tree_rotations(String rotate_type,
								ArrayList<Integer> tree_elements,
								ArrayList<Integer> target_elements) {
		int return_code = 0;
		System.out.println(" - " + rotate_type + " rotation(s) w/output in preorder");
		
		AvlTree<Integer> tree = new AvlTree<>();
		tree_elements.forEach((n) -> tree.addElement(n));

		System.out.print("Pre-Rotate tree: \t");
		tree_elements.forEach(val -> System.out.print(" " + val));
		System.out.println();

		System.out.print("Target balanced tree: \t");
		target_elements.forEach(val -> System.out.print(" " + val));
		System.out.println();

		ArrayList<Integer> curr_elements = tree.getAsArrayListPreOrder();
		System.out.print("Current tree: \t\t");
		curr_elements.forEach(val -> System.out.print(" " + val));
		System.out.println();

		if( !are_lists_equivalent(target_elements, curr_elements) ) {
			return_code = 1;
			System.out.println(" - " + rotate_type + " rotate - fail");
		} else {
			return_code = 0;
			System.out.println(" - " + rotate_type + "rotate - pass");
		}

		return return_code;
	}

	static <T> boolean are_lists_equivalent(ArrayList<T> one, ArrayList<T> two) {
		if( one.size() != two.size() ) {
			return false;
		}
		for( int i = 0; i < one.size(); i++ ) {
			if( one.get(i) != two.get(i) ) {
				return false;
			}
		}
		return true;
	}

	static int test_isEmpty() {
		AvlTree<Integer> tree = new AvlTree<>();
		System.out.print("Testing isEmpty() - should return true: ");
		boolean res = tree.isEmpty();
		if( res ) {
				System.out.println("True - pass");
		} else {
				System.out.println("False - fail");
		}
		return res ? 0 : 1;                     // Note: 0 means success
	}

	static int test_addElement() {
		AvlTree<Integer> tree = new AvlTree<>();
		int res = 0;
		tree.addElement(15);
		tree.addElement(10);
		tree.addElement(20);
		System.out.print("Testing addElement - isEmpty should be false: ");
		if( tree.isEmpty() == false )
				{ System.out.println("false - pass"); }
		else
				{ System.out.println("true - fail"); res++; }
		return res;
	}

	static int test_NotIsEmpty() {
		AvlTree<Integer> tree = new AvlTree<>();
		tree.addElement(10);
		System.out.print("Testing isEmpty() after addElement - should return false: ");
		boolean res = tree.isEmpty();
		if( res ) {
				System.out.println("True - fail");
		} else {
				System.out.println("False - pass");
		}
		return res ? 1 : 0;                     // Note: 0 means success
	}

	static int test_getSize() {
		AvlTree<Integer> tree = new AvlTree<>();
		int size = tree.getSize();
		int res = 0;
		System.out.print("Testing getSize() - should return 0: " + size);
		if( size == 0 ) {
				System.out.println(" - pass");
		} else {
				System.out.println(" - fail");
				res++;
		}

		ArrayList<Integer> node_vals = new ArrayList<>(
				Arrays.asList(10, 5, 3, 7, 15, 12, 19, 21, 1, 2) );
		node_vals.forEach((n) -> tree.addElement(n));

		size = tree.getSize();
		System.out.print("Testing getSize() - should return " + node_vals.size() + ": " + size);

		if( size == node_vals.size() ) {
				System.out.println(" - pass");
		} else {
				System.out.println(" - fail");
				res++;
		}

		tree.addElement(19);                    // Add with a collision - should not add!
		size = tree.getSize();
		System.out.print("Testing getSize() after collision add - should return " + node_vals.size() + ": " + size);

		if( size == node_vals.size() ) {
				System.out.println(" - pass");
		} else {
				System.out.println(" - fail");
				res++;
		}

		return res ;                    // Note: 0 means success
	}
}