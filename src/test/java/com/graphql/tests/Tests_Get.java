package com.graphql.tests;
import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class Tests_Get {
	
	@Test
	public void Test_01() {
		
		given().
			get("https://reqres.in/api/users?page=2").
				then().statusCode(200).log().all();
				
	}

}
