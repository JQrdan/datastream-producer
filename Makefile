run:
	mvn exec:java -Dexec.mainClass=com.jt513.producer.Producer -Dexec.args="testTopic ../ydata/train_0.txt"