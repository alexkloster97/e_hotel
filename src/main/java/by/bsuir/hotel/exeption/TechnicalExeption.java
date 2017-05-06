/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.hotel.exeption;

/**
 *  This class contains information about technical exception
 * @author Maria
 */
public class TechnicalExeption extends Exception{
    private Exception hidden;
    private String propertyMessage;

    /**
     * Constructor with parameters
     * @param error
     */
    public TechnicalExeption(String error) {
        super(error);
        
    }

    /**
     * Constructor with parameters
     * @param hidden
     * @param message
     */
    public TechnicalExeption(Exception hidden, String message) {
        super(message);
        this.hidden = hidden;
    }

    

    /**
     * This method is used to return exception
     * @return exception
     */
    public Exception getHidden() {
        return hidden;
    }

    /**
     * This method is used to return property message
     * @return property message
     */
    public String getPropertyMessage() {
        return propertyMessage;
    }

    /**
     *  This method is used to set property message
     * @param propertyMessage - property message
     */
    public void setPropertyMessage(String propertyMessage) {
        this.propertyMessage = propertyMessage;
    }
    
}
