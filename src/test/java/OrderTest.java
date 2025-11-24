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
        mainPage.clickOrderButtonHeader();
        // 4. В поле Имя ввести Имя
        userInformationPage.setNameField(name);
        // 5. В поле Фамилия ввести фамилию
        userInformationPage.setSecondNameField(secondName);
        // 6. В поле Адрес ввести валидный адрес
        userInformationPage.setAddressField(address);
        // 7. В поле Станция метро ввести станцию
        userInformationPage.setStationField(stationShort, stationFull);
        // 8. В поле Телефон ввести валидный номер
        userInformationPage.setPhoneField(phone);
        // 9. Нажать кнопку Далее
        userInformationPage.clickNextButton();
        // 10. Выбрать дату доставки самоката
        rentalDataPages.setOrderDate(day);
        // 11. Выбрать срок аренды
        rentalDataPages.setRentalPeriod(period);
        // 12.Выбрать цвет самоката
        rentalDataPages.setGreyScooterColour();
        // 13. Оставить комментарий для курьера
        rentalDataPages.setCommentField(comment);
        // 14. Нажать кнопку заказать
        rentalDataPages.clickOrderButton();
        // 15. Подтвердить оформление заказа
        rentalDataPages.clickConfirmOrderButton();
        // 16. Сравнение ОР (заказ принят) и ФР
        Assert.assertTrue("Сообщение о принятом заказе не отображается", rentalDataPages.isOrderAccept());
    }
}

