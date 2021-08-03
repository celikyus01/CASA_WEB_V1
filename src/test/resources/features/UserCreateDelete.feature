Feature: UserCreateDelete

  @create
  Scenario Outline: M3-6011/M3-4283 Organisation admin user creates Supervisor userxxx
    Given Log in as a "<LoggedInRole>"
    When Navigate to "Users" page
    When the user clicks on the "Add User"
    And the user clicks on the "Optional Information"
    When Enter the testdata for the "<Scenarioname>" scenario in Usermanagement sheet
    When the user click on Create User button
    Then Verify User has Created Successfully
    When the user clicks on the "View Users"
    Then View the user and verify the details are correct
    Then Delete the newly created user
    Examples:
      | Scenarioname          | LoggedInRole |
      | Dev_CreateTechnician1 | Developer    |


  @create11
  Scenario Outline: Create Dev    --M3-6011/M3-4283 Organisation admin user creates Supervisor userxxx - E2E-1
    Given Log in as a "<LoggedInRole>"
    When Navigate to "Users" page
    When the user clicks on the "Add User"
    And the user clicks on the "Optional Information"
    When Enter the testdata for the "<Scenarioname>" scenario in Usermanagement sheet
    When the user click on Create User button
    Then Verify User has Created Successfully
    When the user gets all sites and buildings assigned to organisation
#    When the user clicks on the "View Users"
    Then View the user and verify the details are correct
    When the user gets the create password link on "<emailRecieved>" , "<emailPassword>"
    When logout from user
    When Then Click on Reset password link
    When the user create the password
    And the user verifies successful registration
    And the user navigates to My Profile
    And verifies the informations are matching in My Profile page
    And logout from user
    And the user login in as a developer
    Then Delete the newly created user
    Examples:
      | Scenarioname               | LoggedInRole      | emailRecieved              | emailPassword |
#      | orgAdmin_CreateSupervisor1 | OrganisationAdmin | testemailfathom3@gmail.com | Password001!  |
      | orgAdmin_CreateSiteadminUser1 | OrganisationAdmin | testemailfathom3@gmail.com | Password001!  |
#      | orgAdmin_CreateSiteadminUser2 | OrganisationAdmin | testemailfathom3@gmail.com | Password001!  |
#      | Dev_CreateInstaller1          | Developer         | testemailfathom3@gmail.com | Password001!  |
#      | Dev_CreateDev1                | Developer         | testemailfathom3@gmail.com | Password001!  |
#      | Dev_CreateSiteadminUser1      | Developer         | testemailfathom3@gmail.com | Password001!  |
#      | Dev_CreateOrgadmin1           | Developer         | testemailfathom3@gmail.com | Password001!  |
#      | Dev_CreateSupervisor1         | Developer         | testemailfathom3@gmail.com | Password001!  |
#      | Dev_CreateSupervisor2         | Developer         | testemailfathom3@gmail.com | Password001!  |
#      | Dev_CreateInspector1          | Developer         | testemailfathom3@gmail.com | Password001!  |
#      | Installer_SiteAdmin_Supervisor1 | Installer    | testemailfathom3@gmail.com | Password001!  |



  @create12 @nonWebUser
  Scenario Outline: M3-6011/M3-4283 Organisation admin user creates Supervisor userxxx - E2E
    Given Log in as a "<LoggedInRole>"
    When Navigate to "Users" page
    When the user clicks on the "Add User"
    And the user clicks on the "Optional Information"
    When Enter the testdata for the "<Scenarioname>" scenario in Usermanagement sheet
    When the user click on Create User button
    Then Verify User has Created Successfully
    When the user clicks on the "View Users"
    Then View the user and verify the details are correct
    When logout from user
    When Then Click on Reset password link on "<emailRecieved>" , "<emailPassword>"
    When the user create the password
    And the user verifies successful registration
#    And the user navigates to My Profile
#    And verifies the informations are matching in My Profile page
#    And logout from user
    And the user login in as a developer
    Then Delete the newly created user
    Examples:
      | Scenarioname                           | LoggedInRole | emailRecieved              | emailPassword |
#      | Dev_CreateTech1                        | Developer         | testemailfathom3@gmail.com | Password001!  |
#      | orgAdmin_CreateTech1                   | OrganisationAdmin | testemailfathom3@gmail.com | Password001!  |
      | Inspector_Create_Inspector_Technician1 | Installer    | testemailfathom3@gmail.com | Password001!  |
  #      | Installer_CreateFloorplan1      | Installer    | testemailfathom3@gmail.com | Password001!  |


#Scenario Outline: Select incompetable Roles together and verify it is not possible
#  User cannot create