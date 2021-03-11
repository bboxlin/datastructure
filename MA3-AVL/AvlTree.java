/*
 *  Microassignment: Avl Tree Rotations
 *
 *  AvlTree: Yet another Avl Tree Implementation
 * 
 *  Copyright:
 *   For academic use only under the Creative Commons
 *   Attribution-NonCommercial-NoDerivatives 4.0 International License
 *   http://creativecommons.org/licenses/by-nc-nd/4.0
 */

import java.util.ArrayList;

public class AvlTree<T extends Comparable<T>> extends Collection<T> 
{
	private int _remove_counter = 0; 	// Keeps track of the remove direction (left or right)
	private int _size_counter = 0; 		// Keeps track of Tree's size
	protected AvlNode<T> _root = null;	// Tree's root node reference

	//************************************************************************/
	//** Start of MA TODOs 
	//** There are three TODOs in this MA
	//************************************************************************/

	// MA TODO: Implement me FIRST!
	protected AvlNode<T> rotateRight(AvlNode<T> root)
	{
		// Check for null roots
		if (root == null)
			return root;

		// Remove this return and replace with the rotate algorithm
		return root;
		
		// New root comes from left side
		// Reassign right child to new root's left child
		// Recalculate root's height as it has probably changed
		// With that done, we can now reassign old root to new root
		// And perform additional balances as necessary
		// Return the new root
	}

	// MA TODO: Implement me SECOND!
	protected AvlNode<T> rotateLeft(AvlNode<T> root)
	{
		// Check for null roots
		if (root == null)
			return root;

		return root;
		
		// New root comes from right side
		// Reassign right child to new root's left child
		// Recalculate root's height as it has probably changed
		// With that done, we can now reassign old root to new root
		// And perform additional balances as necessary
		// return the new root
	}
	
	// MA TODO: Implement me THIRD!
	//  The purpose of this function is to figure out where the imbalance
	//   occurs within root (left or right) and return the result of the
	//   appropriate rotation (left or right) on root's child.
	protected AvlNode<T> balance(AvlNode<T> root)
	{
		// Check for null roots first
		if (root == null)
			return root;
		
		// MA TODO implement before this return statement!
		//  If you find an imbalance, you will need to either
		//  return the result of rotateLeft or rotateRight

		// Calculate balance status
		// If imblanced, check for the four AVL tree cases
		// Rotate as required, which returns a new root pointer
		// Set new height on new root
		// Return updated root
		
		return root;
	}

	//************************************************************************/
	//** End of MA TODOs */
	//************************************************************************/

	// Removes the largest element from the subtree starting at root
	protected AvlNode<T> removeLargest(AvlNode<T> root)
	{
		if (root == null) { return root; }
		
		// BASE CASE: root is largest (has no right node)
		if (root.getRightChild() == null)
		{
			AvlNode<T> left = root.getLeftChild();
			// Return left node to parent
			return left;
		}
		else
		{
			// RECURSIVE CASE:
			AvlNode<T> new_right = removeLargest(root.getRightChild());
			root.setRightChild(new_right);
			return root;
		}
	}
	
	// Removes the smallest element in the subtree starting at root
	// Update: using the same recursion as removeLargest, UNLIKE BST MA code
	protected AvlNode<T> removeSmallest(AvlNode<T> root)
	{
		if (root == null)
			return root;
		
		// BASE CASE: root is smallest (has no left node)
		if (root.getLeftChild() == null)
		{
			AvlNode<T> right = root.getRightChild();
			// Return right node to parent
			return right;
		}
		else
		{
			// RECURSIVE CASE:
			AvlNode<T> new_left = removeSmallest(root.getLeftChild());
			root.setLeftChild(new_left);
			return root;
		}
	}
	
	// Used by setHeight to handle null nodes who have a height of -1
	private int calcNodeHeight(AvlNode<T> node) {
		return (node == null) ? -1 : node.getHeight();
	}
	
	// Sets the height of a node and balances unbalanced nodes
	protected AvlNode<T> setHeight(AvlNode<T> root)
	{
		// Check for null roots
		//  Java allows this C-style no curly brackets {} single line scope
		//  It's frowned upon since it's easy to cause bugs if another line is accidentally added
		//  The compiler won't care about the white space indents, though the latest g++ (C++)
		//   compilers do throw a warning about how you are indenting and it looks sketchy.
		//  So, to save a future you, just put in the curly brackets: { return root; }
		if (root == null)
			return root;
		
		AvlNode<T> left = root.getLeftChild();
		AvlNode<T> right = root.getRightChild();
		
		// The following code comes from another source. I left it commented out for comparision's sake.
		//  It *does* calculate heights properly, but the use of subtraction here is confusing

		//start at 1 because we assume null trees have height of -1
		// int height = 1;
		// Add in larger of left or right heights
		// int left_height = (left == null) ? -1 : left.getHeight();
		// int right_height = (right == null) ? -1 : right.getHeight();
		// height += (left_height < right_height) ? right_height: left_height;

		// This is Crandall's solution. See helper calc function. Which is easier to read?
		//  Yes, you'll need to know what Math.max() does, but that's a library function you can look up.
		int height = Math.max(calcNodeHeight(left), calcNodeHeight(right)) + 1;
		
		// Reassign new height to root
		root.setHeight(height);
		
		// Check to see if balance factor is out of balance
		int balance_factor = root.getBalanceFactor();
		if (Math.abs(balance_factor) > 1)
		{
			return balance(root);
		}
		return root;
	}
	
	protected AvlNode<T> addElementHelper(AvlNode<T> root, T item)
	{
		// BASE CASE: create new node
		if (root == null)
		{
			root = new AvlNode<T>();	// Allocate a new node
			root.setValue(item);		// Set value of new node
			_size_counter++;			// Bookeeping: track tree size
			return setHeight(root);		// Set height of new node and return it
		}
		
		// Otherwise: traverse tree, use the same helper as in BST
		// RECURSIVE CASE: figure out which child to call
		//  is "item" larger than us (root's item)?
		if (item.compareTo(root.getValue()) > 0){
			AvlNode<T> right_child = root.getRightChild();
			AvlNode<T> result = addElementHelper(right_child, item);
			root.setRightChild(result);
		}
		else if(item.compareTo(root.getValue()) < 0)
		{
			// Same as above, except switching from right to left
			AvlNode<T> left_child = root.getLeftChild();
			AvlNode<T> result = addElementHelper(left_child, item);
			root.setLeftChild(result);
		}
		else
		{
			return root;			// Note: this is if the added value is already in the tree
		}
		
		// For BST, this process would end
		//return root;
		
		// For AVL though, we need to return a balanced node
		//  This could have AVL rotations change which root is returned, but
		//  that isn't important at this level of the recursive calls
		return setHeight(root);
	}
	
	protected AvlNode<T> removeElementHelper(AvlNode<T> root, T item)
	{
		// Use the same helper as in BST
		// Check for a null tree and return as base case
		if (root == null) { return null; }
		
		// Three possibilities:
		//  we found the item (root value == item)
		//  item is less than root (item < root)
		//  item is greater than root (item > root)
		if (item.equals(root.getValue()))
		{
			// Increment removal counter
			_remove_counter++;
			
			// We found the item
			//  do we remove from the left or right?
			if (_remove_counter % 2 == 0)
			{
				// Check to see if left is not null
				if (root.getLeftChild() != null)
				{
					// Left subtree remove
					//  We need the largest from the left side
					AvlNode<T> largest = findLargest(root.getLeftChild());
					
					// Take the largest's value, put inside root
					root.setValue(largest.getValue());
					
					// Having gotten the value, we can now remove the node from the tree
					AvlNode<T> result = removeLargest(root.getLeftChild());
					root.setLeftChild(result);
					return setHeight(root);
				}
				else
				{
					//else, delete the current root, return the result
					return setHeight(removeSmallest(root));
				}
			}
			else
			{
				// Right subtree remove
				if (root.getRightChild() != null)
				{
					AvlNode<T> smallest = findSmallest(root.getRightChild());
					
					root.setValue(smallest.getValue());
					
					AvlNode<T> result = removeSmallest(root.getRightChild());
					root.setRightChild(result);
					return setHeight(root);
				}
				else
				{
					return setHeight(removeLargest(root));
				}
			}				
		}
		else if (item.compareTo(root.getValue()) < 0)
		{
			// Item is less than root
			AvlNode<T> result = removeElementHelper(
					root.getLeftChild(), //send along our left child
					item				 //and the same item
					);
			root.setLeftChild(result);
		}
		else
		{
			//item is greater than root
			AvlNode<T> result = removeElementHelper(
					root.getRightChild(),
					item
					);
			root.setRightChild(result);
		}
		
		// Similar to addElementHelper, need to balance the (possibly replaced via rotate) root		
		return setHeight(root);
	}
	
	protected AvlNode<T> findLargest(AvlNode<T> root)
	{
		while(root != null && root.getRightChild() != null)
			root = root.getRightChild();
		return root;
	}
	
	protected AvlNode<T> findSmallest(AvlNode<T> root)
	{
		while(root != null && root.getLeftChild() != null)
			root = root.getLeftChild();
		return root;
	}

	void printInOrderHelper(AvlNode<T> root) {
		if (root == null) return;

		printInOrderHelper(root.getLeftChild());
		System.out.print(" " + root.getValue());
		printInOrderHelper(root.getRightChild());
	}

	void printPreOrderHelper(AvlNode<T> root) {
		if (root == null) return;

		System.out.print(" " + root.getValue());
		printPreOrderHelper(root.getLeftChild());
		printPreOrderHelper(root.getRightChild());
	}

	void printPostOrderHelper(AvlNode<T> root) {
		if (root == null) return;

		printPostOrderHelper(root.getLeftChild());
		printPostOrderHelper(root.getRightChild());
		System.out.print(" " + root.getValue());
	}

	void getAsArrayListPreOrderHelper(AvlNode<T> root, ArrayList<T> list)
	{
		if (root == null) return;
		
		list.add(root.getValue());
		getAsArrayListPreOrderHelper(root.getLeftChild(), list);
		getAsArrayListPreOrderHelper(root.getRightChild(), list);
	}

	public void printInOrder()   { printInOrderHelper(_root);   }
	public void printPreOrder()  { printPreOrderHelper(_root);  }
	public void printPostOrder() { printPostOrderHelper(_root); }

	public ArrayList<T> getAsArrayListPreOrder() {
		ArrayList<T> list = new ArrayList<>();
		getAsArrayListPreOrderHelper(_root, list);
		return list;
	}

	public void removeElement(T item) {
		_root = removeElementHelper(_root, item);
	}

	// Base class Collection interface (API) implementations
	@Override
	public void addElement(T item) {
		_root = addElementHelper(_root, item);
	}

	@Override
	public boolean isEmpty() {
		return _root == null;
	}

	@Override
	public int getSize() {
		return _size_counter;
	}
	
}
