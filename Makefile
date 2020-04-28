build:
	mvn clean package

songs:
	mvn exec:java -Dexec.mainClass=com.datastream.producer.Producer -Dexec.args="songs ../ydata/train_0.txt ../ydata/train_1.txt ../ydata/train_2.txt ../ydata/train_3.txt ../ydata/train_4.txt ../ydata/train_5.txt ../ydata/train_6.txt ../ydata/train_7.txt ../ydata/train_8.txt ../ydata/train_9.txt" -Dlog4j.configuration=file:src/main/resources/log4j.properties

genres:
	mvn exec:java -Dexec.mainClass=com.datastream.producer.Producer -Dexec.args="genres ../ydata/genre-hierarchy.txt" -Dlog4j.configuration=file:src/main/resources/log4j.properties

song-attributes:
	mvn exec:java -Dexec.mainClass=com.datastream.producer.Producer -Dexec.args="songattributes ../ydata/song-attributes.txt" -Dlog4j.configuration=file:src/main/resources/log4j.properties

all:
	$(MAKE) genres
	$(MAKE) song-attributes
	$(MAKE) songs