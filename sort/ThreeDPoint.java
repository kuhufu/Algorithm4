package sort;

import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.StdRandom;

public class ThreeDPoint implements Comparable<ThreeDPoint>{
	
	public ThreeDPoint(double first, double second, double thrid) {
		this.first = first;
		this.second = second;
		this.thrid = thrid;
	}
	
	private double first;
	private double second;
	private double thrid;
	
	
	
	public double getFirst() {
		return first;
	}

	public void setFirst(double first) {
		this.first = first;
	}

	public double getSecond() {
		return second;
	}

	public void setSecond(double second) {
		this.second = second;
	}

	public double getThrid() {
		return thrid;
	}

	public void setThrid(double thrid) {
		this.thrid = thrid;
	}

	public String toString(){
		return first + ", " + second + ", " + thrid;
	}
	
	@Override
	public int compareTo(ThreeDPoint td) {
		if(this == td) return 0;
		if(this.first > td.first) return +1;
		if(this.first < td.first) return -1;
		if(this.second > td.second) return +1;
		if(this.second < td.second) return -1;
		if(this.thrid > td.thrid) return +1;
		if(this.thrid < td.thrid) return -1;
		return 0;
	}
	
	public static void main(String[] args) {
		int N = 9;
		ThreeDPoint[] ps = {
				new ThreeDPoint(0, 0, 0),
				new ThreeDPoint(0, 1, 0),
				new ThreeDPoint(0, 2, 0),
				
				new ThreeDPoint(1, 0, 0),
				new ThreeDPoint(1, 1, 0),
				new ThreeDPoint(1, 2, 0),
				
				new ThreeDPoint(2, 0, 0),
				new ThreeDPoint(2, 1, 0),
				new ThreeDPoint(2, 2, 0)
		};
				
		StdRandom.shuffle(ps);
		
		new Merge().sort(ps,new Comparator<ThreeDPoint>() {
			@Override
			public int compare(ThreeDPoint o1, ThreeDPoint o2) {
				if(o1.second > o2.second) return 1;
				if(o1.second < o2.second) return -1;
				return 0;
			}
		});
		
		
		for (ThreeDPoint threeDPoint : ps) {
			System.out.println(threeDPoint);
		}
	}
}
