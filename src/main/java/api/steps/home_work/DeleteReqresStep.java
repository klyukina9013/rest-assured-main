package api.steps.home_work;

import api.models.UserResponse;
import api.steps.seminar.SpecHelper;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;

public class DeleteReqresStep {

    @Step("Отправить запрос DELETE https://reqres.in/api/users c параметром id = {0}")
    public static Response deleteUser(String id){
        return given()
                .spec(SpecHelper.getRequestSpec())
                .when()
                .delete(String.format("users/%s", id));
    }

    @Step("Проверка статуса кода запроса")
    public void check204(Response response) {
        int statusCode = response.statusCode();
        Assertions.assertEquals(statusCode, 204);
    }

    @Step("Проверка статуса кода запроса")
    public void checkStatusCode(Response response) {
        int statusCode = response.statusCode();
        Assertions.assertEquals(statusCode, 200);
    }
}
