package com.mum.edu.cs435.labs.lab05;

import java.util.List;

public class SelfAware {

	public static final int[] NOT_SELF_AWARE_ARRAY = { 1, 2, 3, 4 };
	public static final int[] SELF_AWARE_ARRAY = { 2, 0, 2, 0 };
	public static final int SIZE_OF_SELF_AWARE_ARRAYS = 5;

	public static void main(String[] args) {
		System.out.println("Expected true: " + isSelfAware(SELF_AWARE_ARRAY));
		System.out.println("Expected false: " + isSelfAware(NOT_SELF_AWARE_ARRAY));
		printArrays(isSelfAware(SIZE_OF_SELF_AWARE_ARRAYS));
	}

	public static boolean isSelfAware(int[] arr) {

		for (int i = 0; i < arr.length; i++) {
			int count = 0;

			for (int j = 0; j < arr.length; j++) {
				if (arr[j] == i)
					count++;
			}

			if (arr[i] != count)
				return false;
		}

		return true;
	}

	public static List<int[]> isSelfAware(int n) {
		// TODO: Implement a list of all self aware arrays of size n
		return null;
	}

	public static void printArrays(List<int[]> listOfArrays) {
		listOfArrays.forEach(x -> {
			for (int i : x) {
				System.out.print(i + " ");
			}
		});
	}

}
