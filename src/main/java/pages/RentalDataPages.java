package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RentalDataPages {
    private WebDriver driver;
    private By orderDate = By.xpath(".//input[@placeholder = '* Когда привезти самокат']");
    private By rentalPeriod = By.xpath(".//div[@class = 'Dropdown-control']");
    private By blackScooterColour = By.xpath(".//label[@for = 'black']");
    private By greyScooterColour = By.xpath(".//label[@for = 'grey']");
    private By commentField = By.xpath(".//input[@placeholder = 'Комментарий для курьера']");
    private By confirmOrderButton = By.xpath("(//button[text() = 'Заказать'])[2]");
    private By modalConfirmOrder = By.xpath("//div[contains(@class, 'Order_Modal')]");
    private By confirmYesButton = By.xpath("//button[text() = 'Да']");
    private By orderAcceptHeader = By.xpath("//div[contains(@class,'Order_ModalHeader') and text()='Заказ оформлен']");
    private By calendarDay(String day) {
        return By.xpath("//div[contains(@class,'react-datepicker__day') and text()='" + day + "']");
    }

    public RentalDataPages(WebDriver driver) {
        this.driver = driver;
    }

    public void setOrderDate(String day) {
        driver.findElement(orderDate).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(calendarDay(day)));
        driver.findElement(calendarDay(day)).click();
    }

    public void setRentalPeriod(String period) {
        driver.findElement(rentalPeriod).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By periodList = By.xpath("//div[contains(@class,'Dropdown-option') and (text()='" + period + "')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(periodList));
        driver.findElement(periodList).click();
    }

    public void setGreyScooterColour() {
        driver.findElement(greyScooterColour).click();
    }

    public void setBlackScooterColour() {
        driver.findElement(blackScooterColour).click();
    }

    public void setCommentField(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    public void clickOrderButton() {
        driver.findElement(confirmOrderButton).click();
    }

    public void clickConfirmOrderButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(modalConfirmOrder));
        wait.until(ExpectedConditions.elementToBeClickable(confirmYesButton));
        driver.findElement(confirmYesButton).click();
    }
    public boolean isOrderAccept() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(orderAcceptHeader));
        return successMessage.isDisplayed();
    }
    public void fillRentalDataPage(String day, String period, String comment) {
        setOrderDate(day);
        setRentalPeriod(period);
        setGreyScooterColour();
        setCommentField(comment);
    }
}

