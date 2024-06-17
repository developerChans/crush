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
  CURRENT_DEPLOYMENT=$(kubectl get svc ${SERVICE} -o jsonpath='{.spec.selector.version}')
  if [ "$CURRENT_DEPLOYMENT" == "blue" ]; then
    NEW_DEPLOYMENT="${SERVICE}-green"
    NEW_VERSION="green"
  else
    NEW_DEPLOYMENT="${SERVICE}-blue"
    NEW_VERSION="blue"
  fi

  echo "Deploying new version ($NEW_VERSION) for $SERVICE..."
  kubectl set image deployment/${NEW_DEPLOYMENT} ${SERVICE}=${DOCKER_REPO}/${SERVICE}:${VERSION}
  kubectl rollout status deployment/${NEW_DEPLOYMENT}

  # 서비스 업데이트
  echo "Updating service to point to new deployment for $SERVICE..."
  kubectl patch svc ${SERVICE} -p "{\"spec\": {\"selector\": {\"app\": \"${SERVICE}\", \"version\": \"${NEW_VERSION}\"}}}"

  # 이전 버전 정리 (선택 사항)
  #echo "Deleting old deployment for $SERVICE..."
  #kubectl delete deployment ${CURRENT_DEPLOYMENT}
done
