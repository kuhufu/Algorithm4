package sort;

import java.util.Arrays;
import java.util.Comparator;

public class Merge extends Sort{
	
	public void sort(Comparable[] a) {
		Comparable[] aux = new Comparable[a.length];
		sort(a, aux, 0, a.length-1);
		assert isSorted(a);
	}
	
	public void sort(Comparable[] a, Comparator cmp) {
		this.cmp = cmp;
		Comparable[] aux = new Comparable[a.length];
		sort(a, aux, 0, a.length-1);
		assert isSorted(a);
	}
	
	public void sort(Comparable[] a, Comparable[] aux, int lo, int hi){
		if(hi <= lo) return;
		int mi = lo + (hi - lo) / 2;
		sort(a, aux, lo, mi);
		sort(a, aux, mi+1, hi);
		merge(a, aux, lo, mi, hi);	
	}
	
	private void merge(Comparable[] a, Comparable[] aux, int lo, int mi, int hi){
		for(int k = lo; k <= hi; k++){
			aux[k] = a[k];
		}	
		
		int i = lo, j = mi+1;
		for(int k = lo; k <= hi; k++){
			if	   (i > mi) 				a[k] = aux[j++];
			else if(j > hi) 				a[k] = aux[i++];
			else if(less(aux[j], aux[i])) 	a[k] = aux[j++];
			else 							a[k] = aux[i++];
		}	
	}
}
