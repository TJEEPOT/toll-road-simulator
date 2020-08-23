/******************************************************************************

Project     : CMP-4008Y - Programming 1, Assignment 2:
              Toll Road Simulator.

File        : Van.java

Date        : 25-Feb-2019

Author      : Martin Siddons

Description : Details on the Vehicle Subclass, Van

History     : 25-Feb-2019 - v1.0 - Initial setup, implemented vars + methods.
            : 11-Mar-2019 - v1.1 - Added toString override for test harness.

******************************************************************************/
package tollroad;

public final class Van extends Vehicle
{
    private final int payload;
    
    // Van constructor:
    public Van(String r, String m, int p)
    {
        super(r,m); // Construct reg and make with superclass.
        this.payload = p;        
    }
    
    
    // Accessor method for payload
    public int getPayload()
    {
        return payload;
    }
    
    
    // Override abstract method from Vehicle:
    @Override
    public int calculateBasicTripCost()
    {
        if(this.payload <= 600)
            return 500;
        else if(this.payload <= 800) // no need to check if greater than 600.
            return 750;
        return 1000; // anything left is above 800kg anyway, no need to check.
    }
    
    
    // toString implementation:
    @Override
    public String toString()
    {   // formatted as the reference file.
        return reg + "," +
               make + "," +
               payload; 
    }
    
    
    // Test Harness:
    public static void main(String[] args)
    {
        Van v1 = new Van("A123 BCD", "Ford", 100);
        Van v2 = new Van("CB13IIZ", "Kia", 1100);
        
        System.out.println("v1 toString: " + v1);
        System.out.println("v1 reg: " + v1.getReg());
        System.out.println("Trip cost for 100 payload: " + 
                            v1.calculateBasicTripCost());
        System.out.println("v2 payload: " + v2.getPayload());
        System.out.println("v2 make: " + v2.getMake());
        System.out.println("v2 trip cost: " + v2.calculateBasicTripCost());
    }
}
