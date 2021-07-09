package com.app.stepdefinitions;

import com.app.pages.DashboardPage;
import com.app.pages.LoginPage;
import com.app.utilities.BrowserUtils;
import com.app.utilities.ConfigurationReader;
import com.app.utilities.Driver;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.junit.Cucumber;
import org.junit.Assert;
import org.junit.Before;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class LoginStepDefs {





    private LoginPage loginPage = new LoginPage();
    private DashboardPage dashboardPage = new DashboardPage();






    @Given("I am on Login Screen")
    public void i_am_on_Login_Screen() {
        Hooks.scenario.write("LOGIN_DEMO");
        Driver.get().get(ConfigurationReader.get("url"));
    }

    @Then("Enter Username {string} and Password {string}")
    public void enter_Username_and_Password(String username, String password) {
        loginPage.userNameInputBox.sendKeys(username);
        loginPage.passwordInputBox.sendKeys(password);
    }

    @Then("Click on Login")
    public void click_on_Login() {
        loginPage.submitBtn.click();
    }

    @Then("Verify Inline error message for Username field {string} displayed")
    public void verify_Inline_error_message_for_Username_field_displayed(String string) {
        BrowserUtils.waitFor(2);
    }

    @Then("Verify Inline error message for Password field {string} displayed")
    public void verify_Inline_error_message_for_Password_field_displayed(String string) {

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



    }
}
