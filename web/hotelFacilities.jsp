
<%@page import="by.bsuir.hotel.entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="message" var="msgs" />
<!DOCTYPE html>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Service</title>
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
    <%User user = (User) request.getSession().getAttribute("user");%>
    <%if(user==null){%>
         <li><a href="index.jsp"><fmt:message bundle="${msgs}" key="menu.main"/></a></li>
            <li><a href="about.jsp"><fmt:message bundle="${msgs}" key="menu.about"/></a></li>
            <li><a href="contacts.jsp"><fmt:message bundle="${msgs}" key="menu.contact"/></a></li>
    <%}else{
        if(user.getGroup().getId()==1){%>
            <li><a href="adminPage.jsp"><fmt:message bundle="${msgs}" key="menu.main"/></a></li>
            <li><a href="Servlet?command=SeeClientsApplication"><fmt:message bundle="${msgs}" key="menu.clients"/></a></li>
            <li><a href="number.jsp"><fmt:message bundle="${msgs}" key="menu.information.number"/></a></li>
            <li><a href="adminOffice.jsp" ><fmt:message bundle="${msgs}" key="menu.personal"/></a></li>
            <li><a href="about.jsp"><fmt:message bundle="${msgs}" key="menu.about"/></a></li>
            <li><a href="contacts.jsp"><fmt:message bundle="${msgs}" key="menu.contact"/></a></li>
    <%}else{
        if(user.getGroup().getId()==2){%>
                <li><a href="userPage.jsp"><fmt:message bundle="${msgs}" key="menu.main"/></a></li>
                <li><a href="number.jsp"><fmt:message bundle="${msgs}" key="menu.information.number"/></a></li>
                <li><a href="Servlet?command=NewBooking"><fmt:message bundle="${msgs}" key="menu.booking"/></a></li>
                 <li><a href="userOffice.jsp"><fmt:message bundle="${msgs}" key="menu.personal"/></a></li>
                <li><a href="about.jsp"><fmt:message bundle="${msgs}" key="menu.about"/></a></li>
                <li><a href="contacts.jsp"><fmt:message bundle="${msgs}" key="menu.contact"/></a></li>
        <%}%>
    <%}%>
    <%}%>
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
                       
                       <h2><fmt:message bundle="${msgs}" key="hotelFacilities.h2"/></h2>   
                        
                       <center><img src="images/concierge.jpg" alt="Hotel" /></center><p/>
                          
                        <p><fmt:message bundle="${msgs}" key="hotelFacilities.breakfast"/><p>
                        <p><fmt:message bundle="${msgs}" key="hotelFacilities.reseption"/><p>
                        <p><fmt:message bundle="${msgs}" key="hotelFacilities.booking"/></p>
                        <p><fmt:message bundle="${msgs}" key="hotelFacilities.wifi"/></p>
                        <p><fmt:message bundle="${msgs}" key="hotelFacilities.cleaning"/></p>
                        <p><fmt:message bundle="${msgs}" key="hotelFacilities.luggage"/></p>
                        
                
               </div> <!-- end of post body -->
                            
            </div> <!-- end of a post -->

        </div> <!-- end of main column -->
        <%if(user==null){%>
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
			</div>
          </form>	
	 
        </div>	
        <%}%>
        <div class="cleaner"></div> 
      </div> <!-- end of main content -->
    
    </div> <!-- templatemo_content -->

</div> <!-- end of templatemo_content_wrapper -->


</body>
</html>
