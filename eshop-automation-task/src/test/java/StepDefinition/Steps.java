package StepDefinition;

import Com.Vodafone.Base.Base;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.TimeoutException;

public class Steps extends Base {




    @Given("the user in the home page of vodafone shop website")
    public void the_user_in_the_home_page_of_vodafone_shop_website() throws IOException {
        openBrowser();

    }
    @When("the user select English as a language")
    public void the_user_select_English_as_english_a_language() {
        driver.findElement(By.className("lang")).click();
    }


    @And("the user select the brand from brand sections")
    public void theUserSelectTheBrandFromBrandSections() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            WebElement cookieMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cookie-text")));
            // Cookie message found, dismiss it using JavaScript executor
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", cookieMessage.findElement(By.className("fa-times")));
        } catch (TimeoutException e) {
            // Cookie message not found, continue
        }

        WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/div/app-home-page/div/app-brand/div/div/div[1]/ul/li[7]/a/div")));
        element2.click();
    }


    @And("the user selected one of the returned products")
    public void theUserSelectedOneOfTheReturnedProducts() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        //show more
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/div/app-shop-by-brand/div/div/div[2]/div/div[3]/div/div/div[2]/button")));
        element.click();

        //show more again to show more products
        WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/div/app-shop-by-brand/div/div/div[2]/div/div[3]/div/div/div[2]/button")));
        element2.click();

        WebElement element3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"home-0\"]/div/ul/li[23]/a/div/div[3]/a/span")));
        element3.click();
    }

    @And("the user add the selected item to the basket")
    public void theUserAddTheSelectedItemToTheBasket() {
        driver.manage().timeouts().implicitlyWait(6000, TimeUnit.SECONDS);

        //if the product is out of stock
        if (driver.findElement(By.xpath("//*[text()='Add to basket']")).isDisplayed()) {
            driver.findElement(By.xpath("//*[text()='Add to basket']")).click();
        }
        else {
            driver.navigate().back();
            driver.manage().timeouts().implicitlyWait(9000, TimeUnit.SECONDS);
            driver.findElement(By.xpath("//*[@id=\"home-0\"]/div/ul/li[7]/a/div/div[3]/a/span")).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Add to basket']")));
            element.click();
        }
    }

    @And("the user proceed to checkout")
    public void theUserProceedToCheckout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/div/app-shoping-cart/div/div[1]/div[2]/div[2]/div[2]/div/div[3]/button")));
        element.click();
    }

    @And("the user fill delivery option fields")
    public void theUserFillDeliveryOptionFields() {
        driver.manage().timeouts().implicitlyWait(6000, TimeUnit.SECONDS);
        Select DropDownTown = new Select(driver.findElement(By.xpath("//*[@id=\"headingOne\"]/div[1]/select")));
        DropDownTown.selectByValue("0");
        driver.manage().timeouts().implicitlyWait(6000, TimeUnit.SECONDS);
        Select DropDownArea = new Select(driver.findElement(By.xpath("//*[@id=\"headingOne\"]/div[2]/select")));
        DropDownArea.selectByVisibleText("Ain Shams");
        driver.findElement(By.xpath("//*[@id=\"collapseOne\"]/div/div[2]/div[1]/div[1]/div[1]/p")).click();
    }
    @And("the user add address details")
    public void theUserAddAddressDetails(DataTable address) {
        driver.manage().timeouts().implicitlyWait(4000, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("[placeholder=\"Street Name\"]")).sendKeys(address.cell(0,0));
        driver.findElement(By.cssSelector("[placeholder=\"196\"]")).sendKeys(address.cell(0,1));
        driver.findElement(By.cssSelector("[maxlength=\"2\"]")).sendKeys(address.cell(0,2));
        driver.findElement(By.cssSelector("[maxlength=\"5\"]")).sendKeys(address.cell(0,3));
        driver.findElement(By.cssSelector("[placeholder=\"Landmark\"]")).sendKeys(address.cell(0,4));
        driver.findElement(By.cssSelector("[placeholder=\"Address Name\"]")).sendKeys(address.cell(0,5));
        driver.findElement(By.xpath("//*[text()=' Continue ']")).click();
    }


    @And("the user fill personal info fields but leave full name field empty")
    public void theUserFillPersonalInfoFieldsButLeaveFullNameFieldEmpty(DataTable personalInfo) {
        driver.manage().timeouts().implicitlyWait(4000, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("[placeholder=\"Email\"]")).sendKeys(personalInfo.cell(0,0));
        driver.findElement(By.cssSelector("[placeholder=\"01012345678\"]")).sendKeys(personalInfo.cell(0,1));
        driver.findElement(By.cssSelector("[placeholder=\"Landline/Alternative Number\"]")).sendKeys(personalInfo.cell(0,2));
        driver.findElement(By.id("shippingAddressContinue")).click();
    }


    @Then("error message should be appear when the user click continue without fill the full name field empty")
    public void errorMessageShouldBeAppearWhenTheUserClickContinueWithoutFillTheFullNameFieldEmpty() {
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);

        Assert.assertTrue(driver.findElement(By.xpath("//*[text()=' Please enter a valid name ']")).isDisplayed());

    }

}
