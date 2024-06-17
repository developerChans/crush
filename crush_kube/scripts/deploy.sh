#!/bin/bash

# 설정 변수
SERVICES=("crush_chat" "crush_match" "crush_user")
# SERVICES=("crush_chat" "crush_front" "crush_match" "crush_user")
DOCKER_REPO="develooper/crush"
VERSION=$(date +%Y%m%d%H%M%S)

for SERVICE in "${SERVICES[@]}"; do
  # Docker 이미지 빌드
  echo "Building Docker image for $SERVICE..."
  docker build -t ${DOCKER_REPO}/${SERVICE}:${VERSION} ../${SERVICE}

  # Docker 이미지 푸시
  echo "Pushing Docker image for $SERVICE..."
  docker push ${DOCKER_REPO}/${SERVICE}:${VERSION}

  # Kubernetes Deployment 업데이트
  echo "Updating Kubernetes deployment for $SERVICE..."
  kubectl set image deployment/${SERVICE} ${SERVICE}=${DOCKER_REPO}/${SERVICE}:${VERSION}
  kubectl rollout status deployment/${SERVICE}
done
