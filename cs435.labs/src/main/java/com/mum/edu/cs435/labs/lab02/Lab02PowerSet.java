package com.mum.edu.cs435.labs.lab02;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lab02PowerSet {

	public static void main(String[] args) {

	}

	public static <T> Set<List<T>> powerSet(List<T> x) {
		Set<List<T>> p = new HashSet<List<T>>();

		List<T> s = new ArrayList<>();
		p.add(s);
		List<T> t = new ArrayList<>();

		while (!x.isEmpty()) {
			T f = x.remove(0);

			for (List<T> i : p) {
				
				p.add(t);
			}
		}

		return p;
	}

}
