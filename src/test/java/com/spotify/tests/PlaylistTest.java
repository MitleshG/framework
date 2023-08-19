package com.spotify.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.spotify.api.PlayListAPI;
import com.spotify.pojo.Playlist;

import Utils.ConfigReader;
import authmanager.TokenCreator;
import io.restassured.response.Response;

public class PlaylistTest {
	static String playlistid;

	@Test(priority = 1)
	public void createPlayList() throws IOException {
		Playlist reqplaylist = new Playlist();

		reqplaylist.setName(ConfigReader.readConfig("name"));
		reqplaylist.setDescription(ConfigReader.readConfig("description"));
		reqplaylist.setPublic(false);

		Response response = PlayListAPI.post(reqplaylist, TokenCreator.renewToken());
		Playlist responseplaylist = response.as(Playlist.class);
		String namevalue = reqplaylist.getName();
		String nameresponse = responseplaylist.getName();

		playlistid = responseplaylist.getId();
		Assert.assertEquals(namevalue, nameresponse);
	}

	@Test(priority = 2)
	public void getPlayList() throws IOException {
		Playlist reqplaylist = new Playlist();

		reqplaylist.setName(ConfigReader.readConfig("name"));
		reqplaylist.setDescription(ConfigReader.readConfig("description"));
		reqplaylist.setPublic(false);

		Response response = PlayListAPI.get(playlistid, TokenCreator.renewToken());
		Playlist responseplaylist = response.as(Playlist.class);
		Assert.assertEquals(responseplaylist.getName(), reqplaylist.getName());
	}

	@Test(priority = 3)
	public void ChangeUserPlayList() throws IOException {
		Playlist reqplaylist = new Playlist();

		reqplaylist.setName(ConfigReader.readConfig("name")+"updated value");
		reqplaylist.setDescription(ConfigReader.readConfig("description"));
		reqplaylist.setPublic(false);

		Response response = PlayListAPI.update(playlistid, reqplaylist, TokenCreator.renewToken());
		int statuscode = response.statusCode();
		Assert.assertEquals(statuscode, 200);
	}

	@Test(priority = 4)
	public void shouldNotBeAbleToCreatePlayList() throws IOException {
		Playlist reqplaylist = new Playlist();

		reqplaylist.setName(ConfigReader.readConfig("name"));
		reqplaylist.setDescription(ConfigReader.readConfig("description"));
		reqplaylist.setPublic(false);

		Response response = PlayListAPI.post(reqplaylist, "12345");
		int stscode = response.statusCode();

		Assert.assertEquals(stscode, 401);
	}

}
