/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.hotel.dao;

import by.bsuir.hotel.entity.Bill;
import by.bsuir.hotel.exeption.ConnectionPollException;
import by.bsuir.hotel.exeption.DAOExeption;

/**
 * This interface is used to organize access to bill table in database
 * @author Maria
 */
public interface BillInter {
    /**
     * This method is used to find bill by id
     * @param idApplication - number of application
     * @return bill
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    public Bill getBillByApplication(int idApplication) throws DAOExeption, ConnectionPollException;
}
