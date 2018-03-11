package com.mum.edu.cs435.lectures.lec10.subsetsum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * NOTE: This returns the set of indices that works.
 * 
 * This is the same as RecursiveSS, but without
 * all the print statements. This is the core of that program
 * that does the computation.
 *
 */
public class RecursiveSS_Clean {
	static int[] test = { 2, 5, 123, 48, 29, 19, 34, 10, 20, 88, 47, 19, 21,
			42, 4, 8, 3, 7, 5, 9, 14, 26, 25, 31, 33, 18, 44, 35, 48, 53, 51,
			61, 69, 72 };
	//static int[] test = {2, 4, 7};

	public static void main(String[] args) {
		int k = 611;
		//int k = 6;
		RecursiveSS ss = new RecursiveSS();
		int[] result = ss.subsetsum(test, k);
		System.out.println("Input values: " + Arrays.toString(test) + ", k = " + k);
		System.out.println("Indices: " + Arrays.toString(result));
		System.out.println("Values: " + Arrays.toString(valuesOf(test, result)));
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
		if (S == null || S.length == 0) {
			if (k == 0)
				return Constants.EMPTY_SET;
			else
				return Constants.NULL;
		}
		int n = S.length;
		int last = S[n - 1];
		S = removeLast(S);

		// See if restricting T to {0,1,...,n-2} is enough
		int[] T = recSubsetSum(S, k);
		if (sum(T) == k)
			return T;
		else {
			// This step effectively ignores all elements of S that are too big
			if (k >= last) {
				T = recSubsetSum(S, k - last);
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
}
