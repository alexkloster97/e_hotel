/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.command;

import by.bsuir.hotel.dao.HotelNumberDAO;
import by.bsuir.hotel.entity.HotelNumber;
import by.bsuir.hotel.entity.User;
import by.bsuir.hotel.exeption.ConnectionPollException;
import by.bsuir.hotel.exeption.DAOExeption;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to organize command "Find hotelnumber by type"
 * @author Maria
 */
public class FindNumberByType implements Command{
    private List<HotelNumber> number;
    private HotelNumber minNumber;
    private HotelNumber maxNumber;
    private HotelNumberDAO dao;
    final static Logger log = Logger.getLogger(FindNumberByType.class);


    /**
     * Constructor without parameters
     */
    public FindNumberByType() {
        minNumber = new HotelNumber();
        maxNumber = new HotelNumber();
        number = new ArrayList<HotelNumber>();
        
    }
    
    /**
     * This method is used to find hotelnumber by type 
     * @param request - request
     * @param response - response
     * @return page which would be made the transition
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page=null;
        String type = request.getParameter("type");
        System.out.println(type);
        try {
            dao = new HotelNumberDAO();
            number = dao.getByTypeRoom(type);
            minNumber=number.get(0);
            maxNumber=number.get(0);
            for(int i=0; i<number.size(); i++){
                if(maxNumber.getPricePerDay()<number.get(i).getPricePerDay()){
                    maxNumber = number.get(i);
                }
                if(minNumber.getPricePerDay()>number.get(i).getPricePerDay()){
                    minNumber = number.get(i);
                }
            }
            if(((User)request.getSession().getAttribute("user")).getGroup().getId()==2){
                request.setAttribute("minPrice", minNumber);
                request.setAttribute("maxPrice", maxNumber);
                page="/showInfNumberUser.jsp";
            }else{
                request.setAttribute("typeNumber", number);
                page = "/showInfNumberAdmin.jsp";
            }
        } catch (ConnectionPollException ex) {
            log.error(ex);
            return "/error.jsp";
        } catch (DAOExeption ex) {
            log.error(ex);
            return "/error.jsp";
        }
        return page;
    }
    
}
