FROM openjdk:17
VOLUME /tmp
COPY target/*.jar app.jar
EXPOSE 8084
ENTRYPOINT ["java","-jar","/app.jar"]