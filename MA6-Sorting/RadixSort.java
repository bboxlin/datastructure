
import java.util.ArrayList;



public class RadixSort<T extends Comparable<T>> extends Sorter<T>  {
	
	public RadixSort() {
		name = "Radix Sort";
	}
	
	
	@Override
	public ArrayList<T> sort(SortStats stats, ArrayList<T> data) {
		if(data.size()>1) {
			radixSort(stats, data);
		}
		return data;
	}
	/*
	 * 
	 * Buckets's Structural Diagram:
	 *    
	 * ArrayList (Bucket)
	 * x   x   x  ....
	 * x   x   x   
	 * ^   ^   ^
	 * |   |   |   
	 * 0   1   2   3   4   5   6   7   8    9  ----------------> ArrayList (List of Buckets)
	 * 
	 *     (digit)           1  ---   Starting from lowest digit, elements are add to the digit position of the BucketList in a ArrayList data structure. 
	 *                                << We want to use the arranged elements that stored in the BucketList to do the next iteration, so we set the 
	 *                                  data list = All elements in the buckets in order(several buckets), for the reference. 
	 *                                  
	 *     (Tens)            2  ---   Then going to the next higher digit and do the same arrangement technique.
	 *     (Hundreds)        3  ---
	 *                       ....
	 *                       n  ---   After arranged, all being sorted.
	 *                       
	 *     where n = numbers of the digit the max value had = numbers of iteration we will need 
	 *            
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void radixSort(SortStats stats, ArrayList<T> data) {
		
		//the numbers of the digit the max valu had.
		int digit = getMaxDigit(stats, data);
		
		//construct up the structure buckets in side of the list of buckets
		ArrayList<ArrayList<T>> bucketList = new ArrayList<>();
		for(int i = 0; i< 10; i++) {
			ArrayList<T> bucket = new ArrayList<>(); //buckets
			bucketList.add(bucket);
		}
		
		//i = numbers of iteration through the numbers of the digit max value had.
		int i = 0;
		int mod = 10;
		int div = 1;
		while(i<digit) {
			//traverse through the data list
			for(int j = 0; j<data.size(); j++) {
				int cur = Integer.parseInt(data.get(j).toString());
				
				//XX%10 get digits ones place,  XXX%100 get tens and ones place but / 10 again get tens place only.
				//so we when we *10 the mod value we need to *10 the div value.
				int digitVal = cur%mod/div;
				Object curval = cur;
				
				//digitVal = value of the current digit = position of the bucketList, then add currentValue (Linkedlist) to that position.
				bucketList.get(digitVal).add((T)curval);
			}
			i++;
			mod*=10;
			div*=10;
			
			//we have to update the data list for the reference of next digit arrangement.
			setDataList(data, bucketList);
		}
		
	}
	
	/*
	 * <Helper Method> 
	 * 
	 * Function: To reset the data list in the new order, the order is according to the elements in the buckets.
	 * 
	 */
	private void setDataList(ArrayList<T> data, ArrayList<ArrayList<T>> bucketList) {
		data.clear();
		for (ArrayList<T> currList : bucketList) { 
            data.addAll(currList);
            currList.clear();
        }
	 
	}
	
	
	/*
	 * <Helper Method>
	 * 
	 * Function: To get the max value's digit number 
	 * 
	 */
	private int getMaxDigit(SortStats stats, ArrayList<T> data) {
		
		T max = data.get(0);
		for(int i = 0; i<data.size(); i++) {
			if(data.get(i).compareTo(max)>0) {
				max = data.get(i);
			}
		}
		int maxValue = Integer.parseInt(max.toString());;
		int digit = 1;
		while(maxValue>0) {
			maxValue = maxValue / 10;
			digit ++;
		}
		return digit;
	}

}

