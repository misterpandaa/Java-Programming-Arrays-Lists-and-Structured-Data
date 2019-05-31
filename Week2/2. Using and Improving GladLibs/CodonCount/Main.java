
/**
 * Assignment 1: Codon Count
 * 
 * @author Deny Kiantono
 * @version 1.0
 */

import edu.duke.*;
import java.util.*;

public class Main {
    private HashMap<String, Integer> codonCounts;
    
    public Main() {
        codonCounts = new HashMap<String, Integer>();
    }
    
    private void buildCodonMap(int start, String dna) {
        codonCounts.clear();
        
        for (int i = start; i < (dna.length() - 3); i += 3) {
            String codon = dna.substring(i, i + 3);
            
            if (codonCounts.containsKey(codon)) {
                codonCounts.put(codon, codonCounts.get(codon) + 1);
            } else {
                codonCounts.put(codon, 1);
            }
        }
    }
    
    private String getMostCommonCodon() {
        int maxCount = Integer.MIN_VALUE;
        String mostCommonCodon = "";        
        
        for (String key : codonCounts.keySet()) {
            int currCount = codonCounts.get(key);
            
            if (currCount > maxCount) {
                maxCount = currCount;
                mostCommonCodon = key;
            }
        }
        
        return mostCommonCodon;
    }
    
    private void printCodonCounts(int start, int end) {
        for (String key : codonCounts.keySet()) {
            int currCount = codonCounts.get(key);
            
            if (currCount >= start && currCount <= end) {
                System.out.println(key + " -> " + currCount);
            }
        }
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        String dna = fr.asString().trim().toUpperCase();
        
        int start = 0;
        buildCodonMap(start, dna);
        System.out.println("Reading frame starting with " + start + " results in " + codonCounts.size() + " unique codons");
        System.out.println("and most common codon is " + getMostCommonCodon() + " with count " + codonCounts.get(getMostCommonCodon()));
        System.out.println("Counts of codons between 1 and 5 inclusive are:");
        printCodonCounts(1, 5);
        
        start = 1;
        buildCodonMap(start, dna);
        System.out.println("\n\nReading frame starting with " + start + " results in " + codonCounts.size() + " unique codons");
        System.out.println("and most common codon is " + getMostCommonCodon() + " with count " + codonCounts.get(getMostCommonCodon()));
        System.out.println("Counts of codons between 1 and 5 inclusive are:");
        printCodonCounts(1, 5);
        
        start = 2;
        buildCodonMap(start, dna);
        System.out.println("\n\nReading frame starting with " + start + " results in " + codonCounts.size() + " unique codons");
        System.out.println("and most common codon is " + getMostCommonCodon() + " with count " + codonCounts.get(getMostCommonCodon()));
        System.out.println("Counts of codons between 1 and 5 inclusive are:");
        printCodonCounts(1, 5);
    }
}
