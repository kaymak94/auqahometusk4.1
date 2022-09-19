import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class OrderCardWithDeliveryTest {

    public static String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldReturnSuccessMsg() {
        String planningDate = generateDate(4);
        Configuration.holdBrowserOpen = true;
        $("[data-test-id='city'] input").val("Ростов-на-Дону");
        $x("//input[@placeholder='Дата встречи']").val(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE));
        $x("//input[@placeholder='Дата встречи']").val(generateDate(4));
        $x("//input[@name='name']").val("Кожин Павел");
        $x("//input[@name='phone']").val("+79612847267");
        $("[data-test-id='agreement']").click();
        $x("//span[@class='button__text']").click();
        $x("//*[contains(text(),'Успешно!')]").shouldBe(visible, Duration.ofSeconds(15));
        $("[class='notification__content']").shouldHave(Condition.text("Встреча успешно забронирована на " + generateDate(4))).shouldBe(visible, Duration.ofSeconds(15));


    }
}