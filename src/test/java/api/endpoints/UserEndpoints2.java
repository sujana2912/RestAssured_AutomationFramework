package api.endpoints;
import io.restassured.*;
import io.restassured.http.ContentType;
import io.restassured.response.*;

import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payload.User;


public class UserEndpoints2 {
	
	//method created getting urls from properties file
	static ResourceBundle getURL()
	{
		ResourceBundle routes = ResourceBundle.getBundle("routes"); //load properties file
		return routes;
		
	}
	

	public static Response CreateUser(User payload)
	{
		String post_url=getURL().getString("post_url");
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)

				.when()
				.post(post_url);

		return response;

	}

	public static Response readUser(String UN)
	{
		String get_url=getURL().getString("get_url");
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", UN)

				.when()
				.get(get_url);

		return response;

	}

	public static Response updateUser(String UN,User payload)
	{
		String update_url=getURL().getString("update_url");
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", UN)
				.body(payload)

				.when()
				.put(update_url);

		return response;

	}
	public static Response deleteUser(String UN)
	{
		String delete_url=getURL().getString("delete_url");
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", UN)

				.when()
				.delete(delete_url);

		return response;

	}


}
