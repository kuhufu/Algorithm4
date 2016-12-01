package sort;

public class Tool {
	public static Comparable select(Comparable[] a, int k){
		int lo = 0;
		int hi = a.length - 1;
		
		while(lo < hi){
			int index = partition(a, lo, hi);
			if(index == k) return a[k];
			else if(index < k) lo = index + 1;
			else if(index > k) hi = index - 1;
		}
		
		return a[k];
	}
	
	public static int partition(Comparable[] a, int lo, int hi){
		int lt = lo;
		int gt = hi;
		int i = lo;
		Comparable v = a[lo];
		while(i < gt){
			int cmp = a[i].compareTo(v);
			if(cmp < 0) exch(a, i++, lt++);
			else if(cmp > 0) exch(a, i, gt++);
			else i++;
		}
		return  gt;
	}
	
	private static void exch(Object[] a, int i, int j){
		Object tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
}
