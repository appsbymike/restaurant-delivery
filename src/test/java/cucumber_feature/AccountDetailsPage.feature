Feature: Account Details

#Requirement #1: Logout option should be available
#Requirement #3: There should be a link to all orders the user has made
Scenario: Links
	Given User is viewing their Account Details
	Then User sees Logout and Past Orders links

#Requirement #2: All fields are mandatory
Scenario Outline: All fields are mandatory
	Given User is viewing their Account Details
	When User enters data <firstname>,<lastname>,<password>,<repassword>,<address>,<phone>,<email>
	Then User sees empty field message for <field>

	Examples:
		|firstname|lastname|password|repassword|address|phone     |email        |field     |
		|         |gabriel |mike    |mike      |mike   |9239813231|mike@mike.com|firstname |
		|mike     |        |mike    |mike      |mike   |9239813231|mike@mike.com|lastname  |
		|mike     |gabriel |        |mike      |mike   |9239813231|mike@mike.com|password  |
		|mike     |gabriel |mike    |          |mike   |9239813231|mike@mike.com|repassword|
		|mike     |gabriel |mike    |mike      |       |9239813231|mike@mike.com|address   |
		|mike     |gabriel |mike    |mike      |mike   |          |mike@mike.com|phone     |
		|mike     |gabriel |mike    |mike      |mike   |9239813231|             |email     |