/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.hotel.dao;

import by.bsuir.hotel.connection.ConnectionPool;
import by.bsuir.hotel.entity.Entity;
import by.bsuir.hotel.entity.TypeOfRoom;
import by.bsuir.hotel.exeption.ConnectionPollException;
import by.bsuir.hotel.exeption.DAOExeption;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to organize access to typeroom table in database
 * @author Maria
 */
public class TypeRoomDAO extends AbstractDAO{

     /**
     * This parameter  is used to find typeroom by id
     */
    public final static String SQL_GET_TYPEROOM_BY_ID="SELECT typenumber.typeNumber, typenumber.description "
            + "FROM typenumber "
            + "WHERE idTypeNumber=?";
    /**
     * This parameter  is used to find typeroom 
     */
    public final static String SQL_GET_TYPEROOM = "SELECT idTypeNumber,typeNumber, description FROM typenumber";
    /**
     * This parameter  is used to update typeroom
     */
    public final static String SQL_UPDATE_TYPEROOM = "UPDATE typenumber SET typenumber.typeNumber= ?, typenumber.description= ? WHERE typenumber.idTypeNumber= ?";
    /**
     * This parameter  is used to create typeroom 
     */
    public final static String SQL_INSERT_TYPEROOM = "INSERT INTO typenumber (typenumber, description) VALUES(?,?)";
    /**
     * This parameter  is used to delete typeroom 
     */
    public final static String SQL_DELETE_TYPEROOM = "DELETE FROM typenumber WHERE idTypeNumber= ?";

    /**
     * Constructor without parameters
     */
    public TypeRoomDAO() {
        super();
    }

    
    /**
     * This method is used to find object by id
     * @param id - number of hotelnumber
     * @return object
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    @Override
    public Entity getByID(int id) throws DAOExeption, ConnectionPollException {
        TypeOfRoom type = new TypeOfRoom();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_GET_TYPEROOM_BY_ID);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            type.setType(resultSet.getString("typeNumber"));
            type.setDescription(resultSet.getString("description"));
            type.setId(id);
        } catch (SQLException ex) {
            try {
                ConnectionPool.getConnectionPool().setConnection(connection);
            } catch (SQLException ex1) {
                ConnectionPollException exep = new ConnectionPollException(ex1.getMessage());
                exep.setPropertyMessage("connection.pool.exception");
                throw exep;
            }
            DAOExeption dao = new DAOExeption (ex.getMessage());
            dao.setPropertyMessage("dao.sqlexception");
            throw dao;
        }finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    DAOExeption dao = new DAOExeption(ex.getMessage());
                    dao.setPropertyMessage("dao.sqlexception");
                    throw dao;
                }
            } else {
                DAOExeption dao = new DAOExeption("");
                dao.setPropertyMessage("dao.statement");
                throw dao;
            }
        }
        try {
                ConnectionPool.getConnectionPool().setConnection(connection);
            } catch (SQLException ex1) {
                ConnectionPollException exep = new ConnectionPollException(ex1.getMessage());
                exep.setPropertyMessage("connection.pool.exception");
                throw exep;
            }
        return type;
    }

    /**
     * This method is used to find all typenumbers
     * @return list of typenummbers
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    @Override
    public List<Entity> read() throws DAOExeption, ConnectionPollException {
        List<Entity> types = new ArrayList<Entity>();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_GET_TYPEROOM);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                TypeOfRoom type = new TypeOfRoom();
                type.setType(resultSet.getString("typeNumber"));
                type.setDescription(resultSet.getString("description"));
                type.setId(resultSet.getInt("idTypeNumber"));
                types.add(type);
            }
        } catch (SQLException ex) {
            try {
                ConnectionPool.getConnectionPool().setConnection(connection);
            } catch (SQLException ex1) {
                ConnectionPollException exep = new ConnectionPollException(ex1.getMessage());
                exep.setPropertyMessage("connection.pool.exception");
                throw exep;
            }
            DAOExeption dao = new DAOExeption (ex.getMessage());
            dao.setPropertyMessage("dao.sqlexception");
            throw dao;
        }finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    DAOExeption dao = new DAOExeption(ex.getMessage());
                    dao.setPropertyMessage("dao.sqlexception");
                    throw dao;
                }
            } else {
                DAOExeption dao = new DAOExeption("");
                dao.setPropertyMessage("dao.statement");
                throw dao;
            }
        }
        try {
                ConnectionPool.getConnectionPool().setConnection(connection);
            } catch (SQLException ex1) {
                ConnectionPollException exep = new ConnectionPollException(ex1.getMessage());
                exep.setPropertyMessage("connection.pool.exception");
                throw exep;
            }
        return types;
    }

    /**
     * This method is used to create object 
     * @param obj  - this object
     * @return result of create
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    @Override
    public int create(Entity obj) throws DAOExeption, ConnectionPollException {
        PreparedStatement ps = null;
        int count = 0;
        try {
            ps = connection.prepareStatement(SQL_INSERT_TYPEROOM);
            ps.setString(1, ((TypeOfRoom)obj).getType());
            ps.setString(2, ((TypeOfRoom)obj).getDescription());
            count = ps.executeUpdate();
        } catch (SQLException ex) {
            try {
                ConnectionPool.getConnectionPool().setConnection(connection);
            } catch (SQLException ex1) {
                ConnectionPollException exep = new ConnectionPollException(ex1.getMessage());
                exep.setPropertyMessage("connection.pool.exception");
                throw exep;
            }
            DAOExeption dao = new DAOExeption (ex.getMessage());
            dao.setPropertyMessage("dao.sqlexception");
            throw dao;
        }
        finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    DAOExeption dao = new DAOExeption(ex.getMessage());
                    dao.setPropertyMessage("dao.sqlexception");
                    throw dao;
                }
            } else {
                DAOExeption dao = new DAOExeption("");
                dao.setPropertyMessage("dao.statement");
                throw dao;
            }
        }
        try {
                ConnectionPool.getConnectionPool().setConnection(connection);
            } catch (SQLException ex1) {
                ConnectionPollException exep = new ConnectionPollException(ex1.getMessage());
                exep.setPropertyMessage("connection.pool.exception");
                throw exep;
            }
        return count;
    }

    /**
     * This method is used to delete record from database
     * @param obj - this object
     * @return result delete
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    @Override
    public int delete(Entity obj) throws DAOExeption, ConnectionPollException {
        PreparedStatement ps = null;
        int count = 0;
        try {
            ps = connection.prepareStatement(SQL_DELETE_TYPEROOM);
            ps.setInt(1, ((TypeOfRoom)obj).getId());
            count = ps.executeUpdate();
        } catch (SQLException ex) {
            try {
                ConnectionPool.getConnectionPool().setConnection(connection);
            } catch (SQLException ex1) {
                ConnectionPollException exep = new ConnectionPollException(ex1.getMessage());
                exep.setPropertyMessage("connection.pool.exception");
                throw exep;
            }
            DAOExeption dao = new DAOExeption (ex.getMessage());
            dao.setPropertyMessage("dao.sqlexception");
            throw dao;
        }
        finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    DAOExeption dao = new DAOExeption(ex.getMessage());
                    dao.setPropertyMessage("dao.sqlexception");
                    throw dao;
                }
            } else {
                DAOExeption dao = new DAOExeption("");
                dao.setPropertyMessage("dao.statement");
                throw dao;
            }
        }
        try {
                ConnectionPool.getConnectionPool().setConnection(connection);
            } catch (SQLException ex1) {
                ConnectionPollException exep = new ConnectionPollException(ex1.getMessage());
                exep.setPropertyMessage("connection.pool.exception");
                throw exep;
            }
        return count;
    }

    /**
     * This method is used to update record in database
     * @param obj - object for update
     * @return result of update
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    @Override
    public int update(Entity obj) throws DAOExeption, ConnectionPollException {
        int count =0;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_UPDATE_TYPEROOM);
            ps.setString(1, ((TypeOfRoom)obj).getType());
            ps.setString(2, ((TypeOfRoom)obj).getDescription());
            ps.setInt(3, ((TypeOfRoom)obj).getId());
            count  = ps.executeUpdate();
        } catch (SQLException ex) {
            try {
                ConnectionPool.getConnectionPool().setConnection(connection);
            } catch (SQLException ex1) {
                ConnectionPollException exep = new ConnectionPollException(ex1.getMessage());
                exep.setPropertyMessage("connection.pool.exception");
                throw exep;
            }
            DAOExeption dao = new DAOExeption (ex.getMessage());
            dao.setPropertyMessage("dao.sqlexception");
            throw dao;
        }
        finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    DAOExeption dao = new DAOExeption(ex.getMessage());
                    dao.setPropertyMessage("dao.sqlexception");
                    throw dao;
                }
            } else {
                DAOExeption dao = new DAOExeption("");
                dao.setPropertyMessage("dao.statement");
                throw dao;
            }
        }
        try {
                ConnectionPool.getConnectionPool().setConnection(connection);
            } catch (SQLException ex1) {
                ConnectionPollException exep = new ConnectionPollException(ex1.getMessage());
                exep.setPropertyMessage("connection.pool.exception");
                throw exep;
            }
        return count;
    }
    
}
