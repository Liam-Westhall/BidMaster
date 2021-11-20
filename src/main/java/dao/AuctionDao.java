package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Auction;
import model.Bid;
import model.Customer;
import model.Item;

import java.sql.*;



public class AuctionDao {
	
	public List<Auction> getAllAuctions() {
		
		List<Auction> auctions = new ArrayList<Auction>();
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Each record is required to be encapsulated as a "Auction" class object and added to the "auctions" ArrayList
		 * Query to get data about all the auctions should be implemented
		 */
		
		/*Sample data begins*/
		try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "Videogames123456789!");
		Statement st = con.createStatement();
		String getAuctionsQuery = "SELECT * FROM auction";
		ResultSet rs = st.executeQuery(getAuctionsQuery);
		while(rs.next()) {
			Auction auction = new Auction();
			auction.setAuctionID(rs.getInt("auctionid"));
			auction.setBidIncrement(rs.getFloat("bidincrement"));
			auction.setMinimumBid(rs.getFloat("minimumbid"));
			auction.setCopiesSold(rs.getInt("copiesold"));
			auction.setMonitor(rs.getInt("monitor"));
			auction.setItemID(rs.getInt("itemid"));
			auctions.add(auction);
		}
		
		}
		
		catch(Exception e) {
			System.out.println(e);
		}
		/*Sample data ends*/
		
		return auctions;

	}

	public List<Auction> getAuctions(String customerID) {
		
		List<Auction> auctions = new ArrayList<Auction>();
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Each record is required to be encapsulated as a "Auction" class object and added to the "auctions" ArrayList
		 * Query to get data about all the auctions in which a customer participated should be implemented
		 * customerID is the customer's primary key, given as method parameter
		 */
		
		/*Sample data begins*/
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "Videogames123456789!");
			Statement st = con.createStatement();
			String getAuctioncustQuery = "SELECT * FROM auction WHERE customerid = " + customerID;
			ResultSet rs = st.executeQuery(getAuctioncustQuery);
			while(rs.next()) {
				Auction auction = new Auction();
				auction.setAuctionID(rs.getInt("auctionid"));
				auction.setBidIncrement(rs.getFloat("bidincrement"));
				auction.setMinimumBid(rs.getFloat("minimumbid"));
				auction.setCopiesSold(rs.getInt("copiesold"));
				auction.setMonitor(rs.getInt("monitor"));
				auction.setItemID(rs.getInt("itemid"));
				auctions.add(auction);
			}
		}
		
		catch(Exception e) {
			System.out.println(e);
		}
		
		/*Sample data ends*/
		
		return auctions;

	}

	public List<Auction> getOpenAuctions(String employeeEmail) {
		List<Auction> auctions = new ArrayList<Auction>();
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Each record is required to be encapsulated as a "Auction" class object and added to the "auctions" ArrayList
		 * Query to get data about all the open auctions monitored by a customer representative should be implemented
		 * employeeEmail is the email ID of the customer representative, which is given as method parameter
		 */
		
		/*Sample data begins*/
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "Videogames123456789!");
			Statement st = con.createStatement();
			String getEmployIdQuery = "SELECT employeeid FROM employee WHERE email = " + employeeEmail;
			ResultSet rs1 = st.executeQuery(getEmployIdQuery);
			int employeeId = rs1.getInt("employeeId");
			String getOpenAuctionmonitor = "SELECT * FROM auction WHERE monitor = " + employeeId;
			ResultSet rs2 = st.executeQuery(getOpenAuctionmonitor);
			while(rs2.next()) {
				Auction auction = new Auction();
				auction.setAuctionID(rs2.getInt("auctionid"));
				auction.setBidIncrement(rs2.getFloat("bidincrement"));
				auction.setMinimumBid(rs2.getFloat("minimumbid"));
				auction.setCopiesSold(rs2.getInt("copiesold"));
				auction.setMonitor(rs2.getInt("monitor"));
				auction.setItemID(rs2.getInt("itemid"));
				auctions.add(auction);
			}
		}
		
		
		catch(Exception e) {
			System.out.println(e);
		}
		
		/*Sample data ends*/
		
		return auctions;

		
		
	}

	public String recordSale(String auctionID) {
		/*
		 * The students code to update data in the database will be written here
		 * Query to record a sale, indicated by the auction ID, should be implemented
		 * auctionID is the Auction's ID, given as method parameter
		 * The method should return a "success" string if the update is successful, else return "failure"
		 */
		String queryResult = "";
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "Videogames123456789!");
			Statement st = con.createStatement();
			String recordSaleQuery = "UPDATE auction SET sold = 1 WHERE auctionid = " + auctionID;
			int updated = st.executeUpdate(recordSaleQuery);
			
			if(updated == 0) {
				queryResult = "failure";
			}
			else {
				queryResult = "success";
			}
			
		}
		
		catch(Exception e) {
			System.out.println(e);
		}
		
		/* Sample data begins */
		return queryResult;
		/* Sample data ends */
	}

	public List getAuctionData(String auctionID, String itemID) {
		
		List output = new ArrayList();
		Item item = new Item();
		Bid bid = new Bid();
		Auction auction = new Auction();
		Customer customer = new Customer();
		
		/*
		 * The students code to fetch data from the database will be written here
		 * The item details are required to be encapsulated as a "Item" class object
		 * The bid details are required to be encapsulated as a "Bid" class object
		 * The auction details are required to be encapsulated as a "Auction" class object
		 * The customer details are required to be encapsulated as a "Customer" class object
		 * Query to get data about auction indicated by auctionID and itemID should be implemented
		 * auctionID is the Auction's ID, given as method parameter
		 * itemID is the Item's ID, given as method parameter
		 * The customer details must include details about the current winner of the auction
		 * The bid details must include details about the current highest bid
		 * The item details must include details about the item, indicated by itemID
		 * The auction details must include details about the item, indicated by auctionID
		 * All the objects must be added in the "output" list and returned
		 */
		
		/*Sample data begins*/
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "Videogames123456789!");
			Statement st = con.createStatement();
			String getAuctionQuery = "SELECT * from auction WHERE auctionid = " + auctionID + " AND itemid = " + itemID;
			ResultSet r1 = st.executeQuery(getAuctionQuery); //auction table
			String getCurrentWinnerAndBidQuery = "SELECT * from bid WHERE auctionid = " + auctionID + " AND itemid = " + itemID; //to get winner get bid with auction id and most recent time 
			ResultSet r2 = st.executeQuery(getCurrentWinnerAndBidQuery); //Bid table
			String getItemDetails = "SELECT * from items WHERE itemid = " + itemID;
			ResultSet r3 = st.executeQuery(getItemDetails); //Item Table
			while(r1.next()) {
				auction.setAuctionID(r1.getInt("auctionid"));
				auction.setBidIncrement(r1.getFloat("bidincrement"));
				auction.setMinimumBid(r1.getFloat("minimumbid"));
				auction.setCopiesSold(r1.getInt("copiesold"));
				auction.setMonitor(r1.getInt("monitor"));
				auction.setItemID(r1.getInt("itemid"));   //Set data in auction
			}
			while(r2.next()) {
				bid.setCustomerID(r2.getString("customerid"));
				bid.setAuctionID(r2.getInt("auctionid"));    //Set data in bid
				bid.setBidPrice(r2.getFloat("bidprice"));
				bid.setMaxBid(r2.getFloat("maximumbid"));
				bid.setBidTime(r2.getString("bidtime"));
			}
		String winningcustomer = r2.getString("customerid");
		String winningCustomerDetailsQuery = "SELECT * from customer WHERE customerid = " + winningcustomer;
		ResultSet r4 = st.executeQuery(winningCustomerDetailsQuery);
		while(r4.next()) {
			customer.setCustomerID(r4.getString("customerid"));
			customer.setAddress(r4.getString("address"));
			customer.setLastName(r4.getString("lastname"));
			customer.setFirstName(r4.getString("firstname"));
			customer.setCity(r4.getString("city"));
			customer.setState(r4.getString("state"));   //Customer table
			customer.setEmail(r4.getString("email"));
			customer.setZipCode(r4.getInt("zipcode"));
			customer.setTelephone(r4.getString("telephone"));
			customer.setCreditCard(r4.getString("creditcard"));
			customer.setRating(r4.getInt("rating"));
		}
		while(r3.next()) {
			item.setDescription(r3.getString("Description"));
			item.setItemID(r3.getInt("itemid"));
			item.setName(r3.getString("itemname"));
			item.setNumCopies(r3.getInt("numcopies")); // item table
			item.setSoldPrice(r3.getInt("soldprice"));
			item.setType(r3.getString("type"));
			item.setYearManufactured(r3.getInt("yearmanufactured"));
		}
			
		}
		
		catch(Exception e) {
			System.out.println(e);
		}
		/*Sample data ends*/
		
		output.add(item);
		output.add(bid);
		output.add(auction);
		output.add(customer);
		
		return output;

	}

	
}
