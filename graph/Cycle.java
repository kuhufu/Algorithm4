package graph;

import edu.princeton.cs.algs4.In;

public class Cycle {
	private boolean[] marked;
	private boolean hasCycle;
	
	public Cycle(Graph g) {
		marked = new boolean[g.V()];
		for(int v = 0; v < g.V(); v++){
			if(!marked[v])
				dfs(g, v, v);
		}
	}
	
	private void dfs(Graph g, int v, int u){
		marked[v] = true;
		
		for (int  w : g.adj(v)) {
			if(!marked[w])
				dfs(g, w, v);
			else if(w != u)
				hasCycle = true;
		}
	}
	
	public boolean hasCycle(){
		return hasCycle;
	}
	
	public static void main(String[] args) {
		String filename = "data/tinyG.txt";
		Graph g = new Graph(new In(filename));
		
		Cycle c = new Cycle(g);
		System.out.println(c.hasCycle());
		
		
	}
}
