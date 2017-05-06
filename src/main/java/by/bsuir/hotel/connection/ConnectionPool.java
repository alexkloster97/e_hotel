/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.hotel.connection;

import by.bsuir.hotel.resource.ResourcesBundle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import org.apache.log4j.Logger;


/**
 * This class is used to  organize connection to database
 * @author Maria
 */
public class ConnectionPool {

    private final int CONNECTION_NUMBER ;
    private Queue<Connection> connectionQueue;
    private final Semaphore semaphore ;
    private static ConnectionPool connection;
    final static Logger log = Logger.getLogger(ConnectionPool.class);

    private ConnectionPool() throws SQLException {
        CONNECTION_NUMBER = Integer.parseInt(new ResourcesBundle(ResourcesBundle.dbProperties).getValue(ResourcesBundle.CONNECTION_NUMBER));
        semaphore = new Semaphore(CONNECTION_NUMBER, true);
        connectionQueue = new LinkedBlockingQueue<Connection>();
        for (int i = 0; i < CONNECTION_NUMBER; i++) {
            connectionQueue.add(newConnection());
        }

    }

    /**
     * This method is used to create connection to database
     * @return Connection to database
     * @throws SQLException
     */
    public Connection newConnection() throws SQLException {
        try {
            Class.forName(new ResourcesBundle(ResourcesBundle.dbProperties).getValue(ResourcesBundle.DRIVER));
        } catch (ClassNotFoundException ex) {
            log.error(ex);

        }
        return DriverManager.getConnection(new ResourcesBundle(ResourcesBundle.dbProperties).getValue(ResourcesBundle.DATABASE));
    }

    /**
     * This method is used to get free connection from queue
     * @return Connection from queue
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            semaphore.acquire();
            connection = connectionQueue.poll();
        } catch (InterruptedException ex) {
           log.error(ex);
        }
        if(connection == null){
            connection = connectionQueue.poll();
        }
        return connection;
    }

    /**
     * This method is used to set connection to queue
     * @param connection - connection 
     */
    public void setConnection(Connection connection) {
        if(connection!=null){
            connectionQueue.add(connection);
            semaphore.release();
        }

    }
    
    /**
     * This method is used to create object of class ConnectionPool
     * @return object of class ConnectionPool
     * @throws SQLException
     */
    public synchronized static ConnectionPool getConnectionPool() throws SQLException{
        if(connection == null)
            connection = new ConnectionPool();
        return connection;
    }
    
    /**
     * This method is used to destroy connection 
     */
    public void destroy(){
        Connection connection;
        Logger log = Logger.getRootLogger();
        for(int i=0; i<CONNECTION_NUMBER; i++){
            connection = connectionQueue.poll();
            if(connection!=null)
                try {
                connection.close();
            } catch (SQLException ex) {
                log.error(ex);
            }
        }
    }
}
