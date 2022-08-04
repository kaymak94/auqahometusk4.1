import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class OrderCardWithDeliveryTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    void ShouldReturnSuccessMsg() {
        Configuration.holdBrowserOpen = true;
        $("[data-test-id='city'] input").val("Ростов-на-Дону");
        $x("//input[@placeholder='Дата встречи']").val(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE));
        $x("//input[@placeholder='Дата встречи']").val("08.08.2022");
        $x("//input[@name='name']").val("Кожин Павел");
        $x("//input[@name='phone']").val("+79612847267");
        $("[data-test-id='agreement']").click();
        $x("//span[@class='button__text']").click();
        $x("//*[contains(text(),'Успешно!')]").shouldBe(visible, Duration.ofSeconds(15));
        $("[class='notification__content']").shouldHave(Condition.text("Встреча успешно забронирована на " + " 08.08.2022")).shouldBe(visible, Duration.ofSeconds(15));


    }

}