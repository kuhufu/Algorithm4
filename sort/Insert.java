package sort;

import java.util.Comparator;

public class Insert extends Sort{
	
	public  void sort(Comparable[] a){	
		sort(a, 0, a.length-1);
	}
	
	public  void sort(Comparable[] a, Comparator cmp){
		this.cmp = cmp;
		sort(a, 0, a.length-1);
	}
	
	public void sort(Comparable[] a, int lo, int hi){
		sort(a, lo, hi+1, 1);
	}
	
	public void sort(Comparable[] a, int begin, int end, int step){
		for(int i = begin + step; i < end; i++){
			for(int j = i; j > begin + step && less(a[j], a[j-step]); j -= step){
				exch(a, j, j-step);
			}
		}
	}
	
	public static void main(String[] args) {
		Integer[] a = {1,3,9,4,5,7,6,1};
		new Insert().sort(a, 0, 8, 1);
		for (Integer integer : a) {
			System.out.print(integer + " ");
		}
	}
}
