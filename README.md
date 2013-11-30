AuctionSystem
=============

Java based web application which is created with Java, JSP/Servlets, JSTL,JDBC, Apache Tomcat and java mail library.

The core requirements of this aapplication are split across four modules

    User Registration
    Offering Items for Auction
    Bidding
    Admin Functionality

User Registration

    A user visits a registration page and enters the following details: a username, a password, an email address.
    This page displays a form with the current values of: username, nickname, first name, last name, email, year of birth, full address and credit card number. (Use a dummy credit card number since you will not be interacting with real payment systems)
    On submitting this information, the system sends an email containing a confirmation URL to the supplied address.
    The user reads the email, goes to the confirmation URL; the system confirms the registration.
    Every detail other than the username can be changed at any time by the user.

Offering Items for Auction

    User A logs into his/her account.
    User A can add an item with the following details:
        Title (10 words max.)
        Category
        Picture
        Description (100 words max.)
        Postage details
        Reserve Price
        Bidding Start Price
        Bidding Increments.
        Time for the bidding to end (closing time) - optional.
    The user clicks a button to register the item and start the auction.

The reserve price is confidential and should be visible only to the user. The minimum closing time is 3 min and the maximum is 60 min. If this is not supplied, the system uses a default of 10 min from the time the item was registered. (Note: Obviously, the closing times are for demo convenience and not reflective of real-world auction sites)

Bidding

The bidding functionality works as follows:

    User B logs into his/her account
    User B searches for a particular item
    The results of the search are displayed as a list.
    Clicking on an item in the list takes the user to the page for the item, showing Title, Picture, Description, Postage Details, the amount for the current winning bid and the bidding increment.
    The user then enters a new bid into a form input. The new bid should NOT be less than current bid plus increment. If this condition is violated, the user is informed of the error.
    If the user's bid is accepted, he/she is shown a success message and the page for the item with the current winning bid refreshed with his or her bidding amount. The user is also notified by email.
    When another user posts a winning bid, User B is also notified that he/she has lost the winning bid.

When the closing time is crossed, depending on the winning bid, there are two possible actions:

    If the winning bid amount is greater than or equal to the reserve price, both the user with the winning bid and the item owner are notified by email. If either is logged in, they get a message on their profile page as well.
    Else, the item owner is notified that the winning bid is lower than the reserve price. The item owner has to log in to his/her account, go to the item page, and either accept or reject the winning bid. In either case, the user with the winning bid is notified by email.

Admin Functionality.

The admin is a special account in the system which can only be accessed via a separate page (that is, not the usual user login page). The admin has the following functions:

    Halt an ongoing auction
    Remove an item from auction
    Ban a user from the site.

Also application must include input form validation, guarded views, and preventing form resubmission on pressing back button.
