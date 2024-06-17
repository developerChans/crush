#!/bin/bash


# Gradle 빌드
SERVICES=("crush_chat" "crush_match" "crush_user")
# SERVICES=("crush_chat" "crush_front" "crush_match" "crush_user")

for SERVICE in "${SERVICES[@]}"; do
  echo "Building $SERVICE..."
  (cd ../${SERVICE} && ./gradlew clean )
done
