package com.graphql.tests;

import Files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JsonPath js = new JsonPath(Payload.coursePrice());
		Integer noOfcousrse = js.getInt("courses.size()");
		System.out.println(noOfcousrse);

	}

}
