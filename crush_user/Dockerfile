# FROM openjdk:17

# # 작업 디렉터리 설정
# WORKDIR /app

# # Jar 파일 복사
# COPY build/libs/user_service-0.0.1-SNAPSHOT.jar /app/app.jar

# # MySQL 초기화 스크립트 복사
# COPY init-user-db.sql /docker-entrypoint-initdb.d/

# # 컨테이너 실행 시 명령
# ENTRYPOINT ["java", "-jar", "/app/app.jar"]

# Base image
FROM ubuntu:20.04

# Set environment variables to avoid interactive prompts during package installation
ENV DEBIAN_FRONTEND=noninteractive

# Install dependencies
RUN apt update && \
    apt install -y openjdk-17-jdk mysql-server && \
    apt clean && \
    rm -rf /var/lib/apt/lists/*

# Set the working directory
WORKDIR /app

# Copy the jar file
COPY build/libs/user_service-0.0.1-SNAPSHOT.jar /app/app.jar

# Copy MySQL initialization script
COPY init-user-db.sql /app/init.sql

# Set MySQL root password and create database environment variables
# ENV MYSQL_ROOT_PASSWORD=
ENV MYSQL_DATABASE=user_db
ENV MYSQL_PORT=3306
ENV MYSQL_HOST=localhost
ENV MYSQL_USER=test1
ENV MYSQL_PASSWORD=test1!

# Expose MySQL port
# EXPOSE 3306

# Expose application port
# EXPOSE 8080

# Initialize MySQL and run the application
# CMD service mysql start && \
    # sleep 10 && \
    # mysql -u root -p -e "CREATE DATABASE IF NOT EXISTS ${MYSQL_DATABASE};" && \
    # mysql -u root -p password ${MYSQL_DATABASE} < /app/init.sql && \
    # sudo serice mysql restart \
CMD java -jar /app/app.jar
