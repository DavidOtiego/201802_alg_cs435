package com.mum.edu.cs435.lectures.lec02.sum_of_two;

import java.util.HashMap;

public class Faster {
	public static boolean sumExists(int[] arr, int z) {
		final Integer ZERO = new Integer(0);
		HashMap<Integer, Integer> h = new HashMap<>();
		for(int i = 0; i < arr.length; ++i) {
			if(h.containsKey(z - arr[i])) return true;
			if(!h.containsKey(arr[i])) h.put(arr[i], ZERO);
		}
		return false;
	}
	
	public static void main(String[] args) {
		System.out.println(sumExists(new int[]{2,3,5}, 7));
	}
}
