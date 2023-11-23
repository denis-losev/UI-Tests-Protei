package org.protei.main_page_test;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.protei.BaseTestPattern;
import org.protei.page_object_model.LoginPage;
import org.protei.page_object_model.MainAppPage;
import org.protei.page_object_model.ResultModal;

@RunWith(Parameterized.class)
public class AddDataTest extends BaseTestPattern {
    private final String loginMail = "test@protei.ru",
            password = "test";
    private final String email,
            name,
            gender;
    private final int checkBoxOptions,
            radioSelect;
    private final String checkBoxText,
            radioSelectText;

    public AddDataTest(String email, String name, String gender, int checkBoxOptions, String checkBoxText, int radioSelect, String radioSelectText) {
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.checkBoxOptions = checkBoxOptions;
        this.checkBoxText = checkBoxText;
        this.radioSelect = radioSelect;
        this.radioSelectText = radioSelectText;
    }

    @Parameterized.Parameters(name = "Email: {0}, name: {1}, gender: {2}, checkbox: {3}, radio: {4}")
    public static Object[][] getParameters() {
        return new Object[][]{
                {"mail01@mail.ru", "Alex", "Мужской", 2, "1.2", 1, "2.1"},
                {"mail02@mail.ru", "Anna", "Женский", 1, "1.1", 3, "2.3"},
                {"mail03@mail.ru", "Stan", "Мужской", 12, "1.1, 1.2", 2, "2.2"},
                {"mail04@mail.ru", "Maria", "Женский", 0, "Нет", 0, ""},
                {"mail05@mail.ru", "Jane", "Женский", 0, "Нет", 1, "2.1"},
                {"mail06@mail.ru", "Sergey", "Мужской", 2, "1.2", 0, ""}
        };
    }

    @Test
    @DisplayName("Тест успешного добавления данных пользователя")
    @Description("Данные добавляются в таблицу")
    public void addDataTest() {
        MainAppPage loginUser = new LoginPage(webDriver).loginUser(loginMail, password);
        ResultModal addData = loginUser.addUserData(email, name, gender, checkBoxOptions, radioSelect);
        MainAppPage result = addData.closeModal();
        Assert.assertEquals(email, result.getTableData(1));
        Assert.assertEquals(name, result.getTableData(2));
        Assert.assertEquals(gender, result.getTableData(3));
        Assert.assertEquals(checkBoxText, result.getTableData(4));
        Assert.assertEquals(radioSelectText, result.getTableData(5));
    }
}
