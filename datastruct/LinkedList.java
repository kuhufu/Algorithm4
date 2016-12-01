package datastruct;

public class LinkedList<Item> {
	private Node first;
	private Node last;
	private int size;
	
	private class Node{
		Item item;
		Node prev;
		Node next;
	}
	
	public void insertFirst(Item item){
		Node newNode = new Node();
		newNode.item = item;
		if(isEmpty()){
			first = last = newNode;
			return;
		}
		first.prev = newNode;
		newNode.next = first;
		first = newNode;
	}
	
	public void insertLast(Item item){
		Node newNode = new Node();
		newNode.item = item;
		if(isEmpty()){
			first = last = newNode;
			return;
		}
		
		last.next = newNode;
		newNode.prev = last;
		last = newNode;
	}
	
	public Item removeFirst(){
		if(isEmpty()) return null;
		if(first.next == null){
			Item item = first.item;
			first = null;
			last = null;
			return item;
		}
		Item item = first.item;
		first.next.prev = null;
		first = first.next;
		return item;
	}
	
	public Item removeLast(){
		if(isEmpty()) return null;
		if(last.prev == null){
			Item item = last.item;
			last = null;
			first = null;
			return item;
		}
		Item item = first.item;
		last.prev.next = null;
		last = last.prev;
		return item;
	}
	
	public void insertBefore(Node node, Item item){
		Node newNode = new Node();
		newNode.item = item;
		if(node == null) return;
		if(node == first) {
			insertFirst(item);
			return;
		}
		
		newNode.next = node;
		newNode.prev = node.prev;
		node.prev.next = newNode;
		node.prev = newNode;	
	}
	
	public void insertAfter(Node node, Item item){
		Node newNode = new Node();
		newNode.item = item;
		if(node == null) return;
		if(node == last) {
			insertLast(item);
			return;
		}
		
		newNode.prev = node;
		newNode.next = node.next;
		node.next.prev = newNode;
		node.next = newNode;	
	}
	
	public boolean removeAfter(Node node){
		return false;
	}
	
	public boolean isEmpty(){
		return first == null;
	}
	
	public String toString(){
		String s = "";
		for(Node f = first; f != null; f = f.next){
			s += " " + f.item;
		}
		
		return s;
	}
}
