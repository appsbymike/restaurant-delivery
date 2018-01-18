Feature: Receipt

Background: User navigated to receipt
	Given User is logged in
	When User navigates to their receipt

#Requirement #1: Complete receipt information should be displayed

Scenario: Receipt information should be displayed for all items
	Then User sees receipt information for each item

#Requirement #2: Logout should be available
#Requirement #3: Link to see menu items once more
#Requirement #4: Link to view alls order made previously

Scenario: Available links
	Then User sees Logout Button
	And User sees Menu Button
	And User sees Past Orders Button