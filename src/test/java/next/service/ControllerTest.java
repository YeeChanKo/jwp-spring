package next.service;

import static io.restassured.RestAssured.given;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ControllerTest {
	private static final Logger log = LoggerFactory
			.getLogger(ControllerTest.class);

	@Before
	public void setup() {
		RestAssured.port = 8080; // default가 8080
		// RestAssured 안에 보면 디폴트 값 다 나와 있음
	}

	@Test
	public void home() {
		String body = given().contentType(ContentType.HTML).when().get("/")
				.then().statusCode(HttpStatus.OK.value()).extract().asString();
		log.debug("body : {}", body);
	}

	@Test
	public void askQuestion() {
		given().auth().preemptive().basic("admin", "password")
				.contentType(ContentType.HTML).when().get("/questions/new")
				.then().statusCode(HttpStatus.OK.value());
	}

	@Test
	public void postQuestion() {
		given().auth().preemptive().basic("admin", "password")
				.queryParam("title", "blabla title")
				.queryParam("contents", "blabla contents")
				.contentType(ContentType.URLENC).when().post("/questions")
				.then().statusCode(HttpStatus.FOUND.value());
	}
}
