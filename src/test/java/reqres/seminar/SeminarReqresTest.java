package reqres.seminar;

import api.models.CreateUserPayload;
import api.models.ListUsersResponse;
import api.models.Users;
import api.steps.seminar.ReqresSteps;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import static io.restassured.RestAssured.given;

public class SeminarReqresTest {

    private final static String URL = "https://reqres.in/api/";

    private final ReqresSteps reqresSteps = new ReqresSteps();

   // @Test
    // в строке аватар есть цифра равная айдишнику
    public void successCheckAvatar(){
        List<Users> users = given()
                .when() // когда
                .contentType(ContentType.JSON)
                .get(URL + "users?page=2") // сам метод
                .then()
                .log().all() //чтобы логировалось в терминал
                .extract().body().jsonPath().getList("data",Users.class); // извлекаем тело ответа; getList тк поле data это список

     //   System.out.println("");
        users.stream().forEach(x -> Assertions.assertTrue(x.getAvatar().contains(x.getId().toString())));
    }

  //  @Test
    // все почты заканчивааются на @reqres.in
    public void successCheckEMail(){
        ListUsersResponse response = given()
                .when() // когда
                .contentType(ContentType.JSON)
                .get(URL + "users?page=2") // сам метод
                .then()
                .log().all() //чтобы логировалось в терминал
                .extract().body().jsonPath().getObject(".",ListUsersResponse.class); // точка - из корневого каталога

        Assertions.assertTrue(response.getData().stream().allMatch(x -> x.getEMail().endsWith("@reqres.in"))); // allMatch возвраащает true or falce в зависимости от того, пройду ли все проверки
    }

  //  @Test
    // все почты заканчивааются на @reqres.in
    public void successCheckEMail2(){
        ListUsersResponse response = reqresSteps.getUsersSuccessful();
        reqresSteps.checkEmail(response);

    }

  //  @ParameterizedTest
  //  @ValueSource(strings = {"1", "2"})
    // все почты заканчивааются на @reqres.in с параметризацией
    public void successCheckEMail3(String page){
        ListUsersResponse response = reqresSteps.getUsersSuccessful(page);
        reqresSteps.checkEmail(response);

    }

  //  @Test
    // с POST первый с сайта
    public void successCreateUser(){
        CreateUserPayload payload = new CreateUserPayload("Klyukina V","student");
        Response response = reqresSteps.postUsersSuccess(payload);
        reqresSteps.checkCreateUser(response, payload); // в ответе то, что мы записали

    }


  //  @ParameterizedTest
  //  @CsvFileSource(resources = "/test-data/client-data.csv", numLinesToSkip = 1)
    // с CSV
    public void successCreateUser2(String name, String job){
        CreateUserPayload payload = new CreateUserPayload(name,job);
        Response response = reqresSteps.postUsersSuccess(payload);
        reqresSteps.checkCreateUser(response, payload);

    }

}
