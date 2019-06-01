import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder slicedMessage = new StringBuilder();
        
        for (int i = whichSlice; i < message.length(); i+= totalSlices) {
            slicedMessage.append(message.charAt(i));
        } 
        
        return slicedMessage.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        
        for (int i = 0; i < klength; i++) {
            String slicedMessage = sliceString(encrypted, i, klength);
            CaesarCracker cc = new CaesarCracker();
            key[i] = cc.getKey(slicedMessage);
        }
        
        return key;
    }

    public void breakVigenere() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        int[] key = tryKeyLength(encrypted, 5, 'e');
        VigenereCipher vc = new VigenereCipher(key);
        String decrypted = vc.decrypt(encrypted);
        System.out.println("Decrypted message = " + decrypted);
    }
    
    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> wordList = new HashSet<String>();
        
        for (String word : fr.lines()) {
            word = word.toLowerCase();
            wordList.add(word);
        }
        
        return wordList;
    }
    
    public int countWords(String message, HashSet<String> dictionary) {
        String[] wordList = message.split("\\W+");
        int totalOccurences = 0;
        
        for (String word : wordList) {
            if (dictionary.contains(word.toLowerCase())) {
                totalOccurences++;
            }
        }
        
        return totalOccurences;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        String finalMessage = "";
        int maxOccurences = Integer.MIN_VALUE;
        
        for (int i = 1; i <= 100; i++) {
            /*
             * Code for break vigenere with unknown key using english language: 
             * int[] key = tryKeyLength(encrypted, i, 'e');
            */
            int[] key = tryKeyLength(encrypted, i, mostCommonCharIn(dictionary));
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            int currOccurences = countWords(decrypted, dictionary);
            
            if (currOccurences > maxOccurences) {
                maxOccurences = currOccurences;
                finalMessage = decrypted;
            }
        }
        
        return finalMessage;
    }
    
    public void breakVigenereUnknownKey() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        FileResource dictResource = new FileResource("dictionaries/English");
        HashSet<String> dictionary = readDictionary(dictResource);
        String decrypted = breakForLanguage(encrypted, dictionary);
        System.out.println("Decrypted message = " + decrypted);
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<Character, Integer> charCounts = new HashMap<Character, Integer>();
        
        for (String word : dictionary) {
            for (int i = 0; i < word.length(); i++) {
                char currChar = word.charAt(i);
                
                if (!charCounts.containsKey(currChar)) {
                    charCounts.put(currChar, 1);
                } else {
                    charCounts.put(currChar, charCounts.get(currChar) + 1);
                }
            }
        }
        
        char mostCommon = '\0';
        int maxOccurences = Integer.MIN_VALUE;
        
        for (char key : charCounts.keySet()) {
            int currCount = charCounts.get(key);
            
            if (currCount > maxOccurences) {
                maxOccurences = currCount;
                mostCommon = key;
            }
        }
        
        return mostCommon;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        int maxOccurences = Integer.MIN_VALUE;
        String detectedLanguage = "";
        String decrypted = "";
        
        for (String language : languages.keySet()) {
            HashSet<String> dictionary = languages.get(language);
            
            decrypted = breakForLanguage(encrypted, dictionary);
            int currentOccurences = countWords(decrypted, dictionary);
            
            if (currentOccurences  > maxOccurences) {
                detectedLanguage = language;
                maxOccurences = currentOccurences;
            }
        }
        
        System.out.println("Language = " + detectedLanguage);
        System.out.println("Decrypted message = " + decrypted);
    }
    
    public void breakVigenereUnknownKeyAndLanguage() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        String[] languageList = {"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish"};
        FileResource dictResource;
        
        for (String language : languageList) {
            dictResource = new FileResource("dictionaries/" + language);
            languages.put(language, readDictionary(dictResource));
            System.out.println("Finished reading " + language + " dictionary");
        }
        
        breakForAllLangs(encrypted, languages);
    }
}
