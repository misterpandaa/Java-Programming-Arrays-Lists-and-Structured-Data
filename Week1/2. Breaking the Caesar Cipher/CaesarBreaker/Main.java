
/**
 * Assignment 2: Caesar Cipher Two Keys Decrypt
 * 
 * @author Deny Kiantono
 * @version 1.0
 */

import edu.duke.*;

public class Main {
    public int[] countLetters(String encrypted) {
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
    
    public int maxIndex(int[] freqs) {
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
    
    public String decrypt(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        
        return cc.encrypt(encrypted, 26 - dkey);
    }
    
    public void testDecrypt() {
        String message = "COBB ZXHB FK QEB ZLKCBOBKZB OLLJ!";
        System.out.println("Decrypted message = " + decrypt(message));
    }
    
    public String halfOfString(String message, int start) {
        StringBuilder halvedMessage = new StringBuilder();
        
        for (int i = start; i < message.length(); i+= 2) {
            halvedMessage.append(message.charAt(i));
        }
        
        return halvedMessage.toString();
    }
    
    public void testHalfOfString() {
        String message = "Qbkm Zgis";
        int start = 0;
        System.out.println("Halved message = " + halfOfString(message, start));
        
        message = "Qbkm Zgis";
        start = 1;
        System.out.println("Halved message = " + halfOfString(message, start));
    }
    
    public int getKey(String s) {
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        
        return dkey;
    }
    
    public String decryptTwoKeys(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        
        String oddString = halfOfString(encrypted, 0);
        String evenString = halfOfString(encrypted, 1);
        
        int key1 = getKey(oddString);
        int key2 = getKey(evenString);
        
        System.out.println("key1 = " + key1);
        System.out.println("key2 = " + key2);
        
        return cc.encryptTwoKeys(encrypted, 26 - key1, 26 - key2);
    }
    
    public void testDecryptTwoKeys() {
        String message = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
        System.out.println("Decrypted message = " + decryptTwoKeys(message)); 
        
        message = "Io iwjv jz dv bcm kjvammmikz mwju edbc twpz pvb wi awm v ncmxmqnm xvzog. TMZMZMZMZMZMZMGT TJCY!";
        System.out.println("Decrypted message = " + decryptTwoKeys(message));
        
        message = "Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbu";
        System.out.println("Decrypted message = " + decryptTwoKeys(message));
    }
}
