package search;

import java.util.Arrays;
import java.util.HashMap;

import datastruct.LinkedQueue;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stopwatch;

public class FrequencyCounter {
	public static void main(String[] args) {
		String tiny = "tinyTale.txt";
		String med = "medTale.txt";
		String lei = "leipzig1M.txt";
		String filename = lei;
		String path = "data/" + filename;
		
		int minlen = 0;
		int maxSize = 1000000;
		
		ST<String, Integer> rst = new RedBlackBST();
		ST<String, Integer> tst = new BST<>();
		ST<String, Integer> bst = new BinarySearchST<>(1500000);
		ST<String, Integer> sst = new SequentialSearchST<>();
		
		test(rst, maxSize, path, minlen);
		test(tst, maxSize, path, minlen);
		test(bst, maxSize, path, minlen);
		test(sst, maxSize, path, minlen);
	}
	
	public static void test(ST<String, Integer> st, int maxSize, String path, int minlen){
		int size = 0;
		In in = new In(path);
		Stopwatch timer = new Stopwatch();
		
		while(!in.isEmpty()){
			if(size++ > maxSize) break; 
			String word = in.readString();
			if(word.length() < minlen) continue;
			if(!st.contains(word)) st.put(word, 1);
			else st.put(word, st.get(word)+1);
		}
		
		String max = "";
		int num = 0;
		for (String key : st.keys()) {
			if(st.get(key) > num) {
				max = key;
				num = st.get(key);
			}
		}
		
		System.out.println(timer.elapsedTime());
		System.out.println(max + ": " + num);
	}
}
