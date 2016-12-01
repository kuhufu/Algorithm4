package search;

import java.util.ArrayList;
import java.util.Collections;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class Test {
	public static void main(String[] args) {
		BSTST<Integer, Integer> bst0 = new BSTNR<>();
		BSTST<Integer, Integer> bst1 = new RedBlackBST<>();
		BSTST<Integer, Integer> bst2 = new AVLTreeST<>();
		
		testDraw(bst1, 127);
	}
	
	public static void testDraw(BSTST<Integer, Integer> bst, int N){
		ArrayList<Integer> es = new ArrayList<>();
		for(int i = N; i > 0; i--){
			es.add(i);
		}
		Collections.shuffle(es);
		
		for (Integer e : es) {
			int h = bst.height();
			bst.put(e, e);	
		}
		
		
		bst.draw();
	}
	
	public static void testHeight(BSTST<Integer, Integer> bst, int N){
		ArrayList<Integer> es = new ArrayList<>();
		for(int i = 0; i < N; i++){
			es.add(i);
		}
		Collections.shuffle(es);
		
		for (Integer e : es) {
			bst.put(e, e);
		}
		
		System.out.println("size: " + bst.size());
		System.out.println("实际值：" + bst.height());
		System.out.printf("估计值：%.1f\n", 2.99 * Math.log(N) / Math.log(2));
		System.out.println();
	}
	
	public static void testDelete(BSTST<Integer, Integer> bst, int N){
		ArrayList<Integer> es = new ArrayList<>();
		for(int i = 0; i < N; i++){
			es.add(i);
		}
		Collections.shuffle(es);

		for (Integer e : es) {
			bst.put(e, e);
		}
		System.out.println("初始高度：" + bst.height());
		
		for(int i = 0; i < N*N; i++){
			Integer e = bst.select(StdRandom.uniform(N));
			assert(e != null);
			bst.delete(e);
			bst.put(StdRandom.uniform(N), 1);
		}
		
		System.out.println("删后高度" + bst.height());
	}
}
