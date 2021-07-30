Feature: OrganisationCreateDelete
 
Scenario Outline: M3-4291 Developer user creates organisation with user
	Given Log in as a '<Role>' username as '<Username>'
	Then Navigate to Create Organisation Page
	When Enter the testdata for organisation scenario '<ScenarioName>' and click on 'Create'
	Then Verify Organisation has Created Successfully 
	Then View the Organisation and verify the details as per '<ScenarioName>'	 
	Then Navigate to Organisation page
	And Delete newly created Organisation
	Then Delete the newly created user during organisation creation

	Examples: 
	| Username     | Role      | ScenarioName                    |
	| at.developer | developer | Devusr_crt_Organisation_wituser |
	 

Scenario Outline: M3-4290, M3-4294 Developer user creates organisation
	Given Log in as a '<Role>' username as '<Username>'
	Then Navigate to Create Organisation Page
	When Enter the testdata for organisation scenario '<ScenarioName>' and click on 'Create'
	Then Verify Organisation has Created Successfully 
	Then View the Organisation and verify the details as per '<ScenarioName>'	 
	Then Navigate to Organisation page
	And Delete newly created Organisation
	
	Examples: 
	| Username     | Role      | ScenarioName            |
	| at.developer | developer | Devusr_crt_Organisation |

Scenario Outline: M3-4292 Field Validation On Create Organisation Page
	Given Log in as a '<Role>' username as '<Username>'
	Then Navigate to Create Organisation Page
	And Click on Create Organisation Button
	Then Verify inline error messages for fields on 'create' Organisation page
	| fields           | errormessage                       |
	| Country          | Please choose a country.           |	 
	| Organisationname | Please enter an organisation name. |
	| Currency         | Please choose a currency.          |
	| Language         | Please choose a language.          |
	| Customer ID      | Please enter the Customer ID.      |
	Then Select a country 'United Kingdom' and enter 'At_EclarisUK' as Organisation Name on 'create' Organisation page
	Then Click on Create Organisation Button
	Then Verify inline error messages for fields on 'create' Organisation page
	| fields		        | errormessage                             |
	| Organisationname      | Organisation Name already used.		   |
	When Upload invalid file for Profilepicture in optional information section on 'create' Organisation page
    Then Error Prompt for Invalid File Upload should display on 'create' Organisation page
	Then I click add new 'Organisation' section on 'create' Organisation page
	Then Click on Create button in 'User' section on 'create' Organisation page
	Then Verify inline error messages for mandatory fields on 'create' Organisation page
	| fields       | errormessage                                           |
	| Username     | Invalid Username - must be at least 8 characters long. |
	| Emailaddress | Invalid email address.                                 |
	And Enter following testdata in Username field and validate errormessage on 'create' Organisation page
	| Username   | errormessage                                           |
	| a          | Invalid Username - must be at least 8 characters long. |
	| at.install | Username already taken.                                |
	And Enter following testdata in emailaddress field and validate errormessage on 'create' Organisation page
	| emailaddress                     | errormessage                                    |
	| yoga.asafe+at.orgadmin@gmail.com | Email address already registered to an account. |
	| asafepriyanka@com                | Invalid email address.                          |

Examples: 
	| Username     | Role      | ScenarioName            |
	| at.developer | developer | Devusr_crt_Organisation |

Scenario Outline: M3-5951 Field Validation On Update Organisation Page
	Given Log in as a '<Role>' username as '<Username>'
	Then View the Organisationname'Automation_USAORG' and click on Edit link
	When Remove all the Organisation details and click on update organisation button
	Then Verify inline error messages for fields on 'update' Organisation page
	| fields           | errormessage                       |
	| Country          | Please choose a country.           |	 
	| Organisationname | Please enter an organisation name. |
	| Language         | Please choose a language.          |
	| Customer ID      | Please enter the Customer ID.      |
	Then Select a country 'United Kingdom' and enter 'At_EclarisUK' as Organisation Name on 'update' Organisation page
	Then Click on Update Organisation Button
	Then Verify inline error messages for fields on 'update' Organisation page
	| fields		        | errormessage                             |
	| Organisationname      | Organisation Name already used.		   |
	When Upload invalid file for Profilepicture in optional information section on 'update' Organisation page
    Then Error Prompt for Invalid File Upload should display on 'update' Organisation page
	Then I click add new 'Organisation' section on 'update' Organisation page
	Then Click on Create button in 'User' section on 'update' Organisation page
	Then Verify inline error messages for mandatory fields on 'update' Organisation page
	| fields       | errormessage                                           |
	| Username     | Invalid Username - must be at least 8 characters long. |
	| Emailaddress | Invalid email address.                                 |
	Then Enter following testdata in Username field and validate errormessage on 'update' Organisation page
	| Username   | errormessage                                           |
	| a          | Invalid Username - must be at least 8 characters long. |
	| at.install | Username already taken.                                |
	And Enter following testdata in emailaddress field and validate errormessage on 'update' Organisation page
	| emailaddress                     | errormessage                                    |
	| yoga.asafe+at.orgadmin@gmail.com | Email address already registered to an account. |
	| asafepriyanka@com                | Invalid email address.                          |

Examples: 
	| Username     | Role      | ScenarioName            |
	| at.developer | developer | Devusr_crt_Organisation |

Scenario Outline:M3-4293 As developer edit Organisation details
	Given Log in as a '<Role>' username as '<Username>'
	Then Navigate to Create Organisation Page
	When Enter the testdata for organisation scenario '<ScenarioName>' and click on 'Create'
	Then Verify Organisation has Created Successfully 
	Then Delete the newly created user during organisation creation
	Then Navigate to the newly created Organisation and edit by clicking on Edit icon
	Then Remove all the Organisation details
	When Enter the testdata for organisation scenario '<EditScenarioName>' and click on 'Update'
	Then Verify Organisation has Updated Successfully 
	Then View the Organisation and verify the details as per '<EditScenarioName>'	 
	Then Navigate to Organisation page
	And Delete newly created Organisation
	Then Delete the newly created user during organisation creation

	Examples: 
	| Username     | Role      | ScenarioName                    | EditScenarioName   |
	| at.developer | developer | Devusr_crt_Organisation_wituser | UpdateOrganisation |

Scenario Outline: M3-4295 Verify Search functionality in Organisation Page
	Given Log in as a '<Role>' username as '<Username>'
	When Enter the Search text with Organisation Name 'At' and Tap on Search icon
	Then List of Organisations matching the search text'At' will be displayed
	#When Remove search text by click on x icon on search field
	#Then Search text will be removed
	Examples: 
	| Username     | Role      | ScenarioName            |
	| at.developer | developer | Devusr_crt_Organisation |