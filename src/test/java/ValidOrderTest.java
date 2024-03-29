import driverRule.DriverRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pages.AboutOrder;
import pages.MainPage;
import pages.UserInformation;
import configure.EnvConfig;


@RunWith(Parameterized.class)
public class ValidOrderTest {

    private final String name;
    private final String surName;
    private final String address;
    private final int idStation;
    private final String telephone;
    private final String date;
    private final String period;
    private final String comment;
    private final String color;

    public ValidOrderTest(String name, String surName, String address, int idStation, String telephone, String date, String period, String color, String comment) {
        this.name = name;
        this.surName = surName;
        this.address = address;
        this.idStation = idStation;
        this.telephone = telephone;
        this.date = date;
        this.period = period;
        this.color = color;
        this.comment = comment;
    }


    @Rule
    public DriverRule driverRule = new DriverRule();

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {"Иван", "Иванов", "Иваново", 0, "89111111111",  "01.06.2024", "сутки", "black", "Оставить у подъезда"},
                {"Медведь", "Медведев", "Берлога", 224, "89222222222",  "02.06.2024", "семеро суток", "grey", ""}
        };
    }
    @Test
    public void CreateValidOrderByButtonInHeader() {
        WebDriver driver = driverRule.getDriver();

        MainPage mainPage = new MainPage(driver);
        mainPage.open()
                .addCookie(EnvConfig.COOKIE_NAME_1, EnvConfig.COOKIE_VALUE)
                .addCookie(EnvConfig.COOKIE_NAME_2, EnvConfig.COOKIE_NAME_2)
                .open();


        UserInformation userInformation = mainPage.clickOnOrderButtonInHeader();
        userInformation.enterUserName(name)
                .enterUserSurName(surName)
                .enterUserAddress(address)
                .selectMetroStation(idStation)
                .enterUserPhone(telephone);


        AboutOrder aboutOrder = userInformation.clickOnButtonNext();
        aboutOrder.enterDate(date)
                .selectOrderPeriod(period)
                .selectColor(color)
                .enterComment(comment)
                .clickToContinueOrder()
                .confirmOrder()
                .checkTheOrderCreation();

    }


    @Test
    public void createValidOrderByBigButton() {
        WebDriver driver = driverRule.getDriver();

        MainPage mainPage = new MainPage(driver);
        mainPage.open()
                .addCookie(EnvConfig.COOKIE_NAME_1, EnvConfig.COOKIE_VALUE)
                .addCookie(EnvConfig.COOKIE_NAME_2, EnvConfig.COOKIE_NAME_2)
                .open();

        UserInformation userInformation = mainPage.clickOnBigOrderButton();
        userInformation.enterUserName(name)
                .enterUserSurName(surName)
                .enterUserAddress(address)
                .selectMetroStation(idStation)
                .enterUserPhone(telephone);


        AboutOrder aboutOrder = userInformation.clickOnButtonNext();
        aboutOrder.enterDate(date)
                .selectOrderPeriod(period)
                .selectColor(color)
                .enterComment(comment)
                .clickToContinueOrder()
                .confirmOrder()
                .checkTheOrderCreation();
    }


}
