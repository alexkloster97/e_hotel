/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.hotel.dao;

import by.bsuir.hotel.connection.ConnectionPool;
import by.bsuir.hotel.entity.Entity;
import by.bsuir.hotel.exeption.ConnectionPollException;
import by.bsuir.hotel.exeption.DAOExeption;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


/**
 * Abstract class
 * @author Maria
 */
public abstract class AbstractDAO {
    final static Logger log = Logger.getLogger(AbstractDAO.class);
     /**
     * connection to database
     */
    protected Connection connection;

    /**
     *  Constructor without parameters
     */
    public AbstractDAO() {
         try {
             connection = ConnectionPool.getConnectionPool().getConnection();
         } catch (SQLException ex) {
             ConnectionPollException exep = new ConnectionPollException(ex.getMessage());
             exep.setPropertyMessage("connection.pool.exception");
             
         }
    }
     
     /**
     * This method is used to find object by id
     * @param id - number of object 
     * @return object
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    abstract public Entity getByID(int id) throws DAOExeption, ConnectionPollException;
     /**
     * This method is used to find all
     * @return list of objects
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    abstract public List<Entity> read() throws DAOExeption, ConnectionPollException;
     /**
     * This method is used to create new record in database
     * @param obj - this object
     * @return result (1 or 0 )
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    abstract public int create(Entity obj) throws DAOExeption, ConnectionPollException;
     /**
     * This method is used to delete record from database
     * @param obj - object that should be deleted 
     * @return result ( 1 or 0 )
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    abstract public int delete(Entity obj) throws DAOExeption, ConnectionPollException;
     /**
     * This method is used to update record in the database
     * @param obj - object that should be deleted 
     * @return result ( 1 or 0 )
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    abstract public int update(Entity obj) throws DAOExeption, ConnectionPollException;
     
     /**
     * This method is used to close statement
     * @param st - statement 
     */
    public void close(Statement st) {


        if(st!=null){
             try {
                 st.close();
             } catch (SQLException ex) {
                 log.error(ex);
                
             }
         }
     }
}
