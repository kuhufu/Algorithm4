package search;

public class BinarySearch{

	public static int rank(int key, int[] a) {
		return rank(key, a, 0, a.length - 1);
	}
	
	/**
	 * 根据首尾元素的比较结果判断数组的升降序
	 * @param key
	 * @param a
	 * @param lo
	 * @param hi
	 * @return
	 */
	public static int rank(int key, int[] a, int lo, int hi) {		
		return a[lo] < a[hi] ? rankAsc(key, a, lo, hi) : rankDesc(key, a, lo, hi);
	}
	
	/**
	 * 对升序数组进行查找
	 * @param key
	 * @param a
	 * @param lo
	 * @param hi
	 * @return
	 */
	public static int rankAsc(int key, int[] a, int lo, int hi) {
		while(lo <= hi){
			int mid = (lo + hi) / 2;
			if(key < a[mid]) hi = mid - 1;
			else if(key > a[mid]) lo = mid + 1;
			else return mid;
		}		
		return -1;
	}
	
	/**
	 * 对降序数组进行查找
	 * @param key
	 * @param a
	 * @param lo
	 * @param hi
	 * @return
	 */
	public static int rankDesc(int key, int[] a, int lo, int hi) {
		while(lo <= hi){
			int mid = (lo + hi) / 2;
			if(key > a[mid]) hi = mid - 1;
			else if(key < a[mid]) lo = mid + 1;
			else return mid;
		}		
		return -1;
	}
	
	/**
	 * 返回匹配键的最小索引
	 * @param key
	 * @param a
	 * @return
	 */
	public static int rankFirstIndex(int key, int[] a) {
		int lo = 0;
		int hi = a.length - 1;
		int index = -1;
		
		while(lo <= hi){
			int mid = (lo + hi) / 2;
			if(key < a[mid]) hi = mid - 1;
			else if(key > a[mid]) lo = mid + 1;
			else{index = mid; break;}
		}
		
		while(index > 0){
			if(a[index-1] != a[index])break;
			else index--;
		}
		
		return index;
	}
	
	public static void main(String[] args) {
		int[] a = {1,2,2,2,2,2,2,3,3,3,3,4};
		//System.out.println(rank(2, a));
		System.out.println(rankFirstIndex(3, a));
	}
	
}
