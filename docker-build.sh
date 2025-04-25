./gradlew build

docker build --build-arg JAR_FILE=build/libs/\*.jar -t integrador_web .

docker tag integrador_web thiagolarangeira/integrador_web:2

docker push thiagolarangeira/integrador_web:2

docker run -d -p 8080:8080 -e INT_WEB_POSTGRES_PASS --name integrador_web integrador_web