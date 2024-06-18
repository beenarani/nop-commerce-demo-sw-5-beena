package com.nopcommerce.demo.testsuite;

import com.google.common.base.Verify;
import com.nopcommerce.demo.pages.HomePage;
import com.nopcommerce.demo.pages.LoginPage;
import com.nopcommerce.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import resources.testdata.TestData;

/**
 * 1.LoginPageTest
 * Inside LoginPageTest class create following testmethods
 * 1.userShouldNavigateToLoginPageSuccessFully().
 * Click on login link
 * verify that "Welcome, Please Sign In!" message display
 * 2. verifyTheErrorMessageWithInValidCredentials().
 * Click on login link
 * Enter EmailId
 * Enter Password
 * Click on Login Button
 * Verify that the Error message
 * 3. verifyThatUserShouldLogInSuccessFullyWithValidCredentials.
 * Click on login link
 * Enter EmailId
 * Enter Password
 * Click on Login Button
 * Verify that LogOut link is display
 * 4. verifyThatUserShouldLogOutSuccessFully()
 * Click on login link
 * Enter EmailId
 * Enter Password
 * Click on Login Button
 * Click on LogOut Link
 * Verify that LogIn Link Display
 */
public class LoginPageTest extends BaseTest {
    LoginPage loginPage;
    HomePage homePage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        loginPage = new LoginPage();
        homePage = new HomePage();
    }

    @Test(groups = {"sanity", "smoke", "regression"})
    public void userShouldNavigateToLoginPageSuccessFully() {
        //Click on login link
        homePage.clickOnLoginLink();

        //verify that "Welcome, Please Sign In!" message display
        String expectedMessage = "Welcome, Please Sign In!";
        String actualMessage = loginPage.getWelcomeText();
        Assert.assertEquals(actualMessage, expectedMessage, "Login page welcome msg not displayed");
    }

    @Test(groups = {"smoke", "regression"}, dataProvider = "invalidCredentials", dataProviderClass = TestData.class)
    public void verifyTheErrorMessageWithInValidCredentials(String email, String password) {
        //Click on login link
        homePage.clickOnLoginLink();

        //Enter EmailId
        loginPage.enterEmailId(email);

        //Enter Password
        loginPage.enterPassword(password);

        //Click on Login Button
        loginPage.clickOnLoginButton();

        //Verify that the Error message
        String expMsg = "Login was unsuccessful. Please correct the errors and try again." +
                "No customer account found";
        String actMsg = loginPage.getErrorMessage();
        Assert.assertEquals(actMsg, expMsg);
    }

    @Test(groups = {"regression"}, dataProvider = "validCredentials", dataProviderClass = TestData.class)
    public void verifyThatUserShouldLogInSuccessFullyWithValidCredentials(String email, String password) {

        //Click on login link
        homePage.clickOnLoginLink();

        //Enter EmailId
        loginPage.enterEmailId(email);

        //Enter Password
        loginPage.enterPassword(password);

        //Click on Login Button
        loginPage.clickOnLoginButton();

        //Verify that LogOut link is display
        String expected = "Log Out";
        String actual = homePage.getLogoutLinkText();
        Assert.assertEquals(actual, expected);

    }

    @Test(groups = {"regression"}, dataProvider = "validCredentials", dataProviderClass = TestData.class)

    public void verifyThatUserShouldLogOutSuccessFully(String email, String password) {
        //Click on login link
        homePage.clickOnLoginLink();

        //Enter EmailId
        loginPage.enterEmailId(email);

        //Enter Password
        loginPage.enterPassword(password);

        //Click on Login Button
        loginPage.clickOnLoginButton();

        //Click on LogOut Link
        homePage.clickOnLogoutLink();

        //Verify that LogIn Link Display
        String expMsg = "Log in";
        String actMsg = homePage.getLoginLinkText();
        Assert.assertEquals(actMsg, expMsg);
    }
}