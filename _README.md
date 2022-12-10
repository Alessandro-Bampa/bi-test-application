## Running the application in docker containers

```shell script
 docker-compose build
```

```shell script
 docker-compose up -d
```

stop containers

```shell script
 docker-compose down
```

If you want apply the modifications you must reconpile the app and build docker compose

```shell script
 ./mvnw package -DskipTests
```