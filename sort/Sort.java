package sort;

import java.util.Comparator;

public abstract class Sort {
	
	protected Comparator cmp;
	
	public abstract void sort(Comparable[] a);

	protected  boolean less(Comparable a, Comparable b){
		if(cmp == null) return a.compareTo(b) < 0;
		else return cmp.compare(a, b) < 0;
		
	}
	
	protected  void exch(Comparable[] a, int i, int j){
		Comparable tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	public  boolean isSorted(Comparable[] a){
		for(int i = 1; i < a.length; i++){
			if(less(a[i], a[i-1])) return false;
		}
		return true;
	}

	public void show(Comparable[] a){
		for (Comparable item : a) {
			System.out.print(item + " ");
		}
		System.out.println();
	}
}