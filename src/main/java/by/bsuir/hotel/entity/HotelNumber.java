/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.hotel.entity;

/**
 *  This class contains information about hotelnumber
 * @author Maria
 */
public class HotelNumber extends Entity{
    private int pricePerDay;
    private TypeOfRoom type;
    private String classOfRoom;

    /**
     * Constructor without parameters
     */
    public HotelNumber() {
    }

    /**
     * Constructor with parameters
     * @param pricePerDay
     * @param type
     * @param classOfRoom
     * @param id
     */
    public HotelNumber(int pricePerDay, TypeOfRoom type, String classOfRoom, int id) {
        super(id);
        this.pricePerDay = pricePerDay;
        this.type = type;
        this.classOfRoom = classOfRoom;
    }

    /**
     * This method is used to return price per day
     * @return prica per day
     */
    public int getPricePerDay() {
        return pricePerDay;
    }

    /**
     * This method is used to set price per day
     * @param pricePerDay - price per day
     */
    public void setPricePerDay(int pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    /**
     * This method is used to return type of room
     * @return type of room
     */
    public TypeOfRoom getType() {
        return type;
    }

    /**
     * This method is used to set type of room
     * @param type - type of room
     */
    public void setType(TypeOfRoom type) {
        this.type = type;
    }

    /**
     * This method is used to return class of room
     * @return class of room
     */
    public String getClassOfRoom() {
        return classOfRoom;
    }

    /** 
     * This method is used to set class of room
     * @param classOfRoom - class of room
     */
    public void setClassOfRoom(String classOfRoom) {
        this.classOfRoom = classOfRoom;
    }

    
    
    
}
