package analyse;
import java.util.Arrays;

import edu.princeton.cs.algs4.*;


public class TwoSumFast {
	public static int count(int[] a){
		int count = 0;
		Arrays.sort(a);
		int len = a.length;
		int posmin = 0;
		int posmax = len - 1;
		Stopwatch timer = new Stopwatch();
		for(int i = 0; i < len; i++){
			if(a[posmin] > 0) break;
			else if(a[posmin] + a[posmax] < 0) posmin++;
			else if(a[posmin] + a[posmax] > 0) posmax--;
			else {posmin++; posmax--; count++;}
		}	
		System.out.println(timer.elapsedTime());
		return count;
	}
	
	public static int equalCount(int[] a){
		int count = 0;
		int len = a.length;
		for(int i = 0; i < len; i++){                                                                    }		
		return count;
	}
	public static int equalCountFast(int[] a){
		if(a.length == 1) return 0;
		Arrays.sort(a);
		int count = 0;
		int len = a.length;
		
		for(int i = 0; i < len; i++){
			for(int j  = i + 1; j < len; j++){
				if(a[i] != a[j]) break;
				count++;
			}
		}		
		return count;
	}
	
	public static void main(String[] args) {
		int a[] = new In("data/8Kints.txt").readAllInts();
		
		System.out.println(count(a));
	}
}
