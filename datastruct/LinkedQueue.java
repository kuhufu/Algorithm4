package datastruct;
import java.util.Iterator;

import org.omg.Messaging.SyncScopeHelper;

public class LinkedQueue<Item> implements Iterable<Item>{
	private Node<Item> first;
	private Node<Item> last;
	private int size;
	
	private class Node<Item>{
		Item item;
		Node<Item> next;
	}
	
	public LinkedQueue() {
	}
	
	public LinkedQueue(LinkedQueue<Item> q) {
		for (Item item : q) {
			enqueue(item);
		}
	}
	
	public void enqueue(Item item){
		Node<Item> newNode = new Node<Item>();
		newNode.item = item;
		if(isEmpty()){
			first = last = newNode;
			return;
		}
		last.next = newNode;
		last = newNode;
		
		size++;
	}
	
	public Item dequeue(){
		if(first == null) return null;
		
		Item item = first.item;
		first = first.next;
		if(isEmpty()) last = null;
		size--;
		return item;
	}
	
	public int size(){
		return size;
	}
	
	public boolean isEmpty(){
		return first == null;
	}

	@Override
	public Iterator<Item> iterator() {
		return new LinkedIterator();
	}
	
	private class LinkedIterator implements Iterator<Item>{
		Node<Item> pos = first;
		
		@Override
		public boolean hasNext() {
			return pos != null;
		}

		@Override
		public Item next() {
			Item item = pos.item;
			pos = pos.next;
			return item;
		}
	}
	
	public static void main(String[] args) {
		LinkedQueue<String> q = new LinkedQueue<>();
		q.enqueue("a");
		q.enqueue("b");
		
		for (String string : q) {
			System.out.println(string);
		}
	}
		
}
