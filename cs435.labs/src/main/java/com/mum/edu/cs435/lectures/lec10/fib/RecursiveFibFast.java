package com.mum.edu.cs435.lectures.lec10.fib;

public class RecursiveFibFast {
	Integer[] table;
	public int fib(int n) {
		//System.out.println("Call to fib at " + n);
		if(n < 0) return -1;
		if(n==0 || n == 1) return n;
		
		//set up table for storing computations
		table = new Integer[n + 1];
		table[0] = 0;
		table[1] = 1;
		return recursiveFib(n);
	}
	private int recursiveFib(int n) {
		System.out.println("Self-call at n = " + n);
		if(table[n-1] == null) {
			table[n-1] = recursiveFib(n-1);
		}
		table[n] = table[n-1] + table[n-2];
		return table[n];
	}

	
	public static void main(String[] args) {
		RecursiveFibFast rff = new RecursiveFibFast();
		System.out.println(rff.fib(3));
		

		
	}
}
