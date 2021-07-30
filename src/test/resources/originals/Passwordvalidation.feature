Feature: Passwordvalidation

Scenario Outline:M3-4394 Verify error message when entering invalid password 
	Given Log in as a '<Role>' username as '<Username>'
	Then Navigate to Create User Page
	When Enter the testdata for User scenario '<ScenarioName>' and click on 'Create'
	Then Verify User has Created Successfully 
	Then Delete the newly created user
	Then Navigate to gmail inbox 'https://accounts.google.com/o/oauth2/auth/identifier?client_id=717762328687-iludtf96g1hinl76e4lc1b9a82g457nn.apps.googleusercontent.com&scope=profile%20email&redirect_uri=https%3A%2F%2Fstackauth.com%2Fauth%2Foauth2%2Fgoogle&state=%7B%22sid%22%3A1%2C%22st%22%3A%2259%3A3%3ABBC%2C16%3A1eecb8a7974f9471%2C10%3A1597317729%2C16%3A99043642e73614fc%2Ce3817664fea97346761730f583d86dedd802bbd77c7654c5c88766dd27c1d09f%22%2C%22cdl%22%3Anull%2C%22cid%22%3A%22717762328687-iludtf96g1hinl76e4lc1b9a82g457nn.apps.googleusercontent.com%22%2C%22k%22%3A%22Google%22%2C%22ses%22%3A%225c466e726f33402ba23d2c5c5a11d83a%22%7D&response_type=code&flowName=GeneralOAuthFlow'  
	Then click on the create password URL
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
Examples: 
	| Username               | Role      | ScenarioName                   |
	| yoganandhini.shanmugam | developer | Passwordvalidation_Welcomemail |


	 
	 












	 
