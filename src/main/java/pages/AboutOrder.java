package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import configure.EnvConfig;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class AboutOrder {
    private final WebDriver driver;

    // локатор заголовка "Заказ оформлен"
    private final By completeOrder = By.xpath(".//div[text()='Заказ оформлен']");

    // локатор модального окна с подтверждением заказа
    private final By orderModalHeader = By.xpath(".//div[contains(@class, 'Order_ModalHeader')]");

    // локатор кнопка "Да" модального окна с подтверждением заказа
    private final By buttonYes = By.xpath(".//button[text()='Да']");

    // локатор кнопки "Заказать"
    private final By continueButton = By.xpath(".//div[contains(@class, 'Order_Buttons')]/button[text()='Заказать']");

    // локатор поля "Комментарий"
    private final By orderComment = By.cssSelector("[placeholder='Комментарий для курьера']");

    // локатор заголовка "Про аренду"
    private final By aboutOrderHeader = By.xpath(".//div[text()='Про аренду']");

    // локатор выпадающего списка с выбором периода аренды
    private final By dropDown = By.cssSelector(".Dropdown-placeholder");

    // локатор поля ввода даты
    private final By orderDate = By.cssSelector("[placeholder='* Когда привезти самокат']");


    public AboutOrder(WebDriver driver) {
        this.driver = driver;
    }

    public void checkTheOrderCreation() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(completeOrder));

        assertTrue(driver.findElement(completeOrder).isDisplayed());
    }

    public AboutOrder confirmOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(orderModalHeader));

        driver.findElement(buttonYes).click();

        return this;
    }

    public AboutOrder clickToContinueOrder() {
        driver.findElement(continueButton).click();

        return this;
    }

    public AboutOrder enterComment(String comment) {
        driver.findElement(orderComment).sendKeys(comment);

        return this;
    }

    public AboutOrder selectColor(String color) {
        driver.findElement(aboutOrderHeader).click();
        driver.findElement(By.id(color)).click();

        return this;
    }

    public AboutOrder selectOrderPeriod(String period) {
        driver.findElement(aboutOrderHeader).click();
        driver.findElement(dropDown).click();
        driver.findElement(By.xpath(".//div[text()='" + period + "']")).click();

        return this;
    }

    public AboutOrder enterDate(String date) {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(orderDate));

        driver.findElement(orderDate).sendKeys(date);

        return this;
    }
}
