package reqres.home_work;

import api.models.CreateUserPayload;
import api.steps.home_work.PutReqresStep;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@Epic("API tests")
@Feature("Reqres service")
@Story("Методы для работы с пользователями")
@Link(name = "Документация сервиса", url = "https://reqres.in/")
public class PutUpdateTest {

    private final PutReqresStep putReqresStep = new PutReqresStep();

    @Test
    @Description("Обновляем поля name и job у пользователя")
    @DisplayName("Успешное обновление полей name и job у пользователя")
    @Severity(SeverityLevel.NORMAL)
    public void successfulUpdateUser(){
        CreateUserPayload payload = new CreateUserPayload("morpheus","zion resident");
        Response response = putReqresStep.putUpdateUser(payload);
        putReqresStep.checkUpdateUser(response, payload);

    }

    @Test
    @Description("Обновляем поля name и job у пользователя")
    @DisplayName("Неуспешное обновление полей name и job у пользователя")
    @Severity(SeverityLevel.NORMAL)
    public void unsuccessfulUpdateUser(){
        CreateUserPayload payload = new CreateUserPayload("morpheus","zion resident");
        Response response = putReqresStep.putUpdateUser(payload);
        putReqresStep.checkUpdateUser2(response, payload);

    }



}
