package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class LinearProbingHashST<Key, Value> implements ST<Key, Value> {
	private static final int MIN_SIZE = 16;
	
	private int N;
	private int M;
	Key[] keys;
	Value[] vals;
	
	public LinearProbingHashST() {
		this(MIN_SIZE);
	}
	
	public LinearProbingHashST(int cap) {
		keys = (Key[]) new Object[cap];
		vals = (Value[]) new Object[cap];
		M = cap;
	}
	
	private int hash(Key key){
		return (key.hashCode() & 0x7fffffff) % M;
	}

	@Override
	public void put(Key key, Value val) {
		if(N >= M/2) resize(2*M);
		
		int i;
		for(i = hash(key); keys[i] != null; i = (i + 1) % M){
			if(key.equals(keys[i])){
				vals[i] = val;
				return;
			}
		}
		keys[i] = key;
		vals[i] = val;
		N++;
	}

	@Override
	public Value get(Key key) {
		if(key == null || isEmpty()) return null;
		
		for(int i = hash(key); keys[i] != null; i = (i + 1) % M){
			if(key.equals(keys[i])){
				return vals[i];
			}
		}
		return null;
	}

	@Override
	public void delete(Key key) {
		if(!contains(key)) return;
		
		int i = hash(key);
		while(!key.equals(keys[i])){
			i = (i + 1) % M;
		}
		keys[i] = null;
		vals[i] = null;
		
		for(; keys[i] != null; i = (i + 1) % M){
				Key oldkey = keys[i];
				Value oldval = vals[i];
				keys[i] = null;
				vals[i] = null;
				N--;
				put(oldkey, oldval);
		}
		
		N--;
		if(N > 0 && N == M/8) resize(M/2);
	}

	@Override
	public boolean contains(Key key) {
		if(key == null || isEmpty()) return false;
		
		for(int i = hash(key); keys[i] != null; i = (i + 1) % M){
			if(key.equals(keys[i])){
				return true;
			}
		}
		return false;
	}
	
	private void resize(int cap){
		if(cap < MIN_SIZE) return;

		LinearProbingHashST<Key, Value> t;
		t = new LinearProbingHashST<>(cap);
		
		for(int i = 0; i < M; i++){
			if(keys[i] != null)
				t.put(keys[i], vals[i]);
		}
		
		keys = t.keys;
		vals = t.vals;
		M = t.M;
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
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {		
		LinearProbingHashST<Integer , Integer> st;
		st = new LinearProbingHashST<>();
		
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
