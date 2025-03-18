JAVA_HOME C:\Program Files\Java\jre1.8.0_421

./gradlew bootRun


# Links
https://www.baeldung.com/spring-boot-google-app-engine
https://hostingtutorials.dev/blog/free-spring-boot-host-with-render
https://medium.com/@cmmapada/advanced-search-and-filtering-using-spring-data-jpa-specification-and-criteria-api-b6e8f891f2bf

# Swagger
https://springdoc.org/#getting-started
`localhost:8080/swagger-ui.html`

- Caso o swgger não funcione 
definir esse dois no application.properties e chamar `localhost:8080/myapp/swagger-ui.html`
```
server.servlet.context-path=/myapp
springdoc.swagger-ui.path=/swagger-ui.html
```
