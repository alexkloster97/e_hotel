<%-- 
    Document   : addNumber
    Created on : 07.06.2014, 19:07:43
    Author     : Игорь
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="by.bsuir.hotel.entity.Entity"%>
<%@page import="by.bsuir.hotel.entity.TypeOfRoom"%>
<%@page import="java.util.List"%>
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
                                <li><a href=<fmt:message bundle="${msgs}" key="jsp.adminPage"/>><fmt:message bundle="${msgs}" key="menu.main"/></a></li>
                                <li><a href="Servlet?command=SeeClientsApplication"><fmt:message bundle="${msgs}" key="menu.clients"/></a></li>
                                <li><a href=<fmt:message bundle="${msgs}" key="jsp.number"/>><fmt:message bundle="${msgs}" key="menu.information.number"/></a></li>
                                <li><a href=<fmt:message bundle="${msgs}" key="jsp.adminOffice"/>><fmt:message bundle="${msgs}" key="menu.personal"/></a></li>
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
                            <form action="Servlet" method="POST" name="controller">
                                <input type="hidden" name="command" value="SaveNumber" />
                                <div class="post_body">
                                    <p> <h2><fmt:message bundle="${msgs}" key="add.h2"/> </h2></p>
                                    <table border="0" cellpadding="15">
                                        <td>
                                            <p><font><fmt:message bundle="${msgs}" key="add.hotelnumber.number"/></font></p> <input name="number" pattern="[0-9]+" type="text" required >
                                            <p><font><fmt:message bundle="${msgs}" key="add.hotelnumber.class"/></font> </p><input name="class" pattern="[a-zA-Zа-яА-Я]+" type="text" required >  
                                        </td>
                                        <td>
                                            <p><font><fmt:message bundle="${msgs}" key="add.hotelnumber.price"/></font> </p><input name="price" pattern="[0-9]+" type="text" required> 
                                            <p><font><fmt:message bundle="${msgs}" key="add.hotelnumber.type"/></font> </p><select name="typeNumber">
                                                <c:forEach items="${type}" var="type">
                                                    <option value="${type.id}">
                                                    <c:out value="${type.type}"/>
                                                    </option>        
                                                </c:forEach>
                                            </select>

                                        </td>
                                    </table>

                                    <p></p>
                                    <c:if test="${error!=null}">
                                        <p><font class="error"><c:out value="${error}"/></font></p>
                                    </c:if>

                                    <div class="post_submit">
                                        <input type="submit" class="autoriz" value=<fmt:message bundle="${msgs}" key="add"/>>
                                    </div>

                                </div> <!-- end of post body -->
                            </form>             
                        </div> <!-- end of a post -->

                    </div> <!-- end of main column -->

                    <div id="side_column">
                        <h3><fmt:message bundle="${msgs}" key="admin.office"/></h3>
                        <div class="side_column_section">
                            <ul class="side_menu">
                                <li><a href="edit.jsp"><fmt:message bundle="${msgs}" key="admin.office.change"/></a></li>
                                <li><a href="addNewAdmin.jsp"><fmt:message bundle="${msgs}" key="admin.office.add.admin"/></a></li>
                                <form id="FindAdmin" action="Servlet" method="get" >
                                    <input type="hidden" name="command" value="FindAdmin" />
                                    <li> <a href="javascript:{}" onclick="document.getElementById('FindAdmin').submit();
                                            return false;"><fmt:message bundle="${msgs}" key="addmin.office.block"/></a></li>
                                </form>
                                <form id="AddNumber" action="Servlet" method="get" >
                                    <input type="hidden" name="command" value="AddNumber" />
                                    <li> <a href="javascript:{}" onclick="document.getElementById('AddNumber').submit();
                                            return false;"><fmt:message bundle="${msgs}" key="admin.office.add.number"/></a></li>
                                </form>
                                <form id="ExitUser" action="Servlet" method="get" >
                                    <input type="hidden" name="command" value="ExitUser" />
                                    <a href="javascript:{}" onclick="document.getElementById('ExitUser').submit();
                                            return false;"><fmt:message bundle="${msgs}" key="user.exit"/></a><br/>
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

                    <div class="section_twitter twitter">
                        <a href=<fmt:message bundle="${msgs}" key="mainAdmin.http"/>><fmt:message bundle="${msgs}" key="mainAdmin"/></a>
                    </div>

                </div>

            </div> <!-- end of templatemo_footer -->

            <div class="cleaner"></div>
        </div> <!-- end of templatemo_footer_wrapper -->

    </body>
</html>
