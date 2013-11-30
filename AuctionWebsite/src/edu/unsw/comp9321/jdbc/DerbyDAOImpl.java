package edu.unsw.comp9321.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import edu.unsw.comp9321.common.ServiceLocatorException;

public class DerbyDAOImpl implements AuctionDAO {

	static Logger logger = Logger.getLogger(DerbyDAOImpl.class.getName());
	private Connection connection;
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

	public DerbyDAOImpl() throws ServiceLocatorException, SQLException {
		connection = DBConnectionFactory.getConnection();
		logger.info("Got connection");
	}

	@Override
	public void storeItem(ItemDTO item) {
		Statement stmnt = null;
		try {
			stmnt = connection.createStatement();
			String sqlString = "INSERT INTO TBL_ITEMS(USERNAME, TITLE, CATEGORY, PICTURE, DESCRIPTON, POSTAGEDETAILS, RESERVEPRICE, BIDDINGSTARTPRICE, BIDDINGINCREMENTS, TIMEFORBIDDING, ADDEDTIMEFORBIDDING) VALUES ("
					+ "'"
					+ item.getUserName()
					+ "',"
					+ "'"
					+ item.getTitle()
					+ "',"
					+ "'"
					+ item.getCategory()
					+ "',"
					+ "'"
					+ item.getPicture()
					+ "', "
					+ "'"
					+ item.getDescription()
					+ "',"
					+ "'"
					+ item.getPostageDetail()
					+ "',"
					+ item.getReservePrice()
					+ ","
					+ item.getBiddingStartPrice()
					+ ","
					+ item.getBiddingIncrement()
					+ ","
					+ item.getBiddingTime()
					+ "," + item.getCurrentTime() + ")";
			System.out.println(sqlString);
			int result = stmnt.executeUpdate(sqlString);
			logger.info("Statement successfully executed " + result);
			stmnt.close();
			logger.info("sql string is " + sqlString);
		} catch (Exception e) {
			logger.severe("Unable to store a user! ");
			e.printStackTrace();
		}

	}
	@Override
	public void storeUserTemp(UserDTO user, int val) {
		// TODO Auto-generated method stub
		Statement stmnt = null;
		try {
			stmnt = connection.createStatement();
			// INSERT INTO TBL_USERS VALUES ('sercan', 'berkay',
			// 'sercan@gmail.com', 'sj', 'sercan','bilge','2000-03-18','19 Mc
			// Cabe Street', '1234567-7');
			/*
			 * USERNAME VARCHAR(20) PRIMARY KEY, PASSWORD VARCHAR(20), EMAIL_ADD
			 * VARCHAR(40), NICKNAME VARCHAR(40), FIRSTNAME VARCHAR(50),
			 * LASTNAME VARCHAR(30), YEAROFBIRTH DATE, FULLADDRESS VARCHAR(200),
			 * CREDITCARDNUMBER VARCHAR(50)
			 */
			System.out.println("Userr ccn = " + user.getCreditcardnumber());
			String sqlString = "INSERT INTO TBL_USERS_TEMP (USERNAME, VAL, PASSWORD, EMAIL_ADD, NICKNAME, FIRSTNAME, LASTNAME, YEAROFBIRTH, FULLADDRESS, CREDITCARDNUMBER) VALUES("
					+ "'"
					+ user.getUsername()
					+ "',"
					+ "'"
					+ val
					+"','"
					+ user.getPassword()
					+ "',"
					+ "'"
					+ user.getEmailadd()
					+ "', "
					+ "'"
					+ user.getNickname()
					+ "',"
					+ "'"
					+ user.getFirstname()
					+ "',"
					+ "'"
					+ user.getLastname()
					+ "',"
					+ "'"
					+ user.getYearofbirth()
					+ "',"
					+ "'"
					+ user.getFulladdress()
					+ "',"
					+ "'"
					+ user.getCreditcardnumber() + "')";
			int result = stmnt.executeUpdate(sqlString);
			logger.info("Statement successfully executed " + result);
			stmnt.close();
			logger.info("sql string is " + sqlString);
		} catch (Exception e) {
			logger.severe("Unable to store a user temp! ");
			e.printStackTrace();
		}

	}
	
	
	
	
	
	
	@Override
	public void storeUser(UserDTO user) {
		Statement stmnt = null;
		try {
			stmnt = connection.createStatement();
			// INSERT INTO TBL_USERS VALUES ('sercan', 'berkay',
			// 'sercan@gmail.com', 'sj', 'sercan','bilge','2000-03-18','19 Mc
			// Cabe Street', '1234567-7');
			/*
			 * USERNAME VARCHAR(20) PRIMARY KEY, PASSWORD VARCHAR(20), EMAIL_ADD
			 * VARCHAR(40), NICKNAME VARCHAR(40), FIRSTNAME VARCHAR(50),
			 * LASTNAME VARCHAR(30), YEAROFBIRTH DATE, FULLADDRESS VARCHAR(200),
			 * CREDITCARDNUMBER VARCHAR(50)
			 */
			System.out.println("Userr ccn = " + user.getCreditcardnumber());
			String sqlString = "INSERT INTO TBL_USERS (USERNAME, PASSWORD, EMAIL_ADD, NICKNAME, FIRSTNAME, LASTNAME, YEAROFBIRTH, FULLADDRESS, CREDITCARDNUMBER) VALUES("
					+ "'"
					+ user.getUsername()
					+ "',"
					+ "'"
					+ user.getPassword()
					+ "',"
					+ "'"
					+ user.getEmailadd()
					+ "', "
					+ "'"
					+ user.getNickname()
					+ "',"
					+ "'"
					+ user.getFirstname()
					+ "',"
					+ "'"
					+ user.getLastname()
					+ "',"
					+ "'"
					+ user.getYearofbirth()
					+ "',"
					+ "'"
					+ user.getFulladdress()
					+ "',"
					+ "'"
					+ user.getCreditcardnumber() + "')";
			int result = stmnt.executeUpdate(sqlString);
			logger.info("Statement successfully executed " + result);
			stmnt.close();
			logger.info("sql string is " + sqlString);
		} catch (Exception e) {
			logger.severe("Unable to store a user! ");
			e.printStackTrace();
		}

	}

	@Override
	public List<UserDTO> getAllUsers() {
		return null;
	}

	@Override
	public UserDTO getUserDetails(String userName) {

		UserDTO user = null;
		try {
			Statement stmnt = connection.createStatement();
			ResultSet results = stmnt
					.executeQuery("SELECT * FROM TBL_USERS WHERE USERNAME = '"
							+ userName + "'");
			while (results.next()) {
				String Usern = results.getString("USERNAME");
				String passw = results.getString("PASSWORD");
				String eMail = results.getString("EMAIL_ADD");
				String nickName = results.getString("NICKNAME");
				String firstName = results.getString("FIRSTNAME");
				String lastName = results.getString("LASTNAME");
				String yob = results.getString("YEAROFBIRTH");
				String fullAdd = results.getString("FULLADDRESS");
				String ccn = results.getString("CREDITCARDNUMBER");
				user = new UserDTO(Usern, passw, eMail, nickName, firstName,
						lastName, yob, fullAdd, ccn);
			}
			results.close();
			stmnt.close();
			logger.info("Fetched comments");
		} catch (Exception e) {
			logger.severe("Failed to get comments " + e.getStackTrace());
		}
		return user;
	}

	@Override
	public List<ItemDTO> getAllItems() {
		List<ItemDTO> itemList = new ArrayList<ItemDTO>();
		try {
			Statement stmnt = connection.createStatement();
			ResultSet results = stmnt.executeQuery("SELECT * FROM TBL_ITEMS");

			while (results.next()) {
				String uname = results.getString("USERNAME");
				String title = results.getString("TITLE");
				String category = results.getString("CATEGORY");
				String pic = results.getString("PICTURE");
				String desc = results.getString("DESCRIPTON");
				String post = results.getString("POSTAGEDETAILS");
				String resPrice = results.getString("RESERVEPRICE");
				String biddingStart = results.getString("BIDDINGSTARTPRICE");
				String biddingInc = results.getString("BIDDINGINCREMENTS");
				String timeForBid = results.getString("TIMEFORBIDDING");
				String timeForAdd = results.getString("ADDEDTIMEFORBIDDING");
				ItemDTO itemDTO = new ItemDTO(uname, title, category, pic,
						desc, post, Integer.parseInt(resPrice),
						Integer.parseInt(biddingStart),
						Integer.parseInt(biddingInc),
						Integer.parseInt(timeForBid),
						Integer.parseInt(timeForAdd));
				itemList.add(itemDTO);
			}
			results.close();
			stmnt.close();
			logger.info("Fetched comments");
		} catch (Exception e) {
			logger.severe("Failed to get comments " + e.getStackTrace());
		}

		return itemList;
	}

	@Override
	public void updateUserDetails(UserDTO user) {

		Statement stmnt = null;
		try {
			stmnt = connection.createStatement();
			String sqlString = "UPDATE TBL_USERS" + " " + "SET USERNAME='"
					+ user.getUsername() + "'," + "PASSWORD='"
					+ user.getPassword() + "'," + "EMAIL_ADD='"
					+ user.getEmailadd() + "', " + "NICKNAME='"
					+ user.getNickname() + "'," + "FIRSTNAME='"
					+ user.getFirstname() + "'," + "LASTNAME='"
					+ user.getLastname() + "'," + "YEAROFBIRTH='"
					+ user.getYearofbirth() + "'," + "FULLADDRESS='"
					+ user.getFulladdress() + "'," + "CREDITCARDNUMBER='"
					+ user.getCreditcardnumber() + "'" + " WHERE USERNAME='"
					+ user.getUsername() + "'";
			int result = stmnt.executeUpdate(sqlString);
			logger.info("Statement successfully executed " + result);
			stmnt.close();
			logger.info("sql string is " + sqlString);
		} catch (Exception e) {
			logger.severe("Unable to store a user! ");
			e.printStackTrace();
		}

	}

	@Override
	public ItemDTO getItemDetails(String itemTitle) {

		ItemDTO item = null;
		try {
			Statement stmnt = connection.createStatement();
			ResultSet results = stmnt
					.executeQuery("SELECT * FROM TBL_ITEMS WHERE TITLE = '"
							+ itemTitle + "'");
			while (results.next()) {
				String usrnName = results.getString("USERNAME");

				String ttle = results.getString("TITLE");

				String c = results.getString("CATEGORY");

				String p = results.getString("PICTURE");

				String d = results.getString("DESCRIPTON");

				String post = results.getString("POSTAGEDETAILS");

				String rp = results.getString("RESERVEPRICE");// DO NOT SHOW

				String bst = results.getString("BIDDINGSTARTPRICE");

				String binc = results.getString("BIDDINGINCREMENTS");

				String timeBid = results.getString("TIMEFORBIDDING");

				String addedTime = results.getString("ADDEDTIMEFORBIDDING");
				item = new ItemDTO(usrnName, ttle, c, p, d, post,
						Integer.parseInt(rp), Integer.parseInt(bst),
						Integer.parseInt(binc), Integer.parseInt(timeBid),
						Integer.parseInt(addedTime));
			}
			results.close();
			stmnt.close();
			logger.info("fetched item");
		} catch (Exception e) {
			logger.severe("Failed to get item details " + e.getStackTrace());
		}
		return item;
	}

	@Override
	public void storeBid(BidDTO bid) {
		Statement stmnt = null;
		try {
			/*
			 * INSERT INTO TBL_BIDS(USERNAME_BID, TITLE_BID, AMOUNT_BID) VALUES
			 * ('yunus', 'Playstation',13);
			 */
			stmnt = connection.createStatement();
			String sqlString = "INSERT INTO TBL_BIDS(USERNAME_BID, TITLE_BID, AMOUNT_BID) VALUES ('"
					+ bid.getUserName()
					+ "', '"
					+ bid.getItemTitle()
					+ "',"
					+ bid.getBidVal() + ")";
			int result = stmnt.executeUpdate(sqlString);
			logger.info("Statement successfully executed " + result);
			stmnt.close();
			logger.info("sql string is from bid : " + sqlString);
		} catch (Exception e) {
			logger.severe("Unable to store a bid! ");
			e.printStackTrace();

		}

	}

	@Override
	public List<BidDTO> getAllBidsForItem(String itemTitle) {
		List<BidDTO> bidList = new ArrayList<BidDTO>();
		try {
			Statement stmnt = connection.createStatement();
			String query = "SELECT * FROM TBL_BIDS WHERE TITLE_BID='"+itemTitle+"'";
			ResultSet results = stmnt.executeQuery(query);
			System.out.println("smtnt = " +query);
			while (results.next()) {
				String uName = results.getString("USERNAME_BID");
				String title = results.getString("TITLE_BID");
				String bidVal = results.getString("AMOUNT_BID");
				BidDTO bid = new BidDTO(uName, title, bidVal);
				bidList.add(bid);
			}
			results.close();
			stmnt.close();
			logger.info("Fetched comments");
		}catch(Exception e) {
			logger.severe("Failed to get comments " + e.getStackTrace());			
		}
		return bidList;
	}

	@Override
	public void deleteUserFromUsersTable(String userName) {
		Statement stmnt = null;
		try {
			stmnt = connection.createStatement();
			String sqlString = "DELETE FROM TBL_USERS WHERE USERNAME='" + userName + "'";
			int result = stmnt.executeUpdate(sqlString);
			logger.info("Statement successfully executed " + result);
			stmnt.close();
			logger.info("sql string is from delete user : " + sqlString);
		} catch (Exception e) {
			logger.severe("Unable to delete user! ");
			e.printStackTrace();

		}
	}
	@Override
	public void deleteUserFromUsersTempTable(String userName) {
		Statement stmnt = null;
		try {
			stmnt = connection.createStatement();
			String sqlString = "DELETE FROM TBL_USERS_TEMP WHERE USERNAME='" + userName + "'";
			int result = stmnt.executeUpdate(sqlString);
			logger.info("Statement successfully executed " + result);
			stmnt.close();
			logger.info("sql string is from delete user temp: " + sqlString);
		} catch (Exception e) {
			logger.severe("Unable to delete user! ");
			e.printStackTrace();

		}
		
	}

	@Override
	public void deleteItemAccordingToUserName(String userName) {
		Statement stmnt = null;
		try {
			stmnt = connection.createStatement();
			String sqlString = "DELETE FROM TBL_ITEMS WHERE USERNAME='" + userName + "'";
			int result = stmnt.executeUpdate(sqlString);
			logger.info("Statement successfully executed " + result);
			stmnt.close();
			logger.info("sql string is from delete item : " + sqlString);
		} catch (Exception e) {
			logger.severe("Unable to delete item of user! ");
			e.printStackTrace();

		}
		
	}

	@Override
	public void deleteBidsAccordingToUserName(String userName) {
		Statement stmnt = null;
		try {
			stmnt = connection.createStatement();
			String sqlString = "DELETE FROM TBL_BIDS WHERE USERNAME_BID='" + userName + "'";
			int result = stmnt.executeUpdate(sqlString);
			logger.info("Statement successfully executed " + result);
			stmnt.close();
			logger.info("sql string is from delete item : " + sqlString);
		} catch (Exception e) {
			logger.severe("Unable to delete item of user! ");
			e.printStackTrace();

		}

		
	}

	@Override
	public void deleteItemsAccordingToItemName(String itemName) {
		Statement stmnt = null;
		try {
			stmnt = connection.createStatement();
			String sqlString = "DELETE FROM TBL_ITEMS WHERE TITLE='" + itemName + "'";
			int result = stmnt.executeUpdate(sqlString);
			logger.info("Statement successfully executed " + result);
			stmnt.close();
			logger.info("sql string is from delete item : " + sqlString);
		} catch (Exception e) {
			logger.severe("Unable to delete item! ");
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteBidsAccordingToItemName(String itemName) {
		Statement stmnt = null;
		try {
			stmnt = connection.createStatement();
			String sqlString = "DELETE FROM TBL_BIDS WHERE TITLE_BID='" + itemName + "'";
			int result = stmnt.executeUpdate(sqlString);
			logger.info("Statement successfully executed " + result);
			stmnt.close();
			logger.info("sql string is from delete item : " + sqlString);
		} catch (Exception e) {
			logger.severe("Unable to delete item from bidd! ");
			e.printStackTrace();
		}
		
	}

	@Override
	public UserDTO getUserDetailsTemp(int val) {

		UserDTO user = null;
		try {
			Statement stmnt = connection.createStatement();
			ResultSet results = stmnt
					.executeQuery("SELECT * FROM TBL_USERS_TEMP WHERE VAL = '"
							+ val + "'");
			while (results.next()) {
				String Usern = results.getString("USERNAME");
				String passw = results.getString("PASSWORD");
				String eMail = results.getString("EMAIL_ADD");
				String nickName = results.getString("NICKNAME");
				String firstName = results.getString("FIRSTNAME");
				String lastName = results.getString("LASTNAME");
				String yob = results.getString("YEAROFBIRTH");
				String fullAdd = results.getString("FULLADDRESS");
				String ccn = results.getString("CREDITCARDNUMBER");
				user = new UserDTO(Usern, passw, eMail, nickName, firstName,
						lastName, yob, fullAdd, ccn);
			}
			results.close();
			stmnt.close();
			logger.info("Fetched comments");
		} catch (Exception e) {
			logger.severe("Failed to get comments " + e.getStackTrace());
		}
		return user;

	}

	
}
