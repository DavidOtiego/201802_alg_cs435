package com.mum.edu.cs435.labs.lab08;

import java.util.LinkedList;

public class MinStack<T extends Comparable<T>> {

	LinkedList<T> items = new LinkedList<T>();
	private LinkedList<T> mins = new LinkedList<T>();

	public T getMin() {
		return mins.getLast();
	}

	public void setMin(T min) {
		if (isEmpty(mins)) {
			mins.addFirst(min);

		} else if (min.compareTo(mins.getLast()) <= 0) {
			mins.add(min);

		}
	}

	public boolean isEmpty() {
		if (items.isEmpty()) {
			return true;
		}

		return false;
	}

	private boolean isEmpty(LinkedList<T> values) {
		if (values.isEmpty()) {
			return true;
		}

		return false;
	}

	public void push(T value) {
		items.addLast(value);
		setMin(value);
	}

	public T pop() throws Exception {
		if (isEmpty()) {
			throw new Exception("The stack is empty");
		}

		T result = items.getLast();

		items.removeLast();

		if (result == mins.getLast())
			mins.removeLast();

		return result;
	}

	public T peek() throws Exception {
		if (isEmpty()) {
			throw new Exception("The stack is empty");
		}

		return items.getLast();
	}

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		MinStack<Integer> ms = new MinStack<>();
		
		sb.append("[0 -> 2 -> 1]");
		sb.append(System.getProperty("line.separator"));
		
		ms.push(1);
		ms.push(2);
		ms.push(0);

		while (!ms.isEmpty()) {
			try {
				sb.append("Min: ");
				sb.append(ms.getMin());
				sb.append(System.getProperty("line.separator"));
				sb.append("Pop: ");
				sb.append(ms.pop().toString());
				sb.append(" ");
				sb.append(System.getProperty("line.separator"));

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		System.out.println(sb.toString());
	}

}
