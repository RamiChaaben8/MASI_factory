# Stage 1: Build
FROM debian:stable-slim AS build

# Install OpenJDK 17 and build tools
RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    wget \
    unzip \
    && rm -rf /var/lib/apt/lists/*

# Download and install JavaFX SDK (x64)
ENV JFX_VERSION=17.0.10
RUN wget https://download2.gluonhq.com/openjfx/${JFX_VERSION}/openjfx-17.0.10_linux-x64_bin-sdk.zip -O /tmp/javafx.zip \
    && unzip /tmp/javafx.zip -d /opt/ \
    && rm /tmp/javafx.zip

ENV PATH_TO_FX=/opt/javafx-sdk-17.0.10/lib

WORKDIR /app
COPY src/ ./src/

# Compile the project with JavaFX modules
RUN find src -name "*.java" > sources.txt \
    && javac --module-path $PATH_TO_FX --add-modules javafx.controls -d out @sources.txt

# Stage 2: Runtime
FROM debian:stable-slim

# Install OpenJDK 17 Runtime and JavaFX system dependencies
RUN apt-get update && apt-get install -y \
    openjdk-17-jre \
    wget \
    unzip \
    libgtk-3-0 \
    libglu1-mesa \
    libxtst6 \
    libasound2 \
    libxrender1 \
    libxi6 \
    libxxf86vm1 \
    && rm -rf /var/lib/apt/lists/*

# Re-install JavaFX SDK for native libraries in runtime
ENV JFX_VERSION=17.0.10
RUN wget https://download2.gluonhq.com/openjfx/${JFX_VERSION}/openjfx-17.0.10_linux-x64_bin-sdk.zip -O /tmp/javafx.zip \
    && unzip /tmp/javafx.zip -d /opt/ \
    && rm /tmp/javafx.zip

WORKDIR /app
COPY --from=build /app/out ./out

# Default main class: factory_abstract.Client
ENTRYPOINT ["java", "--module-path", "/app/out:/opt/javafx-sdk-17.0.10/lib", "--add-modules", "javafx.controls", "-m", "dp.factory/factory_abstract.Client"]
