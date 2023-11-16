package org.protei.login_test;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import org.protei.BaseTestPattern;
import org.protei.page_object_model.LoginPage;
import org.protei.page_object_model.MainAppPage;

public class SuccessLoginTest extends BaseTestPattern {
    private final String email = "test@protei.ru",
            password = "test";

    @Test
    @DisplayName("Тест успешной авторизации пользователя")
    @Description("После успешной авторизации осуществляется переход на главную страницу")
    public void testTest() {
        MainAppPage loginUser = new LoginPage(webDriver).loginUser(email, password);
        Assert.assertEquals("Добавить", loginUser.getAddButtonText());
    }
}
