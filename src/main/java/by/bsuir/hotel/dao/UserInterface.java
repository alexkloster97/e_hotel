/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.hotel.dao;

import by.bsuir.hotel.entity.User;
import by.bsuir.hotel.exeption.ConnectionPollException;
import by.bsuir.hotel.exeption.DAOExeption;
import java.util.List;

/**
 * This interface is used to organize access to user table in database
 * @author Maria
 */
public interface UserInterface {
    /**
     * This method is used to find user by login
     * @param login - login of user
     * @return  user
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    public User findByLogin(String login) throws DAOExeption, ConnectionPollException;
    /**
     * This method is used to find admin
     * @param idGroup - number of usergroup
     * @param idAdmin - id of admin
     * @return list of admins
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    public List<User> findAdmin(int idGroup, int idAdmin)throws DAOExeption, ConnectionPollException;
}
