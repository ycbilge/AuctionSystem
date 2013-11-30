package edu.unsw.comp9321.jdbc;

public class BidDTO {
	private String userName;
	private String itemTitle;
	private String bidVal;

	public BidDTO(String u, String i, String b) {
		this.userName = u;
		this.itemTitle = i;
		this.bidVal = b;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	public String getBidVal() {
		return bidVal;
	}

	public void setBidVal(String bidVal) {
		this.bidVal = bidVal;
	}
}
