package api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor // из lombok, конструирует экземпляр класса
@Getter
@ToString // для вывода в консоль
public class Users {

    private Integer id;

    @JsonProperty("email") //из библиотеки Jackson
    private String eMail;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;
    
    private String avatar;
}
