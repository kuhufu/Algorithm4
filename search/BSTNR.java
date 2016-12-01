package search;

import java.util.ArrayList;
import java.util.Collections;

import datastruct.LinkedQueue;
import edu.princeton.cs.algs4.StdDraw;

public class BSTNR<Key extends Comparable<Key>, Value> implements BSTST<Key, Value> {
	private Node root; 
	
	private class Node{
		Key key;
		Value val;
		Node left;
		Node right;
		int N;
		int height;
		
		public Node(Key key, Value val, int N, int height) {
			this.key = key;
			this.val = val;
			this.N = N;
			this.height = height;
		}
	}
	
	public void put(Key key, Value val) {
		root = put(root, key, val);
	}
	
	private Node put(Node x, Key key, Value val){
		if(x == null) return new Node(key, val, 1, 1);
		int cmp = key.compareTo(x.key);
		if		(cmp < 0) x.left  = put(x.left,  key, val);
		else if	(cmp > 0) x.right = put(x.right, key, val);
		else x.val = val;
		x.N = size(x.left) + size(x.right) + 1;
		x.height = Math.max(height(x.left), height(x.right) ) + 1;
		return x;
	}
	
	public void put1(Key key, Value val) {
		if(key == null) throw new NullPointerException();
		if(root == null) root = new Node(key, val, 1, 1);
		
		Node x = root;
		while(x != null){
			int cmp = key.compareTo(x.key);
			if(cmp < 0){
				if(x.left == null) { x.left = new Node(key, val, 1, 1); return; }
				else{ x = x.left; }
			}else if(cmp > 0){
				if(x.right == null) { x.right = new Node(key, val, 1, 1); return; }
				else{ x = x.right;}
			}else{
				x.val = val;
				return;
			}
		}
	}


	@Override
	public Value get(Key key) {
		Node x = root;
		while(x != null){
			int cmp = key.compareTo(x.key);
			if(cmp == 0) return x.val;
			else if(cmp < 0) x = x.left;
			else if(cmp > 0) x = x.right;
		}
		return null;
	}
	
	public void deleteMax(){
		root = deleteMax(root);
	}
	
	private Node deleteMax(Node x){
		if(x == null) return null;
		if(x.right == null) return x.left;
		x.right = deleteMax(x.right);
		x.N = size(x.left) + size(x.right) + 1;
		x.height = Math.max(height(x.left), height(x.right) ) + 1;
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
		x.height = Math.max(height(x.left), height(x.right) ) + 1;
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
		x.height = Math.max(height(x.left), height(x.right) ) + 1;
		return x;
	}
	
	public Key min(){
		if(root == null) return null;
		return min(root).key;
	}

	private Node min(Node x) {
		while(x != null){
			if(x.left == null) return x;
			else x = x.left;
		}
		return null;
	}
	
	public Key max(){
		if(root == null) return null;
		return max(root).key;
	}

	private Node max(Node x) {
		while(x != null){
			if(x.right == null) return x;
			else x = x.right;
		}
		return null;
	}
	
	public Key floor(Key key){
		Node x = floor(root, key);
		return x == null ? null : x.key;
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
		Node x = ceiling(root, key);
		return x == null ? null : x.key;
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

	private Node select(Node x, int k) {
		while(x != null){
			int t = size(x.left);
			if(k == t) return x;
			else if(k > t){
				x = x.right; 
				k = k-t-1; 
			}else{
				x = x.left;
			}
		}
		return null;
	}
	
	public int rank(Key key){
		return rank(root, key);
	}
	
	private int rank(Node x, Key key){
		int lt = 0;
		while(x != null){
			int cmp = key.compareTo(x.key);
			if(cmp == 0)
				return size(x.left) + lt;
			else if(cmp < 0) {
				x = x.left;
			}else if(cmp > 0) {
				lt += size(x.left) + 1;
				x = x.right;
			}
		}
		return 0;
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
	
	public int height(){
		return height(root);
	}
	
	private int height(Node x){
		return x == null ? 0 : x.height;
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
	
	public void draw(){
		StdDraw.setCanvasSize(1920, 1080);
		
		double size = size();
		double height = height()+0.5;
		draw(root, size, height, height-0.5);
		
		StdDraw.text(0.03, 0.95, "Node:" + size());
		StdDraw.text(0.03, 0.90, "Height:" + height());
	}
	
	public void draw(Node x, double size, double height, double layer){
		if(x == null) return;
		
		double x0 = rank(x.key)/size;
		double y0 = layer/height;
		
		StdDraw.setPenColor();
		StdDraw.setPenRadius(.01);
		StdDraw.point(x0, y0);	
		
		if(x.left != null){
			double x1 = rank(x.left.key)/size;
			double y1 = (layer-1)/height;
			
			StdDraw.setPenRadius();
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.line(x0, y0, x1, y1);
			
			draw(x.left, size, height, layer-1);
		}
		if(x.right != null){
			double x1 = rank(x.right.key)/size;
			double y1 = (layer-1)/height;
			
			StdDraw.setPenRadius();
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.line(x0, y0, x1, y1);
			
			draw(x.right, size, height, layer-1);
		}
	}
	
	public static void main(String[] args) {
		BSTNR<String, Integer> bst = new BSTNR<>();
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
