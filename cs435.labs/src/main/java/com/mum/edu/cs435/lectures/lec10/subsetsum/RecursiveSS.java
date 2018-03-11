package com.mum.edu.cs435.lectures.lec10.subsetsum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * This is an implementation of the exponential (recursive) version of the
 * algorithm. This recursive version has the advantage of making evident what
 * the subproblems are.
 * 
 * Subproblems are finding the two possible summations over initial segments of
 * {0,1,...,n-1}, checking if either is equal to k.
 * 
 * Running time: In the worst case, k is the sum of all numbers in S. We can
 * count self-calls to show there are at least 2^n of them, and that therefore
 * running time is exponential.
 * 
 * In each self-call, two other self-calls are made. At least O(1) work is done
 * in each self-call. Self-calls continue testing sets {s_0, s_1...s_n-1}, {s_0,
 * s_1, ..., s _n-2}, {s_0, s_1,...s_n-3}, . . . , {s_0}. This gives us (in the
 * worst case) a completely filled binary tree; since height is n, number of
 * nodes is > 2^n. Each node represents a self-call. Therefore, T(n) is
 * Omega(2^n).
 */
public class RecursiveSS {
	static int numSelfCalls = 0;
	// public static void main(String[] args) {
	// int[] test = {124, 131, 111, 95, 88, 2, 4};
	// int k = 185;
	// RecursiveSS ss = new RecursiveSS();
	// System.out.println(Arrays.toString(ss.subsetsum(test, k)));
	// }
	static int[] test = { 2, 5, 123, 48, 29, 19, 34, 10, 20, 88, 47, 19, 21,
			42, 4, 8, 3, 7, 5, 9, 14, 26, 25, 31, 33, 18, 44, 35, 48, 53, 51,
			61, 69, 72 };
	// for display only
	static HashMap<ArrayList<Integer>, ArrayList<int[]>> map = new HashMap<>();
	static {

		for (int i = 0; i < test.length; ++i) {
			ArrayList<Integer> next = new ArrayList<>();
			ArrayList<int[]> value = new ArrayList<>();
			for (int j = 0; j < i; ++j) {
				next.add(test[j]);
			}
			map.put(next, value);
		}
	}

	public static void main(String[] args) {
		// int[] test = {2, 5, 123, 48, 29, 19, 34, 10, 20, 88, 47, 19, 21, 42,
		// 4, 8, 3, 7, 5, 9, 14, 26, 25, 31, 33, 18, 44,35, 48, 53, 51, 61, 69,
		// 72};
		// int[] test = {1,3,5,2,4};
		//test
//		ArrayList<Integer> test = new ArrayList<>();
//		test.add(2); test.add(5); test.add(123); test.add(48);
//		System.out.println(map.containsKey(test));
		//end test
		int k = 611;
		RecursiveSS ss = new RecursiveSS();
		
		int[] result = ss.subsetsum(test, k);
		System.out.println("Input values: " + Arrays.toString(test) + ", k = " + k);
		System.out.println("Indices: " + Arrays.toString(result));
		System.out.println("Values: " + Arrays.toString(valuesOf(test, result)));
		System.out.println();
		
		
		for(ArrayList<Integer> l : map.keySet()) {
			ArrayList<int[]> allArrs = map.get(l);
			System.out.println("examining " + l + " explored " + allArrs.size() + " subsets ");
//			for(int i = 0; i < allArrs.size(); ++i) {
//				System.out.print(Arrays.toString(allArrs.get(i)) + ",   ");
//			}
			
		}
		System.out.println("Num self-calls: " + numSelfCalls);
		System.out.println("Num subproblems: " + (k+1)* test.length);
	}
	
	private static Integer[] valuesOf(int[] originalArray, int[] indices) {
		List<Integer> retval = new ArrayList<Integer>();
		for(int i = 0; i < indices.length; ++i) {
			retval.add(originalArray[indices[i]]);
		}
		return retval.toArray(new Integer[0]);
	}

	int[] origS;

	int[] subsetsum(int[] S, int k) {
		this.origS = S;
		return recSubsetSum(makeCopy(S), k);
	}

	int[] recSubsetSum(int[] S, int k) {
		++numSelfCalls;
		// System.out.println("S = " + Arrays.toString(S) + " k = " + k);
		// System.out.println("S = " + Arrays.toString(S));
		if (S == null || S.length == 0) {
			if (k == 0)
				return Constants.EMPTY_SET;
			else
				return Constants.NULL;
		}
		int n = S.length;
		int last = S[n - 1];
		S = removeLast(S);
		
		//for display
		ArrayList<Integer> Slist = arrToList(S);
		if(!map.containsKey(Slist)) {
			System.out.println("WARNING! List " + Slist + " not found as key in map");
			map.put(Slist, new ArrayList<int[]>());
		}

		// See if restricting T to {0,1,...,n-2} is enough
		int[] T = recSubsetSum(S, k);
		
		//for display
		ArrayList l = (ArrayList)map.get(Slist);
		l.add(T);
		
		if (sum(T) == k)
			return T;
		else {
			// This step effectively ignores all elements of S that are too big
			if (k >= last) {
				T = recSubsetSum(S, k - last);
				l.add(T);
				if (sum(T) == k - last)
					return adjoin(T, n - 1);
				else
					return Constants.NULL;
			} else
				return Constants.NULL;
		}

	}

	/**
	 * Support utility for summing all values of S having indices in T
	 */
	int sum(int[] T) {
		if (T == null || T == Constants.NULL)
			return -1;
		if (T.length == 0)
			return 0;
		int m = T.length;
		int accum = 0;
		for (int r = 0; r < m; ++r) {
			int nextIndex = T[r];
			accum += origS[nextIndex];
		}
		return accum;
	}

	/**
	 * Support utility that copies values of T into a new array and appends i to
	 * the end.
	 */
	int[] adjoin(int[] T, int i) {
		if (T == null)
			return null;
		int[] newarr = new int[T.length + 1];
		for (int r = 0; r < T.length; ++r) {
			newarr[r] = T[r];
		}
		newarr[T.length] = i;
		return newarr;
	}

	int[] removeLast(int[] arr) {
		if (arr == null || arr.length <= 0)
			return arr;
		int[] retval = new int[arr.length - 1];
		for (int i = 0; i < retval.length; ++i) {
			retval[i] = arr[i];
		}
		return retval;

	}

	int[] makeCopy(int[] arr) {
		if (arr == null)
			return arr;
		int[] retval = new int[arr.length];
		for (int i = 0; i < arr.length; ++i) {
			retval[i] = arr[i];
		}
		return retval;
	}

	// for demo purposes
	static ArrayList<Integer> arrToList(int[] arr) {
		ArrayList<Integer> ls = new ArrayList<>();
		for (int i : arr) {
			ls.add(i);
		}
		return ls;

	}
}
