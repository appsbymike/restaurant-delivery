Feature: Select Location

#Requirement 1: Each location should have a button to be selected

Scenario: Each location has a button
	Given User is logged in
	When User navigates to Menu Page
	And User adds Item to Cart
	And User accepts message
	And User processes order
	Then User should see a button for each Location