<%-- 
    Document   : application
    Created on : 05.06.2014, 23:52:14
    Author     : Игорь
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

        <div id="templatemo_top_panel_wrapper_outter">
            <div id="templatemo_top_panel_wrapper_inner">
                <div id="templatemo_top_panel">
                    <div id="templatemo_menu">

                        <div id="myjquerymenu" class="jquerycssmenu">
                            <ul>
                                <li><a href=<fmt:message bundle="${msgs}" key="jsp.userPage"/>><fmt:message bundle="${msgs}" key="menu.main"/></a></li>
                                <li><a href=<fmt:message bundle="${msgs}" key="jsp.number"/>><fmt:message bundle="${msgs}" key="menu.information.number"/></a></li>
                                <li><a href="Servlet?command=NewBooking"><fmt:message bundle="${msgs}" key="menu.booking"/></a></li>
                                <li><a href=<fmt:message bundle="${msgs}" key="jsp.userOffice"/>><fmt:message bundle="${msgs}" key="menu.personal"/></a></li>
                                <li><a href=<fmt:message bundle="${msgs}" key="jsp.about"/>><fmt:message bundle="${msgs}" key="menu.about"/></a></li>
                                <li><a href=<fmt:message bundle="${msgs}" key="jsp.contacts"/>><fmt:message bundle="${msgs}" key="menu.contact"/></a></li>
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
                                    <h3><fmt:message bundle="${msgs}" key="application.head"/></h3>

                                    <br/>
                                    <img src="images/booking.jpg" alt="Booking" />

                                    <p><fmt:message bundle="${msgs}" key="application.main"/></p>
                                </center>  
                            </div> <!-- end of post body -->             
                        </div> <!-- end of a post -->

                    </div> <!-- end of main column -->

                    <div id="side_column">

                        <div class="side_column_section">
                            <h3><fmt:message bundle="${msgs}" key="application.form"/></h3>
                            <form action="Servlet" method="get" name="controller">
                                <input type="hidden" name="command" value="Booking" />
                                <center> 
                                    <table border="0" cellpadding="1" >
                                        <tr>
                                            <td>
                                                <p><fmt:message bundle="${msgs}" key="application.dateFrom"/></p>
                                            </td>
                                            <td>
                                                <input name="dateFrom" size="10" type="date" required > 
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <p><fmt:message bundle="${msgs}" key="application.dateTo"/></p></td>
                                            <td>
                                                <input name="dateTo" size="10" type="date" required >
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <p><fmt:message bundle="${msgs}" key="application.price"/></p>
                                            </td>
                                            <td>
                                                <input name="price" size="10" pattern="[0-9]+" type="text" >
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <p><fmt:message bundle="${msgs}" key="application.type"/></p>
                                            </td>
                                            <td>
                                                <select  name="typeNumber">
                                                    <c:forEach items="${type}" var="type">
                                                        <option value="${type.id}"><c:out value="${type.type}"/></option>        
                                                    </c:forEach>
                                                </select>
                                            </td>
                                        </tr>


                                    </table>
                                </center>
                                <c:if test="${error!=null}">
                                    <p><font class="error"><c:out value="${error}"/></font></p>
                                </c:if>
                                <div class="post_submit">
                                    <input type="submit" class="autoriz" value=<fmt:message bundle="${msgs}" key="application.add"/>>
                                </div>   
                            </form>
                        </div>	

                    </div> <!-- end of side column -->

                    <div class="cleaner"></div> 
                </div> <!-- end of main content -->

            </div> <!-- templatemo_content -->

        </div> <!-- end of templatemo_content_wrapper -->

        <div id="templatemo_footer_wrapper">

            <div id="templatemo_footer">

                <div class="section_w550">

                    <div class="section_twitter twitter">
                    </div>

                </div>

            </div> <!-- end of templatemo_footer -->

            <div class="cleaner"></div>
        </div> <!-- end of templatemo_footer_wrapper -->

    </body>
</html>
