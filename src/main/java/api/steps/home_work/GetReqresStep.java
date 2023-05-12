package api.steps.home_work;

import api.models.UserResponse;
import api.steps.seminar.SpecHelper;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;

public class GetReqresStep {

    @Step("Отправить запрос GET https://reqres.in/api/users с параметром id = {0}")
    public static UserResponse getUserIdSuccess(String id){
        return given()
                .spec(SpecHelper.getRequestSpec())
                .when()
                .get(String.format("users/%s", id))
                .then()
                .spec(SpecHelper.getResponseSpec(200))
                .extract().body().jsonPath().getObject(".",UserResponse.class);

    }

    @Step("Проверка начала текста")
    public void checkText(UserResponse response){
        Assertions.assertTrue(response.getSupport().getText().startsWith("To keep ReqRes free"));
    }

    @Step("Отправить запрос GET https://reqres.in/api/users/2")
    public static UserResponse getUserIdUnsuccess(){
        return given()
                .spec(SpecHelper.getRequestSpec())
                .when()
                .get("users/2")
                .then()
                .spec(SpecHelper.getResponseSpec(200))
                .extract().body().jsonPath().getObject(".", UserResponse.class);

    }

    @Step("Проверка окончания доменного имени почты")
    public void checkEMail(UserResponse response){
        Assertions.assertTrue(response.getData().getEMail().contains("@mail.ru"));
    }

}
