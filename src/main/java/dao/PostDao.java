package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Employee;
import model.Item;
import model.Post;

public class PostDao {

	
	public List<Item> getSalesReport(Post post) {

		/*
		 * The students code to fetch data from the database will be written here
		 * Each record is required to be encapsulated as a "Item" class object and added to the "items" List
		 * Query to get sales report for a particular month must be implemented
		 * post, which has details about the month and year for which the sales report is to be generated, is given as method parameter
		 * The month and year are in the format "month-year", e.g. "10-2018" and stored in the expireDate attribute of post object
		 * The month and year can be accessed by getter method, i.e., post.getExpireDate()
		 */

		String postDate = post.getExpireDate();

		String[] split = postDate.split("-");

		String formattedDate = split[1] + split[0];

		List<Item> items = new ArrayList<Item>();

		/*Sample data begins*/
		try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/auction", "root", "Videogames123456789!");
		Statement st = con.createStatement();
		String getAuctionsReportQuery = "SELECT auctionid\r\n"
		+ "FROM post\r\n"
		+ "WHERE '" + formattedDate + "' = (\r\n"
		+ "    SELECT EXTRACT(YEAR_MONTH FROM expire_date)\r\n"
		+ ")";
		ResultSet rs1 = st.executeQuery(getAuctionsReportQuery);
		while (rs1.next()) {
		String getAuctionsReportQuery2 = "SELECT * FROM auction WHERE auctionid = '" + rs1.getInt("auctionid") + "'";
		ResultSet rs2 = st.executeQuery(getAuctionsReportQuery2);
		while (rs2.next()) {
		String getSoldItemsQuery = "SELECT * FROM items WHERE itemID = '" + rs2.getInt("itemID") + "'";
		ResultSet rs3 = st.executeQuery(getSoldItemsQuery);
		while (rs3.next()) {
		Item item = new Item();
		item.setDescription(rs3.getString("description"));
		item.setItemID(rs3.getInt("itemID"));
		item.setName(rs3.getString("name"));
		item.setNumCopies(rs3.getInt("numCopies"));
		item.setSoldPrice(rs3.getInt("soldPrice"));
		item.setType(rs3.getString("type"));
		item.setYearManufactured(rs3.getInt("yearManufactured"));
		items.add(item);
		}
		}

		}

		} catch (Exception e) {
		System.out.println(e);
		}

		/*Sample data ends*/


		return items;

		}
}	