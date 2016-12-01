package datastruct;

public interface Deque<E> {

	void pushLeft(E item);

	E popLeft();

	void pushRight(E item);

	E popRight();

	boolean isEmpty();

	int size();

}