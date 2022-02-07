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
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/auction", "root", "Videogames123456789!");
		Statement st = con.createStatement();
		String getAuctionsQuery = "SELECT * FROM auction";
		ResultSet rs = st.executeQuery(getAuctionsQuery);
		while(rs.next()) {
			Auction auction = new Auction();
			auction.setAuctionID(rs.getInt("auctionid"));
			auction.setBidIncrement(rs.getFloat("bidincrement"));
			auction.setMinimumBid(rs.getFloat("minimumbid"));
			auction.setCopiesSold(rs.getInt("copiessold"));
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
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/auction", "root", "Videogames123456789!");
			Statement st = con.createStatement();
			String getAuctioncustQuery = "SELECT * FROM auction WHERE customerid = " + customerID;
			ResultSet rs = st.executeQuery(getAuctioncustQuery);
			while(rs.next()) {
				Auction auction = new Auction();
				auction.setAuctionID(rs.getInt("auctionid"));
				auction.setBidIncrement(rs.getFloat("bidincrement"));
				auction.setMinimumBid(rs.getFloat("minimumbid"));
				auction.setCopiesSold(rs.getInt("copiessold"));
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
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/auction", "root", "Videogames123456789!");
			Statement st = con.createStatement();
			String auctionQuery = "SELECT a.*\r\n"
					+ "FROM auction a\r\n"
					+ "INNER JOIN employee e on a.monitor = e.employeeid\r\n"
					+ "WHERE e.email = 'email'";
			ResultSet rs = st.executeQuery(auctionQuery);
			
			while(rs.next()) {
				
				Auction auction = new Auction();
				auction.setAuctionID(rs.getInt("auctionid"));
				auction.setBidIncrement(rs.getFloat("bidincrement"));
				auction.setMinimumBid(rs.getFloat("minimumbid"));
				auction.setCopiesSold(rs.getInt("copiessold"));
				auction.setItemID(rs.getInt("itemid"));
				auction.setClosingBid(rs.getInt("closingbid"));
				auction.setCurrentBid(rs.getInt("currentbid"));
				auction.setCurrentHighBid(rs.getInt("currenthighbid"));
				auction.setReserve(rs.getInt("reserve"));
				auctions.add(auction);
				
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		return auctions;

		
	}

	public String recordSale(String auctionID) {
		/*
		 * The students code to update data in the database will be written here
		 * Query to record a sale, indicated by the auction ID, should be implemented
		 * auctionID is the Auction's ID, given as method parameter
		 * The method should return a "success" string if the update is successful, else return "failure"
		 */
		
		int currentHighBid = 0;
		int itemID = 0;
		int auctionIDInt = Integer.parseInt(auctionID);
		
	// FIRST GET THE CURRENT HIGH BID 
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/auction", "root", "Videogames123456789!");
			Statement st = con.createStatement();
			String auctionQuery = "SELECT *\r\n"
					+ "FROM auction a\r\n"
					+ "WHERE a.auctionid = " + auctionIDInt;
			ResultSet rs = st.executeQuery(auctionQuery);
			
			if(rs.next()) {
				currentHighBid = rs.getInt("currenthighbid");
				itemID = rs.getInt("itemid");
			}
			
			//UPDATE AUCTION
			String isAuctionUpdateSuccess = updateAuctionSold(st, currentHighBid, auctionIDInt);
			if (isAuctionUpdateSuccess == "failure")
				return "failure";
			
			// UPDATE ITEMS
			String isItemUpdateSuccess = updateItemSold(st, currentHighBid, itemID);
			if (isAuctionUpdateSuccess == "failure")
				return "failure";
			
			// UPDATE BID
			String isBidUpdateSuccess = updateBidSold(st, currentHighBid, auctionIDInt);
			if (isAuctionUpdateSuccess == "failure")
				return "failure";
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		
		/* Sample data begins */
		return "success";
		/* Sample data ends */
	}
	
	// HELPER METHOD TO UPDATE AUCTION TABLE TO MAKE IT AS SOLD
	private String updateAuctionSold(Statement st, int soldPrice, int auctionID) {
		try {
			String updateAuctionQuery = "UPDATE auction\r\n"
					+ "SET copiessold = 1, closingBid = " + soldPrice + "\r\n"
					+ "WHERE auctionid = " + auctionID;
			st.executeUpdate(updateAuctionQuery);
		} catch(Exception e) {
			System.out.println(e);
			return "failure";
		}
		return "success";
	}
	
	private String updateItemSold(Statement st, int soldPrice, int itemID) {
		try {
			String updateItemQuery = "UPDATE items\r\n"
					+ "SET soldprice = " + soldPrice + "\r\n"
					+ "WHERE itemid = " + itemID;
			st.executeUpdate(updateItemQuery);
		} catch(Exception e) {
			System.out.println(e);
			return "failure";
		}
		return "success";
	}
	
	private String updateBidSold(Statement st, int soldPrice, int auctionID) {
		try {
			String updateBidQuery = "UPDATE bid\r\n"
					+ "SET sold = 1\r\n"
					+ "WHERE auctionid = " + auctionID;
			st.executeUpdate(updateBidQuery);
		} catch(Exception e) {
			System.out.println(e);
			return "failure";
		}
		return "success";
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
		 * 
		 * Query to get data about auction indicated by auctionID and itemID should be implemented
		 * auctionID is the Auction's ID, given as method parameter
		 * itemID is the Item's ID, given as method parameter
		 * The customer details must include details about the current winner of the auction
		 * The bid details must include details about the current highest bid
		 * The item details must include details about the item, indicated by itemID
		 * The auction details must include details about the item, indicated by auctionID
		 * All the objects must be added in the "output" list and returned
		 */
		
		int itemIDInt;
		try {
			itemIDInt = Integer.parseInt(itemID);
		} catch(Exception e) {
			itemIDInt = 0;
		}
		
		int auctionIDInt = Integer.parseInt(auctionID);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/auction", "root", "Videogames123456789!");
			Statement st = con.createStatement();
			
			String auctionBidQuery = "SELECT a.minimumbid, a.bidincrement, a.currentbid, a.currenthighbid, b.customerid, b.bidprice, i.description, i.type, i.name, c.firstname, c.lastname\r\n"
					+ "FROM auction a\r\n"
					+ "INNER JOIN bid b ON a.auctionid = b.auctionid\r\n"
					+ "INNER JOIN customer c on b.currentwinner = c.customerid\r\n"
					+ "INNER JOIN items i on a.itemID = i.itemID\r\n"
					+ "WHERE a.auctionid = " + auctionIDInt + "\r\n"
					+ "AND i.itemID = " + itemIDInt;
			ResultSet rs = st.executeQuery(auctionBidQuery);
			
			
			while(rs.next()) {
				
				item.setItemID(itemIDInt);
				item.setDescription(rs.getString("description"));
				item.setType(rs.getString("type"));
				item.setName(rs.getString("name"));
				
				bid.setCustomerID(rs.getString("customerid"));
				bid.setBidPrice(rs.getInt("bidprice"));
				
				customer.setCustomerID(rs.getString("customerid"));
				customer.setFirstName(rs.getString("firstname"));
				customer.setLastName(rs.getString("lastname"));
				
				auction.setAuctionID(auctionIDInt);
				auction.setBidIncrement(rs.getFloat("bidincrement"));
				auction.setMinimumBid(rs.getFloat("minimumbid"));
				auction.setCurrentBid(rs.getInt("currentbid"));
				auction.setCurrentHighBid(rs.getInt("currenthighbid"));
				
				output.add(item);
				output.add(bid);
				output.add(auction);
				output.add(customer);
				
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	
		
		
		return output;
}
}