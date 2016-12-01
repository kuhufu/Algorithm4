import java.io.File;
import java.util.*;

import org.omg.Messaging.SyncScopeHelper;

import edu.princeton.cs.algs4.*;
import search.BinarySearch;

public class Test {
	public static void main(String[] args) {
		String[] s = {"12", "12", "34", "34"};
		
		String[] t = deldup(s);
		
		for(int i = 0; i < t.length; i++){
			System.out.println(t[i]);
		}
	}
	
	public static String[] deldup(String[] a){
		String[] b = a.clone();
		Arrays.sort(b);
		
		int count = 1;
		for(int i = 1; i < b.length; i++){
			if(!b[i].equals(b[i-1])) count++;
		}
		
		String[] newa = new String[count];
		newa[0] = b[0];
		for(int i = 1, j = 1; i <= count; i++){
			if(!b[i].equals(b[i-1])) newa[j++] = b[i];
		}
		return newa;
	}
}