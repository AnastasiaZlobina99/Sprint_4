package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.EnvConfig;

import java.time.Duration;

public class RentScooterPage {
    //Веб драйвер
    private final WebDriver driver;
    // Локатор для  "Когда привезти самокат"
    private final By inputDate = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    //Локатор выбранной в календаре даты
    private final By dateSelected = By.className("react-datepicker__day--selected");
    // Локатор для выпадающего списка "Срок аренды"
    private final By rentTermDropdown = By.xpath(".//div[@class='Dropdown-control']");
    // Локатор для чекбокса "Черный жемчуг"
    private final By blackCheckbox = By.xpath("//label[@for='black']");
    // Локатор для чекбокса "Серая безысходность"
    private final By greyCheckbox = By.xpath("//label[@for='grey']");
    // Локатор для поля ввода комментария для курьера
    private final By courierCommentInput = By.xpath("//input[@placeholder='Комментарий для курьера']");
    // Локатор для кнопки "Заказать"
    private final By orderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    // Локатор для кнопки подтверждения заказа "Да"
    private final By yesButton = By.xpath(".//button[text()='Да' and @class='Button_Button__ra12g Button_Middle__1CSJM']");
    //Локатор для сообщения об успешном оформлении
    private final By successfully = By.xpath(".//*[text()='Заказ оформлен']");


    //конструктор класса RentScooterPage
    public RentScooterPage(WebDriver driver) {
        this.driver = driver;
    }

    //Нажатие на выбранную дату в календаре
    private void clickDateSelected() {
        driver.findElement(this.dateSelected).click();
    }

    //Установка значения в поле "Дата"
    public void setDate(String date) {
        driver.findElement(inputDate).sendKeys(date);
        this.clickDateSelected();
    }

    //Выбор срока аренды
    public void selectRentTerm(String rentTerm) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.IMPLICIT_WAIT));
        wait.until(ExpectedConditions.elementToBeClickable(rentTermDropdown)).click();
        String rentTermXpath = String.format("//div[@class='Dropdown-option' and text()='%s']", rentTerm);
        driver.findElement(By.xpath(rentTermXpath)).click();
    }

    // Выбора цвета самоката
    public void selectScooterColor(String color) {
        if  (color.equalsIgnoreCase("серая безысходность"))
        {
            driver.findElement(greyCheckbox).click();
        } else if (color.equalsIgnoreCase("чёрный жемчуг"))
        {
            driver.findElement(blackCheckbox).click();
        }
    }

    // Написать комментарий для курьера
    public void courierComment(String comment) {
        driver.findElement(courierCommentInput).sendKeys(comment);
    }

    // Нажать кнопку "Заказать"
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    // Нажать кнопку "Да" для подтверждения заказа
    public void clickConfirmOrderButton() {
        driver.findElement(yesButton).click();
    }

    // Метод для полного заполнения формы аренды
    public void fillRentForm(String day, String rentTerm, String color, String comment) {
        setDate(day);
        selectRentTerm(rentTerm);
        selectScooterColor(color);
        courierComment(comment);
        clickOrderButton();
        clickConfirmOrderButton();
    }

    // Подтверждение заказа отображается на странице
    public boolean isOrderConfirmationDisplayed() {
        driver.findElement(successfully);
        return true;
    }
}
