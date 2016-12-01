package datastruct;
import java.util.Iterator;

import edu.princeton.cs.algs4.Stopwatch;

public class ArrayStackOfInt implements Iterable<Integer>{
	private int[] a;
	private int size = 0;
	
	public ArrayStackOfInt(int cap) {
		a = new int[cap];
	}
	
	public void push(int s){
		if(size == a.length) resize(2 * a.length);
		a[size++] = s;
	}
	
	public int pop(){
		int item = a[--size];
		if(size > 0 && size < a.length/4) resize(a.length/2);
		return item;
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	public int size(){
		return size;
	}
	
	private void resize(int newLen){
		int[]tmp = new int[newLen];
		for(int i = 0; i < size; i++){
			tmp[i] = a[i];
		}
		a = tmp;
	}

	@Override
	public Iterator iterator() {
		return new ReverseArrayIterator();
	}
	
	private class ReverseArrayIterator implements Iterator<Integer>{
		private int i = size;
		
		@Override
		public boolean hasNext() {
			if(i > 0) return true;
			return false;
		}

		@Override
		public Integer next() {
			return a[--i];
		}
	}
	
	public static void main(String[] args) {
		final int capcity = 500_0000;
		
		ArrayStack<Integer> fs = new ArrayStack<>(capcity);
		ArrayStackOfInt fsi = new ArrayStackOfInt(capcity);
		
		Stopwatch t1 = new Stopwatch();
		for(int i = 0; i< capcity; i++){
			fs.push(i);
		}
		System.out.println(t1.elapsedTime());
		
		Stopwatch t2 = new Stopwatch();
		for(int i = 0; i< capcity; i++){
			fsi.push(i);
		}
		System.out.println(t2.elapsedTime());
		
		for (Integer integer : fsi) {
			
		}
	}
}
