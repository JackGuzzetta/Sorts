package edu.iastate.cs228.hw2;

import java.util.Arrays;
import java.util.Comparator;

public class MergeSort extends SorterWithStatistics {
	// This method will be called by the base class sort() method to
	// actually perform the sort.
	@Override
	public void sortHelper(String[] words, Comparator<String> comp) {
		if (words == null || words.length == 0)
			throw new IllegalArgumentException("Null pointer or zero size");
		int t;
		String[] tmp = new String[words.length];
		mergeSortRec(words, tmp, 0, words.length - 1, comp);
	}

	private static void mergeSortRec(String[] arr, String[] tmp, int first, int last, Comparator<String> comp) {
		if (first < 0 || last >= arr.length)
			throw new IllegalArgumentException("index out of bound");
		if (first >= last)
			return;
		int i, j, k;
		int mid = (first + last) / 2;
		mergeSortRec(arr, tmp, first, mid, comp);
		mergeSortRec(arr, tmp, mid + 1, last, comp);
		k = first;
		for (i = first, j = mid + 1; i <= mid && j <= last;) {
			if (comp.compare(arr[i], arr[j]) <= 0)
				tmp[k++] = arr[i++];
			else
				tmp[k++] = arr[j++];
		}
		while (i <= mid)
			tmp[k++] = arr[i++];
		while (j <= last)
			tmp[k++] = arr[j++];
		if (k != last + 1)
			throw new RuntimeException("Error in index");
		for (k = first; k <= last; k++)
			arr[k] = tmp[k];
	}

}
