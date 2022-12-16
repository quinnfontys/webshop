FROM eclipse-temurin
COPY target/fullstack-backend-0.0.1-SNAPSHOT.jar fullstack-backend-1.0.0.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/fullstack-backend-1.0.0.jar"]


#FROM adoptopenjdk/openjdk11:ubl as build
#WORKDIR /app
#COPY mvnw .
#COPY .mvn .mvn
#COPY pom.xml .
#RUN ./mvnw dependency:go-offline -B
#COPY src src
#RUN ./mvnw package -DskipTests
#RUN mkdir -p target/dependency && (cd target/dependency; jar xf ../*.jar)