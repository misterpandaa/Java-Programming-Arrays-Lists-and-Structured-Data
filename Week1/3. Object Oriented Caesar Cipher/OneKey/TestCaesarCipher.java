
/**
 * Assignment 1: One Key
 * 
 * @author Deny Kiantono
 * @version 1.0
 */

import edu.duke.*;

public class TestCaesarCipher {
    private int[] countLetters(String encrypted) {
        int[] counts = new int[26];
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        
        for (int i = 0; i < encrypted.length(); i++) {
            int index = alphabet.indexOf(Character.toLowerCase(encrypted.charAt(i)));
            
            if (index != -1) {
                counts[index]++;
            }
        }
        
        return counts;
    }
    
    private int maxIndex(int[] freqs) {
        int max = Integer.MIN_VALUE;
        int maxIndex = -1;
        
        for (int i = 0; i < freqs.length; i++) {
            if (freqs[i] > max) {
                max = freqs[i];
                maxIndex = i;
            }
        }
        
        return maxIndex;
    }
    
    private String breakCaesarCipher(String input) {
        int[] freqs = countLetters(input);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        
        CaesarCipher cc = new CaesarCipher(dkey);
		
        return cc.decrypt(input);
    }
    
    public void simpleTests() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarCipher cc = new CaesarCipher(18);
		
        String encrypted = cc.encrypt(message);
        System.out.println("Encryption");
        System.out.println("==========");
        System.out.println(message + " -> " + encrypted);
        
        String decrypted = cc.decrypt(encrypted);
        System.out.println("Decryption");
        System.out.println("==========");
        System.out.println(encrypted + " -> " + decrypted);

        decrypted = breakCaesarCipher(encrypted);
        System.out.println("Break Caesar Cipher");
        System.out.println("===================");
        System.out.println(encrypted + " -> " + decrypted);
    }
}
