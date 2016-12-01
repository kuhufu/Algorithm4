package search;

import java.util.Collections;

import datastruct.LinkedQueue;
import edu.princeton.cs.algs4.StdDraw;

public class AVLTreeST<Key extends Comparable<Key>, Value> implements BSTST<Key, Value> {
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	private Node root;
	private Node cache = new Node(null, null, 0, 0);
	
	private class Node{
		Key key;
		Value val;
		Node left;
		Node right;
		int height;
		int N;
		
		public Node(Key key, Value val, int height, int N) {
			this.key = key;
			this.val = val;
			this.N = N;
			this.height = height;
		}
	}
	
	private Node rotateLeft(Node h){
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.height = Math.max(height(x.left), height(x.right))+1;
		h.height = Math.max(height(h.left), height(h.right))+1;
		x.N = h.N;
		h.N = size(h.left) + size(h.right) + 1;
		
		return x;
	}
	
	private Node rotateRight(Node h){
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.height = Math.max(height(x.left), height(x.right))+1;
		h.height = Math.max(height(h.left), height(h.right))+1;
		x.N = h.N;
		h.N = size(h.left) + size(h.right) + 1;
		
		return x;
	}
	
	@Override
	public void put(Key key, Value val) {
        if (key == null) throw new NullPointerException("first argument to put() is null");
        if (val == null) {
            delete(key);
            return;
        }
		root = put(root, key, val);
	}
	
	private Node put(Node x, Key key, Value val){
		if(x == null) return new Node(key, val, 1, 1);
		
		int cmp = key.compareTo(x.key);
		if		(cmp < 0)	x.left  = put(x.left,  key, val);
		else if	(cmp > 0) 	x.right = put(x.right, key, val);
		else 				x.val = val;
		
		if(height(x.right) - height(x.left) > 1) 	x = rotateLeft(x);
		if(height(x.left) - height(x.right) > 1) 	x = rotateRight(x);
		
		x.N = size(x.left) + size(x.right) + 1;
		x.height = Math.max(height(x.left), height(x.right)) + 1;
		return x;
	}

	@Override
	public Value get(Key key) {
		if(cache.key != null)
			if(cache.key.equals(key)) return cache.val;
		
		Value val = get(root, key);
		if(val != null){
			cache.key = key;
			cache.val = val;
		}
		
		return val;
	}
	
	private Value get(Node x, Key key){
		if(x == null) return null;
		int cmp = key.compareTo(x.key);
		if(cmp > 0) return get(x.right, key);
		if(cmp < 0) return get(x.left,  key);
		return x.val;
	}
	
	public void deleteMax(){
		root = deleteMax(root);
	}
	
	private Node deleteMax(Node x){
		if(x == null) return null;
		if(x.right == null) return x.left;
		x.right = deleteMax(x.right);
		x.N = size(x.left) + size(x.right) + 1;
		
		return x;
	}
	
	public void deleteMin(){
		root = deleteMin(root);
	}
	
	private Node deleteMin(Node x){
		if(x == null) return null;
		if(x.left == null) return x.right;
		x.left = deleteMin(x.left);
		x.N = size(x.left) + size(x.right) + 1;
		
		return x;
	}

	@Override
	public void delete(Key key) {
		if(key == null) return;
		root = delete(root, key);
	}
	
	private Node delete(Node x, Key key){
		if(x == null) return null;
		int cmp = key.compareTo(x.key);
		if(cmp > 0) x.right = delete(x.right, key);
		else if(cmp < 0) x.left = delete(x.left, key);
		else{
			if(x.left == null) return x.right;
			if(x.right == null) return x.left;
			
			Node t = x;
			x = min(t.right);
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		x.N = size(x.left) + size(x.right) + 1;
		
		return x;
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

	@Override
	public boolean contains(Key key) {
		
		return get(key) != null;
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
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
        return x.height;
    }

	@Override
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
		
		StdDraw.setPenColor();
		StdDraw.setPenRadius(.001);
		StdDraw.point(x0, y0);
		
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.text(x0, y0, Integer.toString(x.height));
		StdDraw.setPenColor();
		
		StdDraw.setPenRadius();
		if(x.left != null){
			double x1 = rank(x.left.key)/size;
			double y1 = (layer-1)/height;

			StdDraw.line(x0, y0, x1, y1);
			
			draw(x.left, size, height, layer-1);
		}
		if(x.right != null){
			double x1 = rank(x.right.key)/size;
			double y1 = (layer-1)/height;

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
	
	public Iterable<Key> printPre() {
		LinkedQueue<Key> q = new LinkedQueue<>();
		printPre(root, q);
		return q;
	}
	
	private void printPre(Node x, LinkedQueue<Key> q){
		if(x == null) return;
		q.enqueue(x.key);
		printPre(x.left, q);
		printPre(x.right, q);
	}

	public static void main(String[] args) {
		AVLTreeST<String, Integer> bst = new AVLTreeST<>();
		bst.put("3", 3);
		bst.put("1", 1);
		bst.put("0", 0);
		bst.put("2", 2);
		bst.put("4", 4);
		bst.put("5", 5);
		
		System.out.println(bst.size());
		System.out.println(bst.height());
		System.out.println(bst.get("3"));
		System.out.println(bst.select(5));
		System.out.println(bst.rank("3"));
		System.out.println(bst.floor("2"));
		System.out.println(bst.get("d"));

	}
}
