package graph;

import edu.princeton.cs.algs4.In;

public class DepthFirstSearch {
	private int count;
	private boolean[] marked;
	
	DepthFirstSearch(Graph g, int s){
		this.marked = new boolean[g.V()];
		dfs(g, s);
	}
	
	private void dfs(Graph g, int v){
		marked[v] = true;
		count++;
		for (int w : g.adj(v)) {
			if(!marked[w]){
				dfs(g, w);
			}
		}
	}
	
	public boolean marked(int v){
		return marked[v];
	}
	
	public int count(){
		return count;
	}
	
	public static boolean isConnected(Graph g){
		DepthFirstSearch search = new DepthFirstSearch(g, 0);
		if(search.count() == g.V()) return true;
		return false;
	}
	
	public static void main(String[] args) {
		String filename = "data/tinyG.txt";
		Graph g = new Graph(new In(filename));
		int s = 9;
		DepthFirstSearch search = new DepthFirstSearch(g, s);

		for(int v = 0; v < g.V(); v++){
			if(search.marked(v)){
				System.out.print(v + " ");
			}
		}
		System.out.println();
		
		if(search.count() != g.V())
			System.out.print("Not ");
		System.out.println("connected");
	}
}
