package graph;

import analyse.ST;
import edu.princeton.cs.algs4.In;

public class SymbolGraph {
	
	private ST<String ,Integer> st;
	private String[] keys;
	private Graph g;
	
	public SymbolGraph(String stream, String sp) {
		//符号名——索引
		st = new ST<>();
		In in = new In(stream);
		while(in.hasNextLine()){
			String[] a = in.readLine().split(sp);
			for (String e : a) {
				if(!st.contains(e))
					st.put(e, st.size());
			}
		}
		
		//索引——符号名
		keys = new String[st.size()];
		for (String s : st.keys()) {
			keys[st.get(s)] = s;
		}
		
		//构造图
		g = new Graph(st.size());
		in = new In(stream);	
		while(in.hasNextLine()){
			String[] a = in.readLine().split(sp);
			int v = st.get(a[0]);
			for(int i = 1; i < a.length; i++){
				g.addEdge(v, st.get(a[i]));
			}
		}
	}
	
	public boolean contains(String s){
		return st.contains(s);
	}
	
	public int index(String s){
		return st.get(s);
	}
	
	public String name(int v){
		return keys[v];
	}
	
	public Graph G(){
		return g;
	}
	
}
