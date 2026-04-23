# Stage 1: Build
FROM debian:bookworm-slim AS build

RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    wget \
    unzip \
    && rm -rf /var/lib/apt/lists/*

# Download JavaFX SDK 21
ENV JFX_VERSION=21.0.1
RUN wget https://download2.gluonhq.com/openjfx/${JFX_VERSION}/openjfx-${JFX_VERSION}_linux-x64_bin-sdk.zip -O /tmp/javafx.zip \
    && unzip /tmp/javafx.zip -d /opt/ \
    && rm /tmp/javafx.zip

# The directory name inside the zip is javafx-sdk-21.0.1
ENV PATH_TO_FX=/opt/javafx-sdk-21.0.1/lib

WORKDIR /app
COPY src/ ./src/

# Compile
RUN find src -name "*.java" > sources.txt \
    && javac --module-path $PATH_TO_FX --add-modules javafx.controls -d out @sources.txt

# Stage 2: Runtime
FROM debian:bookworm-slim

RUN apt-get update && apt-get install -y \
    openjdk-17-jre \
    xvfb \
    x11vnc \
    websockify \
    novnc \
    libgtk-3-0 \
    libglu1-mesa \
    libxtst6 \
    libasound2 \
    libxrender1 \
    libxi6 \
    libxxf86vm1 \
    procps \
    && rm -rf /var/lib/apt/lists/*

# Copy JavaFX SDK from build stage
COPY --from=build /opt/javafx-sdk-21.0.1 /opt/javafx-sdk-21.0.1
COPY --from=build /app/out /app/out

# Setup noVNC
RUN ln -s /usr/share/novnc/vnc.html /usr/share/novnc/index.html

WORKDIR /app

# Create a startup script
RUN echo '#!/bin/bash\n\
Xvfb :1 -screen 0 1024x768x24 &\n\
export DISPLAY=:1\n\
sleep 2\n\
x11vnc -forever -shared -display :1 -nopw -bg\n\
websockify --web /usr/share/novnc 8080 localhost:5900 &\n\
java --module-path /app/out:/opt/javafx-sdk-21.0.1/lib --add-modules javafx.controls -m dp.factory/dessin.PaintApp\n\
' > /app/start.sh && chmod +x /app/start.sh

EXPOSE 8080

CMD ["/app/start.sh"]
