package edu.iastate.cs228.hw2;

import java.util.Comparator;

public abstract class SorterWithStatistics implements Sorter {
	private int wordsSorted;
	private int totalWordsSorted;
	private long time = 0;
	private long sortTime = 0;
	private Stopwatch timer = new Stopwatch();
	private int count = 0;

	/***
	 * Default constructor
	 */
	public SorterWithStatistics() {
		// TODO
	}

	/***
	 * Public interface to sortHelper that keeps track of performance
	 * statistics, including counting words sorted and timing sort instances.
	 * 
	 * @param words
	 *            input array to be sorted.
	 * @param comp
	 *            Comparator used to sort the input array.
	 */
	public void sort(String[] words, Comparator<String> comp) {
		sortTime = 0;
		if (time == 0)
			timer.start();
		else
			timer.resume();

		sortHelper(words, comp);
		timer.stop();
		sortTime = timer.getElapsedTime();
		time += timer.getElapsedTime();
		wordsSorted = words.length;
		totalWordsSorted += words.length;
		count++;
	}

	/**
	 * Sorts the array words.
	 * 
	 * @param words
	 *            input array to be sorted.
	 * @param comp
	 *            Comparator used to sort the input array.
	 */
	protected abstract void sortHelper(String[] words, Comparator<String> comp);

	/**
	 * Returns number of words sorted in last sort. Throws IllegalStateException
	 * if nothing has been sorted.
	 * 
	 * @return number of words sorted in last sort.
	 */
	public int getWordsSorted() {
		// TODO: return a meaningful result.
		return wordsSorted;
	}

	/**
	 * Returns time the last sort took. Throws IllegalStateException if nothing
	 * has been sorted.
	 * 
	 * @return time last sort took.
	 */
	public long getTimeToSortWords() {
		return sortTime;
	}

	/**
	 * Returns total words sorted by this instance.
	 * 
	 * @return total number of words sorted.
	 */
	public int getTotalWordsSorted() {
		// TODO: return a meaningful result.
		return totalWordsSorted;
	}

	/**
	 * Returns the total amount of time spent sorting by this instance.
	 * 
	 * @return total time spent sorting.
	 */
	public long getTotalTimeToSortWords() {
		return time;
	}

	/**
	 * @return a summary of statistics for the last sorting run.
	 * 
	 *         See the project description for details about what to include.
	 *         This method should NOT generate output directly.
	 */
	public String getReport() {
		String report = "Length of wordlist = " + getWordsSorted() + "\n" + "Total number of words sorted = "
				+ getTotalWordsSorted() + "\n" + "Total time spent sorting = " + getTotalTimeToSortWords() + "\n"
				+ "Average time required to sort the word list = " + getTotalTimeToSortWords()/count + "\n"
				+ "Words sorted per second = " + getTotalWordsSorted()/(getTotalTimeToSortWords() / 1000000.0);
//		wordsSorted = 0;
//		totalWordsSorted = 0;
//		time =0;
//		sortTime=0;
		return report;
	}
}
