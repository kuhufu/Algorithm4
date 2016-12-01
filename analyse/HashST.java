package analyse;

import edu.princeton.cs.algs4.*;

public class HashST<Key, Value>{
	private static int MIN_CAP = 16;
	
	private int N;
	private int M;
	Key[] keys;
	Value[] vals;
	
	public HashST() {
		this(MIN_CAP);
	}
	
	public HashST(int cap) {
		keys = (Key[]) new Object[cap];
		vals = (Value[]) new Object[cap];
		M = cap;
	}
	
	private int hash(Key key){
		return (key.hashCode() & 0x7fffffff) % M;
	}

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
	
	public Value get(Key key) {
		if(key == null || isEmpty()) return null;
		
		for(int i = hash(key); keys[i] != null; i = (i + 1) % M){
			if(key.equals(keys[i])){
				return vals[i];
			}
		}
		return null;
	}
	
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
		if(cap < MIN_CAP) return;
		
		HashST<Key, Value> t;
		t = new HashST<>(cap);
		
		for(int i = 0; i < M; i++){
			if(keys[i] != null)
				t.put(keys[i], vals[i]);
		}
		
		keys = t.keys;
		vals = t.vals;
		M = t.M;
	}
	
	public boolean isEmpty() {
		return N == 0;
	}

	public int size() {
		return N;
	}

	public Iterable<Key> keys() {
		Queue<Key> q = new Queue<>();
		for(int i = 0; i < M; i++){
			if(keys[i] != null)
				q.enqueue(keys[i]);
		}
		return q;
	}

	public static void main(String[] args) {
		HashST<String , Integer> st;
		st = new HashST<>();
		
		Stopwatch timer = new Stopwatch();
		for(int i = 0; i < 4000000; i++){
			st.put(Integer.toString(i), i);
		}
		System.out.println(timer.elapsedTime());
	}
}
