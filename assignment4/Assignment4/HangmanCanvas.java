
/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

	private GLabel wrongs;
	private GLabel curr;
	private GLabel correct;

	/** Resets the display so that only the scaffold appears */
	public static void reset() {
	}

	//amasa da extends shoris gansxvaveba mxolod bulianis saxelebshia
	
	/**
	 * Updates the word on the screen to correspond to the current state of the
	 * game. The argument string shows what letters have been guessed so far;
	 * unguessed letters are indicated by hyphens.
	 */
	public void displayWord(String word) {
		correct = new GLabel(word);
		add(correct, getWidth() / 2 - correct.getWidth() / 2, 9 * getHeight() / 10);
	}

	public void drawman(int i) {
		if (Hangman.done == false) {
			GLine scaffold = new GLine(getWidth() / 2 - BEAM_LENGTH, 4 * getHeight() / 5, getWidth() / 2 - BEAM_LENGTH,
					4 * getHeight() / 5 - SCAFFOLD_HEIGHT);
			GLine beam = new GLine(getWidth() / 2 - BEAM_LENGTH, 4 * getHeight() / 5 - SCAFFOLD_HEIGHT, getWidth() / 2,
					4 * getHeight() / 5 - SCAFFOLD_HEIGHT);
			GLine rope = new GLine(getWidth() / 2, 4 * getHeight() / 5 - SCAFFOLD_HEIGHT, getWidth() / 2,
					4 * getHeight() / 5 - SCAFFOLD_HEIGHT + ROPE_LENGTH);
			add(rope);
			add(beam);
			add(scaffold);
			noteIncorrectGuess(Hangman.guess);
			if (i < 8) {
				noteIncorrectGuess(Hangman.guess);
				GOval head = new GOval(HEAD_RADIUS, HEAD_RADIUS);
				add(head, getWidth() / 2 - HEAD_RADIUS / 2, 4 * getHeight() / 5 - SCAFFOLD_HEIGHT + ROPE_LENGTH);
				if (i < 7) {
					noteIncorrectGuess(Hangman.guess);
					GLine body = new GLine(getWidth() / 2,
							4 * getHeight() / 5 - SCAFFOLD_HEIGHT + ROPE_LENGTH + HEAD_RADIUS, getWidth() / 2,
							4 * getHeight() / 5 - SCAFFOLD_HEIGHT + ROPE_LENGTH + BODY_LENGTH + HEAD_RADIUS);
					add(body);
					GLine hip = new GLine(getWidth() / 2 - HIP_WIDTH / 2,
							4 * getHeight() / 5 - SCAFFOLD_HEIGHT + ROPE_LENGTH + BODY_LENGTH + HEAD_RADIUS,
							getWidth() / 2 + HIP_WIDTH / 2,
							4 * getHeight() / 5 - SCAFFOLD_HEIGHT + ROPE_LENGTH + BODY_LENGTH + HEAD_RADIUS);
					add(hip);

					if (i < 6) {

						noteIncorrectGuess(Hangman.guess);
						GLine LUA = new GLine(getWidth() / 2,
								4 * getHeight() / 5 - SCAFFOLD_HEIGHT + ROPE_LENGTH + HEAD_RADIUS
										+ ARM_OFFSET_FROM_HEAD,
								getWidth() / 2 - UPPER_ARM_LENGTH, 4 * getHeight() / 5 - SCAFFOLD_HEIGHT + ROPE_LENGTH
										+ HEAD_RADIUS + ARM_OFFSET_FROM_HEAD);
						add(LUA);
						GLine LLA = new GLine(getWidth() / 2 - UPPER_ARM_LENGTH,
								4 * getHeight() / 5 - SCAFFOLD_HEIGHT + ROPE_LENGTH + HEAD_RADIUS
										+ ARM_OFFSET_FROM_HEAD,
								getWidth() / 2 - UPPER_ARM_LENGTH, 4 * getHeight() / 5 - SCAFFOLD_HEIGHT + ROPE_LENGTH
										+ HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
						add(LLA);

						if (i < 5) {

							noteIncorrectGuess(Hangman.guess);
							GLine RUA = new GLine(getWidth() / 2,
									4 * getHeight() / 5 - SCAFFOLD_HEIGHT + ROPE_LENGTH + HEAD_RADIUS
											+ ARM_OFFSET_FROM_HEAD,
									getWidth() / 2 + UPPER_ARM_LENGTH, 4 * getHeight() / 5 - SCAFFOLD_HEIGHT
											+ ROPE_LENGTH + HEAD_RADIUS + ARM_OFFSET_FROM_HEAD);
							add(RUA);
							GLine RLA = new GLine(getWidth() / 2 + UPPER_ARM_LENGTH,
									4 * getHeight() / 5 - SCAFFOLD_HEIGHT + ROPE_LENGTH + HEAD_RADIUS
											+ ARM_OFFSET_FROM_HEAD,
									getWidth() / 2 + UPPER_ARM_LENGTH, 4 * getHeight() / 5 - SCAFFOLD_HEIGHT
											+ ROPE_LENGTH + HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
							add(RLA);
							if (i < 4) {

								noteIncorrectGuess(Hangman.guess);
								GLine LL = new GLine(getWidth() / 2 - HIP_WIDTH / 2,
										4 * getHeight() / 5 - SCAFFOLD_HEIGHT + ROPE_LENGTH + BODY_LENGTH + HEAD_RADIUS,
										getWidth() / 2 - HIP_WIDTH / 2, 4 * getHeight() / 5 - SCAFFOLD_HEIGHT
												+ ROPE_LENGTH + BODY_LENGTH + HEAD_RADIUS + LEG_LENGTH);
								add(LL);
								if (i < 3) {

									noteIncorrectGuess(Hangman.guess);
									GLine RL = new GLine(getWidth() / 2 + HIP_WIDTH / 2,
											4 * getHeight() / 5 - SCAFFOLD_HEIGHT + ROPE_LENGTH + BODY_LENGTH
													+ HEAD_RADIUS,
											getWidth() / 2 + HIP_WIDTH / 2, 4 * getHeight() / 5 - SCAFFOLD_HEIGHT
													+ ROPE_LENGTH + BODY_LENGTH + HEAD_RADIUS + LEG_LENGTH);
									add(RL);
									if (i < 2) {

										noteIncorrectGuess(Hangman.guess);
										GLine LF = new GLine(getWidth() / 2 - HIP_WIDTH / 2,
												4 * getHeight() / 5 - SCAFFOLD_HEIGHT + ROPE_LENGTH + BODY_LENGTH
														+ HEAD_RADIUS + LEG_LENGTH,
												getWidth() / 2 - HIP_WIDTH / 2 - FOOT_LENGTH,
												4 * getHeight() / 5 - SCAFFOLD_HEIGHT + ROPE_LENGTH + BODY_LENGTH
														+ HEAD_RADIUS + LEG_LENGTH);
										add(LF);

										if (i < 1) {

											noteIncorrectGuess(Hangman.guess);
											GLine RF = new GLine(getWidth() / 2 + HIP_WIDTH / 2,
													4 * getHeight() / 5 - SCAFFOLD_HEIGHT + ROPE_LENGTH + BODY_LENGTH
															+ HEAD_RADIUS + LEG_LENGTH,
													getWidth() / 2 + HIP_WIDTH / 2 + FOOT_LENGTH,
													4 * getHeight() / 5 - SCAFFOLD_HEIGHT + ROPE_LENGTH + BODY_LENGTH
															+ HEAD_RADIUS + LEG_LENGTH);
											add(RF);
										}
									}
								}
							}
						}
					}
				}
			}
		} else if (Hangman.done == true) {
			remove(wrongs);
			remove(curr);
			remove(correct);
			removeAll();
			Hangman_extension.course = false;
		}
	}

	/**
	 * Updates the display to correspond to an incorrect guess by the user. Calling
	 * this method causes the next body part to appear on the scaffold and adds the
	 * letter to the list of incorrect guesses that appears at the bottom of the
	 * window.
	 */

	public void noteIncorrectGuess(char letter) {
		if (notfirst == false) {
			remove(wrongs);
			remove(curr);
		}
		;// wesit aq shemedzlo letteris shemotana, magram isedac kargi string mqonda
			// amistvis, tore sheidzlebs string=string+letter
		wrongs = new GLabel(used);
		if (wrongs != null) {
			add(wrongs, getWidth() / 2 - wrongs.getWidth() / 2, 9 * getHeight() / 10 - wrongs.getHeight());
		}
		curr = new GLabel(crnt);
		if (crnt != null) {
			add(curr, getWidth() / 2 - curr.getWidth() / 2, 9 * getHeight() / 10 + curr.getHeight());
		}
		notfirst = false;
	}

	/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	int x = Hangman.tries;
	private boolean notfirst = true;
	public static String crnt = "";
	public static String used = "";

	public void run() {

	}

}
