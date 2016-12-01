package sort;

public class MergeY extends Sort {

	@Override
	public void sort(Comparable[] a) {
		int[] perm = new int[a.length];
		int[] aux = new int[a.length];
		for(int i = 0; i<a.length; i++){
			perm[i] = i;
		}
		
		sort(a, perm, aux, 0, a.length-1);
		
		for (int i : perm) {
			System.out.print(i + " ");
		}
	}
	
	private void sort(Comparable[] a, int[] perm, int[] aux, int lo, int hi){
		if(hi<=lo) return;
		int mid = (lo+hi)/2;
		
		sort(a, perm, aux, lo, mid);
		sort(a, perm, aux, mid+1, hi);
		
		merge(a, perm, aux, lo, mid, hi);
	}
	
	public void merge(Comparable[] a, int [] perm, int[] aux, int lo, int mid, int hi){
		for(int k = lo; k <= hi; k++){
			aux[k] = perm[k];
		}	
		
		int i = lo, j = mid+1;
		for(int k = lo; k <= hi; k++){
			if	   (i > mid) 						perm[k] = aux[j++];
			else if(j > hi) 						perm[k] = aux[i++];
			else if(less(a[aux[j]], a[aux[i]])) 	perm[k] = aux[j++];
			else 									perm[k] = aux[i++];
		}	
	}
	
	public static void main(String[] args) {
		Integer[] a = {2};
		new MergeY().sort(a);
	}

}
