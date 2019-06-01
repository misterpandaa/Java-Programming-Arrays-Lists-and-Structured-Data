
/**
 * Assignment: Unique IPs
 * 
 * @author Deny Kiantono 
 * @version 1.0
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         
         for (String line : fr.lines()) {
             WebLogParser parser = new WebLogParser();
             LogEntry newEntry = parser.parseEntry(line);
             records.add(newEntry);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs() {
         ArrayList<String> uniqueIps = new ArrayList<String>();
         
         for (LogEntry le : records) {
            if (!uniqueIps.contains(le.getIpAddress())) {
                uniqueIps.add(le.getIpAddress());
            }
         }
         
         return uniqueIps.size();
     }
     
     public void printAllHigherThanNum(int num) {
         for (LogEntry le : records) {
            if (le.getStatusCode() > num) {
                System.out.println(le);
            }
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
         ArrayList<String> uniqueIps = new ArrayList<String>();
         
         for (LogEntry le : records) {
            if (le.getAccessTime().toString().contains(someday)) {
                if (!uniqueIps.contains(le.getIpAddress())) {
                    uniqueIps.add(le.getIpAddress());                
                }
            }
         }
         
         return uniqueIps;
     }
     
     public int countUniqueIPsInRange(int low, int high) {
         ArrayList<String> uniqueIps = new ArrayList<String>();
         
         for (LogEntry le : records) {
            if (le.getStatusCode() >= low && le.getStatusCode() <= high) {
                if (!uniqueIps.contains(le.getIpAddress())) {
                    uniqueIps.add(le.getIpAddress());
                }
            }
         }
         
         return uniqueIps.size();
     }
}
