package sort;

public class MergeX extends Sort {
	private static final int CUTOFF = 7;
	
	@Override
	public void sort(Comparable[] a) {
		Comparable[] aux = a.clone();
		sort(aux, a, 0, a.length-1);
		assert isSorted(a);
	}
	
	private void sort(Comparable[] src, Comparable[] dst, int lo, int hi){
		//优化：使用插入排序对小数组进行排序
		if(hi <= lo + CUTOFF){
			insertSort(dst, lo, hi);
			return;
		}
		
		int mid = (lo + hi)/2;
		
		//**************************
		sort(dst, src, lo, mid);
		sort(dst, src, mid+1, hi);
		//**************************
		
		if(!less(src[mid+1], src[mid])){
			System.arraycopy(src, lo, dst, lo, hi-lo+1);
			return;
		}
		
		merge(src, dst, lo, mid, hi);
		
	}
	
	private void merge(Comparable[] src, Comparable[] dst, int lo, int mid, int hi){
		int i = lo, j = mid + 1;
		for(int k = lo; k <= hi; k++){
			if(i > mid) 					dst[k] = src[j++];
			else if(j > hi) 				dst[k] = src[i++];
			else if(less(src[i], src[j])) 	dst[k] = src[i++];
			else 							dst[k] = src[j++];
		}
	}

	private void insertSort(Comparable[] a, int lo, int hi){
		for(int i = lo+1; i <= hi; i++){
			for(int j = i; j > lo && less(a[j], a[j-1]); j--){
				exch(a, j, j-1);
			}
		}
	}
}
