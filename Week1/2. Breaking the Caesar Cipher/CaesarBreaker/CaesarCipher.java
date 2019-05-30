
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        StringBuilder encryptedMessage = new StringBuilder();
        
        for (int i = 0; i < input.length(); i++) {
            char currentCharacter = input.charAt(i);
            int index = alphabet.toLowerCase().indexOf(Character.toLowerCase(currentCharacter));
            
            if (index != -1) {
                if (Character.isLowerCase(currentCharacter)) {
                    encryptedMessage.append(Character.toLowerCase(shiftedAlphabet.charAt(index)));                
                } else {
                    encryptedMessage.append(shiftedAlphabet.charAt(index));
                }

            } else {
                encryptedMessage.append(currentCharacter);
            }
        }
        
        return encryptedMessage.toString();
    }
    
    public void testCaesar() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        int key = 23;
        
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String firstShiftedAlphabet = alphabet.substring(key1) + alphabet.substring(0, key1);
        String secondShiftedAlphabet = alphabet.substring(key2) + alphabet.substring(0, key2);
        StringBuilder encryptedMessage = new StringBuilder();
        
        for (int i = 0; i < input.length(); i++) {
            char currentCharacter = input.charAt(i);
            int index = alphabet.toLowerCase().indexOf(Character.toLowerCase(currentCharacter));
            
            if (index != -1) {
                String shiftedAlphabet;
                
                if (i % 2 == 0) {
                    shiftedAlphabet = firstShiftedAlphabet;
                } else {
                    shiftedAlphabet = secondShiftedAlphabet;
                }
                
                if (Character.isLowerCase(currentCharacter)) {
                    encryptedMessage.append(Character.toLowerCase(shiftedAlphabet.charAt(index)));                
                } else {
                    encryptedMessage.append(shiftedAlphabet.charAt(index));
                }
            } else {
                encryptedMessage.append(currentCharacter);
            }
        }
        
        return encryptedMessage.toString();
    }
    
    public void testEncryptTwoKeys() {
        String message = "First Legion";
        int key1 = 23;
        int key2 = 17;
        System.out.println(message + " -> " + encryptTwoKeys(message, key1, key2));
        
        message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        key1 = 8;
        key2 = 21;
        System.out.println(message + " -> " + encryptTwoKeys(message, key1, key2));
    }
}
