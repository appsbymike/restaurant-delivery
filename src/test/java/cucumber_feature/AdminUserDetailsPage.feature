Feature: Admin View User Details

Background: Admin views a user
	Given User is on Home Page
	When User Navigates to Login Page
	And User enters Valid Admin Credentials
	When User Navigates to Navigation Panel
	And User Navigates to User List
	And User clicks on a User

# Requirement #1: Logout option should be available.
# Requirement #5: There should be other links that sends the admin to Location list, Order list, Sell items list
Scenario: Available Buttons
	Then User sees Logout Button
	And User sees Location List Button
	And User Sees Order List Button
	And User Sees Item List Button

# Requirement #2: All fields are mandatory
Scenario Outline: Mandatory Fields
	And User enters data <firstname>,<lastname>,<password>,<repassword>,<address>,<phone>,<email>
	Then User sees empty field message for <field>

	Examples:
		|firstname|lastname|password|repassword|address|phone     |email        |field     |
		|         |gabriel |mike    |mike      |mike   |9239813231|mike@mike.com|firstname |
		|mike     |        |mike    |mike      |mike   |9239813231|mike@mike.com|lastname  |
		|mike     |gabriel |        |mike      |mike   |9239813231|mike@mike.com|password  |
		|mike     |gabriel |mike    |          |mike   |9239813231|mike@mike.com|repassword|
		|mike     |gabriel |mike    |mike      |       |9239813231|mike@mike.com|address   |
		|mike     |gabriel |mike    |mike      |mike   |          |mike@mike.com|phone     |
		|mike     |gabriel |mike    |mike      |mike   |9239813231|             |email     |
	
# Requirement #3: After clicking update button, admin should be send to the list of users
Scenario: Update User
	And User updates a User
	Then User is sent back to User List
	
# Requirement #4: All the review that the user has made should be listed in this page
Scenario: See Reviews
	Then User sees past reviews