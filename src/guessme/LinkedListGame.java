package guessme;

/**
 * A LinkedList-based implementation of the Guess-A-Number game
 */
public class LinkedListGame {

	// TODO: declare data members as necessary

	public LLIntegerNode priorList;
	public LLIntegerNode candList;
	public int numGuesses;
	public int guess;
	public boolean isOver;
	public LLIntegerNode head;
	public LLIntegerNode headprior;
	/********************************************************
	 * NOTE: for this project you must use linked lists
	 * implemented by yourself. You are NOT ALLOWED to use
	 * Java arrays of any type, or any class in the java.util
	 * package (such as ArrayList).
	 *******************************************************/	 
	
	/********************************************************
	 * NOTE: you are allowed to add new methods if necessary,
	 * but DO NOT remove any provided method, and do NOT add
	 * new files (as they will be ignored by the autograder).
	 *******************************************************/
	
	// LinkedListGame constructor method
	public LinkedListGame() {
		// TODO
		priorList = null;
		candList = candList();
		numGuesses = 0;
		//guess = 1000;
		isOver = false;
		
		headprior = null;
	}
	
	// Resets data members and game state so we can play again
	public void reset() {//why head = null is wrong?
		// TODO
		priorList = null;
		
		candList = candList();
		numGuesses = 0;
		guess = 1000;
		isOver = false;
		headprior = null;
	}
	
	// Instruction: Create new LinkedList from 1000 to 9000
	public LLIntegerNode candList() {
		
		candList = new LLIntegerNode(1000);
		head = candList;
	
		for (int i = 1001; i< 10000; i++) {
			LLIntegerNode NewcandList1 = new LLIntegerNode(i); 
			candList.setLink(NewcandList1);
			candList = candList.getLink();
		}
		
		return candList;
		
	}
	// Returns true if n is a prior guess; false otherwise.
	
	public boolean isPriorGuess(int n) {
		// TODO
		LLIntegerNode NewpriorList = priorList;
		while (NewpriorList != null) {
			if (NewpriorList.getInfo() == n) return true;
			NewpriorList = NewpriorList.getLink();
		}	
		return false;
		
	}
	
	// Returns the number of guesses so far.
	public int numGuesses() {
		// TODO
		return numGuesses;
	}
	
	/**
	 * Returns the number of matches between integers a and b.
	 * You can assume that both are 4-digits long (i.e. between 1000 and 9999).
	 * The return value must be between 0 and 4.
	 * 
	 * A match is the same digit at the same location. For example:
	 *   1234 and 4321 have 0 match;
	 *   1234 and 1114 have 2 matches (1 and 4);
	 *   1000 and 9000 have 3 matches (three 0's).
	 */
	public static int numMatches(int a, int b) {
		// TODO
		int numMatches = 0;
		for (int i = 0; i < 4; i++) { //using for-loop to traverse each number and compare them
			if (a % 10 == b % 10) numMatches ++;
			a = a/10; 
			b = b/10;
		}	
		
		return numMatches;
	}
	



	
	/**
	 * Returns true if the game is over; false otherwise.
	 * The game is over if the number has been correctly guessed
	 * or if no candidate is left.
	 */
	public boolean isOver() {
		// TODO
		return isOver;
	}
	
	/**
	 * Returns the guess number and adds it to the list of prior guesses.
	 * The insertion should occur at the end of the prior guesses list,
	 * so that the order of the nodes follow the order of prior guesses.
	 */	
	public int getGuess() {
		// TODO: add guess to the list of prior guesses.
		// get guess from list
		guess = head.getInfo(); 
		// add guess to list
		
		if (priorList == null) {
			priorList = new LLIntegerNode(guess); 
			headprior = priorList;
		}
		else {
			 LLIntegerNode newhead = priorList;
	         while (newhead.getLink() != null) 
	        	 newhead = newhead.getLink();
	         newhead.setLink(new LLIntegerNode(guess));
	         
		}
		numGuesses++;
		return guess;
	}
	
	/**
	 * Updates guess based on the number of matches of the previous guess.
	 * If nmatches is 4, the previous guess is correct and the game is over.
	 * Check project description for implementation details.
	 * 
	 * Returns true if the update has no error; false if no candidate 
	 * is left (indicating a state of error);
	 */
	public boolean updateGuess(int nmatches) {
		if (nmatches != 4) {
		LLIntegerNode NewList = null;
		LLIntegerNode NewcandList = head;
		while (NewcandList != null) {
			// == or != ?: ==
			if (numMatches(guess, NewcandList.getInfo()) == nmatches) {
				
				LLIntegerNode NewList2 = new LLIntegerNode(NewcandList.getInfo());
				
				if (NewList == null) 
					{NewList = NewList2; head = NewList;}
				else if (NewList.getLink() == null)
					{NewList.setLink(NewList2); NewList = NewList.getLink();}
				
					
			}
			//???head???
			NewcandList = NewcandList.getLink();
		}
		candList = NewList;
		}
		
		if (candList == null) {isOver = true; isOver(); return false;}
		if (nmatches == 4) {isOver = true; isOver();}
		return true;
	}
	
	// Returns the head of the prior guesses list.
	// Returns null if there hasn't been any prior guess
	public LLIntegerNode priorGuesses() {
		// TODO
		if (headprior != null) return headprior;
		else return null;
	}
	
	/**
	 * Returns the list of prior guesses as a String. For example,
	 * if the prior guesses are 1000, 2111, 3222, in that order,
	 * the returned string should be "1000, 2111, 3222", in the same order,
	 * with every two numbers separated by a comma and space, except the
	 * last number (which should not be followed by either comma or space).
	 *
	 * Returns an empty string if here hasn't been any prior guess
	 */
	public String priorGuessesString() {
		// TODO
		String priorGuessString = "";
		LLIntegerNode headprior2 = headprior;
		while (headprior2 != null) {
			priorGuessString += headprior2.getInfo();
			if (headprior2.getLink() != null) 
				priorGuessString += ", ";
			headprior2 = headprior2.getLink();
		}
		return priorGuessString;
	}
	
}
