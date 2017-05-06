/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.command;

import by.bsuir.hotel.dao.HotelNumberDAO;
import by.bsuir.hotel.dao.TypeRoomDAO;
import by.bsuir.hotel.entity.HotelNumber;
import by.bsuir.hotel.entity.TypeOfRoom;
import by.bsuir.hotel.exeption.ConnectionPollException;
import by.bsuir.hotel.exeption.DAOExeption;
import by.bsuir.hotel.resource.ResourcesBundle;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to organize command "Edit information about hotelnumber"
 * @author Maria
 */
public class EditNumberCommand implements Command{

    private HotelNumber number;
    private TypeOfRoom type;
    private HotelNumberDAO dao;
    private TypeRoomDAO typeDao;
    private boolean flag;
    final static Logger log = Logger.getLogger(EditNumberCommand.class);


    /**
     * Constructor without parameters
     */
    public EditNumberCommand() {
        flag = false;
        number = new HotelNumber();
        type = new TypeOfRoom();
        
    }

    /**
     * Constructor with parameters
     * @param flag -  show that would be create two different commands
     */
    public EditNumberCommand(boolean flag) {
        this.flag = flag;
        number = new HotelNumber();
        type = new TypeOfRoom();
        
    }
    
    
    /**
     * This method is used to edit hotelnumber or to make the transition
     * @param request - request
     * @param response - response
     * @return page which would be made the transition
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String page = null;
        if(flag){
            int id = Integer.parseInt(request.getParameter("number"));
            System.out.println(id);
            dao = new HotelNumberDAO();
            typeDao = new TypeRoomDAO();
            try {
                number  = (HotelNumber)dao.getByID(id);
                request.getSession().setAttribute("number", number);
                page="/editNumber.jsp";
            } catch (DAOExeption ex) {
                log.error(ex);
                return "/error.jsp";
            } catch (ConnectionPollException ex) {
                log.error(ex);
                return "/error.jsp";
            }
       }else{
            if(Pattern.matches(new ResourcesBundle().getValue(ResourcesBundle.NAME), request.getParameter("class")) &&
                  Pattern.matches(new ResourcesBundle().getValue(ResourcesBundle.PRICE), request.getParameter("price")) &&
                    Pattern.matches(new ResourcesBundle().getValue(ResourcesBundle.DESCRIPTION), request.getParameter("description"))){
                number = (HotelNumber)request.getSession().getAttribute("number");
                number.setClassOfRoom(request.getParameter("class"));
                number.setPricePerDay(Integer.parseInt(request.getParameter("price")));
                type.setId(number.getType().getId());
                type.setType(number.getType().getType());
                type.setDescription(request.getParameter("description"));
                number.setType(type);
                try {
                    int i=dao.update(number);
                    int j = typeDao.update(type);
                    if(i!=0 && j!=0){
                        request.setAttribute("typeNumber", dao.getByTypeRoom(type.getType()));
                        page = "/showInfNumberAdmin.jsp";
                    }
                    else{
                        page = "/number.jsp";
                    }
                } catch (DAOExeption ex) {
                    log.error(ex);
                    return "/error.jsp";
                } catch (ConnectionPollException ex) {
                    log.error(ex);
                    return "/error.jsp";
                }
            }else{
                request.setAttribute("error", "Введены некорректные данные!");
                page = "/editNumber.jsp";
            }
       }
       return page;
    }
    
}
