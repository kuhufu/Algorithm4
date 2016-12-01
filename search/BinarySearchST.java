package search;

import java.util.Arrays;
import java.util.HashMap;
import java.util.NoSuchElementException;

import datastruct.ArrayStack;

public class BinarySearchST<Key extends Comparable<Key>, Value> implements ST<Key, Value> {
	private Key[] keys;
	private Value[] vals;
	private int size;
	
	public BinarySearchST() {
		this(10);
	}

	public BinarySearchST(int cap) {
		keys = (Key[])new Comparable[cap];
		vals = (Value[])new Object[cap];
	}
	
	
	@Override
	public void put(Key key, Value val) {
		if(key == null) throw new NullPointerException("key must be not null");
		if(val == null) throw new NullPointerException("val must be not null");
		
		if(size == keys.length) resize(2 * size);
		
		//greater than all keys
		if(size != 0 && key.compareTo(keys[size-1]) > 0){
			keys[size] = key;
			vals[size] = val;
			size++;
		}
		
		int i = rank(key);
		if(i < size && keys[i].compareTo(key) == 0) {
			vals[i] = val;
			return;
		}
		
		for(int j = size; j > i; j--){
			keys[j] = keys[j-1];
			vals[j] = vals[j-1];
		}
		
		keys[i] = key;
		vals[i] = val;
		size++;
	} 

	@Override
	public Value get(Key key) {
		//if(key == null) throw new NullPointerException("key must be not null");
		int i = rank(key);
		if(i < size && keys[i].compareTo(key) == 0) return vals[i];
		return null;
	}

	@Override
	public void delete(Key key) {
		if(key == null) throw new NullPointerException("key can not be null");
		if(isEmpty()) return;
		
		int i = rank(key);
		if(i == size || keys[i].compareTo(key) != 0){
			return;
		}
		
		for(int j = i; j < size - 1; j++){
			keys[j] = keys[j+1];
			vals[j] = vals[j+1];
		}
		
		size--;
		keys[size] = null;
		vals[size] = null;
		
		if(size > 0 && size == keys.length/4) resize(keys.length/2);
	}

	@Override
	public boolean contains(Key key) {
		return get(key) != null;
	}
	
	private int rank(Key key){
		int lo = 0;
		int hi = size-1;
		while(lo <= hi){
			int mid = (lo + hi)/2;
			int cmp = key.compareTo(keys[mid]);
			if		(cmp > 0) lo = mid + 1;
			else if	(cmp < 0) hi = mid - 1;
			else return mid;
		}
		return lo;
	}
	
	private void resize(int newLen){
		keys = Arrays.copyOf(keys, newLen);
		vals = Arrays.copyOf(vals, newLen);
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}
	
	public int size(Key lo, Key hi){
        if (lo == null) throw new NullPointerException("first argument to size() is null"); 
        if (hi == null) throw new NullPointerException("second argument to size() is null");
        
        if(lo.compareTo(hi) > 0) return 0;
        if(contains(hi)) 	return rank(hi) - rank(lo) + 1;
        else 				return rank(hi) - rank(lo);
	}

	@Override
	public Iterable<Key> keys() {
		ArrayStack<Key> s = new ArrayStack<>(size);
		for(int i = size-1; i >= 0; i--){
			s.push(keys[i]);
		}
		return s; 
	}
	
	public Key min(){
		if(isEmpty()) return null;
		return keys[0];
	}
	
	public Key max(){
		if(isEmpty()) return null;
		return keys[size-1];
	}
	
	public void deleteMin(){
		if (isEmpty()) throw new NoSuchElementException("Symbol table underflow error");
		delete(min());
	}
	
	public void deleteMax(){
		if (isEmpty()) throw new NoSuchElementException("Symbol table underflow error");
		delete(max());
	}
	
	public Key select(int k){
		return keys[k];
	}
	
	public Key ceiling(Key key){
		int i = rank(key);
		if(i == 0 || i == size) return null;
		return keys[i];
	}
	
	public Key floor(Key key){
		int i = rank(key);
		
		if(i == 0) 	
			return null;
		else if(i < size && key.compareTo(keys[i]) == 0) 
			return keys[i];	
		else 
			return keys[i-1];
	}
	
	public static void main(String[] args) {
		HashMap<String, Integer> mp = new HashMap<>();
		BinarySearchST<String, Integer> st = new BinarySearchST<>();
		st.put("f", 4);
		st.put("d", 5);
		st.put("a", 1);
		st.put("c",	3);
		//st.put("b", 2);
		System.out.println(st.floor("e"));
		
		for (String key: st.keys()) {
			//System.out.println(st.get(key));
		}
		

	}
}
