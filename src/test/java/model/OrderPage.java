package model;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    private WebDriver driver;

    private static final By ORDER_CONTENT =
            By.xpath(".//div[contains(@class, 'Order_Content')]");

    private static final By NAME_INPUT =
            By.xpath(".//input[@placeholder='* Имя']");

    private static final By SURNAME_INPUT =
            By.xpath(".//input[@placeholder='* Фамилия']");

    private static final By ADDRESS_INPUT =
            By.xpath(".//input[contains(@placeholder, 'Адрес')]");

    private static final By STATION_INPUT =
            By.xpath(".//input[contains(@placeholder, 'Станция метро')]");

    private static final By PHONE_INPUT =
            By.xpath(".//input[contains(@placeholder, 'Телефон')]");

    private static final By NEXT_BUTTON =
            By.xpath(".//div[contains(@class, 'Order_Content')]//button[text()='Далее']");

    private static final By DATE_INPUT =
            By.xpath(".//input[contains(@placeholder, 'Когда привезти')]");

    private static final By BLACK_COLOR = By.id("black");

    private static final By GREY_COLOR = By.id("grey");

    private static final By DAYS_INPUT =
            By.xpath(".//div[contains(text(), 'Срок аренды')]");

    private static final By DROP_DOWN_MENU =
            By.xpath(".//div[contains(@class, 'Dropdown-menu')]");

    private static final By COMMENT_INPUT =
            By.xpath(".//input[contains(@placeholder, 'Комментарий')]");

    private static final By ORDER_BUTTON =
            By.xpath(".//div[contains(@class, 'Order_Content')]//button[text()='Заказать']");

    private static final By ORDER_MODAL =
            By.xpath(".//div[contains(@class, 'Order_Modal')]");

    private static final By CONFIRM_BUTTON =
            By.xpath(".//div[contains(@class, 'Order_Content')]//button[text()='Да']");

    private static final By STATUS_BUTTON =
            By.xpath(".//div[contains(@class, 'Order_Content')]//button[text()='Посмотреть статус']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void checkOrderButtonIsDisplayed() {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(driver.findElement(ORDER_CONTENT)));
        Assert.assertTrue(element.isDisplayed());
    }

    public void setName(String name) {
        driver.findElement(NAME_INPUT).sendKeys(name);
    }

    public void setSurname(String surname) {
        driver.findElement(SURNAME_INPUT).sendKeys(surname);
    }

    public void setAddress(String address) {
        driver.findElement(ADDRESS_INPUT).sendKeys(address);
    }

    public void setStation(String station) {
        driver.findElement(STATION_INPUT).sendKeys(station);
    }

    public void clickStationSelect(String station) {
        driver.findElement(
                By.xpath(".//div[contains(@class, 'Order_Content')]//div[contains(@class, 'Order_Text') and text()='" + station + "']")
        ).click();
    }

    public void setPhone(String phone) {
        driver.findElement(PHONE_INPUT).sendKeys(phone);
    }

    public void clickNextButton() {
        driver.findElement(NEXT_BUTTON).click();
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.visibilityOf(driver.findElement(DATE_INPUT)));
        Assert.assertTrue(element.isDisplayed());
    }

    public void setDate(String date) {
        driver.findElement(DATE_INPUT).sendKeys(date);
    }

    public void clickColor(String color) {
        if (color.equals("black")) {
            driver.findElement(BLACK_COLOR).click();
        } else {
            driver.findElement(GREY_COLOR).click();
        }
    }

    public void clickDaysInput() {
        driver.findElement(DAYS_INPUT).click();
    }

    public void clickDaysOption(String option) {
        driver.findElement(DROP_DOWN_MENU)
                .findElement(By.xpath(".//div[text()='" + option + "']")).click();
    }

    public void setComment(String comment) {
        driver.findElement(COMMENT_INPUT).sendKeys(comment);
    }

    public void clickOrderButton() {
        driver.findElement(ORDER_BUTTON).click();
    }

    public void checkOrderModalIsDisplayed() {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(driver.findElement(ORDER_MODAL)));
        Assert.assertTrue(element.isDisplayed());
    }

    public void clickConfirmButton() {
        driver.findElement(CONFIRM_BUTTON).click();
    }

    public void checkStatusButtonIsDisplayed() {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(driver.findElement(STATUS_BUTTON)));
        Assert.assertTrue(element.isDisplayed());
    }
}
