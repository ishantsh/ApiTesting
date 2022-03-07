package com.graphql.tests;
import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;


public class Tests_Post {
	
	
	@Test
	public void test_011() {
		HashMap<String, String> maps = new HashMap<String,String>();
		maps.put("name", "ishant");
		maps.put("job", "ENginer");
		
		JSONObject req = new JSONObject(maps);
		System.out.println(req);
		
		given().
			body(req.toJSONString()).
				when().
					post("https://reqres.in/api/users").
						then().
							statusCode(201);
	
	}
	


		

}
