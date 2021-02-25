/*
 *	Microassignment: BST Add and Remove Helpers
 *
 *  BinaryNode: Node in a Binary Tree
 */

public class BinaryNode<T> {

	// Underlying private value in the node
	private T _value;
	
	// *Pointers* to the left and right children
	BinaryNode<T> _left_child;
	BinaryNode<T> _right_child;
	
	// Constructing a new node starts with defaults
	public BinaryNode()
	{
		_left_child = null;
		_right_child = null;
	}
	
	// Constructing with an initial value
	public BinaryNode(T value)
	{
		_value = value;
	}
	
	// Constructing with existing children - used in copying trees
	public BinaryNode(BinaryNode<T> left, BinaryNode<T> right)
	{
		_left_child = left;
		_right_child = right;
	}
	
	// Should be removed - Nodes don't know about tree behaviors
	public boolean isLeaf()
	{
		return _left_child == null && _right_child == null;
	}
	
	// Accessor interfaces to retrive & set private variables
	public BinaryNode<T> getLeftChild()
	{
		return _left_child;
	}
	
	public void setLeftChild(BinaryNode<T> left)
	{
		_left_child = left;
	}
	
	public BinaryNode<T> getRightChild()
	{
		return _right_child;
	}
	
	public void setRightChild(BinaryNode<T> right)
	{
		_right_child = right;
	}
	
	public T getValue()
	{
		return _value;
	}
	
	public void setValue(T value)
	{
		_value = value;
	}	
}
