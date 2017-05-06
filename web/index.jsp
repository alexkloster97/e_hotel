<%@page import="by.bsuir.hotel.entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${empty sessionScope.locale}">
    <c:set var="locale" value="ru" scope="session"/>
</c:if>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="message" var="msgs" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title></title>
        <link href="templatemo_style.css" rel="stylesheet" type="text/css" />
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <link rel="stylesheet" type="text/css" href="jquerycssmenu.css" />
    </head>
    <body>

        <div id="templatemo_top_panel_wrapper_outter">
            <div id="templatemo_top_panel_wrapper_inner">
                <div id="templatemo_top_panel">
                    <div id="templatemo_menu">

                        <div id="myjquerymenu" class="jquerycssmenu">
                            <ul>
                                <li><a href=<fmt:message bundle="${msgs}" key="jsp.index"/>><fmt:message bundle="${msgs}" key="menu.main"/></a></li>
                                <li><a href=<fmt:message bundle="${msgs}" key="jsp.about"/>><fmt:message bundle="${msgs}" key="menu.about"/></a></li>
                                <li><a href=<fmt:message bundle="${msgs}" key="jsp.contacts"/>><fmt:message bundle="${msgs}" key="menu.contact"/></a></li>
                                <li><a href=<fmt:message bundle="${msgs}" key="jsp.galery"/>><fmt:message bundle="${msgs}" key="menu.foto"/></a></li>
                                
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
                                <center><img src="images/templatemo_image_01.jpg" alt="Hotel" /></center><p/>
                                <p><fmt:message bundle="${msgs}" key="index.hello"/>  </p>                      

                            </div> <!-- end of post body -->

                        </div> <!-- end of a post -->

                    </div> <!-- end of main column -->

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
                                        </c:if>
                                    </h3>
                            </div>
                        </form>	
                                
                        <div class="side_column_section">

                            <h3><fmt:message bundle="${msgs}" key="menu.category"/> </h3>
                            <ul class="side_menu">
                                <li><a style="float: left;"href=<fmt:message bundle="${msgs}" key="jsp.hotelFacilities"/>><fmt:message bundle="${msgs}" key="menu.facilities"/></a></li>
                                <li><a style="float: left;"href=<fmt:message bundle="${msgs}" key="jsp.about"/>><fmt:message bundle="${msgs}" key="menu.about"/></a></li>
                                <li><a style="float: left;"href=<fmt:message bundle="${msgs}" key="jsp.galery"/>><fmt:message bundle="${msgs}" key="menu.foto"/></a></li> <br/><br/>         
                            </ul>
                           
                        </div>

                                <a style="float: right;"href="Servlet?command=SetLocale&locales=ru_RU"><img src="images/rus.png" alt="Русский"></a>
                                <a style="float: right;"href="Servlet?command=SetLocale&locales=en_EN"><img src="images/eng.png" alt="Английский"></a>
                            
                       
                    </div> <!-- end of side column -->

                    <div class="cleaner"></div> 
                </div> <!-- end of main content -->

            </div> <!-- templatemo_content -->

        </div> <!-- end of templatemo_content_wrapper -->
            <div class="cleaner"></div>
        </div> <!-- end of templatemo_footer_wrapper -->
    </body>
</html>
