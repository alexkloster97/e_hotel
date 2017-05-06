<%-- 
    Document   : adminPage
    Created on : 05.06.2014, 2:30:15
    Author     : Игорь
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${empty sessionScope.locale}">
    <c:set var="locale" value="ru" scope="session"/>
</c:if>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="message" var="msgs" />
<!DOCTYPE html>
<html >
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

                            <div class="post_body">



                                <center><img src="images/templatemo_image_01.jpg" alt="Orange" /></center><p/>
                                <p><fmt:message bundle="${msgs}" key="index.hello"/>  </p>                      

                            </div> <!-- end of post body -->

                        </div> <!-- end of a post -->

                    </div> <!-- end of main column -->

                    <div id="side_column">

                        <div class="side_column_section">
                            
                            <table border="0" cellpadding="10" cellspacing="10">
                                <h3><fmt:message bundle="${msgs}" key="admin.hello"/></h3>
                                <td>
                                    <h4><fmt:message bundle="${msgs}" key="user.login"/></h4>
                                    <h4><fmt:message bundle="${msgs}" key="add.name"/></h4>
                                    <h4><fmt:message bundle="${msgs}" key="add.surname"/></h4>
                                </td>
                                <td>
                                    <h4>
                                        <c:out value="${user.login}"/>
                                        </h4>
                                    <h4><c:out value="${user.name}"/></h4>
                                    <h4><c:out value="${user.surname}"/></h4>
                                </td>
                            </table>
                            <a href=<fmt:message bundle="${msgs}" key="jsp.adminOffice"/>><fmt:message bundle="${msgs}" key="admin.page.office"/></a>


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