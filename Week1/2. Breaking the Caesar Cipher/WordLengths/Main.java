
/**
 * Assignment 1: Word lengths
 * 
 * @author Deny Kiantono
 * @version 1.0
 */

import edu.duke.*;

public class Main {
    public void countWordLengths(FileResource resource, int[] counts) {
        for (String word : resource.words()) {
            int nonLetterCount = 0;
            
            if (!Character.isLetter(word.charAt(0))) {
                nonLetterCount++;
            }
            
            if (!Character.isLetter(word.charAt(word.length() - 1))) {
                nonLetterCount++;
            }
            
            int wordLength = word.length() - nonLetterCount;
            int maxLength = counts.length - 1;
            
            if (wordLength >= maxLength) {
                counts[maxLength]++;
            } else {
                counts[wordLength]++;            
            }
        }
    }
    
    public int indexOfMax(int values[]) {
        int max = Integer.MIN_VALUE;
        int maxIndex = -1;
        
        for (int i = 0; i < values.length; i++) {
            if (values[i] > max) {
                max = values[i];
                maxIndex = i;
            }
        }
        
        return maxIndex;
    }
    
    public void testCountWordLengths() {
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr, counts);
        
        for (int i = 0; i < counts.length; i++) {
            System.out.println("Length " + i + " = " + counts[i] + " words");
        }
        
        System.out.println("Most common length = " + indexOfMax(counts));
    }
    
    
    
    
}
