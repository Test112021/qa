package pl.jsystems.qa.qaapi.author.service;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pl.jsystems.qa.qaapi.author.model.Author;
import pl.jsystems.qa.qaapi.author.model.AzureError;
import pl.jsystems.qa.qaapi.spec.AzureSpecification;

import java.util.List;
import java.util.Map;

public class AuthorService {

    private static final String AUTHORS = "/Authors";
    private static final String AUTHORS_BY_ID = "/Authors/{id}";

    public Author getAuthorById(int id) {
        return getAuthorByIdResponse(id)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getObject("", Author.class);
    }


    public Author getAuthorById(Response response) {
        return response
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getObject("", Author.class);
    }

    public Response getAuthorByIdResponse(int id) {
        return RestAssured
                .given()
                .spec(AzureSpecification.azureSpec())
//                .get("https://fakerestapi.azurewebsites.net//Authors/{id}", id)
                .get(AUTHORS_BY_ID, id)
                .andReturn();
    }

    public List<Author> getAuthors() {
        return getAuthorsResponse()
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getList("", Author.class);
    }

    public List<Author> getAuthors(Map<String, ?> params) {
        return getAuthorsResponse(params)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getList("", Author.class);
    }


    public List<Author> getAuthors(Response response) {
        return response
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getList("", Author.class);
    }

    public Response getAuthorsResponse() {
        return RestAssured
                .given()
                .spec(AzureSpecification.azureSpec())
//                        .get("https://fakerestapi.azurewebsites.net/api/v1/Authors")
                .get(AUTHORS)
                .andReturn();
    }

    public Response getAuthorsResponse(Map<String, ?> params) {
        return RestAssured
                .given()
                .spec(AzureSpecification.azureSpec())
//                        .get("https://fakerestapi.azurewebsites.net/api/v1/Authors")
//                .queryParam("name", "cos")
//                .queryParam("status", "true")
                .queryParams(params)
                .get(AUTHORS)
                .andReturn();
    }

    public Author postAuthor(Author author) {
        return postAuthor(postAuthorResponse(author));
    }


    public Author postAuthor(Response response) {
        return response
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getObject("", Author.class);
    }

    public Response postAuthorResponse(Object author) {
        return RestAssured
                .given()
                .spec(AzureSpecification.azureSpec())
                .body(author)
                .post(AUTHORS)
                .andReturn();
    }

    public AzureError postAuthorError(Response response) {
        return response
                .then()
                .assertThat()
                .extract()
                .body()
                .jsonPath()
                .getObject("", AzureError.class);
    }

    public void deleteAuthor(int id) {
        deleteAuthor(deleteAuthorResponse(id));
    }


    public void deleteAuthor(Response response) {
        response
                .then()
                .assertThat()
                .statusCode(200);
    }

    public Response deleteAuthorResponse(int id) {
        return RestAssured
                .given()
                .spec(AzureSpecification.azureSpec())
                .delete(AUTHORS_BY_ID, id)
                .andReturn();
    }

    public Author putAuthor(Author author, int id) {
        return putAuthor(putAuthorResponse(author, id));
    }


    public Author putAuthor(Response response) {
        return response
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getObject("", Author.class);
    }

    public Response putAuthorResponse(Object author, int id) {
        return RestAssured
                .given()
                .spec(AzureSpecification.azureSpec())
                .body(author)
                .put(AUTHORS_BY_ID, id)
                .andReturn();
    }



}
