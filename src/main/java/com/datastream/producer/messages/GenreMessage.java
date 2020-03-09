package com.datastream.producer.messages;

public class GenreMessage {
  String genreID;
  String parentID;
  String position;

  public GenreMessage(String genreID, String parentID, String position) {
    this.genreID = genreID;
    this.parentID = parentID;
    this.position = position;
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

  public String toString() {
		return "GenreMessage{"
				+ "\n\tgenreID: " + this.genreID
				+ "\n\tparentID: " + this.parentID
				+ "\n\tposition: " + this.position
				+ "\n}";
	}
}