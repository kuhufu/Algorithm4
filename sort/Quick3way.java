package sort;

public class Quick3way extends Sort{

	@Override
	public void sort(Comparable[] a) {
		sort(a, 0, a.length-1);
		assert isSorted(a);
	}
	
	private void sort(Comparable[] a, int lo, int hi){
		if(hi <= lo) return;
		
		int i = lo;
		int lt = lo;
		int gt = hi;
		Comparable v = a[lo];
		while(i <= gt){
			int cmp = a[i].compareTo(v); 
			if		(cmp < 0) exch(a, i++, lt++);
			else if	(cmp > 0) exch(a,i, gt--);
			else 	i++;
		}
		
		sort(a, lo, lt-1);
		sort(a, gt+1, hi);
	}

}
