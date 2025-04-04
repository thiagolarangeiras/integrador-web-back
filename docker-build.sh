./gradlew build
docker build --build-arg JAR_FILE=build/libs/\*.jar -t integrador_web .
docker run -d -p 8080:8080 --name integrador_web integrador_web