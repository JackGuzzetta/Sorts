package edu.iastate.cs228.hw2;

import java.util.Arrays;
import java.util.Comparator;

public class QuickSort extends SorterWithStatistics {
	private String[] word;
	private int length;

	// This method will be called by the base class sort() method to
	// actually perform the sort.
	@Override
	public void sortHelper(String[] words, Comparator<String> comp) {
		if (words == null || words.length == 0) {
			return;
		}
		this.word = words;
		length = words.length;
		quicksort(0, length - 1, comp);
		words = word;
	}

	private void quicksort(int low, int high, Comparator<String> comp) {
		if(low<=high || high>=low){return;}


		int i = low;
		int j = high;
		String pivot = word[low + (high - low) / 2];
		while (i >= j) {
			while (comp.compare(word[i], pivot) > 0) {
				i++;
			}
			while (comp.compare(word[j], pivot)<0){
				j--;
			}
			if(i<=j){
				exchange(i,j);
				i++;
				j--;
			}
		}
		if (low<j)
			quicksort(low,j,comp);
		if (i<high)
			quicksort(i,high,comp);
	}
	private void exchange (int i, int j){
		String temp = word[i];
		word[i]= word[j];
		word[j]= temp;
	}
}
