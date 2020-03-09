package com.datastream.producer;

public enum MessageType {
  SONGS("songs"),
  GENRES("genres"),
  SONG_ATTRIBUTES("song_attributes");

  public final String label;
 
  private MessageType(String label) {
    this.label = label;
  }
}
