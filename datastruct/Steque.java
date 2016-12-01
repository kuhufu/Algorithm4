package datastruct;
public class Steque<E>{
	private Node<E> first;
	private Node<E> last;
	private int size = 0;
	
	private class Node<E>{
		E item;
		Node<E> prev;
		Node<E> next;
		
		Node(E item, Node<E> prev, Node<E> next){
			this.item = item;
			this.prev = prev;
			this.next = next;
		}
	}
	
	public void push(E item){
		Node<E> newNode = new Node<E>(item, null, null);
		if(isEmpty()) {
			first = last = newNode;
		}else{
			newNode.next = first;
			first.prev = newNode;
			first = newNode;
		}	
		size++;
	}
	
	public E pop(){
		if(isEmpty()) return null;
		E item = first.item;
		first = first.next;
		if(first==null) last = null;
		size--;
		return item;
	}
	
	public void enqueue(E item){
		Node<E> newNode = new Node<E>(item, null, null);
		if(isEmpty()) {
			first = last = newNode;
		}else{
			newNode.prev = last;
			last.next = newNode;
			last = newNode;	
		}
		size++;
	}
	
	public void catenation(Steque<E> s){
		if(s.isEmpty() || s == null) return;
		if(isEmpty()){
			first = s.first;
			last = s.last;
		}else{
			s.first.prev = last;
			last.next = s.first;
			last = s.last;
		}
		
		
	}
	
	public boolean isEmpty(){
		return first == null;
	}
	
	public int size(){
		return size();
	}
	
	public String toString(){
		String s = "";
		for(Node<E> f = first; f != null; f = f.next){
			s += " " + f.item;
		}
		
		return s.trim();
	}
}
