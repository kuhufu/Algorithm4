package tool;

public class StopWatch {
	private long start;
	public StopWatch() {
		start = System.currentTimeMillis();
	}
	private void start(){
		start = System.currentTimeMillis();
	}
	
	public double elapseTime(){
		return (System.currentTimeMillis() - start) / 1000.0;
	}
}
