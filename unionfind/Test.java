package unionfind;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stopwatch;

public class Test {
	private static String filename;
	public static void main(String[] args) {
		String tiny = "data/tinyUF.txt";
		String medium = "data/mediumUF.txt";
		String large = "data/largeUF.txt";
		
		filename = medium;
		//test(new WeightedQuickUnionByHeightUF());
		//test(new WeightedQuickUnionUF());
		test(new QuickUnionPathCompressionUF());
		test(new QuickUnionUF());
		//test(new QuickFindUF());

	}
	
	public static void test(UF uf) {
		In data = new In(filename);
		int n = data.readInt();
		uf.init(n);
		//System.out.println(n);
		Stopwatch timer = new Stopwatch();
		for(int i = 0; i < n; i++){
			int p = data.readInt();
			int q = data.readInt();
			//System.out.println(p + " " + q);
			if(uf.connected(p, q)) continue;
			uf.union(p, q);
		}
		
		System.out.println(timer.elapsedTime());
		System.out.println(uf.count() + " components");
	}
}
