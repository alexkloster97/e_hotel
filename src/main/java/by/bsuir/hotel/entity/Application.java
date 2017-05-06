/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.hotel.entity;

import java.sql.Date;

/**
 * This class contains information about application
 * @author Maria
 */
public class Application extends Entity{
    private TypeOfRoom type;
    private User user;
    private Date dateFrom;
    private Date dateTo;
    private int moneyMax;
    private String status;
    private int idUpdate;

    /**
     * Constructor without parameters
     */
    public Application() {
    }

    /**
     * Constructor with parameters
     * @param type
     * @param user
     * @param dateFrom
     * @param dateTo
     * @param moneyMax
     * @param status
     * @param idUpdate
     * @param id
     */
    public Application(TypeOfRoom type, User user, Date dateFrom, Date dateTo, int moneyMax, String status, int idUpdate, int id) {
        super(id);
        this.type = type;
        this.user = user;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.moneyMax = moneyMax;
        this.status = status;
        this.idUpdate = idUpdate;
    }

    /**
     * This method is used to return type of room
     * @return type of room
     */
    public TypeOfRoom getType() {
        return type;
    }

    /**
     *  This method is used to set type of room
     * @param type - type of room
     */
    public void setType(TypeOfRoom type) {
        this.type = type;
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
     * This method is used to return date from
     * @return date from
     */
    public Date getDateFrom() {
        return dateFrom;
    }

    /**
     * This method is used to set date from
     * @param dateFrom - date from
     */
    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    /**
     * This method is used to return date to
     * @return date to
     */
    public Date getDateTo() {
        return dateTo;
    }

    /**
     * This method is used to set date to
     * @param dateTo - date to
     */
    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    /**
     * This method is used to return max price per day
     * @return max price per day
     */
    public int getMoneyMax() {
        return moneyMax;
    }

    /**
     * This method is used to set max price per day
     * @param moneyMax - max price per day
     */
    public void setMoneyMax(int moneyMax) {
        this.moneyMax = moneyMax;
    }

    /**
     * This method is used to return status of application
     * @return status of application
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method is used to set status of application
     * @param status - status of application
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method is used to return whom this application is updated
     * @return whom this application is updated
     */
    public int getIdUpdate() {
        return idUpdate;
    }

    /**
     * This method is used to set id user who update this application
     * @param idUpdate - id user who upadate this application
     */
    public void setIdUpdate(int idUpdate) {
        this.idUpdate = idUpdate;
    }
    
}
