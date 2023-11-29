<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.web.bean.Expense" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>home</title>

<script>
function toggleDiv(){
	
	var myDiv1 = document.getElementById("formDiv");
	var myDiv2 = document.getElementById("statusDiv");
	if(myDiv1.style.display === "none" || myDiv1.style.display === ""){
		myDiv1.style.display = "block";
		myDiv2.style.display = "none";
		
	}else{
		myDiv1.style.display = "none";
		myDiv2.style.display = "block";
		
		
	}
}

</script>


 <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
</head>
<%
String name = (String) session.getAttribute("name");
String status = (String) session.getAttribute("status");
String email = (String) session.getAttribute("email");

Object incomeObject = session.getAttribute("income");
double income=0.0,spent=0.0;
// Check if the attribute is not null
if (incomeObject != null) {
    try {
        // Convert the attribute to a double
        income = Double.parseDouble(incomeObject.toString());

        // Now, 'incomeDouble' is a double representation of the session attribute
      
    } catch (NumberFormatException e) {
        out.println("Error converting to double: " + e.getMessage());
    }
} else {
    out.println("Income attribute is null.");
}

Object spentObject = session.getAttribute("spent");
if (spentObject != null) {
    try {
        // Convert the attribute to a double
        spent = Double.parseDouble(spentObject.toString());

        // Now, 'incomeDouble' is a double representation of the session attribute
      
    } catch (NumberFormatException e) {
        out.println("Error converting to double: " + e.getMessage());
    }
} else {
    out.println("spent attribute is null.");
}

ArrayList<Expense> arr;
int tableLength;
arr = (ArrayList<Expense>) session.getAttribute("arr");
if(arr==null)
	tableLength=0;
else
	tableLength = arr.size();
double available,per;
available = income-spent;
per = spent/income*100;
int perc = (int)per;

if(status!=null && status.equals("active"))
{
%>




<div class="navbar">
 <h1 class="logo-text"> <span  style="color: antiquewhite">Monthly </span><span style="color: aquamarine;">Budget</span></h1>
  <ul class="navbar-list">
            
           
            <li><a class="new-expense"onclick="toggleDiv()" href="#">New Expense</a></li>
            <li style="display: flex; align-items: center;"><span class="material-symbols-outlined">
                account_circle
                </span><p> Welcome <%= name %></p></li>
            <li><a href=" LoginServlet?email=<%=email%> ">Logout</a></li>
</ul>
    
</div>

    <div class="body-container">
    <%
    if(tableLength>0){
    
    %>
    <div style="overflow: scroll;" class="table-list">
    
    
    
    <%
    for(Expense i:arr){
    %>
    
    <div class="contents">
    	<div class="details">
    		<span style="font-size:45px;" class="material-symbols-outlined">
				<%=i.getCategory()%>
			</span>
			<div>
				<h3 style="font-size:25px;margin-bottom:0px;"><%= i.getDescription() %></h3>
				<p >Date:<%= i.getDay()+" "+i.getMonth()+" "+i.getYear()  %> </p>
			</div>

    	</div>
    	<div class="price">
    		<h3><%= i.getAmount() %></h3>
    	</div>
    </div>
    <hr style="background-color: #fff;">
    <%
    }
    %>
  

  
    
    
    
    
    </div>
    
    <%
    }else{
    %>
    
        <div class="left-container">
            <div class="left-container-header">
                <h3 style="color:aquamarine ;">Description</h3>
    
             </div>
             <div class="left-container-body">
                <h2 style="color: antiquewhite;" class="left-container-body-head">look like you haven't added any Expenses yet</h2>
                <p>No worries Hit the <a href="#" style="color:yellow;" onclick="toggleDiv()" >Add</a> button to get started</p>
                <h1 style="font-size: 90px;" class="material-symbols-outlined">
                    shopping_cart
                    </h1>
             </div>
        </div>
       
       <%
    }
       %> 
        
        
        <div class="right-container" >
            <div class="right-container-header">
            
            
            
            
            
            <div id="formDiv" style="height: 400px; color:yellow; display:none;" class="form-Container">
            <%
                    Date currentDate = new Date();
                    SimpleDateFormat dateformat =new SimpleDateFormat("dd MMMM yyyy");
                    String formatDate = dateformat.format(currentDate);
                    
                    %>
                    <p > <%= formatDate %> </p>
                    
                <form action="TodayServlet"  method="post" class="list-card">
                    
                        <div class="form-group">
                            
                            <select  name="category" id="cars">
                                <option value="other_admission">Category :</option>
                                <option value="restaurant">Food</option>
                                <option value="travel_explore">Travel</option>
                                <option value="shopping_bag">Shopping</option>
                                <option value="credit_score">Loan</option>
                                <option value="other_admission">other</option>
                              </select>
                              



                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" name="desc" placeholder="Description">
                            <hr>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" name="amnt" placeholder="Amount">
                            <hr>
                        </div>
						<input type="text"  name="useremail" value="<%= email %>" hidden >
                        <button type="submit" class="new-expense" >Add</button>

                    </form>
                </div>
    
    		
    		
    		 <div id="statusDiv" style="height: 400px; color:yellow;" class="form-Container" >
    		 <h2>Income : <%= (int)income %></h2>
                 <svg viewBox="0 0 36 36" class="circular-chart orange">
      <path class="circle-bg"
        d="M18 2.0845
          a 15.9155 15.9155 0 0 1 0 31.831
          a 15.9155 15.9155 0 0 1 0 -31.831"
      />
      <path class="circle"
        stroke-dasharray="<%= (int)perc %>,<%= (int)100-perc %>"
        d="M18 2.0845
          a 15.9155 15.9155 0 0 1 0 31.831
          a 15.9155 15.9155 0 0 1 0 -31.831"
      />
      
      
      <text  x="18" y="20.35" class="percentage"><%= perc %>%</text>
      <text  x="18" y="25.35" class="text-msg">Spent</text>
    </svg>
    		 	
		<div class="analysis">
            <div class="child">
                <p>Spent:</p>
                <h2 style="color:#fff;"><%= (int)spent %></h2>
            </div>
            <div class="child">
                <p>Available:</p>
                <h2 style="color:#fff;"><%= (int)available %></h2>
            </div>
		
		</div>
    		 </div>
    		
    		
    
             </div>
        </div>
    </div>

</body>



<%
}else{
%>
<body>
<h1>Home</h1>
${sessionScope.status}
<p>No access</p>
<a href="./login.jsp">login</a>
</body>
<%
}
%>
</html>