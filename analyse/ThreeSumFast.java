package analyse;
import java.util.Arrays;

import edu.princeton.cs.algs4.*;

import tool.StopWatch;

import search.BinarySearch;

public class ThreeSumFast {
	public static int count(int[] a){
		int count = 0;
		int len = a.length;
		//Arrays.sort(a); 
		for(int i = 0; i < len; i++){
			for(int j = i + 1; j < len; j++){
				if(j < BinarySearch.rank(-a[i]-a[j], a))
					count++;
			}
		}		
		return count;
	}
	
	private static int[] doubleingTest(int [] a){
		int[] b = new int[a.length * 2];
		System.arraycopy(a, 0, b, 0, a.length);
		System.arraycopy(a, 0, b, a.length, a.length);
		return b;
	}
	
	public static void main(String[] args) {
		int a[] = new In("data/1Kints.txt").readAllInts();
		Arrays.sort(a); 
		StopWatch timer = new StopWatch();
		System.out.println(count(a));
		System.out.println(timer.elapseTime());
		
	}
}