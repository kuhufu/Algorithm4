package analyse;
import java.util.Arrays;

import edu.princeton.cs.algs4.*;
import search.BinarySearch;
import tool.StopWatch;

public class ThreeSum {
	public static int count(int[] a){
		int count = 0;
		int len = a.length;
		
		for(int i = 0; i < len; i++){
			for(int j  = i + 1; j < len; j++){
				for(int z = j + 1; z < len; z++){
					if((a[i] + a[j] + a[z]) == 0){
						count++;
					}
				}
			}
		}
		
		return count;
	}
	
	public static int countFaster(int[] a){
		int count = 0;
		int len = a.length;
		Arrays.sort(a); 
		
		for(int i = 0; i < len; i++){
			for(int j = i + 1; j < len; j++){
				if(j < BinarySearch.rank(-a[i]-a[j], a))
					count++;
			}
		}	
		
		return count;
	}
	
	public static int countFastest(int[] a){
		int count = 0;
		Arrays.sort(a);
		int len = a.length;
		int posmin = 0;
		int posmax = len - 1;
	
		for(int i = 0; i < len; i++){
			posmin = i + 1;
			posmax = len - 1;
			while((posmin < posmax) && (a[posmin] + a[i] <= 0)){
				if(a[posmin] + a[i] + a[posmax] < 0) posmin++;
				else if(a[posmin] + a[i] + a[posmax] > 0) posmax--;
				else {posmin++; posmax--; count++;}
			}
		}

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
			for(int j  = i  + 1; j < len; j++){
				if(a[i] != a[j]) break;
				count++;
			}
		}		
		return count;
	}
	
	public static void main(String[] args) {
		//Attention: the array must has no duplicate elements;
		int a[] = new In("data/8Kints.txt").readAllInts();
		
		Stopwatch timer = new Stopwatch();
		System.out.print(count(a) + ", ");
		System.out.println(timer.elapsedTime());
		
		Stopwatch timer2 = new Stopwatch();
		System.out.print(countFaster(a) + ", ");
		System.out.println(timer2.elapsedTime());
		
		Stopwatch timer3 = new Stopwatch();
		System.out.print(countFastest(a) + ", ");
		System.out.println(timer3.elapsedTime());
	}
}
