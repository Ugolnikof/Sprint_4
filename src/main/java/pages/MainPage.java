package pages;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import configure.EnvConfig;
import org.openqa.selenium.*;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;

    // локатор блока "Вопросы о важном"
    private final By questions = By.cssSelector("[class='accordion']");

    // локатор кнопки "Заказать" в Хэдере
    private final By buttonInHeader = By.xpath(".//div[contains(@class,'Header_Nav')]/button[contains(@class,'Button_Button')]");

    // локатор большой кнопки "Заказать" внизу страницы
    private final By bigButton = By.xpath(".//div[contains(@class,'Home_FinishButton')]/button[contains(@class,'Button_Button')]");

    // локатор кнопки "Go"
    private final By goButton = By.cssSelector("[class*=Header_Button]");

    // локатор поля ввода номера заказа
    private final By headerInputOrderNumber = By.cssSelector("[class*=Header_Input]");

    // локатор кнопки "Статус заказа"
    private final By orderStatusButton = By.cssSelector("[class*=Header_Link]");

    // локатор хэдера страницы
    private final By pageHeader = By.xpath(".//div[contains(@class,'Home_Header')]");

    // локатор лого "Самокат" в хэдере
    private final By samokatLogo = By.xpath(".//a[contains(@class,'Header_LogoScooter')]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // метод добавления cookie
    public MainPage addCookie(String name, String value) {
        Cookie newCookie = new Cookie(name, value);
        driver.manage().addCookie(newCookie);

        return this;
    }


    public Faq scrollToFaq() {
        WebElement element = driver.findElement(questions);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);

        return new Faq(driver);
    }


    public MainPage open() {
        driver.get(EnvConfig.BASE_URL);

        return this;
    }

    public UserInformation clickOnOrderButtonInHeader() {
        driver.findElement(buttonInHeader).click();

        return new UserInformation(driver);
    }

    public UserInformation clickOnBigOrderButton() {
        WebElement element = driver.findElement(bigButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);

        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(bigButton));

        driver.findElement(bigButton).click();

        return new UserInformation(driver);
    }

    public StatusPage clickOnGo() {
        driver.findElement(goButton).click();

        return new StatusPage(driver);
    }

    public MainPage enterOrderNumber(String orderNumber) {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(headerInputOrderNumber));

        driver.findElement(headerInputOrderNumber).sendKeys(orderNumber);

        return this;
    }

    public MainPage clickOnStatusButton() {
        driver.findElement(orderStatusButton).click();

        return this;
    }

    public void checkRedirectToSamokat() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(pageHeader));

    }

    public MainPage clickOnSamokat() {
        driver.findElement(samokatLogo).click();

        return this;
    }

    public YandexPage clickOnYandex() {
        driver.findElement(By.xpath(".//a[contains(@class,'Header_LogoYandex')]")).click();

        return new YandexPage(driver);
    }

}
