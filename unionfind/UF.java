package unionfind;

public abstract class UF {
	public int accessTime;
	protected int count;
	protected int[] id;
	
	public void init(int n){
		count = n;
		id = new int[n];
		
		for(int i = 0; i< n; i++){
			id[i] = i;
		}
	}

	public abstract int find(int p);

	public abstract void union(int p, int q);

	public abstract boolean connected(int p, int q);

	public int count(){
		return count;
	}

}