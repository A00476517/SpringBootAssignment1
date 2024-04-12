package com.example.hotelapp;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class RegistrationTest {
    WebDriver webDriver;

    private String baseURL = "https://dev.cs.smu.ca/sel/practice.html";

    @BeforeClass
    public void initializeDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\LENOVO\\Downloads\\chromedriver-win64_new\\chromedriver-win64\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get(baseURL);
    }

    @AfterClass
    public void closeDriver() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Test(dataProvider = "registrationData")
    public void registerUser(String fullName, String emailAddress, String gender, String country, boolean subscribeNewsletter) {

        WebDriverWait wait = new WebDriverWait(webDriver, 80);
        try {

            wait.until(ExpectedConditions.urlContains("practice.html"));

            // Fill registration form
            WebElement nameInput = webDriver.findElement(By.id("name"));
            WebElement emailInput = webDriver.findElement(By.id("email"));
            WebElement maleRadioBtn = webDriver.findElement(By.id("male"));
            WebElement femaleRadioBtn = webDriver.findElement(By.id("female"));

            Select countryDropdown = new Select(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='country']"))));
            WebElement submitBtn = webDriver.findElement(By.id("submit"));

            nameInput.sendKeys(fullName);
            emailInput.sendKeys(emailAddress);

            if (gender.equalsIgnoreCase("Male")) {
                maleRadioBtn.click();
            } else if (gender.equalsIgnoreCase("Female")) {
                femaleRadioBtn.click();
            }

            countryDropdown.selectByVisibleText(country);

            wait.until(ExpectedConditions.textToBePresentInElementValue(countryDropdown.getFirstSelectedOption(), country));

            WebElement newsletterCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='checkbox' and @id='newsletter']")));

            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", newsletterCheckbox);

            if (!newsletterCheckbox.isSelected() && subscribeNewsletter && subscribeNewsletter == true) {
                newsletterCheckbox.click();
            }
            wait.until(ExpectedConditions.elementToBeClickable(submitBtn));

            submitBtn.click();

            wait.until(ExpectedConditions.urlContains("confirmation.html"));

            // Verify confirmation page
            WebElement confirmationMsg = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("details-container")));

            wait.until(ExpectedConditions.visibilityOf(confirmationMsg));
            Assert.assertTrue(confirmationMsg.isDisplayed());

            // Verify registration details on confirmation page
            WebElement nameConfirmation = webDriver.findElement(By.xpath("//div[@class='details-container']//span[@id='name']"));
            WebElement emailConfirmation = webDriver.findElement(By.xpath("//div[@class='details-container']//span[@id='email']"));
            WebElement countryConfirmation = webDriver.findElement(By.xpath("//div[@class='details-container']//span[@id='country']"));
            WebElement genderConfirmation = webDriver.findElement(By.xpath("//div[@class='details-container']//span[@id='gender']"));
            WebElement newsletterConfirmation = webDriver.findElement(By.xpath("//div[@class='details-container']//span[@id='newsletterValue']"));
            String expectedNewsletterText = subscribeNewsletter ? "Yes" : "No";

            Assert.assertEquals(nameConfirmation.getText(), fullName);
            Assert.assertEquals(emailConfirmation.getText(), emailAddress);
            Assert.assertEquals(genderConfirmation.getText(), gender);
            Assert.assertEquals(countryConfirmation.getText(), country);
            Assert.assertEquals(newsletterConfirmation.getText(), expectedNewsletterText);

            WebElement goBackLink = webDriver.findElement(By.xpath("//a[contains(text(),'Go Back to Home')]"));
            goBackLink.click();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test failed: " + e.getMessage());
        }

    }

    @DataProvider(name = "registrationData")
    public Object[][] provideRegistrationData() {

        return new Object[][]{
                {"Vrushali", "Vrushali@gmail.com", "Male", "Canada", true},
                {"Anne", "Anne@gmail.com", "Female", "Australia", false},
                {"Kush", "Jordan@gmail.com", "Male", "United Kingdom", false},
                {"Andrew", "Kush@gmail.com", "Male", "United Kingdom", false},

        };
    }
}
