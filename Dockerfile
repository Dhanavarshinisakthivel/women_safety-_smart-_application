# =====================================================================
# DOCKERFILE
# Think of this as a recipe that tells Railway (or any Docker host)
# exactly how to build and run our app in a clean, isolated box
# ("container") - so it works the same everywhere, not just on your PC.
#
# STAGE 1 ("builder"): compile our Java code into a runnable .jar file
# STAGE 2: copy just that .jar into a small, lightweight final image
# (this two-stage trick keeps the final deployed image small and fast)
# =====================================================================

# ---- Stage 1: Build ----
FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
# Download dependencies first (separate layer = faster rebuilds later)
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests -B

# ---- Stage 2: Run ----
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=builder /app/target/women-safety-app.jar app.jar

# Railway tells us which port to listen on via the PORT env variable
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java -jar app.jar"]
