package com.graphql.tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static  io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import Files.Payload;


public class NewGetRequest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		///post
		 RestAssured.baseURI = "https://rahulshettyacademy.com";
		 String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		 .body(Payload.AddPlace()).when().post("maps/api/place/add/json").
		 					then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
		 						.header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		 
		 JsonPath js = new JsonPath(response);
		 String placeId = js.getString("place_id");
		 System.out.println(placeId);
		 
		 // update place 
		 
		 given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").
		 	body("{\r\n"
		 			+ "\"place_id\":\""+ placeId
		 			+ "\",\r\n"
		 			+ "\"address\":\"70 Summer walk, USA\",\r\n"
		 			+ "\"key\":\"qaclick123\"\r\n"
		 			+ "}").when().put("maps/api/place/update/json").
		 						then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		 
		 
		 //getplace 
		 
		 given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId).
		 
		 	when().get("maps/api/place/get/json").
		 			then().log().all().assertThat().statusCode(200).body("address", equalTo("70 Summer walk, USA"))	;
		 
	
	}

}
