/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.hotel.filters;


import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;


/**
 * This class is used to filter
 * @author Maria
 */
public abstract class Filters implements Filter{

    /**
     * This method is used to initialize
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    /**
     * This method is used to destroy
     */
    @Override
    public void destroy() {
        
    }
    
}
