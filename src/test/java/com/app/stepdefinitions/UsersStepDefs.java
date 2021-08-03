package com.app.stepdefinitions;


import com.app.pages.*;
import com.app.utilities.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.*;

import static org.junit.Assert.*;

public class UsersStepDefs {

    UsersPage usersPage = new UsersPage();
    private String username;
    private String email;
    private String confirmEmail;
    private String title;
    private String firstName;
    private String middleName;
    private String surname;
    private String phone;
    private List assignedRoleList = new ArrayList<>();
    private String country;
    private String organistaion;
    private HashMap<String, Object> userDataExcel;
    private String webAppUse;
    private String temPassword;
    private List<String> sites;
    private List<String> buildings;


    private List<String> expectedSites;
    Random rd = new Random();
    private String link;
    private Map<String, String> dataMap;


    @When("Enter the testdata for the {string} scenario in Usermanagement sheet")
    public void enterTheTestdataForTheScenarioInUsermanagementSheet(String scenario) {



        //connect to excel
        ExcelUtil excelUtil = new ExcelUtil("Usermanagement");
        //collect all data in ListOfMap
        List<Map<String, String>> dataList = excelUtil.getDataList();
        //find the correspounding scenario in the list
        this.dataMap = dataList.stream().filter(l -> l.get("Scenarioname").equals(scenario)).findFirst().get();
        System.out.println("scenario name= " + dataMap.get("Scenarioname"));


        int a = rd.nextInt(100000000);
        //get all data
        this.username = (dataMap.get("Username") + "_" + a).toLowerCase();
        System.out.println("Generated username: " + this.username);
        this.email = this.confirmEmail = (dataMap.get("Emailprefix") + a + "@" + dataMap.get("Emaildomain")).toLowerCase();
        System.out.println("Generatied email: " + this.email);
        BrowserUtils.log("Generated username: " + this.username + "Generatied email: " + this.email);
        this.title = dataMap.get("Title");
        this.firstName = dataMap.get("Firstname");
        this.middleName = dataMap.get("Middlename");
        this.surname = dataMap.get("Surname");
        this.phone = dataMap.get("Contactnumber");
        this.assignedRoleList = Arrays.asList(dataMap.get("Roles").split(","));
        this.country = dataMap.get("AssignCountry");
        this.organistaion = dataMap.get("AssignOrganisation");
        this.webAppUse = dataMap.get("webAppUse");
        this.sites = Arrays.asList(dataMap.get("AssignSite").split(","));
        this.buildings = Arrays.asList(dataMap.get("AssignBuildings").split(","));


        // sorting the list is needed for assertions in the next phase of the run
        Collections.sort(this.sites);
        Collections.sort(this.buildings);

        //to enter user details to the form
        usersPage.enterUserDetails(username, email, confirmEmail, title, firstName, middleName, surname, phone, assignedRoleList, country, organistaion, sites, buildings);

        // putting all user details into a map to be used for the assertion in next step to validate the saved user details are same
        this.userDataExcel = new HashMap<>();
        userDataExcel.put("username", username);
        userDataExcel.put("email", email);
        userDataExcel.put("confirmEmail", confirmEmail);
        userDataExcel.put("title", title);
        userDataExcel.put("firstName", firstName);
        userDataExcel.put("middleName", middleName);
        userDataExcel.put("surname", surname);
        userDataExcel.put("phone", phone);
        userDataExcel.put("assignedRoleList", assignedRoleList);
        userDataExcel.put("country", country);
        userDataExcel.put("organistaion", organistaion);
        userDataExcel.put("assignedSites", sites);
        userDataExcel.put("assignedBuildings", buildings);

        System.out.println("userDataExcel = " + userDataExcel.toString());
    }

    @Then("Verify User has Created Successfully")
    public void verifyUserHasCreatedSuccessfully() {
        BrowserUtils.waitForElementToBeVisible(usersPage.userCreatedSuccessMessage);
        assertTrue("Success! message is supposed to be displaye, But NOT! ", usersPage.userCreatedSuccessMessage.isDisplayed());
        assertTrue("Success icon message is supposed to be displaye, But NOT! ", usersPage.userCreatedSuccessIcon.isDisplayed());

    }


    @Then("View the user and verify the details are correct")
    public void viewTheUserAndVerifyTheDetailsAreCorrect() {
        BrowserUtils.waitFor(2);
//        assertTrue("FAIL: you should be in VIEW USER page to perform this action", usersPage.verifyTitle("View Users"));

        // select the correspoinding user
        usersPage.editUserByUserName(this.username);

        // get the user details in Map format from application which has just been saved
        Map<String, Object> actualUserDetails = usersPage.getActualUserDetails();

        // iterate actual user details and verify if it is matching with excel data
        for (String key : actualUserDetails.keySet()) {
            String actualData = actualUserDetails.get(key).toString();
            System.out.println("----key        = " + key);
            System.out.println("    actualData = " + actualData);
            if (!actualData.toString().equals("[]")) {
                Assert.assertEquals(key + "-->  in the application: " + actualData + " -- in the excel :" + this.userDataExcel.get(key), this.userDataExcel.get(key).toString(), actualData.toString());
            } else if (!actualData.toString().equals("[]")) {
                BrowserUtils.log(key + " is not found in the user details");
            }
        }

    }

    @Then("Delete the newly created user")
    public void deleteTheNewlyCreatedUser() {
        usersPage.deleteUserByUserName(this.username);
    }


    @And("Verify lock icon with the {string}")
    public void verifyLockIconWithThe(String username) {
        BrowserUtils.waitFor(2);
        new UsersPage().sendKeysToInputBoxAndHitToEnter("search", username);
        BrowserUtils.waitFor(2);
        System.out.println("username = " + username);
        System.out.println("new UsersPage().isUnlockButtonExist(username) = " + new UsersPage().isUnlockButtonExist(username));
        Assert.assertTrue("FAIL: Unlock button is not exist!! ", new UsersPage().isUnlockButtonExist(username));

    }


    @Then("Lock icon disappears once i click on lock icon")
    public void lock_icon_disappears_once_i_click_on_lock_icon() {
        usersPage.unlockButton.click();
        assertFalse("The locked icon supposed to be disappeared, But NOT disappeared. ", usersPage.isUnlockButtonExist(username));
        System.out.println("***");
    }


    @When("the user click on Create User button")
    public void theUserClickOnCreateUserButton() {
        Driver.get().findElement(By.cssSelector("[type='submit'][value='Create User']")).click();

    }

    @When("Then Click on Reset password link on {string} , {string}")
    public void thenClickOnResetPasswordLinkOn(String emailRecieved, String passwrd) {

        MailUtils.navigateToLinkForResetPassword(new Date().getTime(), emailRecieved, passwrd, this.username);

    }

    @When("the user create the password")
    public void theUserCreateThePassword() {

        this.temPassword = ConfigurationReader.get("passwordGeneralUsage") + rd.nextInt(1000);

        new ForgotPasswordPage().enterPasswordToReset(this.temPassword);
        System.out.println("&&&&& temp password : " + temPassword);
        usersPage.submit();
    }


    @And("the user verifies successful registration")
    public void theUserVerifiesSuccessfulRegistration() {

        if (this.webAppUse.equalsIgnoreCase("FALSE")) {
            WebElement successMessage = Driver.get().findElement(By.xpath("//h3"));
            BrowserUtils.waitForElementToBeVisible(successMessage);
            successMessage.getText().contains("Registration Complete");
        } else if (this.webAppUse.equalsIgnoreCase("TRUE")) {
            Assert.assertTrue(Driver.get().getCurrentUrl().contains("dashboard"));
        }
        BrowserUtils.waitFor(2);
    }


    @When("the new user login")
    public void theNewUserLogin() {

        new LoginPage().login(this.username, this.temPassword);


    }


    @And("the user navigates to My Profile")
    public void theUserNavigatesToMyProfile() {
        new DashboardPage().navigateToMyProfile();
    }


    @And("verifies the informations are matching in My Profile page")
    public void verifiesTheInformationsAreMatchingInMyProfilePage() {

        BrowserUtils.waitFor(2);
        assertTrue("FAIL: you should be in VIEW USER page to perform this action", usersPage.verifyTitle("Welcome " + this.username));


        // get the user details in Map format from application which has just been saved
        Map<String, Object> actualUserDetails = usersPage.getMyProfileUserDetails();

        // iterate actual user details and verify if it is matching with excel data


        for (String key : actualUserDetails.keySet()) {
            String actualData = actualUserDetails.get(key).toString();
            System.out.println("---------------------------");
            System.out.println("key = " + key);
            System.out.println("actualData = " + actualData);
            System.out.println("expected data= " + userDataExcel.get(key));
            System.out.println("---------------------------");


            if (!actualData.toString().equals("[]")) {
                Assert.assertEquals(key + "-->  in the application: " + actualData.toString() + " -- in the excel :" + this.userDataExcel.get(key),
                        this.userDataExcel.get(key).toString().replace("java.util.Arrays$ArrayList", ""),
                        actualData.toString().replace("java.lang.String", ""));
            } else {
                System.out.println("this.userDataExcel.get(key).toString() = " + this.userDataExcel.get(key).toString());
                Assert.assertTrue(this.userDataExcel.get(key).toString().replace("java.util.Arrays$ArrayList", "").equals("[N/A]"));

                BrowserUtils.log(key + " is not found in the user details");
            }
        }


    }

    @When("the user gets all sites and buildings assigned to organisation")
    public void theUserGetsAllSitesAndBuildingsAssignedToOrganisation() {
        System.out.println("scenario name= " + dataMap.get("Scenarioname"));
        System.out.println("organisations in the excel = " + dataMap.get("AssignOrganisation").equals("N/A"));
        System.out.println("sites in the excel " + dataMap.get("AssignSite"));

        // if there is a defined organisation in the excel but no sites, we need to find sites and bildings under the organisation dynamically
        if(dataMap.get("AssignSite").toString().equalsIgnoreCase("N/A") && (dataMap.get("AssignOrganisation").equals("N/A"))){
            BrowserUtils.log("No organisation nor sites is assigned to this user");
        }else if (dataMap.get("AssignSite").toString().equalsIgnoreCase("N/A") && (!(dataMap.get("AssignOrganisation").equals("N/A")))) {
            //get expected sites under certain organisation dynamically
            this.expectedSites = new OrganisationPage().getSitesByOrganisation(this.organistaion);
            BrowserUtils.log("The sites under " + organistaion + " organisation are fetched from the application as " + this.expectedSites);
            userDataExcel.put("assignedSites", new OrganisationPage().getSitesByOrganisation(this.organistaion));

            //get expected buildings under certain site from the application dynamically
            List<String> expectedBuildings = new SitesPage().getBuildingsBySites(expectedSites);
            userDataExcel.put("assignedBuildings", new SitesPage().getBuildingsBySites(expectedSites));
            BrowserUtils.log("The buildings under " + this.expectedSites.toString() + " sites are fetched from the application as " + expectedBuildings);

            // if there is a defined sites in the excel but no buildings, we need to find bildings under the site dynamically
        } else if (!dataMap.get("AssignSite").toString().equalsIgnoreCase("N/A") && dataMap.get("AssignBuildings").equals("N/A")) {
            List<String> buildingsUnderSite = new SitesPage().getBuildingsBySites((List<String>) (userDataExcel.get("assignedSites")));
            userDataExcel.put("assignedBuildings", buildingsUnderSite);
            BrowserUtils.log("The buildings under " + dataMap.get("AssignSite").toString() + " sites are fetched from the application as " + buildingsUnderSite);

        }


    }

    @When("the user gets the create password link on {string} , {string}")
    public void theUserGetsTheCreatePasswordLinkOn(String emailRecieved, String passwrd) {
        this.link = MailUtils.getTheLinkForResetPassword(new Date().getTime(), emailRecieved, passwrd, this.username);

    }

    @When("Then Click on Reset password link")
    public void thenClickOnResetPasswordLink() {
        Driver.get().get(link);
    }
}
