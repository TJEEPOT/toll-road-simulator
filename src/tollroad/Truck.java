/******************************************************************************

Project     : CMP-4008Y - Programming 1, Assignment 2:
              Toll Road Simulator.

File        : Truck.java

Date        : 25-Feb-2019

Author      : Martin Siddons

Description : Details on the Vehicle Subclass, Truck.

History     : 25-Feb-2019 - v1.0 - Initial setup + functionality.
            : 11-Mar-2019 - v1.1 - Added toString override for test harness.

******************************************************************************/
package tollroad;

public final class Truck extends Vehicle 
{
    private final int numTrailers;
    
    // Constructor for truck:
    public Truck(String r, String m, int t)
    {
        super(r,m); // send reg and make values to Vehicle constructor 
        this.numTrailers = t;
    }
    
    
    // Accessor method for number of trailers:
    public int getTrailers()
    {
        return numTrailers;
    }
    
    
    // Override for abstract method:
    @Override
    public int calculateBasicTripCost()
    {
        if (this.numTrailers <= 1)
            return 1250;
        return 1500; // no need to check if two or more, just go.
    }
    
    
    // toString implementation:
    @Override
    public String toString()
    {   // formatted as the reference file.
        return reg + "," +
               make + "," +
               numTrailers; 
    }
    
    
    // Test Harness:
    public static void main(String[] args)
    {
        Truck t1 = new Truck("A123 BCD", "Ford", 1);
        Truck t2 = new Truck("CB13IIZ", "Kia", 3);
        
        System.out.println("t1 toString: " + t1);
        System.out.println("t1 reg: " + t1.getReg());
        System.out.println("Trip cost for 1 trailer: " + 
                            t1.calculateBasicTripCost());
        System.out.println("t2 make: " + t2.getMake());
        System.out.println("t2 trailers: " + t2.getTrailers());
        System.out.println("t2 trip cost: " + t2.calculateBasicTripCost());
    }
}