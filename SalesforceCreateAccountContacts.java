package wsc;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.*;

import com.sforce.soap.partner.Connector;
import com.sforce.soap.partner.DeleteResult;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.soap.partner.QueryResult;
import com.sforce.soap.partner.SaveResult;
import com.sforce.soap.partner.Error;
import com.sforce.soap.partner.sobject.SObject;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

public class Main {
  
	
	static  String USERNAME = JOptionPane.showInputDialog("Username: ");
	static  String PASSWORD = JOptionPane.showInputDialog("Please enter your password+token: ");
	
	//PASSWORD.setEchoChar('*');

	

	
  static PartnerConnection connection;

 public static void main(String[] args) {
	 

    ConnectorConfig config = new ConnectorConfig();
    config.setUsername(USERNAME);
    config.setPassword(PASSWORD);
    //config.setTraceMessage(true);
    
    try {
      
      connection = Connector.newConnection(config);
      
      // display some current settings
      System.out.println("Auth EndPoint: "+config.getAuthEndpoint());
      System.out.println("Service EndPoint: "+config.getServiceEndpoint());
      System.out.println("Username: "+config.getUsername());
      System.out.println("SessionId: "+config.getSessionId());
     // System.out.println("Password: "+config.getPassword());
      
      // run the different examples
     // queryContacts();
      createAccounts();
      createContacts();
     // updateAccounts();
      //deleteAccounts();
      
      
    } catch (ConnectionException e1) {
        e1.printStackTrace();
    }  

  }
  
 /*
  // queries and displays the 5 newest contacts
  private static void queryContacts() {
    
    System.out.println("Querying for the 5 newest Contacts...");
    
    try {
       
      // query for the 5 newest contacts      
      QueryResult queryResults = connection.query("SELECT Id, FirstName, LastName, Account.Name " +
      		"FROM Contact WHERE AccountId&nbsp;!= NULL ORDER BY CreatedDate DESC LIMIT 5");
      if (queryResults.getSize() > 0) {
    	  for (SObject s: queryResults.getRecords()) {
    	    System.out.println("Id: " + s.getId() + " " + s.getField("FirstName") + " " + 
    	        s.getField("LastName") + " - " + s.getChild("Account").getField("Name"));
    	  }
    	}
      
    } catch (Exception e) {
      e.printStackTrace();
    }    
    
  }
  
  */
  static int numberaccounts = Integer.parseInt(JOptionPane.showInputDialog("How many accounts do you want to create?: "));
  
  

  
  // create test Accounts
  private static void createAccounts() {
    
    System.out.println("Creating " + numberaccounts + " new test Accounts...");
    SObject[] records = new SObject[numberaccounts];

    try {
       
      
    	 
    	
      //ORIGINAL for (int i=0;i<5;i++) {
    	 for (int i=0;i<numberaccounts;i++) {
        SObject so = new SObject();
        so.setType("Account");
        so.setField("Name", "Test Account "+i);
        so.setField("BillingStreet", "Test Street "+i);
        so.setField("BillingCity", "Test City "+i);
        so.setField("BillingCountry", "Test Country "+i);
        so.setField("Phone", "00001 "+i);
        so.setField("Fax", "10000 "+i);
        so.setField("Website", "www.talksalesforce.de");
        
        
        records[i] = so;
      }

      
      // create the records in Salesforce.com
      SaveResult[] saveResults = connection.create(records);
      
      // check the returned results for any errors
      for (int i=0; i< saveResults.length; i++) {
        if (saveResults[i].isSuccess()) {
          System.out.println(i+". Successfully created record - Id: " + saveResults[i].getId());
        } else {
          Error[] errors = saveResults[i].getErrors();
          for (int j=0; j< errors.length; j++) {
            System.out.println("ERROR creating record: " + errors[j].getMessage());
          }
        }    
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }    
    
  }
 
  
static int numbercontacts = Integer.parseInt(JOptionPane.showInputDialog("How many contacts do you want to create?: "));


  
  
  // create test Contacts
  private static void createContacts() {
    
    System.out.println("Creating " + numbercontacts + " new test Contacts...");
    SObject[] records = new SObject[numbercontacts];

    try {
       
      
    	 
    	
      //ORIGINAL for (int i=0;i<5;i++) {
    	 for (int i=0;i<numbercontacts;i++) {
        SObject so = new SObject();
        so.setType("Contact");
        
        so.setField("FirstName", "Test First Name "+i);
        so.setField("LastName", "Test Last Name "+i);
        so.setField("LeadSource", "Web");
        so.setField("Phone", "123456 "+i);
        so.setField("MailingStreet", "Test Street "+i);
        so.setField("MailingCity", "Test City "+i);
        so.setField("MailingCountry", "Test Country "+i);
        
        records[i] = so;
      }

      
      // create the records in Salesforce.com
      SaveResult[] saveResults = connection.create(records);
      
      // check the returned results for any errors
      for (int i=0; i< saveResults.length; i++) {
        if (saveResults[i].isSuccess()) {
          System.out.println(i+". Successfully created record - Id: " + saveResults[i].getId());
        } else {
          Error[] errors = saveResults[i].getErrors();
          for (int j=0; j< errors.length; j++) {
            System.out.println("ERROR creating record: " + errors[j].getMessage());
          }
        }    
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }    
    
  }
  
}