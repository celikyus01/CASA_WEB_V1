@login
Feature: Authentication login

  Scenario Outline: M3-4392_Verify Login failure scenario
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


  Scenario Outline: M3-4402_Verify Mobile user try to login on web app
    Given I am on Login Screen
    Then Enter Username '<ValidUsername>' and Password '<ValidPassword>'
    And Click on Login
    And Verify Inline error message for Password field 'Sorry - your account does not have permission to access the web application.' displayed
    Examples:
      | ValidUsername    | ValidPassword |
      | yogaus.inspector | Asafe@1234    |

  @wip7
  Scenario Outline:M3-1991,M3-1992_User account locked after multiple login attempt failed and verify the functionality to unlock
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



#  @wip2
#  Scenario Outline:M3-4393,M3-4395,M3-6046_Verify forgot password functionality and Password validation when resetting password and No reusing of existing Password during Forgot password.
#    Given I am on Login Screen
#    When the user clicks on the "Forgot your password?"
#    Then Then verify the message "Forgotten your password? Let’s get you a new one"
#    And enter your "<Email>" to reset
#    When submit the "Reset your password"
#    Then Then verify the message "Thank you! Please check your email"
#    Then Click on Reset password link on "<Email>" , "<emailPassword>" , "<username>"
#    When "Enter New Password" for the "<previouspassword>"
#    When "Confirm New Password" for the "<previousconfirmpassword>"
#    When submit the "Change Password"
#    Then Then verify the fail message "Password must not have been used by you previously" for the reset password
#    When enter your "<Email>" to reset
#    When "Confirm New Password" for the "<confirmpassword>"
#    And submit the "Change Password"
#    Then Then verify the fail message "Passwords must match" for the reset password
#    When connect the "Test Data" -- "Passwordvalidation"
#    And Enter the Login credentials ScenarioName from the test data and Validate the Errormessage
#      | Scenarioname                 | Errormessage                                      |
#      | PasswordwithoutUppercase     | Password must have at least 1 uppercase letter    |
#      | PasswordwithoutLowercase     | Password must have at least 1 lowercase letter    |
#      | PasswordwithoutNumericvalue  | Password must have at least 1 number(s)           |
#      | PasswordwithoutAlpabets      | Password must start with an alpha character       |
#      | Passwordwithoutnumers        | Password must have at least 1 number(s)           |
#      | Password_lenght5             | Password must be between 8 and 40 characters long |
#      | Password_maxlenght41         | Password must be between 8 and 40 characters long |
#      | Passwordbeginwithspecialchar | Password must start with an alpha character       |
#      | Passwordbeginwithnumbers     | Password must start with an alpha character       |
#      | Passwordwithout_validspecialchar   | Password should have at least 1 special character not in the following list #&'()*+-/:;<=>?[/\]^_`{\|}~. |
#      | Passwordwith_RestrictedSpecialchar | The characters #&'()*+-/:;<=>?[/\]^_`{\|}~ are not allowed.                                              |
#    Then Enter a unique password which has not been used
#    And submit the "Change Password"
#    Then the new password is updated
#    Examples:
#      | Email                      | emailPassword | username         | newPassword | confirmpassword | Password  | previouspassword | previousconfirmpassword |
#      | testemailfathom3@gmail.com | Password001!  | testemailfathom3 | Swathi92@   | Swathi94@       | Asafe1234 | Password001!     | Password001!            |


  @wip3 @succesful_resetPassword
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


  @wip4 @greyCheck @db
  Scenario Outline: DATA BASE Verify password can be changed successfully for the "<testCriteria>" criteria for the "<userType>" user type
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




#  Scenario Outline: special character combination
#  Scenario Outline: Verify password can be changed successfully for the "<criteria>" criteria for the "<userType>" user type






