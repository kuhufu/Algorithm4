package graph;

import edu.princeton.cs.algs4.*;
import graph.Graph;

public class DepthFirstPaths {
	private boolean[] marked;
	private int[] edgeTo;
	private int s;
	
	public DepthFirstPaths(Graph g, int s) {
		this.s = s;
		marked = new boolean[g.V()];
		edgeTo = new int[g.V()];
		dfs(g, s);
	}
	
	private void dfs(Graph g, int v){
		marked[v] = true;
		for (int w : g.adj(v)) {
			if(!marked[w]){
				edgeTo[w] = v;
				dfs(g, w);
			}
		}
	}
	
	private void dfsnr(Graph g, int v){
		Stack<Integer> stack = new Stack<>();
		stack.push(v);
		while(!stack.isEmpty()){
			int s = stack.pop();
			boolean flag = true;
			if(!marked[s]){
				marked[s] = true;
				for (int w : g.adj(s)) {
					if(!marked[w]){
						if(flag){
							edgeTo[w] = s;
							flag = false;
						}
						stack.push(w);
						break;
					}
				}
			}
		}
	}

	public boolean hasPathto(int v) {
		return marked[v];
	}

	public Iterable<Integer> pathTo(int v) {
		if(!hasPathto(v)) return null;
		
		Stack<Integer> stack = new Stack<>();
		for(int x = v; x != s; x = edgeTo[x]){
			stack.push(x);
		}
		stack.push(s);
		return stack;
	}

	public static void main(String[] args) {
		String filename = "data/tinyG.txt";
		Graph g = new Graph(new In(filename));
		int s = 0;
		DepthFirstPaths paths = new DepthFirstPaths(g, s);
		
		for (Integer e : paths.pathTo(5)) {
			System.out.println(e + " ");
		}
	}
}
