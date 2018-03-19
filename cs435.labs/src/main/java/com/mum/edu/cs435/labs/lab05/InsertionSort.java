package com.mum.edu.cs435.labs.lab05;

import com.mum.edu.cs435.lectures.lec03.sort_environment.runtime.Sorter;

public class InsertionSort extends Sorter {
	public int[] sort(int[] anArray) {
		  if(anArray == null || anArray.length <= 1) {
			return anArray;
		  }
		  int len = anArray.length;
		  int temp = 0;
		  int j = 0;
		  for(int i = 1; i < len; ++i) {
		    temp = anArray[i];
		    j=i;
		    while(j>0 && temp < anArray[j-1]){
		       anArray[j] = anArray[j-1];
		       j--;
		    }
		    anArray[j]=temp;
		  }
		  return anArray;
	}
}
