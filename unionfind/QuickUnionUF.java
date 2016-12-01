package unionfind;
/**
 * quick-union
 * @author kuhuf
 *
 */

public class QuickUnionUF extends UF {
	public QuickUnionUF() {
	}
	
	public QuickUnionUF(int n) {
		init(n);
	}

	public int find(int p){
		while(p != id[p]) p = id[p];
		return p;
	}

	public void union(int p, int q){
		int pRoot = find(p);
		int qRoot = find(q);
		
		if(pRoot == qRoot) return;
		id[pRoot] = qRoot;
		
		count--;
	}

	public boolean connected(int p, int q){
		return find(p) == find(q);
	}
}	
