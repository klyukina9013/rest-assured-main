package reqres.home_work;

import api.models.UserResponse;
import api.steps.home_work.GetReqresStep;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@Epic("API tests")
@Feature("Reqres service")
@Story("Методы для работы с пользователями")
@Link(name = "Документация сервиса", url = "https://reqres.in/")
public class GetUsersIdTest {

    private final GetReqresStep getReqresStep = new GetReqresStep();

    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"})
    @Description("Проверяем, что у каждого пользователя поле text начинается с To keep ReqRes free")
    @DisplayName("Проверка поля text")
    @Severity(SeverityLevel.NORMAL)
    public void successfulCheckText(String id){
        UserResponse response = getReqresStep.getUserIdSuccess(id);
        getReqresStep.checkText(response);

    }

    @Test
    @Description("Проверяем, что у каждого пользователя поле email заканчивается на  @mail.ru")
    @DisplayName("Проверка доменного имени почты")
    @Severity(SeverityLevel.NORMAL)
    public void unsuccessfulCheckEMail(){
        UserResponse response = getReqresStep.getUserIdUnsuccess();
        getReqresStep.checkEMail(response);

    }

}
