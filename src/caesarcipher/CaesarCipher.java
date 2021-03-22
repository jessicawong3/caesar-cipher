/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caesarcipher;

import java.util.Arrays;

/**
 *
 * @author Jessica Wong
 * Caesar Cipher Assignment
 */
public class CaesarCipher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // tests        
        System.out.println(encode("This is a sentence. This one has a Y in it!", 4));
        System.out.println(decode(encode("This is a sentence. This one has a Y in it!", 4), 4));
        
        System.out.println(encode("abcdefghijklmnopqrstuvwxyz", 2));
        System.out.println(decode(encode("abcdefghijklmnopqrstuvwxyz", 2), 2));
        
        System.out.println(encode("ABCDEFGHIJKLMNOPQRSTUVWXYZ", 1));
        System.out.println(decode(encode("ABCDEFGHIJKLMNOPQRSTUVWXYZ", 1), 1));
        
        System.out.println(encode("This is to test special characters.!?@#$%^&*()_+", 5));
        System.out.println(decode(encode("This is to test special characters.!?@#$%^&*()_+", 5), 5));

        String secretMessage = encode("This is a secret message!", 2);
        System.out.println(secretMessage);
        System.out.println(Arrays.toString(breakCode(secretMessage)));

    }
    
    /**
     * Takes in a sentence and provides the encoded version
     *
     * @param sentence The sentence to be encoded
     * @param numShift The number to shift the letters by
     * @return Returns the encoded version of the original sentence
     */
    public static String encode(String sentence, int numShift) {
        // create variables to store new characters and sentence
        char charAtIndex;
        String newSentence = "";
        // loop through sentence to see each character
        for (int i = 0; i < sentence.length(); i++) {
            charAtIndex = sentence.charAt(i); // use variable to hold new character
            // loop as many times as needed to shift
            for (int x = 0; x < numShift; x++) {
                // for lower case
                if (charAtIndex >= 'a' && charAtIndex <= 'z') {
                    if (charAtIndex + 1 >= 123) { // loop back around the alphabet if it goes past letter values
                        charAtIndex = ('a');
                    } else {
                        charAtIndex = (char) (1 + charAtIndex); // shift letter by one character
                    }
                } // for uppercase
                else if (charAtIndex >= 'A' && charAtIndex <= 'Z') {
                    if (charAtIndex + 1 >= 91) { // loop back around the alphabet if it goes past letter values
                        charAtIndex = ('A');
                    } else {
                        charAtIndex = (char) (1 + charAtIndex); // shift letter by one character
                    }
                } // for non-letters
                else {
                    charAtIndex = sentence.charAt(i); // keep it the same
                }
            }
            newSentence += charAtIndex; // add new character to new sentence
        }
        return newSentence;
    }

    /**
     * Takes in an encoded sentence and provides the decoded version
     *
     * @param sentence The sentence to be decoded
     * @param numShift The number to shift the letters by
     * @return Returns the decoded version of an encoded sentence
     */
    public static String decode(String sentence, int numShift) {
        // create variables to store new characters and sentence
        char charAtIndex;
        String newSentence = "";
        // loop through sentence to see each character
        for (int i = 0; i < sentence.length(); i++) {
            charAtIndex = sentence.charAt(i); // use variable to hold new character
            // loop as many times as needed to shift
            for (int x = 0; x < numShift; x++) {
                // for lower case
                if (charAtIndex >= 'a' && charAtIndex <= 'z') {
                    if (charAtIndex - 1 <= 96) { // loop back around the alphabet if it goes past letter values
                        charAtIndex = ('z');
                    } else {
                        charAtIndex = (char) (charAtIndex - 1); // shift letter by one character
                    }
                } // for uppercase
                else if (charAtIndex >= 'A' && charAtIndex <= 'Z') {
                    if (charAtIndex - 1 <= 64) { // loop back around the alphabet if it goes past letter values
                        charAtIndex = ('Z');
                    } else {
                        charAtIndex = (char) (charAtIndex - 1); // shift letter by one character
                    }
                } // for non-letters
                else {
                    charAtIndex = sentence.charAt(i); // keep it the same
                }
            }
            newSentence += charAtIndex; // add new character to new sentence
        }
        return newSentence;
    }

    /**
     * Takes in an encoded sentence and tries all possible keys to break the
     * code
     *
     * @param sentence The sentence to be decoded
     * @return Returns an array of decoded versions of an encoded sentence
     */
    public static String[] breakCode(String sentence) {
        String[] decodedMessages = new String[26]; // create variable to store attempts
        for (int i = 0; i < 26; i++) { // decode message using every possible number shift
            decodedMessages[i] = decode(sentence, i);
        }
        return decodedMessages;
    }
}
