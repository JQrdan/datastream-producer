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

class Message {
  String key;
  JsonNode value;

  Message(String key, JsonNode value) {
    this.key = key;
    this.value = value;
  }
}

public class Publisher implements Runnable {

  private final KafkaProducer<String, JsonNode> producer;
  private final String topicName;
  private final String fileLocation;
  private ObjectMapper objectMapper;

  Publisher(KafkaProducer<String, JsonNode> producer, String topicName, String fileLocation) {
    this.producer = producer;
    this.topicName = topicName;
    this.fileLocation = fileLocation;

    objectMapper = new ObjectMapper();
  }

  public Message constructMessage(String[] messageString) {
    String key;
    Object value;

    switch(MessageType.valueOf(this.topicName.toUpperCase())) {
      case GENRES:
        key = messageString[0];
        value = new GenreMessage(messageString[0], messageString[1], messageString[2], messageString[3]);
        break;
      case SONGATTRIBUTES:
        key = messageString[0];
        value = new SongAttributesMessage(messageString[0], messageString[1], messageString[2], messageString[3]);
        break;
      default:
      case SONGS:
        key = messageString[1]; // songId is the second field
        value = new SongMessage(messageString[0], messageString[1], messageString[2]);
    }

    return new Message(key, objectMapper.valueToTree(value));
  }

  @Override
  public void run() {
    File file = new File(fileLocation);

    try (Scanner scanner = new Scanner(file)) {
      while (scanner.hasNextLine()) {
        String[] messageString = scanner.nextLine().split("\t");
        Message message = constructMessage(messageString);
        producer.send(new ProducerRecord<String, JsonNode>(topicName, message.key, message.value));
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
} 
