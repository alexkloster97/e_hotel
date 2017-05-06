<%-- 
    Document   : contacts
    Created on : 05.06.2014, 0:31:06
    Author     : Игорь
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="by.bsuir.hotel.entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="message" var="msgs" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>About</title>
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
                                
                                <c:if test="${user==null}">

                                    <li><a href=<fmt:message bundle="${msgs}" key="jsp.index"/>><fmt:message bundle="${msgs}" key="menu.main"/></a></li>
                                    <li><a href=<fmt:message bundle="${msgs}" key="jsp.about"/>><fmt:message bundle="${msgs}" key="menu.about"/></a></li>
                                    <li><a href=<fmt:message bundle="${msgs}" key="jsp.contacts"/>><fmt:message bundle="${msgs}" key="menu.contact"/></a></li>
                                </c:if>
                                <c:if test="${user.group.id==1}">

                                    <li><a href=<fmt:message bundle="${msgs}" key="jsp.adminPage"/>><fmt:message bundle="${msgs}" key="menu.main"/></a></li>
                                    <li><a href="Servlet?command=SeeClientsApplication"><fmt:message bundle="${msgs}" key="menu.clients"/></a></li>
                                    <li><a href=<fmt:message bundle="${msgs}" key="jsp.number"/>><fmt:message bundle="${msgs}" key="menu.information.number"/></a></li>
                                    <li><a href=<fmt:message bundle="${msgs}" key="jsp.adminOffice"/>><fmt:message bundle="${msgs}" key="menu.personal"/></a></li>
                                    <li><a href=<fmt:message bundle="${msgs}" key="jsp.about"/>><fmt:message bundle="${msgs}" key="menu.about"/></a></li>
                                    <li><a href=<fmt:message bundle="${msgs}" key="jsp.contacts"/>><fmt:message bundle="${msgs}" key="menu.contact"/></a></li>
                                </c:if>

                                <c:if test="${user.group.id==2}">
                                    <li><a href=<fmt:message bundle="${msgs}" key="jsp.userPage"/>><fmt:message bundle="${msgs}" key="menu.main"/></a></li>
                                    <li><a href=<fmt:message bundle="${msgs}" key="jsp.number"/>><fmt:message bundle="${msgs}" key="menu.information.number"/></a></li>
                                    <li><a href="Servlet?command=NewBooking"><fmt:message bundle="${msgs}" key="menu.booking"/></a></li>
                                    <li><a href=<fmt:message bundle="${msgs}" key="jsp.userOffice"/>><fmt:message bundle="${msgs}" key="menu.personal"/></a></li>
                                    <li><a href=<fmt:message bundle="${msgs}" key="jsp.about"/>><fmt:message bundle="${msgs}" key="menu.about"/></a></li>
                                    <li><a href=<fmt:message bundle="${msgs}" key="jsp.contacts"/>><fmt:message bundle="${msgs}" key="menu.contact"/></a></li>
                                </c:if>
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


                                <center><h2><fmt:message bundle="${msgs}" key="contacts.information"/></h2></center>

                                <fmt:message bundle="${msgs}" key="contacts.address"/><br/>
                                <h3></h3>
                                <h4><fmt:message bundle="${msgs}" key="contacts.reseption"/></h4>
                                <fmt:message bundle="${msgs}" key="contacts.reseption.phone"/>
                                <h3></h3>
                                <h4><fmt:message bundle="${msgs}" key="contacts.booking"/></h4>
                                <fmt:message bundle="${msgs}" key="contacts.booking.phone"/>
                                <h3></h3>
                                
                                <h4><fmt:message bundle="${msgs}" key="contacts.restaurant"/></h4>
                                <fmt:message bundle="${msgs}" key="contacts.restaurant.phone"/>
                                <h3></h3>

                                <h4><fmt:message bundle="${msgs}" key="contacts.info"/></h4>
                                <fmt:message bundle="${msgs}" key="contacts.info.phone"/>

                                </p>                      

                            </div> <!-- end of post body -->

                        </div> <!-- end of a post -->

                    </div> <!-- end of main column -->
                    <c:if test="${user==null}">
                    
                    <div id="side_column">
                        <form id="EnterUser" action="Servlet" method="POST" >
                            <input type="hidden" name="command" value="EnterUser" />
                            <div class="side_column_section">
                                <h3><fmt:message bundle="${msgs}" key="user.autoriz"/></h3>
                                <h4><fmt:message bundle="${msgs}" key="user.login"/></h4><input name="login" pattern="[a-zA-Z0-9\s]+" type="text" required ><br/>
                                <h4><fmt:message bundle="${msgs}" key="user.password"/></h4><input name="password" type="password" required ><br/>
                                <a href=<fmt:message bundle="${msgs}" key="jsp.addNewUser"/>><fmt:message bundle="${msgs}" key="user.regist"/></a>
                                <p><a href="javascript:{}"  onclick="document.getElementById('EnterUser').submit();
                                        return false;"><fmt:message bundle="${msgs}" key="user.enter"/></a></p>
                                <br/>
                                    <h3>
                                        <c:if test="${error!=null}">
                                        <c:out value="${error}"/>
                                        </c:if></h3>
                            </div>
                        </form>	

                    </div>	
                    </c:if>
                    <div class="cleaner"></div> 
                </div> <!-- end of main content -->

            </div> <!-- templatemo_content -->

        </div> <!-- end of templatemo_content_wrapper -->

        <div id="templatemo_footer_wrapper">

            <div id="templatemo_footer">

                <div class="section_w550">

                    <div class="section_vk vk">
                         <a href=<fmt:message bundle="${msgs}" key="mainAdmin.http"/>><fmt:message bundle="${msgs}" key="mainAdmin"/></a>
                    </div>

                </div>

            </div> <!-- end of templatemo_footer -->

            <div class="cleaner"></div>
        </div> <!-- end of templatemo_footer_wrapper -->

    </body>
</html>
