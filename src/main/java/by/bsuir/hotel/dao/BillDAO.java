/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.hotel.dao;

import by.bsuir.hotel.connection.ConnectionPool;
import by.bsuir.hotel.entity.Application;
import by.bsuir.hotel.entity.Bill;
import by.bsuir.hotel.entity.Entity;
import by.bsuir.hotel.entity.HotelNumber;
import by.bsuir.hotel.entity.TypeOfRoom;
import by.bsuir.hotel.exeption.ConnectionPollException;
import by.bsuir.hotel.exeption.DAOExeption;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * This class is used to organize access to bill table in database
 * @author Maria
 */
public class BillDAO extends AbstractDAO implements BillInter{
    
    /**
     *  This parameter  is used to find bill by idBill
     */
    public static final String GET_BILL_BY_ID="";
   
    /**
     *  This parameter  is used to read bill 
     */
    public static final String READ_BILLS_FROM_APPLICATION ="";
    /**
     *  This parameter  is used to create bill 
     */
    public static final String CREATE_BILL="INSERT INTO bill "
            + "(idHotelNumber, price, idApplication, idUser) "
            + " VALUES(?,?,?,?)";
    /**
     *  This parameter  is used to delete bill 
     */
    public static final String DELETE_BILL="DELETE FROM bill WHERE idBill=?";
    /**
     *  This parameter  is used to update bill
     */
    public static final String UPDATE_BILL="UPDATE bill "
            + "SET price=? "
            + "WHERE idBill=? ";
    /**
     *  This parameter  is used to find bill by idApplication
     */
    public static final String GET_BILL_BY_ID_APPLICATION = "SELECT idBill, idHotelNumber, price, "
            + "application.idApplication, application.dateFrom, application.idUpdate, application.dateTo, typenumber.idTypeNumber, typenumber.typenumber "
            + "FROM bill "
            + "INNER JOIN application "
            + "ON bill.idApplication = application.idApplication "
            + "INNER JOIN typenumber "
            + "ON application.idTypeNumber = typenumber.idTypeNumber "
            + "WHERE application.idApplication = ?";

    /**
     * This method is used to find bill by id
     * @param id - number of bill
     * @return object
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    @Override
    public Entity getByID(int id) throws DAOExeption, ConnectionPollException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is used to find all bills
     * @return list of bills
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    @Override
    public List<Entity> read() throws DAOExeption, ConnectionPollException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is used to create bill
     * @param obj  - object that should be created
     * @return result of create
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    @Override
    public int create(Entity obj) throws DAOExeption, ConnectionPollException {
        PreparedStatement ps = null;
        int count = 0;
        try {
            ps = connection.prepareStatement(CREATE_BILL);
            ps.setInt(1, ((Bill)obj).getNumber().getId());
            ps.setInt(2, ((Bill)obj).getPrice());
            ps.setInt(3, ((Bill)obj).getApplication().getId());
            ps.setInt(4, ((Bill)obj).getApplication().getUser().getId());
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
        }try {
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
     * @return result of delete
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    @Override
    public int delete(Entity obj) throws DAOExeption, ConnectionPollException {
        PreparedStatement ps = null;
        int count = 0;
        try {
            ps = connection.prepareStatement(DELETE_BILL);
            ps.setInt(1, ((Bill)obj).getId());
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
        }try {
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
     * @param obj - object that should be updated 
     * @return result of update
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    @Override
    public int update(Entity obj) throws DAOExeption, ConnectionPollException {
       PreparedStatement ps = null;
        int count = 0;
        try {
            ps = connection.prepareStatement(UPDATE_BILL);
            ps.setInt(1, ((Bill)obj).getPrice());
            ps.setInt(2, ((Bill)obj).getId());
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
     * This method is used to find bill by id
     * @param idApplication - number of application
     * @return bill
     * @throws ConnectionPollException
     * @throws DAOExeption
     */
    @Override
    public Bill getBillByApplication(int idApplication) throws ConnectionPollException, DAOExeption {
        Bill bill = new Bill();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(GET_BILL_BY_ID_APPLICATION);
            ps.setInt(1, idApplication);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            bill.setId(resultSet.getInt("idBill"));
            bill.setPrice(resultSet.getInt("price"));
            TypeOfRoom type = new TypeOfRoom();
            Application application = new Application();
            HotelNumber number = new HotelNumber();
            number.setId(resultSet.getInt("idHotelNumber"));
            type.setId(resultSet.getInt("idTypeNumber"));
            type.setType(resultSet.getString("typeNumber"));
            number.setType(type);
            application.setDateFrom(resultSet.getDate("dateFrom"));
            application.setDateTo(resultSet.getDate("dateTo"));
            application.setId(resultSet.getInt("idApplication"));
            application.setIdUpdate(resultSet.getInt("idUpdate"));
            application.setType(type);
            bill.setApplication(application);
            bill.setNumber(number);
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
        return bill;
    }
    
}
