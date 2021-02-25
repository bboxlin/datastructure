/*
 * Refering to the LeetCode Learning LinkedList
 * https://leetcode.com/explore/learn/card/linked-list/209/singly-linked-list/1290/
 */

public class SingleLinkedList {
	 
	
	ListNode head;
	int size;
	
	public SingleLinkedList() {
		size = 0;
		head = null;
	}
	
	/*
	 * the data get pass it by Redear
	 * Every each data comes in will be sorted by adjusting the next pointer
	 * 
	 * initial case, initialize the head, and if, there is a value smaller than head,
	 * smaller value being the new head;
	 * 
	 * Else case, have a temp Node called cur, let it traverse through the link, 
	 * if cur.next == null, quite the loop then enter to the if condition, and of course  
	 * here the data being a last node that cur node connecting to.
	 * There is another case, when we traverse the link, we encounter that data is smaller
	 * than our cur.next position's val, in this case, we will connecting to the data as of next node,
	 * and let the data node connect to our previous next node. 
	 */
	
	public void insert(int data) {	 
		 
		if(head == null || head.val > data) {
			ListNode newHead= new ListNode(data);
			newHead.next = head;
			head = newHead;
			size++;
		}else { 
			ListNode cur = head;
			while(cur.next != null && cur.next.val<data) {
				cur = cur.next;
			}
			if(cur.next == null) {
				ListNode nextVal = new ListNode(data);
				cur.next = nextVal;
				size++;
			}else {
				ListNode newNext = new ListNode(data);
				newNext.next = cur.next; //cur.next point to the new val
				cur.next = newNext; //new val then be the new cur.next
				size++;
			}
		}
	}
	
	/*
	 * return the first node --> smallest.
	 */
	public int getMin() {
		return head.val;
	}
	
	/*
	 * return the last node --> biggest.
	 * because here we doing the singleLinked list, we will have to traverse to the end node, size-1.
	 */
	
	public int getMax() {
		return get(size-1);
	}
	
	
	/*
	 * according to the formula, for example odd series sorted of number, med = (n+1)/2  position.
	 * where n = size of linkedlist, for example, to get the last number list(size) will be out of bound, so should be list(size-1)
	 * 
	 * (n+1)/2 here is the position, or index, we need -1 of index in order to get the accurate med position using from the formula.
	 * 
	 * Same reasoning for the even series of sorted number.
	 * 
	 */
	public double getMed() {
		if(size%2==0) {
			return (get(size/2 - 1) + get(size/2 + 1 - 1))/2;   // even: [(n/2) + (n/2)+1 ] /2    minue - 1 of the index ? 
		}else {
			return get((size+1)/2 - 1);   //odd = [(n+1)/2]     list.get((n+1)/2)
		}
	}
	
	
	/*
	 * Helper method to get the specific index value;
	 */
    public int get(int index) {
        ListNode cur = head;
        for(int i = 0; i < index; i++) {
            cur = cur.next;
        }

        return cur.val;
    }
	
    /*
     * To print the list;
     */
	public String toString() {
		ListNode temp = head;
		String result = "";
		while(temp!=null) {
			result = result + temp.val;
			temp = temp.next;
			if(temp !=null) {
				result = result + ", ";
			}
		}
		return "["+result+"]";
	}
	 
}

/*
 * Node component.
 */
class ListNode{
	int val;
	ListNode next;
	public ListNode(int val) {
		this.val = val;
	}
}
