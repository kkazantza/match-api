FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY target/matchapi-0.0.1-SNAPSHOT.jar app.jar
 # 8080 = app, 5005 = debug
EXPOSE 8080
EXPOSE 5005
#ENTRYPOINT ["java", "-jar", "app.jar"]
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "app.jar"]
