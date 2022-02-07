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
	This is the Search Customer page
	This page contains a text field where search parameter (name, address, city, state, etc) can be entered to search for Customers in the database
	This page redirects to the Customer Listing page
-->


<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="width:device-width, initial-scale=1">
		<link href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" />
		
			<style>
	
	h2{
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
	
	input[type = text]{
	
  margin-top: 10px;
  margin-bottom: 10px;
  margin-right: 10px;
  margin-left: 10px;
	
	}
	
	
	input[type = "submit"]{
	background: rgb(105,105,105);
	border: 1px solid black;
	}
	
	</style>
		
		
		
	</head>
	<body>
		<div class="container">
			<h2>Search Customer</h2>
			<form action="getCustomers">
				<input type="text" name="searchKeyword" class="form-control"  placeholder = "customer's first name or last name "/>
				<input type="submit" value="Search" class="btn btn-primary" />
			</form>
		</div>
		<div class="container pt-1">
		<form action="home.jsp">
			<input type="submit" value="Home" class="btn btn-primary"/>
		</form>
	</div>
		
		
		<script src="webjars/jquery/3.3.1-1/jquery.min.js"></script>
		<script src="webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
	</body>
</html>
