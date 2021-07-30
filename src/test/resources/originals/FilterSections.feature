Feature: Filtersections
	 
	Scenario Outline:M3-4285_As developer Verify Filters on User Page
	Given Log in as a '<Role>' username as '<Username>'
	Then Navigate to View User page 
	And Load testdata for Filter scenario '<ScenarioName>' 
	And Apply Country filter
	And Verify list of Organisation showing in Organisation dropdown
	And Apply Organisation filter
	And Verify list of Sites showing in Site dropdown
 	And Apply Site filter
	And Verify Building filter options listed in dropdown
	And Apply Building filter
	And Verify Role filter options listed in dropdown
	And Apply Role filter
	Then Verify filter applied showing on filter tag
	And Verify list of Users showing based on filter criteria 
	Then Remove filter from filtertag '<ScenarioNameForRemove>'
    And Verify list of Users showing based on filter criteria 
	And 
	Examples: 
	| Username               | Role      | ScenarioName                    | ScenarioNameForRemove |
	| yoganandhini.shanmugam | developer | AsDeveloperVerifyUserPageFilter |                       |



	Scenario Outline:M3-5998_As organisationadmin Verify Filters on User Page
	Given Log in as a '<Role>' username as '<Username>'
	Then Navigate to View User page 
	And Load testdata for Filter scenario '<ScenarioName>' 
   	And Apply Site filter
	And Verify Building filter options listed in dropdown
	And Apply Building filter
	#And Verify Role filter options listed in dropdown
	And Apply Role filter
	Then Verify filter applied showing on filter tag
	And Verify list of Users showing based on filter criteria 
	Examples: 
	| Username        | Role      | ScenarioName                   |
	| yogaus.orgadmin | developer | AsOrgAdminVerifyUserPageFilter |


	Scenario Outline:M3-4286_As Siteadmin Verify Filters on User Page
	Given Log in as a '<Role>' username as '<Username>'
	Then Navigate to View User page 
	And Load testdata for Filter scenario '<ScenarioName>' 
  	And Apply Site filter
	And Verify Building filter options listed in dropdown
	And Apply Building filter
	#And Verify Role filter options listed in dropdown
	And Apply Role filter
	Then Verify filter applied showing on filter tag
	And Verify list of Users showing based on filter criteria 
	Examples: 
	| Username       | Role      | ScenarioName                    |
	| yogaus.siteadm | developer | AsSiteadminVerifyUserPageFilter |


	Scenario Outline:M3-5999_As Installer Verify Filters on User Page
	Given Log in as a '<Role>' username as '<Username>'
	Then Navigate to View User page 
	And Load testdata for Filter scenario '<ScenarioName>' 
 	And Apply Site filter
	And Verify Building filter options listed in dropdown
	And Apply Building filter
	#And Verify Role filter options listed in dropdown
	And Apply Role filter
	Then Verify filter applied showing on filter tag
	And Verify list of Users showing based on filter criteria 
	Examples: 
	| Username         | Role      | ScenarioName                    |
	| yogaus.installer | developer | AsInstallerVerifyUserPageFilter |



    Scenario Outline:M3-4296_As developer Verify Filters on Organisation Page
	Given Log in as a '<Role>' username as '<Username>'
	Then Navigate to Organisation page 
	And Load testdata for Filter scenario '<ScenarioName>' 
	And Apply Country filter
	Then Verify filter applied showing on filter tag
	And Verify list of Organisation showing based on filter criteria 
	Examples: 
	| Username               | Role      | ScenarioName                            |
	| yoganandhini.shanmugam | developer | AsDeveloperVerifyOrganisationPageFilter |


   Scenario Outline:M3-4305_As developer Verify filters on Site Page
	Given Log in as a '<Role>' username as '<Username>'
	Then Navigate to Sites page 
	And Load testdata for Filter scenario '<ScenarioName>' 
	And Apply Country filter
	And Verify list of Organisation showing in Organisation dropdown
	And Apply Organisation filter
	Then Verify filter applied showing on filter tag
	And Verify list of Site showing based on filter criteria 
	Examples: 
	| Username               | Role      | ScenarioName                     |
	| yoganandhini.shanmugam | developer | AsDeveloperVerifySiterPageFilter |

	Scenario Outline:M3-4314_As developer Verify filters on buildings Page
	Given Log in as a '<Role>' username as '<Username>'
	Then Navigate to building page 
	And Load testdata for Filter scenario '<ScenarioName>' 
	And Apply Country filter
	And Verify list of Organisation showing in Organisation dropdown
	And Apply Organisation filter
	And Verify list of Sites showing in Site dropdown
 	And Apply Site filter
	Then Verify filter applied showing on filter tag
	And Verify list of buildings showing based on filter criteria 
	Examples: 
	| Username               | Role      | ScenarioName                        |
	| yoganandhini.shanmugam | developer | AsDeveloperVerifyBuildingPageFilter |

	Scenario Outline:M3-6000_As Organisationadmin verify filters on buildings Page
	Given Log in as a '<Role>' username as '<Username>'
	Then Navigate to building page 
	And Load testdata for Filter scenario '<ScenarioName>' 
  	And Apply Site filter
	Then Verify filter applied showing on filter tag
	And Verify list of buildings showing based on filter criteria 
	Examples: 
	| Username         | Role               | ScenarioName                        |
	| yogaus.orgadmin  | Organisation Admin | AsOrgadminVerifyBuildingPageFilter  |

	Scenario Outline:M3-6001_As Siteadmin Verify filters on buildings Page
	Given Log in as a '<Role>' username as '<Username>'
	Then Navigate to building page 
	And Load testdata for Filter scenario '<ScenarioName>' 
  	And Apply Site filter
	Then Verify filter applied showing on filter tag
	And Verify list of buildings showing based on filter criteria 
	Examples: 
	| Username         | Role               | ScenarioName                        |
	| yogaus.siteadm   | Site Admin         | AsSiteadminVerifyBuildingPageFilter |

	Scenario Outline:M3-6002_As Installer Verify filters on buildings Page
	Given Log in as a '<Role>' username as '<Username>'
	Then Navigate to building page 
	And Load testdata for Filter scenario '<ScenarioName>' 
  	And Apply Site filter
	Then Verify filter applied showing on filter tag
	And Verify list of buildings showing based on filter criteria 
	Examples: 
	| Username         | Role               | ScenarioName                        |
 	| yogaus.installer | Installer          | AsInstallerVerifyBuildingPageFilter |


	Scenario Outline:As developer Verify filters on Zones Page
	Given Log in as a '<Role>' username as '<Username>'
	Then Navigate to zones page 
	And Load testdata for Filter scenario '<ScenarioName>' 
	And Apply Country filter
	And Verify list of Organisation showing in Organisation dropdown
	And Apply Organisation filter
	And Verify list of Sites showing in Site dropdown
 	And Apply Site filter
	And Verify Building filter options listed in dropdown
	And Apply Building filter
	And Apply Gateway filter
	Then Verify filter applied showing on filter tag
	And Verify list of Zones showing based on filter criteria 
	Examples: 
	| Username               | Role      | ScenarioName                    |
	| yoganandhini.shanmugam | developer | AsDeveloperVerifyZonePageFilter |


	Scenario Outline:As developer Verify filters on Aisles Page
	Given Log in as a '<Role>' username as '<Username>'
	Then Navigate to zones page 
	And Load testdata for Filter scenario '<ScenarioName>' 
	And Apply Country filter
	And Verify list of Organisation showing in Organisation dropdown
	And Apply Organisation filter
	And Verify list of Sites showing in Site dropdown
 	And Apply Site filter
	And Verify Building filter options listed in dropdown
	And Apply Building filter
	And Apply Gateway filter
	And Apply Zone filter
	Then Verify filter applied showing on filter tag
	And Verify list of Aisles showing based on filter criteria 
	Examples: 
	| Username               | Role      | ScenarioName                     |
	| yoganandhini.shanmugam | developer | AsDeveloperVerifyAislePageFilter |


	Scenario Outline:As developer Verify filters on Legs Page
	Given Log in as a '<Role>' username as '<Username>'
	Then Navigate to Legs page 
	And Load testdata for Filter scenario '<ScenarioName>' 
	And Apply Country filter
	And Verify list of Organisation showing in Organisation dropdown
	And Apply Organisation filter
	And Verify list of Sites showing in Site dropdown
 	And Apply Site filter
	And Verify Building filter options listed in dropdown
	And Apply Building filter
	And Apply Gateway filter
	And Apply Zone filter
	And Apply Aisle filter
	And Apply Rackeye filter
	Then Verify filter applied showing on filter tag
	And Verify list of Legs showing based on filter criteria 
	Examples: 
	| Username               | Role      | ScenarioName                   |
	| yoganandhini.shanmugam | developer | AsDeveloperVerifyLegPageFilter |