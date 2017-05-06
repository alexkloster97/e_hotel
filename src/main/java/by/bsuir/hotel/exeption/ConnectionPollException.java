/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.hotel.exeption;

/**
 * This class contains information about connection poll exception
 * @author Maria
 */
public class ConnectionPollException extends TechnicalExeption{

    /**
     * Constructor with parameters
     * @param error
     */
    public ConnectionPollException(String error) {
        super(error);
    }
    
}
