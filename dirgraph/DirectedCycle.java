package dirgraph;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

public class DirectedCycle {
	private boolean[] marked;
	private int[] edgeTo;
	private boolean[] onStack;
	private Stack<Integer> cycle;
	
	public DirectedCycle(Dirgraph g) {
		int V = g.V();
		marked = new boolean[V];
		edgeTo = new int[V];
		onStack = new boolean[V];
		for(int v = 0; v < V; v++){
			if(!marked[v])
				dfs(g, v);
		}
	}
	
	private void dfs(Dirgraph g, int s){
		marked[s] = true;
		onStack[s] = true;
		
		for (int w : g.adj(s)) {
			if(hasCycle()) return;
			if(!marked[w]){
				edgeTo[w] = s;
				dfs(g, w);
			}
			else if(onStack[w]){
				cycle = new Stack<>();
				for(int x = s; x != w; x = edgeTo[x])
					cycle.push(x);
				cycle.push(w);
				cycle.push(s);
			}
		}
		onStack[s] = false;
	}
	
	public boolean hasCycle(){
		return cycle != null;
	}
	
	public Iterable<Integer> cycle(){
		return cycle;
	}
	
	public static void main(String[] args) {
		Dirgraph g = new Dirgraph(new In("data/tinyDG.txt"));
		Integer[] source = {1, 2, 6};
		List sources = Arrays.stream(source).collect(Collectors.toList());
		
		DirectedCycle cycle = new DirectedCycle(g);
		for (Object object : cycle.cycle()) {
			System.out.print(object.toString() + " ");
		}
		
	}
}
