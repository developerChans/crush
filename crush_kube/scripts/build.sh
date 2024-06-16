#!/bin/bash

# Gradle 빌드
SERVICES=("crush-chat" "crush-front" "crush-match" "crush-user")

for SERVICE in "${SERVICES[@]}"; do
  echo "Building $SERVICE..."
  (cd ../${SERVICE} && ./gradlew clean build)
done
