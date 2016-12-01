package dirgraph;

import edu.princeton.cs.algs4.Queue;

public class Degrees {
	private int[] indegree;
	private int[] outdegree;
	
	public Degrees(Dirgraph g) {
		indegree = new int[g.V()];
		outdegree = new int[g.V()];
		
		for (int i = 0; i < g.V(); i++) {
			for (int w : g.adj(i)) {
				indegree[w]++;
				outdegree[i]++;
			}
		}
	}
	
	public int indegree(int v){
		return indegree(v);
	}
	
	public int outdegree(int v){
		return outdegree(v);
	}
	
	public Iterable<Integer> sources(){
		Queue<Integer> q = new Queue<>();
		for(int v = 0; v < indegree.length; v++){
			if(indegree[v] == 0)
				q.enqueue(v);
		}
		return q;
	}
	
	public Iterable<Integer> sinks(){
		Queue<Integer> q = new Queue<>();
		for(int v = 0; v < outdegree.length; v++){
			if(outdegree[v] == 0)
				q.enqueue(v);
		}
		return q;
	}
	
	public boolean isMap(){
		for (int i : outdegree) {
			if(i != 1)
				return false;
		}
		return true;
	}
}
