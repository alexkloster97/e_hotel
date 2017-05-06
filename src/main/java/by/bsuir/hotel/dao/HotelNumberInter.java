/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.hotel.dao;

import by.bsuir.hotel.entity.HotelNumber;
import by.bsuir.hotel.exeption.ConnectionPollException;
import by.bsuir.hotel.exeption.DAOExeption;
import java.util.List;

/**
 * This interface is used to organize access to hotelnumber table in database
 * @author Maria
 */
public interface HotelNumberInter {
    /**
     * This method is used to find hotelnumber by type of room
     * @param type - type of room
     * @return list of hotelnumbers
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    public List<HotelNumber> getByTypeRoom(String type) throws DAOExeption, ConnectionPollException;
    /**
     * This method is used to find hotelnumber by type of room and price
     * @param type - type of room
     * @param price - price 
     * @return list of hotelnumbers
     * @throws DAOExeption
     * @throws ConnectionPollException
     */
    public List<HotelNumber> getByTypeRoomAndPrice(int type, int price) throws DAOExeption, ConnectionPollException;
}
