/******************************************************************************

Project     : CMP-4008Y - Programming 1, Assignment 2:
              Toll Road Simulator.

File        : Vehicle.java

Date        : 25-Feb-2019

Author      : Martin Siddons

Description : Definitions for the Vehicle Superclass.

History     : 25-Feb-2019 - v1.0 - Initial setup, implemented vars + methods.

******************************************************************************/
package tollroad;

public abstract class Vehicle 
{
    protected final String reg;
    protected final String make;
    
    // Constructor
    public Vehicle(String r, String m)
    {
        this.reg = r;
        this.make = m;
    }
    
    // Accessor methods:
    public String getReg()
    {
        return reg;
    }
    
    public String getMake()
    {
        return make;
    }
    
    // Abstract method for trip cost:
    abstract public int calculateBasicTripCost();
}
