import java.util.HashMap;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;

public class Parse {
	String[] ss;
	HashMap<String, Integer> opsPri = new HashMap<>();
	Stack<String> ops = new Stack<>();
	Stack<Double> vals = new Stack<>();
	
	public Parse(){
		opsPri.put("+", 1);
		opsPri.put("-", 1);
		opsPri.put("*", 2);
		opsPri.put("/", 2);
		opsPri.put("sqrt", 3);
		
	}
	
	public Parse(String str){
		this();
		ss = str.split(" ");
	}
	
	public void initStack(){
		for (String s : ss) {
			
			if(s.equals("(")) continue;
			if(isOps(s)) ops.push(s);
			else if(s.equals(")")){
				String op = ops.pop();
				double v = vals.pop();
				if(op.equals("+")) v += vals.pop();
				else if(op.equals("-")) v -= vals.pop();
				else if(op.equals("*")) v *= vals.pop();
				else if(op.equals("/")) v /= vals.pop();
				else if(op.equals("sqrt")) v = Math.sqrt(v);
				
				vals.push(v);
			}else{
				vals.push(Double.parseDouble(s));
			}
			System.out.println(vals);
			System.out.println(ops);
		}
	}
	
	public double getValue(){
		return vals.pop();
	}
	
	private boolean isOps(String s){
		return opsPri.containsKey(s);
	}
}
