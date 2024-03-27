package pages;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import configure.EnvConfig;
import org.openqa.selenium.*;

import java.time.Duration;

public class MainPage {
    private static WebDriver driver;

    // локатор блока "Вопросы о важном"
    private final By questions = By.cssSelector("[class='accordion']");

    // локатор кнопки "Заказать" в Хэдере
    private final By buttonInHeader = By.xpath(".//div[contains(@class,'Header_Nav')]/button[contains(@class,'Button_Button')]");

    // локатор большой кнопки "Заказать" внизу страницы
    private final By bigButton = By.xpath(".//div[contains(@class,'Home_FinishButton')]/button[contains(@class,'Button_Button')]");

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
}
