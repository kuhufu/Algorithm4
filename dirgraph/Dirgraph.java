package dirgraph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class Dirgraph {
	private int V;
	private int E;
	private Bag<Integer>[] adj;
	
	public Dirgraph(int v) {
		this.V = v;
		this.E = 0;
		adj = (Bag<Integer>[]) new Bag[v];
		for(int i = 0; i < adj.length; i++){
			adj[i] = new Bag<>();
		}
	}
	
	public Dirgraph(In in) {
		this(in.readInt());
		int e = in.readInt();
		
		for(int i = 0; i < e; i++){
			int v = in.readInt();
			int w = in.readInt();
			addEdge(v, w);
		}
	}
	
	public Dirgraph(Dirgraph g) {
		this(g.V());
		for(int i = 0; i < V; i++){
			for (int w : g.adj[i]) {
				adj[i].add(w);
			}
		}
	}
	
	public int V(){
		return V;
	}
	
	public int E(){
		return E;
	}
	
	public void addEdge(int v, int w){
		adj[v].add(w);
		E++;
	}
	
	public boolean hasEdge(int v, int w){
		for (int i : adj(v)) {
			if(i == w) return true;
		}
		return false;
	}
	
	public Iterable<Integer> adj(int v){
		return adj[v];
	}
	
	public Dirgraph reverse(){
		Dirgraph r = new Dirgraph(V);

		for(int i = 0; i < V; i++){
			for (int j : adj[i]) {
				r.addEdge(j, i);
			}
		}
		
		return r;
	}
	
	public String toString(){
		String s = V + " vertices, " + E + " Edges\n";
		for(int v = 0; v < V(); v++){
			s += v + ": ";
			for (int w : this.adj(v)) {
				s += w + " ";
			}
			s += "\n";
		}
		return s;
	}
}
