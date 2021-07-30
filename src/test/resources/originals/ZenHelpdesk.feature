Feature: ZenHelpdesk

#@E2E @UserManagement 

Scenario Outline: Submit Zen desk request
	Given Log in as a '<Role>' username as '<Username>'
	When Click Help icon on bottom left corner
	Then Zen desk prompt will open
	And Verify name and Email address fields are auto populated with User's name and email
	And Edit the username and email
	And Enter the description 'Where are my Rackeyes? Please help @ address of RE2'  on 'How can I help you' field
	And add 5 attachments by click on attachment link
	And Click on Send
	Then Request message has been sent
	And User receives the email regarding the request number from zen desk
	When Asafe representative replies to customer request
	Then Customer will received email from Asafe representative
	Examples: 
	| Username      | Role               | ScenarioName            |
	| at.orgadmin33 | Organisation Admin | Orgadm_Createsupervisor |