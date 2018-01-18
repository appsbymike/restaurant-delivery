Feature: Admin Items

Background: Admin navigates to Item List
	Given User is on Home Page
	When User Navigates to Login Page
	And User enters Valid Admin Credentials
	When User Navigates to Navigation Panel
	And User Navigates to Item List

# Requirement #1: Logout option should be available.
# Requirement #7: There should be other links that sends the admin to Location list, Order list, User list
Scenario: Available Buttons
	Then User sees Logout Button
	And User sees Location List Button
	And User Sees Order List Button
	And User Sees User List Button


# Requirement #2: After admin clicks add button, admin should be send to the Admin ItemPage
Scenario: Add Item
	When User Adds an Item
	Then User is taken back to Items List
	
# Requirement #6: Once clicking on either delete or edit, the admin should be send back to the Admin item Page
Scenario: Delete Item
	When User Deletes an Item
	Then User is taken back to Items List
	
Scenario: Edit Item
	When User Edits an Item
	Then User is taken back to Items List

# Requirement #3: The details should be displayed in table format.
# Requirement #4: Each item should have button to edit.
# Requirement #5: Each item should have button to delete.
Scenario: Available Buttons
	Then User sees Edit button for each Item
	Then User sees Delete button for each Item
