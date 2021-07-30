Feature: SiteCreateDelete





  Scenario Outline: M3-4298,M3-6023_Site creation deletion functionality
    Given Log in as a '<Role>' username as '<Username>'
    Then Navigate to Create Site Page
    When Enter the testdata to create Site '<ScenarioName>' and click on 'Create'
    Then Verify Site has Created Successfully
    Then View the Site and verify the details as per '<ScenarioName>'
    Then Navigate to Sites page
    And Delete newly created Site

    Examples:
      | Username     | Role      | ScenarioName     |
      | at.install   | Installer | Instalr_crt_site |
      | at.developer | developer | Dev_ctr_site     |

  Scenario Outline: M3-4299,M3-4303 As Organisation admin create site and add user in site creation
    Given Log in as a '<Role>' username as '<Username>'
    Then Navigate to Create Site Page
    When Enter the testdata to create Site '<ScenarioName>' and click on 'Create'
    Then Verify Site has Created Successfully
    Then View the Site and verify the details as per '<ScenarioName>'
    Then Delete the newly created user during site creation
    Then Navigate to Sites page
    And Delete newly created Site

    Examples:
      | Username      | Role               | ScenarioName    |
      | at.orgadmin33 | Organisation Admin | Orgadm_crt_site |

  Scenario Outline: M3-4300 and M3-4824 Field Validation On Create Site Page
    Given Log in as a '<Role>' username as '<Username>'
    Then Navigate to Create Site Page
    And Click on Create Site Button
    Then Verify inline error messages for fields on 'create' Site page
      | fields       | errormessage                   |
      | Country      | Please choose a country.       |
      | Timezone     | Please choose a timezone.      |
      | Organisation | Please choose an organisation. |
      | Sitename     | Please enter a site name.      |
      | Postcode     | Please enter a postcode.       |
    Then Select a country 'United Kingdom' then enter 'Europe/London' as timezone and 'At_EclarisUK' as organisation and enter 'At_EclarisSite1' as Sitename on 'create' Site page
    Then Click on Create Site Button
    Then Verify inline error messages for fields on 'create' Site page
      | fields   | errormessage            |
      | Sitename | Site name already taken |
    Then I click add new section on 'create' Site page
    Then Click on Create button in 'User' section on 'create' Site page
    Then Verify inline error messages for mandatory fields on 'create' Site page
      | fields       | errormessage                                           |
      | Username     | Invalid Username - must be at least 8 characters long. |
      | Emailaddress | Invalid email address.                                 |
    And Enter following testdata in Username field and validate errormessage on 'create' Site page
      | Username   | errormessage                                           |
      | a          | Invalid Username - must be at least 8 characters long. |
      | at.install | Username already taken.                                |
    And Enter following testdata in emailaddress field and validate errormessage on 'create' Site page
      | emailaddress                     | errormessage                                    |
      | yoga.asafe+at.orgadmin@gmail.com | Email address already registered to an account. |
      | asafepriyanka@com                | Invalid email address.                          |

    Examples:
      | Username     | Role      | ScenarioName |
      | at.developer | developer | Dev_ctr_site |

  Scenario Outline: M3-4823 and M3-4825 Field Validation On Update Site Page
    Given Log in as a '<Role>' username as '<Username>'
    Then View the Sitename'At_EclarisSite2' and click on Edit link
    When Remove all the Site details and click on update site button
    Then Verify inline error messages for fields on 'update' Site page
      | fields       | errormessage                   |
      | Country      | Please choose a country.       |
      | Timezone     | Please choose a timezone.      |
      | Organisation | Please choose an organisation. |
      | Sitename     | Please enter a site name.      |
      | Postcode     | Please enter a postcode.       |
    Then Select a country 'United Kingdom' then enter 'Europe/London' as timezone and 'At_EclarisUK' as organisation and enter 'At_EclarisSite1' as Sitename on 'update' Site page
    Then Click on Update Site Button
    Then Verify inline error messages for fields on 'update' Site page
      | fields   | errormessage            |
      | Sitename | Site name already taken |
    Then I click add new section on 'create' Site page
    Then Click on Create button in 'User' section on 'update' Site page
    Then Verify inline error messages for mandatory fields on 'update' Site page
      | fields       | errormessage                                           |
      | Username     | Invalid Username - must be at least 8 characters long. |
      | Emailaddress | Invalid email address.                                 |
    And Enter following testdata in Username field and validate errormessage on 'update' Site page
      | Username   | errormessage                                           |
      | a          | Invalid Username - must be at least 8 characters long. |
      | at.install | Username already taken.                                |
    And Enter following testdata in emailaddress field and validate errormessage on 'update' Site page
      | emailaddress                     | errormessage                                    |
      | yoga.asafe+at.orgadmin@gmail.com | Email address already registered to an account. |
      | asafepriyanka@com                | Invalid email address.                          |

    Examples:
      | Username     | Role      | ScenarioName |
      | at.developer | developer | Dev_ctr_site |

  Scenario Outline:M3-4301 and M3-4302 As developer/installer edit site details
    Given Log in as a '<Role>' username as '<Username>'
    Then  Navigate to Create Site Page
    When Enter the testdata to create Site '<ScenarioName>' and click on 'Create'
    Then Verify Site has Created Successfully
    Then Delete the newly created user during site creation
    Then Navigate to the newly created Site and edit by clicking on Edit icon
    Then Remove all the Site details
    When Enter the testdata to create Site '<EditScenarioName>' and click on 'Update'
    Then Verify Site has Updated Successfully
    Then View the Site and verify the details as per '<EditScenarioName>'
    Then Navigate to Sites page
    And Delete newly created Site
    Then Delete the newly created user during site creation

    Examples:
      | Username     | Role      | ScenarioName         | EditScenarioName      |
      | at.developer | developer | Dev_ctrsite_withuser | UpdateSite            |
      | at.install   | Installer | Instalr_crt_site     | Installer_Update_site |

  Scenario Outline: M3-4304 Verify Search functionality in Site Page
    Given Log in as a '<Role>' username as '<Username>'
    When Enter the Search text with Site Name 'At' and Tap on Search icon
    Then List of Sites matching the search text'At' will be displayed
	#When Remove search text by click on x icon on search field
	#Then Search text will be removed
    Examples:
      | Username     | Role      | ScenarioName            |
      | at.developer | developer | Devusr_crt_Organisation |