
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
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    </head>
    <body>

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
                                
                                <p> <h3><fmt:message bundle="${msgs}" key="user.h3"/> </h3></p>
                                <center>
                                    <div class="user_table">
                                        <table border="1" cellpadding="1">
                                            <tr>
                                                <th><fmt:message bundle="${msgs}" key="add.surname"/></th>
                                                <th><fmt:message bundle="${msgs}" key="add.name"/></th>
                                                <th><fmt:message bundle="${msgs}" key="add.birthday"/></th>

                                            </tr>

                                            <td>
                                            <c:out value="${user.surname}" />
                                            </td>
                                            <td>
                                            <c:out value="${user.name}" />   
                                            </td>
                                            <td>
                                            <c:if test="${user.birthday==null}">
                                                <fmt:message bundle="${msgs}" key="info.null"/>

                                            </c:if>
                                            <c:out value="${user.birthday}"/>
                                            </td>
                                            <tr>
                                                <th><fmt:message bundle="${msgs}" key="user.passport"/></th>
                                                <th><fmt:message bundle="${msgs}" key="add.valid"/></th>
                                                <th><fmt:message bundle="${msgs}" key="user.login"/></th>
                                            </tr>
                                            <td>
                                            <c:if test="${user.serialNumber==null || user.passportNumber==0}">
                                                <fmt:message bundle="${msgs}" key="info.null"/>
                                            </c:if>
                                            <c:out value="${user.serialNumber}"/>
                                            <c:out value="${ user.passportNumber}"/>

                                            </td>
                                            <td>
                                            <c:if test="${user.valid==null}">
                                                <fmt:message bundle="${msgs}" key="info.null"/>
                                            </c:if>
                                            <c:out value="${user.valid}"/> 
                                            </td>

                                            <td><c:out value="${user.login}" /></td>
                                        </table>
                                    </div>
                                </center>  


                            </div> <!-- end of post body -->             
                        </div> <!-- end of a post -->

                    </div> <!-- end of main column -->

                    <div id="side_column">

                        <div class="side_column_section">
                            <h3><fmt:message bundle="${msgs}" key="user.office"/></h3>
                            <ul class="side_menu">
                                <li><a href="edit.jsp"><fmt:message bundle="${msgs}" key="user.office.edit"/></a></li>
                                <li><a href="Servlet?command=UserApplication"><fmt:message bundle="${msgs}" key="user.office.application"/></a></li>
                                <form id="ExitUser" action="Servlet" method="get" >
                                    <input type="hidden" name="command" value="ExitUser" />
                                    <a href="javascript:{}" onclick="document.getElementById('ExitUser').submit();
                                            return false;"><fmt:message bundle="${msgs}" key="user.office.exit"/></a><br/>
                                </form>       
                            </ul>
                        </div>	

                        <div class="side_column_section">

                            <h3><fmt:message bundle="${msgs}" key="menu.category"/> </h3>
                            <ul class="side_menu">
                                <li><a href=<fmt:message bundle="${msgs}" key="jsp.hotelFacilities"/>><fmt:message bundle="${msgs}" key="menu.facilities"/></a></li>
                                <li><a href=<fmt:message bundle="${msgs}" key="jsp.about"/>><fmt:message bundle="${msgs}" key="menu.about"/></a></li>
                                <li><a href=<fmt:message bundle="${msgs}" key="jsp.galery"/>><fmt:message bundle="${msgs}" key="menu.foto"/></a></li>          
                            </ul>

                        </div>



                    </div> <!-- end of side column -->

                    <div class="cleaner"></div> 
                </div> <!-- end of main content -->

            </div> <!-- templatemo_content -->

        </div> <!-- end of templatemo_content_wrapper -->
    </body>
</html>