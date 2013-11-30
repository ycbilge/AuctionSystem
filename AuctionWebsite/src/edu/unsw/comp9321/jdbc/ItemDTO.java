package edu.unsw.comp9321.jdbc;

public class ItemDTO {
	private String userName;
	private String title;
	private String category;
	private String picture;
	private String description;
	private String postageDetail;
	private int reservePrice;
	private int biddingStartPrice;
	private int biddingIncrement;
	private int biddingTime;
	private int currentTime;

	public ItemDTO(String userName, String t, String c, String pic, String d, String post,
			int r, int biddingStart, int biddingInc, int biddingTime,
			int currentTime) {
		this.setUserName(userName);
		this.title = t;
		this.category = c;
		this.picture = pic;
		this.description = d;
		this.postageDetail = post;
		this.reservePrice = r;
		this.biddingStartPrice = biddingStart;
		this.biddingIncrement = biddingInc;
		this.biddingTime = biddingTime;
		this.currentTime = currentTime;

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPostageDetail() {
		return postageDetail;
	}

	public void setPostageDetail(String postageDetail) {
		this.postageDetail = postageDetail;
	}

	public int getReservePrice() {
		return reservePrice;
	}

	public void setReservePrice(int reservePrice) {
		this.reservePrice = reservePrice;
	}

	public int getBiddingStartPrice() {
		return biddingStartPrice;
	}

	public void setBiddingStartPrice(int biddingStartPrice) {
		this.biddingStartPrice = biddingStartPrice;
	}

	public int getBiddingIncrement() {
		return biddingIncrement;
	}

	public void setBiddingIncrement(int biddingIncrement) {
		this.biddingIncrement = biddingIncrement;
	}

	public int getBiddingTime() {
		return biddingTime;
	}

	public void setBiddingTime(int biddingTime) {
		this.biddingTime = biddingTime;
	}

	public int getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(int currentTime) {
		this.currentTime = currentTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
