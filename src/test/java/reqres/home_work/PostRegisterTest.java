package reqres.home_work;

import api.models.RegisterUser;
import api.steps.home_work.PostReqresStep;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@Epic("API tests")
@Feature("Reqres service")
@Story("Методы для работы с пользователями")
@Link(name = "Документация сервиса", url = "https://reqres.in/")
public class PostRegisterTest {

    private final PostReqresStep postReqresStep = new PostReqresStep();

    @Test
    @Description("Регистрируем пользователя под передаваемымми в запросе email и password")
    @DisplayName("Успешная регистрация пользователя")
    @Severity(SeverityLevel.NORMAL)
    public void successfulRegUser(){
        RegisterUser newUser = new RegisterUser("eve.holt@reqres.in","pistol");
        Response response = postReqresStep.postRegisterUser(newUser);
        postReqresStep.check200(response);
    }

    @Test
    @Description("Регистрируем пользователя под передаваемымми в запросе email и password")
    @DisplayName("Неуспешная регистрация пользователя")
    @Severity(SeverityLevel.NORMAL)
    public void unsuccessfulRegUser(){
        RegisterUser newUser = new RegisterUser("eve.holt@reqres.in","pistol");
        Response response = postReqresStep.postRegisterUser(newUser);
        postReqresStep.checkToken(response);
    }


}
