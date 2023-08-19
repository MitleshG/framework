package authmanager;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TokenCreator 
{
   public static String renewToken()
   {
	   HashMap<String,String> param=new HashMap<String,String>();
	   param.put("grant_type","refresh_token");
	   param.put("refresh_token","AQBzu8oQ0v9QRnWX2Kkx9t80AUmFWAIpyTEjbtf8IWAxGtJdugiz0MlRWPnFbGZQzplu5heRE0Wkzn5xfT1Tn25FEBgM7e9PuqYA_xOH9jNpk_X917OUpfUDVDD-mNj0reU");
	   param.put("client_id","c149fbba4555476586059a3df62f8488");
	   param.put("client_secret","ffc22f1d52b246c494bf72d49c6ce462");
	   
	   RestAssured.baseURI="https://accounts.spotify.com";
	         Response response=given()
	                    .contentType(ContentType.URLENC)
	                    .formParams(param)
	                    .when()
	                    .post("/api/token")
	                    .then()
	                    .extract()
	                    .response();
	         
	         if(response.statusCode()!=200)
	         {
	        	 throw new RuntimeException("Failed to create the access token");
	         }

	          String token=response.path("access_token");
	          return token;
   
   }
	
}
