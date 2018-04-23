package com.mum.edu.cs435.labs.lab09;

public final class PrimeChecker {

	private static final int FIRST_N_PRIMES = 1000;

	public static boolean IsPrime(int n) {

		if (n <= 1)
			return false;

		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		for (int i = 1; i < FIRST_N_PRIMES; i++) {
			if (IsPrime(i))
				System.out.println(i);
		}
	}

}
