package com.datastream.producer;

public enum MessageType {
  SONGS("songs"),
  GENRES("genres"),
  SONGATTRIBUTES("songAttributes");

  public final String label;
 
  private MessageType(String label) {
    this.label = label;
  }
}
