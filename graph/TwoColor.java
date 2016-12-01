package graph;

import edu.princeton.cs.algs4.In;

public class TwoColor {
	private boolean[] marked;
	private boolean[] color;
	private boolean isTwoColor = true;
	
	public TwoColor(Graph g) {
		marked = new boolean[g.V()];
		color = new boolean[g.V()];
		
		for(int v = 0; v < g.V(); v++){
			if(!marked[v])
				dfs(g, v);
		}
	}
	
	private void dfs(Graph g, int v){
		marked[v] = true;
		
		for (int  w : g.adj(v)) {
			if(!marked[w]){
				color[w] = !color[v];
				dfs(g, w);
			}else if(color[w] == color[v])
				isTwoColor = false;
			
		}
	}
	
	public boolean isBipartite(){
		return isTwoColor;
	}
	
	public static void main(String[] args) {
		String filename = "data/tinyG.txt";
		Graph g = new Graph(new In(filename));
		
		TwoColor c = new TwoColor(g);
		System.out.println(c.isBipartite());
		
	}
}
