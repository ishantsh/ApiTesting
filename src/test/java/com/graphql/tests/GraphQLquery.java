package com.graphql.tests;
import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class GraphQLquery {
	
	@Test
	 public void getAllfilmTest() {
		 //https://swapi-graphql.netlify.app/.netlify/functions/index
		
		RestAssured.baseURI="https://swapi-graphql.netlify.app";
		String query ="{\r\n"
				+ "    \"query\": \"{\\n  allFilms{\\n    films{\\n      title\\n    }\\n  }\\n}\",\r\n"
				+ "    \"variables\": null\r\n"
				+ "}";
		
		
		given().log().all()
		     .contentType("application/json")
		     	.body(query)
		     		.when().log().all()
		     			.post("/.netlify/functions/index")
		     				.then().log().all()
		     					.assertThat().statusCode(200)
		     						.and()
		     							.body("data.allFilms.films[0].title", equalTo("A New Hope"));
		     
	
	
	
	
	}
	
	@Test
	public void getAllUser()
	{
		RestAssured.baseURI="https://giving-pug-66.hasura.app";
		
		String query ="\r\n"
				+ "{\r\n"
				+ "    \"query\": \"{\\n  users(limit: 10) {\\n    id\\n    nama\\n  }\\n}\\n\\n\\n\",\r\n"
				+ "    \"variables\": null\r\n"
				+ "}";
		
		given().log().all()
			.contentType("application/json")
					.header("x-hasura-admin-secret","lDEOE13IU6oApCRymAuc5vXX9Mmh9t91Awswx1364oYZoRuj6n5aqLpDtgXwnbAK")
							.body(query)
									.when()
										.post("/v1/graphql")
											.then()
												.assertThat()
													.statusCode(200);
														
	}

}
