/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.hotel.dao;

import by.bsuir.hotel.connection.ConnectionPool;
import by.bsuir.hotel.entity.Entity;
import by.bsuir.hotel.entity.User;
import by.bsuir.hotel.entity.UserGroup;
import by.bsuir.hotel.exeption.ConnectionPollException;
import by.bsuir.hotel.exeption.DAOExeption;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to organize access to user table in database
 * @author Maria
 */
public class UserDAO extends AbstractDAO implements UserInterface{
    
    /**
     * This parameter  is used to find all users
     */
    final static Logger log = Logger.getLogger(UserDAO.class);


    public static final String SQL_GET_ALL_USERS  = "SELECT user.idUser, user.Name, "
            + "user.Surname, user.Birthday, user.SerialNumber, user.PassportNumber,"
            + " user.Valid, usergroup.typeGroup, user.Password"
            + " FROM user INNER JOIN usergroup ON "
            + "user.idGroup=usergroup.idGroup";
    /**
     * This parameter  is used to find admin 
     */
    public static final String SQL_GET_ADMINS  = "SELECT user.idUser, user.Name, "
            + "user.Surname, user.Birthday,user.Login, user.SerialNumber, user.PassportNumber,"
            + " user.Valid, usergroup.typeGroup,usergroup.idGroup, user.Password"
            + " FROM user INNER JOIN usergroup ON "
            + "user.idGroup=usergroup.idGroup "
            + "WHERE user.idGroup= ? AND user.idUser!=?";
    /**
     * This parameter  is used to find user by login
     */
    public static final String SQL_GET_USER_BY_LOGIN  = "SELECT user.idUser, user.Name, "
            + "user.Surname, user.Birthday, user.SerialNumber, user.PassportNumber,"
            + " user.Valid, usergroup.typeGroup, usergroup.idGroup, user.Password"
            + " FROM user INNER JOIN usergroup ON "
            + "user.idGroup=usergroup.idGroup"
            + " WHERE user.Login = ?";
    /**
     * This parameter  is used to create user 
     */
    public static final String SQL_CREATE_USER = "INSERT INTO user "
            + "(Login, Password, Name, Surname, Birthday, SerialNumber, PassportNumber,Valid, idGroup) "
            + "VALUES (?,?,?,?,?,?,?,?,?);";
    /**
     * This parameter  is used to find idUser by user Login
     */
    public static final String SQL_GET_IDUSER_BY_LOGIN ="SELECT user.idUser "
            + "FROM user "
            + "WHERE user.Login = ?";
    /**
     * This parameter  is used to delete user
     */
    public static final String SQL_DELETE_USER_BY_ID = "DELETE FROM user WHERE idUser= ?";
    /**
     * This parameter  is used to update user by idUser
     */
    public static final String SQL_UPDATE_INF_USER = "UPDATE user "
            + "SET Name =?, Surname =?, Birthday =?,"
            + " Login =?, SerialNumber =?, PassportNumber =?, Valid =?, Password =?  WHERE idUser =?";
    /**
     * This parameter  is used to fing user by idUser
     */
    public static final String SQL_GET_USER_BY_ID ="SELECT user.idUser, user.Name, "
            + "user.Surname, user.Birthday,user.Login, user.SerialNumber, user.PassportNumber,"
            + " user.Valid, usergroup.typeGroup,usergroup.idGroup, user.Password"
            + " FROM user INNER JOIN usergroup ON "
            + "user.idGroup=usergroup.idGroup "
            + "WHERE user.idUser=?";
    
    /**
     *  Constructor without parameters
     */
    public UserDAO() {
        super();
    }

    
    /**
     * This method is used to find all users
     * @return list of objects
     */
    @Override
    public List<Entity> read() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is used to find user by id
     * @param id - number of user
     * @return user
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    @Override
    public Entity getByID(int id) throws DAOExeption, ConnectionPollException {
        PreparedStatement ps = null;
        User user = null;
        try {
            ps = connection.prepareStatement(SQL_GET_USER_BY_ID);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
                UserGroup group = new UserGroup();
                user = new User();
                user.setId(resultSet.getInt("idUser"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setPassword(resultSet.getString("password"));
                user.setLogin(resultSet.getString("login"));
                user.setBirthday(resultSet.getDate("birthday"));
                if(resultSet.getString("passportNumber")==null){
                    user.setPassportNumber(0);
                }else{
                    user.setPassportNumber(Integer.parseInt(resultSet.getString("passportNumber")));
                }
                user.setSerialNumber(resultSet.getString("serialNumber"));
                user.setValid(resultSet.getDate("valid"));
                group.setId(resultSet.getInt("idGroup"));
                group.setGroup(resultSet.getString("typeGroup"));
                user.setGroup(group);
            
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
        return user;
    }

    /**
     * This method is used to create user in database
     * @param obj - this user
     * @return result of create
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    @Override
    public int create(Entity obj) throws DAOExeption, ConnectionPollException {
        PreparedStatement ps = null;
        int count = 0;
        try {
            ps = connection.prepareStatement(SQL_CREATE_USER);
            ps.setString(1, ((User)obj).getLogin());
            ps.setString(2, ((User)obj).getPassword());
            ps.setString(3, ((User)obj).getName());
            ps.setString(4, ((User)obj).getSurname());
            if(((User)obj).getBirthday()==null){
                ps.setDate(5, null);
            }else{
                ps.setDate(5, ((User)obj).getBirthday());
            }
            ps.setString(6, ((User)obj).getSerialNumber());
            ps.setString(7, String.valueOf(((User)obj).getPassportNumber()));
            if(((User)obj).getValid()==null){
                  ps.setDate(8, null);
            }else{
                ps.setDate(8, ((User)obj).getValid());
               
            }
            ps.setInt(9, ((User)obj).getGroup().getId());
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
     * This method is used to delete user 
     * @param obj - this user
     * @return result of delete
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    @Override
    public int delete(Entity obj) throws DAOExeption, ConnectionPollException {
        PreparedStatement ps = null;
        int count = 0;
        try {
            ps = connection.prepareStatement(SQL_DELETE_USER_BY_ID);
            ps.setInt(1, ((User)obj).getId());
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
     * This method is used to update information about user
     * @param obj - this user
     * @return result of update
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    @Override
    public int update(Entity obj) throws DAOExeption, ConnectionPollException { 
        PreparedStatement ps = null;
        int count = 0;
        try {
            ps = connection.prepareStatement(SQL_UPDATE_INF_USER);
            ps.setString(1, ((User)obj).getName());
            ps.setString(2, ((User)obj).getSurname());
            ps.setString(4, ((User)obj).getLogin());
            ps.setString(8, ((User)obj).getPassword());
            if(((User)obj).getBirthday()==null){
                ps.setDate(3, null);
            }else{
                ps.setDate(3, ((User)obj).getBirthday());
            }
            ps.setString(5, ((User)obj).getSerialNumber());
            ps.setString(6, String.valueOf(((User)obj).getPassportNumber()));
            if(((User)obj).getValid()==null){
                  ps.setDate(7, null);
            }else{
                ps.setDate(7, ((User)obj).getValid());  
            }
            ps.setInt(9, ((User)obj).getId());
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
     * This method is used to find user by login
     * @param login - login of user
     * @return user
     * @throws ConnectionPollException
     * @throws DAOExeption
     */
    @Override
    public User findByLogin(String login) throws ConnectionPollException, DAOExeption {
        PreparedStatement ps = null;
        User user = null;
        try {
            ps = connection.prepareStatement(SQL_GET_USER_BY_LOGIN);
            ps.setString(1, login);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                UserGroup group = new UserGroup();
                user = new User();
                user.setId(resultSet.getInt("idUser"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setPassword(resultSet.getString("password"));
                user.setLogin(login);
                user.setBirthday(resultSet.getDate("birthday"));
                if(resultSet.getString("passportNumber")==null){
                    user.setPassportNumber(0);
                }else{
                    user.setPassportNumber(Integer.parseInt(resultSet.getString("passportNumber")));
                }
                user.setSerialNumber(resultSet.getString("serialNumber"));
                user.setValid(resultSet.getDate("valid"));
                group.setId(resultSet.getInt("idGroup"));
                group.setGroup(resultSet.getString("typeGroup"));
                user.setGroup(group);
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
        return user;
    }

    /**
     * This method is used to find admin
     * @param idGroup - number of usergroup
     * @param idAdmin -  id of admin
     * @return list of admins
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    @Override
    public List<User> findAdmin(int idGroup, int idAdmin) throws DAOExeption, ConnectionPollException {
        PreparedStatement ps = null;
        List<User> users = new ArrayList<User>();
        try {
            ps = connection.prepareStatement(SQL_GET_ADMINS);
            ps.setInt(1, idGroup);
            ps.setInt(2, idAdmin);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                UserGroup group = new UserGroup();
                User user = new User();
                user.setId(resultSet.getInt("idUser"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setPassword(resultSet.getString("password"));
                user.setLogin(resultSet.getString("login"));
                user.setBirthday(resultSet.getDate("birthday"));
                if(resultSet.getString("passportNumber")==null){
                    user.setPassportNumber(0);
                }else{
                    user.setPassportNumber(Integer.parseInt(resultSet.getString("passportNumber")));
                }
                user.setSerialNumber(resultSet.getString("serialNumber"));
                user.setValid(resultSet.getDate("valid"));
                group.setId(resultSet.getInt("idGroup"));
                group.setGroup(resultSet.getString("typeGroup"));
                user.setGroup(group);
                users.add(user);
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
        return users;
    }
    
}
