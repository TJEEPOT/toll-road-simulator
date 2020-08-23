/******************************************************************************

Project     : CMP-4008Y - Programming 1, Assignment 2:
              Toll Road Simulator.

File        : Car.java

Date        : 25-Feb-2019

Author      : Martin Siddons

Description : Details on the Vehicle subclass, Car.

History     : 25-Feb-2019 - v1.0 - Initial setup, implemented vars + methods.
            : 11-Mar-2019 - v1.1 - Added toString override for test harness.

******************************************************************************/
package tollroad;

public final class Car extends Vehicle
{
    // Variable definitions for 'reg' and 'make' inherited from Vehicle class.
    private final int numberOfSeats;
    
    // Constructor for car:
    public Car(String r, String m, int n)
    {
        super(r,m); // 'reg' and 'make' values passed to Vehicle constructor.
        this.numberOfSeats = n;
    }
    
    
    // Accessor for car:
    public int getNumberOfSeats()
    {
        return numberOfSeats;
    }
    
    
    // Overriding abstract method from Vehicle to provide new functionality:
    @Override
    public int calculateBasicTripCost()
    {
        if (this.numberOfSeats <= 5)
        {
            return 500;
        }
        return 600;
    }
    
    
    // toString implementation:
    @Override
    public String toString()
    {   // formatted as the reference file.
        return reg + "," +
               make + "," +
               numberOfSeats; 
    }
    
    
    // Test Harness:
    public static void main(String[] args)
    {
        Car c1 = new Car("A123 BCD", "Ford", 1);
        Car c2 = new Car("CB13IIZ", "Kia", 7);
        
        System.out.println("c1 toString: " + c1);
        System.out.println("c1 reg: " + c1.getReg());
        System.out.println("Trip cost for 4 seats: " + 
                            c1.calculateBasicTripCost());
        System.out.println("c2 make: " + c2.getMake());
        System.out.println("c2 number of seats: " + c2.getNumberOfSeats());
        System.out.println("c2 trip cost: " + c2.calculateBasicTripCost());
    }
}
