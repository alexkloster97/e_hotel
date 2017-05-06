/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.command;

import by.bsuir.hotel.dao.ApplicationDAO;
import by.bsuir.hotel.entity.Application;
import by.bsuir.hotel.entity.User;
import by.bsuir.hotel.exeption.ConnectionPollException;
import by.bsuir.hotel.exeption.DAOExeption;
import by.bsuir.hotel.resource.LocaleEN;
import by.bsuir.hotel.resource.LocaleRU;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to organize command "Delete application"
 * @author Maria
 */
public class DeleteApplicationCommand implements Command{
    private ApplicationDAO dao;
    final static Logger log = Logger.getLogger(DeleteApplicationCommand.class);


    /**
     * This method is used to delete application 
     * @param request - request
     * @param response - response
     * @return page which would be made the transition
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        String message;
        int id = Integer.parseInt(request.getParameter("id"));
        dao = new ApplicationDAO();
        try {
            int i= dao.delete(dao.getByID(id));
            if(i!=0){
                List<Application> app = dao.findApplicationByIdUser(((User)request.getSession().getAttribute("user")).getId());
                if(app.size()==0){
                    if (request.getSession().getAttribute("locale").equals("ru-RU")
                            || request.getSession().getAttribute("locale").equals("ru")) {
                        message = new LocaleRU().getValue(LocaleRU.DELETE_APPLIC);
                    } else {
                        message = new LocaleEN().getValue(LocaleEN.DELETE_APPLIC);
                    }
                    request.getSession().setAttribute("result", message);
                    request.getSession().setAttribute("application", app);
                }else{
                request.getSession().setAttribute("application", app);
                }
            }
        } catch (DAOExeption ex) {
            log.error(ex);
            return "/error.jsp";
        } catch (ConnectionPollException ex) {
            log.error(ex);
            return "/error.jsp";
        }
        page="/userApplication.jsp";
        return page;
    }
    
}
