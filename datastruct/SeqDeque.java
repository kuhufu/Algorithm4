package datastruct;

public class SeqDeque<E> implements Deque<E>{
	private int first;
	private int last;
	private int size;
	private E[] s;
	
	public SeqDeque(int len) {
		len = len > 1 ? len : 2;
		s = (E[])new Object[len];
		last = len/2;
		first = last-1;
	}
	
	@Override
	public void pushLeft(E item) {
		if(first == 0){
			resize(2 * s.length, s.length);
			first = s.length/2;
			last += s.length/2;
		}
		s[first--] = item;
		size++;
	}
	@Override
	public E popLeft() {
		if(isEmpty()) return null;
		E item = s[++first];
		//first++;
		size--;
		return item;
	}
	@Override
	public void pushRight(E item) {
		if(last == s.length-1) resize(2 * s.length, 0);
		s[last++] = item;
	}
	@Override
	public E popRight() {
		if(isEmpty()) return null;
		E item = s[last];
		last--;
		size--;
		return item;
	}
	@Override
	public boolean isEmpty() {
		return first == last;
	}
	
	@Override
	public int size() {
		return size;
	}
	
	public int capcity(){
		return s.length;
	}
	
	private void resize(int len, int pos){
		E[] newS = (E[]) new Object[len];
		for(int i = 0; i < s.length; i++){
			newS[pos + i] = s[i];
		}
		s = newS;
	}
	
}
