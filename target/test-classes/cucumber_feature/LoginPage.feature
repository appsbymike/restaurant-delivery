Feature: Log In

Background: User navigated to Login Page
	Given User is on Home Page
	When User Navigates to Login Page

#Login Module Requirements #1 & #2
Scenario: Validate Login Page Input Fields
	Then User sees text input field for Username
	And User sees masked input field for Password

#Login Module Requirement #4
Scenario: Validate Login Page Header
	Then User sees Login Page Header

#Login Module Requirements #3 & #5

Scenario: Log In with Valid Credentials.
	And User enters valid Username and Password
	Then User is sent to Menu Page

#Login Module Requirements #3 & #5

Scenario: Log In with Admin Credentials.
	And User enters Valid Admin Credentials
	Then User is sent to Admin Home Page