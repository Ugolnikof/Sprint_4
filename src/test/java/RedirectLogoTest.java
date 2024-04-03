import configure.EnvConfig;
import driverRule.DriverRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import pages.YandexPage;


public class RedirectLogoTest {
    String text = "Hello, world!";

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

        YandexPage ya = mainPage.clickOnYandex();

        ya.switchToNewTab()
                .enterSomeText(text);

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
