import java.util.Arrays;

import search.BinarySearch;

public class StaticSETOfInts {
	private int[] a;
	
	public StaticSETOfInts(int[] keys) {
		a = Arrays.copyOf(keys, keys.length);
		Arrays.sort(a);
	}
	
	public boolean contains(int key){
		return BinarySearch.rank(key, a) != -1;
	}
	
	public static void main(String[] args) {
		int[] a = {1,2,3,4,5,2,2};
		StaticSETOfInts s = new StaticSETOfInts(a);
		System.out.println(s.howMany(2));
	}
	
	private int rank(int key){
		int lo = 0;
		int hi = a.length-1;
		
		while(lo <= hi){
			int mid = (lo + hi)/2;
			if (key < a[mid]) hi = mid-1;
			else if (key > a[mid]) lo = mid+1;
			else return mid;
			
		}
		
		return -1;
	}
	
	public int howMany(int key){
		int pos1, pos2;
		pos1 = pos2 = rank(key);
		if(pos1 == -1) return 0;
		
		int count = 1;
		while(true){
			if(pos1 == 0 || a[pos1 - 1] != a[pos1]) break;
			pos1--;
			count++;
		}
		while(true){
			if(pos2 == a.length -1 || a[pos2 + 1] != a[pos2]) break;
			pos2++;
			count++;
		}
		
		return count;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(a);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StaticSETOfInts other = (StaticSETOfInts) obj;
		if (!Arrays.equals(a, other.a))
			return false;
		return true;
	}
}
