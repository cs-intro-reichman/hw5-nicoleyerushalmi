/*
 * RUNI version of the Scrabble game.
 */
public class Scrabble {

	// Note 1: "Class variables", like the five class-level variables declared below,
	// are global variables that can be accessed by any function in the class. It is
	// customary to name class variables using capital letters and underline characters.
	// Note 2: If a variable is declared "final", it is treated as a constant value
	// which is initialized once and cannot be changed later.

	// Dictionary file for this Scrabble game
	static final String WORDS_FILE = "dictionary.txt";

	// The "Scrabble value" of each letter in the English alphabet.
	// 'a' is worth 1 point, 'b' is worth 3 points, ..., z is worth 10 points.
	static final int[] SCRABBLE_LETTER_VALUES = { 1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3,
												  1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10 };

	// Number of random letters dealt at each round of this Scrabble game
	static int HAND_SIZE = 10;

	// Maximum number of possible words in this Scrabble game
	static int MAX_NUMBER_OF_WORDS = 100000;

    // The dictionary array (will contain the words from the dictionary file)
	static String[] DICTIONARY = new String[MAX_NUMBER_OF_WORDS];

	// Actual number of words in the dictionary (set by the init function, below)
	static int NUM_OF_WORDS;

	// Populates the DICTIONARY array with the lowercase version of all the words read
	// from the WORDS_FILE, and sets NUM_OF_WORDS to the number of words read from the file.
	public static void init() {
		// Declares the variable in to refer to an object of type In, and initializes it to represent
		// the stream of characters coming from the given file. Used for reading words from the file.  
		In in = new In(WORDS_FILE);
        System.out.println("Loading word list from file...");
        NUM_OF_WORDS = 0;
		while (!in.isEmpty()) {
			// Reads the next "token" from the file. A token is defined as a string of 
			// non-whitespace characters. Whitespace is either space characters, or  
			// end-of-line characters.
			DICTIONARY[NUM_OF_WORDS++] = in.readString().toLowerCase();
		}
        System.out.println(NUM_OF_WORDS + " words loaded.");
	}

	// Checks if the given word is in the dictionary.
	public static boolean isWordInDictionary(String word) {
		//// Replace the following statement with your code
		for(int i = 0; i < DICTIONARY.length; i++){
			if ((word == DICTIONARY[i])) {
				return true;
			}
		}
		return false;
	}
	
	// Returns the Scrabble score of the given word.
	// If the length of the word equals the length of the hand, adds 50 points to the score.
	// If the word includes the sequence "runi", adds 1000 points to the game.
	public static int wordScore(String word) {
		//// Replace the following statement with your code
		int score = 0;
		word = word.toLowerCase();
		for(int i=0; i<word.length(); i++){
			int char_place = word.charAt(i) - 'a';
			//System.out.println("char place: "+char_place);
			score += SCRABBLE_LETTER_VALUES[char_place];
		}
		score = score*word.length();
		if (word.length() == 10) {
			score +=50;
		}
		if ((MyString.subsetOf("runi", word)) ==  true) {
			score += 1000;
		}
		
		return score;
	}

	// Creates a random hand of length (HAND_SIZE - 2) and then inserts
	// into it, at random indexes, the letters 'a' and 'e'
	// (these two vowels make it easier for the user to construct words)
	public static String createHand() {
		//// Replace the following statement with your code
		String hand = MyString.randomStringOfLetters(HAND_SIZE - 2);
		hand = MyString.insertRandomly('a', hand);
		hand = MyString.insertRandomly('e', hand);

		return hand;
	}
	
    // Runs a single hand in a Scrabble game. Each time the user enters a valid word:
    // 1. The letters in the word are removed from the hand, which becomes smaller.
    // 2. The user gets the Scrabble points of the entered word.
    // 3. The user is prompted to enter another word, or '.' to end the hand. 
	public static void playHand(String hand) {
		int n = hand.length();
		int score = 0;
		// Declares the variable in to refer to an object of type In, and initializes it to represent
		// the stream of characters coming from the keyboard. Used for reading the user's inputs.   
		In in = new In();
		int counter = 0;
		//System.out.println("Expected sequence of plays:");
		while (hand.length() > 0) {
			System.out.println("Current Hand: " + MyString.spacedString(hand));
			System.out.println("Enter a word, or '.' to finish playing this hand:");
			// Reads the next "token" from the keyboard. A token is defined as a string of 
			// non-whitespace characters. Whitespace is either space characters, or  
			// end-of-line characters.
			String input = in.readString();
			if ((input.equals(".") )) {
				System.out.println("End of hand. Total score: " + score + " points");
				break;
			}
			//// Replace the following break statement with code
			if ((MyString.subsetOf(input,hand))) {
				counter++;
				hand = MyString.remove(input, hand);
				score += wordScore(input);
				System.out.println(counter + ". '" + input + "' -> score: " + score +"\n");
			} else {
				System.out.println("Invalid word, please try again.");
			}
	}
	if (hand.length() == 0) {
        System.out.println("Ran out of letters. Total score: " + score + " points");
    }
} 

	// Plays a Scrabble game. Prompts the user to enter 'n' for playing a new hand, or 'e'
	// to end the game. If the user enters any other input, writes an error message.
	public static void playGame() {
		// Initializes the dictionary
    	init();
		// The variable in is set to represent the stream of characters 
		// coming from the keyboard. Used for getting the user's inputs.  
		In in = new In();
		while(true) {
			System.out.println("Enter n to deal a new hand, or e to end the game:");
			// Gets the user's input, which is all the characters entered by 
			// the user until the user enter the ENTER character.
			String input = in.readString();
			//// Replace the following break statement with code
			//// that completes the game playing loop
			if ((input.equals("e"))) {
				//System.out.println("end game");
				break;
			}else{
			if ((input.equals("n"))) {
				playHand(createHand());
			}else{System.out.println("Error: Invalid input. Please enter 'n' to deal a new hand or 'e' to end the game.\")");}
		
		}
	}
	}

	public static void main(String[] args) {
		//// Uncomment the test you want to run
		////testBuildingTheDictionary();  
		////testScrabbleScore();    
		////testCreateHands();  
		////testPlayHands();
		////playGame();
	}

	public static void testBuildingTheDictionary() {
		init();
		// Prints a few words
		for (int i = 0; i < 5; i++) {
			System.out.println(DICTIONARY[i]);
		}
		System.out.println(isWordInDictionary("mango"));
	}
	
	public static void testScrabbleScore() {
		System.out.println(wordScore("bee"));	
		System.out.println(wordScore("babe"));
		System.out.println(wordScore("friendship"));
		System.out.println(wordScore("running"));
	}
	
	public static void testCreateHands() {
		System.out.println(createHand());
		System.out.println(createHand());
		System.out.println(createHand());
	}
	public static void testPlayHands() {
		init();
		//playHand("ocostrza");
		//playHand("arbffip");
		//playHand("aretiin");
	}
}

// /**
//  * A library of string functions.
//  */
// public class MyString {
//     public static void main(String args[]) {
//         String hello = "hello";
//         System.out.println(countChar(hello, 'h'));
//         System.out.println(countChar(hello, 'l'));
//         System.out.println(countChar(hello, 'z'));
//         System.out.println(spacedString(hello));
//         //// Put your other tests here.
//     }

//     /**
//      * Returns the number of times the given character appears in the given string.
//      * Example: countChar("Center",'e') returns 2 and countChar("Center",'c') returns 0. 
//      * 
//      * @param str - a string
//      * @param c - a character
//      * @return the number of times c appears in str
//      */
//     public static int countChar(String str, char ch) {
//         //// Replace the following statement with your code
//         int counter = 0;
//         for(int j = 0; j < str.length(); j++){
//             char c = str.charAt(j);
//             if ((c == ch)) {
//                 counter++;
//                 }
//             }
        
//         return counter;
//     }

//     /**
//      * Returns true if str1 is a subset string str2, false otherwise
//      * Examples:
//      * subsetOf("sap","space") returns true
//      * subsetOf("spa","space") returns false
//      * subsetOf("pass","space") returns false
//      * subsetOf("c","space") returns true
//      *
//      * @param str1 - a string
//      * @param str2 - a string
//      * @return true is str1 is a subset of str2, false otherwise
//      */
//     public static boolean subsetOf(String str1, String str2) {
//         //// Replace the following statement with your code
//         boolean bool = false;
        
//         String new_string = "";
//         if ((str1.length() == 0)) {
//             return true;
//         }
//         boolean[] arr = new boolean[str2.length()];
//         for(int i = 0; i < str1.length(); i ++){
//             char ch = str1.charAt(i);
//             boolean found = false;
//             for(int k = 0; k < str2.length(); k++){
//                 if (ch == str2.charAt(k) && !arr[k]) {
//                     arr[k] = true;
//                     found = true;
//                     break;
//                 }
//             }
//             if(!found){
//                 return false;
//             }
//     }
//     return true;
// }

//     /**
//      * Returns a string which is the same as the given string, with a space
//      * character inserted after each character in the given string, except
//      * for the last character.
//      * Example: spacedString("silent") returns "s i l e n t"
//      * 
//      * @param str - a string
//      * @return a string consisting of the characters of str, separated by spaces.
//      */
//     public static String spacedString(String str) {
//         //// Replace the following statement with your code
//         String new_string = "";
//         for (int i = 0; i < str.length(); i++) {
//             char ch = str.charAt(i);
//             if ((i == str.length() - 1)) {
//                 new_string = new_string + ch;
//                 break;
//             }
//             new_string = new_string + ch + " ";

//         }
//         return new_string;
//     }

//     /**
//      * Returns a string of n lowercase letters, selected randomly from
//      * the English alphabet 'a', 'b', 'c', ..., 'z'. Note that the same
//      * letter can be selected more than once.
//      * 
//      * Example: randomStringOfLetters(3) can return "zoo"
//      * 
//      * @param n - the number of letter to select
//      * @return a randomly generated string, consisting of 'n' lowercase letters
//      */
//     public static String randomStringOfLetters(int n) {
//         //// Replace the following statement with your code
//         String new_string = "";
//         for(int i = 0; i < n; i++){
//             char ch  = (char)((Math.random()  * 26) + 97);
//             new_string = new_string + ch;
//         }

//         return new_string;
//     }

//     /**
//      * Returns a string consisting of the string str1, minus all the characters in
//      * the
//      * string str2. Assumes (without checking) that str2 is a subset of str1.
//      * Example: remove("meet","committee") returns "comit"
//      * 
//      * @param str1 - a string
//      * @param str2 - a string
//      * @return a string consisting of str1 minus all the characters of str2
//      */

//      // for(int i = 0; i < str2.length(); i++){
//         //     char ch = str1.charAt(i);
//         //     for(int j = -; j< str1.length(); j ++){
//         //         if ((condition)) {
                    
//         //         }
//         //     }
            
//         // }
//         // for( int j = 0; j < str1.length(); j++){
//             //     // run over the str 2 letters
//             //     if ((ch == str1.charAt(j))) {
                    
//             //     }
//             //     if ((ch != str1.charAt(j))) {
//             //         count ++
//             //     }
//             //     if ((count == str1.length() - 1)) {
//             //         not_used = not_used + str1.charAt(j);
//             // }
//     public static String remove(String str1, String str2) {
//         //// Replace the following statement with your code
//         String not_used = "";
//         String new_string = "";
//         char arr[] = new char[str1.length()];
//         for(int t = 0; t < str1.length(); t++){
//             arr[t] = str1.charAt(t);
//         }
//             for(int i = 0; i < str2.length(); i++){
//             // all str2 letters
//             char ch = str2.charAt(i);
//             for ( int k = 0; k < arr.length; k++ ){
//                 if ((ch == arr[k])) {
//                     arr[k] = '\0';
//                     break;
//                     }
                    
//                     }
//                 }
//                 for(int k = 0; k < arr.length; k++){
//                     if(arr[k] != '\0'){
//                         new_string += arr[k];
//                     }

//                 }

//         return new_string;
//     }

//     /**
//      * Returns a string consisting of the given string, with the given
//      * character inserted randomly somewhere in the string.
//      * For example, insertRandomly("s","cat") can return "scat", or "csat", or
//      * "cast", or "cats".
//      * 
//      * @param ch  - a character
//      * @param str - a string
//      * @return a string consisting of str with ch inserted somewhere
//      */
//     public static String insertRandomly(char ch, String str) {
//         // Generate a random index between 0 and str.length()
//         int randomIndex = (int) (Math.random() * (str.length() + 1));
//         // Insert the character at the random index
//         String result = str.substring(0, randomIndex) + ch + str.substring(randomIndex);
//         return result;
//     }
// }
