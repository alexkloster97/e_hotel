<%-- 
    Document   : adminOffice
    Created on : 06.06.2014, 0:26:56
    Author     : Игорь
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                    <p> <h3> <fmt:message bundle="${msgs}" key="user.h3"/></h3></p>
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
                <h3><fmt:message bundle="${msgs}" key="admin.office"/></h3>
                        <div class="side_column_section">
                            <ul class="side_menu">
                                <li><a href=<fmt:message bundle="${msgs}" key="jsp.edit"/>><fmt:message bundle="${msgs}" key="admin.office.change"/></a></li>
                                <li><a href=<fmt:message bundle="${msgs}" key="jsp.addNewAdmin"/>><fmt:message bundle="${msgs}" key="admin.office.add.admin"/></a></li>
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
