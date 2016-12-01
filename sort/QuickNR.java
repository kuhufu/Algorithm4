package sort;

import edu.princeton.cs.algs4.Stack;

public class QuickNR extends Sort{

	@Override
	public void sort(Comparable[] a) {
		sort(a, 0, a.length-1);
		assert isSorted(a);
	}
	
	public void sort(Comparable[] a, int lo, int hi){
		if(hi <= lo) return;
		
		Stack<Integer> s = new Stack<>();
		int mid = partition(a, lo, hi);
		if(lo < mid-1){
			s.push(mid-1);
			s.push(lo);
		}
		if(hi > mid+1){
			s.push(hi);
			s.push(mid+1);
		}
		
		while(!s.isEmpty()){
			int p = s.pop();
			int q = s.pop();
			mid = partition(a, p, q);
			
			if(p < mid-1){
				s.push(mid-1);
				s.push(p);
			}
			if(q > mid+1){
				s.push(q);
				s.push(mid+1);
			}
		}
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
	
	public static void main(String[] args) {
		//Integer[] a = {4,1};
		Integer[] a = {4,1,2,7,3,2,1,3,8,4};
		new QuickNR().sort(a);
		
		
		for (Integer integer : (Integer[])a) {
			System.out.print(integer + " ");
		}
	}

}
