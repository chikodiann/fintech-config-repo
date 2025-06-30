# Fintech Middleware

This repository contains the **complete microservice-based middleware** for a digital-only fintech company.

Built with **Spring Boot 3.x**, **Java 17+**, and **Spring Cloud**.

---

## 📦 Microservices in this Monorepo

- **account-service**: Manages customer accounts (create, fetch, balances).
- **api-gateway**: Routes and secures external requests with centralized routing rules.
- **bills-service**: Supports bill payments (airtime, electricity, etc.).
- **config-server**: Centralized configuration for all services (Spring Cloud Config).
- **customer-service**: Handles customer onboarding, BVN/NIN validation, login, JWT auth.
- **dashboard-service**: Returns customer dashboard data (accounts, recent transactions).
- **service-discovery**: Eureka server for service registration and discovery.
- **transfer-service**: Supports bank transfers (list banks, submit transfers).
- **fintech-parent-pom**: Shared Maven parent for dependency and plugin management.

---

## 🚀 Features

✅ Microservice architecture with Spring Boot 3.x  
✅ Service discovery with Eureka  
✅ Centralized configuration with Spring Cloud Config  
✅ API Gateway with Spring Cloud Gateway  
✅ JWT-based authentication  
✅ Redis-backed token storage and blacklisting  
✅ Integration with BVN/NIN validation APIs (mock)  
✅ Account creation after successful onboarding  
✅ Bills payments and transfer endpoints  
✅ Dashboard aggregation service

---

## ⚙️ Architecture Diagram
[Client]
|
[API Gateway]
|

Customer | Account | Bills | Transfers | Dashboard
Service Service Service Service Service
Copy
Edit
|
[Service Discovery (Eureka)]
|
[Config Server]

## 📂 Folder Structure

├── account-service
├── api-gateway
├── bills-service
├── config-server
├── customer-service
├── dashboard-service
├── service-discovery
├── transfer-service
├── fintech-parent-pom
└── README.md

## 🧭 How to Run Locally

1️⃣ Clone this repo:

git clone https://github.com/chikodiann/fintech-config-repo.git

cd fintech-middleware

Copy
Edit

2️⃣ Make sure you have:

✅ Java 17+  
✅ Maven  

3️⃣ Start **Config Server** first:

cd config-server
mvn spring-boot:run

4️⃣ Start **Service Discovery (Eureka)**:

cd ../service-discovery
mvn spring-boot:run

5️⃣ Start **API Gateway**:

cd ../api-gateway
mvn spring-boot:run

6️⃣ Start **Other Services** in any order:

account-service/
bills-service/
customer-service/
dashboard-service/
transfer-service/

Each with:
mvn spring-boot:run

✅ Services will auto-register with Eureka.  
✅ Config Server provides centralized properties.  

## 💻 Environment / Config

- Config Server reads from external Git repo:
https://github.com/chikodiann/fintech-config-repo.git

- Service ports can be customized in application.yml or bootstrap.yml.
- Eureka default zone: `http://localhost:2000/eureka`.

---

## 🔐 Authentication

- JWT issued by Customer Service on login
- Stored in Redis for validation
- API Gateway forwards tokens
- Secured endpoints validate with custom JWT filters

---

## 🛠️ Technologies

- Java 17+
- Spring Boot 3.x
- Spring Cloud (Config, Eureka, Gateway)
- Spring Security
- Redis (for token store)
- PostgreSQL (for persistent data)
- Maven

---

## 🗺️ API Coverage

- Customer Onboarding (BVN/NIN validation)
- Login
- Automatic Account Creation
- Fetch Accounts
- Bills Categories & Payment
- Bank List & Transfer
- Customer Dashboard (Accounts + Transactions)

---

## 📜 Postman Collection

A full Postman collection with all request/response examples is included separately:

✅ `FintechMiddleware.postman_collection.json`

✅ Includes:

- Auth token storage in environment
- Pre-request and test scripts
- Chained requests

---

## ✅ Contributors

- [Your Name] (your-email@example.com)

---

## 📄 License

MIT License (Chikodinaka Ann Anyanwu)


