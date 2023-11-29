<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
<div class="container">
    
    <form class="login form-Container" action="LoginServlet" method="post">
        <div class="form-head">
            <h2>Login</h2>
        </div>
             <%
        String error = (String) request.getAttribute("error");
        if(error!=null){
        	out.println("<p class='warning'>"+ error +" </p>");
        }
        
        %>
        <div class="form-group">
           
            <input type="email" class="form-control" id="username" name="email" placeholder="Enter Email" required>
            <hr>
        </div>
        <div class="form-group">
            
            <input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" required>
            <hr>
        </div>
        <button type="submit" class="btn">Login</button>
       
    </form>
    <div " class="lo">
    	<a  href="registration.jsp">Don't have an account <span style="color:aqua;">click here</span> </a>
    </div >
     
	</div>
</body>
</html>