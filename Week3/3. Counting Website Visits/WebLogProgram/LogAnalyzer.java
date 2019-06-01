
/**
 * Assignment: Website Visits
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
     
     public HashMap<String, Integer> countVisitsPerIP() {
         HashMap<String, Integer> ipCounts = new HashMap<String, Integer>();
         
         for (LogEntry le : records) {
            String ip = le.getIpAddress();
            
            if (!ipCounts.containsKey(ip)) {
                ipCounts.put(ip, 1);
            } else {
                ipCounts.put(ip, ipCounts.get(ip) + 1);
            }
         }
         
         return ipCounts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> ipCounts) {
         int maxVisit = Integer.MIN_VALUE;
         
         for (String key : ipCounts.keySet()) {
            int currentCount = ipCounts.get(key);
            
            if (currentCount > maxVisit) {
                maxVisit = currentCount;
            }
         }
         
         return maxVisit;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> ipCounts) {
         ArrayList<String> ipList = new ArrayList<String>();
         int maxVisit = mostNumberVisitsByIP(ipCounts);
         
         for (String key : ipCounts.keySet()) {
            int currentCount = ipCounts.get(key);
            
            if (currentCount == maxVisit) {
                ipList.add(key);
            }
         }
         
         return ipList;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays() {
         HashMap<String, ArrayList<String>> ipsPerDay = new HashMap<String, ArrayList<String>>();
         
         for (LogEntry le : records) {
             String accessTime = le.getAccessTime().toString();
             String day = accessTime.substring(4, 10);
             String currIp = le.getIpAddress();
             ArrayList<String> ipList;
             
             if (!ipsPerDay.containsKey(day)) {
                 ipList = new ArrayList<String>();
             } else {
                 ipList = ipsPerDay.get(day);
             }
             
             ipList.add(currIp);
             ipsPerDay.put(day, ipList);
         }
         
         return ipsPerDay;
     }
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> ipsPerDay) {
         String day = "";
         int maxVisit = Integer.MIN_VALUE;
         
         for (String key : ipsPerDay.keySet()) {
            int currentVisit = ipsPerDay.get(key).size();
            
            if (currentVisit > maxVisit) {
                maxVisit = currentVisit;
                day = key;
            }
         }
         
         return day;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> ipsPerDay, String day) {
        ArrayList<String> ipList = ipsPerDay.get(day);
        HashMap<String, Integer> ipCounts = new HashMap<String, Integer>();
        
        for (String ip : ipList) {
             if (!ipCounts.containsKey(ip)) {
                ipCounts.put(ip, 1);
            } else {
                ipCounts.put(ip, ipCounts.get(ip) + 1);
            }
        }
        
        return iPsMostVisits(ipCounts);
     }
}   
