package edu.unsw.comp9321.bean;

import java.io.Serializable;

public class UserBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String usernameBean;
	private String passwordBean;
	private String emailBean;
	private String nicknameBean;
	private String firstnameBean;
	private String lastnameBean;
	private String yobBean;
	private String fulladdBean;
	private String creditcardnumbBean;
	public String getUsernameBean() {
		return usernameBean;
	}
	public void setUsernameBean(String usernameBean) {
		this.usernameBean = usernameBean;
	}
	public String getPasswordBean() {
		return passwordBean;
	}
	public void setPasswordBean(String passwordBean) {
		this.passwordBean = passwordBean;
	}
	public String getEmailBean() {
		return emailBean;
	}
	public void setEmailBean(String emailBean) {
		this.emailBean = emailBean;
	}
	public String getNicknameBean() {
		return nicknameBean;
	}
	public void setNicknameBean(String nicknameBean) {
		this.nicknameBean = nicknameBean;
	}
	public String getFirstnameBean() {
		return firstnameBean;
	}
	public void setFirstnameBean(String firstnameBean) {
		this.firstnameBean = firstnameBean;
	}
	public String getLastnameBean() {
		return lastnameBean;
	}
	public void setLastnameBean(String lastnameBean) {
		this.lastnameBean = lastnameBean;
	}
	public String getYobBean() {
		return yobBean;
	}
	public void setYobBean(String yobBean) {
		this.yobBean = yobBean;
	}
	public String getFulladdBean() {
		return fulladdBean;
	}
	public void setFulladdBean(String fulladdBean) {
		this.fulladdBean = fulladdBean;
	}
	public String getCreditcardnumbBean() {
		return creditcardnumbBean;
	}
	public void setCreditcardnumbBean(String creditcardnumbBean) {
		this.creditcardnumbBean = creditcardnumbBean;
	}
	
	
	
}
