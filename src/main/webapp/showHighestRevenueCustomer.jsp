<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<!--
	This is the Highest Revenue Customer Details page
	This page displays the data for the customer who generated the highest revenue
	The details are fetched using the "customer" field from the request object
-->

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width:device-width, initial-scale=1">
	<link href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" />
	<title>Highest Revenue Customer Details</title>
	
	<style>
	
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
	
		
	input[type = "submit"]{
	background: rgb(105,105,105);
	border: 1px solid black;
	}
	
	button[type = "submit"]{
	background: rgb(105,105,105);
	border: 1px solid black;
	}
	
	
	h3{
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
	
	table{
	border-collapse: collapse;
    margin: 25px 0;
    font-size: 1.0em;
    font-family: sans-serif;
    min-width: 400px;
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
	}
	</style>
</head>
<body>

	
	<div class="container">
	<h3>The Customer that generated the highest revenue is:</h3>
	<c:if test="${empty customer}">
		<h3> Customer details not found! <h3/> 
	</c:if>
	<c:if test="${not empty customer}">
		<table class="table table-striped">
		  <thead>
		    <tr>
		      <th>Customer ID</th>
		      <th>First Name</th>
		      <th>Last Name</th>
			  <th>Email</th>
		    </tr>
		  </thead>
		  <tbody>
		       <tr>
		         <td>${customer.customerID}</td>
		         <td>${customer.firstName}</td>
		         <td>${customer.lastName}</td>
		         <td>${customer.email}</td>
		       </tr>
		  </tbody>
		</table>
	</c:if>
	</div>
	<div class="container pt-1">
		<form action="home.jsp">
			<input type="submit" value="Home" class="btn btn-success"/>
		</form>
	</div>

	
	<script src="webjars/jquery/3.3.1-1/jquery.min.js"></script>
	<script src="webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
	
</body>
</html>