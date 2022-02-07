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
	This is the Edit Customer page
	This page displays fields to edit a Customer 
	The details are sent to the UpdateCustomerController class in resources package
-->

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Edit Customer</title>
	<link href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" />	
	<script src="webjars/jquery/3.3.1-1/jquery.min.js"></script>
	<script src="webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
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
	
	
	
	div.form-group{
	margin: 1em 0 0.5em 0;
	font-weight: normal;
	position: relative;
	text-shadow: 0 -1px rgba(0,0,0,0.6);
	font-size: 28px;
	line-height: 40px;
	background: #355681;
	background: rgba(53,86,129, 0.8);
	border: 1px solid #fff;
	padding: 5px 15px;
	color: white;
	border-radius: 0 10px 0 10px;
	box-shadow: inset 0 0 5px rgba(53,86,129, 0.5);
	font-family: 'Muli', sans-serif;
	
	}
	
	</style>
</head>
<body>
	<div class="container">
	
	<h1>Edit Customer:</h1>
	<c:if test="${empty editCustomer}">
		<h3> Customer details not found! <h3/> 
	</c:if>
	<c:if test="${not empty editCustomer}"> 	
	<form action="updateCustomer" method="POST">
	  <div class="form-group">
	    <label for="customerEmail">Email address</label>
	    <input required="required" type="email" class="form-control" id="customerEmail" name="customerEmail" placeholder="Enter email" value=${editCustomer.email}>
	  </div>
  	  <div class="form-group">
	    <label for="customerFirstName">First Name</label>
	    <input required="required" type="text" class="form-control" id="customerFirstName" name="customerFirstName" placeholder="First Name" value=${editCustomer.firstName}>
	  </div>
  	  <div class="form-group">
	    <label for="customerLastName">last Name</label>
	    <input required="required"  type="text" class="form-control" id="customerLastName" name="customerLastName" placeholder="Last Name" value=${editCustomer.lastName}>
	  </div>
   	  <div class="form-group">
	    <label for="customerAddress">Address</label>
	    <input required="required" type="text" class="form-control" id="customerAddress" name="customerAddress" placeholder="Address" value=${editCustomer.address}>
	  </div>
   	  <div class="form-group">
	    <label for="customerCity">City</label>
	    <input required="required" type="text" class="form-control" id="customerCity" name="customerCity" placeholder="City" value=${editCustomer.city}>
	  </div>
   	  <div class="form-group">
	    <label for="customerState">State</label>
	    <input required="required" type="text" class="form-control" id="customerState" name="customerState" placeholder="State" value=${editCustomer.state}>
	  </div>
   	  <div class="form-group">
	    <label for="customerZipcode">Zipcode</label>
	    <input required="required" type="text" class="form-control" id="customerZipcode" name="customerZipcode" placeholder="Zipcode" value=${editCustomer.zipCode} >
	  </div>
   	  <div class="form-group">
	    <label for="customerTelephone">Telephone</label>
	    <input required="required" type="text" class="form-control" id="customerTelephone" name="customerTelephone" placeholder="Telephone number" value=${editCustomer.telephone}>
	  </div>
   	  <div class="form-group">
	    <label for="customerSSN">Customer ID</label>
	    <input  type="text" class="form-control" id="customerSSN" name="customerSSN" placeholder="reenter customerID you selected" value=${editCustomer.customerID}>
	  </div>
   	  <div class="form-group">
	    <label for="customerCreditCard">Credit Card Number</label>
	    <input required="required" type="text" class="form-control" id="customerCreditCard" name="customerCreditCard" placeholder="YYYY-MM-DD" value=${editCustomer.creditCard}>
	  </div>
   	  <div class="form-group">
	    <label for="customerRating">Rating</label>
	    <input required="required" type="text" class="form-control" id="customerRating" name="customerRating" placeholder="Hourly Rate" value=${editCustomer.rating} >
	  </div>
	  
	  <button type="submit" class="btn btn-primary">Submit</button>
	</form>
	</c:if>
	</div>
	<div class="container pt-1">
		<form action="home.jsp">
			<input type="submit" value="Home" class="btn btn-success"/>
		</form>
	</div>
	

</body>
</html>