Feature: Receipt

#Requirement #1: Complete receipt information should be displayed
Scenario: Receipt information should be displayed for all items
Given User navigated to their receipt
Then User sees receipt information for each item

#Requirement #2: Logout should be available
#Requirement #3: Link to see menu items once more
#Requirement #4: Link to view alls order made previously
Scenario: Available links
Given User navigated to their receipt
Then User sees Logout link
And User sees Menu link
And User sees Past Orders Link