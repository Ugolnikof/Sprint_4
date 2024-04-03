package pages;

import configure.EnvConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class YandexPage {
    private final WebDriver driver;

    // локатор фрейма в котором находится поле ввода
    private final By frameInputText = By.cssSelector("iframe[name]");

    // локатор поля поиска информации
    private final By inputSearch = By.name("text");

    public YandexPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterSomeText(String text) {
        driver.switchTo().frame(driver.findElement(frameInputText));

        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(inputSearch));

        driver.findElement(inputSearch).sendKeys(text);
    }

    public YandexPage switchToNewTab() {
        // создан список с вкладками браузера
        List<String> browserTabs = new ArrayList<>(driver.getWindowHandles());
        // переключаюсь на вторую вкладку
        driver.switchTo().window(browserTabs.get(1));
        //check is it correct page opened or not (e.g. check page's title)

        return this;
    }



}
