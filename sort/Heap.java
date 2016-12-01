package sort;

public class Heap extends Sort{

	public void sort(Comparable[] a) {
		int N = a.length-1;
		for(int k = N/2; k >=0; k--){
			sink(a, k, N);
		}
		
		while(N >= 0){
			exch(a, 0, N--);
			sink(a, 0, N);
		}
		assert isSorted(a);
	}
	
	private void sink(Comparable[] a, int k, int N){
		while(2*k+1 <= N){
			int j = 2*k+1;
	        if (j < N && less(a[j], a[j+1])) j++;
			if(!less(a[k], a[j])) break;
			exch(a, k, j);
			k = j;
		}
	}

	public static void main(String[] args) {
		Integer[] a = {4,1};
		new Heap().sort(a);
		
		
		for (Integer integer : (Integer[])a) {
			System.out.print(integer + " ");
		}
	}

}
