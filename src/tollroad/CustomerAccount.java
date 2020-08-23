/******************************************************************************

Project     : CMP-4008Y - Programming 1, Assignment 2:
              Toll Road Simulator.

File        : CustomerAccount.java

Date        : 25-Feb-2019

Author      : Martin Siddons

Description : Class to handle setting up and funding a customer account.

History     : 25-Feb-2019 - v1.0 - Initial setup
*           : 11-Mar-2019 - v1.1 - Added constructors, DiscountType methods,
*               addFunds, toString.
*           : 12-Mar-2019 - v1.2 - Implemented makeTrip, Comparable interface.
*           : 14-Mar-2019 - v1.3 - Finished Comparable implementation, added
*               accessors and finished test harness.
*           : 17-Mar-2019 - v1.4 - Fixed bug with makeTrip deducting money from 
*               account before checking if the account had enough to pay. Also
*               ensured methods that don't return values are set as void.

******************************************************************************/
package tollroad;

public class CustomerAccount implements Comparable<CustomerAccount>
{
    private enum DiscountType {NONE, STAFF, FRIENDS_AND_FAMILY}

    private final String firstName;
    private final String surname;
    private final Vehicle vehicle;
    private int balance;
    private DiscountType discount;
    
    // Constructor for account:
    public CustomerAccount(String f, String s, Vehicle v, int b)
    {
        this.firstName = f;
        this.surname = s;
        this.balance = b;
        this.discount = DiscountType.NONE;
        this.vehicle = v;
    }
    
    
    // Accessor Methods (only for approprate fields):
    public Vehicle getVehicle()
    {
        return vehicle;
    }
    
    public int getBalance()
    {
        return balance;
    }
    
    public String getDiscount()
    {
        return discount.name();
    }
    
    
    // Methods for changing account discount value:
    public void activateStaffDiscount()
    {
        this.discount = DiscountType.STAFF;
    }
    
    public void activateFriendsAndFamilyDiscount()
    {
        this.discount = DiscountType.FRIENDS_AND_FAMILY;
    }
    
    public void deactivateDiscount()
    {
        this.discount = DiscountType.NONE;
    }
    
    
    // Method to add funds to the account:
    public void addFunds(int amount)
    {
        this.balance = this.balance + amount;   
    }
    
    
    // Simulation of making a trip on the toll road:
    public int makeTrip() throws InsufficientAccountBalanceException
    {
        int tripCost = this.vehicle.calculateBasicTripCost();
        
        // calculate the trip cost using ints to ensure no lossy convertion nor
        // a need to cast to int.
        if (this.discount == DiscountType.STAFF)
        {
            tripCost = tripCost / 2; // 50% reduction of tripCost.
        }
        else if (this.discount == DiscountType.FRIENDS_AND_FAMILY)
        {
            tripCost = tripCost - (tripCost / 10); // 10% reduction of tripCost.
        }
        
        int newBalance = this.balance - tripCost;
        if (newBalance < 0)
        {
            throw new InsufficientAccountBalanceException();
        }
        else
        {
            this.balance = newBalance;
            return tripCost;
        }
    }
    
    
    // Comparable interface to compare registration numbers:
    @Override
    public int compareTo(CustomerAccount a)
    {
        int comparison = this.vehicle.getReg().compareTo(a.vehicle.getReg());
        if (comparison < 0) // if this.reg comes before a.reg, return -1.
            return -1;
        else if (comparison > 0) // if this.reg comes after a.reg, return -1.
            return 1;
        return 0; // no need to check if they match, they do. Just return 0.
    }
        
    
    // toString implementation, formatted close to the reference file:
    @Override
    public String toString()
    {   
        return vehicle.getClass().getSimpleName() + "," +
               firstName + "," +
               surname + "," +
               vehicle + "," +
               balance + "," +
               discount + "#";
    }
    
    // Test Harness:
    public static void main(String[] args)
    {
        try
        {
            Car v1 = new Car("AA11 1AA", "Ford", 2);
            CustomerAccount ca1 = new CustomerAccount("Mary", "Bloggs", v1, 300);

            System.out.println("ca1 details: " + ca1);
            System.out.println("ca1 first name: " + ca1.firstName);
            System.out.println("ca1 surname: " + ca1.surname);
            System.out.println("ca1 vehicle reg: " + ca1.vehicle.getReg());
            System.out.println("ca1 balance is: " + ca1.getBalance());
            ca1.addFunds(500);
            System.out.println("Adding 500 to account ca1 gives: " 
                                + ca1.getBalance());
            
            System.out.println("ca1 discount type: " + ca1.getDiscount());
            ca1.activateStaffDiscount();
            System.out.println("ca1 discount type after Staff activation: " 
                                + ca1.getDiscount());
            ca1.activateFriendsAndFamilyDiscount();
            System.out.println("ca1 discount type after F+F activation: " 
                                + ca1.getDiscount());
            ca1.deactivateDiscount();
            System.out.println("ca1 discount type after discount deactivation: " 
                                + ca1.getDiscount());
            
            Van v2 = new Van("ZX12 3CV", "Ford", 400);
            CustomerAccount ca2 = new CustomerAccount("John", "Bloggs", v2, 10);
            System.out.println("Comparing ca1 to ca2 gives: " 
                                + ca1.compareTo(ca2));
            System.out.println("Comparing ca2 to ca1 gives: " 
                                + ca2.compareTo(ca1));
            
            ca1.makeTrip();
            System.out.println("ca1 balance after trip: " + ca1.getBalance());
            ca1.makeTrip(); // This will trigger the exception below.
        }
        catch(InsufficientAccountBalanceException e)
        { // Actual registration will be returned in TollRoadMain based on loop.
            System.out.println("REG" + " : makeTrip failed. "
                                + "Insufficient funds");
        }
    }
}