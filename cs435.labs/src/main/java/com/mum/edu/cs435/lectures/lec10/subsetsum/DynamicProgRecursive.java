package com.mum.edu.cs435.lectures.lec10.subsetsum;

import java.util.*;

public class DynamicProgRecursive {

	/**
	 * This is an implementation of a recursive version of the
	 * algorithm in which a memoization table is used. 
	 * 
	 * Using the table, the number of self-calls is drastically reduced.
	 * For the values used in the demo, using the table reduces
	 * the number of self-calls from 25 million to around 5000 (which
	 * is only a fraction of the number of actual subproblems -- about
	 * 20000). 
	 * 
	 */

	static int numSelfCalls = 0;
	static HashMap<Key, int[]> table = new HashMap<>();
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
		// test
		// ArrayList<Integer> test = new ArrayList<>();
		// test.add(2); test.add(5); test.add(123); test.add(48);
		// System.out.println(map.containsKey(test));
		// end test
		
		
		int k = 611;
		DynamicProgRecursive ss = new DynamicProgRecursive();
		int[] result = ss.subsetsum(test, k);
		System.out.println("Input values: " + Arrays.toString(test) + ", k = " + k);
		System.out.println("Indices: " + Arrays.toString(result));
		System.out.println("Values: " + Arrays.toString(valuesOf(test, result)));
		System.out.println();
		for (ArrayList<Integer> l : map.keySet()) {
			ArrayList<int[]> allArrs = map.get(l);
			System.out.println("examining " + l + " explored " + allArrs.size()
					+ " subsets ");
			// for(int i = 0; i < allArrs.size(); ++i) {
			// System.out.print(Arrays.toString(allArrs.get(i)) + ",   ");
			// }

		}
		System.out.println("Num self-calls: " + numSelfCalls);
		System.out.println("Num subproblems: " + (k + 1) * test.length);
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

		// for display
		ArrayList<Integer> Slist = arrToList(S);
		if (!map.containsKey(Slist)) {
			System.out.println("WARNING! List " + Slist
					+ " not found as key in map");
			map.put(Slist, new ArrayList<int[]>());
		}

		// See if restricting T to {0,1,...,n-2} is enough
		// First check if we have a result for these values in the table
		// If not, make another self-call
		Key key = new Key(S, k);
		int[] T = null;
		if(table.containsKey(key)) {
			T = table.get(key);
		} else {
			T = recSubsetSum(S, k);
			table.put(key, T);
			// for display
			ArrayList l = (ArrayList) map.get(Slist);
			l.add(T);
		}

		

		if (sum(T) == k)
			return T;
		else {
			// This step effectively ignores all elements of S that are too big
			if (k >= last) {
				//again, we check whether computation for (S,k-last) has already been done
				//if not, we do another self-call
				key = new Key(S, k-last);
				if(table.containsKey(key)) {
					T = table.get(key);
				} else {
					T = recSubsetSum(S, k - last);
					table.put(key, T);
					// for display
					ArrayList l = (ArrayList) map.get(Slist);
					l.add(T);
				}
				
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
	/**
	 * This serves as a key to the DP table of stored values
	 */
	 
	static class Key {
		Key(int[] arr, int k) {
			this.arr = arr;
			this.k = k;
		}
		int[] arr;
		int k;
		public boolean equals(Object ob) {
			if(ob == null) return false;
			if(!(ob instanceof Key)) return false;
			Key key = (Key)ob;
			return k == key.k && (Arrays.equals(arr, key.arr));
		}
		public int hashCode() {
			return 3 * k + 5 * Arrays.hashCode(arr);
		}
	}
}
