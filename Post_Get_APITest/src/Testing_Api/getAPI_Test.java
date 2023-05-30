package Testing_Api;
import static io.restassured.RestAssured.given;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class getAPI_Test {


		public static void main(String[] args) {
			
			//Step 1 : Declare Base URL
			RestAssured.baseURI= "https://demoqa.com/BookStore";
			
			//Step 2: Configure 
			
			int statusCode = given().header("Content-Type","application/json").when()
					.get("/v1/Books").then().extract().statusCode();
			
			String responseBody = given().header("Content-Type","application/json").log().all()
					.when().get("/v1/Books").then().log().all().extract().response().asString();
			
			System.out.println(statusCode);
			System.out.println(responseBody);
			
			
			
			
			String[]isbn = {"9781449325862","9781449331818","9781449337711","9781449365035","9781491904244","9781491950296","9781593275846","9781593277574"};
			String[]title = {"Git Pocket Guide","Learning JavaScript Design Patterns","Designing Evolvable Web APIs with ASP.NET","Speaking JavaScript",
					"You Don't Know JS","Programming JavaScript Applications","Eloquent JavaScript, Second Edition","Understanding ECMAScript 6",};
			String[] subTitle = {"A Working Introduction", "A JavaScript and jQuery Developer's Guide","Harnessing the Power of the Web","An In-Depth Guide for Programmers",
					             "ES6 & Beyond","Robust Web Architecture with Node, HTML5, and Modern JS Libraries","A Modern Introduction to Programming",
					             "The Definitive Guide for JavaScript Developers",};
			String[] author= {"Richard E. Silverman","Addy Osmani","Glenn Block et al.","Axel Rauschmayer","Kyle Simpson","Eric Elliott",
					          "Marijn Haverbeke","Nicholas C. Zakas",} ;
			
			JsonPath jsp = new JsonPath(responseBody);
			int count = jsp.getList("books").size(); //6
			
			System.out.println(count);
			
			//validate each object in data array
			
		      for(int i = 0; i < count; i++) {
		    	
		    	String exp_isbn = isbn[i];
		    	String exp_title = title[i];
		    	String exp_subTitle = subTitle[i];
		    	String exp_author = author [i];
		    	
		    	String res_isbn = jsp.getString("books["+i+"].isbn");
		    	String res_title = jsp.getString("books["+i+"].title");
		    	String res_subTitle = jsp.getString("books["+i+"].subTitle");
		    	String res_author = jsp.getString("books["+i+"].author");
		    	
		    	Assert.assertEquals(res_isbn, exp_isbn, "isbn at index "+ i);
		    	Assert.assertEquals(res_title, exp_title, "title at index " +i);
		    	Assert.assertEquals(res_subTitle, exp_subTitle, "subTitle at index " +i);
		    	Assert.assertEquals(res_author, exp_author, "author at index " +i);
		    	
		    		  
			
			
			
			
			
			
	}
		}}

