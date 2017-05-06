
<%@page import="by.bsuir.hotel.entity.HotelNumber"%>
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
    </head>
    <body>
        <%List<HotelNumber> number = (List<HotelNumber>) request.getAttribute("typeNumber");%>

        <div id="templatemo_top_panel_wrapper_outter">
            <div id="templatemo_top_panel_wrapper_inner">
                <div id="templatemo_top_panel">
                    <div id="templatemo_menu">

                        <div id="myjquerymenu" class="jquerycssmenu">
                            <ul>
                                <li><a href="adminPage.jsp"><fmt:message bundle="${msgs}" key="menu.main"/></a></li>
                                <li><a href="Servlet?command=SeeClientsApplication"><fmt:message bundle="${msgs}" key="menu.clients"/></a></li>
                                <li><a href="number.jsp"><fmt:message bundle="${msgs}" key="menu.information.number"/></a></li>
                                <li><a href="adminOffice.jsp" ><fmt:message bundle="${msgs}" key="menu.personal"/></a></li>
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
                                    <h3><fmt:message bundle="${msgs}" key="number.hotelnumber.info"/></h3>
                                    <br/>
                                    <table border="0" cellpadding="5" cellspacing="5">
                                        <tr>
                                            <th><fmt:message bundle="${msgs}" key="number.type"/></th>
                                            <th><fmt:message bundle="${msgs}" key="number.class"/></th>
                                            <th><fmt:message bundle="${msgs}" key="number.description"/></th>
                                            <th><fmt:message bundle="${msgs}" key="number.price"/></th>
                                            <th></th>
                                            <th></th>
                                        </tr>
                                        <%for (HotelNumber obj : number) {%>
                                        <tr>
                                            <td><%out.println(obj.getType().getType());%></td>
                                            <td><%out.println(obj.getClassOfRoom());%></td>
                                            <td><%
                                            if (obj.getType().getDescription() == null) {%>
                                                <fmt:message bundle="${msgs}" key="no.info"/>
                                           <% } else {
                                                out.println(obj.getType().getDescription());
                                        }%></td>
                                            <td><%out.println(obj.getPricePerDay() + " y.e.");%></td>
                                            <td>
                                                <a href="Servlet?command=EditNumber&number=<%=obj.getId()%>"><img src="images/edit.png" alt="Редактировать"></a>
                                            </td>
                                            <td>
                                                <a href="Servlet?command=DeleteNumber&number=<%=obj.getId()%>"><img src="images/delete.png" alt="Удалить"></a>
                                            </td>
                                        </tr>   
                                        <%}%>
                                    </table>
                                </center>

                            </div> <!-- end of post body -->

                        </div> <!-- end of a post -->

                    </div> <!-- end of main column -->

                    <div id="side_column">

                        <div class="side_column_section">
                            <h3><fmt:message bundle="${msgs}" key="number.hotelnumber"/></h3>

                            <ul class="numbers">
                                <table border="0" cellpadding="5" cellspacing="5">
                                    <td>
                                        <form id="FindByTypeSingle"  action="Servlet" method="get" >
                                            <input type="hidden" name="command" value="FindByType" />
                                            <input type="hidden" name="type" value="Single" />
                                            <li>
                                                <a href="javascript:{}"  onclick="document.getElementById('FindByTypeSingle').submit();
                                                        return false;"><fmt:message bundle="${msgs}" key="number.single"/></a>
                                            </li>
                                        </form>
                                        <form id="FindByTypeDouble" action="Servlet" method="get" >
                                            <input type="hidden" name="command" value="FindByType" />
                                            <input type="hidden" name="type" value="Double" />
                                            <li>                                          
                                                <a href="javascript:{}"  onclick="document.getElementById('FindByTypeDouble').submit();
                                                        return false;"><fmt:message bundle="${msgs}" key="number.double"/></a>
                                            </li>
                                        </form>
                                        <form id="FindByTypeTwin" action="Servlet" method="get" >
                                            <input type="hidden" name="command" value="FindByType" />
                                            <input type="hidden" name="type" value="Twin" />
                                            <li>

                                                <a href="javascript:{}"  onclick="document.getElementById('FindByTypeTwin').submit();
                                                        return false;"><fmt:message bundle="${msgs}" key="number.twin"/></a></li>
                                        </form> 
                                        <form id="FindByTypeDeluxe" action="Servlet" method="get" >
                                            <input type="hidden" name="command" value="FindByType" />
                                            <input type="hidden" name="type" value="Deluxe" />
                                            <li>


                                                <a href="javascript:{}"  onclick="document.getElementById('FindByTypeDeluxe').submit();
                                                        return false;"><fmt:message bundle="${msgs}" key="number.deluxe"/></a></li>
                                        </form>
                                        <form id="FindByTypeApartament" action="Servlet" method="get" >
                                            <input type="hidden" name="command" value="FindByType" />
                                            <input type="hidden" name="type" value="Apartament" />
                                            <li>
                                                <a href="javascript:{}"  onclick="document.getElementById('FindByTypeApartament').submit();
                                                        return false;"><fmt:message bundle="${msgs}" key="number.apartament"/></a></li>
                                        </form>
                                    </td>
                                </table>
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
                         <a href=<fmt:message bundle="${msgs}" key="mainAdmin.http"/>><fmt:message bundle="${msgs}" key="mainAdmin"/></a>
                    </div>

                </div>

            </div> <!-- end of templatemo_footer -->

            <div class="cleaner"></div>
        </div> <!-- end of templatemo_footer_wrapper -->

    </body>
</html>
