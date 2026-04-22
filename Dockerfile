# Stage 1: Build
FROM openjdk:17-slim AS build

# Install dependencies for JavaFX SDK download
RUN apt-get update && apt-get install -y wget unzip && rm -rf /var/lib/apt/lists/*

# Download and install JavaFX SDK (x64)
# Adjust the version if needed
ENV JFX_VERSION=17.0.10
RUN wget https://download2.gluonhq.com/openjfx/${JFX_VERSION}/openjfx-17.0.10_linux-x64_bin-sdk.zip -O /tmp/javafx.zip \
    && unzip /tmp/javafx.zip -d /opt/ \
    && rm /tmp/javafx.zip

ENV PATH_TO_FX=/opt/javafx-sdk-17.0.10/lib

WORKDIR /app
COPY src/ ./src/

# Compile the project with JavaFX modules
# Uses module-info.java for the module structure
RUN find src -name "*.java" > sources.txt \
    && javac --module-path $PATH_TO_FX --add-modules javafx.controls -d out @sources.txt

# Stage 2: Runtime
FROM openjdk:17-slim

# Install dependencies for JavaFX runtime (the SDK contains the native libs)
RUN apt-get update && apt-get install -y wget unzip && rm -rf /var/lib/apt/lists/*

# Re-download/install JavaFX SDK in runtime stage (or copy it from build stage)
ENV JFX_VERSION=17.0.10
RUN wget https://download2.gluonhq.com/openjfx/${JFX_VERSION}/openjfx-17.0.10_linux-x64_bin-sdk.zip -O /tmp/javafx.zip \
    && unzip /tmp/javafx.zip -d /opt/ \
    && rm /tmp/javafx.zip

ENV PATH_TO_FX=/opt/javafx-sdk-17.0.10/lib

WORKDIR /app
COPY --from=build /app/out ./out

# Default main class: factory_abstract.Client
# The project uses a module named 'dp.factory' as defined in src/module-info.java
# Use: docker run -it --rm <image-name> to interact with the CLI
ENTRYPOINT ["java", "--module-path", "/app/out:/opt/javafx-sdk-17.0.10/lib", "--add-modules", "javafx.controls", "-m", "dp.factory/factory_abstract.Client"]
