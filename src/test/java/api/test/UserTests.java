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
		 faker=new Faker();
		 userPayload=new User();
		 userPayload.setId(faker.idNumber().hashCode());
		 userPayload.setUsername(faker.name().username());
		 userPayload.setFirstName(faker.name().firstName());
		 userPayload.setLastName(faker.name().lastName());
		 userPayload.setEmail(faker.internet().safeEmailAddress());
		 userPayload.setPassword(faker.internet().password(5, 10));
		 userPayload.setPhone(faker.phoneNumber().cellPhone());
	 }
	 
	 @Test(priority = 1)
	 public void testPostUser(){
		 
		 Response responce = UserEndPoints.createUser(userPayload);
		 responce.then().log().all();
		 
		 Assert.assertEquals(responce.getStatusCode(),200);
	 }
	 
	 public void testGetUser(){
		 
		 Response responce = UserEndPoints.getUser(userPayload.getUsername());
		 responce.then().log().all();
		 
		 Assert.assertEquals(responce.getStatusCode(),200);
	 }
	 
	 public void testUpdateUser(){
		 
		 Response responce = UserEndPoints.updateUser(userPayload.getUsername(), userPayload);
		 responce.then().log().all();
		 
		 Assert.assertEquals(responce.getStatusCode(),200);
	 }
	 
	 public void testDeleteUser(){
		 
		 Response responce = UserEndPoints.deleteUser(userPayload.getUsername());
		 responce.then().log().all();
		 
		 Assert.assertEquals(responce.getStatusCode(),200);
	 }
	 
}
