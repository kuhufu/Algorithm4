package graph;

import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

public class GraphProperties {
	Integer[] eccentricitys;	//¿Î–ƒ¬ 
	Integer[] sorted;
	Graph g;
	
	public GraphProperties(Graph g) {
		this.g = g;
		eccentricitys = new Integer[g.V()];
		sorted = new Integer[g.V()];
		for(int i = 0; i < g.V(); i++){
			sorted[i] = i;
			eccentricitys[i] = eccentricity(i);
		}
		
		Arrays.sort(sorted, (a, b)->eccentricitys[a].compareTo(eccentricitys[b]));
	}
	
	public int eccentricity(int s){
		if(eccentricitys[s] != 0) return eccentricitys[s];
		
		boolean[] marked = new boolean[g.V()];
		Queue<Integer> queue = new Queue<>();
		
		marked[s] = true;
		queue.enqueue(s);
		
		int count = 0;
		int levela = 1;
		int levelb = 0;
		
		while(!queue.isEmpty()){
			int v = queue.dequeue();
			if(levela == 0){
				levela = levelb;
				levelb = 0;
				count++;
			}
			levela--;
			for (Integer w : g.adj(v))
				if(!marked[w]){
					marked[w] = true;
					queue.enqueue(w);
					levelb++;
				}
		}
		return count;
	}
	
	public int diameter(){
		return eccentricitys[sorted[sorted.length-1]];
	}
	
	public int radius(){
		return eccentricitys[sorted[0]];
	}
	
	public int center(){
		return sorted[0];
	}
	
	public static void main(String[] args) {
		String filename = "data/tinyG.txt";
		Graph g = new Graph(new In(filename));
		System.out.println(g.toString());
		
		int s = 0;
		int t = 4;
		GraphProperties gp = new GraphProperties(g);
		System.out.print(gp.center());
		
		System.out.println();
	}
}
