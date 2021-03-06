<%-- 
    Document   : editNumber
    Created on : 07.06.2014, 2:57:10
    Author     : Игорь
--%>

<%@page import="by.bsuir.hotel.entity.HotelNumber"%>
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
</head>
<body>
<%HotelNumber number = (HotelNumber) request.getSession().getAttribute("number");%>
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
                <form action="Servlet" method="POST" name="controller">
                    <input type="hidden" name="command" value="SaveEditNumber" />
                <div class="post_body">
                    <p> <h2><fmt:message bundle="${msgs}" key="edit.number.edit"/></h2></p>
                        <table border="0" cellpadding="15">
                        <td>
                            <p><font><fmt:message bundle="${msgs}" key="edit.number.type"/></font></p> <input name="type" readonly type="text" required value=<%=number.getType().getType()%> >
                            <p><font><fmt:message bundle="${msgs}" key="edit.number.class"/></font> </p><input name="class" pattern="[a-zA-Zа-яА-Я]+" type="text" required value=<%=number.getClassOfRoom()%>>
                            <p><font><fmt:message bundle="${msgs}" key="edit.number.price"/></font></p> <input name="price" type="text" pattern="[0-9]+" required value=<%=number.getPricePerDay()%>>
                            <p><font><fmt:message bundle="${msgs}" key="edit.number.description"/></font></p> <input name="description" size="56"pattern="[a-zA-Zа-яА-Я\s]+{0,65}" type="text" value=<%=number.getType().getDescription()%>>
                        </td> 
                        </table>
                <c:if test="${error!=null}">
                    
                        <p><font class="error"><c:out value="${error}"/></font></p>
                </c:if>
                        <div class="post_submit">
                            <input type="submit" class="autoriz" value=<fmt:message bundle="${msgs}" key="edit.number.button"/>>
                        </div>

                </div> <!-- end of post body -->
               </form>             
            </div> <!-- end of a post -->

        </div> <!-- end of main column -->
        
	    <div id="side_column">

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

<div id="templatemo_footer_wrapper">

    	<div id="templatemo_footer">

            <div class="section_w550">
            
                <div class="section_vk vk">
                    </div>

            </div>
       	
        </div> <!-- end of templatemo_footer -->
    
	<div class="cleaner"></div>
</div> <!-- end of templatemo_footer_wrapper -->

</body>
</html>
