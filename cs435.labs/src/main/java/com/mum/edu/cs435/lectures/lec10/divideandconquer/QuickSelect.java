package com.mum.edu.cs435.lectures.lec10.divideandconquer;

import java.util.Arrays;
import java.util.Comparator;

/**
 * An implementation of QUICK-SELECT, a fast median selection algorithm
 * (expected time O(n)).
 */
public class QuickSelect {

	private static <T> void quickCheck(T[] data, int first, int afterLast,
			Comparator<T> comp, int pivot) {
		for (int i = first; i < pivot; i++)
			if (!(comp.compare(data[i], data[pivot]) <= 0))
				throw new RuntimeException("Argh!");
		for (int i = pivot + 1; i < afterLast; i++)
			if (!(comp.compare(data[pivot], data[i]) <= 0))
				throw new RuntimeException("Argh: " + data[pivot] + " "
						+ data[i]);
	}

	private static <T> void swap(T[] data, int i, int j) {
		T temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}

	/**
	 * The actual implementation. The algorithm reorders the elements in data,
	 * such that the data[num] element is the nth smallest element of the array.
	 * All smaller elements will come before that element, all larger elements
	 * will come after that element. Note that the range given determines which
	 * elements are actually looked at.
	 * 
	 * @param data
	 *            the array
	 * @param first
	 *            first element index to look at
	 * @param last
	 *            last element index to look at
	 * @param comparator
	 *            a total ordering of the array elements
	 * @param num
	 *            index of the element to be selected (think: nth smallest
	 *            element)
	 */
	public static <T> void quickSelect(T[] data, int first, int afterLast,
			Comparator<T> comparator, int num) {
		// System.out.println("ENTER: "+first+" "+afterLast+" "+num);

		if (afterLast < first)
			throw new IllegalArgumentException("afterLast < first");
		if (num < first)
			throw new IllegalArgumentException("num < first");
		if (num >= afterLast)
			throw new IllegalArgumentException("num >= afterLast");
		if (first == afterLast)
			return;

		if (first < 0)
			throw new ArrayIndexOutOfBoundsException("first < 0");
		if (afterLast > data.length)
			throw new ArrayIndexOutOfBoundsException("afterLast > data.length");

		int last = afterLast - 1;
		if (first + 3 <= last) {
			// System.out.println("A");
			int center = (first + last) / 2;
			if (comparator.compare(data[first], data[center]) > 0)
				swap(data, first, center);
			if (comparator.compare(data[first], data[last]) > 0)
				swap(data, first, last);
			if (comparator.compare(data[center], data[last]) > 0)
				swap(data, center, last);

			swap(data, center, last - 1);
			T pivot = data[last - 1];

			int i = first;
			int j = last - 1;
			while (true) {
				while (comparator.compare(data[++i], pivot) < 0) {/**/
				}
				while (comparator.compare(data[--j], pivot) > 0) {/**/
				}
				if (i < j)
					swap(data, i, j);
				else
					break;
			}
			swap(data, i, last - 1);

			// System.out.println(i+" "+java.util.Arrays.toString(data));
			// quickCheck(data, first, afterLast, comparator, i);

			if (num == i)
				return;
			else if (num < i)
				quickSelect(data, first, i, comparator, num);
			else if (num > i)
				quickSelect(data, i + 1, last + 1, comparator, num);
		} else {
			// System.out.println("B");
			for (int i = first + 1; i < afterLast; i++) {
				T tmp = data[i];
				int j;
				for (j = i; (j > first)
						&& (comparator.compare(data[j - 1], tmp) > 0); j--)
					data[j] = data[j - 1];
				data[j] = tmp;
			}
			// System.out.println(num+" "+java.util.Arrays.toString(data));
		}

		quickCheck(data, first, afterLast, comparator, num);
	}

	/**
	 * Convenience method, equivalent to
	 * <code>quickSelect(data, 0, data.length-1, comparator, num)</code>.
	 * 
	 * @see #quickSelect(T[], int, int, Comparator, int)
	 */
	public static <T> void quickSelect(T[] data, Comparator<T> comparator,
			int num) {
		quickSelect(data, 0, data.length, comparator, num);
	}

	/**
	 * Same as {@link #quickSelect(T[], int, int, Comparator, int)}, but uses
	 * the natural ordering of the elements.
	 */
	public static <T extends Comparable<T>> void quickSelect(T[] data,
			int first, int afterLast, int num) {
		quickSelect(data, first, afterLast, new Comparator<T>() {
			public int compare(T o1, T o2) {
				return o1.compareTo(o2);
			}
		}, num);
	}

	/**
	 * Same as {@link #quickSelect(T[], Comparator, int)}, but uses the natural
	 * ordering of the elements.
	 */
	public static <T extends Comparable<T>> void quickSelect(T[] data, int num) {
		quickSelect(data, 0, data.length, num);
	}

	/* Changes algorithm here -- returns the kth smallest element (instead of kth biggest)
	 * The boolean is just there to make the signature appear different -- either value
	 * for the boolean produces the same algorithm
	 * 
	 */
	public static <T extends Comparable<T>> T quickSelect(T[] data, int k,
			boolean dummy) {
		quickSelect(data, k - 1);
		return data[k - 1];
	}

	public static void main(String[] args) {
		String[] alphabet = { "a", "b", "c", "d", "e", "f", "g", "h" };
		final int SIZE = 10;
		Comparable[] vals = new String[SIZE];
		for (int i = 0; i < SIZE; ++i) {
			// vals[i] = 2*i;
			// vals[i] = (int)(3*SIZE *Math.random());
			vals[i] = "";
			for (int j = 0; j < alphabet.length; ++j) {
				vals[i] = vals[i]
						+ alphabet[(int) (alphabet.length * Math.random())];
			}
		}
		System.out.println(Arrays.toString(vals));
		System.out.println(quickSelect(vals, 3, true));

	}

}