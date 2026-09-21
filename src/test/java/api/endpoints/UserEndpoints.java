package api.endpoints;
import io.restassured.*;
import io.restassured.http.ContentType;
import io.restassured.response.*;

import static io.restassured.RestAssured.*;

import api.payload.User;


public class UserEndpoints {

	public static Response CreateUser(User payload)
	{
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)

				.when()
				.post(Routes.post_url);

		return response;

	}

	public static Response readUser(String UN)
	{
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", UN)

				.when()
				.get(Routes.get_url);

		return response;

	}

	public static Response updateUser(String UN,User payload)
	{
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", UN)
				.body(payload)

				.when()
				.put(Routes.put_url);

		return response;

	}
	public static Response deleteUser(String UN)
	{
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", UN)

				.when()
				.delete(Routes.delete_url);

		return response;

	}


}
