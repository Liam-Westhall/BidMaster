package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.Employee;

public class EmployeeDao {
	/*
	 * This class handles all the database operations related to the employee table
	 */
	
	public String addEmployee(Employee employee) {

		/*
		 * All the values of the add employee form are encapsulated in the employee object.
		 * These can be accessed by getter methods (see Employee class in model package).
		 * e.g. firstName can be accessed by employee.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database insertion of the employee details and return "success" or "failure" based on result of the database insertion.
		 */
		
		
		String queryResult = "";
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "Videogames123456789!");
			Statement st = con.createStatement();
			String addQuery = "INSERT INTO customer (employeeid, lastname, firstname, address, city, state, zipcode, telephone, email, startdate, hourlyrate, level)\r\n"   //level is the type of employee manager cust rep etc.
					+ "VALUES ( " + employee.getEmployeeID() + "," + employee.getLastName() + "," + employee.getFirstName() + ", "+ employee.getAddress() + ", " + employee.getCity() + ", " + employee.getState() + ", " + employee.getZipCode() + ", " + employee.getTelephone() + ", " + employee.getEmail() +", " + employee.getStartDate() + ", " + employee.getHourlyRate() + ", " + employee.getLevel() + ");";
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
		
		
		/*Sample data begins*/
		return queryResult;
		/*Sample data ends*/
		


	}

	public String editEmployee(Employee employee) {
		/*
		 * All the values of the edit employee form are encapsulated in the employee object.
		 * These can be accessed by getter methods (see Employee class in model package).
		 * e.g. firstName can be accessed by employee.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database update and return "success" or "failure" based on result of the database update.
		 */
		String queryResult = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "Videogames123456789!");
			Statement st = con.createStatement();
			String editEmployeeQuery = "UPDATE employee SET lastname = " + employee.getLastName() + ", firstname = " + employee.getFirstName() + ", address = " + employee.getAddress() + ", city = " + employee.getCity() + ", state = " + employee.getState() + ", zipcode = " + employee.getZipCode() + ", telephone = " + employee.getTelephone() + ", email =" + employee.getEmail() + ", startdate = " + employee.getStartDate() + ", hourlyrate = " + employee.getHourlyRate() + ", level = " + employee.getLevel() + " WHERE employeeid = " + employee.getEmployeeID();
			int editUpdate = st.executeUpdate(editEmployeeQuery);
			if(editUpdate == 0) {
				queryResult = "failure"; //If no rows changed return failure else return success
			}
			else {
				queryResult = "success";
			}
			}
		
		catch(Exception e) {
			System.out.println(e);
		}
		
		
		/*Sample data begins*/
		return queryResult;
		/*Sample data ends*/

	}

	public String deleteEmployee(String employeeID) {
		/*
		 * employeeID, which is the Employee's ID which has to be deleted, is given as method parameter
		 * The sample code returns "success" by default.
		 * You need to handle the database deletion and return "success" or "failure" based on result of the database deletion.
		 */
		
		/*Sample data begins*/
		
		
		/*Sample data begins*/           /*NEED TO CHANGE SSN IN CUSTOMER TO CUSTOMERID ALL OF THEM TO VARCHAR*/
		String queryResult = "";
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "Videogames123456789!");
			Statement st = con.createStatement();
			String delQuery = "DELETE from customer WHERE employeeid = " + employeeID;
			int deleted = st.executeUpdate(delQuery);
			if(deleted == 0) { //If 0 rows deleted then failure else success
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

	
	public List<Employee> getEmployees() {

		/*
		 * The students code to fetch data from the database will be written here
		 * Query to return details about all the employees must be implemented
		 * Each record is required to be encapsulated as a "Employee" class object and added to the "employees" List
		 */

		List<Employee> employees = new ArrayList<Employee>();
		
		/*Sample data begins*/
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "Videogames123456789!");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from employee");
			while(rs.next()) {
				Employee employee = new Employee();
				employee.setEmployeeID(rs.getString("employeeid"));
				employee.setAddress(rs.getString("address"));
				employee.setLastName(rs.getString("lastname"));
				employee.setFirstName(rs.getString("firstname"));
				employee.setCity(rs.getString("city"));
				employee.setState(rs.getString("state"));
				employee.setEmail(rs.getString("email"));
				employee.setZipCode(rs.getInt("zipcode"));
				employee.setTelephone(rs.getString("telephone"));
				employee.setHourlyRate(rs.getFloat("hourlyrate"));
				employee.setLevel(rs.getString("level"));
				employees.add(employee);
			}
			}
			catch(Exception e) {
				System.out.println(e);
			}
		
		/*Sample data ends*/
		
		return employees;
	}

	public Employee getEmployee(String employeeID) {

		/*
		 * The students code to fetch data from the database based on "employeeID" will be written here
		 * employeeID, which is the Employee's ID who's details have to be fetched, is given as method parameter
		 * The record is required to be encapsulated as a "Employee" class object
		 */

		Employee employee = new Employee();
		
		/*Sample data begins*/
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "Videogames123456789!");
			Statement st = con.createStatement();
			String getemployQuery = "SELECT * FROM employee WHERE employeeid = " + employeeID;
			ResultSet rs = st.executeQuery(getemployQuery);
			employee.setEmployeeID(rs.getString("employeeid")); //change ssn in tables to employeeid
			employee.setAddress(rs.getString("address"));
			employee.setLastName(rs.getString("lastname"));
			employee.setFirstName(rs.getString("firstname"));
			employee.setCity(rs.getString("city"));
			employee.setState(rs.getString("state"));
			employee.setEmail(rs.getString("email"));
			employee.setZipCode(rs.getInt("zipcode"));
			employee.setTelephone(rs.getString("telephone"));
			employee.setStartDate(rs.getString("startdate"));
			employee.setHourlyRate(rs.getInt("hourlyrate"));
			employee.setLevel(rs.getString("level"));
			
		}
		
		catch(Exception e) {
			System.out.println(e);
		}
		/*Sample data ends*/
		
		return employee;
	}
	
	public Employee getHighestRevenueEmployee() {
		
		/*
		 * The students code to fetch employee data who generated the highest revenue will be written here
		 * The record is required to be encapsulated as a "Employee" class object
		 */
		
		Employee employee = new Employee();
		
		/*Sample data begins*/
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "Videogames123456789!");
			Statement st = con.createStatement();
			String getemployHighRevQuery = "SELECT rs.lastname\r\n"
					+ "FROM (\r\n"
					+ "        SELECT e.employeeid as employeeid, ROUND(SUM(b.maximum_bid), 2) AS revenue\r\n"
					+ "        FROM bid b\r\n"
					+ "                 LEFT JOIN employee e ON b.monitor = e.employeeid\r\n"
					+ "        WHERE b.sold = TRUE\r\n"
					+ "        GROUP BY e.lastname\r\n"     //Modified original query where it was customer instead by accident
					+ "        ORDER BY revenue DESC\r\n"   //Probably wrong query needs to be fixed
					+ "        LIMIT 1\r\n"
					+ "    ) rs;\r\n";
		  ResultSet r1 = st.executeQuery(getemployHighRevQuery);
		  String highRevemployee = r1.getString("employee"); 
		  String getEmployeedataQuery = "SELECT * FROM employee WHERE employeeid = " + highRevemployee;
		  ResultSet r2 = st.executeQuery(getEmployeedataQuery);
		  while(r2.next()) {
			  employee.setEmployeeID(r2.getString("ssn"));
				employee.setAddress(r2.getString("address"));
				employee.setLastName(r2.getString("lastname"));
				employee.setFirstName(r2.getString("firstname"));
				employee.setCity(r2.getString("city"));
				employee.setState(r2.getString("state"));
				employee.setEmail(r2.getString("email"));
				employee.setZipCode(r2.getInt("zipcode"));
				employee.setTelephone(r2.getString("telephone"));
				employee.setStartDate(r2.getString("startdate"));
				employee.setHourlyRate(r2.getInt("hourlyrate"));
				employee.setLevel(r2.getString("level"));
		  }
		  
		  
		}
		catch(Exception e) {
			System.out.println(e);
		}
		/*Sample data ends*/
		
		return employee;
	}

	public String getEmployeeID(String username) {
		/*
		 * The students code to fetch data from the database based on "username" will be written here
		 * username, which is the Employee's email address who's Employee ID has to be fetched, is given as method parameter
		 * The Employee ID is required to be returned as a String
		 */

		String employID = "";
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "Videogames123456789!");
			Statement st = con.createStatement();
			String idQuery = "SELECT employeeid FROM employee WHERE email =  " + username + "";
			ResultSet rs = st.executeQuery(idQuery);
			String tempemployID = rs.getString("customerid");
			employID = tempemployID;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		return employID;
	}

}
