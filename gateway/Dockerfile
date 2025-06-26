# Build stage
FROM maven:3.9.9-eclipse-temurin-21-alpine AS builder

WORKDIR /build

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

# Runtime stage
FROM amazoncorretto:21

WORKDIR /app

COPY --from=builder /build/target/gateway-*.jar /app.jar

EXPOSE 8222

ENTRYPOINT ["java", "-jar", "/app.jar"]