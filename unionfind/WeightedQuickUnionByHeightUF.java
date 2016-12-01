package unionfind;
/**
 * weighted quick-union
 * @author kuhuf
 *
 */

public class  WeightedQuickUnionByHeightUF extends UF {
	private int[] sz;

	@Override
	public int find(int p) {
		int root = p;
		while(root != id[root]) 
			root = id[root];	
		
		return root;
	}

	@Override
	public void union(int p, int q) {
		int pRoot = find(p);
		int qRoot = find(q);
		
		if(pRoot == qRoot) return;

		//根据树高度加权
		if     (sz[pRoot] < sz[qRoot]) id[pRoot] = qRoot;
		else if(sz[pRoot] > sz[qRoot]) id[qRoot] = pRoot;
		else{ id[qRoot] = pRoot; sz[pRoot]++;}
		
		count--;
	}

	@Override
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}
	
	public void init(int n){
		super.init(n);
		sz = new int[n];
		for(int i = 0; i < n; i++){
			sz[i] = 1;
		}
	}

}
