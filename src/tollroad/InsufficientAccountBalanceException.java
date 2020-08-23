/******************************************************************************

Project     : CMP-4008Y - Programming 1, Assignment 2:
              Toll Road Simulator.

File        : InsufficientAccountBalanceException.java

Date        : 25-Feb-2019

Author      : Martin Siddons

Description : Class to define an exception to be thrown when there are not
              enough funds within the called CustomerAccount.

History     : 25-Feb-2019 - v1.0 - Initial setup

******************************************************************************/
package tollroad;

public final class InsufficientAccountBalanceException extends Exception {

    public InsufficientAccountBalanceException() 
    {
    }
    
}
