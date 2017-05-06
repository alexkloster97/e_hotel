/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.hotel.exeption;

/**
 * This class contains information about DAO exception
 * @author Игорь
 */
public class DAOExeption extends TechnicalExeption{

    /**
     * Constructor with parameters
     * @param error
     */
    public DAOExeption(String error) {
        super(error);
    }
    
}
