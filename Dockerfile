# ---- Build Stage ----
# Use an official Gradle image with a compatible JDK.
# Make sure this JDK version is compatible with your project's requirements.
FROM gradle:jdk21 AS builder

# Set the working directory
WORKDIR /home/gradle/project

# Copy the Gradle wrapper files
COPY gradle/wrapper/gradle-wrapper.jar gradle/wrapper/gradle-wrapper.properties ./gradle/wrapper/
COPY gradlew ./

# Copy the rest of the project
COPY build.gradle settings.gradle gradle.properties ./
COPY gradle/libs.versions.toml gradle/versions.gradle ./gradle/

# Copy the modules. Note : remove build ?
COPY cex-trading-bot ./cex-trading-bot
COPY cex-trading-database ./cex-trading-database
COPY cex-trading-env ./cex-trading-env
COPY cex-trading-tools ./cex-trading-tools

RUN rm -rf cex-trading-*/build

RUN chmod +x ./gradlew

# Build the distZip artifact
RUN ./gradlew :cex-trading-bot:distZip

# ---- Runtime Stage ----
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copy the extracted distribution from the build stage
COPY --from=builder /home/gradle/project/cex-trading-bot/build/distributions/cex-trading-bot.zip /tmp/
RUN unzip /tmp/cex-trading-bot.zip -d /app && \
    mv /app/cex-trading-bot/* /app/ && \
    rm -rf /app/cex-trading-bot /tmp/cex-trading-bot.zip

ENTRYPOINT ["/app/bin/cex-trading-bot"]
