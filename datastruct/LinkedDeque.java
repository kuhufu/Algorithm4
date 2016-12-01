package datastruct;
import java.util.Iterator;

public class LinkedDeque<E> implements Iterable<E>, Deque<E>{
	private Node<E> first;
	private Node<E> last;
	private int size;
	
	
	private class Node<E>{
		E item;
		Node<E> prev;
		Node<E> next;
		
		public Node(E item) {
			this.item = item;
		}
	}
	
	/* (non-Javadoc)
	 * @see t#pushLeft(E)
	 */
	@Override
	public void pushLeft(E item){
		Node<E> newNode = new Node<E>(item);
		if(isEmpty()){
			first = last = newNode;
		}else{
			newNode.next = first;
			first.prev = newNode;
			first = newNode;
		}
		size++;
	}
	
	/* (non-Javadoc)
	 * @see t#popLeft()
	 */
	@Override
	public E popLeft(){
		if(isEmpty()) return null;
		E item = first.item;
		first = first.next;
		if(first == null) last = null;
		else{
			first.prev.next = null;
			first.prev = null;
		}
		size--;
		return item;
	}
	
	/* (non-Javadoc)
	 * @see t#pushRight(E)
	 */
	@Override
	public void pushRight(E item){
		Node<E> newNode = new Node<E>(item);
		if(isEmpty()){
			first = last = newNode;
		}else{
			newNode.prev = last;
			last.next = newNode;
			last = newNode;
		}
		size++;
	}
	
	/* (non-Javadoc)
	 * @see t#popRight()
	 */
	@Override
	public E popRight(){
		if(isEmpty()) return null;
		E item = last.item;
		last = last.prev;
		if(last == null) first = null;
		else{
			last.next.prev = null;
			last.next = null;
		}
		size--;
		return item;
	}
	
	/* (non-Javadoc)
	 * @see t#isEmpty()
	 */
	@Override
	public boolean isEmpty(){
		return first == null;
	}
	

	@Override
	public int size(){
		return size;
	}
	
	@Override
	public Iterator<E> iterator() {
		return new LinkedIterator();
	}
	
	private class LinkedIterator implements Iterator<E>{
		Node<E> pos = first;
		
		@Override
		public boolean hasNext() {
			return pos != null;
		}

		@Override
		public E next() {
			E item = pos.item;
			pos = pos.next;
			return item;
		}
		
	}
	
}
