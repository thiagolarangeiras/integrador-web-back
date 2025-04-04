# Java
JAVA_HOME C:\Program Files\Java\jre1.8.0_421

./gradlew bootRun
./gradlew clean
./gradlew build

# Java Links
https://www.baeldung.com/spring-boot-google-app-engine
https://hostingtutorials.dev/blog/free-spring-boot-host-with-render
https://medium.com/@cmmapada/advanced-search-and-filtering-using-spring-data-jpa-specification-and-criteria-api-b6e8f891f2bf
https://springdoc.org/#getting-started

# Java Swagger
`localhost:8080/swagger`

application.properties
```
server.servlet.context-path=/myapp
springdoc.swagger-ui.path=/swagger-ui.html
```



# Postgres Comandos
psql -U postgres

# Postgres Docker
docker pull postgres
docker run --name postgres1 -e POSTGRES_PASSWORD=1234 -d -p 5432:5432 postgres

server: `localhost:5432/postgres`   
Usuario: `postgres`     
Senha: `1234`       



# Docker Tutorial 
https://spring.io/guides/gs/spring-boot-docker

# Docker Comandos
docker exec -it postgres1 bash
docker builder prune --all
docker commit a a:1     
docker tag a:1 user/a:2         
docker push user/a:2

# Docker Buildar
./gradlew build && java -jar build/libs/integrador-web-0.0.1.jar
docker build --build-arg JAR_FILE=build/libs/\*.jar -t integrador_web .
docker run -p 8080:8080 --name integrador_web integrador_web