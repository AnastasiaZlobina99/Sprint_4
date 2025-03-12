package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.EnvConfig;

import java.time.Duration;

public class MainPage {
    //Веб драйвер
    private final WebDriver driver;
    //Локатор для кнопки "Принять cookies"
    private final By cookieButton = By.id("rcc-confirm-button");
    //Локатор для заголовка для раскрывающегося блока
    private final By accordionHeaders = By.className("accordion__heading");
    //Локатор для абзаца в раскрывающемся блоке
    private final By accordionItems = By.xpath(".//div[@class='accordion__panel']/p");
    // Локатор для кнопки "Заказать" в верхней части страницы
    private final By orderTopButton = By.className("Button_Button__ra12g");
    // Локатор для кнопки "Заказать" в нижней части страницы
    private final By orderBottomButton = By.className("Home_FinishButton__1_cWm");;

    //конструктор класса MainPage
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openMainPage() {
        driver.get(EnvConfig.BASE_URL);
    }

    //Кликаем по кнопке "Принять cookies"
    public MainPage acceptCookies() {
        waitForCookiesFloater();
        driver.findElement(cookieButton).click();
        waitForCookiesFloaterToDisappear();
        return this;
    }

    //Ждем пока кнопка "Принять cookies" появится
    private void waitForCookiesFloater() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.IMPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(cookieButton));
    }

    //Ждем пока всплывающее окно о куках исчезнет
    private void waitForCookiesFloaterToDisappear() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.IMPLICIT_WAIT))
                .until(ExpectedConditions.invisibilityOfElementLocated(cookieButton));
    }

    //Нажать на кнопку оформления заказа в шапке
    public void clickOrderTopButton() {
        driver.findElement(orderTopButton).click();
    }

    //Нажать на кнопку оформления заказа в теле сайта
    public void clickOrderBottomButton() {
        driver.findElement(orderBottomButton).click();
    }

    // Ждем загрузки элемента аккордеона
    public void waitForLoadItem(int index) {
        new WebDriverWait(this.driver, Duration.ofSeconds(EnvConfig.IMPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOf(driver.findElements(this.accordionItems).get(index)));
    }

    //Получение текста на заголовке блока в аккордеоне
    public String getHeaderText(int index) {
        return this.driver.findElements(this.accordionHeaders).get(index).getText();
    }

    //Получение текста из раскрывающегося блока в аккордеоне
    public String getItemText(int index) {
        return this.driver.findElements(this.accordionItems).get(index).getText();
    }

    //Нажатие на заголовок блока в аккордеоне
    public void clickHeader(int index) {
        this.driver.findElements(this.accordionHeaders).get(index).click();
    }

    //Проверка раскрытия блока аккордеона
    public boolean isItemDisplayed(int index) {
        return this.driver.findElements(this.accordionItems).get(index).isDisplayed();
    }
}

