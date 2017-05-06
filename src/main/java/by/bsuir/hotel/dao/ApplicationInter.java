/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.hotel.dao;

import by.bsuir.hotel.entity.Application;
import by.bsuir.hotel.exeption.ConnectionPollException;
import by.bsuir.hotel.exeption.DAOExeption;
import java.util.List;

/**
 * This interface is used to organize access to application table in database
 * @author Maria
 */
public interface ApplicationInter {
    /**
     * This method is used to update application
     * @param idApplication - number of application
     * @param idUpdate - whom this application would be uptated
     * @param status - status of application
     * @return result of update
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    public int updateStatus(int idApplication, int idUpdate, String status) throws DAOExeption, ConnectionPollException;
    /**
     * This method is used to find application by idUser
     * @param idUser - number of the user
     * @return list of the application
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    public List<Application> findApplicationByIdUser(int idUser) throws DAOExeption, ConnectionPollException;
    /**
     * This method is used to find application by status of application
     * @param status - information about application
     * @return list of the applications
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    public List<Application> findApplicationsByStatus(String status) throws DAOExeption, ConnectionPollException; 
}
