package api.steps.home_work;

import api.models.RegisterUser;
import api.steps.seminar.SpecHelper;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;

public class PostReqresStep {

    @Step("Отправить запрос POST https://reqres.in/api/register")
    public static Response postRegisterUser(RegisterUser newUser){
        return given()
                .spec(SpecHelper.getRequestSpec())
                .when()
                .body(newUser)
                .post("register")
                .then()
                .spec(SpecHelper.getResponseSpec(200))
                .extract()
                .response();
    }
    @Step("Проверка статуса кода запроса")
    public void check200(Response response) {
        int statusCode = response.statusCode();
        Assertions.assertEquals(statusCode, 200);
    }

    @Step("Проверка значения token")
    public void checkToken(Response response) {
        Assertions.assertEquals(response.jsonPath().getString("token"),"QpwL5tke4Pnpja7X");
    }


}
