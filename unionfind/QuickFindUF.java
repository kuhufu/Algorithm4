package unionfind;
/**
 * quick-find
 * @author kuhuf
 *
 */
public class QuickFindUF extends UF {
	public QuickFindUF() {
	}
	
	public QuickFindUF(int n) {
		init(n);
	}
	
	public int find(int p){
		return id[p];
	}

	public void union(int p, int q){
		int pID = id[p];
		int qID = id[q];
		accessTime += 2;
		if(qID == pID) return;
		
		for (int i = 0; i < id.length; i++) {
			if(id[i] == pID) id[i] = qID;
		}
		
		count--;
	}
	
	public boolean connected(int p, int q){
		return find(p) == find(q);
	}

}	
