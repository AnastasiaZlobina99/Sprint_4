package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.EnvConfig;
import java.time.Duration;
import java.util.List;

public class OrderScooterPage {
    //Веб драйвер
    private final WebDriver driver;
    //Локатор формы заказа
    private final By orderForm = By.xpath(".//div[starts-with(@class, 'Order_Form')]");
    // Локатор поля для ввода имени
    private final By inputFirstName = By.xpath("//input[@placeholder='* Имя']");
    // Локатор поля для ввода фамилии
    private final By inputLastName = By.xpath("//input[@placeholder='* Фамилия']");
    // Локатор поля для ввода адреса доставки
    private final By inputAddress = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    // Локатор поля для выбора станции метро
    private final By metroStation = By.xpath("//input[@placeholder='* Станция метро']");
    // Локатор списка станций метро
    private final By metroStationList = By.xpath("//div[@class='select-search__select']");
    //Локатор станции
    private final By selectStation = By.xpath("//div[@class='select-search__select']//div[@class='Order_Text__2broi']");
    // Локатор поля для ввода телефона
    private final By inputPhone = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    // Локатор кнопки "Далее"
    private final By nextButton = By.xpath("//button[text()='Далее']");

    //конструктор класса OrderScooterPage
    public OrderScooterPage(WebDriver driver) {
        this.driver = driver;
    }

    // Ожидание загрузки формы заказа
    public void waitForLoadForm() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.IMPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOf(driver.findElement(orderForm)));
    }

    //Заполнение поля Имя
    public void setFirstName(String firstName) {
        driver.findElement(inputFirstName).sendKeys(firstName);
    }

    //Заполнение поля Фамилия
    public void setLastName(String lastName) {
        driver.findElement(inputLastName).sendKeys(lastName);
    }

    //Заполнение поля Адрес
    public void setAddress(String address) {
        driver.findElement(inputAddress).sendKeys(address);
    }

    // Выбор станции метро
    public void selectMetroStation(String stationName) {
        driver.findElement(metroStation).click();
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.IMPLICIT_WAIT)).until(ExpectedConditions.visibilityOfElementLocated(metroStationList));
        List<WebElement> stations = driver.findElements(selectStation);
        for (WebElement station : stations) {
            if (station.getText().equals(stationName)) {
                station.click();
                break;
            }
        }
    }

    //Заполнение поля Телефон
    public void setPhone(String phone) {
        driver.findElement(inputPhone).sendKeys(phone);
    }

    // Нажимаем кнопку Далее
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    // Заполнение формы заказа
    public void fillOrderForm(String firstName, String lastName, String address, String metroStation, String phone) {
        setFirstName(firstName);
        setLastName(lastName);
        setAddress(address);
        selectMetroStation(metroStation);
        setPhone(phone);
        clickNextButton();
    }
}
