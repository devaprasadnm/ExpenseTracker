<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
<div class="container">
    
    <form class="form-Container" action="RegisterServlet" method="post">
        <div class="form-head">
            <h2>Register</h2>
        </div>
        <div class="form-group">
        <%
        String error = (String) request.getAttribute("error");
        if(error!=null){
        	out.println("<p class='warning'>"+ error +" </p>");
        }
        
        %>
          
            <input type="number" class="form-control" id="username" name="income" placeholder="Enter your Income" required>
            <hr>
        </div>
        <div class="form-group">
           
            <input type="text" class="form-control" id="username" name="name" placeholder="Enter Name" required>
            <hr>
        </div>
        <div class="form-group">
           
            <input type="email" class="form-control" id="username" name="email" placeholder="Enter Email" required>
            <hr>
        </div>
        <div class="form-group">
            
            <input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" required>
            <hr>
        </div>
        <button type="submit" class="btn">Register</button>
        
    </form>
</div>
</body>
</html>