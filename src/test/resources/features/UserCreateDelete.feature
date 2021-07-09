Feature: UserCreateDelete

#@E2E @UserManagement 

Scenario Outline: M3-6011/M3-4283 Organisation admin user creates Supervisor user
	Given Log in as a '<Role>' username as '<Username>'
	Then Navigate to Create User Page
	When Enter the testdata for User scenario '<ScenarioName>' and click on 'Create'
	Then Verify User has Created Successfully 
	Then View the user and verify the details as per '<ScenarioName>'
	Then Delete the newly created user
	
	Examples: 
	| Username      | Role               | ScenarioName            |
	| at.orgadmin33 | Organisation Admin | Orgadm_Createsupervisor |
	| at.orgadmin33 | Organisation Admin | Orgadm_CreateInspector  |
	| at.orgadmin33 | Organisation Admin | Orgadm_CreateTechnician |

Scenario Outline:  M3-6263 Site admin user creates different users
	Given Log in as a '<Role>' username as '<Username>'
	Then Navigate to Create User Page
	When Enter the testdata for User scenario '<ScenarioName>' and click on 'Create'
	Then Verify User has Created Successfully 
	Then View the user and verify the details as per '<ScenarioName>'
	Then Delete the newly created user
	
	Examples: 
	| Username       | Role               | ScenarioName               |
	| auto.siteadmin | Site Administrator | Siteadmin_Createsupervisor |
	| auto.siteadmin | Site Administrator | Siteadmin_CreateInspector  |
	| auto.siteadmin | Site Administrator | Siteadmin_CreateTechnician |


 
 Scenario Outline: M3-4132_ As Developer create new user and Assign the user to an existing Organisation
	Given Log in as a '<Role>' username as '<Username>'
	Then Navigate to Create User Page
	When Enter the testdata for User scenario '<ScenarioName>' and click on 'Create'
	Then Verify User has Created Successfully 
	Then View the user and verify the details as per '<ScenarioName>'
	Then Delete the newly created user
	Then Navigate to Organisation page
	And Delete newly created Organisation during user creation
	Examples: 	
	| Username     | Role      | ScenarioName            |
	| at.developer | developer | Dev_CreateOrgadminUser  |
	| at.developer | developer | Dev_CreateSiteadminUser |
	| at.developer | developer | Dev_CreateTechnician    |
	| at.developer | developer | Dev_CreateDeveloper     |
	| at.developer | developer | Dev_Inspector           |
	| at.developer | developer | Dev_CreateInstaller     |
	

 Scenario Outline: M3-4173 As Organisation admin create new user -assign site and building during User creation
	Given Log in as a '<Role>' username as '<Username>'
	Then Navigate to Create User Page
	When Enter the testdata for User scenario '<ScenarioName>' and click on 'Create'
	Then Verify User has Created Successfully 
	Then View the user and verify the details as per '<ScenarioName>'
	Then Navigate to gmail inbox 'https://accounts.google.com/o/oauth2/auth/identifier?client_id=717762328687-iludtf96g1hinl76e4lc1b9a82g457nn.apps.googleusercontent.com&scope=profile%20email&redirect_uri=https%3A%2F%2Fstackauth.com%2Fauth%2Foauth2%2Fgoogle&state=%7B%22sid%22%3A1%2C%22st%22%3A%2259%3A3%3ABBC%2C16%3A1eecb8a7974f9471%2C10%3A1597317729%2C16%3A99043642e73614fc%2Ce3817664fea97346761730f583d86dedd802bbd77c7654c5c88766dd27c1d09f%22%2C%22cdl%22%3Anull%2C%22cid%22%3A%22717762328687-iludtf96g1hinl76e4lc1b9a82g457nn.apps.googleusercontent.com%22%2C%22k%22%3A%22Google%22%2C%22ses%22%3A%225c466e726f33402ba23d2c5c5a11d83a%22%7D&response_type=code&flowName=GeneralOAuthFlow'  
	Then click on the create password URL
	Then Enter valid password '<PasswordsettingScenarioname>'
	Then Verify User logged in success and rackeye logo shown
	Then User logs out
	And  Log in as a '<Role>' username as '<Username>'
	Then Delete the newly created user
	Examples: 
	| Username      | Role               | ScenarioName          | PasswordsettingScenarioname |
	| at.orgadmin33 | Organisation Admin | OrgadmCreateSiteAdmin | CreateValidPassword         |	

 Scenario Outline: M3-4135 As Installer create new user and assign to a site and building
	Given Log in as a '<Role>' username as '<Username>'
	Then Navigate to Create User Page
	When Enter the testdata for User scenario '<ScenarioName>' and click on 'Create'
	Then Verify User has Created Successfully 
	Then View the user and verify the details as per '<ScenarioName>'
	Then Navigate to gmail inbox 'https://accounts.google.com/o/oauth2/auth/identifier?client_id=717762328687-iludtf96g1hinl76e4lc1b9a82g457nn.apps.googleusercontent.com&scope=profile%20email&redirect_uri=https%3A%2F%2Fstackauth.com%2Fauth%2Foauth2%2Fgoogle&state=%7B%22sid%22%3A1%2C%22st%22%3A%2259%3A3%3ABBC%2C16%3A1eecb8a7974f9471%2C10%3A1597317729%2C16%3A99043642e73614fc%2Ce3817664fea97346761730f583d86dedd802bbd77c7654c5c88766dd27c1d09f%22%2C%22cdl%22%3Anull%2C%22cid%22%3A%22717762328687-iludtf96g1hinl76e4lc1b9a82g457nn.apps.googleusercontent.com%22%2C%22k%22%3A%22Google%22%2C%22ses%22%3A%225c466e726f33402ba23d2c5c5a11d83a%22%7D&response_type=code&flowName=GeneralOAuthFlow'  
	Then click on the create password URL
	Then Enter valid password '<PasswordsettingScenarioname>'
	Then navigate to the web application 
	Then Delete the newly created user
	Examples: 
	| Username      | Role               | ScenarioName                | PasswordsettingScenarioname |
	| at.install    | Installer          | Installer_CreateTechnician  | CreateValidPassword         |

Scenario Outline: M3-6264 As Installer create new users
	Given Log in as a '<Role>' username as '<Username>'
	Then Navigate to Create User Page
	When Enter the testdata for User scenario '<ScenarioName>' and click on 'Create'
	Then Verify User has Created Successfully 
	Then View the user and verify the details as per '<ScenarioName>'
	Then Delete the newly created user
	Examples: 
	| Username      | Role               | ScenarioName                    | 
	| at.install    | Installer          | Installer_CreateInspector       | 
	| at.install    | Installer          | Installer_CreateSupervisor      | 
	| at.install    | Installer          | Installer_CreateSiteadmin       | 
	| at.install    | Installer          | Installer_CreateOrgadmin        | 	
 
 Scenario Outline: M3-6009 Developer create supervisor user
	Given Log in as a '<Role>' username as '<Username>'
	Then Navigate to Create User Page
	When Enter the testdata for User scenario '<ScenarioName>' and click on 'Create'
	Then Verify User has Created Successfully 
	Then View the user and verify the details as per '<ScenarioName>'
	Then Delete the newly created user
	
    Examples: 
	| Username     | Role      | ScenarioName         |
	| at.developer | developer | Dev_CreateSupervisor |


 Scenario Outline: M3-6010 Developer creates orgadmin user
	Given Log in as a '<Role>' username as '<Username>'
	Then Navigate to Create User Page
	When Enter the testdata for User scenario '<ScenarioName>' and click on 'Create'
	Then Verify User has Created Successfully 
	Then View the user and verify the details as per '<ScenarioName>'
	Then Delete the newly created user
	Examples: 
	| Username               | Role               | ScenarioName            |
	| at.developer | developer          | Dev_CreateOrgadminUser  |


Scenario Outline: M3-4184 Field validation in Create user page
	Given Log in as a '<Role>' username as '<Username>'
	Then Navigate to Create User Page
	And Click on Createuser without entering any data
	Then Verify inline error messages for mandatory fields on 'create' user page
	| fields       | errormessage                                           |
	| Username     | Invalid Username - must be at least 8 characters long. |
	| Emailaddress | Invalid email address.                                 |
	| Role         | Please select a role                                   |
    And Enter following testdata in Username field and validate errormessage on create page
	| Username   | errormessage                                           |
	| a          | Invalid Username - must be at least 8 characters long. |
	| at.install | Username already taken.                                |
    When Upload invalid file for Profilepicture in optional information section on 'create' user page
    Then Error Prompt for Invalid File Upload should display on 'create' user page
	And Enter following testdata in emailaddress field and validate errormessage on create user page
	| emailaddress                     | errormessage                                    |
	| yoga.asafe+at.orgadmin@gmail.com | Email address already registered to an account. |
	| asafepriyanka@com                | Invalid email address.                          |
	When Email address and Confirm email address not matching on create page
	| emailaddress            | confirmemailaddress                             |
	| asafepriyanka@gmail.com | asafe@gmail.com									|
    Then "Emails don't match." error message should be displayed for Confirm Email address field on 'create' user page
	And Enter following testdata in Phonenumber field and validate errormessage on 'create' user page
	| Phonenumber     | errormessage                            |
	| 6478            | Please enter valid mobile phone number. |
	| +44786433346782 | Please enter valid mobile phone number. |
	Then I Select a 'Supervisor' role on 'create' user page
	Then I Select a 'United Kingdom' country on 'create' user page
	And I Select a 'At_EclarisUK' organisation on 'create' user page	
	Then I assign a 'At_EclarisSite1' site on 'create' user page	
	Then I assign a 'At_EclarisBuilding1' building on 'create' user page	
	And Click on Cancelbutton  on 'create' user page
    Examples: 
	| Username     | Role      | ScenarioName           |
	| at.developer | developer | Dev_CreateOrgadminUser |

Scenario Outline: M3-4281/M3-4282 Field validation on Update user page
	Given Log in as a '<Role>' username as '<Username>'
	Then View the Username'at.orgadmin33' and click on Edit user link
	When Remove all the User details and click on update user button
	Then Verify inline error messages for mandatory fields on 'update' user page
	| fields       | errormessage                                           |
	| Username     | Invalid Username - must be at least 8 characters long. |
	| Emailaddress | Invalid email address.                                 |
	| Role         | Please select a role                                   |
    And Enter following testdata in Username field and validate errormessage on update page
	| Username       | errormessage                                           |
	| a              | Invalid Username - must be at least 8 characters long. |
	| auto.siteadmin | Username already taken.                                |
    When Upload invalid file for Profilepicture in optional information section on 'update' user page
    Then Error Prompt for Invalid File Upload should display on 'update' user page
	And Enter following testdata in emailaddress field and validate errormessage on update user page
	| emailaddress            | errormessage                                    |
	| asafepriyanka@gmail.com | Change of Email Address requires verification by the user themselves from their "My Profile" page. |
	| asafepriyanka@com       | Invalid email address.																			   |
	And Enter following testdata in Phonenumber field and validate errormessage on 'update' user page
	| Phonenumber     | errormessage                            |
	| 6478            | Please enter valid mobile phone number. |
	| +44786433346782 | Please enter valid mobile phone number. |
	Then I Select a 'Supervisor' role on 'update' user page
	Then I Select a 'United Kingdom' country on 'update' user page
	And I Select a 'At_EclarisUK' organisation on 'update' user page	
	Then I assign a 'At_EclarisSite1' site on 'update' user page	
	Then I assign a 'At_EclarisBuilding3' building on 'update' user page
	And Click on Cancelbutton  on 'update' user page	
    Examples: 
	| Username     | Role      | ScenarioName           |
	| at.developer | developer | Dev_CreateOrgadminUser |

Scenario Outline: M3-4256 Edit user details and assign existing organisation , Site and Building when updating user details.
	Given Log in as a '<Role>' username as '<Username>'
	Then Navigate to Create User Page
	When Enter the testdata for User scenario '<ScenarioName>' and click on 'Create'
	Then Verify User has Created Successfully 
	And  Navigate to the newly created user and edit user by clicking on Edit icon
	Then Remove all the User details 
	When Enter the testdata for User scenario '<EditScenarioName>' and click on 'Update'
	Then Verify User has Updated Successfully 
	Then View the user and verify the details as per '<EditScenarioName>'
	Then Delete the newly created user
    Examples: 
	| Username       | Role               | ScenarioName               | EditScenarioName                       |
	| at.developer   | developer          | Dev_CreateOrgadminUser     | UpdateUser_AssignOrgSiteBuilding       |
	| at.orgadmin33  | Organisation Admin | Orgadm_Createsupervisor    | UpdateSuperviser_AssignOrgSiteBuilding |
	| at.install     | Installer          | Installer_CreateTechnician | UpdateTechnician_AssignOrgSiteBuilding |
	| auto.siteadmin | Site Administrator | Siteadmin_Createsupervisor | UpdateSuperviser_AssignOrgSiteBuilding |


Scenario Outline: M3-4280 As organisation admin Update the user details
	Given Log in as a '<Role>' username as '<Username>'
	Then Navigate to Create User Page
	When Enter the testdata for User scenario '<ScenarioName>' and click on 'Create'
	Then Verify User has Created Successfully 
	And  Navigate to the newly created user and edit user by clicking on Edit icon
	Then Remove all the User details 
	When Enter the testdata for User scenario '<EditScenarioName>' and click on 'Update'
	Then Verify User has Updated Successfully 
	Then View the user and verify the details as per '<EditScenarioName>'
	Then Delete the newly created user
    Examples: 
	| Username          | Role               | ScenarioName           | EditScenarioName        |
	| at.orgadmin33     | Organisation Admin | EnterUserdetails       | Orgadmin_UpdateUser     |

Scenario Outline: M3-5909 Test Cancel button during User delete
	Given Log in as a '<Role>' username as '<Username>'
	When Onclick of Delete icon for any 'at.install'user 
	Then Delete 'at.install' user confirm page will display and Click on Cancel 
	Then View the Username'at.install' and click on Edit user link
	  Examples: 
	| Username     | Role      | 
	| at.developer | developer | 

Scenario Outline: M3-4284 Search functionality in User Page
	Given Log in as a '<Role>' username as '<Username>'
	When Enter the Search text with User Name 'Auto' and Tap on Search icon
	Then List of Username matching the search text'Auto' will be displayed
	
	Examples: 
	| Username     | Role      |  
	| at.developer | developer |

#Note -Precondition -Supervisor need to be created -supply testsdata scenario accordingly
Scenario Outline: M3-6007 Developer creates new user and verifies the Email address 
	Given Log in as a '<Role>' username as '<Username>'
	Then Navigate to Create User Page
	When Enter the testdata for User scenario '<ScenarioName>' and click on 'Create'
	Then Verify User has Created Successfully 
	Then View the user and verify the details as per '<ScenarioName>'	
	Then Navigate to gmail inbox 'https://accounts.google.com/o/oauth2/auth/identifier?client_id=717762328687-iludtf96g1hinl76e4lc1b9a82g457nn.apps.googleusercontent.com&scope=profile%20email&redirect_uri=https%3A%2F%2Fstackauth.com%2Fauth%2Foauth2%2Fgoogle&state=%7B%22sid%22%3A1%2C%22st%22%3A%2259%3A3%3ABBC%2C16%3A1eecb8a7974f9471%2C10%3A1597317729%2C16%3A99043642e73614fc%2Ce3817664fea97346761730f583d86dedd802bbd77c7654c5c88766dd27c1d09f%22%2C%22cdl%22%3Anull%2C%22cid%22%3A%22717762328687-iludtf96g1hinl76e4lc1b9a82g457nn.apps.googleusercontent.com%22%2C%22k%22%3A%22Google%22%2C%22ses%22%3A%225c466e726f33402ba23d2c5c5a11d83a%22%7D&response_type=code&flowName=GeneralOAuthFlow'  
	Then click on the create password URL
	Then Enter valid password '<Scenarioname>'
	Then Verify User logged in success and rackeye logo shown
	And Navigate to Myprofile
	And Verify Userdetails on MyProfile with the details as per '<ScenarioName>'
	Then User logs out
	And  Log in as a '<Role>' username as '<Username>'
	Then Delete the newly created user
	Examples: 
	| Username     | Role      | ScenarioName                   | Scenarioname        |
	| at.developer | developer | Passwordvalidation_Welcomemail | CreateValidPassword |

Scenario Outline:M3-6008 User updates Email address through MyProfile and verifies updated Email address
	Given Log in as a '<Role>' username as '<Username>'
	Then Navigate to Myprofile
	Then Update email address for User from Myprofile page
	Then Navigate to gmail inbox 'https://accounts.google.com/o/oauth2/auth/identifier?client_id=717762328687-iludtf96g1hinl76e4lc1b9a82g457nn.apps.googleusercontent.com&scope=profile%20email&redirect_uri=https%3A%2F%2Fstackauth.com%2Fauth%2Foauth2%2Fgoogle&state=%7B%22sid%22%3A1%2C%22st%22%3A%2259%3A3%3ABBC%2C16%3A1eecb8a7974f9471%2C10%3A1597317729%2C16%3A99043642e73614fc%2Ce3817664fea97346761730f583d86dedd802bbd77c7654c5c88766dd27c1d09f%22%2C%22cdl%22%3Anull%2C%22cid%22%3A%22717762328687-iludtf96g1hinl76e4lc1b9a82g457nn.apps.googleusercontent.com%22%2C%22k%22%3A%22Google%22%2C%22ses%22%3A%225c466e726f33402ba23d2c5c5a11d83a%22%7D&response_type=code&flowName=GeneralOAuthFlow'  
	Then click on confirm email change
	Then Verify email address successfully changes
	Examples: 
	| Username     | Role      | ScenarioName                   | Scenarioname        |
	| at.developer | developer | Passwordvalidation_Welcomemail | CreateValidPassword |