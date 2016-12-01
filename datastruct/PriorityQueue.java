package datastruct;

import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class PriorityQueue <Key> implements Iterable<Key>{
	private Key[] minpq;
	private Key[] maxpq;
	private int N = 0;
	private Comparator<Key> comparator;
	private int count = 0;
	
	public PriorityQueue(int cap) {
		minpq = (Key[])new Object[cap+1];
		maxpq = (Key[])new Object[cap+1];
	}
	
	public void insert(Key v){
		insize();
		minpq[++N] = v; 
		maxpq[N] = v;
		minSwim(N);
		maxSwim(N);
		count++;
	}
	
	public Key delMin(){
		Key min = minpq[1];
		exch(minpq, 1, N--);
		minpq[N+1] = null;
		minSink(1);
		count++;
		return min;
	}
	
	public Key delMax(){
		Key max = maxpq[1];
		exch(maxpq, 1, N--);
		maxpq[N+1] = null;
		maxSink(1);
		count++;
		return max;
	}
	
	private void minSwim(int k){
		while(k > 1 && less(minpq, k, k/2) ){
			exch(minpq, k, k/2);
			k = k/2;
		}
	}
	
	private void minSink(int k){
		while(2*k <= N){
			int j = 2*k;
			if(j < N && less(minpq, j+1, j)) j++;
			if(!less(minpq, j, k)) break;
			exch(minpq, k, j);
			k = j;
		}
	}
	private void maxSwim(int k){
		while(k > 1 && less(maxpq, k/2, k) ){
			exch(maxpq, k, k/2);
			k = k/2;
		}
	}
	
	private void maxSink(int k){
		while(2*k <= N){
			int j = 2*k;
			if(j < N && less(maxpq, j, j+1)) j++;
			if(!less(maxpq, k, j)) break;
			exch(maxpq, k, j);
			k = j;
		}
	}

	
	private boolean less(Key[] pq, int i, int j){
		if(comparator == null)
			return ((Comparable<Key>)pq[i]).compareTo(pq[j]) < 0;
		else
			return comparator.compare(pq[i], pq[j]) < 0;
	}
	
	private void exch(Key[] a, int i, int j){
		Key t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	
	public boolean isEmpty(){
		return N == 0;
	}
	
	public int size(){
		return N;
	}
	
	private void insize(){
		if(N==minpq.length-1)
			resize(2*N);
	}
	private void resize(int newLen){
		Key[]tmp = (Key[])new Object[newLen+1];
		for(int i = 0; i <= N; i++){
			tmp[i] = minpq[i];
		}
		minpq = tmp;
		maxpq = tmp.clone();
	}

	@Override
	public Iterator<Key> iterator() {
		return new PQIterator();
	}
	
	private class PQIterator implements Iterator<Key>{
		int pos = 1;
		int c = count;
		
		@Override
		public boolean hasNext() {
			if(c != count) throw new ConcurrentModificationException();
			return pos <= N;
		}

		@Override
		public Key next() {
			if(c != count) throw new ConcurrentModificationException();
			return minpq[pos++];
		}
	}
	
	public static void main(String[] args) {
		PriorityQueue<Integer> pq = new PriorityQueue<>(10);
		pq.insert(3);
		pq.insert(4);
		pq.insert(1);
		pq.insert(4);
		
		for (int i = 0; i < pq.size(); i++) {
			System.out.println(pq.delMax());
		}
	}
}
