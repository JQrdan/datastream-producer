package com.datastream.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.datastream.producer.messages.SongMessage;
import com.datastream.producer.messages.GenreMessage;
import com.datastream.producer.messages.SongAttributesMessage;

import java.io.File;
import java.util.Scanner;

public class Publisher implements Runnable {

  private final KafkaProducer<Integer, JsonNode> producer;
  private final String topicName;
  private final String fileLocation;
  private ObjectMapper objectMapper;

  Publisher(KafkaProducer<Integer, JsonNode> producer, String topicName, String fileLocation) {
    this.producer = producer;
    this.topicName = topicName;
    this.fileLocation = fileLocation;

    objectMapper = new ObjectMapper();
  }

  public Object constructMessage(String[] messageString) {
    Object message;

    switch(MessageType.valueOf(this.topicName.toUpperCase())) {
      case GENRES:
        message = new GenreMessage(messageString[0], messageString[1], messageString[2]);
        break;
      case SONG_ATTRIBUTES:
        message = new SongAttributesMessage(messageString[0], messageString[1], messageString[2], messageString[3]);
        break;
      default:
      case SONGS:
        message = new SongMessage(messageString[0], messageString[1], messageString[2]);
    }

    return message;
  }

  @Override
  public void run() {
    File file = new File(fileLocation);
    int key = 0;

    try (Scanner scanner = new Scanner(file)) {
      while (scanner.hasNextLine()) {
        String[] messageString = scanner.nextLine().split("\t");
        JsonNode jsonNode = objectMapper.valueToTree(constructMessage(messageString));
        producer.send(new ProducerRecord<Integer, JsonNode>(topicName, key++, jsonNode));
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
} 
