/*
 *  Assignment: Implementing Percolates
 *
 *  [this file]: Main Heap class
 *   Heaps Microassignment
 * 
 *  Copyright:
 *   For academic use only under the Creative Commons
 *   Attribution-NonCommercial-NoDerivatives 4.0 International License
 *   http://creativecommons.org/licenses/by-nc-nd/4.0
 */

import java.util.ArrayList;

 
import java.lang.IndexOutOfBoundsException;

public class BinaryHeap<AnyType extends Comparable<? super AnyType>>
{
    private ArrayList<AnyType> data = new ArrayList<>();
    private Integer currentSize = 0;
    private static final int DEFAULT_CAPACITY = 10;

    // Default constructor reserves 10 items worth of space
    public BinaryHeap () {
        this(DEFAULT_CAPACITY);
    }

    public BinaryHeap (int reserve_size) {
        ensureHeapSize(reserve_size);
        currentSize = 0;
    }

    // Array initializer-based constructor
    //  Added every item in items to heap
    public BinaryHeap( AnyType [ ] items ) {
        this();             // Call default constructor
        for (AnyType item : items) {
            insert(item);
        }
    }

    // Quick hack of a print out of the heap's data
    public void dump() {
        for (AnyType val : data) {
            System.out.print(val + ", ");
        }
    }

    // Returns a SHALLOW COPY of the data arraylist
    public ArrayList<AnyType> getData() {
        ArrayList<AnyType> newData = new ArrayList<>();
        for (AnyType item : data) {
            if( item != null) {
                newData.add(item);
            }
        }
        return newData;
    }

    // Expands the heap's internal sized ArrayList
    public void ensureHeapSize(int newSize) {
        data.ensureCapacity(newSize);    // Prevent excessive copying while we're adding
        while (data.size() < newSize) {
            data.add(null);
        }
    }

    public AnyType findMin() {
        if( isEmpty( ) ) {
            throw new IndexOutOfBoundsException( );
        }
        return data.get(0);
    }

    public boolean isEmpty() {
        return (currentSize <= 0);
    }

    public void makeEmpty() {
        while( !isEmpty() ) {
            deleteMin();
        }
    }

    public int size() {
        return currentSize;
    }

    public AnyType deleteMin() {
        if( isEmpty( ) ) {
            throw new IndexOutOfBoundsException( );
        }

        AnyType minItem = findMin( );
        data.set(0, data.get(--currentSize));
        data.set(currentSize, null);
        percolateDown( 0 );

        return minItem;
    }

    // ********************************************************************* //
    //  Microassignment Section: Implement percolations
    // ********************************************************************* //
    public void insert(AnyType x) {
        // MA TODO: Write some kind of heap/percolate insert function
  	
    	//expanding the size of heap by twice
    	if(currentSize<=data.size()) {
    		ensureHeapSize(currentSize*2);
    	}
    	
    	//here the data list element are null, we use currentSize to track the position of connecting x to the last element in heap.
    	data.set(currentSize, x);
        currentSize++;
        int index = currentSize-1;
        //parent: (i-1)/2
        
        //time to swap, if current'val > parent's val 
        while(index > 0 && data.get(index).compareTo(data.get((index-1)/2)) < 0) {
      		//swap
       		AnyType temp = data.get(index);
       		data.set(index, data.get((index-1)/2));
       		data.set((index-1)/2, temp);
       		index = (index-1)/2;
       	}

    }

    private void percolateDown( int hole )
    {
//        // MA TODO: Write some kind of heap/percolateUp function
//    	
//    	
    	//left child index: 2i+1,   right child index: 2i+2
    	//data.get(2*i+1) != null || data.get(2*i+2) != null
    	int i = hole;
    	while(data.get(2*i+1) !=null || data.get(2*i+2) !=null){
    		 
    		//in case that the right child is null, and leftchild<current, swap left
    		if(data.get(2*i+2) == null) {
    			if(data.get(i).compareTo(data.get(2*i+1))>0) {
    				AnyType temp = data.get(2*i+1);
    				data.set(i*2+1, data.get(i));
    				data.set(i, temp);
    				i = i*2+1;
    				break;
    			}else {
    				break;
    			}
    		}
    		//in case that left child is null, and rightchild<current, swap right
    		else if(data.get(2*i+1) == null) {
    			if(data.get(i).compareTo(data.get(2*i+2))>0) {
    				AnyType temp = data.get(2*i+2);
    				data.set(i*2+2, data.get(i));
    				data.set(i, temp);
    				i = i*2+2;
    				break;
    			}else {
    				break;
    			}
    		}
    		else if(data.get(i).compareTo(data.get(2*i+1))>0 || data.get(i).compareTo(data.get(2*i+2))>0) {
    			if(data.get(2*i+1).compareTo(data.get(2*i+2))<= 0) {
    				AnyType temp = data.get(2*i+1);
        			data.set(i*2+1, data.get(i));
        			data.set(i, temp);
        			i = i*2+1;
    			}else if(data.get(2*i+1).compareTo(data.get(2*i+2))> 0){
    				AnyType temp = data.get(2*i+2);
    				data.set(i*2+2, data.get(i));
    				data.set(i, temp);
    				i = i*2+2;
    			}else {
    				break;
    			}
    		}else {
    			 break;
    		}
 
    	}
    	 
    }
}