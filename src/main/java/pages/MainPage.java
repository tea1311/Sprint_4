package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage {
    private WebDriver driver;
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";
    private By cookieQuestion = By.className("App_CookieConsent__1yUIN");
    private By acceptCookieButton = By.className("App_CookieButton__3cvqF");
    private By orderButtonHeader = By.xpath("(//button[text()='Заказать'])[1]");
    private By orderButtonBottom = By.xpath("(//button[text()='Заказать'])[2]");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    public void acceptCookiesIfPresent() {
        List<WebElement> cookies= driver.findElements(cookieQuestion);
        if(!cookies.isEmpty()) {
            driver.findElement(acceptCookieButton).click();
        }
    }
    public void clickOrderButtonHeader() {
        driver.findElement(orderButtonHeader).click();
    }
    public void openPage() {
        driver.get(BASE_URL);
    }
    public void clickOrderButtonBottom(){
        WebElement element = driver.findElement(orderButtonBottom);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(orderButtonBottom));
        driver.findElement(orderButtonBottom).click();
    }
}
