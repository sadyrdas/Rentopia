# Prototypes of Rentopia
## Introduction
This is Bachelor Thesis in CTU. Author: Dastan Sadyraliyev
### Topic 
The rental of CTU dormitories is no longer sufficient to meet the needs of students, new students are often facing the
problem of discovering where and how to reserve a certain item or who to contact. The rental office has no information
system, everything is handled manually in the old-fashioned way.
The IS will cover a number of areas and a system built on microservices seems like a modern solution.
Gather the requirements on new IS with an emphasis on extensibility, performance, reliability, ease of use and scalability.
Create detailed wireframes for the new solution and validate the models.
Implement the core microservices and test the prototype.

## Getting Started
To set up and start the project, follow these steps:

1. **Clone the repository**
   ```bash
   git clone https://gitlab.fel.cvut.cz/sadyrdas/bachelor-projekt.git
2. **First step: Run Eureka discovery-server**(Wait until it starts, then go to the second step)
   ```bash
   mvn spring-boot:run -pl discovery-server
3. ** Second step: Run Api gateway service**(Wait until it starts, then go to the third step)
   ```bash
   mvn spring-boot:run -pl api-gateway
4. **Third step: Run Account management service**
   ```bash
    mvn spring-boot:run -pl account-management-service
5. **Fourth step: Run Equipment management service**
    ```bash
     mvn spring-boot:run -pl equipment-management-service
6. **Fifth step: Run Equipment reservation service**
    ```bash
     mvn spring-boot:run -pl equipment-reservation-service
## Checking 
Go to Eureka Dashboard http://localhost:8761/ to see registered services. The API and endpoints were manually tested using Postman.
## Wireframes
To check future UI and look flow of app, you can use this link to redirect to detailed wireframes with with an opportunity to set variables in form 
https://www.figma.com/proto/zOSz3TgMIG39Jlugn9frJu/Rentopia-Wireframes?node-id=317-4902&t=oPYTngC14FJye6QD-0&scaling=scale-down&page-id=15%3A2725&starting-point-node-id=317%3A4902

