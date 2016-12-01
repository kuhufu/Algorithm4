package sort;

public class Shell extends Sort {

	@Override
	public void sort(Comparable[] a) {
		int N = a.length;
		int h = 1;
		Insert insertSort = new Insert();
		while(h < N/3) h = h * 3 + 1;
		while(h >= 1){
			/*for( int i = h; i < N; i++){
				for(int j = i; j >= h && less(a[j], a[j-h]); j -= h){
					exch(a, j, j-h);
				}
			}*/
			insertSort.sort(a, 0, N, h);
			h /=3;
		}
		
		assert isSorted(a);
	}
	
	public void sort(double[] a) {
		int N = a.length;
		int h = 1;
		while(h < N/3) h = h * 3 + 1;
		while(h >= 1){
			for( int i = h; i < N; i++){
				for(int j = i; j >= h && a[j] < a[j-h]; j -= h){
					double tmp = a[j];
					a[j] = a[j-h];
					a[j-h] = tmp;
				}
			}
			h /=3;
		}
	}

}
