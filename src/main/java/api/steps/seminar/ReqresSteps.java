package api.steps.seminar;

import api.models.CreateUserPayload;
import api.models.ListUsersResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;

public class ReqresSteps {

    public static ListUsersResponse getUsersSuccessful(){
        return given()
                .spec(SpecHelper.getRequestSpec())
                .when()
                .get("users?page=2")
                .then()
                .spec(SpecHelper.getResponseSpec(200))
                .extract().body().jsonPath().getObject(".",ListUsersResponse.class); // точка - из корневого каталога

    }

    //параметризация

    public static ListUsersResponse getUsersSuccessful(String page){
        return given()
                .spec(SpecHelper.getRequestSpec())
                .when()
                .get(String.format("users?page=%s", page))
                .then()
                .spec(SpecHelper.getResponseSpec(200))
                .extract().body().jsonPath().getObject(".",ListUsersResponse.class); // точка - из корневого каталога

    }

    // для POST
    public static Response postUsersSuccess(CreateUserPayload payload){
        return given()
                .spec(SpecHelper.getRequestSpec())
                .when()
                .body(payload)
                .post("users")
                .then()
                .spec(SpecHelper.getResponseSpec(201))
                .extract()
                .response();

    }

    public void checkEmail(ListUsersResponse response){
        Assertions.assertTrue(response.getData().stream().allMatch(x -> x.getEMail().endsWith("@reqres.in")));
    } // allMatch возвраащает true or falce в зависимости от того, пройду ли все проверки


    public void checkCreateUser(Response response, CreateUserPayload payload) {
        Assertions.assertEquals(payload.getName(), response.jsonPath().get("name"));
        Assertions.assertEquals(payload.getJob(), response.jsonPath().get("job"));
    }


}
