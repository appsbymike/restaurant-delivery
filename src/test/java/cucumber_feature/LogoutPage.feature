Feature: Logout Page

#‘Thanks for Visiting Us !!! You have been logged out’ should be displayed.
#‘Want to login again? Click below’ should be displayed with a Login button to go to the login screen.
Scenario: Logout Page Text
	Given User is logged in
	When User logs out
	Then Logout message should be displayed
	And Logout link should be displayed