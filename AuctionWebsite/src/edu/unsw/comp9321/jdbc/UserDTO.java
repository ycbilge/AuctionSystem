package edu.unsw.comp9321.jdbc;

public class UserDTO {
	
	public UserDTO(String uname, String pword, String emailad, String nick, String first, String last, String yob, String fullAdd, String ccn) {
		this.username = uname;
		this.password = pword;
		this.emailadd = emailad;
		this.nickname = nick;
		this.firstname = first;
		this.lastname = last;
		this.yearofbirth = yob; // sql date
		this.fulladdress = fullAdd;
		this.creditcardnumber = ccn;
		
	}
	
	private String username;
	private String password;
	private String emailadd;
	private String nickname;
	private String firstname;
	private String lastname;
	private String yearofbirth;
	private String fulladdress;
	private String creditcardnumber;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailadd() {
		return emailadd;
	}
	public void setEmailadd(String emailadd) {
		this.emailadd = emailadd;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getYearofbirth() {
		return yearofbirth;
	}
	public void setYearofbirth(String yearofbirth) {
		this.yearofbirth = yearofbirth;
	}
	public String getFulladdress() {
		return fulladdress;
	}
	public void setFulladdress(String fulladdress) {
		this.fulladdress = fulladdress;
	}
	public String getCreditcardnumber() {
		return creditcardnumber;
	}
	public void setCreditcardnumber(String creditcardnumber) {
		this.creditcardnumber = creditcardnumber;
	}

}
