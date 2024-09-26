package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {

	Faker faker;
	User userPayload;

	@BeforeClass
	public void setupData() {
		faker = new Faker();
		userPayload = new User();
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
	}

	@Test(priority = 1)
	public void testPostUser() {

		Response responce = UserEndPoints.createUser(this.userPayload);
		responce.then().log().all();

		Assert.assertEquals(responce.getStatusCode(), 200);
	}
	
	@Test(priority = 2)
	public void testGetUserByName() {

		Response responce = UserEndPoints.getUser(this.userPayload.getUsername());
		responce.then().log().all();

		Assert.assertEquals(responce.getStatusCode(), 200);
	}
	
	@Test(priority = 3)
	public void testUpdateUserByName() {

		Response responce = UserEndPoints.updateUser(this.userPayload.getUsername(), this.userPayload);
		responce.then().log().all();

		Assert.assertEquals(responce.getStatusCode(), 200);
	}
	
	@Test(priority = 4)
	public void testDeleteUser() {

		Response responce = UserEndPoints.deleteUser(this.userPayload.getUsername());
		responce.then().log().all();

		Assert.assertEquals(responce.getStatusCode(), 200);
	}

}
