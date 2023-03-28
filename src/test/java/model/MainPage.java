package model;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private static final By ACCORDION_ITEMS =
            By.xpath(".//div[@class='accordion__item']");

    private static final By ACCORDION_ITEM_TEXT =
            By.xpath(".//div[@class='accordion__panel']/p");

    private static final By CONFIRM_COOKIE_BUTTON = By.id("rcc-confirm-button");

    private static final By FAQ_BLOCK = By.className("Home_FAQ__3uVm4");

    private WebDriver driver;

    private static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(PAGE_URL);
    }

    public void clickConfirmCookieButton() {
        driver.findElement(CONFIRM_COOKIE_BUTTON).click();
    }

    public boolean isFAQBlockDisplayed(){
        WebElement element = driver.findElement(FAQ_BLOCK);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        WebElement faqBlock = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(FAQ_BLOCK));
        return faqBlock.isDisplayed();
    }

    public void clickAccordionItem(int index) {
        driver.findElements(ACCORDION_ITEMS).get(index).click();
    }

    public String getAccordionItemText(int index) {
        return driver.findElements(ACCORDION_ITEMS).get(index)
                .findElement(ACCORDION_ITEM_TEXT)
                .getText();
    }

    public String checkAccordionItemOpensAndGetText(int index) {
        clickAccordionItem(index);

        Assert.assertTrue(isFAQBlockDisplayed());

        return getAccordionItemText(index);
    }
}
