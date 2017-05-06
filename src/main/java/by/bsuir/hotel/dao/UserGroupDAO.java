/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.hotel.dao;

import by.bsuir.hotel.connection.ConnectionPool;
import by.bsuir.hotel.entity.Entity;
import by.bsuir.hotel.entity.UserGroup;
import by.bsuir.hotel.exeption.ConnectionPollException;
import by.bsuir.hotel.exeption.DAOExeption;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to organize access to usergroup table in database
 * @author Maria
 */
public class UserGroupDAO extends AbstractDAO{

    /**
     * This parameter  is used to find usergroup by id
     */
    public final static String SQL_GET_USERGROUP_BY_ID="SELECT usergroup.typeGroup "
            + "FROM usergroup "
            + "WHERE idGroup=?";
    /**
     * This parameter  is used to find all usergroups
     */
    public final static String SQL_GET_USERGROUP = "SELECT idGroup,typeGroup FROM usergroup";
    /**
     * This parameter  is used to update usergroup
     */
    public final static String SQL_UPDATE_USERGROUP = "UPDATE usergroup SET usergroup.typeGroup= ? WHERE usergroup.idGroup= ?";
    /**
     * This parameter  is used to create usergroup
     */
    public final static String SQL_INSERT_TYPEGROUP = "INSERT INTO usergroup (typeGroup) VALUES(?)";
    /**
     * This parameter  is used to delete usergroup
     */
    public final static String SQL_DELETE_TYPEGROUP = "DELETE FROM usergroup WHERE idGroup= ?";

    /**
     * Constructor without parameters
     */
    public UserGroupDAO() {
        super();
    }

    /**
     *  This method is used to find all usergroups
     * @return list of usergroup
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    @Override
    public List<Entity> read() throws DAOExeption, ConnectionPollException {
        List<Entity> groups = new ArrayList<Entity>();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_GET_USERGROUP);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                UserGroup group = new UserGroup();
                group.setGroup(resultSet.getString("typeGroup"));
                group.setId(resultSet.getInt("idGroup"));
                groups.add(group);
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
        return groups;
    }

    /**
     * This method is used to create new usergroup
     * @param obj - this usergroup
     * @return result of create
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    @Override
    public int create(Entity obj) throws DAOExeption, ConnectionPollException {
        PreparedStatement ps = null;
        int count = 0;
        try {
            ps = connection.prepareStatement(SQL_INSERT_TYPEGROUP);
            ps.setString(1, ((UserGroup)obj).getGroup());
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
     * This method is used to delete  usergroup
     * @param obj - this usergroup
     * @return result of delete
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    @Override
    public int delete(Entity obj) throws DAOExeption, ConnectionPollException {
        PreparedStatement ps = null;
        int count = 0;
        try {
            ps = connection.prepareStatement(SQL_DELETE_TYPEGROUP);
            ps.setInt(1, ((UserGroup)obj).getId());
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
     * This method is used to update  usergroup
     * @param obj - this usergroup
     * @return result of update
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    @Override
    public int update(Entity obj) throws DAOExeption, ConnectionPollException {
        int count =0;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_UPDATE_USERGROUP);
            ps.setString(1, ((UserGroup)obj).getGroup());
            ps.setInt(2, ((UserGroup)obj).getId());
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

    /**
     * This method is used to find  usergroup by id
     * @param id - number of usergroup
     * @return usergroup
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    @Override
    public Entity getByID(int id) throws DAOExeption, ConnectionPollException {
        UserGroup group = new UserGroup();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_GET_USERGROUP_BY_ID);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            group.setGroup(resultSet.getString("typeGroup"));
            group.setId(id);
            
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
        return group;
    }
    
}
