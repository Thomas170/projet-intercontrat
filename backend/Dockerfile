# Stage 1: Build the application
FROM maven:3-eclipse-temurin-22 as builder

COPY . .

# Build the Spring Boot application
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:22

# Copy the jar from the build stage
COPY --from=builder /target/project-0.0.1-SNAPSHOT.jar /project.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "project.jar"]
