package com.app.pages;

import com.app.utilities.BrowserUtils;
import com.app.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.Collections;
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
          navigateToMenu("Users");
        Assert.assertTrue("FAIL: failed to get title as View Users", new UsersPage().verifyTitle("View Users"));

        try {
            BrowserUtils.waitFor(2);
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
    public void enterUserDetails(String username, String emailaddress, String confirmEmailaddress, String title, String firstName, String middleName, String surname, String phoneNumber, List<String> assignedRoleList, String country, String organistaion, List<String> sites, List<String> buildings) {

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
        selectOrganisationByName(organistaion);
        System.out.println("sites.toString() = " + sites.toString());
        selectSites(sites);
        selectBuildings(buildings);


    }

    private void selectSites(List<String> sites) {
        System.out.println(sites.toString());

        for (String site : sites) {
            try {
                if (site.equalsIgnoreCase("N/A")) {
                    System.out.println("site is not applicable for this user in the excel file");
                } else {
                    BrowserUtils.waitFor(1);
                    WebElement element;
                    try {
                        element = Driver.get().findElement(By.xpath("//label[.='Select Site']/..//label[@class='lbl-checkbox' and text()='" + site + "']"));
                    } catch (NoSuchElementException n) {
                        element = Driver.get().findElement(By.xpath("//label[.='Select Site']/..//label[@class='lbl-checkbox clear' and text()='" + site + "']"));
                    }
                    BrowserUtils.scrolltoElement(element);
                    element.click();
                }
            } catch (ElementNotInteractableException e) {
                BrowserUtils.log("site input is not suitable for this user.. ");
                System.out.println("COUTION: site input is not suitable for this user.. ");
            }
        }

    }


    private void selectBuildings(List<String> buildings) {
        System.out.println("buildings = " + buildings);
        try {
            for (String building : buildings) {
                if (building.equals("N/A")) {
                    System.out.println("building is not applicable for this user in the excel file");
                } else {
                    BrowserUtils.waitFor(1);
                    WebElement element = Driver.get().findElement(By.xpath("//label[.='Select Building']/../..//label[@class='lbl-checkbox' and text()='" + building + "']"));
                    BrowserUtils.scrolltoElement(element);
                    element.click();
                }
            }
        } catch (ElementNotInteractableException e) {
            BrowserUtils.log("building input is not suitable for this user.. ");
            System.out.println("COUTION: site input is not suitable for this user.. ");
        }

    }


    /**
     * the user detail page should be displayed before this method is invoked
     *
     * @return: the actual data in the user details page in Map format
     */
    public Map<String, Object> getActualUserDetails() {
        System.out.println("checkpoint1");
        Map<String, Object> actualUserData = new HashMap<>();
        actualUserData.put("username", getTextOfInputBox("username"));
        actualUserData.put("email", getTextOfInputBox("em_address"));
        actualUserData.put("title", getSelectedValueDD("title").toString());
        System.out.println("checkpoint2");
        actualUserData.put("firstName", getTextOfInputBox("firstname"));
        actualUserData.put("middleName", getTextOfInputBox("middlename"));
        actualUserData.put("surname", getTextOfInputBox("lastname"));
        actualUserData.put("phone", phoneInput.getAttribute("value").replace("+", ""));
        System.out.println("checkpoint2");


//
//        if (this.assignedRoleList.size()==0) {
//
//            List<String> roleL = this.assignedRoleList.stream().map(WebElement::getText).sorted().collect(Collectors.toList());
//            System.out.println("roleL.toString() = " + roleL.toString());
//            actualUserData.put("assignedRoleList", roleL);
//        }
//        if (this.sitesAssigned.size()==0) {
//            List<String> siteL = this.sitesAssigned.stream().map(WebElement::getText).sorted().collect(Collectors.toList());
//            System.out.println("siteL.toString() = " + siteL.toString());
//            actualUserData.put("assignedSites", siteL);
//        }
//        if (this.buildingsAssigned.size()==0) {
//            List<String> buildingL = this.buildingsAssigned.stream().map(WebElement::getText).sorted().collect(Collectors.toList());
//            actualUserData.put("assignedBuildings", buildingL);
//        }
//        if (this.devicesAssigned.size()==0) {
//            List<String> deviceL = this.devicesAssigned.stream().map(WebElement::getText).sorted().collect(Collectors.toList());
//            actualUserData.put("devicesAssigned", deviceL);
//        }


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
        actualUserData.put("phone", getTextOfInputBox("telephone").replace("+", ""));

        List<String> actualRoles = this.rolesAssignedMyProfile.stream().map(s -> s.getText()).collect(Collectors.toList());
        Collections.sort(actualRoles);
        actualUserData.put("assignedRoleList", actualRoles);

        List<String> actualSites = this.sitesAssignedMyProfile.stream().map(s -> s.getText()).collect(Collectors.toList());
        Collections.sort(actualSites);
        actualUserData.put("assignedSites", actualSites);

        List<String> actualBuildings = this.buildingsAssignedMyProfile.stream().map(s -> s.getText()).collect(Collectors.toList());
        Collections.sort(actualBuildings);
        actualUserData.put("assignedBuildings", actualBuildings);


        return actualUserData;

    }


}
