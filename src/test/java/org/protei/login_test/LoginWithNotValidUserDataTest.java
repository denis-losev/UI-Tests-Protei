package org.protei.login_test;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.protei.BaseTestPattern;
import org.protei.page_object_model.LoginPage;
import org.protei.page_object_model.MainAppPage;

@RunWith(Parameterized.class)
public class LoginWithNotValidUserDataTest extends BaseTestPattern {
    private final String email,
            password,
            notificationText;

    public LoginWithNotValidUserDataTest(String email, String password, String notificationText) {
        this.email = email;
        this.password = password;
        this.notificationText = notificationText;
    }

    @Parameterized.Parameters(name = "Email: {0}, password: {1}, notification: {2}")
    public static Object[][] getParameters() {
        return new Object[][]{
                {"test@protei.ru", "pas$wrd", "Неверный E-Mail или пароль"},
                {"mail@mail.mail", "test", "Неверный E-Mail или пароль"},
                {"mail@mail.mail", "", "Неверный E-Mail или пароль"},
                {"", "test", "Неверный формат E-Mail"},
                {"", "", "Неверный формат E-Mail"}
        };
    }

    @Test
    @DisplayName("Тест авторизации с невалидными данными")
    @Description("При вводе невалидных данных появляется соответствующая нотификация")
    public void loginWithNotValidUserDataTest() {
        MainAppPage loginUser = new LoginPage(webDriver).loginUser(email, password);
        Assert.assertEquals(notificationText, loginUser.getErrorNotificationText());
    }
}
