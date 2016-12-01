package unionfind;
/**
 * weighted quick-union
 * @author kuhuf
 *
 */

public class WeightedQuickUnionUF extends UF {
	private int[] sz;

	@Override
	public int find(int p) {
		while(p != id[p]) p = id[p];
		return p;
	}

	@Override
	public void union(int p, int q) {
		int pRoot = find(p);
		int qRoot = find(q);
		
		if(pRoot == qRoot) return;
		//根据结点数加权
		if(sz[pRoot] < sz[qRoot]) {id[pRoot] = qRoot; sz[qRoot] += sz[pRoot];}
		else 					  {id[qRoot] = pRoot; sz[pRoot] += sz[qRoot];}

		count--;
	}

	@Override
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}
	
	public boolean marked(int v, int s){
		return find(v) == find(s);
	}
	
	public int count(int s){
		int sRoot = find(s);
		int count = 0;
		for (int i : id) {
			if(find(i) == sRoot)
				count++;
		}
		return count;
	}
	
	public void init(int n){
		super.init(n);
		sz = new int[n];
		for(int i = 0; i < n; i++){
			sz[i] = 1;
		}
	}

}
