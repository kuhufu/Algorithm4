package search;

import java.util.*;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class SeperateChainingHashST<Key extends Comparable<Key>, Value> implements ST<Key, Value> {
	private static final int MIN_SIZE = 13;
	
	private int M;
	private int N;
	private static int[] primes = {
			31, 61, 
			127, 251, 509,
			1021, 2039, 4093, 8191, 
			16381, 32749, 65521,
			131071, 262139, 524287,
			1048573, 2097143, 4194301, 8388593,
			16777213, 33554393, 67108859, 1342217689,
			268435399, 536870909, 1073741789, 2147483647};
	
	private SequentialSearchST<Key, Value>[] st;

	public SeperateChainingHashST() {
		this(MIN_SIZE);
	}
	
	public SeperateChainingHashST(int M) {
		this.M = M;
		st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
		for(int i = 0; i < M; i++){
			st[i] = new SequentialSearchST();
		}
	}
	
	private int hash(Key key){
		return (key.hashCode() & 0x7fffffff) % M;
	}
	
	@Override
	public void put(Key key, Value val) {
		if(!contains(key)){
			N++;
		}
		if(N/M >= 4) resize(M*2);
		int i = hash(key);
		st[i].put(key, val);
	}

	@Override
	public Value get(Key key) {
		int i = hash(key);
		return st[i].get(key);
	}

	@Override
	public void delete(Key key) {
		if(contains(key)){
			N--;
		}
		if(N > 0 && N/M <= 2) resize(M/2);
		int i = hash(key);
		st[i].delete(key);
	}

	@Override
	public boolean contains(Key key) {
		if(key == null) return false;
		int i = hash(key);
		return st[i].contains(key);
	}
	
	private void resize(int length){
		if(length < MIN_SIZE) return;
		
		SeperateChainingHashST<Key, Value> t;
		t = new SeperateChainingHashST<>(length);
		for(int i = 0; i < M; i++){
			for (Key key : st[i].keys()) {
				t.put(key, this.get(key));
			}
		}
		
		M = t.M;
		st = t.st;
	}

	@Override
	public boolean isEmpty() {
		return N == 0;
	}

	@Override
	public int size() {
		return N;
	}

	@Override
	public Iterable<Key> keys() {
		Queue<Key> q = new Queue<>();
		
		for(int i = 0; i < M; i++){
			for (Key key : st[i].keys()) {
				q.enqueue(key);
			}
		}
		return q;
	}

	public static void main(String[] args) {
		SeperateChainingHashST<Integer, Integer> st;
		st = new SeperateChainingHashST<>();
		
		int N = 1000_0000;
		ArrayList<Integer> ls = new ArrayList<>();
		for(int i = 0; i < N; i++){
			ls.add(StdRandom.uniform(N));
		}
		Collections.shuffle(ls);
		
		Stopwatch timer = new Stopwatch();
		for (Integer e : ls) {
			st.put(e, e);
		}
		System.out.println(timer.elapsedTime());
		
		//JavaµÄHashMap
		HashMap<Integer, Integer> hp = new HashMap<>();
		Stopwatch timer2 = new Stopwatch();
		for (Integer e : ls) {
			hp.put(e, e);
		}
		System.out.println(timer2.elapsedTime());
	}

}
