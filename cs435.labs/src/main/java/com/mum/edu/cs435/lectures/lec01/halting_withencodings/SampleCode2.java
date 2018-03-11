package com.mum.edu.cs435.lectures.lec01.halting_withencodings;

public class SampleCode2 {
	public static final String SAMPLE = 
			"public class Endless {\n" +
		    "  public java.math.BigInteger aMethod(java.math.BigInteger x) {\n "  +
			"    int w = x.intValue() + 3;\n" +
			"    while(true){}\n" +
			"  }\n" +
			"}";
}
