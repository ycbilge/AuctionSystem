DROP TABLE TBL_USERS;
DROP TABLE TBL_USERS_TEMP;

CREATE TABLE TBL_USERS (
	USERNAME VARCHAR(20) PRIMARY KEY,
	PASSWORD VARCHAR(20),
	EMAIL_ADD VARCHAR(40),
	NICKNAME VARCHAR(40),
	FIRSTNAME VARCHAR(50),
	LASTNAME VARCHAR(30),
	YEAROFBIRTH DATE,
	FULLADDRESS VARCHAR(200),
	CREDITCARDNUMBER VARCHAR(50)
);
