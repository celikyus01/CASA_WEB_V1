@M3-6540
Feature: TE1 Example Test Execution

	@TEST_M3-6542
	Scenario Outline: TC2 Example Test Case 2
		Given I am on Login Screen
		When Enter Username "<ValidUsername>" and Password "<ValidPassword>"
		And Click on Login
		Then Verify page title as "Dashboard"
		Examples:
		| ValidUsername   | ValidPassword |
		| yusuf_inspector | Adana001@     |
	@TEST_M3-6583
	Scenario Outline: TC1  Example TestCase1
		Given I am on Login Screen
		Then Enter Username '<ValidUsername>' and Password '<InvalidPassword>'
		And Click on Login
		Then Verify Inline error message for Username field 'Incorrect Username' displayed
		And Verify Inline error message for Password field 'Incorrect Password' displayed
		Then Enter Username '<InvalidUsername>' and Password '<ValidPassword>'
		And Click on Login
		Then Verify Inline error message for Username field 'Incorrect Username' displayed
		And Verify Inline error message for Password field 'Incorrect Password' displayed
		Examples:
		| ValidUsername | InvalidPassword | InvalidUsername | ValidPassword |
		| Swathi.priya  | Vashvika        | Swathi          | Vashvika14@   |
