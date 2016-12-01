package search;

/**
 * ˫������
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
	 * 1. �ҵ���ֵ�㣨��ֵ��Ҳ���ö��ַ��ҵ��ģ�(extreme point, binary method)
	 * 2. ���ݼ�ֵ�㽫�����Ϊ�����֣��ֱ���ж�������������keyֵ
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
