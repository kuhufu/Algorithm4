package dirgraph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class DepthFirstOrder {
	private boolean[] marked;
	private Queue<Integer> pre;		//«∞–Ú
	private Queue<Integer> post;	//∫Û–Ú
	private Stack<Integer> reversePost;
	
	public DepthFirstOrder(Dirgraph g) {
		marked = new boolean[g.V()];
		pre = new Queue<>();
		post = new Queue<>();
		reversePost = new Stack<>();
		
		for(int i = 0; i < g.V(); i++){
			if(!marked[i])
				dfs(g, i);
		}
		
	}
	
	private void dfs(Dirgraph g, int s){
		marked[s] = true;
		pre.enqueue(s);
		
		for (int w : g.adj(s)) {
			if(!marked[w])
				dfs(g, w);
		}
		
		post.enqueue(s);
		reversePost.push(s);
	}
	
	public Iterable<Integer> pre(){
		return pre;
	}
	
	public Iterable<Integer> post(){
		return post;
	}
	
	public Iterable<Integer> reversePost(){
		return reversePost;
	}
	
	public static void main(String[] args) {
		Dirgraph g = new Dirgraph(new In("data/tinyDAG.txt"));
		
		DepthFirstOrder dfo = new DepthFirstOrder(g);
		
		for (Object obj : dfo.pre()) {
			System.out.print(obj + " ");
		}
		System.out.println();
		
		for (Object obj : dfo.post()) {
			System.out.print(obj + " ");
		}
		System.out.println();
		
		for (Object obj : dfo.reversePost()) {
			System.out.print(obj + " ");
		}
	}
}
