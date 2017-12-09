mvn clean compile assembly:single
mvn test
java -jar target/fss-client-jar-with-dependencies.jar $1