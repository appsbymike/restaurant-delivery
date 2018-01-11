Feature: Admin Account Page

# Requirement #1: Logout option should be available.
# Requirement #2: The Navigate Application link should only be available in the Admin Account page.
Scenario: Logout is Available
	Given User is on Home Page
	When User Navigates to Login Page
	And User enters Valid Admin Credentials
	Then User sees Logout Button
	And User sees Navigate Application Button