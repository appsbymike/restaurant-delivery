Feature: Admin Locations List

# Requirement #1: Logout option should be available.
# Requirement #6: There should be other links that sends the admin to Userlist, Order list, Sell items list
Scenario: Available Buttons
	Given User is on Home Page
	When User Navigates to Login Page
	And User enters Valid Admin Credentials
	When User Navigates to Navigation Panel
	And User Navigates to Locations List
	Then User sees Logout Button
	And User Sees Item List Button
	And User Sees Order List Button
	And User Sees User List Button
	
# Requirement #2: The add button should add an location and send the admin back to the list of location
Scenario: Add Location
	Given User is on Home Page
	When User Navigates to Login Page
	And User enters Valid Admin Credentials
	When User Navigates to Navigation Panel
	And User Navigates to Locations List
	And User Adds a Location
	Then User is taken back to Locations List
	
# Requirement #5: Once clicking on either delete or edit, the admin should be send back to the Admin Location Page
Scenario: Remove Location
	Given User is on Home Page
	When User Navigates to Login Page
	And User enters Valid Admin Credentials
	When User Navigates to Navigation Panel
	And User Navigates to Locations List
	And User Removes a Location
	Then User is taken back to Locations List
	
Scenario: Edit Location
	Given User is on Home Page
	When User Navigates to Login Page
	And User enters Valid Admin Credentials
	When User Navigates to Navigation Panel
	And User Navigates to Locations List
	And User Edits a Location
	Then User is taken back to Locations List
	
# Requirement #3: The details should be displayed in table format.
# Requirement #4: Each location should have two button to delete or edit
Scenario: Available Buttons
	Given User is on Home Page
	When User Navigates to Login Page
	And User enters Valid Admin Credentials
	When User Navigates to Navigation Panel
	And User Navigates to Locations List
	Then User sees Edit button for each Location
	Then User sees Delete button for each Location