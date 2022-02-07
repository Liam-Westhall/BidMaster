package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.Customer;

import java.util.stream.IntStream;

public class CustomerDao {
	/*
	 * This class handles all the database operations related to the customer table
	 */
	
	/**
	 * @param String searchKeyword
	 * @return ArrayList<Customer> object
	 */
	public List<Customer> getCustomers(String searchKeyword) {
		/*
		 * This method fetches one or more customers based on the searchKeyword and returns it as an ArrayList
		 */
		
		List<Customer> customers = new ArrayList<Customer>();

		/*
		 * The students code to fetch data from the database based on searchKeyword will be written here
		 * Each record is required to be encapsulated as a "Customer" class object and added to the "customers" List
		 */
		
		/*Sample data begins*/
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/auction", "root", "Videogames123456789!");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from customer where firstname like \'%" + searchKeyword + "%\'" + "or lastName like \'%" + searchKeyword + "%\'");
			while(rs.next()) {
				Customer customer = new Customer();
				customer.setCustomerID(rs.getString("customerid"));
				customer.setAddress(rs.getString("address"));
				customer.setLastName(rs.getString("lastname"));
				customer.setFirstName(rs.getString("firstname"));
				customer.setCity(rs.getString("city"));
				customer.setState(rs.getString("state"));
				customer.setEmail(rs.getString("email"));
				customer.setZipCode(rs.getInt("zipcode"));
				customer.setTelephone(rs.getString("telephone"));
				customer.setCreditCard(rs.getString("creditcard"));
				customer.setRating(rs.getInt("rating"));
				customers.add(customer);
			}
			}
			catch(Exception e) {
				System.out.println(e);
			}
		/*Sample data ends*/
		
		return customers;
	}


	public Customer getHighestRevenueCustomer() {
		/*
		 * This method fetches the customer who generated the highest total revenue and returns it
		 * The students code to fetch data from the database will be written here
		 * The customer record is required to be encapsulated as a "Customer" class object
		 */


		/*Sample data begins*/
		Customer highRevCustomer = new Customer();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/auction", "root", "Videogames123456789!");
			Statement highRevstatement = con.createStatement();
			
			String highRevQuery ="select CustomerID, sum(soldprice*copiesSold) as rev"
					+ " from items it"
					+ " inner join auction auc"
					+ " on auc.itemid = it.itemID"
					+ " inner join post p"
					+ " on p.auctionid = auc.auctionid"
					+ " Group by p.customerid"
					+ " order by rev DESC"
					+ " limit 1";
			ResultSet rs = highRevstatement.executeQuery(highRevQuery);
			String highcustomerid = "";
			while(rs.next()) {
				highcustomerid = rs.getString("customerid");
			}
			String findCustomer = "select * from customer c where c.customerid= '" + highcustomerid + "'"; 
			ResultSet rss = highRevstatement.executeQuery(findCustomer);
			while(rss.next()) {
			
				Customer customer = new Customer();
				customer.setCustomerID(rss.getString("customerid"));
				customer.setAddress(rss.getString("address"));
				customer.setLastName(rss.getString("lastname"));
				customer.setFirstName(rss.getString("firstname"));
				customer.setCity(rss.getString("city"));
				customer.setState(rss.getString("state"));
				customer.setEmail(rss.getString("email"));
				customer.setZipCode(rss.getInt("zipcode"));
				customer.setTelephone(rss.getString("telephone"));
				customer.setCreditCard(rss.getString("creditcard"));
				customer.setRating(rss.getInt("rating"));
				highRevCustomer = customer;
		
		}
		}
		catch(Exception e) {
			System.out.println(e);
		/*Sample data ends*/
	
		
		}
		return highRevCustomer;
	}

	public List<Customer> getCustomerMailingList() {

		/*
		 * This method fetches the all customer mailing details and returns it
		 * The students code to fetch data from the database will be written here
		 * Each customer record is required to be encapsulated as a "Customer" class object and added to the "customers" List
		 */

		
		List<Customer> customers = new ArrayList<Customer>();
		
		/*Sample data begins*/
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/auction", "root", "Videogames123456789!");
			Statement mailStatement = con.createStatement();
			String mailListQuery = "SELECT * FROM customer;";
			ResultSet rs = mailStatement.executeQuery(mailListQuery);
			while(rs.next()) {
				Customer customer = new Customer();
				customer.setCustomerID(rs.getString("customerid"));
				customer.setAddress(rs.getString("address"));
				customer.setLastName(rs.getString("lastname"));
				customer.setFirstName(rs.getString("firstname"));
				customer.setCity(rs.getString("city"));
				customer.setState(rs.getString("state"));
				customer.setEmail(rs.getString("email"));
				customer.setZipCode(rs.getInt("zipcode"));
				customer.setTelephone(rs.getString("telephone"));
				customer.setCreditCard(rs.getString("creditcard"));
				customer.setRating(rs.getInt("rating"));
				customers.add(customer);
			}
			}
		catch(Exception e) {
			System.out.println(e);
		}
		/*Sample data ends*/
		
		return customers;
	}

	public Customer getCustomer(String customerID) {

		/*
		 * This method fetches the customer details and returns it
		 * customerID, which is the Customer's ID who's details have to be fetched, is given as method parameter
		 * The students code to fetch data from the database will be written here
		 * The customer record is required to be encapsulated as a "Customer" class object
		 */
		
		/*Sample data begins*/
		Customer customer = new Customer();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/auction", "root", "Videogames123456789!");
			Statement st = con.createStatement();
			String query = "SELECT * FROM customer WHERE customerID = '" + customerID + "'";
			ResultSet rs = st.executeQuery(query);
			Customer tempcustomer = new Customer();
			customer.setCustomerID(rs.getString("customerid"));
			customer.setAddress(rs.getString("address"));
			customer.setLastName(rs.getString("lastname"));
			customer.setFirstName(rs.getString("firstname"));
			customer.setCity(rs.getString("city"));
			customer.setState(rs.getString("state"));
			customer.setEmail(rs.getString("email"));
			customer.setZipCode(rs.getInt("zipcode"));
			customer.setTelephone(rs.getString("telephone"));
			customer.setCreditCard(rs.getString("creditcard"));
			customer.setRating(rs.getInt("rating"));
			customer = tempcustomer;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		/*Sample data ends*/
		
		return customer;
	}
	
	public String deleteCustomer(String customerID) {

		/*
		 * This method deletes a customer returns "success" string on success, else returns "failure"
		 * The students code to delete the data from the database will be written here
		 * customerID, which is the Customer's ID who's details have to be deleted, is given as method parameter
		 */

		/*Sample data begins*/           /*NEED TO CHANGE SSN IN CUSTOMER TO CUSTOMERID ALL OF THEM TO VARCHAR*/
		String queryResult = "";
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/auction", "root", "Videogames123456789!");
			Statement st = con.createStatement();
			String delQuery = "DELETE from customer WHERE customerid = '" + customerID + "'";
			int deleted = st.executeUpdate(delQuery);
			if(deleted == 0) {
				queryResult = "failure";
			}
			else {
				queryResult = "success";
			}
		}
		
		catch(Exception e) {
			System.out.println(e);
		}
		
		return queryResult;
		/*Sample data ends*/
		
	}


	public String getCustomerID(String username) {
		/*
		 * This method returns the Customer's ID based on the provided email address
		 * The students code to fetch data from the database will be written here
		 * username, which is the email address of the customer, who's ID has to be returned, is given as method parameter
		 * The Customer's ID is required to be returned as a String
		 */
		String custID = "";
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/auction", "root", "Videogames123456789!");
			Statement st = con.createStatement();
			String idQuery = "SELECT customerid FROM customer WHERE email =  '" + username + "'";
			ResultSet rs = st.executeQuery(idQuery);
			String tempCustID = rs.getString("customerid");
			custID = tempCustID;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		return custID;
	}


	public List<Customer> getSellers() {
		
		/*
		 * This method fetches the all seller details and returns it
		 * The students code to fetch data from the database will be written here
		 * The seller (which is a customer) record is required to be encapsulated as a "Customer" class object and added to the "customers" List
		 */

		List<Customer> customers = new ArrayList<Customer>();
		
		/*Sample data begins*/
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/auction", "root", "Videogames123456789!");
			Statement st1 = con.createStatement();
			Statement st2 = con.createStatement();
			String sellersIDQuery = "SELECT customerid FROM post"; // grab the IDs of the sellers from the post table
			ResultSet rs1 = st1.executeQuery(sellersIDQuery); // table of customerids of sellers
			while(rs1.next()) { //while that result set exists perform select statements to grab the details from customer table
				Customer customer = new Customer();
				String sellerInfoQuery = "SELECT * FROM customer WHERE customerid = '" + rs1.getString("customerid") + "'" ; 
				ResultSet rs2 = st2.executeQuery(sellerInfoQuery);
				while(rs2.next()) {
				customer.setCustomerID(rs2.getString("customerid"));
				customer.setAddress(rs2.getString("address"));
				customer.setLastName(rs2.getString("lastname"));
				customer.setFirstName(rs2.getString("firstname"));
				customer.setCity(rs2.getString("city"));
				customer.setState(rs2.getString("state"));
				customer.setEmail(rs2.getString("email"));
				customer.setZipCode(rs2.getInt("zipcode"));
				customer.setTelephone(rs2.getString("telephone"));
				customer.setCreditCard(rs2.getString("creditcard"));
				customer.setRating(rs2.getInt("rating"));
				customers.add(customer);
				}
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		/*Sample data ends*/
		
		return customers;

	}


	public String addCustomer(Customer customer) {

		/*
		 * All the values of the add customer form are encapsulated in the customer object.
		 * These can be accessed by getter methods (see Customer class in model package).
		 * e.g. firstName can be accessed by customer.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database insertion of the customer details and return "success" or "failure" based on result of the database insertion.
		 */
		String queryResult = "";
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/auction", "root", "Videogames123456789!");
			Statement st = con.createStatement();
			String addQuery = "INSERT INTO customer (customerid, lastname, firstname, address, city, state, zipcode, telephone, email, creditcard, rating)\r\n"
					+ "VALUES ( '" + customer.getCustomerID() + "', '" + customer.getLastName() + "', '" + customer.getFirstName() +  "', '"+ customer.getAddress() + "', '"+ customer.getCity() + "', '" + customer.getState() + "', '" + customer.getZipCode() + "', '" + customer.getTelephone() + "', '" + customer.getEmail() +"', '" + customer.getCreditCard() + "', '" + customer.getRating() + "');";
			System.out.println(addQuery);
			int inserted = st.executeUpdate(addQuery);
			if(inserted == 0) {
				queryResult = "failure";
			}
			else {
				queryResult = "success";
			}
		}
		
		catch(Exception e) {
			System.out.println(e);
		}
		
		System.out.println(queryResult);
		
		/*Sample data begins*/
		return queryResult;
		/*Sample data ends*/

	}

	public String editCustomer(Customer customer) {
		/*
		 * All the values of the edit customer form are encapsulated in the customer object.
		 * These can be accessed by getter methods (see Customer class in model package).
		 * e.g. firstName can be accessed by customer.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database update and return "success" or "failure" based on result of the database update.
		 */
		String queryResult = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/auction", "root", "Videogames123456789!");
			Statement st = con.createStatement();
			String editQuery = "UPDATE customer SET lastname = '" + customer.getLastName() + "', firstname = '" + customer.getFirstName() + "', lastname = '" + customer.getLastName() + "', address = '" + customer.getAddress() + "', city = '" + customer.getCity() + "', state = '" + customer.getState() + "', zipcode = '" + customer.getZipCode() + "', telephone = '" + customer.getTelephone() + "', email = '" + customer.getEmail() + "', creditcard = '" + customer.getCreditCard() + "', rating = '" + customer.getRating() + "'WHERE customerid = '" + customer.getCustomerID() + "'";
			int editUpdate = st.executeUpdate(editQuery); //Returns rows changed and executes the update
			System.out.println("editQuery");
			if(editUpdate == 0) {  //If not updated then failure
				queryResult = "failure";
			}
			
			else {
				queryResult = "success";
			}
		}

		
		catch(Exception e) {
			System.out.println(e);
		}
		
		System.out.println(queryResult);
		
		/*Sample data begins*/
		return queryResult;
		/*Sample data ends*/

	}

}
