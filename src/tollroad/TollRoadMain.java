/******************************************************************************

Project     : CMP-4008Y - Programming 1, Assignment 2:
              Toll Road Simulator.

File        : TollRoadMain.java

Date        : 25-Feb-2019

Author      : Martin Siddons

Description : Main class to load a Toll Road from file and simulate it's use. 

History     : 25-Feb-2019 - v1.0 - Initial setup
*           : 15-Mar-2019 - v1.1 - Implemented initialiseTollRoadFromFile.
*           : 16-Mar-2019 - v1.2 - Implemented simulateFromFile and main.

******************************************************************************/
package tollroad;

import java.io.*;

public class TollRoadMain 
{   // Build a TollRoad object and populate it with customer data from file:
    public static TollRoad initialiseTollRoadFromFile()
    {
        TollRoad road = new TollRoad();
        // Load the data via Java 7's Automatic Resource Management.
        try (BufferedReader in = new BufferedReader(
                                 new FileReader("customerData.txt")))
        {
            String thisLine;
            while ((thisLine = in.readLine()) != null)
            {   // Split the line into accounts.
                String[] accounts = thisLine.split("#");
                for (String account: accounts)
                {   // Split the account into elements.
                    String[] element = account.split(",");
                    // Assign each element to a CustomerAccount variable which
                    // is then assigned to the road.
                    
                    // Make a vehicle list to hold the vehicle to be created.
                    Vehicle[] vehicle = new Vehicle[1];
                    if ("Car".equals(element[0]))
                    {
                        Car c = new Car(element[1], 
                                        element[4], 
                                        Integer.parseInt(element[5]));
                        vehicle[0] = c;
                    }
                    else if ("Van".equals(element[0]))
                    {
                        Van v = new Van(element[1], 
                                        element[4], 
                                        Integer.parseInt(element[5]));
                        vehicle[0] = v;
                    }
                    else
                    {
                        Truck t = new Truck(element[1], 
                                            element[4],
                                            Integer.parseInt(element[5]));
                        vehicle[0] = t;
                    }
                    
                    // Create the account, including the vehicle just created.
                    CustomerAccount c = new CustomerAccount(element[2],
                                                            element[3],
                                                            vehicle[0],
                                           Integer.parseInt(element[6]));
                    
                    // Ensure that the account is set to the correct discount.
                    if ("STAFF".equals(element[7]))
                    {
                        c.activateStaffDiscount();
                    }
                    else if ("FRIENDS_AND_FAMILY".equals(element[7]))
                    {
                        c.activateFriendsAndFamilyDiscount();
                    }
                    
                    // Add the CustomerAccount to the toll road.
                    road.addCustomer(c);
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("\nAn IO exception has occured, Aborting.");
        }
        // No need for finally block when using Automatic Resource Management.
        return road;
    }
    
    
    public static void simulateFromFile(TollRoad road)
    {   // Load the transactions via Java 7's Automatic Resource Management.
        try (BufferedReader in = new BufferedReader(
                                 new FileReader("transactions.txt")))
        {
            String thisLine;
            while ((thisLine = in.readLine()) != null)
            {   // Split the line into accounts.
                String[] transactions = thisLine.split("\\$");
                for (String transaction: transactions)
                {   // Split the transaction into useable elements.
                    String[] element = transaction.split(",");
                    if ("addFunds".equals(element[0]))
                    {
                        try
                        {
                            String reg = element[1];
                            int amount = Integer.parseInt(element[2]);
                            System.out.print(reg);
                            road.findCustomer(reg)
                                .addFunds(amount);
                            System.out.println(": " + amount 
                                             + " added successfully");
                        }
                        catch(CustomerNotFoundException e)
                        {
                            System.out.println(": makeTrip failed. "
                                    + "CustomerAccount does not exist");
                        }

                    }
                    if ("makeTrip".equals(element[0]))
                    {
                        try
                        {
                            String reg = element[1];
                            System.out.print(reg);
                            road.chargeCustomer(reg);
                            System.out.println(": Trip completed successfully");
                        }
                        catch(CustomerNotFoundException e)
                        {
                            System.out.println(": makeTrip failed. "
                                    + "CustomerAccount does not exist");
                        }
                        catch(InsufficientAccountBalanceException e)
                        {
                            System.out.println(": makeTrip failed. "
                                             + "Insufficient funds");
                        }
                    }
                }
            }
        }
        catch(IOException e)
        {
            System.out.println("\nAn IO exception has occured, Aborting.");
        }
        // No need for finally block when using Automatic Resource Management.
    }
    
    
    // Call the static methods above and print moneyMade:
    public static void main(String[] args) 
    {
        TollRoad r = initialiseTollRoadFromFile();
        simulateFromFile(r);
        System.out.println("During the simulation, the toll road made "
                         + r.getMoneyMade() + " pence.");
    }
}
