# Containerization Project — Spring Boot Microservices + Docker (MySQL + MongoDB)

A **Spring Boot microservices** project that demonstrates containerization and service-to-service communication using **Docker** and **Docker Compose**.

The system is composed of **5 Java microservices** + **2 database containers**:

- **auth-service** (port **8090**) — validates admin credentials (`admin/admin`)
- **data-access-service** (port **8080**) — inserts student data into **MySQL** (requires admin auth)
- **analytics-service** (port **8070**) — reads MySQL and computes **avg / count / max / min**
- **add-to-mongo-service** (port **8060**) — fetches analytics and stores it in **MongoDB**
- **show-data-service** (port **8050**) — validates admin and returns analytics data from MongoDB
- **mysql** (port **3306**) — database `school` with table `student`
- **mongo_db** (port **27017**) — stores the latest Analytics object

> Educational project: focuses on Dockerization + microservices communication using `RestTemplate` and JDBC.

---

## High-Level Flow

1) **Admin validation**  
`auth-service` checks if username/password equals `admin/admin` and returns `"accepted"` or `"access denied"`.

2) **Insert student data**  
`data-access-service` calls `auth-service` first, then writes into MySQL using `JdbcTemplate`.

3) **Compute analytics**  
`analytics-service` runs:
```sql
select avg(mark), count(mark), max(mark), min(mark) from student;
```

4) **Sync analytics to MongoDB**  
`add-to-mongo-service` fetches Analytics from `analytics-service`, clears old Mongo data, then saves the new one.

5) **Show analytics**  
`show-data-service` validates admin and returns analytics data stored in MongoDB.

---

## Ports

| Service | Port |
|--------|------|
| auth-service | 8090 |
| data-access-service | 8080 |
| analytics-service | 8070 |
| add-to-mongo-service | 8060 |
| show-data-service | 8050 |
| MySQL | 3306 |
| MongoDB | 27017 |

---

## Prerequisites

- Java 8 (Dockerfiles use `openjdk:8`)
- Maven
- Docker + Docker Compose

Check:
```bash
java -version
mvn -version
docker --version
docker compose version
```

---

## Database Schema (MySQL)

Database: `school`  
Table: `student`

```sql
use school;

CREATE TABLE `student` (
  `id` int unsigned NOT NULL,
  `nam` varchar(30) NOT NULL,
  `mark` int NOT NULL,
  PRIMARY KEY (`id`)
);

insert into student(id,nam,mark) VALUES(1,"Mohammad",99);
insert into student(id,nam,mark) VALUES(2,"abod",95);
insert into student(id,nam,mark) VALUES(3,"ahmad",90);
insert into student(id,nam,mark) VALUES(4,"aya",88);
insert into student(id,nam,mark) VALUES(5,"rashed",93);
insert into student(id,nam,mark) VALUES(6,"hanan",77);
insert into student(id,nam,mark) VALUES(7,"tala",75);
```

---

## About `host.docker.internal`

The services call each other using URLs like:

- `http://host.docker.internal:8090/...`
- `jdbc:mysql://host.docker.internal:3306/school`
- `mongodb://host.docker.internal:27017/school`

`host.docker.internal` is “like localhost but from inside containers”.

✅ If you keep the code as-is, make sure `host.docker.internal` works on your OS.  
On some Linux setups, you might need to add host mapping, or (better) switch to Docker Compose service names.

---

## Run the Project (Docker Compose)

Start everything with Docker Compose:

```bash
docker compose -p containerization-project up --build
```

### Typical workflow

1) Build jars (from the repo root):
```bash
mvn clean package
```

2) Build Docker images (example):
```bash
docker build -f Dockerfile -t analytics .
```

3) Start all services:
```bash
docker compose -p containerization-project up --build
```

> If images are already built, you can remove `--build`.

---

## Run Services Individually (without Compose)

Example:
```bash
docker run -p 8070:8070 analytics
```

Repeat for other services by mapping their ports.

---

## API Endpoints (Quick Test)

### 1) Auth (validate admin)
```bash
curl -i http://localhost:8090/admin/admin
```

### 2) Insert student data (requires admin)
```bash
curl -X POST http://localhost:8080/admin/admin/99/Ahmad/91
```

### 3) Compute analytics
```bash
curl http://localhost:8070/analytics
```

### 4) Send analytics to MongoDB
```bash
curl http://localhost:8060/senddata
```

### 5) Show data (reads from MongoDB, requires admin)
```bash
curl http://localhost:8050/admin/admin/showdata
```

---

## Scheduling (fixedRate)

The project uses Spring scheduling like:

- `@EnableScheduling`
- `@Scheduled(fixedRateString = "${fixedRate}")`
- `fixedRate=60000`

Meaning scheduled tasks may run every **60 seconds** depending on configuration.

---

## Dockerfile Pattern

Example Dockerfile pattern:

```dockerfile
FROM openjdk:8
EXPOSE 8080
ADD target/data-access.jar data-access.jar
CMD ["java","-jar","data-access.jar"]
```

Each service follows the same idea with a different jar name and port.

---

## Security Notes

- Credentials are hardcoded for learning purposes (`admin/admin` and DB credentials).
- If you publish this repo, replace secrets with environment variables and a `.env` file.

---

## Suggested GitHub Topics

`spring-boot`, `microservices`, `docker`, `docker-compose`, `mysql`, `mongodb`, `resttemplate`, `jdbc`

---

## Author

Mohammad Alsheikh  
Malialsheikh2001@gmail.com
