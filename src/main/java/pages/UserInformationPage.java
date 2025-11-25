package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserInformationPage {
    private WebDriver driver;
    private By nameField = By.xpath(".//input[@placeholder = '* Имя']");
    private By secondNameField = By.xpath(".//input[@placeholder = '* Фамилия']");
    private By addressField = By.xpath(".//input[@placeholder = '* Адрес: куда привезти заказ']");
    private By stationField = By.xpath("//input[@placeholder='* Станция метро']");
    private By phoneField = By.xpath(".//input[@placeholder = '* Телефон: на него позвонит курьер']");
    private By nextButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    public UserInformationPage(WebDriver driver) {
        this.driver = driver;
    }
    public void setNameField(String name) {
        driver.findElement(nameField).sendKeys(name);
    }
    public void setSecondNameField(String secondName) {
        driver.findElement(secondNameField).sendKeys(secondName);
    }
    public void setAddressField(String address) {
        driver.findElement(addressField).sendKeys(address);
    }
    public void setStationField(String station, String fullStationName) {
        driver.findElement(stationField).sendKeys(station);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By option = By.xpath("//button[contains(@class,'select-search__option') and contains(.,'" + fullStationName + "')]");
        wait.until(ExpectedConditions.elementToBeClickable(option));
        driver.findElement(option).click();
    }
    public void setPhoneField(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }
    public void fillUserInfoPage(String name, String secondName, String address, String stationShort,
                                 String stationFull, String phone) {
        setNameField(name);
        setSecondNameField(secondName);
        setAddressField(address);
        setStationField(stationShort, stationFull);
        setPhoneField(phone);
    }
}
