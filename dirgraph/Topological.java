package dirgraph;


public class Topological {
	private Dirgraph g;
	private Iterable<Integer> order;
	
	
	public Topological(Dirgraph g) {
		DirectedCycle c = new DirectedCycle(g);
		if(!c.hasCycle()){
			order = new DepthFirstOrder(g).reversePost();
		}
	}
	
	public boolean isDAG(){
		return order != null;
	}
	
	public Iterable<Integer> order(){
		if(!isDAG()) return null;
		return order;
	}
	
	public static void main(String[] args) {
		String filename = "data/jobs.txt";
		String separator = "/";
		SymbolGraph sg = new SymbolGraph(filename, separator);
		
		Topological top = new Topological(sg.G());
		
		for (int v : top.order()) {
			System.out.println(sg.name(v));
		}
	}
}
