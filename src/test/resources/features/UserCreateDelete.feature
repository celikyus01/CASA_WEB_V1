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


  @create1
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
    And the user navigates to My Profile
    And verifies the informations are matching in My Profile page
    And logout from user
    And the user login in as a developer
    Then Delete the newly created user
    Examples:
      | Scenarioname    | LoggedInRole | emailRecieved              | emailPassword |
#      | Dev_CreateDev1  | Developer    | testemailfathom3@gmail.com | Password001!  |
      | Dev_CreateTech1 | Technician   | testemailfathom3@gmail.com | Password001!  |


#Scenario Outline: Select incompetable Roles together and verify it is not possible