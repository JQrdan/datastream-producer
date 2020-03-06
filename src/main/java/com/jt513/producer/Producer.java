package com.jt513.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

public class Producer {
  private static KafkaProducer<Integer, String> createProducer() {
    Properties props = new Properties();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka1:9092,kafka2:9093,kafka3:9094");
    props.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaExampleProducer");
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    return new KafkaProducer<>(props);
  }

  public static void main(String[] args) {
    if (args.length < 2) {
      System.out.println("Please provide command line arguments: topicName EventFiles");
      System.exit(-1);
    }
    
    String topicName = args[0];
    String[] files = Arrays.copyOfRange(args, 1, args.length);

    KafkaProducer<Integer, String> producer = createProducer();
    Thread[] publishers = new Thread[files.length];
    for (int i = 0; i < files.length; i++) {
      publishers[i] = new Thread(new Publisher(producer, topicName, files[i]));
      publishers[i].start();
    }

    try {
      for (Thread t : publishers) {
        t.join();
      }
    } catch (InterruptedException e) {
      // do something
    } finally {
      producer.close();
    }
  }
}