
Feature: Review Item

#Requirement #1: Logout should be available
#Requirement #2: The menu page should be the only page with a link to Review Item Page

Scenario: Logout should be available
	Given User is logged in
	When User navigates to Single Item Page
	Then User sees Logout Button

#Requirement #3: The user should be able to write a review
#Requirement #4: The user should be able to view the new review they just submitted

Scenario: User should be able to write a review and view it
	Given User is logged in
	When User navigates to Single Item Page
	When User writes a review
	And User submits a review
	Then User should see the review they submitted