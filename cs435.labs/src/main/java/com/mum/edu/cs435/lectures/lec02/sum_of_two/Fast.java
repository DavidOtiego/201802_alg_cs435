package com.mum.edu.cs435.lectures.lec02.sum_of_two;

import java.util.HashMap;

public class Fast {
	public static boolean sumExists(int[] arr, int z) {
		final Integer ZERO = new Integer(0);
		HashMap<Integer, Integer> h = new HashMap<>();
		for(int i = 0; i < arr.length; ++i) {
			h.put(z - arr[i], ZERO);
		}
		for(int j = 0; j < arr.length; ++j) {
			if(h.containsKey(arr[j]) && arr[j] != (z-arr[j])) {
				System.out.println("values found: " + arr[j] + ", " + (z-arr[j]));
				return true;
			}
		}
		return false;
	}
}
