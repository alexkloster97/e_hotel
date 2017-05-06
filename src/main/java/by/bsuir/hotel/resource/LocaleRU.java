/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.hotel.resource;

import java.util.ResourceBundle;

/**
 * This class is used to organaize work with  russian locale
 * @author Maria
 */
public class LocaleRU {
    private ResourceBundle bundle;
    /**
     * This parameter  is used to work with property
     */
    public static final String USER_EXIST = "USER_EXIST";
    /**
     * This parameter  is used to work with property
     */
    public static final String ADMIN_ADD_SUCCSES = "ADMIN_ADD_SUCCSES";
    /**
     * This parameter  is used to work with property
     */
    public static final String NUMBER_EXIST = "NUMBER_EXIST";
    /**
     * This parameter  is used to work with property
     */
    public static final String NONE_ADMIN = "NONE_ADMIN";
    /**
     * This parameter  is used to work with property
     */
    public static final String DEFAULT_STATUS = "DEFAULT_STATUS";
    /**
     * This parameter  is used to work with property
     */
    public static final String BOOKING_RESULT = "BOOKING_RESULT";
    /**
     * This parameter  is used to work with property
     */
    public static final String TRY_AGAIN = "TRY_AGAIN";
    /**
     * This parameter  is used to work with property
     */
    public static final String NO_NEW_APPLIC = "NO_NEW_APPLIC";
    /**
     * This parameter  is used to work with property
     */
    public static final String APPLIC_ACCEPT = "APPLIC_ACCEPT";
    /**
     * This parameter  is used to work with property
     */
    public static final String APPLIC_DONT_ACCEPT = "APPLIC_DONT_ACCEPT";
    /**
     * This parameter  is used to work with property
     */
    public static final String APPLIC = "APPLIC";
    /**
     * This parameter  is used to work with property
     */
    public static final String DELETE_APPLIC = "DELETE_APPLIC";
    /**
     * This parameter  is used to work with property
     */
    public static final String EDIT_APPLIC = "EDIT_APPLIC";
    /**
     * This parameter  is used to work with property
     */
    public static final String WRONG_LOGIN = "WRONG_LOGIN";
    /**
     * This parameter  is used to work with property
     */
    public static final String WRONG_PASSWORD = "WRONG_PASSWORD";
    /**
     * This parameter  is used to work with property
     */
    public static final String NO_APPLIC = "NO_APPLIC";
    /**
     * This parameter  is used to work with property
     */
    public static final String WARNING = "WARNING";
    
    
    /**
     * Constructor without parameters
     */
    public LocaleRU() {
        bundle = ResourceBundle.getBundle("warnings_ru");
    }
    
    /**
     * This method is used to return information from property by key
     * @param key - key witch help to find information in property
     * @return information from property by key
     */
    public String getValue(String key) {
        return bundle.getString(key);
    }
}
