import io.github.bonigarcia.wdm.WebDriverManager;
import model.MainPage;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

@RunWith(Parameterized.class)
public class FAQTest {
    private WebDriver driver;

    private final int elementIndex;

    private final String expectedText;

    public FAQTest(int elementIndex, String expectedText) {
        this.elementIndex = elementIndex;
        this.expectedText = expectedText;
    }

    @Parameters
    public static Object[][] testData() {
        return new Object[][]{
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        };
    }

    @BeforeClass
    public static void setupClass() {
//         WebDriverManager.firefoxdriver().setup();
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setupTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--remote-allow-origins=*", "--disable-extensions");
        driver = new ChromeDriver(options);
//        driver = new FirefoxDriver();
    }

    @Test
    public void checkFAQ() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickConfirmCookieButton();

        String actualText = mainPage.checkAccordionItemOpensAndGetText(elementIndex);

        Assert.assertEquals(expectedText, actualText);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
