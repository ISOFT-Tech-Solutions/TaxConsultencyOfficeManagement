# Use an official OpenJDK runtime as a parent image for Java 21
FROM eclipse-temurin:21-jre

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file into the container at /app
COPY target/TaxConsultencyManagement-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose port 8080
EXPOSE 8082

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
