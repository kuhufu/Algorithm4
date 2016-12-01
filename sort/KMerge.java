package sort;

public class KMerge extends Sort {
	
	@Override
	public void sort(Comparable[] a) {
		sort(a, 2);
	}

	public void sort(Comparable[] a, int k) {
		Comparable[] aux = new Comparable[a.length];
		sort(a, aux, 0, a.length-1, k);
		assert isSorted(a);
	}
	
	public void sort(Comparable[] a, Comparable[] aux, int lo, int hi, int k){
		if(hi<=lo) return;
		
		int interval = (int)Math.ceil((double)(hi - lo+1)/k);
		int[] lopos = new int[k];
		int[] hipos = new int[k];
		for(int i = 0; i < k; i++){
			lopos[i] = lo + i * interval;
			hipos[i] = Math.min(hi, lopos[i]+interval-1);
		}
		
		for(int i = 0; i < k; i++){
			sort(a, aux, lopos[i], hipos[i], k);
		}		
		merge(a, aux, lo, hi, k, lopos, hipos);	
	}
	
	private void merge(Comparable[] a, Comparable[] aux, int lo, int hi, int k, int[] lopos, int[] hipos){
		for(int i = lo; i <= hi; i++){
			aux[i] = a[i];
		}
				
		for(int i = lo; i <= hi; i++){
			a[i] = getMin(aux, lopos, hipos);
		}
	}
	
	private Comparable getMin(Comparable[] a, int[] lopos, int[] hipos){
		int k = lopos.length;
		int min;
		for(min = 0; min < k; min++){
			if(lopos[min] <= hipos[min]) break;
		}
		
		for(int i = 0; i < k; i++){
			if(lopos[i] > hipos[i]) continue;
			if(less(a[lopos[i]], a[lopos[min]]))min = i;
		}
		
		Comparable minel = a[lopos[min]];
		lopos[min]++;
		
		return  minel;
	}
	
	public static void main(String[] args) {
		int n = 4095;
		int k = 3;
		System.out.println((int)Math.ceil((double)(n-0)/k));
	}
}
