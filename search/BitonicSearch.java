package search;

/**
 * 双调查找
 * @author kuhuf
 * Attention: the array's length must greater than 2 
 * Attention: the array must be ordered
 */
public class BitonicSearch {
	
	public static int find(int[] a, int key){
		int lo = 0;
		int hi = a.length-1;
		int mid = -1;
		while(lo <= hi){
			mid = (lo + hi) / 2;
			if(a[mid] > a[mid - 1]){
				int index = BinarySearch.rank(key, a, lo, mid);
				if(index != -1) return index;
				lo = mid + 1;
			}else if(a[mid] > a[mid + 1]){
				int index = BinarySearch.rank(key, a, mid, hi);
				if(index != -1) return index;
				hi = mid - 1;
			}
		}
		
		
		return -1;
	}
	
	/**
	 * 1. 找到极值点（极值点也是用二分法找到的）(extreme point, binary method)
	 * 2. 根据极值点将数组分为两部分，分别进行二分搜索，查找key值
	 * according to the extreme point, divide the array into two parts
	 * use Binary search respectively find the key value
	 * @param a
	 * @param key
	 * @return
	 */
	public static int find2(int[] a, int key){
		int lo = 0;
		int hi = a.length-1;
		int mid = -1;
		while(lo <= hi){
			mid = (lo + hi) / 2;
			if(a[mid] > a[mid - 1]){
				lo = mid + 1;
			}else{
				hi = mid - 1;
			}
		}
		
		int left = BinarySearch.rank(key, a, 0, mid);
		int right = BinarySearch.rank(key, a, mid, a.length-1);
		
		return left > right ? left : right;
	}
	
	public static void main(String[] args) {
		int[] a = {-1, 2,3, 4,5};
		
		System.out.println( find2(a, 3) );
		
		
	} 
}
