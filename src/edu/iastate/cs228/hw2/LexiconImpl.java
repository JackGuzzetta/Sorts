package edu.iastate.cs228.hw2;

import java.util.Arrays;
import java.util.Comparator;

public class LexiconImpl implements Lexicon, Comparator<String> {

	/***
	 * Lookup table mapping characters in lexicographical order to to their
	 * input order. This is public to support automated grading.
	 */
	public CharacterValue[] characterOrdering;

	/***
	 * Creates an array of CharacterValue from characterOrdering. Sorts it using
	 * Arrays.sort().
	 * 
	 * @param characterOrdering
	 *            character order from configuration file
	 */
	public LexiconImpl(char[] characterOrdering) {
		this.characterOrdering = new CharacterValue[characterOrdering.length];
		for (int i = 0; i < characterOrdering.length; i++) {
			this.characterOrdering[i] = new CharacterValue(i, characterOrdering[i]);
		}
		Comparator<CharacterValue> custom = new Comparator<CharacterValue>() {
			@Override
			public int compare(CharacterValue o1, CharacterValue o2) {
				return o1.character - o2.character;
			}
		};
		Arrays.sort(this.characterOrdering, custom);

	}

	/***
	 * Compares two words based on the configuration
	 * 
	 * @param a
	 *            first word
	 * @param b
	 *            second word
	 * @return negative if a<b, 0 if equal, postive if a>b
	 */
	@Override
	public int compare(String a, String b) {
		int sum = 0;
		char[] Aarr = a.toCharArray();
		char[] Barr = b.toCharArray();
		int smaller;
		if (Aarr.length < Barr.length)
			smaller = Aarr.length;
		else
			smaller = Barr.length;
		for (int i = 0; i < smaller; i++) {
			sum += getCharacterOrdering(Aarr[i]) - getCharacterOrdering(Barr[i]);
		}
		return sum;
	}

	/**
	 * Uses binary search to find the order of key.
	 * 
	 * @param key
	 * @return ordering value for key or -1 if key is an invalid character.
	 */
	public int getCharacterOrdering(char key) {

		if (isValid(key + "") != false) {
			int start = 0;
			int end = (characterOrdering.length) - 1;
			while (start <= end) {
				int mid = (start + end) / 2;
				if (key == characterOrdering[mid].character) {
					return characterOrdering[mid].value;
				}
				if (key < characterOrdering[mid].character) {
					end = mid - 1;
				} else {
					start = mid + 1;
				}

			}
		}
		return -1;

	}

	/**
	 * Searches characterOrdering for key via binary search. This is public only
	 * to facilitate automated grading.
	 * 
	 * @param characterOrdering
	 *            the specified sort order
	 * @param key
	 *            the search term
	 * @return ordering value for key or -1 if key is an invalid character.
	 */
	public static class CharacterValue {
		public int value;
		public char character;

		public CharacterValue(int value, char character) {
			this.value = value;
			this.character = character;
		}

		public boolean equals(Object o) {
			if (o == null || o.getClass() != this.getClass()) {
				return false;
			}
			CharacterValue x = (CharacterValue) o;
			int v1 = this.value;
			int v2 = x.value;
			char c1 = this.character;
			char c2 = x.character;
			if ((v1 == v2) && (c1 == c2))
				return true;

			else
				return false;

		}

	}

	/**
	 * Returns whether or not word is valid according to the alphabet known to
	 * this lexicon.
	 * 
	 * @param word
	 *            word to be checked.
	 *
	 * @return true if valid. false otherwise
	 */
	public boolean isValid(String word) {
		boolean valid = false;
		for (int i = 0; i < word.length(); i++) {
			for (int j = 0; j < characterOrdering.length; j++) {
				if (word.charAt(i) == characterOrdering[j].character)
					valid = true;
			}
		}
		return valid;

	}

}
