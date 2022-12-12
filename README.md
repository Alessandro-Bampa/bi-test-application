# Java Test Application

#### That is a simple web application which contains following features:
- Persistent data which can be modified and read.
- HTTP REST API for accessing the data by clients.
- Expiration of old data

To build this application, I used the Java framework `Quarkus` and `MongoDB` as the database. \
I used docker-compose for build the images and run the containers of App and Database. 

For your convenience, when I start the containers I populate the database with sample data `./data/mongo-init.js` , \
so every time you start the containers the database will be cleaned and initialized with that data.


#### API :

- In the `swagger_ui` folder you can find the `index.htm` that contains the auto-generated documentation using OpenApi standard. \
The API endpoints, calls and response schemas are defined there.

#### CACHE :

- Data caching is provided by MongoDB that keeps most recently used data in RAM;
- Otherwise, I could have used an in-memory database like Redis to implement a caching system;

#### ITEM EXPIRATION :

- When I populate the database with the sample data `./data/mongo-init.js`, I also insert a TTL index in the `updated` field, \
which allows me not only to improve query performance, but also to decide how soon the document should expire based on the update date.
- I put 600 seconds of TTL


### Running the application in docker containers

### Prerequisite: `docker` & `docker-compose`

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
 ./mvnw package
```

If you want run the App in dev mode without docker:

- You must remember to replace in the application.properties the db connection string with `mongodb://localhost:27017/bi_test_db`
```shell script
 ./mvnw clean package quarkus:dev
```
