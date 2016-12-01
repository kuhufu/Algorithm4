package dirgraph;

public class KosaraSCC {
	private boolean[] marked;
	private int[] id;
	private int count;
	
	public KosaraSCC(Dirgraph g) {
		marked = new boolean[g.V()];
		id = new int[g.V()];
		DepthFirstOrder order = new DepthFirstOrder(g);
		for (int i : order.reversePost()) {
			if(!marked[i]){
				dfs(g, i);
				count++;
			}
		}
	}
	
	private void dfs(Dirgraph g, int v){
		marked[v] = true;
		id[v] = count;
		for (int w : g.adj(v)) {
			if(!marked[w])
				dfs(g, w);
		}
	}
	
	public boolean stronglyConnected(int v, int w){
		return id[v] == id[w];
	}
	
	public int id(int v){
		return id[v];
	}
	
	public int count(){
		return count;
	}
}
