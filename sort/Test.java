package sort;

import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class Test {
	public static void main(String[] args) {
		//int n = 9; 
		//assert N<1;
		
		for(int n = 4096; true; n *= 2){
			System.out.println(n);
			
			double time0 = timeRandomInput("Shell", n, 1);
			double time1 = timeRandomInput("Quick", n, 1);
			//double time2 = timeRandomInput("MergeX", n, 1);
			//double time3 = timeRandomInput("KMerge4", n, 1);
			
			System.out.println(time0);
			System.out.println(time1);
			//System.out.println(time2);
			//System.out.println(time3);
			System.out.println();
		}
		
		//System.out.println(timeRandomInput("Merge3", N, 3));
		
	}
	
	public static double time(String alg, Comparable[] a){
		Stopwatch timer = new Stopwatch();
		switch (alg) {
		case "Select": new Select().sort(a); break;
		case "Insert": new Insert().sort(a); break;
		case "Shell" : new Shell().sort(a); break;
		case "Merge" : new Merge().sort(a); break;
		case "MergeBU": new MergeBU().sort(a); break;
		case "MergeX": new MergeX().sort(a); break;
		case "KMerge3": new KMerge().sort(a, 3); break;
		case "Quick" : new Quick().sort(a); break;
		case "Quick3way" : new Quick3way().sort(a); break;
		case "QuickNR" : new QuickNR().sort(a); break;
		case "Heap"  : new Heap().sort(a); break;
		default: break;
		}
		return timer.elapsedTime();
	}
	
	public static double timeRandomInput(String alg, int N, int T){
		double total = 0.0;
		Double[] a = new Double[N];
		for(int t = 0; t < T; t++){
			for(int i = 0; i < N; i++){
				a[i] = StdRandom.uniform();
			}
			total += time(alg, a);
		}
		return total;
	}
}
