package dirgraph;

import edu.princeton.cs.algs4.*;

public class BreadFirstDirectedPaths {
	private boolean[] marked;
	private int[] edgeTo;
	private int s;
	
	public BreadFirstDirectedPaths(Dirgraph g, int s) {
		marked = new boolean[g.V()];
		edgeTo = new int[g.V()];
		this.s = s;
		bfs(g, s);
	}
	
	private void bfs(Dirgraph g, int v){
		Queue<Integer> q = new Queue<>();
		marked[v] = true;
		q.enqueue(v);
		
		while(!q.isEmpty()){
			int x = q.dequeue();
			for (Integer w : g.adj(x)) {
				if(!marked[w]){
					marked[w] = true;
					q.enqueue(w);
				}
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
	
	public int disTo(int v) {
		if(!hasPathTo(v)) return -1;
		
		int count = 0;
		for(int i = v; i != s; i = edgeTo[i]){
			count++;
		}
		return count;
	}
}
