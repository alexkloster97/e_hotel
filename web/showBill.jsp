<%-- 
    Document   : showBill
    Created on : 08.06.2014, 16:39:42
    Author     : Игорь
--%>

<%@page import="by.bsuir.hotel.entity.Bill"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="by.bsuir.hotel.entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="message" var="msgs" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title></title>
        <link href="templatemo_style.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" type="text/css" href="jquerycssmenu.css" />
    </head>
    <body>
        <%Bill bill = (Bill) request.getSession().getAttribute("bill");%>
        <%User admin = (User) request.getSession().getAttribute("admin");%>
        <div id="templatemo_top_panel_wrapper_outter">
            <div id="templatemo_top_panel_wrapper_inner">
                <div id="templatemo_top_panel">
                    <div id="templatemo_menu">

                        <div id="myjquerymenu" class="jquerycssmenu">
                            <ul>
                                <li><a href="userPage.jsp"><fmt:message bundle="${msgs}" key="menu.main"/></a></li>
                                <li><a href="number.jsp"><fmt:message bundle="${msgs}" key="menu.information.number"/></a></li>
                                <li><a href="Servlet?command=NewBooking"><fmt:message bundle="${msgs}" key="menu.booking"/></a></li>
                                <li><a href="userOffice.jsp"><fmt:message bundle="${msgs}" key="menu.personal"/></a></li>
                                <li><a href="about.jsp"><fmt:message bundle="${msgs}" key="menu.about"/></a></li>
                                <li><a href="contacts.jsp"><fmt:message bundle="${msgs}" key="menu.contact"/></a></li>
                            </ul>
                            <br style="clear: left" />
                        </div>

                    </div>
                    <br/><br/>
                    <center><img src="images/logo.png" alt="Emblem" /> </center>

                </div> <!-- end of templatemo_top_panel -->

            </div> <!-- end of templatemo_top_panel_wrapper_inner -->
        </div> <!-- end of  templatemo_top_panel_wrapper_outter -->

        <div id="templatemo_content_wrapper">

            <div id="templatemo_content">


                <div id="templatemo_main_content_top"></div>
                <div id="templatemo_main_coontent">

                    <div id="main_column">

                        <div class="post_box">
                            <div class="box"></div> <!-- end of date box -->

                            <div class="post_body">


                                <center>
                                    <h3><fmt:message bundle="${msgs}" key="bill.info"/></h3>
                                    <br/>
                                    <table border="0" cellpadding="1" cellspacing="1">
                                        <tr>
                                            <th><fmt:message bundle="${msgs}" key="bill.hotelnumber"/></th>
                                            <th><fmt:message bundle="${msgs}" key="bill.type"/></th>
                                            <th><fmt:message bundle="${msgs}" key="bill.dateFrom"/></th>
                                            <th><fmt:message bundle="${msgs}" key="bill.dateTo"/></th>
                                            <th><fmt:message bundle="${msgs}" key="bill.price"/></th>
                                        </tr>

                                        <tr>
                                            <td><%out.println(bill.getNumber().getId());%></td>
                                            <td><%out.println(bill.getNumber().getType().getType());%></td>
                                            <td><%out.println(bill.getApplication().getDateFrom());%></td>
                                            <td><%out.println(bill.getApplication().getDateTo());%></td>
                                            <td><%out.println(bill.getPrice() + " y.e.");%></td>
                                        </tr> 
                                        
                                    </table>
                                        <br/><br/><br/>
                                    <h3><fmt:message bundle="${msgs}" key="bill.admin.info"/></h3>
                                    <br/>
                                    <table border="0" cellpadding="5" cellspacing="5">
                                        <tr>
                                            <th><fmt:message bundle="${msgs}" key="bill.admin.surname"/></th>
                                            <th><fmt:message bundle="${msgs}" key="bill.admin.name"/></th>
                                            
                                        </tr>

                                        <tr>
                                            <td><%out.println(admin.getSurname());%></td>
                                            <td><%out.println(admin.getName());%></td>
                                        </tr> 

                                    </table>
                                <br/>
                                        <p><font><fmt:message bundle="${msgs}" key="bill.thanks"/></font></p>
</center>



                            </div> <!-- end of post body -->

                        </div> <!-- end of a post -->

                    </div> <!-- end of main column -->

                    <div id="side_column">

                        <div class="side_column_section">
                            <h3><fmt:message bundle="${msgs}" key="user.hello"/> <c:out value="${user.name} "/> <c:out value="${user.surname}"/>!</h3>
                                <ul class="side_menu">
                                    <li><a href="userOffice.jsp"><fmt:message bundle="${msgs}" key="menu.personal"/></a></li>
                                    <li><a href="Servlet?command=UserApplication"><fmt:message bundle="${msgs}" key="user.myapplication"/></a></li><br/>
                                    <form id="ExitUser" action="Servlet" method="get" >
                                    <input type="hidden" name="command" value="ExitUser" />
                                    <li><a href="javascript:{}" onclick="document.getElementById('ExitUser').submit();
                                        return false;"><fmt:message bundle="${msgs}" key="user.exit"/></a></li>
                                        </form>
                                </ul>




                        </div>

                    </div> <!-- end of side column -->

                    <div class="cleaner"></div> 
                </div> <!-- end of main content -->

            </div> <!-- templatemo_content -->

        </div> <!-- end of templatemo_content_wrapper -->

        <div id="templatemo_footer_wrapper">

            <div id="templatemo_footer">

                <div class="section_w550">

                    <div class="section_vk vk">
                    </div>

                </div>

            </div> <!-- end of templatemo_footer -->

            <div class="cleaner"></div>
        </div> <!-- end of templatemo_footer_wrapper -->

    </body>
</html>
