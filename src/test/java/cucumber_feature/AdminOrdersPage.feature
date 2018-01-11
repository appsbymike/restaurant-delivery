Feature: Admin Orders List

# Requirement #1: Logout option should be available.
# Requirement #5: There should be other links that sends the admin to Location list, User list, Sell items list
Scenario: Available Buttons
	Given User is on Home Page
	When User Navigates to Login Page
	And User enters Valid Admin Credentials
	When User Navigates to Navigation Panel
	And User Navigates to Orders List
	Then User sees Logout Button
	And User sees Location List Button
	And User Sees Order List Button
	And User Sees User List Button
	
# Requirement #2: The details should be displayed in table format.
# Requirement #3: Each Order should have button to delete
Scenario: Delete Button
	Given User is on Home Page
	When User Navigates to Login Page
	And User enters Valid Admin Credentials
	When User Navigates to Navigation Panel
	And User Navigates to Orders List
	Then User sees Delete button for each Order
	
# Requirement #4: Once clicking on either delete, the admin should be send back to the Admin Order Page
Scenario: Delete Item
	Given User is on Home Page
	When User Navigates to Login Page
	And User enters Valid Admin Credentials
	When User Navigates to Navigation Panel
	And User Navigates to Orders List
	And User Deletes an Order
	Then User is taken back to Orders List