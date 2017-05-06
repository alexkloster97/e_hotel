/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.command;

import by.bsuir.hotel.dao.ApplicationDAO;
import by.bsuir.hotel.dao.BillDAO;
import by.bsuir.hotel.dao.HotelNumberDAO;
import by.bsuir.hotel.entity.Application;
import by.bsuir.hotel.entity.Bill;
import by.bsuir.hotel.entity.HotelNumber;
import by.bsuir.hotel.entity.User;
import by.bsuir.hotel.exeption.ConnectionPollException;
import by.bsuir.hotel.exeption.DAOExeption;
import by.bsuir.hotel.resource.LocaleEN;
import by.bsuir.hotel.resource.LocaleRU;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to organize command "Find applications"
 * @author Maria
 */
public class ClientsApplicationCommand implements Command {

    private boolean flag;
    private List<Application> clientsApplication;
    private ApplicationDAO dao;
    private HotelNumberDAO daoNumber;
    private BillDAO daoBill;
    final static Logger log = Logger.getLogger(ClientsApplicationCommand.class);


    /**
     * Constructor with parameters
     * @param flag - show that would be create two different commands
     */
    public ClientsApplicationCommand(boolean flag) {
        this.flag = flag;
        clientsApplication = new ArrayList<Application>();
    }

    /**
     *  This method is used to find applications or to make the transition
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
            dao = new ApplicationDAO();
            try {

                clientsApplication = dao.findApplicationsByStatus("Ожидает рассмотрения");
                if (clientsApplication.size() != 0) {
                    request.getSession().setAttribute("UsersApplication", clientsApplication);
                    request.getSession().setAttribute("none", null);
                } else {
                    if (request.getSession().getAttribute("locale").equals("ru-RU")
                            || request.getSession().getAttribute("locale").equals("ru")) {
                        message = new LocaleRU().getValue(LocaleRU.NO_NEW_APPLIC);
                    } else {
                        message = new LocaleEN().getValue(LocaleEN.NO_NEW_APPLIC);
                    }
                    request.getSession().setAttribute("none", message);
                    request.getSession().setAttribute("UsersApplication", null);
                }
            } catch (DAOExeption ex) {
                log.error(ex);
                return "/error.jsp";
            } catch (ConnectionPollException ex) {
                log.error(ex);
                return "/error.jsp";
            }
            page = "/clients.jsp";
        } else {
            int id = Integer.parseInt(request.getParameter("application"));
            int idAdmin = ((User) request.getSession().getAttribute("user")).getId();
            dao = new ApplicationDAO();
            daoNumber = new HotelNumberDAO();
            try {
                Application app = (Application) dao.getByID(id);
                List<HotelNumber> numbers = new ArrayList<HotelNumber>();
                numbers = daoNumber.getByTypeRoomAndPrice(app.getType().getId(), app.getMoneyMax());
                if (numbers.size() != 0) {
                    long difference = app.getDateTo().getTime() - app.getDateFrom().getTime();
                    int days = (int) difference / (24 * 60 * 60 * 1000);
                    if (days > 0) {
                        dao.updateStatus(id, idAdmin, "Заявка принята");
                        daoBill = new BillDAO();
                        Bill bill = new Bill();
                        int price = days * numbers.get(0).getPricePerDay();
                        bill.setPrice(price);
                        bill.setApplication(app);
                        bill.setNumber(numbers.get(0));
                        daoBill.create(bill);
                    } else {
                        dao.updateStatus(id, idAdmin, "Заявка отклонена");
                    }
                } else {
                    dao.updateStatus(id, idAdmin, "Заявка отклонена");
                }
                if (request.getSession().getAttribute("locale").equals("ru-RU")
                        || request.getSession().getAttribute("locale").equals("ru")) {
                    message = new LocaleRU().getValue(LocaleRU.APPLIC);
                } else {
                    message = new LocaleEN().getValue(LocaleEN.APPLIC);
                }
                request.setAttribute("result", message);
                page = "/result.jsp";
            } catch (DAOExeption ex) {
                log.error(ex);
                return "/error.jsp";
            } catch (ConnectionPollException ex) {
                log.error(ex);
                return "/error.jsp";
            }
        }
        return page;
    }
}
