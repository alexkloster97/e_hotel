/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.hotel.dao;

import by.bsuir.hotel.connection.ConnectionPool;
import by.bsuir.hotel.entity.Application;
import by.bsuir.hotel.entity.Entity;
import by.bsuir.hotel.entity.TypeOfRoom;
import by.bsuir.hotel.entity.User;
import by.bsuir.hotel.exeption.ConnectionPollException;
import by.bsuir.hotel.exeption.DAOExeption;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *  This class is used to organize access to application table in database
 * @author Maria
 */
public class ApplicationDAO extends AbstractDAO implements ApplicationInter {
    
    /**
     * This parameter  is used to find application by idApplication
     */
    public static final String GET_APPLICATION_BY_ID = "SELECT idApplication, dateFrom, dateTo , moneyMAX, status, idUpdate,"
            + " idUser, typenumber.idTypeNumber, typenumber.typeNumber "
            + "FROM application "
            + "INNER JOIN typenumber "
            + "ON application.idTypeNumber = typenumber.idTypeNumber "
            + " WHERE application.idApplication=?";
    /**
     * This parameter  is used to find application by idUser
     */
    public static final String GET_APPLICATION_BY_USERID = "SELECT idApplication, dateFrom, dateTo , moneyMAX, status, idUpdate,"
            + " idUser, typenumber.idTypeNumber, typenumber.typeNumber "
            + "FROM application "
            + "INNER JOIN typenumber "
            + "ON application.idTypeNumber = typenumber.idTypeNumber "
            + " WHERE application.idUser=?";
    /**
     * This parameter  is used to find all applications 
     */
    public static final String GET_ALL_APPLICATION = "SELECT idApplication, dateFrom, dateTo , moneyMAX, status, idUpdate,"
            + " user.idUser, user.Name, user.Surname, idTypeNumber "
            + "FROM application "
            + "INNER JOIN user "
            + "ON application.idUser = user.idUser ";
    /**
     * This parameter  is used to update application
     */
    public static final String UPDATE_APPLICATION = "UPDATE application "
            + "SET dateFrom=?, dateTo=? , moneyMAX=?, status=?, idUpdate=? "
            + "WHERE idApplication=? ";
    /**
     * This parameter  is used to update application by status
     */
    public static final String UPDATE_STATUS_APPLICATION = "UPDATE application "
            + "SET status=?, idUpdate=? "
            + "WHERE idApplication=? ";
    /**
     * This parameter  is used to delete application 
     */
    public static final String DELETE_APPLICATION = "DELETE FROM application WHERE idApplication=? ";
    /**
     *This parameter  is used to create application 
     */
    public static final String CREATE_APPLICATION = "INSERT INTO application "
            + "(idApplication, dateFrom, dateTo, moneyMAX, status, idTypeNumber, idUpdate, idUser)"
            + " VALUES(?,?,?,?,?,?,?,?)";
    /**
     * This parameter  is used to find application by status
     */
    public static final String GET_APPLICATION_BY_STATUS = "SELECT idApplication, status, dateFrom, dateTo, "
            + " user.idUser, user.Name, user.Surname, user.Login "
            + "FROM application "
            + "INNER JOIN user "
            + "ON application.idUser = user.idUser "
            + "WHERE application.status= ?";
    
    /**
     *  Constructor without parameters
     */
    public ApplicationDAO() {
        super();
    }
    
    /**
     * This method is used to find application by id
     * @param id - number of application
     * @return object
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    @Override
    public Entity getByID(int id) throws DAOExeption, ConnectionPollException {
        Application application = new Application();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(GET_APPLICATION_BY_ID);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            TypeOfRoom type = new TypeOfRoom();
            User user = new User();
            type.setId(resultSet.getInt("idTypeNumber"));
            type.setType(resultSet.getString("typeNumber"));
            user.setId(resultSet.getInt("idUser"));
            application.setDateFrom(resultSet.getDate("dateFrom"));
            application.setDateTo(resultSet.getDate("dateTo"));
            application.setId(resultSet.getInt("idApplication"));
            application.setStatus(resultSet.getString("status"));
            application.setIdUpdate(resultSet.getInt("idUpdate"));
            application.setMoneyMax(resultSet.getInt("moneyMAX"));
            application.setType(type);
            application.setUser(user);
            
            
        } catch (SQLException ex) {
            try {
                ConnectionPool.getConnectionPool().setConnection(connection);
            } catch (SQLException ex1) {
                ConnectionPollException exep = new ConnectionPollException(ex1.getMessage());
                exep.setPropertyMessage("connection.pool.exception");
                throw exep;
            }
            DAOExeption dao = new DAOExeption(ex.getMessage());
            dao.setPropertyMessage("dao.sqlexception");
            throw dao;
        } finally {
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
        return application;
    }
    
    /**
     * This method is used to read information
     * @return list of application
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    @Override
    public List<Entity> read() throws DAOExeption, ConnectionPollException {
        List<Entity> applications = new ArrayList<Entity>();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(GET_ALL_APPLICATION);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Application application = new Application();
                TypeOfRoom type = new TypeOfRoom();
                User user = new User();
                type.setId(resultSet.getInt("idTypeNumber"));
                user.setId(resultSet.getInt("idUser"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                application.setDateFrom(resultSet.getDate("dateFrom"));
                application.setDateTo(resultSet.getDate("dateTo"));
                application.setId(resultSet.getInt("idApplication"));
                application.setStatus(resultSet.getString("status"));
                application.setIdUpdate(resultSet.getInt("idUpdate"));
                application.setMoneyMax(resultSet.getInt("moneyMAX"));
                application.setType(type);
                application.setUser(user);
                applications.add(application);
            }
        } catch (SQLException ex) {
            try {
                ConnectionPool.getConnectionPool().setConnection(connection);
            } catch (SQLException ex1) {
                ConnectionPollException exep = new ConnectionPollException(ex1.getMessage());
                exep.setPropertyMessage("connection.pool.exception");
                throw exep;
            }
            DAOExeption dao = new DAOExeption(ex.getMessage());
            dao.setPropertyMessage("dao.sqlexception");
            throw dao;
        } finally {
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
        return applications;
    }
    
    /**
     * This method is used to create application
     * @param obj -  object that should be created
     * @return result of create
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    @Override
    public int create(Entity obj) throws DAOExeption, ConnectionPollException {
        PreparedStatement ps = null;
        int count = 0;
        try {
            ps = connection.prepareStatement(CREATE_APPLICATION);
            ps.setInt(1, ((Application) obj).getId());
            ps.setDate(2, ((Application) obj).getDateFrom());
            ps.setDate(3, ((Application) obj).getDateTo());
            ps.setInt(4, ((Application) obj).getMoneyMax());
            ps.setString(5, ((Application) obj).getStatus());
            ps.setInt(6, ((Application) obj).getType().getId());
            ps.setInt(7, ((Application) obj).getIdUpdate());
            ps.setInt(8, ((Application) obj).getUser().getId());
            count = ps.executeUpdate();
        } catch (SQLException ex) {
            try {
                ConnectionPool.getConnectionPool().setConnection(connection);
            } catch (SQLException ex1) {
                ConnectionPollException exep = new ConnectionPollException(ex1.getMessage());
                exep.setPropertyMessage("connection.pool.exception");
                throw exep;
            }
            DAOExeption dao = new DAOExeption(ex.getMessage());
            dao.setPropertyMessage("dao.sqlexception");
            throw dao;
        } finally {
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
     * This method is used to delete application
     * @param obj - object that should be deleted 
     * @return  result of delete
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    @Override
    public int delete(Entity obj) throws DAOExeption, ConnectionPollException {
        PreparedStatement ps = null;
        int count = 0;
        try {
            ps = connection.prepareStatement(DELETE_APPLICATION);
            ps.setInt(1, ((Application) obj).getId());
            count = ps.executeUpdate();
        } catch (SQLException ex) {
            try {
                ConnectionPool.getConnectionPool().setConnection(connection);
            } catch (SQLException ex1) {
                ConnectionPollException exep = new ConnectionPollException(ex1.getMessage());
                exep.setPropertyMessage("connection.pool.exception");
                throw exep;
            }
            DAOExeption dao = new DAOExeption(ex.getMessage());
            dao.setPropertyMessage("dao.sqlexception");
            throw dao;
        } finally {
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
     * This method is used to update application
     * @param obj  - object that should be updated 
     * @return result of update
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    @Override
    public int update(Entity obj) throws DAOExeption, ConnectionPollException {
        PreparedStatement ps = null;
        int count = 0;
        try {
            ps = connection.prepareStatement(UPDATE_APPLICATION);
            ps.setDate(1, ((Application) obj).getDateFrom());
            ps.setDate(2, ((Application) obj).getDateTo());
            ps.setInt(3, ((Application) obj).getMoneyMax());
            ps.setString(4, ((Application) obj).getStatus());
            ps.setInt(5, ((Application) obj).getIdUpdate());
            ps.setInt(6, ((Application) obj).getId());
            count = ps.executeUpdate();
        } catch (SQLException ex) {
            try {
                ConnectionPool.getConnectionPool().setConnection(connection);
            } catch (SQLException ex1) {
                ConnectionPollException exep = new ConnectionPollException(ex1.getMessage());
                exep.setPropertyMessage("connection.pool.exception");
                throw exep;
            }
            DAOExeption dao = new DAOExeption(ex.getMessage());
            dao.setPropertyMessage("dao.sqlexception");
            throw dao;
        } finally {
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
     * This method is used to update application
     * @param idApplication - number of application
     * @param idUpdate - whom this application would be uptated
     * @param status - status of application
     * @return result of update
     * @throws ConnectionPollException
     * @throws DAOExeption
     */
    @Override
    public int updateStatus(int idApplication, int idUpdate, String status) throws ConnectionPollException, DAOExeption {
        PreparedStatement ps = null;
        int count = 0;
        try {
            ps = connection.prepareStatement(UPDATE_STATUS_APPLICATION);
            ps.setString(1, status);
            ps.setInt(2, idUpdate);
            ps.setInt(3, idApplication);
            count = ps.executeUpdate();
        } catch (SQLException ex) {
            try {
                ConnectionPool.getConnectionPool().setConnection(connection);
            } catch (SQLException ex1) {
                ConnectionPollException exep = new ConnectionPollException(ex1.getMessage());
                exep.setPropertyMessage("connection.pool.exception");
                throw exep;
            }
            DAOExeption dao = new DAOExeption(ex.getMessage());
            dao.setPropertyMessage("dao.sqlexception");
            throw dao;
        } finally {
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
     * This method is used to find application by idUser 
     * @param idUser -  number of the user
     * @return list of the application
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    @Override
    public List<Application> findApplicationByIdUser(int idUser) throws DAOExeption, ConnectionPollException {
        List<Application> applications = new ArrayList<Application>();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(GET_APPLICATION_BY_USERID);
            ps.setInt(1, idUser);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Application app = new Application();
                app.setId(resultSet.getInt("idApplication"));
                app.setDateFrom(resultSet.getDate("dateFrom"));
                app.setDateTo(resultSet.getDate("dateTo"));
                app.setMoneyMax(resultSet.getInt("moneyMAX"));
                app.setStatus(resultSet.getString("status"));
                app.setIdUpdate(resultSet.getInt("idUpdate"));
                User user = new User();
                TypeOfRoom type = new TypeOfRoom();
                user.setId(resultSet.getInt("idUser"));
                app.setUser(user);
                type.setId(resultSet.getInt("idTypeNumber"));
                type.setType(resultSet.getString("typeNumber"));
                app.setType(type);
                applications.add(app);
            }
            
        } catch (SQLException ex) {
            try {
                ConnectionPool.getConnectionPool().setConnection(connection);
            } catch (SQLException ex1) {
                ConnectionPollException exep = new ConnectionPollException(ex1.getMessage());
                exep.setPropertyMessage("connection.pool.exception");
                throw exep;
            }
            DAOExeption dao = new DAOExeption(ex.getMessage());
            dao.setPropertyMessage("dao.sqlexception");
            throw dao;
        } finally {
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
        return applications;
    }
    
    /**
     * This method is used to find application by status of application
     * @param status - information about application
     * @return list of the applications
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    @Override
    public List<Application> findApplicationsByStatus(String status) throws DAOExeption, ConnectionPollException {
        List<Application> list = new ArrayList<Application>();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(GET_APPLICATION_BY_STATUS);
            ps.setString(1, status);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Application application = new Application();
                User user = new User();
                user.setId(resultSet.getInt("idUser"));
                user.setSurname(resultSet.getString("surname"));
                user.setName(resultSet.getString("name"));
                user.setLogin(resultSet.getString("login"));
                application.setId(resultSet.getInt("idApplication"));
                application.setStatus(resultSet.getString("status"));
                application.setDateFrom(resultSet.getDate("dateFrom"));
                application.setDateTo(resultSet.getDate("dateTo"));
                application.setUser(user);
                list.add(application);
            }
            
        } catch (SQLException ex) {
            try {
                ConnectionPool.getConnectionPool().setConnection(connection);
            } catch (SQLException ex1) {
                ConnectionPollException exep = new ConnectionPollException(ex1.getMessage());
                exep.setPropertyMessage("connection.pool.exception");
                throw exep;
            }
            DAOExeption dao = new DAOExeption(ex.getMessage());
            dao.setPropertyMessage("dao.sqlexception");
            throw dao;
        } finally {
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
        
        return list;
    }
}
