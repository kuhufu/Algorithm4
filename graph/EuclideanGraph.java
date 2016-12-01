package graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class EuclideanGraph {
	
	public static void draw(Graph g){
		double[] x = new double[g.V()];
		double[] y = new double[g.V()];
		
		for(int i = 0; i < g.V(); i++){
			x[i] = StdRandom.uniform();
			y[i] = StdRandom.uniform();
		}
		
		StdDraw.setCanvasSize(900, 900);
		for(int i = 0; i < g.V(); i++){
			StdDraw.setPenColor();
			StdDraw.setPenRadius(0.02);
			StdDraw.point(x[i], y[i]);
			for (int  w : g.adj(i)) {
				StdDraw.setPenRadius();
				StdDraw.setPenColor(StdDraw.BOOK_RED);
				StdDraw.line(x[i], y[i], x[w], y[w]);
			}
		}
	}
	
	public static void main(String[] args) {
		String filename = "data/mediumG.txt";
		Graph g = new Graph(new In(filename));
		
		EuclideanGraph.draw(g);
	}
}
