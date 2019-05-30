
/**
 * Assignment 1: Word Play
 * 
 * @author Deny Kiantono 
 * @version 1.0
 */
public class Main {
    public boolean isVowel(char ch) {
        ch = Character.toLowerCase(ch);
        
        if (ch == 'a' || ch == 'i' || ch == 'u' || ch == 'e' || ch == 'o') {
            return true;
        }
        
        return false;
    }
    
    public void testIsVowel() {
        char ch = 'f';
        System.out.println(ch + " is a vowel = " + isVowel(ch));
        
        ch = 'A';
        System.out.println(ch + " is a vowel = " + isVowel(ch));
    }
    
    public String replaceVowels(String phrase, char ch) {
        StringBuilder newPhrase = new StringBuilder();
        
        for (int i = 0; i < phrase.length(); i++) {
            char currentCharacter = phrase.charAt(i);
            
            if (isVowel(currentCharacter)) {
                newPhrase.append(ch);
            } else {
                newPhrase.append(currentCharacter);
            }
        }
        
        return newPhrase.toString();
    }
    
    public void testReplaceVowels() {
        String phrase = "Hello World";
        char ch = '*';
        System.out.println(phrase + " -> " + replaceVowels(phrase, ch));
    }
    
    
    public String emphasize(String phrase, char ch) {
        StringBuilder newPhrase = new StringBuilder();
        
        for (int i = 0; i < phrase.length(); i++) {
            char currentCharacter = phrase.charAt(i);
            
            if (Character.toLowerCase(currentCharacter) == Character.toLowerCase(ch)) {
                if (i % 2 == 0) {
                    newPhrase.append('*');
                } else {
                    newPhrase.append('+');
                }
            } else {
                newPhrase.append(currentCharacter);
            }
        }
        
        return newPhrase.toString();
    }
    
    public void testEmphasize() {
        String phrase = "dna ctgaaactga";
        char ch = 'a';
        System.out.println(phrase + " with " + ch + " replaced become = " + emphasize(phrase, ch));
        
        phrase = "Mary Bella Abracadabra";
        ch = 'a';
        System.out.println(phrase + " with " + ch + " replaced become = " + emphasize(phrase, ch));
    }
}
