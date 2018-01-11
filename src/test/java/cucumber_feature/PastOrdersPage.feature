
Feature: View Past Orders

# Requirement #1: Logout option should be available
# Requirement #2: Button to return to Menu should be available

Scenario: Available Buttons
	Given User is logged in
	When User navigates to Past Orders Page
	Then User sees the Logout Button
	And User sees the Menu Button