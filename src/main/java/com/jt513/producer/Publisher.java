package com.jt513.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.File;
import java.util.Scanner;

public class Publisher implements Runnable {

  private final KafkaProducer<Integer, String> producer;
  private final String topicName;
  private final String fileLocation;

  Publisher(KafkaProducer<Integer, String> producer, String topicName, String fileLocation) {
    this.producer = producer;
    this.topicName = topicName;
    this.fileLocation = fileLocation;
  }

  @Override
  public void run() {
    File file = new File(fileLocation);
    int key = 0;

    try (Scanner scanner = new Scanner(file)) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        producer.send(new ProducerRecord<>(topicName, key++, line));
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
} 