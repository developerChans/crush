#!/bin/bash

if ! type -p java > /dev/null || [[ "$(java -version 2>&1)" != *"17."* ]]; then
    echo "JDK 17 is not installed or not set as default. Installing OpenJDK 17..."
    sudo apt update
    sudo apt install -y openjdk-17-jdk
    sudo update-alternatives --install /usr/bin/java java /usr/lib/jvm/java-17-openjdk-amd64/bin/java 1
    sudo update-alternatives --set java /usr/lib/jvm/java-17-openjdk-amd64/bin/java
else
    echo "JDK 17 is already installed and set as default."
fi

# Gradle 빌드
SERVICES=("crush_chat" "crush_match" "crush_user")
# SERVICES=("crush_chat" "crush_front" "crush_match" "crush_user")

for SERVICE in "${SERVICES[@]}"; do
  echo "Building $SERVICE..."
  (cd ../${SERVICE} && ./gradlew clean build)
done
