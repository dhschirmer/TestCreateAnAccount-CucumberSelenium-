import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.After;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.Assert.*;

public class AccountCreationSteps {
    private WebDriver driver;
    private double uniqueEmailId;
    private String lastName;
    private String password;
    private String passwordCheck;
    private boolean tcCheck;


    @Before
    public void setUp() {
        tcCheck = false;
    }

    @Given("I use {string}")
    public void iUse(String browser) {
        if (browser.equals("Google Chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equals("Microsoft Edge")) {
            driver = new EdgeDriver();
        } else {
            fail("Invalid browser");
        }
    }

    @And("I browse to Basketball England membership")
    public void iBrowseToBasketballEnglandMembership() {
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
    }

    @And("I have entered {string} as valid date of birth")
    public void iHaveEnteredAsValidDateOfBirth(String date) {
        driver.findElement(By.id("dp")).click();
        driver.findElement(By.id("dp")).sendKeys(date);

        new Actions(driver)
                .sendKeys(Keys.ESCAPE)
                .perform();
    }

    @And("I have entered {string} as first name")
    public void iHaveEnteredAsFirstName(String firstName) {
        driver.findElement(By.id("member_firstname")).click();
        driver.findElement(By.id("member_firstname")).sendKeys(firstName);
    }

    @And("I have entered {string} as last name")
    public void iHaveEnteredAsLastName(String lastName) {
        driver.findElement(By.id("member_lastname")).click();
        driver.findElement(By.id("member_lastname")).sendKeys(lastName);
        this.lastName = lastName;
    }

    @And("I have entered {string} as email address")
    public void iHaveEnteredAsEmailAddress(String email) {
        driver.findElement(By.id("member_emailaddress")).click();
        uniqueEmailId = Math.random();
        String [] divEmail = email.split("@");
        driver.findElement(By.id("member_emailaddress")).sendKeys(divEmail[0] + "@" + uniqueEmailId + divEmail[1]);
        System.out.println(divEmail[0] + "@" + uniqueEmailId + divEmail[1] );
    }

    @And("I have entered {string} as confirm email address")
    public void iHaveEnteredAsConfirmEmailAddress(String confirmEmail) {
        driver.findElement(By.id("member_confirmemailaddress")).click();
        String [] divEmail = confirmEmail.split("@");
        driver.findElement(By.id("member_confirmemailaddress")).sendKeys(divEmail[0] + "@" + uniqueEmailId + divEmail[1]);
        System.out.println(divEmail[0] + "@" + uniqueEmailId + divEmail[1] );

    }

    @And("I have entered {string} as password")
    public void iHaveEnteredAsPassword(String password) {
        driver.findElement(By.id("signupunlicenced_password")).click();
        driver.findElement(By.id("signupunlicenced_password")).sendKeys(password);
        this.password = password;
    }

    @And("I have entered {string} as retype your password")
    public void iHaveEnteredAsRetypeYourPassword(String confirmPassword) {
        driver.findElement(By.id("signupunlicenced_confirmpassword")).click();
        driver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys(confirmPassword);
        this.passwordCheck = confirmPassword;
    }

    @And("I have checked Terms and Conditions")
    public void iHaveCheckedTermsAndConditions() {
        driver.findElement(By.cssSelector(".md-checkbox > .md-checkbox:nth-child(1) .box")).click();
        this.tcCheck = true;
    }

    @And("I have not checked Terms and Conditions")
    public void iHaveNotCheckedTermsAndConditions() {
    }

    @And("I have checked age control and code of conduct")
    public void iHaveCheckedAgeControlAndCodeOfConduct() {
        driver.findElement(By.cssSelector(".md-checkbox:nth-child(2) > label > .box")).click();
        driver.findElement(By.cssSelector(".md-checkbox:nth-child(7) .box")).click();
    }

    @When("I click Confirm and Join")
    public void iClickConfirmAndJoin() {
        driver.findElement(By.name("join")).click();
    }

    @Then("The membership is created")
    public void theMembershipIsCreated() {
        WebElement field = driver.findElement(By.xpath("/html/body/div/div[2]/div/h2"));
        String expected = "THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND";
        String actual = field.getText();
        assertEquals(expected, actual);

        driver.close();
    }


    @Then("I will get a {string}")
    public void iWillGetA(String expectedErrorMessage) {

        if (lastName.isEmpty()) {
            WebElement field = driver.findElement(By.xpath("/html/body/div/div[2]/div/div/div/div/div/div/div/form/div[5]/div[2]/div/span/span"));
            String actual = field.getText();
            assertEquals(expectedErrorMessage, actual);
        } else if (!Objects.equals(passwordCheck, password)){
            WebElement field = driver.findElement(By.xpath("/html/body/div/div[2]/div/div/div/div/div/div/div/form/div[8]/div/div[2]/div[2]/div/span/span"));
            String actual = field.getText();
            assertEquals(expectedErrorMessage, actual);
        } else if (!tcCheck) {
            WebElement field = driver.findElement(By.xpath("/html/body/div/div[2]/div/div/div/div/div/div/div/form/div[11]/div/div[2]/div[1]/span/span"));
            String actual = field.getText();
            assertEquals(expectedErrorMessage, actual);
        } else {
            fail("Unexpected error");
        }

        driver.close();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
