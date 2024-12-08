# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot JAR file into the container
COPY target/digital-menu-0.0.1-SNAPSHOT.jar /app/digital-menu.jar

# Expose the port your application listens on (8081 in this case)
EXPOSE 8081

# Set environment variables for the PostgreSQL connection (Optional)
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5433/postgres
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=iamdkj237

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/digital-menu.jar"]
