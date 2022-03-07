package com.graphql.tests;
import static io.restassured.RestAssured.*;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class Json_server {
	//@Test
	public void testing_server() {
		
		baseURI= "http://localhost:3000";
		
		given().
			get("/posts").
					then().
						statusCode(200).log().all();	
	}
	
	@Test
	
	     public void post_req() {
		
		
		
		JSONObject js = new JSONObject();
		js.put("firstName", "Ishant");
		js.put("LastName", "Sharma");
		js.put("SubjectID", 1);
		
		baseURI= "http://localhost:3000";
		
		given().
			contentType(ContentType.JSON).accept(ContentType.JSON).
					body(js.toJSONString()).
					when().post("/posts").
							then().statusCode(201).log().all();
								
	}
	

}
