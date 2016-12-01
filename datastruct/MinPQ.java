package datastruct;

import java.util.Comparator;
import java.util.Iterator;

public class MinPQ <Key> implements Iterable<Key>{
	private Key[] pq;
	private int N = 0;
	private Comparator<Key> comparator;
	
	public MinPQ(int cap) {
		pq = (Key[])new Object[cap+1]; 
	}
	
	public MinPQ(int cap, Comparator c) {
		pq = (Key[])new Object[cap+1]; 
		this.comparator = c;
	}
	
	public void insert(Key v){
		insize();
		pq[++N] = v; 
		swim(N);
	}
	
	public Key delMin(){
		Key max = pq[1];
		exch(1, N--);
		pq[N+1] = null;
		sink(1);
		return max;
	}
	
	private void swim(int k){
		while(k > 1 && less(k, k/2) ){
			exch(k, k/2);
			k = k/2;
		}
	}
	
	private void sink(int k){
		while(2*k <= N){
			int j = 2*k;
			if(j < N && less(j+1, j)) j++;
			if(!less(j, k)) break;
			exch(k, j);
			k = j;
		}
	}
	
	private boolean less(int i, int j){
		if(comparator == null)
			return ((Comparable<Key>)pq[i]).compareTo(pq[j]) < 0;
		else
			return comparator.compare(pq[i], pq[j]) < 0;
	}
	
	private void exch(int i, int j){
		Key t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
	}
	
	public boolean isEmpty(){
		return N == 0;
	}
	
	public int size(){
		return N;
	}
	
	private void insize(){
		if(N==pq.length-1)
			resize(2*N);
	}
	private void resize(int newLen){
		Key[]tmp = (Key[])new Object[newLen+1];
		for(int i = 0; i <= N; i++){
			tmp[i] = pq[i];
		}
		pq = tmp;
	}

	@Override
	public Iterator<Key> iterator() {
		return new PQIterator();
	}
	
	private class PQIterator implements Iterator<Key>{
		int pos = 1;
		
		@Override
		public boolean hasNext() {
			return pos <= N;
		}

		@Override
		public Key next() {
			return pq[pos++];
		}
	}
	
	public static boolean isMinPQ(Comparable[] pq){
		int N = pq.length - 1;
		
		for(int i = 0; 2*i+1 <= N; i++){
			if(pq[i].compareTo(pq[2*i+1]) > 0)
				return false;
			if(2*i+2>N)
				return true;
			if(pq[i].compareTo(pq[2*i+2]) > 0)
				return false;
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		MinPQ<Integer> pq = new MinPQ<>(10);
		pq.insert(3);
		pq.insert(4);
		pq.insert(1);
		
		System.out.println(pq.delMin());
		pq.insert(-1);
		System.out.println(pq.delMin());
	}
}
