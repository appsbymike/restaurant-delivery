Feature: Registration

#Requirement 1 - All fields are mandatory
Scenario Outline: All fields are mandatory
	Given User navigated to Registration Page
	When User forgets to enter credential and fills in <username>,<password>,<repassword>,<firstname>,<lastname>,<address>,<phone> and <email>
	Then User sees Missing Credentials Message for <field>
	
	Examples:
	|username|password|repassword|firstname|lastname|address|phone     |email        |
	|        |testuser|testuser  |test     |test    |test   |1234567890|test@test.com|
	|testuser|        |testuser  |test     |test    |test   |1234567890|test@test.com|
	|testuser|testuser|          |test     |test    |test   |1234567890|test@test.com|
	|testuser|testuser|testuser  |         |test    |test   |1234567890|test@test.com|
	|testuser|testuser|testuser  |test     |        |test   |1234567890|test@test.com|
	|testuser|testuser|testuser  |test     |test    |       |1234567890|test@test.com|
	|testuser|testuser|testuser  |test     |test    |test   |          |test@test.com|
	|testuser|testuser|testuser  |test     |test    |test   |1234567890|             |

#Requirement 2 - Page Header says "User Details"

#Requirement 3 - After register, the user should be sent to the login page