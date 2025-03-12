package praktikum;

import org.hamcrest.MatcherAssert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import praktikum.pages.MainPage;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.fail;

@RunWith(Parameterized.class)
public class FAQTest {

    // Порядковый номер элемента аккордеона с вопросами
    private final int numberOfElement;
    //Текст ожидаемый в заголовке элемента аккордеона
    private final String expectedHeaderText;
    //Текст ожидаемый в раскрывающемся блоке элемента аккордеона с вопросами
    private final String expectedItemText;

    // Конструктор класса FAQTest
    public FAQTest(int numberOfAccordionItem, String expectedHeaderText, String expectedItemText) {
        this.numberOfElement = numberOfAccordionItem;
        this.expectedHeaderText = expectedHeaderText;
        this.expectedItemText = expectedItemText;
    }

    //Параметризация теста
    @Parameterized.Parameters(name = "Текст в блоке\"Вопросы о важном\". Проверяемый элемент: {1}")
    public static Object[][] setTestData() {
        return new Object[][] {
                {0, "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                // для данного заголовка тест будет падать, поскольку реализовано "Я жизу за МКАДом, привезёте?"
                {7, "Я живу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области." },
        };
    }

    @Rule
    public DriverRule factory = new DriverRule();

    @Test
    public void checkingAccordionWithQuestionsAnswers ()  {

        WebDriver driver = factory.getDriver();
        var mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.acceptCookies();
        mainPage.clickHeader(this.numberOfElement);
        mainPage.waitForLoadItem(this.numberOfElement);

        if (mainPage.isItemDisplayed(this.numberOfElement)) {
            MatcherAssert.assertThat("Problems with text in header #" + this.numberOfElement,
                    this.expectedHeaderText,
                    equalTo(mainPage.getHeaderText(this.numberOfElement))
            );
            MatcherAssert.assertThat("Problems with text in item #" + this.numberOfElement,
                    this.expectedItemText,
                    equalTo(mainPage.getItemText(this.numberOfElement))
            );
        }
        else {
            fail("Accordion header item #" + this.numberOfElement + "didn't load");
        }
    }
}
