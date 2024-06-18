package com.nopcommerce.demo.testsuite;

import com.google.common.base.Verify;
import com.nopcommerce.demo.pages.BuildYourOwnComputerPage;
import com.nopcommerce.demo.pages.ComputerPage;
import com.nopcommerce.demo.pages.DesktopsPage;
import com.nopcommerce.demo.pages.HomePage;
import com.nopcommerce.demo.testbase.BaseTest;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import resources.testdata.TestData;

import java.awt.*;

/**
 * 3. ComputerPageTest
 * Inside ComputerPageTest class create following testmethods
 * 1. verifyUserShouldNavigateToComputerPageSuccessfully()
 * Click on Computer tab
 * Verify "Computer" text
 * 2. verifyUserShouldNavigateToDesktopsPageSuccessfully()
 * Click on Computer tab
 * Click on Desktops link
 * Verify "Desktops" text
 * 3. verifyThatUserShouldBuildYouOwnComputerAndAddThemToCartSuccessfully(String processor, String ram, String hdd, String os, String software)
 * Click on Computer tab
 * Click on Desktops link
 * Click on product name "Build your own computer"
 * Select processor "processor"
 * Select RAM "ram"
 * Select HDD "hdd"
 * Select OS "os"
 * Select Software "software"
 * Click on "ADD TO CART" Button
 * Verify message "The product has been added to your shopping cart"
 * DATA SET
 * | processor | ram | hdd | os | software |
 * | 2.2 GHz Intel Pentium Dual-Core E2200 | 2 GB | 320 GB | Vista Home [+$50.00] | Microsoft Office [+$50.00] |
 * | 2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00] | 4GB [+$20.00] | 400 GB [+$100.00]| Vista Premium [+$60.00] | Acrobat Reader [+$10.00] |
 * | 2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00] | 8GB [+$60.00] | 320 GB | Vista Home [+$50.00] | Total Commander [+$5.00] |
 */
public class ComputerPageTest extends BaseTest {

    HomePage homePage;
    ComputerPage computerPage;
    DesktopsPage desktopsPage;
    BuildYourOwnComputerPage buildYourOwnComputerPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        homePage = new HomePage();
        computerPage = new ComputerPage();
        desktopsPage = new DesktopsPage();
        buildYourOwnComputerPage = new BuildYourOwnComputerPage();
    }

    @Test(groups = {"sanity", "smoke",})
    public void verifyUserShouldNavigateToComputerPageSuccessfully() {
        //Click on Computer tab
        homePage.selectMenu("Computers");

        // Verify "Computer" text
        Assert.assertEquals(computerPage.getComputerHeading(), "Computers");
    }

    @Test(groups = {"smoke", "regression"})

    public void verifyUserShouldNavigateToDesktopsPageSuccessfully() {
        // Click on Computer tab
        homePage.selectMenu("Computers");

        // Click on Desktops link
        computerPage.clickOnDesktopLink();

        // Verify "Desktops" text
        Assert.assertEquals(desktopsPage.getDesktopHeading(), "Desktops");
    }

    @Test(groups = {"regression"}, dataProvider = "buildComData", dataProviderClass = TestData.class)
    public void verifyThatUserShouldBuildYouOwnComputerAndAddThemToCartSuccessfully(String processor, String
            ram, String hdd, String os, String software) {

        //Click on computers
        homePage.selectMenu("Computers");

        //Click on desktops
        computerPage.clickOnDesktopLink();

        ////Click on 'Build your own computer'
        desktopsPage.clickOnBuildYourOwnCompLink();

        //selecting specifications
        buildYourOwnComputerPage.selectProcessorFromDropdown(processor);
        buildYourOwnComputerPage.selectRam(ram);
        buildYourOwnComputerPage.selectHddRadio(hdd);
        buildYourOwnComputerPage.selectOs(os);
        buildYourOwnComputerPage.selectCheckBox(software);

        // //click on add to cart button
        buildYourOwnComputerPage.clickOnAddToCart();
    }
}