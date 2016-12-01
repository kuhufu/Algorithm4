package graph;

import java.util.Collection;

import analyse.HashST;
import edu.princeton.cs.algs4.*;

public class StringGraph{
	private int V;
	private int E;
	HashST<String, Bag<String>> st;
	
	public StringGraph(int V) {
		this.V = V;
		this.E = 0;
	}
	
	public StringGraph(In in){
		this(in.readInt());
		int E = in.readInt();
		
		for(int i = 0; i < E; i++){
			String v = in.readString();
			String w = in.readString();
			addEdge(v, w);
		}
	}
	
	public int V(){
		return V;
	}
	
	public int E(){
		return E;
	}
	
	public Iterable<String> adj(String v){
		return st.get(v);
	}
	
	public void addEdge(String v, String w){
		if(v == w) return; 		//不允许存在自环
		for (String e : st.get(v)) {
			if(e == w) return;	//不允许存在平行边
		}
		
		st.get(v).add(w);
		st.get(w).add(v);
		E++;
	}
	
	public boolean hasEdge(String v, String w){
		for(String e : st.get(v)){
			if(e == w) return true;
		}
		return false;
	}
	
	public static int degree(StringGraph g, String v){
		int degree = 0;
		for (String w : g.adj(v)) {
			degree++;
		}
		return degree;
	}
	
	public static int maxDegree(StringGraph g){
		int max = 0;
		for (String key : g.st.keys()) {
			if(degree(g, key) > max)
				max = degree(g, key);
		}
		return max;
	}
	
	public static double avgDegree(StringGraph g){
		return 2.0 * g.E() / g.V();
	}
	
	public static int numberOfSelfLoops(StringGraph g){
		int count = 0;
		
		for (String v : g.st.keys()) {
			for (String w : g.adj(v)) {
				if(w == v) count++;
			}
		}
		return count;
	}
	
	public String toString(){
		String s = V + " vertices, " + E + " Edges\n";
		
		for (String v : this.st.keys()) {
			s += v + ": ";
			for (String w : this.adj(v)) {
				s += w + " ";
			}
			s += "\n";
		}
		return s;
	}
	
	public static void main(String[] args) {
		
	}
}
