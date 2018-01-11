Feature: Payment Information

#Requirement #1: All fields are mandatory

Scenario Outline: All fields are mandatory
	Given User is logged in
	When User adds multiple items to their Cart
	And User selects a location
	And User confirms order
	When User enters <card number>, <security number>, <zipcode>
	Then User sees missing field message for <field>
	
	Examples:
	|card number|security number|zipcode|field   |
	|           |12345          |12345  |ccnumber|
	|12345      |               |12345  |seccode |
	|12345      |12345          |       |zipcode |