<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--
	This is the Home page for Manager
	This page contains various buttons to navigate the online auction house
	This page contains manager specific accessible buttons
-->

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="width:device-width, initial-scale=1">
		<link href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" />
		<title>Manager Home</title>
		<style type="text/css">
		h1 {
		margin: 1em 0 0.5em 0;
	font-weight: 600;
	font-family: 'Titillium Web', sans-serif;
	position: relative;  
	font-size: 36px;
	line-height: 40px;
	padding: 15px 15px 15px 15%;
	color: #355681;
	box-shadow: 
		inset 0 0 0 1px rgba(53,86,129, 0.4), 
		inset 0 0 5px rgba(53,86,129, 0.5),
		inset -285px 0 35px white;
	border-radius: 0 10px 0 10px;
	text-align: center;
	left: -45px
	}
	
	h2{
	margin: 1em 0 0.5em 0;
	font-weight: normal;
	position: relative;
	text-shadow: 0 -1px rgba(0,0,0,0.6);
	font-size: 28px;
	line-height: 40px;
	background: #355681;
	background: rgba(53,86,129, 0.8);
	border: 2px solid black;
	padding: 5px 15px;
	color: white;
	border-radius: 0 10px 0 10px;
	box-shadow: inset 0 0 5px rgba(53,86,129, 0.5);
	font-family: 'Muli', sans-serif;
	}
	
	div.card-body{
	background: #355681;
	background: rgba(53,86,129, 0.8);
	
	}
	
	div.card{
	
	border: 2px solid black;
	margin-top: 5px;
    margin-bottom: 5px;
    margin-right: 5px;
    margin-left: 5px;
	}
	
	
	input[type = "submit"]{
	background: rgb(105,105,105);
	border: 1px solid black;
	}
	
	h5{
	
	margin: 1em 0 0.5em 0;
	font-weight: 600;
	font-family: 'Titillium Web', sans-serif;
	position: relative;
	font-size: 18px;
	line-height: 20px;
	color: #000000;
	font-family: 'Muli', sans-serif;
	
	}
		
		</style>
	</head>
	<body>
	
		<h1>Welcome to the Online Auction House!</h1>
		<div class="container">
			<h2>Manager Options:</h2>
			<%
				String email = (String)session.getAttribute("email");
				String role = (String)session.getAttribute("role");
				
				//redirect to appropriate home page if already logged in
				if(email != null) {
					if(role.equals("customerRepresentative")) {
						response.sendRedirect("customerRepresentativeHome.jsp");
					}
					else if(!role.equals("manager")){
						response.sendRedirect("home.jsp");	
					}
				}
				else {
					// redirect to log in if not alreaddy logged in
					response.sendRedirect("index.jsp");
				}

			%>
			
			<div class="row">
				<div class="col">
					<div class="card">
					  <div class="card-body">
					    <h5 class="card-title">Manage Employee Options:</h5>
    					<div class="container">
							<form action="addEmployee.jsp">
								<input type="submit" value="Add Employee" class="btn btn-primary"/>
							</form>
							<form action="getEmployees" class="pt-1">
								<input type="submit" value="View / Edit / Delete Employee" class="btn btn-primary"/>
							</form>
							
						</div>
					  </div>
					</div>
				</div>
				<div class="col">
					<div class="card">
					  <div class="card-body">
					    <h5 class="card-title">Access Sales Report:</h5>
    					<div class="container">
							<form action="searchSalesReport.jsp">
								<input type="submit" value="Sales Report" class="btn btn-primary"/>
							</form>
						</div>
					  </div>
					</div>
				</div>
				<div class="col">
					<div class="card">
					  <div class="card-body">
					    <h5 class="card-title">View Available Items:</h5>
    					<div class="container">
							<form action="getItems">
								<input type="submit" value="View Items" class="btn btn-primary"/>
							</form>
						</div>
					  </div>
					</div>
				</div>
				<div class="col">
					<div class="card">
					  <div class="card-body">
					    <h5 class="card-title">View Recorded Sales:</h5>
    					<div class="container">
							<form action="searchSales.jsp">
								<input type="submit" value="View Sales" class="btn btn-primary"/>
							</form>
						</div>
					  </div>
					</div>
				</div>
				<div class="col">
					<div class="card">
					  <div class="card-body">
					    <h5 class="card-title">View Revenue Summary:</h5>
    					<div class="container">
							<form action="searchSummaryListing.jsp">
								<input type="submit" value="View Revenue Summary" class="btn btn-primary"/>
							</form>
						</div>
					  </div>
					</div>
				</div>
				<div class="col">
					<div class="card">
					  <div class="card-body">
					    <h5 class="card-title">View Highest Revenue Generators Options:</h5>
    					<div class="container">
							<form action="getHighestRevenue">
								<input type="submit" value="Customer Representative" class="btn btn-primary"/>
							</form>
							<form action="getHighestRevenueCustomer" class="pt-1">
								<input type="submit" value="Customer" class="btn btn-primary"/>
							</form>
							
						</div>
					  </div>
					</div>
				</div>
				<div class="col">
					<div class="card">
					  <div class="card-body">
					    <h5 class="card-title">View Bestselling Items:</h5>
    					<div class="container">
							<form action="getBestsellers">
								<input type="submit" value="View Bestsellers" class="btn btn-primary"/>
							</form>
						</div>
					  </div>
					</div>
				</div>
				
			</div>
			
			
		</div>
		<div class="container">
			<form action="logout">
				<input type="submit" value="Logout" class="btn btn-danger"/>
			</form>
		</div>
		
		<script src="webjars/jquery/3.3.1/jquery.min.js"></script>
		<script src="webjars/bootstrap/4.1.3/bootstrap.min.js"></script>
	</body>
</html>