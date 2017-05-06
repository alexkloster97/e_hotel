/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.command;

import by.bsuir.hotel.dao.HotelNumberDAO;
import by.bsuir.hotel.dao.TypeRoomDAO;
import by.bsuir.hotel.entity.Entity;
import by.bsuir.hotel.entity.HotelNumber;
import by.bsuir.hotel.entity.TypeOfRoom;
import by.bsuir.hotel.exeption.ConnectionPollException;
import by.bsuir.hotel.exeption.DAOExeption;
import by.bsuir.hotel.resource.LocaleEN;
import by.bsuir.hotel.resource.LocaleRU;
import by.bsuir.hotel.resource.ResourcesBundle;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to organize command "Add new hotel number"
 * @author Maria
 */
public class AddNumberCommand implements Command {

    private HotelNumberDAO dao;
    private TypeRoomDAO typeDAO;
    private HotelNumber number;
    private List<Entity> typeRoom;
    private boolean flag;
    final static Logger log = Logger.getLogger(AddNumberCommand.class);


    /**
     * Constructor without parameters
     */
    public AddNumberCommand() {
        flag = false;
        number = new HotelNumber();
        typeRoom = new ArrayList<Entity>();
    }

    /**
     * Constructor with parameters
     * @param flag - show that would be create two different commands
     */
    public AddNumberCommand(boolean flag) {
        this.flag = flag;
        number = new HotelNumber();
        typeRoom = new ArrayList<Entity>();
    }

    /**
     *  This method is used to create new hotelnumber or to make the transition
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
        if (flag) {
            typeDAO = new TypeRoomDAO();
            try {
                typeRoom = typeDAO.read();
                request.setAttribute("type", typeRoom);
            } catch (DAOExeption ex) {
                log.error(ex);
                return "/error.jsp";
            } catch (ConnectionPollException ex) {
                log.error(ex);
                return "/error.jsp";
            }
            page = "/addNumber.jsp";
        } else {
            typeDAO = new TypeRoomDAO();
            if (Pattern.matches(new ResourcesBundle().getValue(ResourcesBundle.NAME), request.getParameter("class"))
                    && Pattern.matches(new ResourcesBundle().getValue(ResourcesBundle.PRICE), request.getParameter("price"))
                    && Pattern.matches(new ResourcesBundle().getValue(ResourcesBundle.PRICE), request.getParameter("number"))) {
                TypeOfRoom type = new TypeOfRoom();
                dao = new HotelNumberDAO();
                number.setId(Integer.parseInt(request.getParameter("number")));
                number.setClassOfRoom(request.getParameter("class"));
                number.setPricePerDay(Integer.parseInt(request.getParameter("price")));
                type.setId(Integer.parseInt(request.getParameter("typeNumber")));
                number.setType(type);
                try {
                    List<Entity> list = new ArrayList<Entity>();
                    list = dao.read();
                    int k = 0;
                    for (int i = 0; i < list.size() && k == 0; i++) {
                        if (list.get(i).getId() == number.getId()) {
                            k = 1;
                            typeRoom = typeDAO.read();
                            request.setAttribute("type", typeRoom);
                            if (request.getSession().getAttribute("locale").equals("ru-RU")
                                    || request.getSession().getAttribute("locale").equals("ru")) {
                                message = new LocaleRU().getValue(LocaleRU.NUMBER_EXIST);
                            } else {
                                message = new LocaleEN().getValue(LocaleEN.NUMBER_EXIST);
                            }
                            request.setAttribute("error", message);
                            page = "/addNumber.jsp";
                        }
                    }
                    if (k == 0) {
                        int i = dao.create(number);
                        if (i != 0) {
                            page = "/adminOffice.jsp";
                        } else {
                            page = "/addNumber.jsp";
                        }
                    }
                } catch (DAOExeption ex) {
                    log.error(ex);
                    return "/error.jsp";
                } catch (ConnectionPollException ex) {
                    log.error(ex);
                    return "/error.jsp";
                }
            } else {
                try {
                    typeRoom = typeDAO.read();
                } catch (DAOExeption ex) {
                    log.error(ex);
                } catch (ConnectionPollException ex) {
                    log.error(ex);
                }
                request.setAttribute("type", typeRoom);
                if (request.getSession().getAttribute("locale").equals("ru-RU")
                        || request.getSession().getAttribute("locale").equals("ru")) {
                    message = new LocaleRU().getValue(LocaleRU.WARNING);
                } else {
                    message = new LocaleEN().getValue(LocaleEN.WARNING);
                }
                request.setAttribute("error", message);
                page = "/addNumber.jsp";
            }

        }
        return page;
    }
}
