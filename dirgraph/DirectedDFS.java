package dirgraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import edu.princeton.cs.algs4.In;

public class DirectedDFS {
	private boolean[] marked;
	
	public DirectedDFS(Dirgraph g, int s) {
		marked = new boolean[g.V()];
		dfs(g, s);
	}
	
	public DirectedDFS(Dirgraph g, Iterable<Integer> sources){
		marked = new boolean[g.V()];
		for (int s : sources) {
			if(!marked[s])
				dfs(g, s);
		}
	}
	
	private void dfs(Dirgraph g, int v){
		marked[v] = true;
		
		for (int w : g.adj(v)) {
			if(!marked[w])
				dfs(g, w);
		}
	}
	
	public boolean marked(int v){
		return marked[v];
	}
	
	public static void main(String[] args) {
		Dirgraph g = new Dirgraph(new In("data/tinyDG.txt"));
		Integer[] source = {1, 2, 6};
		List sources = Arrays.stream(source).collect(Collectors.toList());
		
		DirectedDFS dfs = new DirectedDFS(g, sources);
		for(int i = 0; i < g.V(); i++){
			if(dfs.marked(i))
				System.out.println(i + " ");
		}
	}
}
