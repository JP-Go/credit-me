# Running this project

## Requirements

- Docker (+ docker compose plugin)
- Java 17+
- Kotlin 1.7+

## Steps

1. Create a .docker directory with the following files

```sql
# .docker/setup.sql
CREATE DATABASE IF NOT EXISTS credit_me;
CREATE USER 'some-user'@'%' IDENTIFIED BY 'some-password';
GRANT ALL ON credit_me.* TO 'some-user'@'%';
FLUSH PRIVILEGES;
```
```env
MYSQL_ROOT_PASSWORD=some-password
```

2. Run `docker compose up -d` to boot up the container
3. Setup the enviroment variables
```bash
DB_HOST=localhost # (or other if docker-compose network was modified)
DB_USERNAME=some-user
DB_PASSWORD=some-password
DB_DATABASE=credit_me
```

4. Run `./grandlew bootRun` to start the development server

Server should run on port 8080
