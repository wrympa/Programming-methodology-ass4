
/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Hangman extends ConsoleProgram {
	public RandomGenerator rgen = RandomGenerator.getInstance();

	public static String current;
	private String alphabet = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
	private String backup;
	public static String word;
	public static String used = "";
	private String a;
	public static String wrongs = "";
	public static char guess;
	private char dash = '-';
	private int length;
	public static int tries = 8;
	private int letters = 0;
	public static boolean done = false;
	private boolean used1 = false;
	private boolean first = false;
	private boolean izletr = true;
	private boolean wrongagain = false;
	public static boolean course = false;
	private HangmanCanvas canvas;
	private int max=0;

	public void setup(){
		done=false;
		if(course==false)
		{
		try {
			max = HangmanLexicon.getWordCount();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		}
		int x = rgen.nextInt(0, max /* HangmanLexicon.amount */);
		try {
			word = HangmanLexicon.getWord(x);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(course==true)
		{
			wrongs="";
			tries=8;
			used="";
			backup="";
			used1=false;
			first=false;
			izletr=true;
			wrongagain=false;
			length=0;
			letters=0;
		}
		println("you are playing hangman. you have 8 guesses.");
		println("the word now looks like: " + current);
		println(word);
		length = word.length();
		current = "";
		for (int i = 0; i < length; i++) {
			current = current + "-";
		}
		canvas.drawman(tries);
	}

	public void check(String a) {
		if (first == false) {
			first = true;
		} else {
			for (int i = 0; i < used.length() + 0; i++) {
				if (a.charAt(0) == used.charAt(i) || (char) (a.charAt(0) - 32) == used.charAt(i)
						|| (char) (a.charAt(0) + 32) == used.charAt(i)) {
					used1 = true;
				}
			}
		}
	}

	public void wrongegen(String a) {
		for (int i = 0; i < wrongs.length(); i++) {
			if (a.charAt(0) == wrongs.charAt(i) || (char) (a.charAt(0) - 32) == wrongs.charAt(i)
					|| (char) (a.charAt(0) + 32) == wrongs.charAt(i)) {
				wrongagain = true;
				break;
			} else {
				wrongagain = false;
			}
		}
	}

	public void process() {
		if (letters != length && tries != 0) {
			HangmanCanvas.crnt = current;
			HangmanCanvas.used = used;
			for (int i = 0; i < length; i++) {
				if (guess == word.charAt(i) || (char) (a.charAt(0) - 32) == word.charAt(i)) {
					backup = current;
					current = "";
					for (int b = 0; b < length; b++) {
						if (guess == word.charAt(b) || (char) (a.charAt(0) - 32) == word.charAt(b)) {
							if (a.charAt(0) - 32 == word.charAt(b)) {
								current = current + (char) (guess - 32);
								letters++;
							} else {
								current = current + guess;
								letters++;
							}
						} else if (backup.charAt(b) != dash) {
							current = current + backup.charAt(b);
						} else {
							current = current + "-";
						}
					}
					if (letters == length) {
						println(current);
						HangmanCanvas.crnt = current;
						HangmanCanvas.used = used;
						win();
						done = true;
					} else if (done == false) {
						println(current);
						HangmanCanvas.crnt = current;
						HangmanCanvas.used = used;
						println("keep it up. you have " + tries + " tries left.");
						canvas.drawman(tries);
						game();
					}
				} else if (i == length - 1 && done == false) {
					tries--;
					canvas.drawman(tries);
					wrongs = wrongs + guess;
					if (tries == 0) {
						loss();
						done = true;
					} else if (done == false) {
						println("miss. you have " + tries + " tries left.");
						HangmanCanvas.crnt = current;
						HangmanCanvas.used = used;
						game();

					}
				}
			}
		}
	}

	public void isLetter(char a) {
		for (int i = 0; i < alphabet.length(); i++) {
			if (a != alphabet.charAt(i)) {
				izletr = false;
			} else if (a == alphabet.charAt(i)) {
				izletr = true;
				break;
			}
		}
	}

	public void game() {
		if (done == false) {
			a = readLine("enter your guess: ");

			if (a.length() != 1) {
				while (a.length() != 1) {
					a = readLine("enter ONE letter: ");
				}
			}

			isLetter(a.charAt(0));

			if (izletr == false) {
				println("enter a letter");
				izletr = true;
				game();
			} else {
				check(a);
				if (used1 == true) {
					wrongegen(a);
					if (wrongagain == true) {
						tries--;
						if (tries == 0) {
							println("miss. dont't enter the same wrong answer again. you have " + tries
									+ " tries left.");
							println(current);
							HangmanCanvas.crnt = current;
							HangmanCanvas.used = used;
							canvas.drawman(tries);
							loss();
							done = true;
						} else {
							println("miss. dont't enter the same wrong answer again. you have " + tries
									+ " tries left.");
							println(current);
							HangmanCanvas.crnt = current;
							HangmanCanvas.used = used;
							canvas.drawman(tries);
							used1 = false;
							game();
						}
					} else {
						println("enter a letter yuo haven't used. ");
						used1 = false;
						game();
					}
				} else {
					guess = a.charAt(0);
					used = used + " " + a;
					process();
				}
			}
		}
	}

	public void init() {
		canvas = new HangmanCanvas();
		add(canvas);
	}

	public void win() {
		if (done == false) {
			println("yuo win");
			canvas.drawman(tries);
			canvas.displayWord("the word was " + word);
			done = true;
			println("wanna try again??? 1 for yes 0/ anything else for no");
			int choice = readInt();
			if (choice == 1) {
				course = true;
				canvas.drawman(tries);
				course = true;
				run();
			} else {
				exit();
			}
		}
	}

	public void loss() {
		if (done == false) {
			println("the word was " + word);
			canvas.drawman(tries);
			canvas.displayWord("the word was " + word);
			println("yuo loss");
			done = true;
			println("wanna try again??? 1 for yes 0/ anything else for no");
			int choice = readInt();
			if (choice == 1) {
				course = true;
				canvas.drawman(tries);
				course = true;
				run();
			} else {
				exit();
			}
		}
	}

	public void run() {
		setup();
		game();
	}

}
