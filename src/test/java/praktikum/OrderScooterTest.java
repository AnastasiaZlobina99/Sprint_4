package praktikum;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import praktikum.pages.MainPage;
import praktikum.pages.OrderScooterPage;
import praktikum.pages.RentScooterPage;
//В Google Chrome данные тесты будут падать
@RunWith(Parameterized.class)
public class OrderScooterTest {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phoneNumber;
    private final String deliveryDay;
    private final String rentTerm;
    private final String color;
    private final String comment;

    public OrderScooterTest(String firstName, String lastName, String address, String metroStation, String phoneNumber,
                            String deliveryDay, String rentTerm, String color, String comment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.deliveryDay = deliveryDay;
        this.rentTerm = rentTerm;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] setTestData() {
        return new Object[][]{
                {"Иван", "Иванов", "Италия", "Комсомольская", "89337374455", "22.04.2025", "двое суток", "серая безысходность", "Мы очень ждем! Быстрее, пожалуйста"},
                {"Петр", "Петров", "Россия", "Технопарк", "89123335566", "17.04.2025", "сутки", "чёрный жемчуг", "Арендую самокат для друга, ждем вас"},
        };
    }
    @Rule
    public DriverRule factory = new DriverRule();

    @Test //тест для нижней кнопки
    public void checkingOrderScooterBottomButton() {

        WebDriver driver = factory.getDriver();
        var mainPage = new MainPage(driver);
        var orderScooterPage = new OrderScooterPage(driver);
        var rentScooterPage = new RentScooterPage(driver);
        mainPage.openMainPage();
        mainPage.acceptCookies();
        mainPage.clickOrderBottomButton();
        orderScooterPage.waitForLoadForm();
        orderScooterPage.fillOrderForm(firstName, lastName, address, metroStation, phoneNumber);
        rentScooterPage.fillRentForm(deliveryDay, rentTerm, color, comment);
        Assert.assertTrue(rentScooterPage.isOrderConfirmationDisplayed());
    }

    @Test //тест для верхней кнопки
    public void checkingOrderScooterTopButton() {

        WebDriver driver = factory.getDriver();
        var mainPage = new MainPage(driver);
        var orderScooterPage = new OrderScooterPage(driver);
        var rentScooterPage = new RentScooterPage(driver);
        mainPage.openMainPage();
        mainPage.acceptCookies();
        mainPage.clickOrderTopButton();
        orderScooterPage.waitForLoadForm();
        orderScooterPage.fillOrderForm(firstName, lastName, address, metroStation, phoneNumber);
        rentScooterPage.fillRentForm(deliveryDay, rentTerm, color, comment);
        Assert.assertTrue(rentScooterPage.isOrderConfirmationDisplayed());
    }
}
