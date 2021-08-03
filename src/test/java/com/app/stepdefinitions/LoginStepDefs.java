package com.app.stepdefinitions;


import com.app.pages.*;
import com.app.utilities.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


import static org.junit.Assert.*;

public class LoginStepDefs {

    private static long sendTime;

    private static LocalDateTime localDateTime;

    private LoginPage loginPage = new LoginPage();
    private DashboardPage dashboardPage = new DashboardPage();
    private ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
    private ExcelUtil passwordValidation_sheet;
    private ExcelUtil passwordCreate_sheet;
    private static String newPassword;
    private static String userEmail;
    public boolean isTestFailed = false;
    private String role;


    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:MM");


    @Given("I am on Login Screen")
    public void i_am_on_Login_Screen() {
        Driver.get().get(ConfigurationReader.get("url"));
    }

    @Then("Enter Username {string} and Password {string}")
    public void enter_Username_and_Password(String username, String password) {
        loginPage.userNameInputBox.sendKeys(username);
        loginPage.passwordInputBox.sendKeys(password);
        String host = "pop.gmail.com";// change accordingly
        String mailStoreType = "pop3";
        String username1 = "testEmailFathom3@gmail.com";// change accordingly
        String password1 = "Password001!";// change accordingly

        userEmail = username1;


    }

    @Then("Click on Login")
    public void click_on_Login() {
        loginPage.submitBtn.click();
    }


    @Then("Verify Inline error message for Password field {string} displayed")
    public void verify_Inline_error_message_for_Password_field_displayed(String errorMessage) {
        assertEquals(errorMessage, loginPage.secondErrorMessage.getText());
        loginPage.userNameInputBox.clear();
        loginPage.passwordInputBox.clear();
    }


    @Then("Verify page title as {string}")
    public void verifyPageTitleAs(String expectedTitle) throws InterruptedException {

        Thread.sleep(3000);
        String actualTitle = dashboardPage.pageTitle.getText();
        //assertEquals("!! NOTE: Title was expected to be "+expectedTitle+", but found" +actualTitle ,expectedTitle,actualTitle);

        Assert.assertTrue(Driver.get().getCurrentUrl().contains("dashboard"));

    }

    @And("logout from user")
    public void logoutFromUser() {
        dashboardPage.logOut();

    }


    @Then("Enter Username {string} and Password {string} for {int} user should get {string} error message")
    public void enterUsernameAndPasswordForUserShouldGetErrorMessage(String username, String password, int nooftry, String message) {

        for (int i = 1; i <= nooftry; i++) {

            loginPage.userNameInputBox.sendKeys(username);
            loginPage.passwordInputBox.sendKeys(password);
            loginPage.submitBtn.click();
            assertEquals("Expected: Incorrect Password , Actual :" + loginPage.incorrectUsername, message, loginPage.incorrectPassword.getText());
            loginPage.userNameInputBox.clear();
            loginPage.passwordInputBox.clear();

        }

        loginPage.userNameInputBox.sendKeys(username);
        loginPage.passwordInputBox.sendKeys(password);
        loginPage.submitBtn.click();


    }


    @Then("Navigate to {string} page")
    public void navigateToPage(String pageName) {
        dashboardPage.navigateToMenu(pageName);
    }

    @Then("user able to login successfully")
    public void user_able_to_login_successfully() {

        BrowserUtils.waitFor(1);
        assertTrue("Login unsuccesful", Driver.get().getCurrentUrl().contains("dashboard"));
    }

    @When("the user clicks on the {string}")
    public void the_user_clicks_on_the(String buttonName) {
        new DashboardPage().click(buttonName);

    }

    @Then("Forgot password page displayed")
    public void forgot_password_page_displayed() {
        BrowserUtils.waitForElementToBeVisible(forgotPasswordPage.pageDescription);
        assertTrue(forgotPasswordPage.pageDescription.equals("Forgotten your password? Let’s get you a new one"));
    }

    @Then("Then verify the message {string}")
    public void thenVerifyTheMessage(String message) {
        assertEquals(message + " -- could not be conformed in the page", message, new ForgotPasswordPage().pageDescription.getText());


    }

    @Then("Then verify the fail message {string} for the reset password")
    public void thenVerifyTheFailMessageForTheResetPassword(String message) {

        forgotPasswordPage.verifyFailMessage(message);
//        System.out.println("forgotPasswordPage.getFailMessagesForResetPassword().toString() = " + forgotPasswordPage.getFailMessagesForResetPassword().toString());
//        assertTrue("Assertion FAIL! : There is no such message: "+ message,forgotPasswordPage.getFailMessagesForResetPassword().contains(message));

    }

    @Then("Click on Reset your password button")
    public void click_on_Reset_your_password_button() {
        new LoginPage().submitBtn.click();
        // assertTrue(new ForgotPasswordPage().pageDescription.getText().equals("Thank you! Please check your email"));

        sendTime = new Date().getTime();

    }

    @Then("Navigate to gmail inbox {string}")
    public void navigate_to_gmail_inbox(String string) {
        Driver.get().get("https://mail.google.com/mail/u/0/?zx=mlyiabco874l#inbox");
    }


    @And("find the last email sent by Rackeye in {int} minute from mail box")
    public void findTheLastEmailSentByRackeyeInMinuteFromMailBox(int arg0) {

        int emailTime = Integer.parseInt(forgotPasswordPage.emailTime.toString().replace(":", ""));
        int sendingTime = Integer.parseInt(dateTimeFormatter.format(localDateTime).replace(":", ""));
        Assert.assertTrue("No email sent", emailTime <= sendingTime + 2 || emailTime >= sendingTime);
        forgotPasswordPage.email.click();

    }


    @Then("Click on Reset password link on {string} , {string} , {string}")
    public void clickOnResetPasswordLinkOn(String email, String password, String username) {
        MailUtils.navigateToLinkForResetPassword(new Date().getTime(), email, password, username);
    }

    @When("connect the {string} -- {string}")
    public void connectThe(String document, String sheet) {
        passwordCreate_sheet = new ExcelUtil(sheet);

    }

    @Then("Enter the Login credentials ScenarioName from the test data and Validate the Errormessage")
    public void enter_the_Login_credentials_ScenarioName_from_the_test_data_and_Validate_the_Errormessage(List<Map<String, String>> dataTable) {

        for (Map<String, String> map : dataTable) {
            String scenarioName = map.get("Scenarioname");

            List<Map<String, String>> dataList = passwordValidation_sheet.getDataList();

            for (Map<String, String> sheetMap : dataList) {

                if (sheetMap.get("Scenarioname").equals(scenarioName)) {

                    String password = sheetMap.get("Password");

                    dashboardPage.sendKeysToInputBox("newpassword", password);
                    dashboardPage.sendKeysToInputBox("confirmpassword", password);

                    dashboardPage.submit();
                    forgotPasswordPage.verifyFailMessage(map.get("Errormessage"));

                }

            }

        }

    }

    @Then("Enter a unique password which has not been used")
    public void enter_a_unique_password_which_has_not_been_used() {

        Random random = new Random();
        String newPass = "Password" + (random.nextInt(9000) + 1000) + "!";

        dashboardPage.sendKeysToInputBox("newpassword", newPass);
        dashboardPage.sendKeysToInputBox("confirmpassword", newPass);

        newPassword = newPass;


    }

    @Then("the new password is updated")
    public void the_new_password_is_updated() {
        assertTrue(Driver.get().getCurrentUrl().contains("dashboard"));
        BrowserUtils.log(newPassword + "is assigned for  " + userEmail);

    }


    @When("submit the {string}")
    public void submitThe(String arg0) {
        new DashboardPage().submit();
    }


    @And("connect to the Data Base")
    public void connectToTheDataBase() {
        DBUtils.createConnection();

    }


    @And("disconnect from the Data Base")
    public void disconnectFromTheDataBase() {

        DBUtils.destroy();
    }


    @When("the user validates the scenarios for the {string} criteria and the {string} organisation")
    public void theUserValidatesTheScenariosForTheCriteriaAndTheOrganisation(String criteria, String organisationName) {
        boolean isTestFailed = false;

        //gets all data from "passwordCreate_sheet" sheet in the excel file and store them in List<Map<String,String>> format
        List<Map<String, String>> rowDataList = passwordCreate_sheet.getDataList();

        //empty "list of map" created to store matching rows inside
        List<Map<String, String>> dataList = new ArrayList<>();
        // below loop iterates the rowDataList list and adds rows to the dataList if it is matching with the criteria (i.e password_max_length)
        // to prevent the code from crash by "NullPointerException" due to empty rows in the excel, statement is surrounded with try-catch
        for (Map<String, String> map : rowDataList) {

            try {
                if (map.get("criteria").equals(criteria)) {
                    dataList.add(map);
                }
            } catch (NullPointerException n) {
                break;
            }
        }

        for (Map<String, String> dataRow : dataList) {
            System.out.println("-------------SCENARIO START " + dataRow.get("Scenarioname") + " ------------");

            //update databse with the set data in the excel
            DBUtils.update_orgSecPol(organisationName, dataRow.get("criteria"), dataRow.get("Set"));

            //sent keys for reset password
            forgotPasswordPage.enterPasswordToReset(dataRow.get("TestData"));
            BrowserUtils.waitFor(2);


            System.out.println("Test Data = " + dataRow.get("TestData"));

            //if the message is displayed in grey format, (class attribute = pending), it means that message can not fulfill the requiremetn.
            // verifyFailMessageInGrey() method returns true if the message is in grey format
            boolean IsMessageGrey = forgotPasswordPage.verifyFailMessageInGrey(dataRow.get("Message"));


            System.out.println("IsMessageGrey = " + IsMessageGrey);
            System.out.println("Is message grey excel= " + dataRow.get("Error Message should display"));

            //validate expected result based on the message.. To perform soft assertion, try-catch is used
            boolean isScenarioPassed = false; // represent current scenario id failed
            String failMessage = null;

            // currently isScenarioFailed= false, and there are 2 conditions than can validate the scenario..
            if (dataRow.get("Error Message should display").equals("TRUE") && IsMessageGrey) { // if the message is expected to be grey
                // Assert.assertTrue(IsMessageGrey);
                isScenarioPassed = true;
            } else if (dataRow.get("Error Message should display").equals("FALSE") && !IsMessageGrey) {
                isScenarioPassed = true;
            } else if (dataRow.get("Error Message should display").equals("TRUE") && !IsMessageGrey) {
                failMessage = "!!! FAIL:" + dataRow.get("Message") + "was expected to be \"grey\", but it is in \"green\" format.. ";
            } else if (dataRow.get("Error Message should display").equals("FALSE") && IsMessageGrey) {
                failMessage = "!!! FAIL: " + dataRow.get("Message") + " was expected to be \"green\",  but it is in \"grey\" format.. ";

            }


            //Log the result in the report

            if (isScenarioPassed) {
                BrowserUtils.log("PASSED : " + dataRow.get("Scenarioname") + " -- " + dataRow.get("criteria") + " was set to " + dataRow.get("Set") + " -- " + dataRow.get("TestData") + " password used  -- " + dataRow.get("Message") + " is successfully displayed ");
            } else {
                BrowserUtils.log("FAILED : " + dataRow.get("Scenarioname") + " -- " + dataRow.get("criteria") + " was set to " + dataRow.get("Set") + " -- " + dataRow.get("TestData") + " password used  -- " + failMessage);
                BrowserUtils.logScreenShot(dataRow.get("Scenarioname") + " -- passwordUsed: " + dataRow.get("TestData"));
                isTestFailed = true;
            }
        }

        if (isTestFailed) {
            DBUtils.destroy();
            BrowserUtils.log(" ");
            BrowserUtils.log("COUTION!: one or more of the scenario in the " + criteria + " is failed!!   ");
            DBUtils.update(criteria, dataList.get(0).get("default")); //  update the field with the default value to avoid conflicts in the message details
            Assert.fail("FAIL: one or more of the scenario in the " + criteria + " is failed!!");

        }
    }


    @Given("Log in as a {string}")
    public void logInAsA(String role) {
        this.role = role;
        Driver.get().get(ConfigurationReader.get("url"));
        loginPage.login(ConfigurationReader.get("username" + role), ConfigurationReader.get("password" + role));
        BrowserUtils.waitFor(3);
    }

    @Then("Navigate to Create User Page")
    public void navigate_to_Create_User_Page() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    public static void main(String[] args) {

        DBUtils.createConnection();
        DBUtils.update_orgSecPol(621, "PASSWORD_MIN_SIZE", "8");
        DBUtils.destroy();

    }



    @And("enter your {string} to reset")
    public void enterYourToReset(String email) {
        new UsersPage().sendKeysToInputBox("email",email);
    }

    @And("the user login in as a developer")
    public void theUserLoginInAsADeveloper() {
        loginPage.loginAsDeveloper();
    }
}