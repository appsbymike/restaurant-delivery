Feature: Admin User Page

Background: Admin navigates to User List
	Given User is on Home Page
	When User Navigates to Login Page
	And User enters Valid Admin Credentials
	And User Navigates to Navigation Panel
	And User Navigates to User List

# Requirement #1: Logout option should be available.
# Requirement #3: There should be other links that sends the admin to Location list, Order list, Sell items list
Scenario: Available Buttons
	Then User sees Logout Button
	And User sees Location List Button
	And User Sees Order List Button
	And User Sees Item List Button

# Requirement #2: First, last name, email should be included in the list of user
# How do I test for this...?
Scenario: User List Format
	Then User sees list of users in the specified format