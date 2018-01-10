Feature: Select Location

#Requirement 1: Each location should have a button to be selected
Scenario: Each location has a button
	Given User has item in cart
	When User processes order
	Then User should see a button for each Location