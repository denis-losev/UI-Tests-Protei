package org.protei.main_page_test;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import org.protei.BaseTestPattern;
import org.protei.page_object_model.LoginPage;
import org.protei.page_object_model.MainAppPage;
import org.protei.page_object_model.ResultModal;

public class ValidationTest extends BaseTestPattern {
    private final String loginMail = "test@protei.ru",
            password = "test";

    @Test
    @DisplayName("Тест валидации поля E-Mail")
    @Description("При отправке формы с пустым значением в поле E-Mail появляется ошибка")
    public void emptyEmailFieldTest() {
        MainAppPage loginUser = new LoginPage(webDriver).loginUser(loginMail, password);
        ResultModal dataInput = loginUser.fillRequireFields("", "Denis");
        Assert.assertEquals("Неверный формат E-Mail", dataInput.getErrorNotificationText());
    }

    @Test
    @DisplayName("Тест валидации поля E-Mail")
    @Description("При отправке формы с некорректным значением в поле E-Mail появляется ошибка")
    public void incorrectEmailFieldTest() {
        MainAppPage loginUser = new LoginPage(webDriver).loginUser(loginMail, password);
        ResultModal dataInput = loginUser.fillRequireFields("mail.com", "Denis");
        Assert.assertEquals("Неверный формат E-Mail", dataInput.getErrorNotificationText());
    }

    @Test
    @DisplayName("Тест валидации поля Name")
    @Description("При отправке формы с пустым значением в поле Name появляется ошибка")
    public void emptyNameFieldTest() {
        MainAppPage loginUser = new LoginPage(webDriver).loginUser(loginMail, password);
        ResultModal dataInput = loginUser.fillRequireFields("mail@mail.ru", "");
        Assert.assertEquals("Поле имя не может быть пустым", dataInput.getErrorNotificationText());
    }
}
