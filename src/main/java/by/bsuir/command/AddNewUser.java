/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.command;

import by.bsuir.hotel.MD5.HashingMD5;
import by.bsuir.hotel.dao.UserDAO;
import by.bsuir.hotel.entity.User;
import by.bsuir.hotel.entity.UserGroup;
import by.bsuir.hotel.exeption.ConnectionPollException;
import by.bsuir.hotel.exeption.DAOExeption;
import by.bsuir.hotel.resource.LocaleEN;
import by.bsuir.hotel.resource.LocaleRU;
import by.bsuir.hotel.resource.ResourcesBundle;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Date;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * This class is used to organize command "Add new user"
 * @author Maria
 */
public class AddNewUser implements Command{

    private User user;
    private UserDAO dao;
    private UserGroup group;
    final static Logger log = Logger.getLogger(AddNewUser.class);


    /**
     * Constructor without parameters
     */
    public AddNewUser() {
        user = new User();
       
        group = new UserGroup();
    }
    
    /**
     * This method is used to create new user
     * @param request - request
     * @param response - response
     * @return page
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        String message;
        if(Pattern.matches(new ResourcesBundle().getValue(ResourcesBundle.NAME), request.getParameter("name")) &&
                Pattern.matches(new ResourcesBundle().getValue(ResourcesBundle.NAME), request.getParameter("surname"))&&
                Pattern.matches(new ResourcesBundle().getValue(ResourcesBundle.LOGIN), request.getParameter("login"))
                && Pattern.matches(new ResourcesBundle().getValue(ResourcesBundle.PASSPORTNUMBER), request.getParameter("passport"))
                && Pattern.matches(new ResourcesBundle().getValue(ResourcesBundle.SERIALNUMBER), request.getParameter("serialnumber"))){
            String birthday = String.valueOf(request.getParameter("birthday"));
            String valid = String.valueOf(request.getParameter("valid"));
            user.setName(request.getParameter("name"));
            user.setSurname(request.getParameter("surname"));
            user.setPassportNumber(Integer.parseInt(request.getParameter("passport")));
            user.setSerialNumber(request.getParameter("serialnumber"));
            user.setLogin(request.getParameter("login"));
            String password;
            try {
                password = HashingMD5.hashing(request.getParameter("password"));
            } catch (Exception ex) {
                log.error(ex);
                return "/error.jsp";
            }
            user.setPassword(password);
            if(birthday.equals("")){
                user.setBirthday(null);
            }else{
                user.setBirthday(Date.valueOf(birthday));
            }
            if(valid.equals("")){
                user.setValid(null);
            }else{
            user.setValid(Date.valueOf(valid));
            }
            group.setId(2);
            user.setGroup(group);
            try {
                dao = new UserDAO();
                User tmp = dao.findByLogin(user.getLogin());
                if(tmp!=null){
                        if (request.getSession().getAttribute("locale").equals("ru-RU") 
                                || request.getSession().getAttribute("locale").equals("ru")) {
                        message = new LocaleRU().getValue(LocaleRU.USER_EXIST);
                    } else {
                        message = new LocaleEN().getValue(LocaleEN.USER_EXIST);
                    }
                       request.setAttribute("error", message);
                       page = "/addNewUser.jsp";
                }else{
                    int i = dao.create(user);
                    if(i!=0 ){
                        page="/index.jsp";
                    }
                    else{
                       page = "/addNewUser.jsp";
                    }
                }
            } catch (DAOExeption ex) {
                log.error(ex);
                return "/error.jsp";
            } catch (ConnectionPollException ex) {
                log.error(ex);
                return "/error.jsp";
            }
        }else{
            if (request.getSession().getAttribute("locale").equals("ru-RU")
                    || request.getSession().getAttribute("locale").equals("ru")) {
                message = new LocaleRU().getValue(LocaleRU.WARNING);
            } else {
                message = new LocaleEN().getValue(LocaleEN.WARNING);
            }
            request.setAttribute("error", message);
            page = "/addNewUser.jsp";
        }
        return page;
    }
    
}
