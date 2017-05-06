/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.hotel.resource;

import java.util.ResourceBundle;

/**
 *  This class is used to organaize work with property
 * @author Maria
 */
public class ResourcesBundle {
   
    private ResourceBundle bundle;
    /**
     * This parameter  is used to work with property
     */
    public static final String NAME="NAME";
    /**
     * This parameter  is used to work with property
     */
    public static final String SERIALNUMBER="SERIALNUMBER";
    /**
     * This parameter  is used to work with property
     */
    public static final String PASSPORTNUMBER="PASSPORTNUMBER";
    /**
     * This parameter  is used to work with property
     */
    public static final String LOGIN="LOGIN";
    /**
     * This parameter  is used to work with property
     */
    public static final String PRICE="PRICE";
    /**
     * This parameter  is used to work with property
     */
    public static final String DESCRIPTION="DESCRIPTION";
    /**
     * This parameter  is used to work with property
     */
    public static final String CONNECTION_NUMBER="CONNECTION_NUMBER";
    /**
     * This parameter  is used to work with property
     */
    public static final String DRIVER="DRIVER";
    /**
     * This parameter  is used to work with property
     */
    public static final String DATABASE="DATABASE";
    /**
     * This parameter  is used to work with database property
     */
    public static final String dbProperties = "database";
    
    /**
     * Constructor without parameters
     */
    public ResourcesBundle() {
        bundle = ResourceBundle.getBundle("regex");
    }
    /**
     * Constructor with parameters
     * @param s - address of property
     */
    public ResourcesBundle(String s) {
        bundle = ResourceBundle.getBundle(s);
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

