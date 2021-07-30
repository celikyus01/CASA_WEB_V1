Feature: RoleCreateDelete
	 

@mytag @Ignore
Scenario Outline:Role CRUD functionality
	Given Log in as a '<Role>' username as '<Username>'
	Then Navigate to Create role Page
	When Enter the testdata to create role '<ScenarioName>' and click on Create
	Then Verify role has Created Successfully 
	Then View the role and verify the details as per '<ScenarioName>'	 
	Then Navigate to role page
	And Delete newly created role

	Examples: 
	| Username               | Role      | ScenarioName |
	| yoganandhini.shanmugam | developer | CreateRole   |
 
