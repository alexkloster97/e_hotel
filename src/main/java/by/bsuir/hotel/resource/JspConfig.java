package by.bsuir.hotel.resource;

import java.util.ResourceBundle;

/**
 * Created by Alexandr on 03.05.2017.
 */
public class JspConfig {
    public static final String ABOUT = "about";
    public static final String ADD_NEW_ADMIN = "addNewAdmin";
    public static final String ADD_NEW_USER = "addNewUser";
    public static final String ADMIN_OFFICE = "adminOffice";
    public static final String ADMIN_PAGE = "adminPage";
    public static final String APPLICATION = "application";
    public static final String CLIENTS = "clients";
    public static final String CONTACTS = "contacts";
    public static final String DELETE_ADMIN = "deleteAdmin";
    public static final String EDIT = "edit";
    public static final String EDIT_APPLICATION = "editApplication";
    public static final String EDIT_NUMBER = "editNumber";
    public static final String GALERY = "galery";
    public static final String HOTEL_FACILITIES = "hotelFacilities";
    public static final String INDEX = "index";
    public static final String NUMBER = "number";
    public static final String RESULT = "result";
    public static final String RESULT_USER = "resultUser";
    public static final String SHOW_BILL = "showBill";
    public static final String SHOW_INF_NUMBER_ADMIN = "showInfNumberAdmin";
    public static final String SHOW_INT_NUMBER_USER = "showInfNumberUser";
    public static final String USER_APPLICATION = "userApplication";
    public static final String USER_OFFICE = "userOffice";
    public static final String USER_PAGE = "userPage";

    private static final String JSP_BOUNDLE_NAME = "jsp";
    private static ResourceBundle bundle = ResourceBundle.getBundle(JSP_BOUNDLE_NAME);

    /**
     * Reads property from jsp.properties
     *
     * @param key   property to read
     * @return      value of property
     */
    public static String getProperty(String key) {
        if(bundle == null) {
            bundle = ResourceBundle.getBundle(JSP_BOUNDLE_NAME);
        }

        return bundle.getString(key);
    }
}
