#!/bin/bash

echo "Logging into Docker..."
echo $DOCKER_ACCESS_TOKEN | docker login -u $DOCKER_USERNAME --password-stdin

# 설정 변수
SERVICES=("crush_chat" "crush_match" "crush_user")
# SERVICES=("crush_chat" "crush_front" "crush_match" "crush_user")
DOCKER_REPO="developerchans"
VERSION=$(date +%Y%m%d%H%M%S)

for SERVICE in "${SERVICES[@]}"; do
  # Docker 이미지 빌드
  echo "Building Docker image for $SERVICE..."
  echo "docker build -t $SERVICE:$VERSION ../$SERVICE"
  sudo docker build -t ${SERVICE}:${VERSION} ../${SERVICE}
  echo "docker tag $SERVICE:$VERSION $DOCKER_REPO/$SERVICE:$VERSION"
  sudo docker tag ${SERVICE}:${VERSION} ${DOCKER_REPO}/${SERVICE}
  # Docker 이미지 푸시
  echo "docker push $DOCKER_REPO/$SERVICE:$VERSION"
  sudo docker push ${DOCKER_REPO}/${SERVICE}
  # docker push ${DOCKER_REPO}/${SERVICE}:${VERSION}
  pwd
  # sudo kubectl apply -f /home/crush/crush/crush_kube/deployments/${SERVICE}-secret.yaml 
  sudo kubectl apply -f /home/crush/crush/crush_kube/deployments/${SERVICE}-service.yaml
  sudo kubectl apply -f /home/crush/crush/crush_kube/deployments/${SERVICE}-deployment.yaml

  # Kubernetes Deployment 업데이트
  echo "Updating Kubernetes deployment for $SERVICE..."
  sudo kubectl set image deployments/${SERVICE} ${SERVICE}=${DOCKER_REPO}/${SERVICE}:${VERSION}
  echo "Updating rollout status deployment for $SERVICE..."
  sudo kubectl rollout status deployments/${SERVICE}

done
