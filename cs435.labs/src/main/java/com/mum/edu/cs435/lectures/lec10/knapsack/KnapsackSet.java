package com.mum.edu.cs435.lectures.lec10.knapsack;

import java.util.ArrayList;

public class KnapsackSet extends AbstractKnapsack {
	ArrayList[] A;  //array of lists; each list contians int[] array
	int[] w;
	int[] v;
	int W;
	
	int n;
	

	public int[] knapsack(int[] w, int[] v, int W) {
		this.w = w;
		this.v = v;
		this.W = W;
		if(w == null || v==null || w.length != v.length) {
		    return Constants.NULL;
		}
		if(nonpositive(w) || nonpositive(v) || W <= 0) {
			return Constants.NULL;
		}
		if(w.length == 0) { //sum of the w's from emptyset is 0, which is < W; benefit is then 0
			return Constants.EMPTY_SET;
		}
		
		n = w.length;
		A = new ArrayList[n];
		initializeMatrix();
		fillFirstRow();
		fillRemainingRows();
		return (int[])A[n-1].get(W);
	}
	boolean nonpositive(int[] arr) {
		for(int x : arr) {
			if(x <= 0) return true;
		}
		return false;
	}
	/**
	 * Fills matrix with NULLs. 
	 */
	void initializeMatrix() {
		for(int i = 0; i < n; ++i) {
			A[i] = new ArrayList();
			for(int j = 0; j <= W; ++j) {
				A[i].add(Constants.NULL);
			}
		}
	}
	
	/**
	 * Follows algorithm for loading the first row.
	 * A[0,0] is emptyset. A[0,s0] is {0}. All others
	 * are NULL.
	 */
	void fillFirstRow() {
		for(int j = 0; j <= W; ++j) {
			if(j < w[0]) A[0].set(j, Constants.EMPTY_SET);
			else A[0].set(j, Constants.ZERO_ELEMENT);
		}
	}
	
	/**
	 * Computes value of rows 1 to n-1 using the algoirthm.
	 */
	void fillRemainingRows() {
		for(int i = 1; i < n; ++i) {		
			for(int j = 0; j <= W; ++j) {
				int[] useOld = (int[])A[i-1].get(j);
				int[] useNew = useOld;
				if(j - w[i] >= 0) {
					useNew = ((int[])A[i-1].get(j-w[i]));
					useNew = adjoin(useNew, i);
				}
				//using sum is not optimal; better to keep track of current
				//benefits in each cell (using recursion for KnapsackBenefit)
				int benefitOld = sum(useOld);
				int benefitNew = sum(useNew);
				A[i].set(j, ((benefitOld >= benefitNew) ? useOld : useNew));
			}

		}	
	}
	
	/**
	 * Support utility for summing all values of v having
	 * indices in T
	 */
	int sum(int[] T) {
		if(T == null || T == Constants.NULL) return -1;
		if(T.length == 0) return 0;
		int m = T.length;
		int accum = 0;
		for(int r = 0; r < m; ++r) {
			int nextIndex = T[r];
			accum += v[nextIndex];
		}
		return accum;
	}
	
	/**
	 * Support utility that copies values of T into a new array and
	 * appends i to the end.
	 */
	int[] adjoin(int[] T, int i) {
		if(T == null) return null;
		int[] newarr = new int[T.length + 1];
		for(int r = 0; r < T.length; ++r) {
			newarr[r] = T[r];
		}
		newarr[T.length] = i;
		return newarr;
	}
	
	void print() {
		for(int i = 0; i < A.length; ++i) {
			System.out.println("Row " + i + ":");
			System.out.print("  ");
			int j = 0;
			for(; j<A[i].size()-1; ++j) {
				Util.prnt((int[])A[i].get(j));
				System.out.print(", ");
			}
			Util.prntln((int[])A[i].get(j));
		}
	}
	
	public static void main(String[] args) {
		KnapsackSet ks = new KnapsackSet();
		int[] w = {3,1,3,5};
		int[] v = {4,2,3,2};
		int W = 7;
		ks.knapsack(w, v, W);
		ks.print();
	}
	
	
}
