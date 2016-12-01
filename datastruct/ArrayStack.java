package datastruct;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayStack<Item> implements Iterable<Item>{
	private Item[] a;
	private int size;
	
	public ArrayStack() {
		this(10);
	}
	
	public ArrayStack(int cap) {
		a = (Item[])new Object[cap];
	}
	
	public void push(Item s){
		if(size == a.length) resize(2 * a.length);
		a[size++] = s;
	}
	
	public Item pop(){
		if(size == 0) throw new NoSuchElementException();
		Item item = a[--size];
		a[size] = null;
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
		a = Arrays.copyOf(a, newLen);
	}

	@Override
	public Iterator<Item> iterator() {
		return new ArrayIterator();
	}
	
	private class ArrayIterator implements Iterator<Item>{
		private int i = size;
		
		@Override
		public boolean hasNext() {
			if(i > 0) return true;
			return false;
		}

		@Override
		public Item next() {
			return a[--i];
		}
		
	}
}
