package:
	mvn package

clean:
	mvn clean

songs:
	mvn exec:java -Dexec.mainClass=com.datastream.producer.Producer -Dexec.args="songs ../ydata/train_0.txt ../ydata/train_1.txt ../ydata/train_2.txt ../ydata/train_3.txt ../ydata/train_4.txt ../ydata/train_5.txt ../ydata/train_6.txt ../ydata/train_7.txt ../ydata/train_8.txt ../ydata/train_9.txt"

genres:
	mvn exec:java -Dexec.mainClass=com.datastream.producer.Producer -Dexec.args="genres ../ydata/genre-hierarchy.txt"

song-attributes:
	mvn exec:java -Dexec.mainClass=com.datastream.producer.Producer -Dexec.args="songAttributes ../ydata/song-attributes.txt"