package dirgraph;

import edu.princeton.cs.algs4.Stack;

public class DepthFirstDirectedPaths {
	private boolean[] marked;
	private int[] edgeTo;
	private int s;
	
	public DepthFirstDirectedPaths(Dirgraph g, int s) {
		marked = new boolean[g.V()];
		edgeTo = new int[g.V()];
		this.s = s;
		dfs(g, s);
	}
	
	private void dfs(Dirgraph g, int v){
		marked[v] = true;
		
		for (int w : g.adj(v)) {
			if(!marked[w]){
				edgeTo[w] = v;
				dfs(g,w);
			}
		}
	}
	
	public boolean hasPathTo(int v){
		return marked[v];
	}
	
	public Iterable<Integer> pathTo(int v){
		if(!hasPathTo(v)) return null;
		
		Stack<Integer> stack = new Stack<>();
		for(int x = v; x != s; x = edgeTo[x]){
			stack.push(x);
		}
		return stack;
	}
}
