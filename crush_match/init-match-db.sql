FROM openjdk:11-jre-slim

# 작업 디렉터리 설정
WORKDIR /app

# Jar 파일 복사
COPY build/libs/match-service.jar /app/app.jar

# MySQL 설치 및 설정
RUN apt-get update && apt-get install -y mysql-server && \
    service mysql start && \
    mysql -e "CREATE DATABASE matchdb;" && \
    mysql -e "CREATE USER 'root'@'%' IDENTIFIED BY 'your_password';" && \
    mysql -e "GRANT ALL PRIVILEGES ON matchdb.* TO 'root'@'%';" && \
    mysql -e "FLUSH PRIVILEGES;"

# MySQL 초기화 스크립트 복사
COPY init-match-db.sql /docker-entrypoint-initdb.d/

# 컨테이너 실행 시 명령
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
