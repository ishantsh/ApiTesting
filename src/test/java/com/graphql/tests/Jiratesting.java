package com.graphql.tests;

import static io.restassured.RestAssured.*;

import java.io.File;

import Files.Payload;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

public class Jiratesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "http://localhost:8080";
		// to create the session for user and use this session object throughout the
		// subsequent methods
		SessionFilter session = new SessionFilter();

		given().header("Content-Type", "application/json").log().all().body(Payload.usercred()).filter(session).when()
				.post("/rest/auth/1/session").then().log().all();

		given().pathParam("key", "10003").log().all().header("Content-Type", "application/json")
				.body("{\r\n" + "    \"body\": \"HeY ishant, you are awsome\",\r\n" + "    \"visibility\": {\r\n"
						+ "        \"type\": \"role\",\r\n" + "        \"value\": \"Administrators\"\r\n" + "    }\r\n"
						+ "}")
				.filter(session).when().post("/rest/api/2/issue/{key}/comment").then().log().all().assertThat()
				.statusCode(201);

		// add attachment
		given().header("X-Atlassian-Token", "no-check").pathParam("key", "10003").log().all()
				.header("Content-Type", "multipart/form-data").multiPart("file", new File("jira.txt")).filter(session)
				.when().post("/rest/api/2/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);

	}

}
