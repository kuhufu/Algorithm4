package sort;

import java.util.Arrays;

public class MergeBU extends Sort {
	
	@Override
	public void sort(Comparable[] a) {
		int N = a.length;
		Comparable[] aux = new Comparable[N];	
		for(int sz = 1; sz < N; sz *= 2){
			for(int i = 0; i < N; i += sz+sz){
				int lo = i;
				int mi = lo + sz - 1;
				int hi = Math.min(lo+sz+sz-1, N-1);
				merge(a, aux, lo, mi, hi);
			}
		}
		assert isSorted(a);
	}
	
	private void merge(Comparable[] a, Comparable[] aux, int lo, int mi, int hi){
		for(int k = lo; k <= hi; k++){
			aux[k] = a[k];
		}
		
		int i = lo, j = mi+1;
		for(int k = lo; k <= hi; k++){
			if(i > mi) 						a[k] = aux[j++];
			else if( j > hi) 				a[k] = aux[i++];
			else if(less(aux[j], aux[i])) 	a[k] = aux[j++];
			else 							a[k] = aux[i++];
		}
		
	}
}
