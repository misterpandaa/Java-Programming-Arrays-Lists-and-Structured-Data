
/**
 * Assignment: Reading Web Logs
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
     
     
}
