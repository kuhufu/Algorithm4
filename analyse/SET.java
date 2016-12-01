package analyse;

public class SET<Key extends Comparable<Key>> {
	ST<Key, Integer> st;
	
	public SET() {
		st = new ST<>();
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
	
	public Iterable<Key> items(){
		return st.keys();
	}
	
	public String toString(){
		String s = "";
		for (Key  e : st.keys()) {
			s += ", " + e.toString();
		}
		return s.substring(2);
	}
}
