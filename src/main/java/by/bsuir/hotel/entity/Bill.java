/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.hotel.entity;

/**
 * This class contains information about bill
 * @author Maria
 */
public class Bill extends Entity{
    private HotelNumber number;
    private User user;
    private int price;
    private Application application;
    


    /**
     * Constructor without parameters
     */
    public Bill() {
    }

    /**
     * Constructor with parameters
     * @param number
     * @param user
     * @param price
     * @param application
     * @param id
     */
    public Bill(HotelNumber number, User user, int price, Application application, int id) {
        super(id);
        this.number = number;
        this.user = user;
        this.price = price;
        this.application = application;
    }

    /**
     * This method is used to return hotelroom number
     * @return number of hotelroom
     */
    public HotelNumber getNumber() {
        return number;
    }

    /**
     * This method is used to set number of hotelroom
     * @param number - number of hotelroom
     */
    public void setNumber(HotelNumber number) {
        this.number = number;
    }

    /**
     * This method is used to return user
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * This method is used to set user
     * @param user - user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * This method is used to return price 
     * @return price
     */
    public int getPrice() {
        return price;
    }

    /**
     * This method is used to set price
     * @param price - price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * This method is used to return application
     * @return application
     */
    public Application getApplication() {
        return application;
    }

    /**
     * This method is used to set application
     * @param application - application
     */
    public void setApplication(Application application) {
        this.application = application;
    }

    
    
    
    
    
    
    
}
