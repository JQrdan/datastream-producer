package com.datastream.producer.messages;

public class SongMessage {
  String userID;
  String songID;
  String rating;

  public SongMessage(String userID, String songID, String rating) {
    this.userID = userID;
    this.songID = songID;
    this.rating = rating;
  }

  public String getUserID() {
    return this.userID;
  }

  public String getSongID() {
    return this.songID;
  }

  public String getRating() {
    return this.rating;
  }

  public String toString() {
		return "SongMessage{"
				+ "\n\tuserID: " + this.userID
				+ "\n\tsongID: " + this.songID
				+ "\n\trating: " + this.rating
				+ "\n}";
	}
}