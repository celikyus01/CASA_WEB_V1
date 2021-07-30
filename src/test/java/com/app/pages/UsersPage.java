package com.app.pages;

import com.app.utilities.BrowserUtils;
import com.app.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UsersPage extends BasePage {


    @FindBy(id = "telephone")
    public WebElement phoneInput;

    @FindBy(css = ".tableButton.unlock.pointer")
    public WebElement unlockButton;

    @FindBy(css = ".jquery-image.desktop-image.user-avatar")
    public WebElement redLockedIcon;

    @FindBy(xpath = "//div[@class='element roles-list']//li")
    public List<WebElement> assignedRoleList;

    @FindBy(xpath = "//*[.='Sites Assigned']/../..//span")
    public List<WebElement> sitesAssigned;

    @FindBy(xpath = "//*[.='Buildings Assigned']/../..//span")
    public List<WebElement> buildingsAssigned;

    @FindBy(xpath = "//*[.='Devices Assigned']/../..//span")
    public List<WebElement> devicesAssigned;

    @FindBy(xpath = "//p[.='Sites Assigned']/../..//li")
    public List<WebElement> sitesAssignedMyProfile;

    @FindBy(xpath = "//p[.='Buildings Assigned']/../..//li")
    public List<WebElement> buildingsAssignedMyProfile;

    @FindBy(xpath = "//div[@class='element roles-list']//span")
    public List<WebElement> rolesAssignedMyProfile;




    @FindBy(xpath = "//h2[.='Success!']")
    public WebElement userCreatedSuccessMessage;

    @FindBy(css = ".tick-icon")
    public WebElement userCreatedSuccessIcon;

    @FindBy(id = "search")
    public WebElement searchUserInputBox;

    @FindBy(css = "[type='submit'][value='Search']")
    public WebElement searchUserButton;

    @FindBy(xpath = "//*[.='Delete' and @class='button negative right']")
    public WebElement deleteConfirmationButton;


    public void selectRole(List<String> roles) {

        for (String role : roles) {
            Driver.get().findElement(By.xpath("//label[.='" + role + "']")).click();
        }
    }


    public void selectOrganisationByName(String orgName) {
        try {
            WebElement element = Driver.get().findElement(By.xpath("//label[.='" + orgName + "']"));
            BrowserUtils.waitForElementToBeClickable(element);
            element.click();
        } catch (NoSuchElementException e) {
            System.out.println(orgName + " element is not interactable for this user type");
        }
    }

    public void deleteUserByUserName(String username) {
        navigateToMenu("Users");
        Assert.assertTrue("FAIL: failed to get title as View Users", new UsersPage().verifyTitle("View Users"));
        BrowserUtils.waitForElementToBeVisible(searchUserInputBox);

        try {
            searchUserInputBox.sendKeys(username);
            searchUserButton.click();
            WebElement element = Driver.get().findElement(By.xpath("//*[.='" + username + "']/..//a[@class='tableButton delete']"));
            element.click();
        } catch (NoSuchElementException n) {
            searchUserInputBox.clear();
            searchUserInputBox.sendKeys(username);
            searchUserButton.click();
            WebElement element = Driver.get().findElement(By.xpath("//*[.='" + username + "']/..//a[@class='tableButton delete']"));
            element.click();

        }

        Assert.assertTrue(verifyTitle("Delete?"));
        BrowserUtils.waitForElementToBeClickable(deleteConfirmationButton);
        deleteConfirmationButton.click();


    }


    public void unLockUserByUserName(String username) {


        try {
            Driver.get().findElement(By.xpath("//*[.='" + username + "']/..//a[@class='tableButton unlock pointer']")).click();
        } catch (NoSuchElementException n) {
            n.printStackTrace();
            BrowserUtils.log(username + "or unlock button cannot be found in the Users page");
        }


    }

    public boolean isUnlockButtonExist(String username) {

        boolean isUnlockExist = false;

        try {
            Driver.get().findElement(By.xpath("//*[.='" + username + "']/..//a[@class='tableButton unlock pointer']"));
            isUnlockExist = true;
        } catch (NoSuchElementException n) {
            n.printStackTrace();
            if (isUnlockExist) {
                BrowserUtils.log(username + "cannot be found in the Users page");
            } else {
                BrowserUtils.log("unlock button cannot be found in the Users page");
            }
        }

        return isUnlockExist;


    }


    public void editUserByUserName(String username) {
        //  navigateToMenu("Users");
        Assert.assertTrue("FAIL: failed to get title as View Users", new UsersPage().verifyTitle("View Users"));

        try {
            new UsersPage().searchUserInputBox.sendKeys(username, Keys.ENTER);
            BrowserUtils.waitFor(2);
            Driver.get().findElement(By.xpath("//*[.='" + username + "']/..//a[@class='tableButton edit pointer']")).click();
        } catch (NoSuchElementException n) {
            n.printStackTrace();
            BrowserUtils.log(username + " cannot be found in the Users page");
        }

    }

    public void viewUserByUserName(String username) {
        navigateToMenu("Users");
        Assert.assertTrue("FAIL: failed to get title as View Users", new UsersPage().verifyTitle("View Users"));
        new UsersPage().searchUserInputBox.sendKeys(username, Keys.ENTER);

        try {
            Driver.get().findElement(By.xpath("//*[.='" + username + "']/..//a[@class='tableButton troubleshoot pointer']")).click();
        } catch (NoSuchElementException n) {
            n.printStackTrace();
            BrowserUtils.log(username + " cannot be found in the Users page");
        }


    }

    /**
     * the user detail page should be displayed before this method is invoked
     * it only enters the data, it doesn't click the submit button
     */
    public void enterUserDetails(String username, String emailaddress, String confirmEmailaddress, String title, String firstName, String middleName, String surname, String phoneNumber, List<String> assignedRoleList, String country, String organistaion) {

        sendKeysToInputBox("username", username);
        sendKeysToInputBox("em_address", emailaddress);
        sendKeysToInputBox("em_address_confirm", confirmEmailaddress);
        selectDD("title", title);
        sendKeysToInputBox("firstname", firstName);
        sendKeysToInputBox("middlename", middleName);
        sendKeysToInputBox("lastname", surname);
        phoneInput.sendKeys(phoneNumber);
        selectRole(assignedRoleList);
        selectDD("create_user_country_select", country);
        selectOrganisationByName("yusuf organisation");


    }

    /**
     * the user detail page should be displayed before this method is invoked
     *
     * @return: the actual data in the user details page in Map format
     */
    public Map<String, Object> getActualUserDetails() {

        Map<String, Object> actualUserData = new HashMap<>();

        actualUserData.put("username", getTextOfInputBox("username"));
        actualUserData.put("email", getTextOfInputBox("em_address"));
        actualUserData.put("title", getSelectedValueDD("title").toString());
        actualUserData.put("firstName", getTextOfInputBox("firstname"));
        actualUserData.put("middleName", getTextOfInputBox("middlename"));
        actualUserData.put("surname", getTextOfInputBox("lastname"));
        actualUserData.put("phone", phoneInput.getAttribute("value").replace("+", ""));
        actualUserData.put("assignedRoleList", this.assignedRoleList);
        actualUserData.put("sitesAssigned", this.sitesAssigned);
        actualUserData.put("buildingsAssigned", this.buildingsAssigned);
        actualUserData.put("devicesAssigned", this.devicesAssigned);

        return actualUserData;

    }

    /**
     * the user detail page should be displayed before this method is invoked
     *
     * @return: the actual data in the user details page in Map format
     */
    public Map<String, Object> getMyProfileUserDetails() {

        Map<String, Object> actualUserData = new HashMap<>();

        actualUserData.put("email", getTextOfInputBox("em_address"));
        actualUserData.put("title", getSelectedValueDD("title").toString());
        actualUserData.put("firstName", getTextOfInputBox("firstname"));
        actualUserData.put("middleName", getTextOfInputBox("middlename"));
        actualUserData.put("surname", getTextOfInputBox("lastname"));
        actualUserData.put("phone", getTextOfInputBox("telephone").replace("+",""));
//        actualUserData.put("phone", phoneInput.getAttribute("value").replace("+", ""));



        actualUserData.put("assignedRoleList", this.rolesAssignedMyProfile.stream().map(s->s.getText()).collect(Collectors.toList()));
        actualUserData.put("sitesAssigned", this.sitesAssignedMyProfile.stream().map(s->s.getText()).collect(Collectors.toList()));
        actualUserData.put("buildingsAssigned", this.buildingsAssignedMyProfile.stream().map(s->s.getText()).collect(Collectors.toList()));


        return actualUserData;

    }



}
