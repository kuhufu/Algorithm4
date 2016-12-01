package search;

import datastruct.LinkedQueue;
import edu.princeton.cs.algs4.Stopwatch;

public class SequentialSearchST<Key, Value> implements ST<Key, Value> {
	private Node first;
	private int size;
	
	private class Node{
		Key key;
		Value value;
		Node next;
		
		public Node(Key key, Value value, Node next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}
	
	@Override
	public void put(Key key, Value val){
		if(key == null) throw new NullPointerException("key can not be null");
		if(val == null) throw new NullPointerException("val can not be null");
		for(Node x = first; x != null; x = x.next){
			if(x.key.equals(key)){
				x.value = val;
				return;
			}
		}
		first = new Node(key, val, first);
		size++;
	}

	@Override
	public Value get(Key key){
		if(key == null) throw new NullPointerException("key can not be null");
		for(Node x = first; x != null; x = x.next){
			if(x.key.equals(key)) return x.value;
		}
		return null;
	}

	@Override
	public void delete(Key key){
		if(key == null) throw new NullPointerException("key must be not null");
		if(first.key.equals(key)){ first = first.next; size--; return; }
		
		for(Node x = first, n = x.next; x != null && n != null; x = x.next, n = x.next){
			if(n.key.equals(key)){
				x.next = n.next;
				n.next = null;
				size--;
				return;
			}
		}
	}

	@Override
	public boolean contains(Key key){
		if(key == null) throw new NullPointerException("key must be not null");
		return get(key) != null;
	}

	@Override
	public boolean isEmpty(){
		return first == null;
	}

	@Override
	public int size(){
		return size;
	}
	
	@Override
	public Iterable<Key> keys(){
		LinkedQueue<Key> q = new LinkedQueue<>();
		for(Node x = first; x != null; x = x.next){
			q.enqueue(x.key);
		}
		return q;
	}
	
	public static void main(String[] args) {
		ST<String, Integer> st = new SequentialSearchST<>();
		st = new SequentialSearchST<>();
		
		Stopwatch timer = new Stopwatch();
		for(int i = 0; i < 100000; i++){
			st.put(Integer.toString(i), i);
		}
		System.out.println(timer.elapsedTime());
		
		st.delete("3");
		assert !st.contains("3");
		st.get("7");
	}
}
