# stage 1 — build
FROM gradle:8.7-jdk21 AS builder

WORKDIR /app
COPY . .

RUN gradle build -x test

# stage 2 — runtime
FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]