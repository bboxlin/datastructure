/*
 *  Assignment: Benchmarking a linked list
 *
 *  [this file]_main: Main class for the assignment
 * 
 *  Copyright:
 *   For academic use only under the Creative Commons
 *   Attribution-NonCommercial-NoDerivatives 4.0 International License
 *   http://creativecommons.org/licenses/by-nc-nd/4.0
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Benchmarking {

//读取文件
	//插入value到linkedlist
	//report，min,max,med of the list
	
	public static void main(String[] args) throws IOException {
		
	 
		LinkedList <Integer> input1List = new LinkedList<Integer>();
		readText(input1List,args[0]);
//		long time_insert_start = System.currentTimeMillis();
//		readText(input1List,"C:\\Users\\boxiang\\Desktop\\PA1-Benchmarking\\input1.txt");
//		long time_insert_end = System.currentTimeMillis();
//		System.out.println(time_insert_end - time_insert_start);
		
		System.out.println(input1List);  
		System.out.println(input1List.size());
		
	//	System.out.println(med(input1List));
		
		
//		LinkedList <Integer> test = new LinkedList<Integer>();
//		test.add(2);
//		test.add(3);
//		test.add(5); 
//		if(test.get(test.size()-1)<6) test.add(6);
//		for(int i = 0; i<test.size();i++) {
//			if(6<test.get(i)) {
//				test.add(i,6);
//				break;
//			}
//		}
//		
//		System.out.println(test);
//		 
		
		 
		
	}
	
	public static void readText(LinkedList<Integer> list, String address) throws IOException {
//		Scanner txt = null;
//		try {
//			txt = new Scanner (new File("input1.txt"));
//			
//		}
//		catch (Exception e) {
//			System.out.println("File not exist");
//		}
//		while(txt.hasNextLine()){
//			String s = txt.nextLine();
//			insert(list,Integer.parseInt(s));
//		}
//		txt.close();
		
		
		File text = new File(address);
		BufferedReader br1 = new BufferedReader(new FileReader(text));
		String s = null; 
		while((s=br1.readLine())!=null) { 
			insert(list,Integer.parseInt(s));
		}
		br1.close();
	}
	
	public static void insert(LinkedList<Integer> list, int cur) {
		if(list.size()==0) {   
			list.addFirst(cur);
		}
		if(cur>list.getLast()) {  //if cur is greater than the last number in the list, cur to be the last.
			list.addLast(cur);
		}
		for(int i = 0; i<list.size(); i++) {  //if cur is smaller, cur to be linked to the front of i node. 
			if(cur<list.get(i)) {
				list.add(i,cur);
				break;
			}
			
		} 
	}
	
	public static int min(LinkedList<Integer> list) {
		return list.getFirst();
	}
	public static int max(LinkedList<Integer> list) {
		return list.getLast();
	}
	public static int med(LinkedList<Integer> list) { 
		int size = list.size();
		if(size % 2 == 0) { 
			return list.get(((size-1)/2+(size-1)/2+1)/2);   // even: [(n/2) + ((n/2)+1)]/2  ===> [(size-1)/2+(size-1)/2+1]/2
		}else {
			return list.get(size/2);   //odd = (n+1)/2  where n+1 = size
		}
		 
	}
	
}