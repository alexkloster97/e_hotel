<%-- 
    Document   : addNewUser
    Created on : 04.06.2014, 22:27:54
    Author     : Игорь
--%>

<%@page import="by.bsuir.hotel.entity.User"%>
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
                                <li><a href="index.jsp"><fmt:message bundle="${msgs}" key="menu.main"/></a></li>
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
                            <form action="Servlet" method="POST" name="controller">
                                <input type="hidden" name="command" value="addNewUser" />
                                <div class="post_body">
                                    <p> <h2><fmt:message bundle="${msgs}" key="add.h2"/> </h2></p>
                                    <table border="0" cellpadding="15">
                                        <td>
                                            <p><font><fmt:message bundle="${msgs}" key="add.surname"/></font></p> <input name="surname" pattern="[a-zA-Zа-яА-Я]+" type="text" required >
                                            <p><font><fmt:message bundle="${msgs}" key="add.name"/></font> </p><input name="name" pattern="[a-zA-Zа-яА-Я]+" type="text" required >
                                            <p><font><fmt:message bundle="${msgs}" key="add.birthday"/></font></p> <input name="birthday" type="date" >
                                            <p><font><fmt:message bundle="${msgs}" key="add.serial_number"/></font></p> <input name="serialnumber" pattern="[A-Z]+" type="text" >
                                        </td>
                                        <td>
                                            <p><font><fmt:message bundle="${msgs}" key="add.number_passport"/></font> </p><input name="passport" pattern="[0-9]+" type="text" > 
                                            <p><font><fmt:message bundle="${msgs}" key="add.valid"/></font> </p><input name="valid"  type="date" >
                                            <p><font><fmt:message bundle="${msgs}" key="user.login"/></font> </p><input name="login" pattern="[a-zA-Z0-9]+" type="text" required >
                                            <p><font><fmt:message bundle="${msgs}" key="user.password"/></font> </p><input name="password"  type="text" required >
                                        </td>
                                    </table>
                                    <%if (request.getAttribute("error") == null) {%>
                                    <p></p>
                                    <%} else {%>
                                    <p><font class="error"><%out.println(request.getAttribute("error"));%></font></p>
                                        <%}%>
                                    <div class="post_submit">
                                        <input type="submit" class="autoriz" value=<fmt:message bundle="${msgs}" key="add.registration"/>>
                                    </div>

                                </div> <!-- end of post body -->
                            </form>             
                        </div> <!-- end of a post -->

                    </div> <!-- end of main column -->

                    <div id="side_column">

                        <div class="side_column_section">

                            <h3><fmt:message bundle="${msgs}" key="menu.category"/></h3>
                            <ul class="side_menu">
                                <li><a href="hotelFacilities.jsp"><fmt:message bundle="${msgs}" key="menu.facilities"/></a></li>
                                <li><a href="about.jsp"><fmt:message bundle="${msgs}" key="menu.about"/></a></li>
                                <li><a href="contacts.jsp"><fmt:message bundle="${msgs}" key="menu.contact"/></a></li>
                                <li><a href="galery.jsp"><fmt:message bundle="${msgs}" key="menu.foto"/></a></li>         
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

                    <div class="section_twitter twitter">
                        <a href=<fmt:message bundle="${msgs}" key="mainAdmin.http"/>><fmt:message bundle="${msgs}" key="mainAdmin"/></a>
                    </div>

                </div>

            </div> <!-- end of templatemo_footer -->

            <div class="cleaner"></div>
        </div> <!-- end of templatemo_footer_wrapper -->

    </body>
</html>
