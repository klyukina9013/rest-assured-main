package api.steps.home_work;

import api.models.CreateUserPayload;
import api.steps.seminar.SpecHelper;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;

public class PutReqresStep {

    @Step("Отправить запрос PUT https://reqres.in/api/users/2")
    public static Response putUpdateUser(CreateUserPayload payload){
        return given()
                .spec(SpecHelper.getRequestSpec())
                .when()
                .body(payload)
                .put("users/2")
                .then()
                .spec(SpecHelper.getResponseSpec(200))
                .extract()
                .response();

    }

    @Step("Проверка значения полей name и job")
    public void checkUpdateUser(Response response, CreateUserPayload payload) {
        Assertions.assertEquals(payload.getName(), response.jsonPath().get("name"));
        Assertions.assertEquals(payload.getJob(), response.jsonPath().get("job"));
    }

    @Step("Проверка значения полей name и job")
    public void checkUpdateUser2(Response response, CreateUserPayload payload) {
        Assertions.assertEquals(payload.getName(), response.jsonPath().get("name"));
        Assertions.assertEquals(payload.getJob(), response.jsonPath().get("jobs"));
    }

}
