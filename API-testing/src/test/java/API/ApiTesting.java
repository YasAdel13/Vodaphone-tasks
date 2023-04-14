package API;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiTesting {
    RequestSpecification request;

    @BeforeClass
    public void beforeClass(){
        request = given().baseUri("https://jsonplaceholder.typicode.com/");
    }
@Test
    public void getAllPosts(){
        given().spec(request)
                .when().get("posts")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("id",hasSize(100));
    }
@Test
    public void getPostById(){
        given().spec(request)
                .when().get("posts/1")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("id",is(equalTo(1)));
    }
@Test
    public void createAPost(){
    HashMap<String,String> body = new HashMap<>();
    body.put("userId","1");
    body.put("title","foo");
    body.put("body","bar");


    given().spec(request)
            .contentType(ContentType.JSON)
            .body(body)
            .when().post("posts")
            .then().log().all()
            .assertThat().statusCode(201)
            .assertThat().body("id",is(equalTo(101)));;


    }
}
