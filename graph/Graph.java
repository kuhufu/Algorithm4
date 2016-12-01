package graph;

import java.util.Collection;

import edu.princeton.cs.algs4.*;

public class Graph {
	private int V;
	private int E;
	private Bag<Integer>[] adj;
	Collection c;;
	
	public Graph(int V) {
		this.V = V;
		this.E = 0;
		adj = (Bag<Integer>[])new Bag[V];
		for(int i = 0; i < adj.length; i++){
			adj[i] = new Bag<>();
		}
	}
	
	public Graph(Graph g) {
		this.V = g.V;
		this.E = g.E;
		adj = (Bag<Integer>[]) new Bag[V];
		for(int i = 0; i < adj.length; i++){
			adj[i] = new Bag<>();
			for (Integer e : g.adj[i]) {
				adj[i].add(e);
			}
		}
	} 
	
	public Graph(In in){
		this(in.readInt());
		int E = in.readInt();
		
		for(int i = 0; i < E; i++){
			int v = in.readInt();
			int w = in.readInt();
			addEdge(v, w);
		}
	}
	
	public int V(){
		return V;
	}
	
	public int E(){
		return E;
	}
	
	public Iterable<Integer> adj(int v){
		return adj[v];
	}
	
	public void addEdge(int v, int w){
		if(v == w) return; 		//不允许存在自环
		for (int e : adj(v)) {
			if(e == w) return;	//不允许存在平行边
		}
		
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}
	
	public boolean hasEdge(int v, int w){
		for(Integer e : adj(v)){
			if(e == w) return true;
		}
		return false;
	}
	
	public static int degree(Graph g, int v){
		int degree = 0;
		for (int w : g.adj(v)) {
			degree++;
		}
		return degree;
	}
	
	public static int maxDegree(Graph g){
		int max = 0;
		for(int v = 0; v < g.V(); v++){
			if(degree(g, v)>max)
				max = degree(g, v);
		}
		return max;
	}
	
	public static double avgDegree(Graph g){
		return 2.0 * g.E() / g.V();
	}
	
	public static int numberOfSelfLoops(Graph g){
		int count = 0;
		for(int v = 0; v < g.V(); v++){
			for (int w : g.adj(v)) {
				if(w == v) count++;
			}
		}
		return count;
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
	
	public static void main(String[] args) {
		
	}
}
