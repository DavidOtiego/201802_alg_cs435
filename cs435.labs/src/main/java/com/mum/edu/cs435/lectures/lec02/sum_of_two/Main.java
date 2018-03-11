package com.mum.edu.cs435.lectures.lec02.sum_of_two;

public class Main {

	public static void main(String[] args) {
		int[] test = {3, 7, 2, 4, 6, 11};
		int z = 13;
		System.out.println(testSlow(test,z));
//		System.out.println(testFast(test, z));
//		System.out.println(testFaster(test, z));
	}
	
	public static boolean testSlow(int[] arr, int z) {
		return Slow.sumExists(arr, z);
	}
	
	public static boolean testFast(int[] arr, int z) {
		return Fast.sumExists(arr, z);
	}
	
	public static boolean testFaster(int[] arr, int z) {
		return Faster.sumExists(arr, z);
	}

}
