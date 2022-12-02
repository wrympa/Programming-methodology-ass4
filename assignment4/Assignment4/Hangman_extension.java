
import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.AudioClip;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Hangman_extension extends ConsoleProgram {
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
	private HangmanCanvas_extension canvas;
	private boolean oneshot = false;
	public static boolean course=false;
	private int max = 0;
	 AudioClip loss = MediaTools.loadAudioClip("loss.au");
	 AudioClip miss = MediaTools.loadAudioClip("vinethud.au");
	 AudioClip hit = MediaTools.loadAudioClip("elbebe.au");
	 AudioClip one = MediaTools.loadAudioClip("mg.au");
	
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
			oneshot=false;
			length=0;
			letters=0;
		}
		println("you are playing hangman. you have 8 guesses. Also, in this extension, you can enter the whole word if you think you know what it is. Do be careful since if you miss a whole guess, you lose.");
		println("the word now looks like: " + current);
		println(word);
		length = word.length();
		current = "";
		for (int i = 0; i < length; i++) {
			current = current + "-";
		}
		canvas.drawman(tries);
	}

	public void check(String a) {// naxulobs upper da lowr caseshi gamoyenebulia tu ara
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

	public void wrongegen(String a) {// amowmebs tu kvlav arasworia
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

	public void process() {// amowmebs asos/sityvas
		if (letters != length && tries != 0) {
			HangmanCanvas_extension.crnt = current;
			HangmanCanvas_extension.used = used;
			for (int i = 0; i < length; i++) {
				if (a.length() == length) { // amowmebs tu motamashe cdilobs bolomde gamoicnos sityva 96 xazamde
					for (int y = 0; y < length; y++) {
						if (a.charAt(y) == word.charAt(y) || (char) (a.charAt(y) - 32) == word.charAt(y)) {
							oneshot = true;
						} else {
							oneshot = false;
							tries = 0;
							canvas.drawman(tries);
							loss();
							break;
						}
					}
					if (oneshot == true) {
						one.play();
						win();
						break;
					}
					break;
				} else if (guess == word.charAt(i) || (char) (a.charAt(0) - 32) == word.charAt(i)) { // amowmebs aso-aso lower/upper casebs aignorebs																			// casebs)
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
							current = current + "-"; // es adgens currents
						}
					}
					if (letters == length) {// mogebis ekrani
						println(current);
					
						win();
						done = true;
					} else if (done == false) {// tamashi midis
						println(current);
						println("keep it up. you have " + tries + " tries left.");
						hit.play();
						HangmanCanvas_extension.crnt = current;
						HangmanCanvas_extension.used = used;
						canvas.drawman(tries);
						game();
					}
				} else if (i == length - 1 && done == false) { // tu arasworia
					tries--;
					HangmanCanvas_extension.crnt = current;
					HangmanCanvas_extension.used = used;
					canvas.drawman(tries);
					wrongs = wrongs + guess;
					if (tries == 0) {// tu svlebi amogewura
					
						loss();
						done = true;
					} else if (done == false) { // tu svlebi dagrcha
						println("miss. you have " + tries + " tries left.");
						miss.play();
						game();

					}
				}
			}
		}
	}

	public void isLetter(char a) {// amowmebs asoa tu ara
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
			a = readLine("enter your guess: ");// igebs inputs

			if (a.length() != 1 && a.length() != length) {// tu arc 1 asoa da arc bolomde xsnis sityvas
				while (a.length() != 1) {
					a = readLine("enter ONE letter: ");
				}
			}

			isLetter(a.charAt(0));// amowmebs tua aso

			if (izletr == false) {// geubneba rom aso sheiyvano
				println("enter a letter");
				izletr = true;
				game();
			} else {
				check(a);// amowmebs gamoyenebulia ukve tu ara
				if (used1 == true) {
					wrongegen(a);// naxulobs tu arasworia
					if (wrongagain == true) {// tu ki gaklebs qulas da tavidan wer
						tries--;
							println("miss. dont't enter the same wrong answer again. you have " + tries
									+ " tries left.");
							miss.play();
							HangmanCanvas_extension.crnt = current;
							HangmanCanvas_extension.used = used;
							println(current);
							canvas.drawman(tries);
							used1 = false;
							if (tries == 0) 
							{
								loss();
							}
							else
							{
								game();
							}
					} else if (a.length() == 1) {// tu swori tavidan sheiyvanet gaatarebs
						println("enter a letter yuo haven't used. ");
						miss.play();
						HangmanCanvas_extension.crnt = current;
						HangmanCanvas_extension.used = used;
						used1 = false;
						game();
					}
				} else {
					guess = a.charAt(0);// tu axali asoa gadadis procesze
					used = used + " " + guess;
					process();
				}
			}
		}
	}

	public void init() {
		canvas = new HangmanCanvas_extension();
		add(canvas);
	}

	public void win() {
		if (done == false) {
			HangmanCanvas_extension.crnt = current;
			HangmanCanvas_extension.used = used;
			println("yuo win");
			canvas.drawman(tries);
			canvas.displayWord("the word was " + word);
			done = true;
			println("wanna go again??? 1 for yes 0/ anything else for no");
			int choice=readInt();

			if(choice==1)
			{
				course=true;
				canvas.drawman(tries);
				course=true;
				run();
			}
			else
			{
				exit();
			}
		}
	}

	public void loss() {
		if (done == false) {
			HangmanCanvas_extension.crnt = current;
			HangmanCanvas_extension.used = used;
			println("the word was " + word);
			canvas.displayWord("the word was " + word);
			println("yuo loss");
			done = true;
			loss.play();
			println("wanna try again??? 1 for yes 0/ anything else for no");
			int choice=readInt();
			if(choice==1)
			{
				course=true;
				canvas.drawman(tries);
				course=true;
				run();
			}
			else
			{
				exit();
			}
		}
	}

	public void run() {
		setup();
		game();
	}

}