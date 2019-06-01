
/**
 * Assignment: Website Visits
 * 
 * @author Deny Kiantono 
 * @version 1.0
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }
    
    public void testUniqueIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        System.out.println("Total unique IPs = " + la.countUniqueIPs());
    }
    
    public void testPrintAllHigherThanNum() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAllHigherThanNum(200);
    }
    
    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog-short_log");
        
        ArrayList<String> record = la.uniqueIPVisitsOnDay("Sep 14");
        System.out.println("Sep 14 = " + record);
        
        record = la.uniqueIPVisitsOnDay("Sep 30");
        System.out.println("Sep 30 = " + record);
    }
    
    public void testCountUniqueIPsInRange() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        System.out.println("countUniqueIPsInRange 200 - 299 = " + la.countUniqueIPsInRange(200, 299));
        System.out.println("countUniqueIPsInRange 300 - 399 = " + la.countUniqueIPsInRange(300, 399));
    }
    
    public void testCountVisitsPerIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        HashMap<String, Integer> ipCounts = la.countVisitsPerIP();
        System.out.println(ipCounts);
    }
    
    public void testMostNumberVisitsByIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String, Integer> ipCounts = la.countVisitsPerIP();
        System.out.println("Most number of visits = " + la.mostNumberVisitsByIP(ipCounts));
    }
    
    public void testIPsMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String, Integer> ipCounts = la.countVisitsPerIP();
        ArrayList<String> ipList = la.iPsMostVisits(ipCounts);
        System.out.println("Ips with most visits = " + ipList);
    }
    
    public void testIPsForDays() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String, ArrayList<String>> ipsPerDay = la.iPsForDays();
        System.out.println("Ips per day = " + ipsPerDay);
    
    }
    
    public void testDayWithMostIPVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String, ArrayList<String>> ipsPerDay = la.iPsForDays();
        String day = la.dayWithMostIPVisits(ipsPerDay);
        System.out.println("Day with most ip visit = " + day); 
    }
    
    public void testIPsWithMostVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String, ArrayList<String>> ipsPerDay = la.iPsForDays();
        ArrayList<String> ipList = la.iPsWithMostVisitsOnDay(ipsPerDay, "Sep 30");
        System.out.println("IP with most visits on Sep 30 = " + ipList);
    }
}
