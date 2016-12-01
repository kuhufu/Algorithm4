package analyse;

public class HashSET<Key> {
	private HashST<Key, Integer> st;
	
	public HashSET() {
		st = new HashST<>();
	}
	
	public void add(Key key){
		if(key == null) throw new NullPointerException();
		st.put(key, 0);
	}
	
	public void delete(Key key){
		if(key == null) throw new NullPointerException();
		st.delete(key);
	}
	
	public boolean contains(Key key){
		return st.contains(key);
	}
	
	public int size(){
		return st.size();
	}
	
	public boolean isEmpty(){
		return st.isEmpty();
	}
	
	public String toString(){
		String s = "";
		for (Key  e : st.keys()) {
			s += ", " + e.toString();
		}
		return s.substring(2);
	}
}
