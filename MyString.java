/**
 * A library of string functions.
 */
public class MyString {
    public static void main(String args[]) {
        String hello = "hello";
        System.out.println(countChar(hello, 'h'));
        System.out.println(countChar(hello, 'l'));
        System.out.println(countChar(hello, 'z'));
        System.out.println(spacedString(hello));
        //// Put your other tests here.
    }

    /**
     * Returns the number of times the given character appears in the given string.
     * Example: countChar("Center",'e') returns 2 and countChar("Center",'c') returns 0. 
     * 
     * @param str - a string
     * @param c - a character
     * @return the number of times c appears in str
     */
    public static int countChar(String str, char ch) {
        //// Replace the following statement with your code
        int counter = 0;
        for(int j = 0; j < str.length(); j++){
            char c = str.charAt(j);
            if ((c == ch)) {
                counter++;
                }
            }
        
        return counter;
    }

    /**
     * Returns true if str1 is a subset string str2, false otherwise
     * Examples:
     * subsetOf("sap","space") returns true
     * subsetOf("spa","space") returns false
     * subsetOf("pass","space") returns false
     * subsetOf("c","space") returns true
     *
     * @param str1 - a string
     * @param str2 - a string
     * @return true is str1 is a subset of str2, false otherwise
     */
    public static boolean subsetOf(String str1, String str2) {
        //// Replace the following statement with your code
        boolean bool = false;
        
        String new_string = "";
        if ((str1.length() == 0)) {
            return true;
        }
        boolean[] arr = new boolean[str1.length()];
        for(int i = 0; i < str2.length(); i ++){
            
            for(int k = i; k < str1.length(); k++){
                if (str1.charAt(k) == str2.charAt(i)) {
                    arr[k - i] = true;
                    break;
                }
            }
            for(int t = 0; t< arr.length; t++){
                if (arr[t] == false) {
                    return false;
                }
            }
        }
            
        return true;
    }

    /**
     * Returns a string which is the same as the given string, with a space
     * character inserted after each character in the given string, except
     * for the last character.
     * Example: spacedString("silent") returns "s i l e n t"
     * 
     * @param str - a string
     * @return a string consisting of the characters of str, separated by spaces.
     */
    public static String spacedString(String str) {
        //// Replace the following statement with your code
        String new_string = "";
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if ((i == str.length() - 1)) {
                new_string = new_string + ch;
                break;
            }
            new_string = new_string + ch + " ";

        }
        return new_string;
    }

    /**
     * Returns a string of n lowercase letters, selected randomly from
     * the English alphabet 'a', 'b', 'c', ..., 'z'. Note that the same
     * letter can be selected more than once.
     * 
     * Example: randomStringOfLetters(3) can return "zoo"
     * 
     * @param n - the number of letter to select
     * @return a randomly generated string, consisting of 'n' lowercase letters
     */
    public static String randomStringOfLetters(int n) {
        //// Replace the following statement with your code
        String new_string = "";
        for(int i = 0; i < n; i++){
            char ch  = (char)((Math.random()  * 26) + 97);
            new_string = new_string + ch;
        }

        return new_string;
    }

    /**
     * Returns a string consisting of the string str1, minus all the characters in
     * the
     * string str2. Assumes (without checking) that str2 is a subset of str1.
     * Example: remove("meet","committee") returns "comit"
     * 
     * @param str1 - a string
     * @param str2 - a string
     * @return a string consisting of str1 minus all the characters of str2
     */

     // for(int i = 0; i < str2.length(); i++){
        //     char ch = str1.charAt(i);
        //     for(int j = -; j< str1.length(); j ++){
        //         if ((condition)) {
                    
        //         }
        //     }
            
        // }
        // for( int j = 0; j < str1.length(); j++){
            //     // run over the str 2 letters
            //     if ((ch == str1.charAt(j))) {
                    
            //     }
            //     if ((ch != str1.charAt(j))) {
            //         count ++
            //     }
            //     if ((count == str1.length() - 1)) {
            //         not_used = not_used + str1.charAt(j);
            // }
    public static String remove(String str1, String str2) {
        //// Replace the following statement with your code
        String not_used = "";
        String new_string = "";
        char arr[] = new char[str1.length()];
        for(int t = 0; t < str1.length(); t++){
            arr[t] = str1.charAt(t);
        }
            for(int i = 0; i < str2.length(); i++){
            // all str2 letters
            char ch = str2.charAt(i);
            for ( int k = 0; k < arr.length; k++ ){
                if ((ch == arr[k])) {
                    arr[k] = ' ';
                    }
                    break;
                    }
                }
                for(int k = 0; k < arr.length; k++){
                    if(arr[k] == ' '){
                        k++;
                        break;
                    }

                    new_string = new_string + arr[k];
                }

        return str1;
    }

    /**
     * Returns a string consisting of the given string, with the given
     * character inserted randomly somewhere in the string.
     * For example, insertRandomly("s","cat") can return "scat", or "csat", or
     * "cast", or "cats".
     * 
     * @param ch  - a character
     * @param str - a string
     * @return a string consisting of str with ch inserted somewhere
     */
    public static String insertRandomly(char ch, String str) {
        // Generate a random index between 0 and str.length()
        int randomIndex = (int) (Math.random() * (str.length() + 1));
        // Insert the character at the random index
        String result = str.substring(0, randomIndex) + ch + str.substring(randomIndex);
        return result;
    }
}
