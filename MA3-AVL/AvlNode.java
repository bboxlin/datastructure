/*
 *  Microassignment: Avl Tree Rotations
 *
 *  AvlNode: Basic node stored in AVL trees
 *
 *  Copyright:
 *   For academic use only under the Creative Commons
 *   Attribution-NonCommercial-NoDerivatives 4.0 International License
 *   http://creativecommons.org/licenses/by-nc-nd/4.0
 */

class AvlNode<T extends Comparable<T>>
{
    // Constructors
	AvlNode()                           // Basic no data constructor
	    { }
	
    AvlNode(T new_value)                // Create with an Node's value and no children
    {
        this(new_value, null, null);
    }

    AvlNode(T new_value, AvlNode<T> new_left_tree, AvlNode<T> new_right_tree)
    {
        value  = new_value;
        left   = new_left_tree;
        right  = new_right_tree;
        height = 0;
    }

    private T value;             	        // The data in the node as type T - see Java Generics
    private AvlNode<T>    left;             // Left child
    private AvlNode<T>    right;            // Right child
    private int           height = 0;       // Height
    
    // As a habit, we are going to keep using getter/setter functions instead of accessing the property directly
    //  This protects our data and allows us to do sanity checking for new values
    //  It could also let us do things like track the number times data is queried or set
    //  Could also help with debugging since these calls actually show up in the call stack
    public void setValue(T new_value)
    {
    	value = new_value;
    }
    
    public T getValue()
    {
    	return value;
    }
    
    public void setLeftChild(AvlNode<T> new_left)
    {
    	left = new_left;
    }
    
    public AvlNode<T> getLeftChild()
    {
    	return left;
    }
    
    public void setRightChild(AvlNode<T> new_right)
    {
    	right = new_right;
    }
    
    public AvlNode<T> getRightChild()
    {
    	return right;
    }    
    
    public void setHeight(int new_height)
    {
    	height = new_height;
    }
    
    public int getHeight()
    {
    	return height;
    }
    
    // Balance factor that helps determines if the current node is balanced
    public int getBalanceFactor()
    {
    	int left_height = (left == null) ? -1 : left.getHeight();
    	int right_height = (right == null) ? -1 : right.getHeight();
    	
    	return right_height - left_height;
    }
    
}