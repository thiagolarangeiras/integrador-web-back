./gradlew build && java -jar build/libs/integrador-web-0.0.1-SNAPSHOT.jar
docker build --build-arg JAR_FILE=build/libs/\*.jar -t teste .
docker run -p 8080:8080 teste