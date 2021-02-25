/*
 *	Microassignment: BST Add and Remove Helpers
 *
 *  BinarySearchTree: A Binary Tree Implementation
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math;

public class BSTAddRemove_main {
	public static void main(String[] args) {
		small_demo();
		int testing_result = run_tests();

		if(testing_result > 0 ) {
			System.out.println("Some tests didn't pass.");
		}else{
			System.out.println("All tests passed - SUCCESS!.");
		}
		System.exit(testing_result);			// 0 means all tests pass
	}

	static void small_demo() {
		System.out.println(" ----- Small Tree Demo ----- ");
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		ArrayList<Integer> node_vals = new ArrayList<>(
			Arrays.asList(10, 5, 3, 7, 15, 12, 19, 21, 1, 2) );
		node_vals.forEach((n) -> tree.addElement(n));
		System.out.print("Inorder: \t");
		tree.printInorder();
		System.out.print("Preorder: \t");
		tree.printPreorder();
		System.out.print("Postorder: \t");
		tree.printPostorder();
	}

	static int run_tests() {
		System.out.println(" ----- Beginning Tree API Tests -----");
		int return_code = 0;

		return_code |= test_isEmpty();
		return_code |= test_addElement();
		return_code |= test_NotIsEmpty();
		return_code |= test_getSize();
		return_code |= test_deletes();

		return return_code;
	}

	static int test_delete_emptyTree() {
		int ret = 0;
		System.out.print(" Deleting 10 from empty (null) tree");
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		tree.removeElement(10);
		if( tree.isEmpty() == true )
			{ System.out.println("\t- Pass"); }
		else
			{ System.out.println("\t- Fail"); ret++; }
		return ret;
	}

	static int test_delete_singleNodeTree() {
		int ret = 0;
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		System.out.print(" Deleting 10 from single node tree");
		tree.addElement(10);
		tree.removeElement(10);
		if( tree.isEmpty() == true )
			{ System.out.println("\t- Pass"); }
		else
			{ System.out.println("\t- Fail"); ret++; }
		return ret;
	}

	static int test_delete_delMissSingleNode() {
		int ret = 0;
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		System.out.print(" Delete miss from single node tree");
		tree.addElement(10);
		tree.removeElement(15);
		if( tree.isEmpty() != true )
			{ System.out.println("\t- Pass"); }
		else
			{ System.out.println("\t- Fail"); ret++; }
		return ret;
	}

	static int test_delete_delPickRightTree() {
		int ret = 0;
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();	
		ArrayList<Integer> node_vals = new ArrayList<>(
			Arrays.asList(10, 5, 3, 7, 15, 12, 19, 21, 1, 2, 17) );
		node_vals.forEach((n) -> tree.addElement(n));

		System.out.println(" Removing 15, pull right child, so replacing with 17");
		System.out.println("  Outputs in preorder traversal --");
		System.out.print("  Starting tree: \t");
		tree.printPreorder();

		ArrayList<Integer> target_list = new ArrayList<>(
			Arrays.asList(10, 5, 3, 1, 2, 7, 17, 12, 19, 21) );

		System.out.print("    Target tree: \t ");
		target_list.forEach((n) -> System.out.print(n + " "));
		System.out.println();

		tree.removeElement(15);
		ArrayList<Integer> curr_list = tree.getAsArrayListPreOrder();
		System.out.print("    Ending tree: \t");
		tree.printPreorder();

		int list_check = 0;
		int list_min_len = Math.min( target_list.size(), curr_list.size() );
		for( int i = 0; i < list_min_len; i++ ) {
			if( target_list.get(i) != curr_list.get(i) ) {
				list_check++;
			}
		}
		if( list_check > 0 ) {
			System.out.println("        -- Remove incorrect");
		} else {
			System.out.println("        -- Remove correct");
		}
		ret |= list_check;
		return ret;
	}

	static int test_delete_delPickLeftTree() {
		int ret = 0;
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();	
		ArrayList<Integer> node_vals = new ArrayList<>(
			Arrays.asList(10, 5, 3, 7, 15, 12, 19, 21, 1, 2, 17) );
		node_vals.forEach((n) -> tree.addElement(n));
		System.out.println(" Removing 15, pull right child, so replacing with 17: ");
		tree.removeElement(15);
		System.out.println(" Removing 5, pull left child, so replacing with 3: ");
		System.out.println("  Outputs in preorder traversal --");

		System.out.print("  Starting tree: \t");
		tree.printPreorder();

		ArrayList<Integer> target_list = new ArrayList<>(
			Arrays.asList(10, 3, 1, 2, 7, 17, 12, 19, 21) );

		System.out.print("    Target tree: \t ");
		target_list.forEach((n) -> System.out.print(n + " "));
		System.out.println();

		tree.removeElement(5);
		ArrayList<Integer> curr_list = tree.getAsArrayListPreOrder();
		System.out.print("    Ending tree: \t");
		tree.printPreorder();

		int list_check = 0;
		int list_min_len = Math.min( target_list.size(), curr_list.size() );
		for( int i = 0; i < list_min_len; i++ ) {
			if( target_list.get(i) != curr_list.get(i) ) {
				list_check++;
			}
		}
		if( list_check > 0 ) {
			System.out.println("        -- Remove incorrect");
		} else {
			System.out.println("        -- Remove correct");
		}
		ret |= list_check;
		return ret;
	}


	static int test_deletes() {
		int ret = 0;
		System.out.println("Testing delete conditions --");

		ret |= test_delete_emptyTree();
		ret |= test_delete_singleNodeTree();
		ret |= test_delete_delMissSingleNode();
		ret |= test_delete_delPickRightTree();
		ret |= test_delete_delPickLeftTree();

		return ret;
	}

	static int test_addElement() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
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

	static int test_getSize() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
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

		tree.addElement(19);			// Add with a collision - should not add!
		size = tree.getSize();
		System.out.print("Testing getSize() after collision add - should return " + node_vals.size() + ": " + size);

		if( size == node_vals.size() ) {
			System.out.println(" - pass");
		} else {
			System.out.println(" - fail");
			res++;
		}

		return res ;			// Note: 0 means success
	}

	static int test_isEmpty() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		System.out.print("Testing isEmpty() - should return true: ");
		boolean res = tree.isEmpty();
		if( res ) {
			System.out.println("True - pass");
		} else {
			System.out.println("False - fail");
		}
		return res ? 0 : 1;			// Note: 0 means success
	}

	static int test_NotIsEmpty() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		tree.addElement(10);
		System.out.print("Testing isEmpty() after addElement - should return false: ");
		boolean res = tree.isEmpty();
		if( res ) {
			System.out.println("True - fail");
		} else {
			System.out.println("False - pass");
		}
		return res ? 1 : 0;			// Note: 0 means success
	}
}
