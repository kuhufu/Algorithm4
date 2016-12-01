package datastruct;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class LinkedStack<Item> implements Iterable<Item>{
	private Node first;
	private int size;
	private int moCount;
	
	private class Node{
		Item item;
		Node next;
	}
	
	public void push(Item item){
		Node newNode = new Node();
		newNode.next = first;
		newNode.item = item;
		first = newNode;
		size++;
		moCount++;
	}
	
	public Item pop(){
		if(isEmpty()) return null;
		Node old = first;
		first = first.next;
		size--;
		moCount++;
		return old.item;
	}
	
	public Item peek(){
		return isEmpty() ? null : first.item;
	}
	
	public void reverse(){
		Node prev = first;
		Node reverse = null;
		
		while(prev != null){
			Node curr = prev.next;
			prev.next = reverse;
			reverse = prev;
			prev = curr;
		}
		first = reverse;
	}
	
	public boolean isEmpty(){
		return first == null;
	}
	
	public int size(){
		return size;
	}

	@Override
	public Iterator<Item> iterator() {
		return new LinkedIterator();
	}
	
	private class LinkedIterator implements Iterator<Item>{
		private Node current = first;
		private int count = moCount;
		
		@Override
		public boolean hasNext() {
			checkModified();
			return current != null;
		}

		@Override
		public Item next() {
			checkModified();
			Item item = current.item;
			current = current.next;
			return item;
		}
		
		private void checkModified(){
			if(count != moCount)
				throw new ConcurrentModificationException();
		}
	}
}
