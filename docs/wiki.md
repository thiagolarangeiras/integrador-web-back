# Java
JAVA_HOME C:\Program Files\Java\jre1.8.0_421

./gradlew bootRun
./gradlew clean
./gradlew build

./gradlew build
docker build --build-arg JAR_FILE=build/libs/integrador-web-0.0.1.jar -t integrador_web .
docker tag integrador_web thiagolarangeira/integrador_web:2
docker push thiagolarangeira/integrador_web:2

docker run -d -p 8080:8080 \
-e INT_WEB_POSTGRES_URL="jdbc:postgresql://host.docker.internal:5432/integrador-web" \
-e INT_WEB_POSTGRES_USER="postgres" \
-e INT_WEB_POSTGRES_PASS="1234" \
--name integrador_web integrador_web

# Java Links
https://www.baeldung.com/spring-boot-google-app-engine
https://hostingtutorials.dev/blog/free-spring-boot-host-with-render
https://medium.com/@cmmapada/advanced-search-and-filtering-using-spring-data-jpa-specification-and-criteria-api-b6e8f891f2bf
https://springdoc.org/#getting-started
https://medium.com/@felipeacelinoo/protegendo-sua-api-rest-com-spring-security-e-autenticando-usuários-com-token-jwt-em-uma-aplicação-d70e5b0331f9

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