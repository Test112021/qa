package pl.jsystems.qa.qaapi;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pl.jsystems.qa.qaapi.author.model.Author;
import pl.jsystems.qa.qaapi.author.model.AzureError;
import pl.jsystems.qa.qaapi.author.service.AuthorService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.truth.Truth.assertThat;
import static org.hamcrest.Matchers.*;

@Tag("Api")
public class ApiTest {

    AuthorService authorService;

    @BeforeEach
    public void setUp() {
        authorService = new AuthorService();
    }

    @Test
    public void firstApiTest() {
        RestAssured
                .given()
                .get("https://fakerestapi.azurewebsites.net/api/v1/Activities")
                .then()
                .assertThat()
                .statusCode(200)
                .body("[0].id", equalTo(1))
                .body("[0].title", equalTo("Activity 1"))
                .body("[0].dueDate", startsWith("2021-11"))
                .body("[0].completed", is(false));
    }

    @Test
    public void apiTest() {

        List<Author> author = RestAssured
                .given()
                .get("https://fakerestapi.azurewebsites.net/api/v1/Authors")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getList("", Author.class);

        assertThat(author.get(0).getId()).isEqualTo(1);
        assertThat(author.get(0).getIdBook()).isEqualTo(1);
    }


    @Test
    public void authorByIdTest() {

//        Author author = RestAssured
//                .given()
//                .get("https://fakerestapi.azurewebsites.net/api/v1/Authors/{id}", 1)
//                .then()
//                .assertThat()
//                .statusCode(200)
//                .extract()
//                .body()
//                .jsonPath()
//                .getObject("", Author.class);

        Response response = RestAssured
                .given()
                .get("https://fakerestapi.azurewebsites.net/api/v1/Authors/{id}", 1)
                .andReturn();


        Author author = response
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getObject("", Author.class);

        assertThat(response.getStatusCode()).isEqualTo(200);

        assertThat(author.getId()).isEqualTo(1);
        assertThat(author.getIdBook()).isEqualTo(1);
    }

    @Test
    public void authorById2Test() {

//        Response response = RestAssured
//                .given()
//                .get("https://fakerestapi.azurewebsites.net/api/v1/Authors/{id}", 1)
//                .andReturn();
//
//
//        Author author = response
//                .then()
//                .assertThat()
//                .statusCode(200)
//                .extract()
//                .body()
//                .jsonPath()
//                .getObject("", Author.class);


        final Response response = authorService.getAuthorByIdResponse(1);
        assertThat(response.getStatusCode()).isEqualTo(200);

        final Author author = authorService.getAuthorById(response);

        assertThat(author.getId()).isEqualTo(1);
        assertThat(author.getIdBook()).isEqualTo(1);
    }

    @Test
    public void authorsById2Test() {

        final Response response = authorService.getAuthorsResponse();
        assertThat(response.getStatusCode()).isEqualTo(200);

        final List<Author> author = authorService.getAuthors(response);

        assertThat(author.get(0).getId()).isEqualTo(1);
        assertThat(author.get(0).getIdBook()).isEqualTo(1);
    }

    @Test
    public void authorsByIdQueryTest() {

        Map<String, Object> params = new HashMap<>();
        params.put("name", "cos");
        params.put("status", true);

        final Response response = authorService.getAuthorsResponse(params);
        assertThat(response.getStatusCode()).isEqualTo(200);

        final List<Author> author = authorService.getAuthors(response);

        assertThat(author.get(0).getId()).isEqualTo(1);
        assertThat(author.get(0).getIdBook()).isEqualTo(1);
    }

    @Test
    public void postAuthorTest() {

        Author author = Author.builder()
                .idBook(200)
                .firstName("First Name")
                .lastName("Last Name")
                .build();

        final Response response = authorService.postAuthorResponse(author);
        System.out.println(response.getBody().prettyPrint());

        final Author createdAuthor = authorService.postAuthor(response);

        assertThat(createdAuthor.getId()).isEqualTo(0);
        assertThat(createdAuthor.getIdBook()).isEqualTo(200);
    }

    @Test
    public void postAuthorErrorTest() {

        final String authorJson = "{\"id\": \"S56\", \"idBook\": \"S56\", \"first_Name\": 256, \"last_Name\": 699}";

        final Response response = authorService.postAuthorResponse(authorJson);
        System.out.println(response.getBody().prettyPrint());

        final AzureError error = authorService.postAuthorError(response);
        assertThat(response.getStatusCode()).isEqualTo(400);
        assertThat(error.title).isEqualTo("One or more validation errors occurred.");
    }

    @Test
    public void deleteAuthorTest() {


        authorService.deleteAuthor(12);

        final List<Author> authors = authorService.getAuthors();
        //TODO sprawdzimy czy usuniety

    }

    @Test
    public void putAuthorTest() {

        Author author = Author.builder()
                .id(1)
                .idBook(200)
                .firstName("First Name A")
                .lastName("Last Name B")
                .build();

        final Author author1 = authorService.putAuthor(author, 1);

        assertThat(author1.getId()).isEqualTo(1);
        assertThat(author1.firstName).isEqualTo("First Name A");
    }

}
