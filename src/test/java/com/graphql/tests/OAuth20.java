package com.graphql.tests;
import static io.restassured.RestAssured.*;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Files.Payload;
import Pojo.getCourse;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;


public class OAuth20 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		
		/*
		 * System.setProperty("webdriver.chrome.driver",
		 * "D:\\Driver\\chromedriver.exe"); WebDriver driver = new ChromeDriver();
		 * driver.manage().window().maximize(); //driver.manage().deleteAllCookies();
		 * driver.get(
		 * "https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php"
		 * );
		 * driver.findElement(By.id("identifierId")).sendKeys("neha.mishra409@gmail.com"
		 * ); driver.findElement(By.id("identifierId")).sendKeys(Keys.ENTER);
		 * 
		 * Thread.sleep(1000);
		 * 
		 * driver.findElement(By.cssSelector("input[type='password']")).sendKeys(
		 * "9670051369");
		 * driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.
		 * ENTER); String url= driver.getCurrentUrl();
		 */
		
		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AX4XfWgkGpWhN2Jj1RLw3qWRw48DfXNSABPN0UeFr4H3eSLOEHLmYIMk5FuJGhG-AEABUQ&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=1&prompt=none";
		String partialcode= url.split("code=")[1];
		String code= partialcode.split("&scope")[0];
		System.out.println("code is " + code);
		

		
		String accessTokenResponse = given().log().all().urlEncodingEnabled(false)
				.queryParam("code", code).queryParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
		queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php").
		queryParam("grant_type", "authorization_code").
					when().post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		JsonPath js = new JsonPath(accessTokenResponse);
		String accessToken = js.get("access_token");
		
		
		
		
		
		getCourse gc= given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON)
				.when().get("https://rahulshettyacademy.com/getCourse.php").
					as(getCourse.class);
		
		//pojo
		System.out.println(gc.getLinkedln());
		System.out.println(gc.getInstructor());
		
		//System.out.println(response);

	}

}
