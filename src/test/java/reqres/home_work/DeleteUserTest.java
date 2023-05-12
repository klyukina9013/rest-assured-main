package reqres.home_work;

import api.models.UserResponse;
import api.steps.home_work.DeleteReqresStep;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@Epic("API tests")
@Feature("Reqres service")
@Story("Методы для работы с пользователями")
@Link(name = "Документация сервиса", url = "https://reqres.in/")
public class DeleteUserTest {

    private final DeleteReqresStep deleteReqresStep = new DeleteReqresStep();

    @ParameterizedTest
    @ValueSource(strings = {"1", "2"})
    @Description("Удаляем пользователей с id, передаваемыми в методе")
    @DisplayName("Успешное удаление пользователя")
    @Severity(SeverityLevel.NORMAL)
    public void successfulDelUser(String id){
        Response response = deleteReqresStep.deleteUser(id);
        deleteReqresStep.check204(response);

    }


    @ParameterizedTest
    @ValueSource(strings = {"31", "202"})
    @Description("Неуспешное удаление пользователя")
    @Severity(SeverityLevel.NORMAL)
    public void unsuccessfulDelUser(String id){
        Response response = deleteReqresStep.deleteUser(id);
        deleteReqresStep.checkStatusCode(response);

    }
}
