package analyse;

import edu.princeton.cs.algs4.*;

public class LookupIndex {
	public static void main(String[] args) {
		
		String movies = "data/movies.txt"; 
		String amino = "data/aminoI.txt";
		
		String path = movies;
		String sp = "/";
		In in = new In(path);
		ST<String, Queue<String>> st = new ST<>();
		ST<String, Queue<String>> ts = new ST<>();
		
		while(in.hasNextLine()){
			String[] a = in.readLine().split(sp);
			String key = a[0];
			
			for(int i = 1; i < a.length; i++){
				String val = a[i];
				if(!st.contains(key))
					st.put(key, new Queue<>());
				if(!ts.contains(val)){
					ts.put(val, new Queue<>());
				}
				st.get(key).enqueue(a[i]);
				ts.get(val).enqueue(key);
			}
		}
		System.out.println("º”‘ÿÕÍ±œ");
		
		while(!StdIn.isEmpty()){
			String query = StdIn.readLine();
			if(st.contains(query)){
				for (String s : st.get(query)) {
					System.out.println(" " + s);
				}
			}
			if(ts.contains(query)){
				for (String s : ts.get(query)) {
					System.out.println(" " + s);
				}
			}
		}
		
	}
}
