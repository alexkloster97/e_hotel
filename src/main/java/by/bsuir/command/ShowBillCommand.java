/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.command;

import by.bsuir.hotel.dao.BillDAO;
import by.bsuir.hotel.dao.UserDAO;
import by.bsuir.hotel.entity.Bill;
import by.bsuir.hotel.entity.User;
import by.bsuir.hotel.exeption.ConnectionPollException;
import by.bsuir.hotel.exeption.DAOExeption;
import org.apache.log4j.Logger;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to organize command "Show bill"
 * @author Maria
 */
public class ShowBillCommand implements Command{

    private BillDAO dao;
    private Bill bill;
    private UserDAO userDao;
    private User admin;
    final static Logger log = Logger.getLogger(ShowBillCommand.class);


    /**
     * Constructor without parameters
     */
    public ShowBillCommand() {
        bill = new Bill();
        admin = new User();
    }
    
    
    /**
     * This method is used to show information about bill
     * @param request - request
     * @param response - response
     * @return page which would be made the transition
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dao = new BillDAO();
        userDao = new UserDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            bill = dao.getBillByApplication(id);
            request.getSession().setAttribute("bill", bill);
            admin = (User)userDao.getByID(bill.getApplication().getIdUpdate());
            request.getSession().setAttribute("admin", admin);
        } catch (ConnectionPollException ex) {
            log.error(ex);
            return "/error.jsp";
        } catch (DAOExeption ex) {
            log.error(ex);
            return "/error.jsp";
        }
        
        return "/showBill.jsp";
    }
    
}
