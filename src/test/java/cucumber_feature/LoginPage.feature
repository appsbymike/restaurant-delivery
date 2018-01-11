Feature: Log In

#Login Module Requirements #1 & #2
Scenario: Validate Login Page Input Fields
	Given User is on Home Page
	When User Navigates to Login Page
	Then User sees text input field for Username
	And User sees masked input field for Password

#Login Module Requirement #4
Scenario: Validate Login Page Header
	Given User is on Home Page
	When User Navigates to Login Page
	Then User sees Login Page Header

#Login Module Requirements #3 & #5

Scenario: Log In with Valid Credentials.
	Given User is on Home Page
	When User Navigates to Login Page
	And User enters valid Username and Password
	Then User is sent to Menu Page

#Login Module Requirements #3 & #5

Scenario: Log In with Admin Credentials.
	Given User is on Home Page
	When User Navigates to Login Page
	And User enters Valid Admin Credentials
	Then User is sent to Admin Home Page