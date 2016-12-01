package search;

public interface BSTST<Key, Value> extends ST<Key, Value> {

	void deleteMax();

	void deleteMin();

	Key min();

	Key max();

	Key floor(Key key);

	Key ceiling(Key key);

	Key select(int k);

	int rank(Key key);

	int height();

	Iterable<Key> keys();

	Iterable<Key> keys(Key lo, Key hi);
	
	void draw();

}