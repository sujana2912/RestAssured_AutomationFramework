package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTest {
	
	@Test(priority=1, dataProvider="Data", dataProviderClass= DataProviders.class)
	void testPostUser(String userId, String username, String fname, String lname, String useremail, String pwd, String ph)
	{
		User userpayload = new User();
		
		userpayload.setId(Integer.parseInt(userId));
		userpayload.setUsername(username);
		userpayload.setFirstName(fname);
		userpayload.setLastName(lname);
		userpayload.setEmail(useremail);
		userpayload.setPassword(pwd);
		userpayload.setPhone(ph);
		
		Response response = UserEndpoints.CreateUser(userpayload);
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		
	}
	
	@Test(priority=2, dataProvider="UserNames", dataProviderClass= DataProviders.class)
	void testDeleteUser(String username)
	{
		Response response = UserEndpoints.deleteUser(username);
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
