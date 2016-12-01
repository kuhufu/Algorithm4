import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;

public class MinDistanceline {
	private Point2D[] points;
	final int N;
	
	/**
	 * 
	 * @param points 外部传入随机点
	 */
	public MinDistanceline(Point2D[] points) {
		this.points = points;
		N = points.length;
	}
	
	/**
	 * 画出任意两点间距最小的线
	 */
	public void DrawMinDistanceLine(){
		if(N==1)return;
		
		Point2D begin = points[0];
		Point2D end = points[1];
		double min = begin.distanceTo(end);
		for(int i=0; i<N; i++){
			for(int j=i+1; j<N; j++){
				if(min > points[i].distanceTo(points[j])){
						min = points[i].distanceTo(points[j]);
						begin=points[i];
						end = points[j];
				}
			}
		}
		
		StdDraw.line(begin.x(), begin.y(), end.x(), end.y());
	}
	
}
