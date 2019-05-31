
/**
 * Assignment 2: Words in Files
 * 
 * @author Deny Kiantono
 * @version 1.0
 */

import edu.duke.*;
import java.util.*;
import java.io.*;

public class Main {
    private HashMap<String, ArrayList<String>> wordCounts;
    
    public Main() {
        wordCounts = new HashMap<String, ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);
        
        for (String word : fr.words()) {
            if (wordCounts.containsKey(word)) {
                String currentFileName = f.getName();
                ArrayList<String> fileName = wordCounts.get(word);
                
                if (!fileName.contains(currentFileName)) {
                    fileName.add(currentFileName);
                    wordCounts.put(word, fileName);
                }
            } else {
                ArrayList<String> fileName = new ArrayList<String>();
                fileName.add(f.getName());
                wordCounts.put(word, fileName);
            }
        }
    }
    
    private void buildWordFileMap() {
        wordCounts.clear();
        
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    
    private int maxNumber() {
        int maxCount = Integer.MIN_VALUE;
        
        for (String key : wordCounts.keySet()) {
            int currentCount = wordCounts.get(key).size();
            
            if (currentCount > maxCount) {
                maxCount = currentCount;
            }
        }
        
        return maxCount;
    }
    
    private ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> wordList = new ArrayList<String>();
        
        for (String key : wordCounts.keySet()) {
            int currentCount = wordCounts.get(key).size();
            
            if (currentCount == number) {
                wordList.add(key);
            }
        }
        
        return wordList;
    }
    
    private void printFilesIn(String word) {
        ArrayList<String> fileName = wordCounts.get(word);
        
        for(String fname : fileName) {
            System.out.println(fname);
        }
    }
    
    public void tester() {
        buildWordFileMap();
        
        int maxOccurences = maxNumber();
        
        System.out.println("Maximum number of files = " + maxOccurences);
        
        ArrayList<String> wordList = wordsInNumFiles(maxOccurences);
        System.out.println("\nWords that are in the maximum number of files:");
        
        for(String word : wordList) {
            System.out.println(word);
            System.out.println(word + " is contained in files:");
            printFilesIn(word);
            System.out.println();
        }
        
        System.out.println("Total words in 4 files = " + wordsInNumFiles(4).size());
        printFilesIn("red");
    } 
}
