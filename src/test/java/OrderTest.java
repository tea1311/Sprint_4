import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class OrderTest extends BaseTest {
    private final String name;
    private final String secondName;
    private final String address;
    private final String stationShort;
    private final String stationFull;
    private final String phone;
    private final String day;
    private final String period;
    private final String comment;
    private final String buttonType;

    public OrderTest(String name, String secondName, String address,
                     String stationShort, String stationFull, String phone,
                     String day, String period, String comment, String buttonType) {

        this.name = name;
        this.secondName = secondName;
        this.address = address;
        this.stationShort = stationShort;
        this.stationFull = stationFull;
        this.phone = phone;
        this.day = day;
        this.period = period;
        this.comment = comment;
        this.buttonType = buttonType;
    }

    @Parameterized.Parameters
    public static Object[][] getRentalData() {
        return new Object[][]{
                {"Елизавета", "Томская", "Открытое шоссе, 5а", "Преоб", "Преображенская площадь", "+79128327654", "28", "трое суток", "Позвонить заранее", "header"},
                {"Олег", "Ким", "Преображенская, 4", "Черкиз", "Черкизовская", "89235678654", "18", "сутки", "Ожидаю в 10", "bottom"}
        };
    }

    @Test
// Оформление заказа на кнопку Заказать
    public void getOrder() {
        // 1. Открыть сайт
        mainPage.openPage();
        // 2. Проверить и принять куки
        mainPage.acceptCookiesIfPresent();
        // 3. Нажать кнопку Заказать
        if ("header".equals(buttonType)) {
            mainPage.clickOrderButtonHeader();
        } else if ("bottom".equals(buttonType)) {
            mainPage.clickOrderButtonBottom();
        }
        // 4. Заполнение формы информация о пользователе
        userInformationPage.fillUserInfoPage(name, secondName, address, stationShort, stationFull, phone);
        // 5. Нажать кнопку Далее
        userInformationPage.clickNextButton();
        // 6. Заполнение данных об аренде
        rentalDataPages.fillRentalDataPage(day, period, comment);
        // 7. Нажать кнопку заказать
        rentalDataPages.clickOrderButton();
        // 8. Подтвердить оформление заказа
        rentalDataPages.clickConfirmOrderButton();
        // 9. Сравнение ОР (заказ принят) и ФР
        Assert.assertTrue("Сообщение о принятом заказе не отображается", rentalDataPages.isOrderAccept());
    }
}

