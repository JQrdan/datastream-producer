package com.datastream.producer.messages;

//song id<TAB>albumid<TAB>artist id<TAB>genre id

public class SongAttributesMessage {
  String songID;
  String albumID;
  String artistID;
  String genreID;

  public SongAttributesMessage(String songID, String albumID, String artistID, String genreID) {
    this.songID = songID;
    this.albumID = albumID;
    this.artistID = artistID;
    this.genreID = genreID;
  }

  public String getSongID() {
    return this.songID;
  }

  public String getAlbumID() {
    return this.albumID;
  }

  public String getArtistID() {
    return this.artistID;
  }

  public String getGenreID() {
    return this.genreID;
  }

  public String toString() {
		return "SongAttributesMessage{"
				+ "\n\tsongID: " + this.songID
				+ "\n\talbumID: " + this.albumID
        + "\n\tartistID: " + this.artistID
        + "\n\tgenreID: " + this.genreID
				+ "\n}";
	}
}