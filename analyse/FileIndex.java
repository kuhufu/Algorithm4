package analyse;

import java.io.File;

import edu.princeton.cs.algs4.*;

public class FileIndex {
	public static void main(String[] args) {
		String file1 = "data/ex1.txt";
		String file2 = "data/ex2.txt";
		String file3 = "data/ex3.txt";
		String file4 = "data/ex4.txt";
		String[] paths = {file1, file2, file3, file4};
		ST<String, SET<File>> st = new ST<>();
		
		for (String path : paths) {
			File file = new File(path);
			In in = new In(file);
			
			while(!in.isEmpty()){
				String word = in.readString();
				if(!st.contains(word)){
					st.put(word, new SET<>());
				}
				st.get(word).add(file);
			}
		}
		
		while(!StdIn.isEmpty()){
			String query = StdIn.readString();
			if(st.contains(query)){
				for (File file : st.get(query).items()) {
					System.out.println(" " + file.getName());
				}
			}
		}
	}
}
