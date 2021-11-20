package dao;

import java.util.ArrayList;
import java.util.List;

import model.Bid;
import model.Customer;

import java.sql.*;
public class BidDao {

	public List<Bid> getBidHistory(String auctionID) {
		
		List<Bid> bids = new ArrayList<Bid>();

		/*
		 * The students code to fetch data from the database
		 * Each record is required to be encapsulated as a "Bid" class object and added to the "bids" ArrayList
		 * auctionID, which is the Auction's ID, is given as method parameter
		 * Query to get the bid history of an auction, indicated by auctionID, must be implemented
		 */

		/*Sample data begins*/
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "Videogames123456789!");
			Statement st = con.createStatement();
			String bidhistoryQuery = ("SELECT * FROM bids WHERE auctionid = " + auctionID);
			ResultSet rs = st.executeQuery(bidhistoryQuery);
			while(rs.next()) {
				Bid bid = new Bid();
				bid.setCustomerID(rs.getString("customerid"));
				bid.setAuctionID(rs.getInt("auctionid"));
				bid.setBidPrice(rs.getFloat("bidprice"));
				bid.setMaxBid(rs.getFloat("maximumbid"));
				bid.setBidTime(rs.getString("bidtime"));
				bids.add(bid);
			}
		}
		
		catch(Exception e) {
			System.out.println(e);
		}
		
		/*Sample data ends*/
		
		return bids;
	}

	public List<Bid> getAuctionHistory(String customerID) {
		
		List<Bid> bids = new ArrayList<Bid>();

		/*
		 * The students code to fetch data from the database
		 * Each record is required to be encapsulated as a "Bid" class object and added to the "bids" ArrayList
		 * customerID, which is the Customer's ID, is given as method parameter
		 * Query to get the bid history of all the auctions in which a customer participated, indicated by customerID, must be implemented
		 */

		/*Sample data begins*/
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "Videogames123456789!");
			Statement st = con.createStatement();
			String bidhistoryQuery = ("SELECT * FROM bids WHERE customerid = " + customerID);
			ResultSet rs = st.executeQuery(bidhistoryQuery);
			while(rs.next()) {
				Bid bid = new Bid();
				bid.setCustomerID(rs.getString("customerid"));
				bid.setAuctionID(rs.getInt("auctionid"));
				bid.setBidPrice(rs.getFloat("bidprice"));
				bid.setMaxBid(rs.getFloat("maximumbid"));
				bid.setBidTime(rs.getString("bidtime"));
				bids.add(bid);
			}
		}
		
		catch(Exception e) {
			System.out.println(e);
		}
		
		/*Sample data ends*/
		
		return bids;
	}

	public Bid submitBid(String auctionID, String itemID, Float currentBid, Float maxBid, String customerID) {
		
		Bid bid = new Bid();

		/*
		 * The students code to insert data in the database
		 * auctionID, which is the Auction's ID for which the bid is submitted, is given as method parameter
		 * itemID, which is the Item's ID for which the bid is submitted, is given as method parameter
		 * currentBid, which is the Customer's current bid, is given as method parameter
		 * maxBid, which is the Customer's maximum bid for the item, is given as method parameter
		 * customerID, which is the Customer's ID, is given as method parameter
		 * Query to submit a bid by a customer, indicated by customerID, must be implemented
		 * After inserting the bid data, return the bid details encapsulated in "bid" object
		 */

		/*Sample data begins*/
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "Videogames123456789!");
			Statement st = con.createStatement();
			String addBidQuery = "INSERT INTO bid (customerid, auctionid, bidtime, bidprice, maximumbid,)\r\n"
					+ "VALUES ( " + bid.getCustomerID() + ", " + bid.getAuctionID() + ", " + bid.getBidTime() + ", " + bid.getBidPrice() + ", " + bid.getMaxBid() +");";
			ResultSet rs = st.executeQuery(addBidQuery);
			String getBidItemQuery = "SELECT itemname FROM auction WHERE auctionid = " + auctionID;
			bid.setAuctionID(Integer.valueOf(auctionID));
			bid.setCustomerID(customerID);
			bid.setBidPrice(currentBid);
			bid.setMaxBid(maxBid);
			return bid;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		
		/*Sample data ends*/
		
		return bid;
	}

	public List<Bid> getSalesListing(String searchKeyword) {
		
		List<Bid> bids = new ArrayList<Bid>();

		/*
		 * The students code to fetch data from the database
		 * Each record is required to be encapsulated as a "Bid" class object and added to the "bids" ArrayList
		 * searchKeyword, which is the search parameter, is given as method parameter
		 * Query to  produce a list of sales by item name or by customer name must be implemented
		 * The item name or the customer name can be searched with the provided searchKeyword
		 */

		/*Sample data begins*/
		
		
		try { //First we do a search for item if rs is null then return based on customer name
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "Videogames123456789!");
			Statement st = con.createStatement(); //Bid does not have customer name so we have to grab the customer id first
			String getCustomerIDforBidQuery = "select * from customer where Firstname like \'%" + searchKeyword + "%\'" + "or lastName like \'%" + searchKeyword + "%\'";
			ResultSet r1 = st.executeQuery(getCustomerIDforBidQuery);
			String custID = r1.getString("customerid"); //if its null it will just get by itemname
			String 	getSalesListingQuery = "select * from bid where itemname like \'%" + searchKeyword + "%\'" + "or customerid like \'%" + custID + "%\'";
			ResultSet r2 = st.executeQuery(getSalesListingQuery);
			while(r2.next()) {
				Bid bid = new Bid();
				bid.setCustomerID(r2.getString("customerid"));
				bid.setAuctionID(r2.getInt("auctionid"));
				bid.setBidPrice(r2.getFloat("bidprice"));
				bid.setMaxBid(r2.getFloat("maximumbid"));
				bid.setBidTime(r2.getString("bidtime"));
				bids.add(bid);
			}
			
			
		}
	
		catch(Exception e) {
			System.out.println(e);
		}
		/*Sample data ends*/
		
		return bids;
	}

}
