@QTTP-17
Feature: TEST EXE: Login

	@TEST_QTTP-8 @TESTSET_QTTP-9
	Scenario Outline: Unsuccesful Login
		Given I am on Login Screen
		Then Enter Username '<ValidUsername>' and Password '<InvalidPassword>'
		And Click on Login
		And Verify Inline error message for Password field 'Incorrect Password' displayed
		Then Enter Username '<InvalidUsername>' and Password '<ValidPassword>'
		And Click on Login
		And Verify Inline error message for Password field 'Incorrect Password' displayed
		Examples:
		| ValidUsername | InvalidPassword | InvalidUsername | ValidPassword |
		| Swathi.priya  | Vashvika        | Swathi          | Vashvika14@   |
	@TEST_QTTP-12 @TESTSET_QTTP-9
	Scenario Outline: Mobile user try to login on web app
		Given I am on Login Screen
		    Then Enter Username '<ValidUsername>' and Password '<ValidPassword>'
		    And Click on Login
		    And Verify Inline error message for Password field 'Sorry - your account does not have permission to access the web application.' displayed
		    Examples:
		      | ValidUsername    | ValidPassword |
		      | yogaus.inspector | Asafe@1234    |
	@TEST_QTTP-13 @TESTSET_QTTP-9
	Scenario Outline: User account locked after multiple login attempt failed and verify the functionality to unlock
		Given I am on Login Screen
		Then Enter Username "<usernameforLockedaccount>" and Password "<validpasswordforlockedAccount>"
		And Click on Login
		And logout from user
		Then Enter Username "<usernameforLockedaccount>" and Password "<invalidpasswordforlockedaccount>" for 10 user should get "Incorrect Password" error message
		And Verify Inline error message for Password field 'This account has been locked. Please contact an administrator for more information.' displayed
		Then Enter Username '<usernameforLockedaccount>' and Password '<validpasswordforlockedAccount>'
		And Click on Login
		And Verify Inline error message for Password field "This account has been locked. Please contact an administrator for more information." displayed
		Then Enter Username '<AdminUsernameToUnlock>' and Password '<AdminPasswordToUnlock>'
		And Click on Login
		And Navigate to "Users" page
		And Verify lock icon with the "<usernameforLockedaccount>"
		And Lock icon disappears once i click on lock icon
		And logout from user
		Then Enter Username '<usernameforLockedaccount>' and Password '<validpasswordforlockedAccount>'
		And Click on Login
		And user able to login successfully
		Examples:
		| usernameforLockedaccount | validpasswordforlockedAccount | invalidpasswordforlockedaccount | AdminUsernameToUnlock | AdminPasswordToUnlock |
		| swathi.user5             | Vashvika14@                   | Vashvika                        | swathi.user1          | Vashvika14@           |
	@TEST_QTTP-14 @TESTSET_QTTP-9
	Scenario Outline: Verify password can be changed successfully
		Given I am on Login Screen
		When the user clicks on the "Forgot your password?"
		Then Then verify the message "Forgotten your password? Let’s get you a new one"
		And enter your "<Email>" to reset
		When submit the "Reset your password"
		Then Then verify the message "Thank you! Please check your email"
		Then Click on Reset password link on "<Email>" , "<emailPassword>" , "<username>"
		When connect the "Test Data" -- "Passwordvalidation"
		Then Enter a unique password which has not been used
		And submit the "Change Password"
		Then the new password is updated
		Examples:
		| Email                      | emailPassword | username         |
		| testemailfathom3@gmail.com | Password001!  | testemailfathom3 |
	@TEST_QTTP-15 @TESTSET_QTTP-9
	Scenario Outline: Verify password can be changed successfully *DATA BASE* 
		Given I am on Login Screen
		When the user clicks on the "Forgot your password?"
		Then Then verify the message "Forgotten your password? Let’s get you a new one"
		And enter your "<emailSent>" to reset
		When submit the "Reset your password"
		Then Then verify the message "Thank you! Please check your email"
		Then Click on Reset password link on "<emailRecieved>" , "<emailPassword>" , "<emailUsername>"
		And connect to the Data Base
		When connect the "Test Data" -- "PasswordCreate"
		When the user validates the scenarios for the "<testCriteria>" criteria and the "yusuf organisation" organisation
		And disconnect from the Data Base
		Examples:
		| userType  | emailUsername | emailPassword | emailSent                            | emailRecieved              | testCriteria          |
		| installer | testUserTech  | Password001!  | testemailfathom3+installer@gmail.com | testemailfathom3@gmail.com | PASSWORD_MIN_SIZE     |
		| installer | testUserTech  | Password001!  | testemailfathom3+installer@gmail.com | testemailfathom3@gmail.com | PASSWORD_MAX_SIZE     |
		| installer | testUserTech  | Password001!  | testemailfathom3+installer@gmail.com | testemailfathom3@gmail.com | ALPHABETIC_MIN_SIZE   |
		| installer | testUserTech  | Password001!  | testemailfathom3+installer@gmail.com | testemailfathom3@gmail.com | LOWERCASE_MIN_SIZE    |
		| installer | testUserTech  | Password001!  | testemailfathom3+installer@gmail.com | testemailfathom3@gmail.com | UPPERCASE_MIN_SIZE    |
		| installer | testUserTech  | Password001!  | testemailfathom3+installer@gmail.com | testemailfathom3@gmail.com | NUMERIC_MIN_SIZE      |
		| installer | testUserTech  | Password001!  | testemailfathom3+installer@gmail.com | testemailfathom3@gmail.com | SPECIAL_CHAR_MIN_SIZE |
		| installer | testUserTech  | Password001!  | testemailfathom3+installer@gmail.com | testemailfathom3@gmail.com | START_WITH_ALPHA_CHAR |
