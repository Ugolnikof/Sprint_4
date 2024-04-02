import configure.EnvConfig;
import driverRule.DriverRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class RedirectLogoTest {

    @Rule
    public DriverRule driverRule = new DriverRule();

    @Test()
    public void redirectToYandex() {
        WebDriver driver = driverRule.getDriver();

        MainPage mainPage = new MainPage(driver);
        mainPage.open()
                .addCookie(EnvConfig.COOKIE_NAME_1, EnvConfig.COOKIE_VALUE)
                .addCookie(EnvConfig.COOKIE_NAME_2, EnvConfig.COOKIE_NAME_2)
                .open();

        driver.findElement(By.xpath(".//a[contains(@class,'Header_LogoYandex')]")).click();

        List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
        //switch to new tab
        driver.switchTo().window(browserTabs.get(1));
        //check is it correct page opened or not (e.g. check page's title)

        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[name]")));

        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.name("text")));

        driver.findElement(By.name("text")).sendKeys("hello world");

        //then close tab and get back
        driver.close();
        driver.switchTo().window(browserTabs.get(0));

    }


    @Test
    public void redirectToSamokat() {
        WebDriver driver = driverRule.getDriver();

        MainPage mainPage = new MainPage(driver);
        mainPage.open()
                .addCookie(EnvConfig.COOKIE_NAME_1, EnvConfig.COOKIE_VALUE)
                .addCookie(EnvConfig.COOKIE_NAME_2, EnvConfig.COOKIE_NAME_2)
                .open()
                .clickOnSamokat()
                .checkRedirectToSamokat();
    }


}
