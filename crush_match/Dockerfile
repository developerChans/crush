FROM openjdk:17

# 작업 디렉터리 설정
WORKDIR /app

# Jar 파일 복사
COPY build/libs/match_service-0.0.1-SNAPSHOT.jar /app/app.jar

# MySQL 초기화 스크립트 복사
COPY init-match-db.sql /docker-entrypoint-initdb.d/

# 컨테이너 실행 시 명령
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
