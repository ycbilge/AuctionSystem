package edu.unsw.comp9321.logic;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.unsw.comp9321.bean.UserBean;
import edu.unsw.comp9321.common.ServiceLocatorException;
import edu.unsw.comp9321.jdbc.AuctionDAO;
import edu.unsw.comp9321.jdbc.BidDTO;
import edu.unsw.comp9321.jdbc.DerbyDAOImpl;
import edu.unsw.comp9321.jdbc.ItemDTO;
import edu.unsw.comp9321.jdbc.UserDTO;
import edu.unsw.comp9321.mail.CongratsMailSender;
import edu.unsw.comp9321.mail.MailSender;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean debug;
	private MailSender mailSender;
	private AuctionDAO auction;
	// bu logger da hata olabilir
	static Logger logger = Logger.getLogger(Controller.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		debug = true;
		mailSender = new MailSender();
		try {
			auction = new DerbyDAOImpl();
		} catch (ServiceLocatorException e) {
			logger.severe("Trouble connecting to database " + e.getStackTrace());
			// throw new ServletException();
		} catch (SQLException e) {
			logger.severe("Trouble connecting to database " + e.getStackTrace());
			// throw new ServletException();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			processRequest(request, response);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			processRequest(request, response);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			AddressException, MessagingException {
		String action = request.getParameter("action");
		String forwardPage = "error.jsp";
		// String email = "";
		if (debug) {
			System.out.println(("I am at processRequest"));
			System.out.println("Action = " + action);
			System.out
					.println("Username = " + request.getParameter("username"));
			System.out.println("Pass = " + request.getParameter("password"));
		}
		if (action.equals("login")) {
			String userName = request.getParameter("username");
			String password = request.getParameter("password");
			request.setAttribute("usern", userName);
			forwardPage = processLogin(request, response, userName, password);
		} else if (action.equals("Go Back to Login")) {
			forwardPage = "login.jsp";
		} else if (action.equals("register")) {
			forwardPage = "register1.jsp";
		} else if (action.equals("logout")) {
			forwardPage = "enough.jsp";
		} else if (action.equals("register1")) {
			String userName = request.getParameter("Rusername");
			String password = request.getParameter("Rpassword");
			String email = request.getParameter("Remail");

			forwardPage = processRegister(userName, password, email);
			if (forwardPage.equals("register2.jsp")) {
				request.setAttribute("uName", userName);
				request.setAttribute("eMail", email);
				request.setAttribute("pass", password);
			}
		} else if (action.equals("register2")) {
			String uname = request.getParameter("R2username");
			request.setAttribute("userNN", uname);
			String p = request.getParameter("R2password");
			System.out.println("Password = " + p);
			String emailAdd = request.getParameter("R2email");
			String n = request.getParameter("R2nickname");
			String f = request.getParameter("R2firstname");
			String l = request.getParameter("R2lastname");
			String y = request.getParameter("R2yob");
			String a = request.getParameter("R2address");
			String c = request.getParameter("R2creditCardNumber");
			System.out.println("Mail = " + emailAdd);
			forwardPage = handlePostUser(request, response, emailAdd, uname, p,
					n, f, l, y, a, c);

		} else if (action.equals("registrationCompleted")) {
			String val = request.getParameter("val");
			if (Integer.parseInt(val) == mailSender.getVal()) {
				try {

					UserDTO userdto = auction.getUserDetailsTemp(Integer
							.parseInt(val));

					if (userdto != null) {
						auction.deleteUserFromUsersTempTable(userdto
								.getUsername());
						auction.storeUser(userdto);
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("exception in admin home search user");
				}

				forwardPage = "registrationCompleted.jsp";
			}
		} else if (action.equals("banItem")) {
			System.out.println("itemname = "
					+ request.getParameter("admin_item_title"));
			String itemName = request.getParameter("admin_item_title");
			try {
				auction.deleteBidsAccordingToItemName(itemName);
				auction.deleteItemsAccordingToItemName(itemName);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("exception from ban item");
			}
			forwardPage = "/WEB-INF/restricted/adminHome.jsp";
		} else if (action.equals("banUser")) {
			System.out.println("username = "
					+ request.getParameter("admin_user_fname"));
			String userName = request.getParameter("admin_user_fname");
			try {
				auction.deleteUserFromUsersTable(userName);
				auction.deleteItemAccordingToUserName(userName);
				auction.deleteBidsAccordingToUserName(userName);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("exception from ban user");
			}
			forwardPage = "/WEB-INF/restricted/adminHome.jsp";
		} else if (action.equals("adminHomeSearchItem")) {
			String itemName = request.getParameter("itemName_adminHome");
			try {

				ArrayList<ItemDTO> itemdtoList = (ArrayList<ItemDTO>) auction
						.getAllItems();
				if (itemdtoList != null) {
					ArrayList<ItemDTO> itemsShow = new ArrayList<ItemDTO>();
					for (ItemDTO itemDTO : itemdtoList) {
						if (itemDTO.getTitle().contains(itemName)) {
							itemsShow.add(itemDTO);
						}
						request.setAttribute("itemList", itemsShow);
					}
					request.setAttribute("noItem", false);

				} else {
					request.setAttribute("noItem", true);
				}

				forwardPage = "/WEB-INF/restricted/searchResultForItemAdmin.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("exception in admin home search user");
			}

		} else if (action.equals("backToHome")) {
			forwardPage = "/WEB-INF/restricted/adminHome.jsp";
		} else if (action.equals("adminHomeSearchUser")) {
			String userr = request.getParameter("userName_adminHome");
			// System.out.println("user name from admin home = " + userr);
			try {

				UserDTO userdto = auction.getUserDetails(userr);
				if (userdto != null) {
					System.out.println("usar name = " + userdto.getUsername());
					request.setAttribute("noUser", false);
					request.setAttribute("uname_admin", userdto.getUsername());
					request.setAttribute("ufname_admin", userdto.getFirstname());
					request.setAttribute("ulname_admin", userdto.getLastname());
					request.setAttribute("unname_admin", userdto.getNickname());
				} else {
					request.setAttribute("noUser", true);
				}
				forwardPage = "/WEB-INF/restricted/userSearchResultAdmin.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("exception in admin home search user");
			}
		} else if (action.equals("adminLogin")) {
			String adminName = request.getParameter("adminName");
			String adminPass = request.getParameter("adminPass");

			if (adminName.equals("yunus") && adminPass.equals("pass")) {
				forwardPage = "/WEB-INF/restricted/adminHome.jsp";
			} else {
				forwardPage = "login.jsp";
			}

		} else if (action.equals("updateInfo")) {
			UserBean userBeann = (UserBean) request.getSession().getAttribute(
					"userBean");
			String userName = userBeann.getUsernameBean();
			// String userName = request.getParameter("uNname");
			UserDTO user = auction.getUserDetails(userName);
			request.setAttribute("unameDB", user.getUsername());
			request.setAttribute("passDB", user.getPassword());
			request.setAttribute("eMailDB", user.getEmailadd());
			request.setAttribute("nicnameDB", user.getNickname());
			request.setAttribute("firstnameDB", user.getFirstname());
			request.setAttribute("lastnameDB", user.getLastname());
			request.setAttribute("yobDB", user.getYearofbirth());
			request.setAttribute("fuladdDB", user.getFulladdress());
			request.setAttribute("ccnDB", user.getCreditcardnumber());
			// TODO update when pressed it
			forwardPage = "updateUserInfo.jsp";
		} else if (action.equals("updateUserInfo")) {
			UserDTO userDTO = new UserDTO(request.getParameter("uuiuname"),
					request.getParameter("uuipassword"),
					request.getParameter("uuiemail"),
					request.getParameter("uuinickname"),
					request.getParameter("uuifirstname"),
					request.getParameter("uuilastname"),
					request.getParameter("uuiyob"),
					request.getParameter("uuiaddress"),
					request.getParameter("uuicreditCardNumber"));
			auction.updateUserDetails(userDTO);
			forwardPage = "home.jsp";
		} else if (action.equals("searchItemInDB")) {
			ArrayList<ItemDTO> itemsToBeShown = new ArrayList<ItemDTO>();
			ArrayList<ItemDTO> items = (ArrayList<ItemDTO>) auction
					.getAllItems();
			String textToBeSearched = request.getParameter("itemTitle");
			System.out.println("textde bu var = " + textToBeSearched);
			Calendar now = Calendar.getInstance();
			int hour = now.get(Calendar.HOUR_OF_DAY);
			hour *= 60;
			int min = now.get(Calendar.MINUTE);
			int totalMin = hour + min;

			for (ItemDTO itemDTO : items) {
				System.out.println("title = " + itemDTO.getTitle());
				if (itemDTO.getTitle().contains(textToBeSearched)) {
					int totalTime = itemDTO.getBiddingTime();
					int addedTime = itemDTO.getCurrentTime();// added time
					int diff = totalMin - addedTime;
					int remainingTime = totalTime - diff;
					// System.out.println("bulunan = " + itemDTO.getTitle());
					if (remainingTime > 0)
						itemsToBeShown.add(itemDTO);
				}
			}
			System.out.println("searchItemInDb deyim");
			request.setAttribute("showItems", itemsToBeShown);
			forwardPage = "showResults.jsp";
		} else if (action.equals("selectedItem")) {
			String itemTitleFromResults = request.getParameter("itemId");
			// System.out.println("item title = " + itemTitleFromResults);
			ItemDTO itemdto = auction.getItemDetails(itemTitleFromResults);
			request.setAttribute("itemToBeShown", itemdto);
			UserBean userBeann = (UserBean) request.getSession().getAttribute(
					"userBean");
			System.out.println("addded time = " + itemdto.getBiddingTime());
			Calendar now = Calendar.getInstance();
			int hour = now.get(Calendar.HOUR_OF_DAY);
			hour *= 60;
			int min = now.get(Calendar.MINUTE);
			int totalMin = hour + min;

			int totalTime = itemdto.getBiddingTime();
			int addedTime = itemdto.getCurrentTime();// added time
			int diff = totalMin - addedTime;
			int remainingTime = totalTime - diff;
			System.out.println("title = " + itemTitleFromResults);
			ArrayList<BidDTO> bidList = (ArrayList<BidDTO>) auction
					.getAllBidsForItem(itemTitleFromResults);
			int max = 0;
			String maxBidHolder = "No bid yet!";
			if (bidList != null) {
				System.out.println("null degil");
				for (BidDTO bidDTO : bidList) {
					System.out.println("user = " + bidDTO.getUserName());
					if (Integer.parseInt(bidDTO.getBidVal()) > max) {
						max = Integer.parseInt(bidDTO.getBidVal());
						maxBidHolder = bidDTO.getUserName();
					}
				}
			}
			request.setAttribute("maxBidValue", max);
			request.setAttribute("maxBidHolder", maxBidHolder);
			if (remainingTime < 0) {
				remainingTime = 0;
			}
			if (max > itemdto.getReservePrice()) {
				request.setAttribute("reservePriceBeated", true);
			} else {
				request.setAttribute("reservePriceBeated", false);
			}
			request.setAttribute("remainingTimeToBid", remainingTime);
			if (itemdto.getUserName().equals(userBeann.getUsernameBean())) {
				request.setAttribute("sameUserName", true);
			} else {
				request.setAttribute("sameUserName", false);
			}
			forwardPage = "showAnItem.jsp";
		} else if (action.equals("searchItem")) {
			forwardPage = "searchItem.jsp";
		} else if (action.equals("addBidToItem")) {
			UserBean userBeann = (UserBean) request.getSession().getAttribute(
					"userBean");
			String userName = userBeann.getUsernameBean();
			String bidVal = request.getParameter("bid-Bid");
			String title = request.getParameter("itemTitle-Bid");
			if (!isItInt(bidVal)) {
				request.setAttribute("errorString", "bid value must be integer!");
				forwardPage = "bidError.jsp";
			} else {
				ItemDTO itemdto = auction.getItemDetails(title);
				ArrayList<BidDTO> bidList = (ArrayList<BidDTO>) auction
						.getAllBidsForItem(request
								.getParameter("itemTitle-Bid"));
				int max = 0;

				if (bidList != null) {
					System.out.println("null degil");
					for (BidDTO bidDTO : bidList) {
						System.out.println("user = " + bidDTO.getUserName());
						if (Integer.parseInt(bidDTO.getBidVal()) > max) {
							max = Integer.parseInt(bidDTO.getBidVal());
						}
					}
				}

				if (Integer.parseInt(bidVal) <= max) {
					request.setAttribute("errorString",
							"new bid must be higher than the maximum price up to now");
					forwardPage = "bidError.jsp";
				} else if (Integer.parseInt(bidVal) <= itemdto
						.getBiddingStartPrice()) {
					request.setAttribute("errorString",
							"new bid must be higher than the initial price");
					forwardPage = "bidError.jsp";
				} else {
					forwardPage = handleAddBid(userName, title, bidVal);
				}
				// TODO CHECK first price dan yuksek mi obur price dan yuksek mi
				if (forwardPage.equals("home.jsp")) {
					if (Integer.parseInt(bidVal) > itemdto.getReservePrice()) {
						CongratsMailSender cMailSender = new CongratsMailSender();
						cMailSender.sendMail(userBeann.getEmailBean(), itemdto);
					}
				}
			}
		} else if (action.equals("addItem")) {
			UserBean userBeann = (UserBean) request.getSession().getAttribute(
					"userBean");
			String userName = userBeann.getUsernameBean();
			request.setAttribute("userNN", userName);
			forwardPage = "addItem.jsp";
		} else if (action.equals("adminPage123123")) {
			forwardPage = "/WEB-INF/restricted/adminLogin.jsp";
		} else if (action.equals("addItemToDB")) {
			System.out.println("ItemToDb ye geldim");
			String name = request.getParameter("uNNname");
			// System.out.println("name = " + name);
			String title = request.getParameter("title-addItemForm");
			String category = request.getParameter("category-addItemForm");
			String picture = request.getParameter("picture-addItemForm");
			String description = request
					.getParameter("description-addItemForm");
			String postageDetail = request.getParameter("postage-addItemForm");
			String reservePrice = request
					.getParameter("reservePrice-addItemForm");
			String biddingStartPrice = request
					.getParameter("biddingStart-addItemForm");
			String biddingIncrement = request
					.getParameter("biddingIncrement-addItemForm");
			String biddingTime = request
					.getParameter("biddingTime-addItemForm");
			System.out.println("isitint = " + isItInt(reservePrice));
			if (!isItInt(reservePrice) || !isItInt(biddingStartPrice)
					|| !isItInt(biddingIncrement) || !isItInt(biddingTime)) {
				request.setAttribute("errorString", "reserver price/bidding price/increment/time must be int");
				forwardPage = "bidError.jsp";
			} else {
				Calendar now = Calendar.getInstance();
				int hour = now.get(Calendar.HOUR_OF_DAY);
				hour *= 60;
				int min = now.get(Calendar.MINUTE);
				int currentTime = hour + min;
				if (Integer.parseInt(reservePrice) <= 0
						|| Integer.parseInt(biddingStartPrice) <= 0
						|| Integer.parseInt(biddingIncrement) <= 0) {
					request.setAttribute(
							"errorString",
							"reserve price and/or bidding start/increment price cannot be less than or equal to 0.");
					forwardPage = "bidError.jsp";
				} else {

					// forwardPage = "addSuccessFully.jsp";//home a git button
					forwardPage = handlePostItem(name, title, category,
							picture, description, postageDetail, reservePrice,
							biddingStartPrice, biddingIncrement, biddingTime,
							currentTime);

				}
			}
		}

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/" + forwardPage);
		dispatcher.forward(request, response);
	}

	public static boolean isItInt(String str) {
		return str.matches("-?\\d+(\\.\\d+)?"); // match a number with optional
												// '-' and decimal.
	}

	private String handlePostItem(String name, String t, String c, String p,
			String d, String postag, String r, String biddingStart,
			String biddingInc, String biddingTime, int currentTime) {
		String page = "";
		int reservePrice = Integer.parseInt(r);
		int biddingStartInt = Integer.parseInt(biddingStart);
		int biddingIncInt = Integer.parseInt(biddingInc);
		int biddingTimeInt = Integer.parseInt(biddingTime);
		try {
			ItemDTO item = new ItemDTO(name, t, c, p, d, postag, reservePrice,
					biddingStartInt, biddingIncInt, biddingTimeInt, currentTime);
			auction.storeItem(item);
			page = "home.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			page = "error.jsp";
		}
		return page;
	}

	private String handleAddBid(String u, String t, String bVal) {

		String page = "";
		try {
			BidDTO bid = new BidDTO(u, t, bVal);
			auction.storeBid(bid);
			page = "home.jsp";
		} catch (Exception e) {
			e.printStackTrace();

			page = "error.jsp";
		}
		return page;
	}

	private String handlePostUser(HttpServletRequest request,
			HttpServletResponse response, String emailAdd, String u, String p,
			String n, String f, String l, String y, String a, String c)
			throws AddressException, UnsupportedEncodingException,
			MessagingException {
		String page = "";
		System.out.println("password in handle post = "
				+ request.getParameter("R2password"));
		try {
			int val = mailSender.getVal();
			System.out.println("val = " + val);
			UserDTO user = new UserDTO(u, p, emailAdd, n, f, l, y, a, c);
			// auction.storeUser(user);
			auction.storeUserTemp(user, val);
			page = "checkMail.jsp";
			mailSender.sendMail(emailAdd);
		} catch (Exception e) {
			e.printStackTrace();
			page = "error.jsp";
		}
		return page;
	}

	private String processRegister(String userName, String password,
			String email) {
		if (userName.equals("") || password.equals("") || email.equals("")) {
			return "error.jsp";
		} else if (!email.contains("@")) {
			return "error.jsp";
		} else {
			return "register2.jsp";
		}
	}

	private String processLogin(HttpServletRequest request,
			HttpServletResponse response, String userName, String password) {
		if (userName.equals("") || password.equals("")) {
			return "loginError.jsp";
		} else {
			try {
				UserDTO user = auction.getUserDetails(userName);
				if (user != null) {
					if (user.getUsername().equals(userName)
							&& user.getPassword().equals(password)) {
						UserBean userBeann = (UserBean) request.getSession()
								.getAttribute("userBean");
						userBeann.setUsernameBean(user.getUsername());
						userBeann.setPasswordBean(user.getPassword());
						userBeann.setEmailBean(user.getEmailadd());
						userBeann.setNicknameBean(user.getNickname());
						userBeann.setFirstnameBean(user.getFirstname());
						userBeann.setLastnameBean(user.getLastname());
						userBeann.setYobBean(user.getYearofbirth());
						userBeann.setFulladdBean(user.getFulladdress());
						userBeann.setCreditcardnumbBean(user
								.getCreditcardnumber());
						return "home.jsp";
					} else {
						return "loginError.jsp";
					}
				} else {
					return "loginError.jsp";
				}
			} catch (Exception e) {
				e.printStackTrace();
				return "loginError.jsp";
			}
		}
	}

}
