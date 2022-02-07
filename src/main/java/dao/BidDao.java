package dao;

import java.util.ArrayList;
import java.util.List;

import model.Bid;
import model.Customer;

import java.sql.*;
public class BidDao {

	// COMPLETE
		public List<Bid> getBidHistory(String auctionID) {
			
			List<Bid> bids = new ArrayList<Bid>();

			/*
			 * The students code to fetch data from the database
			 * Each record is required to be encapsulated as a "Bid" class object and added to the "bids" ArrayList
			 * auctionID, which is the Auction's ID, is given as method parameter
			 * Query to get the bid history of an auction, indicated by auctionID, must be implemented
			 */

			/*Sample data begins*/
//			for (int i = 0; i < 10; i++) {
//				Bid bid = new Bid();
//				bid.setAuctionID(123);
//				bid.setCustomerID("123-12-1234");
//				bid.setBidTime("2008-12-11");
//				bid.setBidPrice(100);
//				bids.add(bid);			
//			}
			
			// CONVERT auctionID STRING -> INT
			int auctionIDInt;
			try {
				auctionIDInt = Integer.parseInt(auctionID);
			} catch(Exception e) {
				auctionIDInt = 0;
			}
			
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/auction", "root", "Videogames123456789!");
				Statement st = con.createStatement();
				String bidHistoryQuery = "SELECT * FROM bid WHERE auctionId = " + auctionIDInt;
				ResultSet rs = st.executeQuery(bidHistoryQuery);
				
				while(rs.next()) {
					
					Bid bid = new Bid();
					bid.setAuctionID(auctionIDInt);
					bid.setCustomerID(rs.getString("customerID"));
					bid.setBidTime(rs.getString("bidTime"));
					bid.setBidPrice(rs.getFloat("bidPrice"));
					bids.add(bid);
				}
				
			} catch (Exception e) {
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
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/auction", "root", "Videogames123456789!");
			Statement st = con.createStatement();
			String bidhistoryQuery = ("SELECT * FROM bid WHERE customerid = '" + customerID + "'");
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
//
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
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/auction", "root", "Videogames123456789!");
			Statement st = con.createStatement();
			String addBidQuery = "INSERT INTO bid (customerid, auctionid, bidtime, bidprice, maxbid,)\r\n"
					+ "VALUES ( " + bid.getCustomerID() + ", " + bid.getAuctionID() + ", " + bid.getBidTime() + ", " + bid.getBidPrice() + ", " + bid.getMaxBid() +");";
			int i = st.executeUpdate(addBidQuery);
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
		
		String customerID = "";
		int itemID = 0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/auction", "root", "Videogames123456789!");
			Statement st = con.createStatement();
			
			// QUERY TO GET CUSTOMER BY searchKeyword
			String getCustomerQuery = "SELECT customerID FROM customer WHERE firstName = '" + searchKeyword + "' OR lastName = '" + searchKeyword + "'";
			ResultSet rs = st.executeQuery(getCustomerQuery);
			
			
			
			
			// IF searchKeyword is customer name
			if(rs.next()) {
				customerID = rs.getString("customerID");
				
				String soldBidCustomerQuery = "SELECT bid.*\r\n"
						+ "FROM bid\r\n"
						+ "INNER JOIN post on bid.auctionid = post.auctionid\r\n"
						+ "WHERE post.customerid = '" +  customerID + "'\r\n"
						+ "AND bid.sold = 1";
				
				rs = st.executeQuery(soldBidCustomerQuery);
				while(rs.next()) {
					
					Bid bid = new Bid();
					bid.setAuctionID(rs.getInt("auctionid"));
					bid.setCustomerID(rs.getString("customerid"));
					bid.setBidTime(rs.getString("bidtime"));
					bid.setBidPrice(rs.getFloat("bidprice"));
					bids.add(bid);
				}
				return bids;
				
			} 
			
			// QUERY TO GET ITEM NAME
			String getItemQuery = "SELECT itemID FROM items WHERE name = '" + searchKeyword + "'";
			ResultSet rs2 = st.executeQuery(getItemQuery);
			if(rs2.next()) {
				itemID = rs2.getInt("itemID");
				
				String soldBidItemQuery = "SELECT bid.*\r\n"
						+ "FROM bid\r\n"
						+ "INNER JOIN post on bid.auctionid = post.auctionid\r\n"
						+ "INNER JOIN auction on bid.auctionid = auction.auction\r\n"
						+ "WHERE auction.itemid = " + itemID + "\r\n"
						+ "AND bid.sold = 1";
				
				rs2 = st.executeQuery(soldBidItemQuery);
						while(rs2.next()) {
							
							Bid bid = new Bid();
							bid.setAuctionID(rs2.getInt("auctionID"));
							bid.setCustomerID(rs2.getString("customerID"));
							bid.setBidTime(rs2.getString("bidTime"));
							bid.setBidPrice(rs2.getFloat("bidPrice"));
							bids.add(bid);
						}
			}
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return bids;
	}
}
