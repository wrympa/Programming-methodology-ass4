/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import acm.util.*;
import acmx.export.java.io.FileReader;
import acmx.export.java.util.ArrayList;

public class HangmanLexicon {
	public static ArrayList st;
	public static int amount=0;
/** Returns the number of words in the lexicon. 
 * @throws FileNotFoundException */
	public static int getWordCount() throws FileNotFoundException {
		BufferedReader rd = new BufferedReader(new FileReader("./HangmanLexicon.txt"));
		st = new ArrayList();
		String wrds= null;
		try {
			while((wrds=rd.readLine())!=null)
			{
			amount++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return amount;
	}

	
/** Returns the word at the specified index. 
 * @throws IOException */
	public static String getWord(int index) throws IOException {
		if(Hangman_extension.course==false)
		{
		@SuppressWarnings("resource")
		BufferedReader rd = new BufferedReader(new FileReader("./HangmanLexicon.txt"));
		st = new ArrayList();
		String wrds= null;
		while((wrds=rd.readLine())!=null)
		{
		st.add(wrds);
		amount++;
		}
		}
		return (String) st.get(index);
		//es sawyisi
		/*
		switch (index) {
			case 0: return "BUOY";
			case 1: return "COMPUTER";
			case 2: return "CONNOISSEUR";
			case 3: return "DEHYDRATE";
			case 4: return "FUZZY";
			case 5: return "HUBBUB";
			case 6: return "KEYHOLE";
			case 7: return "QUAGMIRE";
			case 8: return "SLITHER";
			case 9: return "ZIRCON";
			default: throw new ErrorException("getWord: Illegal index");
		}
		*/
	}
	
}
