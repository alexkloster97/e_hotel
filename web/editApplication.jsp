<%-- 
    Document   : editApplication
    Created on : 08.06.2014, 14:20:18
    Author     : Игорь
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="by.bsuir.hotel.entity.TypeOfRoom"%>
<%@page import="by.bsuir.hotel.entity.Entity"%>
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
                            <form action="Servlet" method="POST" name="controller">
                                <input type="hidden" name="command" value="SaveEditApplication" />
                                <div class="post_body">
                                    
                                    <p> <h2><fmt:message bundle="${msgs}" key="appEdit.edit"/> </h2></p>
                                    <table border="0" cellpadding="15">
                                        <td>
                                            <p><font><fmt:message bundle="${msgs}" key="appEdit.dateFrom"/></font></p> <input name="dateFrom"  type="date" required value=<c:out value="${myApplication.dateFrom}"/> >
                                            <p><font><fmt:message bundle="${msgs}" key="appEdit.dateTo"/></font> </p><input name="dateTo"  type="date" required value=<c:out value="${myApplication.dateTo}"/>>

                                        </td>
                                        <td>
                                            <p><font><fmt:message bundle="${msgs}" key="appEdit.money"/></font> </p><input name="price" pattern="[0-9]+" type="text" value=<c:out value="${myApplication.moneyMax}"/>> 
                                            <p><font><fmt:message bundle="${msgs}" key="appEdit.typeRoom"/></font> </p>
                                            <select  name="typeNumber">
                                                <c:forEach items="${type}" var="number">
                                                    <c:if test="${number.type==myApplication.type.type}">
                                                        <option selected value=<c:out value="${number.id}"/>><c:out value="${number.type}"/></option> 
                                                    </c:if>
                                                    <option value=<c:out value="${number.id}"/>><c:out value="${number.type}"/></option>
                                                </c:forEach>
                                                
                                                
                                            </select>

                                        </td>
                                    </table>
                                <c:if test="${error!=null}">
                                    
                                    <p><font class="error"><c:out value="${error}"/></font></p>
                                </c:if>
                                    <div class="post_submit">
                                        <input type="submit" class="autoriz" value=<fmt:message bundle="${msgs}" key="appEdit.button"/>>
                                    </div>

                                </div> <!-- end of post body -->
                            </form>             
                        </div> <!-- end of a post -->

                    </div> <!-- end of main column -->

                    <div id="side_column">

                        <div class="side_column_section">

                            
                            <h3><fmt:message bundle="${msgs}" key="user.hello"/> <c:out value="${user.name} "/> <c:out value="${user.surname}"/>!</h3>
                                <ul class="side_menu">
                                    <a href="userOffice.jsp"><fmt:message bundle="${msgs}" key="menu.personal"/></a>
                                    <a href="Servlet?command=UserApplication"><fmt:message bundle="${msgs}" key="user.myapplication"/></a>
                                    <form id="ExitUser" action="Servlet" method="get" >
                                    <input type="hidden" name="command" value="ExitUser" />
                                    <a href="javascript:{}" onclick="document.getElementById('ExitUser').submit();
                                            return false;"><fmt:message bundle="${msgs}" key="user.exit"/></a>
                                        </form>
                                </ul>

                        </div>

                    </div> <!-- end of side column -->

                    <div class="cleaner"></div> 
                </div> <!-- end of main content -->

            </div> <!-- templatemo_content -->

        </div> <!-- end of templatemo_content_wrapper -->

    </body>
</html>
