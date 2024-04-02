import configure.EnvConfig;
import driverRule.DriverRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pages.MainPage;


@RunWith(Parameterized.class)
public class InvalidOrderTest {
    private final String orderNumber;

    public InvalidOrderTest(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Rule
    public DriverRule driverRule = new DriverRule();

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {"123"},
                {"456"},
                {"789"}
        };
    }

    @Test()
    public void checkInvalidOrder() {
        WebDriver driver = driverRule.getDriver();

        MainPage mainPage = new MainPage(driver);
        mainPage.open()
                .addCookie(EnvConfig.COOKIE_NAME_1, EnvConfig.COOKIE_VALUE)
                .addCookie(EnvConfig.COOKIE_NAME_2, EnvConfig.COOKIE_NAME_2)
                .open()
                .clickOnStatusButton()
                .enterOrderNumber(orderNumber)
                .clickOnGo()
                .checkNotFoundImg();
    }



}
