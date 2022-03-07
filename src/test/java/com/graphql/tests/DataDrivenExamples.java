package com.graphql.tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class DataDrivenExamples {
	@DataProvider(name = "dataforpost")
	public Object[][] dataforPost(){
		
		Object[][] data = new Object[2][3];
		
		data[0][0]= "Ishan";
		data[0][1]= "shihj";
		data[0][2] = 3;
		
		data[1][0]= "hari";
		data[1][1]= "yadav";
		data[1][2] = 3;
		
		return data;
				
	
	}
	
@Test(dataProvider = "dataforpost")
	
	public void post_req(String firstName, String LastName, int subjectID) {
		
		
		
		JSONObject js = new JSONObject();
		js.put("firstName", firstName);
		js.put("LastName", LastName);
		js.put("SubjectID", subjectID);
		
		baseURI= "http://localhost:3000";
		
		given().
			contentType(ContentType.JSON).accept(ContentType.JSON).
					body(js.toJSONString()).
					when().post("/posts").
							then().statusCode(201).log().all();
								
	} 

}
