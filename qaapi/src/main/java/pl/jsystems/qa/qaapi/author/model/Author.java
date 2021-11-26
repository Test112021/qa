package pl.jsystems.qa.qaapi.author.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @JsonProperty(value = "id", required = true)
    public long id;
    @JsonProperty(value = "idBook", required = true)
    public long idBook;
    @JsonProperty(value = "firstName", required = true)
    public String firstName;
    @JsonProperty(value = "lastName", required = true)
    public String lastName;
}
