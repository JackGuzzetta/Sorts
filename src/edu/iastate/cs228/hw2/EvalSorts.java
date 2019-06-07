package edu.iastate.cs228.hw2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class EvalSorts {
	public static final int kNumberOfWordsToSort = 10000;

	/**
	 * Main is responsible for only:
	 * <UL>
	 * <LI>extracting file names from args (the character order file and the
	 * word file),</LI>
	 * <LI>reading the files, and</LI>
	 * <LI>constructing an instance of the app configured with the input
	 * data.</LI>
	 * </UL>
	 * All file related exceptions should be handled in main.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		// these are member variables local to main. (not directly visible to
		// the app instance.
		char[] alphabet = null; // main's ref to the Lexicon it creates.
		String[] wordList = null; // main's ref to the list of words to be
									// sorted.
		EvalSorts theApp = null; // main's ref to the app.
		LexiconImpl comp = null; // the concrete lexicon your app uses.
		/*
		 * 
		 * Here you should add code that extracts the file names from the args
		 * array, opens and reads the data from the files,constructs an instance
		 * of Lexicon from the character order file, and then create an instance
		 * of this class (EvalSorts) to act as a configured instance of the
		 * application. After you have constructed the configured instance, you
		 * should start it running (see below).
		 * 
		 * Note: in production code main() is NOT the application. It is onely
		 * the code that bridges from the operating system's default user
		 * interface (the shell's command line) and the application.
		 * 
		 * 
		 * 
		 */
		if (args.length != 2)
			throw new NullPointerException("Invalid array size: " + args.length);
		try {

			alphabet = readCharacterOrdering(args[0]);
			comp = new LexiconImpl(alphabet);
			wordList = readWordsFile(args[1], comp);
		} catch (FileNotFoundException e) {

			System.out.println("File Not Found :(");
		} catch (FileConfigurationException e) {

			e.printStackTrace();
		}
		// configure an instance of the app
		theApp = new EvalSorts(comp, wordList, kNumberOfWordsToSort);
		// now execute that instance
		theApp.runSorts();

	}

	// these are instance member variables belonging to the app (not main())
	private String[] words; // the app's private ref to the word lit
	private Lexicon lex; // the app's private ref to the relevant lexicon
	private int numWordsToSort = kNumberOfWordsToSort;

	/**
	 * This constructor configures an instance of EvalSorts to sort input read
	 * my main, using the character order read by main and now embedded in an
	 * instance of Lexicon
	 * 
	 * @param lex
	 *            the instance of lexicon to be used
	 * @param wordList
	 *            the wordlist (as array of string) to be sorted
	 * @param numWordsToSort
	 *            each sort will be repeated until it has sorted this many
	 *            words.
	 */
	public EvalSorts(Lexicon lex, String[] wordList, int numWordsToSort) {
		words = wordList;
		this.lex = lex;
		this.numWordsToSort = numWordsToSort;
	}

	/**
	 * runSorts() performs the sort evaluation.
	 * 
	 * Note: The three sorters extend a common base so they share the same
	 * interface for starting the sort and collecting statistics. Thus, you
	 * should create instances of the sorter and save references to each in an
	 * array of base type. This allows you to use a simple loop to run all the
	 * reports and collect the statistics. If you have
	 * 
	 * selectionsort.sort(); mergesort.sort(); quicksort.sort();
	 * 
	 * or three cut and paste blocks (one for each sort) in your solution, you
	 * are not exploiting the polymorphic behavior of the sorters and you will
	 * lose points for style.
	 */
	public void runSorts() {

		SorterWithStatistics[] sorters = new SorterWithStatistics[3];
		sorters[0] = new SelectionSort();
		sorters[1] = new MergeSort();
		sorters[2] = new QuickSort();
//		sorters[0].sort(words,lex);
//		System.out.println("Selection Sort");
//		System.out.println(sorters[0].getReport());
//		System.out.println("");
		System.out.println("Quick Sort");
		sorters[2].sort(words,lex);
		System.out.println(sorters[2].getReport());
		System.out.println("");
		System.out.println("Merge Sort");
		sorters[1].sort(words,lex);
		System.out.println(sorters[1].getReport());
//		for (int i = 0; i < 3; i++) {
//			sorters[i].sort(words, lex);
//
//		}
//		System.out.println(sorters[2].getReport());

		// TODO: run the sorters (repeatedly), collect statistics, and generate
		// a report (see the pdf for details).

	}

	/**
	 * Reads the characters contained in filename and returns them as a
	 * character array.
	 * 
	 * @param filename
	 *            the file containing the list of characters
	 * @returns an char array representing the ordering of characters to be used
	 *          or null if there is a FileNotFoundException.
	 */
	public static char[] readCharacterOrdering(String filename)
			throws FileNotFoundException, FileConfigurationException {
		try {
			File testFile = new File(filename);
			int counter = 0;
			Scanner inputFile = new Scanner(testFile);
			while (inputFile.hasNextLine()) {
				inputFile.nextLine();
				counter++;
			}

			inputFile = new Scanner(testFile);

			char[] words = new char[counter];
			int i = 0;
			while(inputFile.hasNextLine()) {
				String line = inputFile.nextLine();
				if ((line.length() > 1)) {
					throw new FileConfigurationException();
				}
				words[i] = line.charAt(0);
				i++;
			}
			return words;
		}

		catch (FileNotFoundException FNE) {
			return null;
		}
	}

	/**
	 * Reads the words from the file and returns them as a String array.
	 * 
	 * @param filename
	 *            file containing words
	 * @return the words contained in the file or null if there was a
	 *         FileNotFoundException.
	 */
	public static String[] readWordsFile(String filename, Lexicon comp)
			throws FileNotFoundException, FileConfigurationException {
		try {
			File testFile = new File(filename);
			int counter = 0;
			Scanner inputFile = new Scanner(testFile);
			while (inputFile.hasNextLine()) {
				
				counter++;
				inputFile.nextLine();
			}

			inputFile = new Scanner(testFile);

			String[] words = new String[counter];
			int i = 0;
			while (inputFile.hasNextLine()) {
				String line = inputFile.nextLine();
				if (!(comp.isValid(line))) {
					throw new FileConfigurationException();
				}
				words[i] = line;
				i++;
			}
			return words;
		} catch (FileNotFoundException FNE) {
			return null;
		}
	}

}
