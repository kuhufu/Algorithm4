package graph;

import edu.princeton.cs.algs4.*;
import graph.Graph;

public class BreadFirstPaths {
	private boolean[] marked;
	private int[] edgeTo;
	private int s;
	
	public BreadFirstPaths(Graph g, int s) {
		this.s = s;
		marked = new boolean[g.V()];
		edgeTo = new int[g.V()];
		for(int i = 0; i < edgeTo.length; i++){
			edgeTo[i] = i;
		}
		bfs(g, s);
	}
	
	private void bfs(Graph g, int s){
		Queue<Integer> queue = new Queue<>();
		marked[s] = true;
		queue.enqueue(s);
		
		while(!queue.isEmpty()){
			int v = queue.dequeue();
			for (Integer w : g.adj(v))
				if(!marked[w]){
					marked[w] = true;
					edgeTo[w] = v;
					queue.enqueue(w);
				}
		}
	}

	public boolean hasPathTo(int v) {
		
		return marked[v];
	}

	public Iterable<Integer> pathTo(int v) {
		if(!hasPathTo(v)) return null;
		
		Stack<Integer> stack = new Stack<>();
		for(int i = v; i != s; i = edgeTo[i]){
			stack.push(i);
		}
		stack.push(s);
		return stack;
	}
	
	public int disTo(int v) {
		if(!hasPathTo(v)) return -1;
		
		int count = 0;
		for(int i = v; i != s; i = edgeTo[i]){
			count++;
		}
		return count;
	}

	public static void main(String[] args) {
		String filename = "data/tinyG.txt";
		Graph g = new Graph(new In(filename));
		System.out.println(g.toString());
		
		int s = 0;
		int t = 4;
		BreadFirstPaths paths = new BreadFirstPaths(g, s);
		System.out.print("pathTo(" + t + "): ");
		for (Integer e : paths.pathTo(t)) {
			System.out.print(e + " ");
		}
		System.out.println();
		
		System.out.println("distTo(" + t +"): " +paths.disTo(t));
	}
}
