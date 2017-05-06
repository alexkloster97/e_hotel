/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.hotel.dao;

import by.bsuir.hotel.connection.ConnectionPool;
import by.bsuir.hotel.entity.Entity;
import by.bsuir.hotel.entity.HotelNumber;
import by.bsuir.hotel.entity.TypeOfRoom;
import by.bsuir.hotel.exeption.ConnectionPollException;
import by.bsuir.hotel.exeption.DAOExeption;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * This class is used to organize access to hotelnumber table in database
 * @author Maria
 */
public class HotelNumberDAO extends AbstractDAO implements HotelNumberInter{
    
    /**
     *  This parameter  is used to find hotelnumber by idBill
     */
    public final static String SQL_SELECT_NUMBER_BY_ID="SELECT idHotelNumber, pricePerDay, typenumber.typeNumber , typenumber.description,typenumber.idTypeNumber, classNumber "
            + "FROM hotelnumber "
            + "INNER JOIN typenumber "
            + "ON hotelnumber.idTypeNumber = typenumber.idTypeNumber "
            + " WHERE hotelnumber.idHotelNumber=?";
    /**
     *  This parameter  is used to find hotelnumber by type
     */
    public final static String SQL_SELECT_NUMBERS_BY_TYPE="SELECT idHotelNumber, pricePerDay, typenumber.typeNumber , typenumber.description, classNumber "
            + "FROM hotelnumber "
            + "INNER JOIN typenumber "
            + "ON hotelnumber.idTypeNumber = typenumber.idTypeNumber "
            + " WHERE typenumber.typeNumber=?";
    /**
     *  This parameter  is used to find hotelnumber by type and price
     */
    public final static String SQL_SELECT_NUMBERS_BY_TYPE_AND_PRICE="SELECT idHotelNumber, pricePerDay, typenumber.typeNumber , typenumber.description, classNumber "
            + "FROM hotelnumber "
            + "INNER JOIN typenumber "
            + "ON hotelnumber.idTypeNumber = typenumber.idTypeNumber "
            + " WHERE typenumber.idTypeNumber= ? AND hotelnumber.pricePerDay<= ? "
            + "ORDER BY pricePerDay ASC ";
    /**
     *  This parameter  is used to update hotelnumber by id
     */
    public static final String SQL_EDIT_NUMBER_BY_ID="UPDATE hotelnumber "
            + "SET pricePerDay=?, idTypeNumber=?,  classNumber=? "
            + "WHERE hotelnumber.idHotelNumber =?";
    /**
     *  This parameter  is used to delete hotelnumber by id
     */
    public static final String SQL_DELETE_NUMBER="DELETE FROM hotelnumber WHERE hotelnumber.idHotelNumber = ?";
    /**
     *  This parameter  is used to find all hotelnumbers 
     */
    public static final String SQL_SELECT_NUMBERS="SELECT hotelnumber.pricePerDay, hotelnumber.idHotelNumber, typenumber.idTypeNumber, typenumber.typeNumber , typenumber.description, hotelnumber.classNumber "
            + "FROM hotelnumber "
            + "INNER JOIN typenumber "
            + "ON hotelnumber.idTypeNumber = typenumber.idTypeNumber";
    /**
     *  This parameter  is used to create hotelnumber
     */
    public static final String SQL_INSERT_NUMBER = "INSERT INTO hotelnumber "
            + "(idHotelNumber, pricePerDay, classNumber, idTypeNumber) " 
            + "VALUES (?,?,?,?) ";
        

    /**
     * Constructor without parameters
     */
    public HotelNumberDAO() {
        super();
    }
    
    /**
     * This method is used to find all hotelnumbers
     * @return list of entities
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    @Override
    public List<Entity> read() throws DAOExeption, ConnectionPollException {
        List<Entity> numbers = new ArrayList<Entity>();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_SELECT_NUMBERS);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                HotelNumber number = new HotelNumber();
                TypeOfRoom type = new TypeOfRoom();
                type.setType(resultSet.getString("typeNumber"));
                type.setId(resultSet.getInt("idTypeNumber"));
                type.setDescription(resultSet.getString("description"));
                number.setPricePerDay(resultSet.getInt("pricePerDay"));
                number.setId(resultSet.getInt("idHotelNumber"));
                number.setType(type);
                number.setClassOfRoom(resultSet.getString("classNumber"));
                numbers.add(number);
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
        return numbers;
    }

    /**
     * This method is used to create  hotelnumber
     * @param obj - object that should be created 
     * @return result of create
     * @throws ConnectionPollException
     * @throws DAOExeption
     */
    @Override
    public int create(Entity obj) throws ConnectionPollException, DAOExeption {
        PreparedStatement ps = null;
        int count = 0;
        try {
            ps = connection.prepareStatement(SQL_INSERT_NUMBER);
            ps.setInt(1, ((HotelNumber)obj).getId());
            ps.setInt(2, ((HotelNumber)obj).getPricePerDay());
            ps.setString(3, ((HotelNumber)obj).getClassOfRoom());
            ps.setInt(4, ((HotelNumber)obj).getType().getId());
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
     * This method is used to delete record from database
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
            ps = connection.prepareStatement(SQL_DELETE_NUMBER);
            ps.setInt(1, ((HotelNumber)obj).getId());
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
     * This method is used to update record in the database
     * @param obj - object that should be deleted
     * @return result of update
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    @Override
    public int update(Entity obj) throws DAOExeption, ConnectionPollException {
        int count =0;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_EDIT_NUMBER_BY_ID);
            ps.setInt(1, ((HotelNumber)obj).getPricePerDay());
            ps.setInt(2, ((HotelNumber)obj).getType().getId());
            ps.setString(3, ((HotelNumber)obj).getClassOfRoom());
            ps.setInt(4, ((HotelNumber)obj).getId());
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
     * This method is used to find hotelnumber by id 
     * @param id - number of hotelnumber
     * @return object
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    @Override
    public Entity getByID(int id) throws DAOExeption, ConnectionPollException {
        HotelNumber number = new HotelNumber();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_SELECT_NUMBER_BY_ID);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            TypeOfRoom type = new TypeOfRoom();
            type.setType(resultSet.getString("typeNumber"));
            type.setId(resultSet.getInt("idTypeNumber"));
            type.setDescription(resultSet.getString("description"));
            number.setPricePerDay(resultSet.getInt("pricePerDay"));
            number.setId(resultSet.getInt("idHotelNumber"));
            number.setType(type);
            number.setClassOfRoom(resultSet.getString("classNumber"));

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
        return number;
    }

    

    /**
     * This method is used to find hotelnumber by type of room
     * @param type - type of room
     * @return list of hotelnumbers
     * @throws ConnectionPollException
     * @throws DAOExeption
     */
    @Override
    public List<HotelNumber> getByTypeRoom(String type) throws ConnectionPollException, DAOExeption {
        List<HotelNumber> numbers = new ArrayList<HotelNumber>();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_SELECT_NUMBERS_BY_TYPE);
            ps.setString(1, type);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                TypeOfRoom typeRoom = new TypeOfRoom();
                HotelNumber number = new HotelNumber();
                typeRoom.setType(resultSet.getString("typeNumber"));
                typeRoom.setDescription(resultSet.getString("description"));
                number.setId(resultSet.getInt("idHotelNumber"));
                number.setPricePerDay(resultSet.getInt("pricePerDay"));
                number.setType(typeRoom);
                number.setClassOfRoom(resultSet.getString("classNumber"));
                numbers.add(number);
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
        return numbers;
    }

    /**
     * This method is used to find hotelnumber by type of room and price
     * @param type - type of room
     * @param price - price 
     * @return list of hotelnumbers
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    @Override
    public List<HotelNumber> getByTypeRoomAndPrice(int type, int price) throws DAOExeption, ConnectionPollException {
        List<HotelNumber> numbers = new ArrayList<HotelNumber>();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_SELECT_NUMBERS_BY_TYPE_AND_PRICE);
            ps.setInt(1, type);
            ps.setInt(2, price);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                TypeOfRoom typeRoom = new TypeOfRoom();
                HotelNumber number = new HotelNumber();
                typeRoom.setType(resultSet.getString("typeNumber"));
                typeRoom.setDescription(resultSet.getString("description"));
                number.setId(resultSet.getInt("idHotelNumber"));
                number.setPricePerDay(resultSet.getInt("pricePerDay"));
                number.setType(typeRoom);
                number.setClassOfRoom(resultSet.getString("classNumber"));
                numbers.add(number);
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
        return numbers;
    }

}
