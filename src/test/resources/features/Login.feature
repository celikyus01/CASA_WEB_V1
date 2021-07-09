Feature: Authentication login

Scenario Outline: M3-4392_Verify Login failure scenario
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


Scenario Outline: M3-4402_Verify Mobile user try to login on web app
Given I am on Login Screen 
Then Enter Username '<ValidUsername>' and Password '<ValidPassword>'
And Click on Login 
And Verify Inline error message for Password field 'Sorry - your account does not have permission to access the web application.' displayed
Examples:
| ValidUsername    | ValidPassword |
| yogaus.inspector | Asafe@1234    |


Scenario Outline:M3-1991,M3-1992_User account locked after multiple login attempt failed and verify the functionality to unlock
Given I am on Login Screen
Then Enter Username '<usernameforLockedaccount>' and Password '<validpasswordforlockedAccount>' 
And Click on Login 
And logout from user
Then Enter Username '<usernameforLockedaccount>' and Password '<invalidpasswordforlockedaccount>' for '10' user should get 'Incorrect Password' error message
And Verify Inline error message for Password field 'This account has been locked. Please contact an administrator for more information.' displayed
Then Enter Username '<usernameforLockedaccount>' and Password '<validpasswordforlockedAccount>' 
And Click on Login 
And Verify Inline error message for Password field 'This account has been locked. Please contact an administrator for more information.' displayed
Then Enter Username '<AdminUsernameToUnlock>' and Password '<AdminPasswordToUnlock>' 
And Click on Login 
And Navigate to View User page
And Search for user which is locked '<usernameforLockedaccount>'
And Lock icon disappears once i click on lock icon
And logout from user
Then Enter Username '<usernameforLockedaccount>' and Password '<validpasswordforlockedAccount>' 
And Click on Login 
And user able to login successfully
Examples:
| usernameforLockedaccount | validpasswordforlockedAccount | invalidpasswordforlockedaccount | AdminUsernameToUnlock | AdminPasswordToUnlock |
| swathi.user5             | Vashvika14@                   | Vashvika                        | swathi.user1          | Vashvika14@           |



    
Scenario Outline:M3-4393,M3-4395,M3-6046_Verify forgot password functionality and Password validation when resetting password and No reusing of existing Password during Forgot password.
Given I am on Login Screen
When the user clicks on the Forgot your Password? link
Then Forgot password page displayed
And Enter Email address to receive a reset password link email
Then Click on Reset your password button
Then Navigate to gmail inbox 'https://accounts.google.com/o/oauth2/auth/identifier?client_id=717762328687-iludtf96g1hinl76e4lc1b9a82g457nn.apps.googleusercontent.com&scope=profile%20email&redirect_uri=https%3A%2F%2Fstackauth.com%2Fauth%2Foauth2%2Fgoogle&state=%7B%22sid%22%3A1%2C%22st%22%3A%2259%3A3%3ABBC%2C16%3A1eecb8a7974f9471%2C10%3A1597317729%2C16%3A99043642e73614fc%2Ce3817664fea97346761730f583d86dedd802bbd77c7654c5c88766dd27c1d09f%22%2C%22cdl%22%3Anull%2C%22cid%22%3A%22717762328687-iludtf96g1hinl76e4lc1b9a82g457nn.apps.googleusercontent.com%22%2C%22k%22%3A%22Google%22%2C%22ses%22%3A%225c466e726f33402ba23d2c5c5a11d83a%22%7D&response_type=code&flowName=GeneralOAuthFlow' 
Then Click on Reset password link on Email
Then Enter a '<previouspassword>' and '<previousconfirmpassword>' which has already been used
And Click on Change password button
Then Error message displayed advising the user the 'Password has been used by you previously.'
And Enter a '<newPassword>' and '<confirmpassword>' differently
Then 'Passwords don't match.' error message displayed
And Enter the Login credentials ScenarioName from the test data and Validate the Errormessage	 
	    | ScenarioName                     | Errormessage                                                                                                                                    |
	    | PasswordwithoutUppercase         | Password should have at least 1 uppercase letter.                                                                                               |
	    | PasswordwithoutLowercase         | Password should have at least 1 lowercase letter.                                                                                               |
	    | PasswordwithoutNumericvalue      | Password should have at least 1 number.                                                                                                         |
	    | PasswordwithoutAlpabets          | Password should have at least 1 lowercase letter.,Password should have at least 1 uppercase letter.,Password must start with a alpha character. |
	    | Password_lenght5                 | Password should be between 8 and 40 characters long.                                                                                            |
	    | Password_maxlenght41             | Password should be between 8 and 40 characters long.                                                                                            |
	    | Passwordbeginwithnumbers         | Password must start with a alpha character.                                                                                                     |
	    | Passwordbeginwithspecialchar     | Password must start with a alpha character.                                                                                                     |
	    | Passwordwithout_validspecialchar | Password should have at least 1 special character not in the following list #&'()*+-/:;<=>?[/\]^_`{\|}~. |
	    | Passwordwith_RestrictedSpecialchar | The characters #&'()*+-/:;<=>?[/\]^_`{\|}~ are not allowed.                                                                                |  
Then Enter a unique password which has not been used
And click on confirm to set password
Then the new password is updated


Examples:
 |Email                |newPassword|confirmpassword|Password |previouspassword|previousconfirmpassword|
 |asafeyoga23@gmail.com|Swathi92@  |Swathi94@      |Asafe1234|Praveen87@      |Praveen87@             |









