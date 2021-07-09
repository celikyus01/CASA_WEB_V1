Feature: MyProfile
	
Scenario Outline: M3-4354 View My Profile details
	Given Log in as a '<Role>' username as '<Username>'
	Then  Navigate to Myprofile
	And User displayed with My Profile page
	Then Verify Personal information section as per '<ScenarioName>'
	And Verify Profile picture apprears if user set profile picture
	Then Verify Site Assigned section has list of sites users assgined as per '<ScenarioName>'
	And Verify Buildings assigned section has list of buildings user assigned as per '<ScenarioName>'
	Examples: 
	| Username    | Role			   | ScenarioName			|
	| priya_admin | Organisation Admin |  ViewMyProfile         |


Scenario Outline: M3-4355 Edit My Profile details
	Given Log in as a '<Role>' username as '<Username>'
	Then  Navigate to Myprofile
	And User displayed with My Profile page
	Then Edit Personal information section  title , First name , middle name , surname , Email and Contact number as per '<ScenarioName>'
	And If user has set the profile picture then remove it and upload new picture
	And Click on save
	Then Your profile has been updated success page displayed
	Then  Navigate to Myprofile
	And Verify updated information are displayed as per '<ScenarioName>'
	Examples: 
	| Username						 | Role				  | ScenarioName		|
	| MyProfile_AutoUser_DoNOTDelete | Supervisor         |  EditMyProfile      |

Scenario Outline: M3-4356 Field validation on My Profile page
	Given Log in as a '<Role>' username as '<Username>'
	Then  Navigate to Myprofile
	When User leaves all fields blank and Click on Save
	Then Inline error message is displayed for Email
	When Upload invalid file for Profilepicture in optional information section 
	Then Error prompt is displayed Invalid file, please upload only files with extensions: png, jpg, jpeg, svg should be displayed
	When Invalid email address entered as'+98@£££'
	Then Inline Validation error message is displayed for email field
	And Enter following testdata in Phonenumber field and validate errormessage on myprofile page
	| Phonenumber       | errormessage                            |
	| 6478              | Please enter valid mobile phone number. |
	| +4478643334678208 | Please enter valid mobile phone number. |
	Examples: 
	| Username						 | Role				  | ScenarioName		|
	| MyProfile_AutoUser_DoNOTDelete | Supervisor         |  EditMyProfile      |

Scenario Outline:M3-4357 Email address change request Verification
	Given Log in as a '<Role>' username as '<Username>'
	Then Navigate to Myprofile
	Then Update email address for User from Myprofile page
	Then Navigate to gmail inbox 'https://accounts.google.com/o/oauth2/auth/identifier?client_id=717762328687-iludtf96g1hinl76e4lc1b9a82g457nn.apps.googleusercontent.com&scope=profile%20email&redirect_uri=https%3A%2F%2Fstackauth.com%2Fauth%2Foauth2%2Fgoogle&state=%7B%22sid%22%3A1%2C%22st%22%3A%2259%3A3%3ABBC%2C16%3A1eecb8a7974f9471%2C10%3A1597317729%2C16%3A99043642e73614fc%2Ce3817664fea97346761730f583d86dedd802bbd77c7654c5c88766dd27c1d09f%22%2C%22cdl%22%3Anull%2C%22cid%22%3A%22717762328687-iludtf96g1hinl76e4lc1b9a82g457nn.apps.googleusercontent.com%22%2C%22k%22%3A%22Google%22%2C%22ses%22%3A%225c466e726f33402ba23d2c5c5a11d83a%22%7D&response_type=code&flowName=GeneralOAuthFlow'  
	Then click on confirm email change
	Then Verify email address successfully changes
	Examples: 
	| Username						 | Role				  | ScenarioName		|
	| MyProfile_AutoUser_DoNOTDelete | Supervisor         |  EditMyProfile      |
