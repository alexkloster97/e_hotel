/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.command;

import by.bsuir.hotel.dao.HotelNumberDAO;

import by.bsuir.hotel.entity.HotelNumber;

import by.bsuir.hotel.exeption.ConnectionPollException;
import by.bsuir.hotel.exeption.DAOExeption;
import org.apache.log4j.Logger;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to organize command "Delete hotelnumber"
 * @author Maria
 */
public class DeleteNumberCommand implements Command{
    private HotelNumber number;
    private HotelNumberDAO dao;
    final static Logger log = Logger.getLogger(DeleteNumberCommand.class);


    /**
     * Constructor without parameters
     */
    public DeleteNumberCommand() {
        number = new HotelNumber();
        
    }
        
    /**
     * This method is used to delete hotelnumber
     * @param request - request
     * @param response - response
     * @return page which would be made the transition
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page=null;
        int id = Integer.parseInt(request.getParameter("number"));
            System.out.println(id);
            try {
                dao = new HotelNumberDAO();
                number  = (HotelNumber)dao.getByID(id);
                int i = dao.delete(number);
                if(i!=0){
                    request.setAttribute("typeNumber", dao.getByTypeRoom(number.getType().getType()));
                    page="/showInfNumberAdmin.jsp";
                }
            } catch (DAOExeption ex) {
                log.error(ex);
                return "/error.jsp";
            }
             catch (ConnectionPollException ex) {
                log.error(ex);
                return "/error.jsp";
            }
            return page;
    }
    
}
