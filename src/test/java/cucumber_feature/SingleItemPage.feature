Feature: Review Item

#Requirement #1: Logout should be available
#Requirement #2: The menu page should be the only page with a link to Review Item Page
Scenario: Logout should be available
	Given User navigated to review an Item
	Then Logout button should be displayed

#Requirement #3: The user should be able to write a review
#Requirement #4: The user should be able to view the new review they just submitted
Scenario: User should be able to write a review and view it
	Given User navigated to review an Item
	When User writes a review
	And User submits a review
	Then User should see the review they submitted