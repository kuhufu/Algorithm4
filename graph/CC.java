package graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

public class CC {
	private boolean[] marked; 
	private int[] id;
	private int count;
	
	public CC(Graph g) {
		marked = new boolean[g.V()];
		id = new int[g.V()];
		
		for(int i = 0; i < g.V(); i++){
			if(!marked[i]){
				dfs(g, i);
				count++;
			}
		}
	}
	
	private void dfs(Graph g, int v){
		marked[v] = true;
		id[v] = count;
		
		for (int w : g.adj(v)) {
			if(!marked[w]){
				dfs(g, w);
			}
		}
	}
	
	public boolean connected(int v, int w){
		return id[v] == id[w];
	}
	
	public int count(){
		return count;
	}
	
	public int id(int v){
		return id[v];
	}
	
	public static void main(String[] args) {
		String filename = "data/tinyG.txt";
		Graph g = new Graph(new In(filename));
		CC cc = new CC(g);
		
		int M = cc.count();
		Bag<Integer>[] components = (Bag<Integer>[])new Bag[M];
		for(int i = 0; i < M; i++){
			components[i] = new Bag();
		}
		
		for(int i = 0; i < g.V(); i++){
			components[cc.id(i)].add(i);
		}
		
		System.out.println("components: " + M);
		for (Bag<Integer> bag : components) {
			for (Integer v : bag) {
				System.out.print(v + " ");
			}
			System.out.println();
		}
	}
}
