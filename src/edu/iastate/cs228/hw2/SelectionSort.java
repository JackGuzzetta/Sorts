package edu.iastate.cs228.hw2;

import java.util.Arrays;
import java.util.Comparator;

public class SelectionSort extends SorterWithStatistics {
	
	//This method will be called by the base class sort() method to 
	// actually perform the sort. 
	@Override
	public void sortHelper(String[] words, Comparator<String> comp) {
		for(int i=0; i<words.length; i++){
			for (int m=0; m<words.length;m++){
				if(comp.compare(words[i],words[m])>0){
					String temp= words[i];
					words[i]=words[m];
					words[m]=temp;
				}
			}
		}
	}
}
