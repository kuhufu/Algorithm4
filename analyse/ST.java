package analyse;

import java.util.NoSuchElementException;

import datastruct.LinkedQueue;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.Stopwatch;

public class ST<Key extends Comparable<Key>, Value>{
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	private Node root;
	
	private class Node{
		Key key;
		Value val;
		Node left;
		Node right;
		boolean color;
		int N;
		
		public Node(Key key, Value val, boolean color, int N) {
			this.key = key;
			this.val = val;
			this.N = N;
			this.color = color;
		}
	}
	
	private boolean isRed(Node x){
		if(x == null) return false;
		return x.color == RED;
	}
	
	private Node rotateLeft(Node h){
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = size(h.left) + size(h.right) + 1;
		
		return x;
	}
	
	private Node rotateRight(Node h){
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = size(h.left) + size(h.right) + 1;
		
		return x;
	}
	
	private void flipColors(Node x){
        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;
	}
	
	private Node moveRedLeft(Node x){
		flipColors(x);
		if(isRed(x.right.left)){
			x.right = rotateRight(x.right);
			x = rotateLeft(x);
			flipColors(x);
		}
		return x;
	}
	
	private Node moveRedRight(Node x){
		flipColors(x);
		if(isRed(x.left.left)){
			x = rotateRight(x);
			flipColors(x);
		}
		return x;
	}
	
	private Node balance(Node x){
		if(isRed(x.right)) 						x = rotateLeft(x);
		if(isRed(x.left) && isRed(x.left.left))	x = rotateRight(x);
		if(isRed(x.left) && isRed(x.right))		flipColors(x);
		
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
	
		public void put(Key key, Value val) {
        if (key == null) throw new NullPointerException("first argument to put() is null");
        if (val == null) {
            delete(key);
            return;
        }
		root = put(root, key, val);
		root.color = BLACK;
	}
	
	private Node put(Node x, Key key, Value val){
		if(x == null) return new Node(key, val, RED, 1);
		
		int cmp = key.compareTo(x.key);
		if		(cmp < 0)	x.left  = put(x.left,  key, val);
		else if	(cmp > 0) 	x.right = put(x.right, key, val);
		else 				x.val = val;
		
		if(isRed(x.right) && !isRed(x.left)) 	x = rotateLeft(x);
		if(isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
		if(isRed(x.left) && isRed(x.right)) 	flipColors(x);
		
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

		public Value get(Key key) {
		if(key == null) throw new NullPointerException();
		return get(root, key);
	}
	
	private Value get(Node x, Key key){
		if(x == null) return null;
		int cmp = key.compareTo(x.key);
		if(cmp > 0) return get(x.right, key);
		if(cmp < 0) return get(x.left,  key);
		return x.val;
	}
	
	public void deleteMax(){
		if(isEmpty()) throw new NoSuchElementException();
		
		if(!isRed(root.left) && !isRed(root.right))
			root.color = RED;
		
		root = deleteMax(root);
		if(!isEmpty())
			root.color = BLACK;
	}
	
	private Node deleteMax(Node x){
		if(x.right == null)
			return null;
		
		if(!isRed(x.right) && !isRed(x.right.left))
			x = moveRedRight(x);
		
		x.right = deleteMax(x.right);
		
		return balance(x);
	}
	
	public void deleteMin(){
		if(isEmpty()) throw new NoSuchElementException();
		
		if(!isRed(root.left) && !isRed(root.right))
			root.color = RED;
		
		root = deleteMin(root);
		if(!isEmpty()) root.color = BLACK;
	}
	
	private Node deleteMin(Node x){
		if(x.left == null) 
			return null;
		
		if(!isRed(x.left) && !isRed(x.left.left))
			x = moveRedLeft(x);
		
		x.left = deleteMin(x.left);
		return balance(x);
	}

	public void delete(Key key) {
		if(key == null) return;
		if((!contains(key))) return;
		
		if(!isRed(root.left) && !isRed(root.right))
			root.color = RED;
		
		root = delete(root, key);
		if(!isEmpty()) root.color = BLACK;
	}
	
	private Node delete(Node x, Key key){
		if(key.compareTo(x.key) < 0){
			if(!isRed(x.left) && !isRed(x.left.left))
				x = moveRedLeft(x);
			x.left = delete(x.left, key);
		}else{
			if(isRed(x.left))
				x = rotateRight(x);
			if(key.compareTo(x.key) == 0 && x.right == null)
				return null;
			if(!isRed(x.right) && !isRed(x.right.left))
				x = moveRedRight(x);
			if(key.compareTo(x.key) == 0){
				Node t = min(x.right);
				x.key = t.key;
				x.val = t.val;
				x.right = deleteMin(x.right);
			}else
				x.right = delete(x.right, key);
		}
		return balance(x);
	}
	
	public Key min(){
		return root == null ? null : min(root).key;
	}

	private Node min(Node x) {
		if(x == null) return null;
		if(x.left == null) return x;
		return min(x.left);
	}
	
	public Key max(){
		return root == null ? null : max(root).key;
	}

	private Node max(Node x) {
		if(x == null) return null;
		if(x.right == null) return x;
		return max(x.right);
	}
	
	public Key floor(Key key){
		return floor(root, key).key;
	}

	private Node floor(Node x, Key key) {
		if(x == null) 	return null;
		int cmp = key.compareTo(x.key);
		if(cmp == 0) 	return x;
		if(cmp < 0) 	return floor(x.left, key);
		
		Node t = floor(x.right, key);
		if(t != null) 	return t;
		else 			return x;
	}
	
	public Key ceiling(Key key){
		return ceiling(root, key).key;
	}

	private Node ceiling(Node x, Key key) {
		if(x == null) 	return null;
		int cmp = key.compareTo(x.key);
		if(cmp > 0) 	return ceiling(x.right, key);
		if(cmp == 0) 	return x;
		
		Node t = ceiling(x.left, key);
		if(t != null) 	return t;
		else 			return x;
	}
	
	public Key select(int k){
		Node x = select(root , k);
		return x == null ? null : x.key;
	}

	//返回以x为根节点的子树中排名为k的结点
	private Node select(Node x, int k) {
		if(x == null) return null;
		int t = size(x.left);
		if(k > t) 		return select(x.right, k-t-1);
		else if(k < t) 	return select(x.left, k);
		else 			return x;
	}
	
	public int rank(Key key){
		return rank(root, key);
	}
	
	//返回以x为根节点的子树中小于key的结点数
	private int rank(Node x, Key key){
		if(x == null) return 0;
		int cmp = key.compareTo(x.key);
		if(cmp > 0) return 1 + size(x.left) + rank(x.right, key);
		if(cmp < 0) return rank(x.left, key);
		return size(x.left);
	}

	public boolean contains(Key key) {
		
		return get(key) != null;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public int size() {
		return size(root);
	}
	
	private int size(Node x){
		return x == null ? 0 : x.N;
	}
	
    public int height() {
        return height(root);
    }
    
    private int height(Node x) {
        if (x == null) return 0;
        return 1 + Math.max(height(x.left), height(x.right));
    }

	public Iterable<Key> keys() {
		return keys(min(), max());
	}
	
	public Iterable<Key> keys(Key lo, Key hi) {
		LinkedQueue<Key> q = new LinkedQueue<>();
		keys(root, q, lo, hi);
		return q;
	}
	
	private void keys(Node x,LinkedQueue<Key> q, Key lo, Key hi) {
		if(x == null) return;
		int cmplo = lo.compareTo(x.key);
		int cmphi = hi.compareTo(x.key);
		if(cmplo < 0) keys(x.left, q, lo ,hi);
		if(cmplo <= 0 && cmphi >=0) q.enqueue(x.key);
		if(cmphi > 0) keys(x.right, q, lo, hi);
	}
	
	public void clear(){
		root = null;
	}
	
	public void draw(){
		StdDraw.setCanvasSize(1920, 1080);
		
		double size = size();
		double height = height()+0.5;
		draw(root, size, height, height-0.5);
		
		StdDraw.setPenColor();
		StdDraw.text(0.03, 0.95, "Node:" + size());
		StdDraw.text(0.03, 0.90, "Height:" + height());
		
	}
	
	public void draw(Node x, double size, double height, double layer){
		if(x == null) return;
		double x0 = rank(x.key)/size;
		double y0 = layer/height;
		//if(x.color == RED){y0 = (layer+1)/height;}
		
		//StdDraw.setPenColor();
		StdDraw.setPenRadius(.02);
		StdDraw.point(x0, y0);
		//StdDraw.text(x0, y0, x.key.toString());

		StdDraw.setPenRadius();
		if(x.left != null){
			double x1 = rank(x.left.key)/size;
			double y1 = (layer-1)/height;
			
			if(x.left.color == RED){
				StdDraw.setPenColor(StdDraw.RED);
				//y1 = y0;
			}else{
				StdDraw.setPenColor();
			}
			StdDraw.line(x0, y0, x1, y1);
			
			draw(x.left, size, height, layer-1);
		}
		if(x.right != null){
			double x1 = rank(x.right.key)/size;
			double y1 = (layer-1)/height;
			
			if(x.right.color == RED){
				StdDraw.setPenColor(StdDraw.RED);
				//y1 = y0;
			}else{
				StdDraw.setPenColor();
			}
			StdDraw.line(x0, y0, x1, y1);
			
			draw(x.right, size, height, layer-1);
		}
	}

	public Iterable<Key> printLevel(){
		return printLevel(root);
	}
	
	private Iterable<Key> printLevel(Node x){
		if(x == null) return null;
		LinkedQueue<Node> nodes = new LinkedQueue<>();
		LinkedQueue<Key> keys = new LinkedQueue<>();
		nodes.enqueue(x);
		while(!nodes.isEmpty()){
			x = nodes.dequeue();
			keys.enqueue(x.key);
			if(x.left != null) nodes.enqueue(x.left);
			if(x.right != null) nodes.enqueue(x.right);
		}
		return keys;
	}
	
	public static void main(String[] args) {
		ST<String, Integer> st = new ST<>();
		Stopwatch timer = new Stopwatch();
		for(int i = 0; i < 1000000; i++){
			st.put(Integer.toString(i), i);
		}
		System.out.println(timer.elapsedTime());

	}
}
