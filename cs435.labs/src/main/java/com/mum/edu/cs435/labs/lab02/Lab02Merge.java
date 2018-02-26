package com.mum.edu.cs435.labs.lab02;

public class Lab02Merge {
	
	public static void main(String[] args) {
		int[] a = new int[] { 1, 4, 5, 8, 17 };
		int[] b = new int[] { 2, 4, 8, 11, 13, 21, 23, 25 };

		int[] m = merge(a, b);

		for (int i : m) {
			System.out.print(i + " ");
		}
	}
	
	public static int[] merge(int[] a, int[] b) {
		int[] m = new int[a.length + b.length];
		int aIndex = 0;
		int bIndex = 0;
		int mIndex = 0;

		while (aIndex < a.length && bIndex < b.length) {
			if (a[aIndex] < b[bIndex])
				m[mIndex++] = a[aIndex++];
			else
				m[mIndex++] = b[bIndex++];
		}

		if (aIndex < a.length - 1) {
			System.arraycopy(a, aIndex, m, mIndex, m.length - mIndex);
		}

		if (bIndex < b.length - 1) {
			System.arraycopy(b, bIndex, m, mIndex, m.length - mIndex);
		}

		return m;
	}
}
