import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.FAQPage;
import pages.MainPage;
import pages.RentalDataPages;
import pages.UserInformationPage;

public class BaseTest {
    // Открыть браузер
    WebDriver driver;
    MainPage mainPage;
    UserInformationPage userInformationPage;
    RentalDataPages rentalDataPages;
    FAQPage faqPage;

    @Before
    public void startUp(){
        String browser = System.getProperty("browser", "chrome");
        if (browser.equals("chrome")) {
            startBrowserChrome();
        } else if (browser.equals("firefox")) {
            startBrowserFirefox();
        }
        mainPage = new MainPage(driver);
        userInformationPage = new UserInformationPage(driver);
        rentalDataPages = new RentalDataPages(driver);
        faqPage = new FAQPage(driver);
    }
    public void startBrowserFirefox() {
        driver = new FirefoxDriver();
        WebDriverManager.firefoxdriver().setup();
    }
    public void startBrowserChrome() {
        driver = new ChromeDriver();
        WebDriverManager.chromedriver().setup();
    }
    @After
    public void tearDown(){
        driver.quit();
    }
}
