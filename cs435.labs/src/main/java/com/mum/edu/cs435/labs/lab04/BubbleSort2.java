package com.mum.edu.cs435.labs.lab04;

import java.util.Arrays;
import com.mum.edu.cs435.lectures.lec03.sort_environment.runtime.Sorter;

/**
 * Optimization for BubbleSort.java. Takes into account the fact that largest
 * elements are pushed to the right.
 */
public class BubbleSort2 extends Sorter {

	int[] arr;

	public int[] sort(int[] array) {
		this.arr = array;
		bubbleSort();
		return arr;

	}

	private void bubbleSort() {

		int len = arr.length;
		for (int i = 0; i < len; ++i) {
			boolean noSwap = true;
			for (int j = 0; j < len - i - 1; ++j) {
				if (arr[j] > arr[j + 1]) {
					swap(j, j + 1);
					noSwap = false;
				}
			}
			
			if (noSwap) {
				break;
			}
		}
	}

	int[] swap(int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
		return arr;

	}

	public static void main(String[] args) {
		int[] test = { 21, 13, 1, -22, 51, 5, 18 };
		BubbleSort2 bs = new BubbleSort2();

		System.out.println(Arrays.toString(bs.sort(test)));

	}

}
