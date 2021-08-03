package com.app.pages;

import com.app.utilities.BrowserUtils;
import com.app.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SitesPage extends BasePage {


    public List<String> getBuildingsBySites(List<String> expectedSites) {

        List<String> buildingList = new ArrayList<>();
        for (String site : expectedSites) {

            navigateToMenu("Sites");
            Assert.assertTrue("FAIL: failed to get title as View Sites", new UsersPage().verifyTitle("View Sites"));

            BrowserUtils.waitFor(2);
            Driver.get().findElement(By.id("search")).clear();
            Driver.get().findElement(By.id("search")).sendKeys(site, Keys.ENTER);
            BrowserUtils.waitFor(2);
            click(Driver.get().findElement(By.xpath("//a[contains(text(),'" + site + "')]")));
            BrowserUtils.waitFor(2);
            List<WebElement> elements = Driver.get().findElements(By.cssSelector(".table-cell"));

            for (WebElement element : elements) {
                buildingList.add(element.getText());
            }
        }
        Collections.sort(buildingList);
        System.out.println("buildingList found in the application = " + buildingList);
        return buildingList;

    }
}