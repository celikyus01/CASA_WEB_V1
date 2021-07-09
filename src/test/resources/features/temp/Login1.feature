@login
Feature: Authentication login

  Scenario Outline: M3-4392_Verify Login failure scenario
    Given I am on Login Screen
    Then Enter Username '<ValidUsername>' and Password '<InvalidPassword>'
    And Click on Login
    Then Verify Inline error message for Username field 'Incorrect Username' displayed
    And Verify Inline error message for Password field 'Incorrect Password' displayed
    Then Enter Username '<InvalidUsername>' and Password '<ValidPassword>'
    And Click on Login
    Then Verify Inline error message for Username field 'Incorrect Username' displayed
    And Verify Inline error message for Password field 'Incorrect Password' displayed
    Examples:
      | ValidUsername | InvalidPassword | InvalidUsername | ValidPassword |
      | Swathi.priya  | Vashvika        | Swathi          | Vashvika14@   |

  @login_succesful
  Scenario Outline: Succesful Login
    Given I am on Login Screen
    When Enter Username "<ValidUsername>" and Password "<ValidPassword>"
    And Click on Login
    Then Verify page title as "Dashboard"
    Examples:
      | ValidUsername   | ValidPassword |
      | yusuf_inspector | Adana001@     |



