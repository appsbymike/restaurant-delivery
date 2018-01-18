Feature: Registration

Background: User navigated to registration page
	Given User is on Home Page
	When User navigates to Registration Page

#Requirement 1 - All fields are mandatory
Scenario Outline: All fields are mandatory
	When User forgets to enter credential and fills in <username>,<password>,<repassword>,<firstname>,<lastname>,<address>,<phone> and <email>
	Then User sees Missing Credentials Message for <field>
	
	Examples:
	|username|password|repassword|firstname|lastname|address|phone     |email        |field     |
	|        |testuser|testuser  |test     |test    |test   |1234567890|test@test.com|username  |
	|testuser|        |testuser  |test     |test    |test   |1234567890|test@test.com|password  |
	|testuser|testuser|          |test     |test    |test   |1234567890|test@test.com|repassword|
	|testuser|testuser|testuser  |         |test    |test   |1234567890|test@test.com|firstname |
	|testuser|testuser|testuser  |test     |        |test   |1234567890|test@test.com|lastname  |
	|testuser|testuser|testuser  |test     |test    |       |1234567890|test@test.com|address   |
	|testuser|testuser|testuser  |test     |test    |test   |          |test@test.com|phone     |
	|testuser|testuser|testuser  |test     |test    |test   |1234567890|             |email     |

#Requirement 2 - Page Header says "User Details"
Scenario: Validate Page Header
	Then User sees Registration Page Header

#Requirement 3 - After register, the user should be sent to the login page
Scenario: User successfully registers
	When User successfully registers
	Then User is redirected to Login Page