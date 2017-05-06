/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.hotel.entity;

import java.sql.Date;


/**
 * This class contains information about user
 * @author Maria
 */
public class User extends Entity{
    private String surname;
    private String name;
    private Date birthday;
    private int passportNumber;
    private String serialNumber;
    private Date valid;
    private String login;
    private String password;
    private UserGroup group;

    /**
     * Constructor without parameters
     */
    public User() {
    }

    /**
     * Constructor with parameters
     * @param surname
     * @param name
     * @param birthday
     * @param passportNumber
     * @param serialNumber
     * @param valid
     * @param login
     * @param password
     * @param group
     * @param id
     */
    public User(String surname, String name, Date birthday, int passportNumber, String serialNumber, Date valid, String login, String password, UserGroup group, int id) {
        super(id);
        this.surname = surname;
        this.name = name;
        this.birthday = birthday;
        this.passportNumber = passportNumber;
        this.serialNumber = serialNumber;
        this.valid = valid;
        this.login = login;
        this.password = password;
        this.group = group;
    }

    /**
     * This method is used to return surname
     * @return surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * This method is used to set surname
     * @param surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * This method is used to return name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * This method is used to set name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method is used to return date of birth
     * @return date of birth
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * This method is used to set date of birth
     * @param birthday - date of birth
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * This method is used to return passport number
     * @return passport number
     */ 
    public int getPassportNumber() {
        return passportNumber;
    }

    /**
     * This method is used to set passport number
     * @param passportNumber - passport number
     */
    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }

    /**
     * This method is used to return serial number of passport
     * @return serial number of passport
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * This method is used to set serial number of passport
     * @param serialNumber - serial number of passport
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * This method is used to return valid of passport
     * @return valid of passport
     */
    public Date getValid() {
        return valid;
    }

    /**
     * This method is used to set valid of passport
     * @param valid - valid of passport
     */
    public void setValid(Date valid) {
        this.valid = valid;
    }

    /**
     * This method is used to return login
     * @return login
     */
    public String getLogin() {
        return login;
    }

    /**
     * This method is used to set login
     * @param login - login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * This method is used to return password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method is used to set password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This method is used to return usergroup
     * @return usergroup
     */
    public UserGroup getGroup() {
        return group;
    }

    /**
     * This method is used to set usergroup
     * @param group - usergroup
     */
    public void setGroup(UserGroup group) {
        this.group = group;
    }

    

    
    
}
