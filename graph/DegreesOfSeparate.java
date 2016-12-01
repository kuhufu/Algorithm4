package graph;

import edu.princeton.cs.algs4.StdIn;

public class DegreesOfSeparate {
	
	public static void main(String[] args) {
		String filename = "data/movies.txt";
		String separator = "/";
		SymbolGraph sg = new SymbolGraph(filename, separator);
		Graph g = sg.G();
		
		//String start = StdIn.readLine();
		String start = "Bacon, Kevin";
		while(!sg.contains(start)){
			System.out.println(start + " " + "Not in database");
			start = StdIn.readLine();
		}
		
		int s = sg.index(start);
		BreadFirstPaths bfp = new BreadFirstPaths(g, s);
		//DepthFirstPaths bfp = new DepthFirstPaths(g, s);
		
		while(!StdIn.isEmpty()){
			String sink = StdIn.readLine();
			if(sg.contains(sink)){
				int t = sg.index(sink);
				for (int  v : bfp.pathTo(t)) {
					System.out.println(" " + sg.name(v));
				}
			}else
				System.out.println("Not in database");
		}
	}
}
