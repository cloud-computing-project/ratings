FROM openjdk:8-jre-alpine

RUN mkdir /app

WORKDIR /app

ADD ./api/target/ratings-api-1.0.0-SNAPSHOT.jar /app

EXPOSE 8088

CMD ["java", "-jar", "ratings-api-1.0.0-SNAPSHOT.jar"]