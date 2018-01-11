Feature: Menu

#Requirement 1: Logout should be available

Scenario: Logout is Available
	Given User is logged in
	When User navigates to Menu Page
	Then User sees Logout Button

#Requirement 2: Items can be added to cart by clicking a button

Scenario: Adding Item to Cart
	Given User is logged in
	When User adds Item to Cart
	Then User sees message "Item added to cart!"

#Requirement 3: A button named proccess order should allow the user to select a location

Scenario: Process Order
	Given User is logged in
	When User adds Item to Cart
	And User accepts message
	And User clicks Process Order
	Then User is sent to Locations Page