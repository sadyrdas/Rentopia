@echo off

REM Start Service Discovery (Eureka server)
echo Starting Service Discovery...
start "" java -jar discovery-server-0.0.1-SNAPSHOT.jar
timeout /t 10 /nobreak >nul

REM Start API Gateway
echo Starting API Gateway...
start "" java -jar api-gateway-0.0.1-SNAPSHOT.jar
timeout /t 5 /nobreak >nul

REM Start remaining services
echo Starting Account Management Service...
start "" java -jar account-management-service-0.0.1-SNAPSHOT.jar

echo Starting Equipment Management Service...
start "" java -jar equipment-management-service-0.0.1-SNAPSHOT.jar

echo Starting Equipment Reservation Service...
start "" java -jar equipment-reservation-service-0.0.1-SNAPSHOT.jar

echo All services are starting...
