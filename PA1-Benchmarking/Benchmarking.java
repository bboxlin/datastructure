import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * PA1-Benchmarkking 
 * 
 * @author Boxiang Lin
 *
 * Description: Using single linkedlist data structure to store the integer values from
 * the input txt file. Read the integer value in the txt line per line, each line read 
 * will be inserting to the linkedlist at a accurate position to make the list in sorted
 * order. 
 * 
 * 
 * To read the txt, using args command line ---->
 * 
 * 
 */
public class Benchmarking {
	
	private SingleLinkedList list;
	private int max, min;
	private double med;
	private long time_insert, time_med,time_max,time_min;
	
	public Benchmarking() {
		 this.list = new SingleLinkedList();
	}
 
	
	public void run(String address) {
		long start = 0;
		long end = 0;
		
		
		try {
			start = System.nanoTime();
			read_insert(address);
			end = System.nanoTime();
			this.time_insert = end-start;  //time for whole insertion
		}catch(IOException e){
			e.printStackTrace();
			System.exit(0);
		}
		
		start = System.nanoTime();
		this.max = list.getMax();
		end = System.nanoTime();
		this.time_max = end - start;  
		
		
		start = System.nanoTime();
		this.min = list.getMin();
		end = System.nanoTime();
		this.time_min = end-start;
		
		start = System.nanoTime();
		this.med = list.getMed();
		end = System.nanoTime();
		this.time_med = end-start;
	}
	
	
	/*
	 * Method to read file and insert to the LinkedList
	 */
	public void read_insert(String address) throws IOException {
		File text = new File(address);
		BufferedReader br1 = new BufferedReader(new FileReader(text));
		String s = null; 
		while((s=br1.readLine())!=null) { 
			list.insert(Integer.parseInt(s));
		}
		br1.close();
	}
	
	/*
	 * Print
	 */
	public void result() {
		System.out.println("The data in the txt has been sorted to following:                       " + "\n" + list); 
		System.out.println("stats as below: ---------------------------------------------------------------------");
		System.out.println("The execucation time for inserting all the element into the LinkedList: " + time_insert + " (ns)");
		System.out.println("The maxiumum value in the LinkedList is:                                " + max);
		System.out.println("The minimum value in the LinkedList is:                                 " + min);
		System.out.println("The median value in the LinkedList is:                                  " + med);
		System.out.println("The execucation time for find the max element in the LinkedList:        " + time_max + " (ns)");
		System.out.println("The execucation time for find the min element in the LinkedList:        " + time_min + " (ns)");
		System.out.println("The execucation time for find the min element in the LinkedList:        " + time_med + " (ns)");
	 
	 
	}
	
	public static void main(String[] args) {
		Benchmarking program = new Benchmarking();
		program.run(args[0]);
		program.result();
	}

}
