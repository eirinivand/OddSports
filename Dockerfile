LABEL authors="evandorou"

FROM openjdk:21-slim

# The application's jar file
ARG JAR_FILE=target/*.jar

# Add the application's jar to the container
ADD ${JAR_FILE} oddsports.jar

# Run the jar file
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar","oddsports.jar"]
