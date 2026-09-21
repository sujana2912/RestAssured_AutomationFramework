package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest {
	
	Faker faker;
	User userpayload;
	
	public Logger logger;
	
	@BeforeClass
	public void setUp()
	{
		faker = new Faker();
		userpayload = new User();
		
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password(5,10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());
		//userpayload.setUserStatus(faker.idNumber().hashCode());
		
		//logs
		logger = LogManager.getLogger(this.getClass());
		logger.debug("debugging-----------------------");		
	}
	
	
	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("*********************POSTING USER*********************************");
		Response response = UserEndpoints.CreateUser(userpayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("*********************USER Created*********************************");
	}
	
	@Test(priority=2)
	public void testReadUser()
	{
		logger.info("*********************geting USER*********************************");
		Response response = UserEndpoints.readUser(this.userpayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*********************USER got*********************************");
	}
	
	@Test(priority=3)
	public void testUpdateUser()
	{
		logger.info("*********************Updating USER*********************************");
		
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndpoints.updateUser(this.userpayload.getUsername(),userpayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//checking data after update
		Response responseAfterUpdate = UserEndpoints.readUser(this.userpayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		logger.info("*********************USER Updated*********************************");
	}
	
	@Test(priority=4)
	public void testDeleteUser()
	{
		logger.info("*********************Deleting USER*********************************");
		Response response = UserEndpoints.deleteUser(this.userpayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*********************USER deletd*********************************");
	}

}
