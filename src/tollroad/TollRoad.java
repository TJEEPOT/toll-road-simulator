/******************************************************************************

Project     : CMP-4008Y - Programming 1, Assignment 2:
              Toll Road Simulator.

File        : TollRoad.java

Date        : 25-Feb-2019

Author      : Martin Siddons

Description : Class to set up ArrayList per toll road to hold customer accounts.
              In addition, it will perform searches on customer accounts on a 
              specified toll road and can charge accounts found.

History     : 25-Feb-2019 - v1.0 - Initial setup
*           : 14-Mar-2019 - v1.1 - Getters and setters, addCustomer, 
*               findCustomer created. 
*           : 15-Mar-2019 - v1.3 - Completed findCustomer, chargeCustomer,
*               test harness.
*           : 16-Mar-2019 - v1.4 - Researched Java storage methods and switched 
*               ArrayList for HashMap to improve efficiency.

******************************************************************************/
package tollroad;

import java.util.*;

public class TollRoad
{   // Using a HashMap to store the collection of CustomerAccounts along with 
    // that account's vehicle registration. This should allow for more efficient 
    // searching compared to ArrayList as findCustomer only needs to look 
    // through an array of Strings rather than query every CustomerAccount.
    private final Map<String, CustomerAccount> accountList;
    private int moneyMade;
    
    public TollRoad()
    {
        this.accountList = new HashMap<String, CustomerAccount>();
        this.moneyMade = 0;
    }
    
    // Getters for both fields:
    public Collection<CustomerAccount> getAccountList()
    {
        return accountList.values();
    }
    public int getMoneyMade()
    {
        return moneyMade;
    }
    
    
    // Add a new customer to the map for -this- road:
    public void addCustomer(CustomerAccount acc)
    {   // Map key is registration number, value is the given CustomerAccount.
        accountList.put(acc.getVehicle().getReg(), acc);
    }
    
    
    // Customer search of -this- road via registration number:
    public CustomerAccount findCustomer(String regNum) throws 
            CustomerNotFoundException
    {
        for (String s: this.accountList.keySet())
        {   // Compare the current reg with regNum, return 0 if they match
            int c = s.compareTo(regNum);
            if (c == 0) // Return this.CustomerAccount if matching.
                return this.accountList.get(s); 
        }
        // If the end of the list is reached without match, throw exception.
        throw new CustomerNotFoundException(); 
    }

    
    // Method to call makeTrip() from CustomerAccount on -this- road:
    public void chargeCustomer(String registrationNumber) throws 
            CustomerNotFoundException, InsufficientAccountBalanceException
    {   
        CustomerAccount a = this.findCustomer(registrationNumber);
        moneyMade = this.moneyMade + a.makeTrip();
    }
    
    
    // Another toString:
    @Override
    public String toString()
    {
        return accountList + ", " + moneyMade;
    }
    
    
    // Test harness.
    public static void main(String[] args)
    {
        try
        {
            TollRoad t = new TollRoad();
            Car v1 = new Car("AA11 1AA", "Ford", 2);
            CustomerAccount ca1 = new CustomerAccount("Mary", "Bloggs", v1, 30);
            
            t.addCustomer(ca1);
            System.out.println("Get list of customers: " + t.getAccountList());
            
            System.out.println("Return ca1: " + t.findCustomer("AA11 1AA"));
            
            ca1.addFunds(500);
            System.out.println("Charging 'AA11 1AA':");
            t.chargeCustomer("AA11 1AA");
            System.out.println("New toll balance: " + t.getMoneyMade());
        }
        catch(CustomerNotFoundException e)
        {   // Actual reg will be printed in TollRoadMain based on loop num.
            System.out.println("AA11 1AA" + " : findCustomer failed. "
                                + "CustomerAccount does not exist");
        }
        catch(InsufficientAccountBalanceException e)
        {
            System.out.println("AA11 1AA" + " : makeTrip failed. "
                    + "Insufficient funds");
        }
    }
}