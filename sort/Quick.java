package sort;

public class Quick extends Sort {

	@Override
	public void sort(Comparable[] a) {
		sort(a, 0, a.length-1);
		assert isSorted(a);

	}
	
	private void sort(Comparable[] a, int lo, int hi){
		if(hi <= lo) return;
		int j = partition(a, lo, hi);
		sort(a, lo, j-1);
		sort(a, j+1, hi);
	}
	
	private int partition(Comparable[] a, int lo, int hi){
		int i = lo;
		int j = hi + 1;
		Comparable v = a[lo];
		while(true){
			while(less(a[++i], v)) 
				if(i==hi) break;
			while(less(v, a[--j])) 
				if(j==lo) break;
			if(i>=j) break;
			exch(a, i, j);
		}
		exch(a, lo, j);
		return j;
	}
	
	private int partition2(Comparable[] a, int lo, int hi){
		int pos = lo;
		for(int i = lo+1; i <= hi; i++){
			if(!less(a[lo], a[i])) exch(a, ++pos, i);
		}
		exch(a, lo, pos);
		return pos;
	}

	
	public static void main(String[] args) {
		Integer[] a = {4,1,7,3,2,3,1,3,8,4};
		new Quick().sort(a, 0, a.length-1);
		
		
		for (Integer integer : (Integer[])a) {
			System.out.print(integer + " ");
		}
	}
}
