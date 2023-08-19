package com.spotify.api;

import static io.restassured.RestAssured.given;

import com.spotify.pojo.Playlist;

import io.restassured.response.Response;

public class PlayListAPI 
{
	public static Response post(Playlist requestPlaylist,String token)
	{
		
		 return given(SpacBuilders.reqSpec())
		//.header("Authorization","Bearer "+token)	
		.auth().oauth2(token)		 
        .body(requestPlaylist)
        .when()
     	 .post("/users/fzr4lm9d5odzzbtjvmrlccaqh/playlists")
        .then()
        .spec(SpacBuilders.resSpec())
        .extract()
        .response();
       
	}
	
	public static Response get(String playlist_id,String token)
	{
		return given(SpacBuilders.reqSpec())
	     //.header("Authorization","Bearer "+token)
		 .auth().oauth2(token)
         .when()
         .get("/playlists/"+playlist_id)
         .then()
		    .spec(SpacBuilders.resSpec())
		    .extract()
		    .response();
	}

	public static Response update(String playlist_id,Playlist requestPlaylist,String token)
	{
		return given(SpacBuilders.reqSpec())
				//.header("Authorization","Bearer "+token)	
				.auth().oauth2(token)
				.body(requestPlaylist)
				 
		         .when()
		         .put("/playlists/"+playlist_id)
		         .then()
				 .extract()
				 .response();
		
	}
}
