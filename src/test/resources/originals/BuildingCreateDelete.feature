Feature: BuildingCreateDelete

 
Scenario Outline:M3-4308 and M3-4311 Building creation and deletion functionality
	Given Log in as a '<Role>' username as '<Username>'
	Then Navigate to Create building Page
	When  Enter the testdata to building scenario '<ScenarioName>' and click on 'Create'
	Then Verify building has Created Successfully 
	Then View the building and verify the details as per '<ScenarioName>'	 
	Then Navigate to building page
	And Delete newly created building

	Examples: 
	| Username       | Role       | ScenarioName                |
	| auto.siteadmin | Site admin | AsSiteadmin_Createbuilding  |
	| at.developer   | developer  | AsDeveloper_Create_building |
 

Scenario Outline:M3-4309 Organisation admin creates building with user
	Given Log in as a '<Role>' username as '<Username>'
	Then Navigate to Create building Page
	When  Enter the testdata to building scenario '<ScenarioName>' and click on 'Create'
	Then Verify building has Created Successfully 
	Then View the building and verify the details as per '<ScenarioName>'	 
	Then Navigate to building page
	And Delete the newly created user during building creation
	Then Navigate to building page
	And Delete newly created building
	Examples: 
	| Username      | Role               | ScenarioName                       |
	| at.orgadmin33 | Organisation admin | AsOrgAdmin_Create_Building_witUser |
 
Scenario Outline: M3-4316 Field Validation On Create Building Page
	Given Log in as a '<Role>' username as '<Username>'
	Then  Navigate to Create building Page
	And Click on Create Building Button
	Then Verify inline error messages for fields on 'create' Building page
	| fields           | errormessage                       |
	| Country          | Please choose a country.           |	 
	| Organisation     | Please choose an organisation.     |
	| Sitename         | Please choose a site.		        |
	| Buildingname     | Please enter a building name.      |
	Then Select a country 'United Kingdom' then enter 'At_EclarisUK' as organisation and 'At_EclarisSite1' as Site and enter 'At_EclarisBuilding1' as Buildingname on 'create' Building page
	Then Click on Create Building Button
	Then Verify inline error messages for fields on 'create' Building page
	| fields	    | errormessage                     |
	| Buildingname  | Building name already taken.	   |
	Then I click add new section on 'create' Building page
	Then Click on Create button in 'User' section on 'create' Building page
	Then Verify inline error messages for mandatory fields on 'create' Building page
	| fields       | errormessage                                           |
	| Username     | Invalid Username - must be at least 8 characters long. |
	| Emailaddress | Invalid email address.                                 |
	And Enter following testdata in Username field and validate errormessage on 'create' Building page
	| Username   | errormessage                                           |
	| a          | Invalid Username - must be at least 8 characters long. |
	| at.siteadm | Username already taken.                                |
	And Enter following testdata in emailaddress field and validate errormessage on 'create' Building page
	| emailaddress                     | errormessage                                    |
	| yoga.asafe+at.orgadmin@gmail.com | Email address already registered to an account. |
	| asafepriyanka@com                | Invalid email address.                          |

Examples: 
	| Username     | Role      | ScenarioName                |
	| at.developer | developer | AsDeveloper_Create_building |

Scenario Outline: M3-5875 Field Validation On Update Building Page
	Given Log in as a '<Role>' username as '<Username>'
	Then View the Buildingname'At_EclarisBuilding1' and click on Edit link
	When Remove all the Building details '<Role>' and click on update building button
	Then Verify inline error messages for fields on 'update' Building page
	| fields           | errormessage                       |
	| Country          | Please choose a country.           |	 
	| Organisation     | Please choose an organisation.     |
	| Sitename         | Please choose a site.		        |
	| Buildingname     | Please enter a building name.      |
	Then Select a country 'United Kingdom' then enter 'At_EclarisUK' as organisation and 'At_EclarisSite1' as Site and enter 'At_EclarisBuilding2' as Buildingname on 'update' Building page
	Then Click on Update Building Button
	Then Verify inline error messages for fields on 'update' Building page
	| fields	    | errormessage                     |
	| Buildingname  | Building name already taken.	   |
	Then I click add new section on 'update' Building page
	Then Click on Create button in 'User' section on 'update' Building page
	Then Verify inline error messages for mandatory fields on 'update' Building page
	| fields       | errormessage                                           |
	| Username     | Invalid Username - must be at least 8 characters long. |
	| Emailaddress | Invalid email address.                                 |
	And Enter following testdata in Username field and validate errormessage on 'update' Building page
	| Username      | errormessage                                           |
	| a             | Invalid Username - must be at least 8 characters long. |
	| at.orgadmin33 | Username already taken.                                |
	And Enter following testdata in emailaddress field and validate errormessage on 'update' Building page
	| emailaddress                     | errormessage                                    |
	| yoga.asafe+at.orgadmin@gmail.com | Email address already registered to an account. |
	| asafepriyanka@com                | Invalid email address.                          |

Examples: 
	| Username     | Role      | ScenarioName                |
	| at.developer | developer | AsDeveloper_Create_building |

Scenario Outline: M3-5902 Edit Building details login as a Developer  
	Given Log in as a '<Role>' username as '<Username>'
	Then Navigate to Create building Page
	When Enter the testdata to building scenario '<ScenarioName>' and click on 'Create'
	Then  Verify building has Created Successfully  
	Then Delete the newly created user during building creation
	Then Navigate to the newly created Building and edit by clicking on Edit icon
	Then Remove all the Building details for '<Role>' 
	When Enter the testdata to building scenario '<EditScenarioName>' and click on 'Update'
	Then Verify building has Updated Successfully
	Then  View the building and verify the details as per '<EditScenarioName>' 
	Then Navigate to building page
	And Delete the newly created user during building Update
	Then Navigate to building page
	And Delete newly created building
	
	Examples: 
	| Username     | Role      | ScenarioName                         | EditScenarioName           |
	| at.developer | developer | Asdeveloper_Create_building_withuser | Asdeveloper_UpdateBuilding |

Scenario Outline: M3-4310 Edit Building details login as Organisation Admin
	Given Log in as a '<Role>' username as '<Username>'
	Then Navigate to Create building Page
	When Enter the testdata to building scenario '<ScenarioName>' and click on 'Create'
	Then  Verify building has Created Successfully  
	Then Delete the newly created user during building creation
	Then Navigate to the newly created Building and edit by clicking on Edit icon
	Then Remove all the Building details for '<Role>' 
	When Enter the testdata to building scenario '<EditScenarioName>' and click on 'Update'
	Then Verify building has Updated Successfully
	Then  View the building and verify the details as per '<EditScenarioName>' 
	Then Navigate to building page
	And Delete the newly created user during building Update
	Then Navigate to building page
	And Delete newly created building
	
	Examples: 
	| Username      | Role               | ScenarioName                       | EditScenarioName          |
	| at.orgadmin33 | Organisation admin | AsOrgAdmin_Create_Building_witUser | AsOrgAdmin_UpdateBuilding |

Scenario Outline: M3-4312 Search functionality in Buildings Page
	Given Log in as a '<Role>' username as '<Username>'
	When Enter the Search text with Buiding Name 'At' and Tap on Search icon
	Then List of Building matching the search text'At' will be displayed
	#When Remove search text by click on x icon on search field
	#Then Search text will be removed
	Examples: 
	| Username     | Role      | ScenarioName                |
	| at.developer | developer | AsDeveloper_Create_building |