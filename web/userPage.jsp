<%-- 
    Document   : userPage
    Created on : 05.06.2014, 2:30:30
    Author     : Игорь
--%>

<%@page import="by.bsuir.hotel.entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty sessionScope.locale}">
    <c:set var="locale" value="ru" scope="session"/>
</c:if>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="message" var="msgs" />
<!DOCTYPE html>
<html >
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=cp1251" />
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

                                <center><img src="images/templatemo_image_01.jpg" alt="Orange" /></center><p/>
                                <p><fmt:message bundle="${msgs}" key="index.hello"/> </p>                      

                            </div> <!-- end of post body -->

                        </div> <!-- end of a post -->

                    </div> <!-- end of main column -->

                    <div id="side_column">
                        <form id="ExitUser" action="Servlet" method="get" >
                            <input type="hidden" name="command" value="ExitUser" />
                            <div class="side_column_section">
                                
                                <h3><fmt:message bundle="${msgs}" key="user.hello"/> <c:out value="${user.name} "/><c:out value="${user.surname}"/>!</h3>
                                <ul class="side_menu">
                                    <li><a href="userOffice.jsp"><fmt:message bundle="${msgs}" key="menu.personal"/></a></li>
                                    <li><a href="Servlet?command=UserApplication"><fmt:message bundle="${msgs}" key="user.myapplication"/></a></li>
                                    <li><a href="javascript:{}" onclick="document.getElementById('ExitUser').submit();
                                            return false;"><fmt:message bundle="${msgs}" key="user.exit"/></a></li>   
                                </ul>

                            </div>
                        </form>

                        <div class="side_column_section">

                            <h3><fmt:message bundle="${msgs}" key="menu.category"/> </h3>
                            <ul class="side_menu">
                                <li><a style="float: left;"href=<fmt:message bundle="${msgs}" key="jsp.hotelFacilities"/>><fmt:message bundle="${msgs}" key="menu.facilities"/></a></li>
                                <li><a style="float: left;"href=<fmt:message bundle="${msgs}" key="jsp.about"/>><fmt:message bundle="${msgs}" key="menu.about"/></a></li>
                                <li><a style="float: left;"href=<fmt:message bundle="${msgs}" key="jsp.galery"/>><fmt:message bundle="${msgs}" key="menu.foto"/></a></li>          
                            </ul>

                        </div>

<a style="float: right;"href="Servlet?command=SetLocale&locales=ru_RU"><img src="images/rus.png" alt="Русский"></a>
                                <a style="float: right;"href="Servlet?command=SetLocale&locales=en_EN"><img src="images/eng.png" alt="Английский"></a>

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