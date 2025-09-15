package ru.netology.selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;

public class SelenideCardReservationTest {

    public String generateDate(int days, String pattern) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(pattern));

    }

    @Test
    public void shouldCompletedReservationCard () {

        String planningDate = generateDate(4,"dd.MM.yyyy");

        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Салехард");
        $("[data-test-id='date'] input").press(Keys.chord(Keys.SHIFT,Keys.HOME), Keys.DELETE)
                .setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Чернов Владимир");
        $("[data-test-id='phone'] input").setValue("+71230000000");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(Selectors.byText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        $(Selectors.byText("Встреча успешно забронирована на " + planningDate));
    }
}
