
Feature: Review Order

#Requirement #1: All items should have the image, name and price

Scenario: Each item has image, name and price
	Given User is logged in
	When User adds multiple items to their Cart
	And User selects a location
	Then User sees the image, name and price of each item in their cart
	
#Requirement #2: Each item has delete button

Scenario: Each item has image, name and price
	Given User is logged in
	When User adds multiple items to their Cart
	And User selects a location
	Then User sees delete button for each item in their cart
	
#Requirement #3: After clicking on confirm, user is sent to payment page

Scenario: Each item has image, name and price
	Given User is logged in
	When User adds multiple items to their Cart
	And User selects a location
	And User confirms their purchase
	Then User is sent to Payment Page

#Requirement #4: After click on cancel order, user is sent to menu items page

Scenario: Each item has image, name and price
	Given User is logged in
	When User adds multiple items to their Cart
	And User selects a location
	And User cancels their purchase
	Then User is sent back to Menu Page

#Requirement #5: After clicking on delete, user should be sent back to Review Order page

Scenario: Each item has image, name and price
	Given User is logged in
	When User adds multiple items to their Cart
	And User selects a location
	And User clicks on the delete button for an item in their Cart
	Then User is sent back to Review Order Page
	And User no longer sees that item in their Cart