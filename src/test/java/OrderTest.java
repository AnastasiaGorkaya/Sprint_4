import io.github.bonigarcia.wdm.WebDriverManager;
import model.MainPage;
import model.OrderPage;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;

    private final int orderButtonIndex;
    private final String name;
    private final String surname;
    private final String address;
    private final String station;
    private final String phone;
    private final String date;
    private final String days;
    private final String color;
    private final String comment;

    public OrderTest(int orderButtonIndex, String name, String surname, String address, String phone, String station, String date, String days, String color, String comment) {
        this.orderButtonIndex = orderButtonIndex;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.station = station;
        this.phone = phone;
        this.date = date;
        this.days = days;
        this.color = color;
        this.comment = comment;
    }

    @Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {0, "Вася", "Пупкин", "ул. Пупкина, д. 12", "+79087253654", "Парк культуры", "28.03.2023", "сутки", "grey", " "},
                {1, "Эвтаназия", "Степановна", "ул. Беспечности, 8", "00000000000", "Черкизовская", "30.04.2666", "трое суток", "black", "ALLOW"}
        };
    }

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.firefoxdriver().setup();
//        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setupTest() {
        driver = new FirefoxDriver();
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--remote-allow-origins=*", "--disable-extensions");
//        driver = new ChromeDriver(options);
    }

    @Test
    public void checkOrderButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickConfirmCookieButton();
        mainPage.clickOrderButton(orderButtonIndex);

        OrderPage orderPage = new OrderPage(driver);
        orderPage.checkOrderButtonIsDisplayed();
        orderPage.setName(name);
        orderPage.setSurname(surname);
        orderPage.setAddress(address);
        orderPage.setStation(station);
        orderPage.clickStationSelect(station);
        orderPage.setPhone(phone);
        orderPage.clickNextButton();
        orderPage.setDate(date);
        orderPage.clickColor(color);
        orderPage.clickDaysInput();
        orderPage.clickDaysOption(days);
        orderPage.setComment(comment);
        orderPage.clickOrderButton();
        orderPage.checkOrderModalIsDisplayed();
        orderPage.clickConfirmButton();
        orderPage.checkStatusButtonIsDisplayed();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
