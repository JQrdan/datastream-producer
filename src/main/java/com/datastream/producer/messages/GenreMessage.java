package com.datastream.producer.messages;

public class GenreMessage {
  String genreID;
  String parentID;
  String position;
  String genreName;

  public GenreMessage(String genreID, String parentID, String position, String genreName) {
    this.genreID = genreID;
    this.parentID = parentID;
    this.position = position;
    this.genreName = genreName;
  }

  public String getGenreID() {
    return this.genreID;
  }

  public String getParentID() {
    return this.parentID;
  }

  public String getPosition() {
    return this.position;
  }

  public String getGenreName() {
    return this.genreName;
  }

  public String toString() {
		return "GenreMessage{"
				+ "\n\tgenreID: " + this.genreID
				+ "\n\tparentID: " + this.parentID
				+ "\n\tposition: " + this.position
				+ "\n}";
	}
}