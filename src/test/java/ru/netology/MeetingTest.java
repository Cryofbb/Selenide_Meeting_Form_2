package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class MeetingTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.CONTROL + "a", Keys.DELETE));
    }

    @Test
    void shouldSubmitRequest() {
        DataGenerator.User user = DataGenerator.Registration.userReg("ru");
        $("[data-test-id='city']").$("[placeholder='Город']").setValue(user.getCity());
        $("[data-test-id='date'] .input__control").setValue(DataGenerator.dataGen(3));
        $("[data-test-id= 'name']").$("[name ='name']").setValue(user.getName());
        $("[data-test-id='phone']").$("[name='phone']").setValue(user.getPhone());
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=success-notification] .notification__content").shouldBe(visible, Duration.ofSeconds(15)).shouldBe(exactText("Встреча успешно запланирована на " + DataGenerator.dataGen(3)));
    }

    @Test
    void shouldNotSubmitIfLess3Days() {
        DataGenerator.User user = DataGenerator.Registration.userReg("ru");
        $("[data-test-id='city']").$("[placeholder='Город']").setValue(user.getCity());
        $("[data-test-id='date'] .input__control").setValue(DataGenerator.dataGen(1));
        $("[data-test-id= 'name']").$("[name ='name']").setValue(user.getName());
        $("[data-test-id='phone']").$("[name='phone']").setValue(user.getPhone());
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=date] .input_invalid .input__sub").shouldBe(visible).shouldBe(exactText("Заказ на выбранную дату невозможен"));
    }
    @Test
    void shouldNotSubmitToday() {
        DataGenerator.User user = DataGenerator.Registration.userReg("ru");
        $("[data-test-id='city']").$("[placeholder='Город']").setValue(user.getCity());
        $("[data-test-id='date'] .input__control").setValue(DataGenerator.dataGen(0));
        $("[data-test-id= 'name']").$("[name ='name']").setValue(user.getName());
        $("[data-test-id='phone']").$("[name='phone']").setValue(user.getPhone());
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=date] .input_invalid .input__sub").shouldBe(visible).shouldBe(exactText("Заказ на выбранную дату невозможен"));
    }
    @Test
    void shouldNotSubmitMinusDay() {
        DataGenerator.User user = DataGenerator.Registration.userReg("ru");
        $("[data-test-id='city']").$("[placeholder='Город']").setValue(user.getCity());
        $("[data-test-id='date'] .input__control").setValue(DataGenerator.dataGen(-1));
        $("[data-test-id= 'name']").$("[name ='name']").setValue(user.getName());
        $("[data-test-id='phone']").$("[name='phone']").setValue(user.getPhone());
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=date] .input_invalid .input__sub").shouldBe(visible).shouldBe(exactText("Заказ на выбранную дату невозможен"));
    }
    @Test
    void shouldNotSubmitIfEmptyDate() {
        DataGenerator.User user = DataGenerator.Registration.userReg("ru");
        $("[data-test-id='city']").$("[placeholder='Город']").setValue(user.getCity());
        $("[data-test-id= 'name']").$("[name ='name']").setValue(user.getName());
        $("[data-test-id='phone']").$("[name='phone']").setValue(user.getPhone());
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=date] .input_invalid .input__sub").shouldBe(visible).shouldBe(exactText("Неверно введена дата"));
    }
    @Test
    void shouldSubmit5Days() {
        DataGenerator.User user = DataGenerator.Registration.userReg("ru");
        $("[data-test-id='city']").$("[placeholder='Город']").setValue(user.getCity());
        $("[data-test-id='date'] .input__control").setValue(DataGenerator.dataGen(5));
        $("[data-test-id= 'name']").$("[name ='name']").setValue(user.getName());
        $("[data-test-id='phone']").$("[name='phone']").setValue(user.getPhone());
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=success-notification] .notification__content").shouldBe(visible, Duration.ofSeconds(15)).shouldBe(exactText("Встреча успешно запланирована на " + DataGenerator.dataGen(5)));
    }

    @Test
    void shouldNotSubmitWithEmptyCity() {
        DataGenerator.User user = DataGenerator.Registration.userReg("ru");
        $("[data-test-id='city']").$("[placeholder='Город']").setValue("");
        $("[data-test-id='date'] .input__control").setValue(DataGenerator.dataGen(3));
        $("[data-test-id= 'name']").$("[name ='name']").setValue(user.getName());
        $("[data-test-id='phone']").$("[name='phone']").setValue(user.getPhone());
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=city].input_invalid .input__sub").shouldBe(visible).shouldBe(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotSubmitWithEmptyName() {
        DataGenerator.User user = DataGenerator.Registration.userReg("ru");
        $("[data-test-id='city']").$("[placeholder='Город']").setValue(user.getCity());
        $("[data-test-id='date'] .input__control").setValue(DataGenerator.dataGen(3));
        $("[data-test-id= 'name']").$("[name ='name']").setValue("");
        $("[data-test-id='phone']").$("[name='phone']").setValue(user.getPhone());
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=name].input_invalid .input__sub").shouldBe(visible).shouldBe(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotSubmitWithEmptyPhone() {
        DataGenerator.User user = DataGenerator.Registration.userReg("ru");
        $("[data-test-id='city']").$("[placeholder='Город']").setValue(user.getCity());
        $("[data-test-id='date'] .input__control").setValue(DataGenerator.dataGen(3));
        $("[data-test-id= 'name']").$("[name ='name']").setValue(user.getName());
        $("[data-test-id='phone']").$("[name='phone']").setValue("");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldBe(visible).shouldBe(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotSubmitWithEmptyAgreement() {
        DataGenerator.User user = DataGenerator.Registration.userReg("ru");
        $("[data-test-id='city']").$("[placeholder='Город']").setValue(user.getCity());
        $("[data-test-id='date'] .input__control").setValue(DataGenerator.dataGen(3));
        $("[data-test-id= 'name']").$("[name ='name']").setValue(user.getName());
        $("[data-test-id='phone']").$("[name='phone']").setValue(user.getPhone());
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=agreement].input_invalid .checkbox__text").shouldBe(visible).shouldBe(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }

    @Test
    void shouldNotSubmitWithEnglishName() {
        DataGenerator.User user = DataGenerator.Registration.userReg("ru");
        $("[data-test-id='city']").$("[placeholder='Город']").setValue(user.getCity());
        $("[data-test-id='date'] .input__control").setValue(DataGenerator.dataGen(3));
        $("[data-test-id= 'name']").$("[name ='name']").setValue(DataGenerator.nameGenUS());
        $("[data-test-id='phone']").$("[name='phone']").setValue(user.getPhone());
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=name].input_invalid .input__sub").shouldBe(visible).shouldBe(exactText("Имя и Фамилия указаны неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotSubmitWithSymbolName() {
        DataGenerator.User user = DataGenerator.Registration.userReg("ru");
        $("[data-test-id='city']").$("[placeholder='Город']").setValue(user.getCity());
        $("[data-test-id='date'] .input__control").setValue(DataGenerator.dataGen(3));
        $("[data-test-id= 'name']").$("[name ='name']").setValue("%Имя%");
        $("[data-test-id='phone']").$("[name='phone']").setValue(user.getPhone());
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=name].input_invalid .input__sub").shouldBe(visible).shouldBe(exactText("Имя и Фамилия указаны неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotSubmitWith10Phone() {
        DataGenerator.User user = DataGenerator.Registration.userReg("ru");
        $("[data-test-id='city']").$("[placeholder='Город']").setValue(user.getCity());
        $("[data-test-id='date'] .input__control").setValue(DataGenerator.dataGen(3));
        $("[data-test-id= 'name']").$("[name ='name']").setValue(user.getName());
        $("[data-test-id='phone']").$("[name='phone']").setValue("+7012345678");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldBe(visible).shouldBe(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotSubmitWrongCity() {
        DataGenerator.User user = DataGenerator.Registration.userReg("ru");
        $("[data-test-id='city']").$("[placeholder='Город']").setValue("Мос");
        $("[data-test-id='date'] .input__control").setValue(DataGenerator.dataGen(3));
        $("[data-test-id= 'name']").$("[name ='name']").setValue(user.getName());
        $("[data-test-id='phone']").$("[name='phone']").setValue(user.getPhone());
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=city].input_invalid .input__sub").shouldBe(visible).shouldBe(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldNotSubmitEnglishCity() {
        DataGenerator.User user = DataGenerator.Registration.userReg("ru");
        $("[data-test-id='city']").$("[placeholder='Город']").setValue(DataGenerator.cityGenUS());
        $("[data-test-id='date'] .input__control").setValue(DataGenerator.dataGen(3));
        $("[data-test-id= 'name']").$("[name ='name']").setValue(user.getName());
        $("[data-test-id='phone']").$("[name='phone']").setValue(user.getPhone());
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=city].input_invalid .input__sub").shouldBe(visible).shouldBe(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldNotSubmitForeignCity() {
        DataGenerator.User user = DataGenerator.Registration.userReg("ru");
        $("[data-test-id='city']").$("[placeholder='Город']").setValue(DataGenerator.cityGenUA());
        $("[data-test-id='date'] .input__control").setValue(DataGenerator.dataGen(3));
        $("[data-test-id= 'name']").$("[name ='name']").setValue(user.getName());
        $("[data-test-id='phone']").$("[name='phone']").setValue(user.getPhone());
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=city].input_invalid .input__sub").shouldBe(visible).shouldBe(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldNotSubmitSymbolCity() {
        DataGenerator.User user = DataGenerator.Registration.userReg("ru");
        $("[data-test-id='city']").$("[placeholder='Город']").setValue("Мос%ква");
        $("[data-test-id='date'] .input__control").setValue(DataGenerator.dataGen(3));
        $("[data-test-id= 'name']").$("[name ='name']").setValue(user.getName());
        $("[data-test-id='phone']").$("[name='phone']").setValue(user.getPhone());
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=city].input_invalid .input__sub").shouldBe(visible).shouldBe(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldNotSubmitWithSingleName() {
        DataGenerator.User user = DataGenerator.Registration.userReg("ru");
        $("[data-test-id='city']").$("[placeholder='Город']").setValue(user.getCity());
        $("[data-test-id='date'] .input__control").setValue(DataGenerator.dataGen(3));
        $("[data-test-id= 'name']").$("[name ='name']").setValue(DataGenerator.nameGenSingle("ru"));
        $("[data-test-id='phone']").$("[name='phone']").setValue(user.getPhone());
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=name].input_invalid .input__sub").shouldBe(visible).shouldBe(exactText("Имя и Фамилия указаны неверно. Проверьте, что введённые данные совпадают с паспортными."));
    }
    @Test
    void shouldSubmitRequestWithDoubleName() {
        DataGenerator.User user = DataGenerator.Registration.userReg("ru");
        $("[data-test-id='city']").$("[placeholder='Город']").setValue(user.getCity());
        $("[data-test-id='date'] .input__control").setValue(DataGenerator.dataGen(3));
        $("[data-test-id= 'name']").$("[name ='name']").setValue("Имя-имя Фамилия");
        $("[data-test-id='phone']").$("[name='phone']").setValue(user.getPhone());
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=success-notification] .notification__content").shouldBe(visible, Duration.ofSeconds(15)).shouldBe(exactText("Встреча успешно запланирована на " + DataGenerator.dataGen(3)));
    }
    @Test
    void shouldReplan() {
        DataGenerator.User user = DataGenerator.Registration.userReg("ru");
        $("[data-test-id='city']").$("[placeholder='Город']").setValue(user.getCity());
        $("[data-test-id='date'] .input__control").setValue(DataGenerator.dataGen(3));
        $("[data-test-id= 'name']").$("[name ='name']").setValue(user.getName());
        $("[data-test-id='phone']").$("[name='phone']").setValue(user.getPhone());
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=success-notification] .notification__content").shouldBe(visible, Duration.ofSeconds(15)).shouldBe(exactText("Встреча успешно запланирована на " + DataGenerator.dataGen(3)));
        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.CONTROL + "a", Keys.DELETE));
        $("[data-test-id='date'] .input__control").setValue(DataGenerator.dataGen(5));
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=replan-notification] .notification__title").shouldBe(visible).shouldBe(exactText("Необходимо подтверждение"));
        $$("button").find(exactText("Перепланировать")).click();
        $("[data-test-id=success-notification] .notification__content").shouldBe(visible, Duration.ofSeconds(15)).shouldBe(exactText("Встреча успешно запланирована на " + DataGenerator.dataGen(5)));
    }

    @Test
    void shouldSubmitWithYoLetter() {
        DataGenerator.User user = DataGenerator.Registration.userReg("ru");
        $("[data-test-id='city']").$("[placeholder='Город']").setValue(user.getCity());
        $("[data-test-id='date'] .input__control").setValue(DataGenerator.dataGen(3));
        $("[data-test-id= 'name']").$("[name ='name']").setValue("Ёжи Петруччо");
        $("[data-test-id='phone']").$("[name='phone']").setValue(user.getPhone());
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=success-notification] .notification__content").shouldBe(visible, Duration.ofSeconds(15)).shouldBe(exactText("Встреча успешно запланирована на " + DataGenerator.dataGen(3)));
    }
}




