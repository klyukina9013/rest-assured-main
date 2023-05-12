package api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class CreateUserPayload {
    private String name;
    private String job;

}
