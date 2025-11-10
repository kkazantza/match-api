# ⚽ Match API

## Overview
**Match API** is a RESTful web service built with **Java 17**, **Spring Boot**, and **PostgreSQL**, designed to manage sports matches and their corresponding betting odds.  
It supports full **CRUD operations** for both matches and match odds through a clean REST API.

---

## Features
- Manage **sports matches** (create, read, update, delete)
- Manage **match odds** associated with each match
- Built using **Java 17 (Oracle JDK)**, **Spring Boot**, **JPA**, and **PostgreSQL**
- Ready for **Docker Compose** deployment

## API Entities

### Match
| Field | Type | Description |
|--------|------|-------------|
| id | Long | Unique identifier |
| description | String | Match description (e.g., "OSFP-PAO") |
| matchDate | LocalDate | Date of the match |
| matchTime | LocalTime | Time of the match |
| teamA | String | Team A name |
| teamB | String | Team B name |
| sport | Enum | Sport type (FOOTBALL, BASKETBALL) |

### MatchOdds
| Field | Type | Description |
|--------|------|-------------|
| id | Long | Unique identifier |
| matchId | Long | Associated match ID |
| specifier | String | Betting specifier (e.g., "1", "X", "2") |
| odd | Double | Betting odd |

---

## REST Endpoints

### Matches
| Method | Endpoint | Description |
|--------|-----------|-------------|
| `GET` | `/api/matches` | Get all matches |
| `GET` | `/api/matches/{id}` | Get match by ID |
| `POST` | `/api/matches` | Create new match |
| `PUT` | `/api/matches/{id}` | Update match |
| `DELETE` | `/api/matches/{id}` | Delete match |

### Match Odds
| Method | Endpoint | Description |
|--------|-----------|-------------|
| `GET` | `/api/odds` | Get all odds |
| `GET` | `/api/odds/{id}` | Get odds by ID |
| `POST` | `/api/odds` | Create new odds |
| `PUT` | `/api/odds/{id}` | Update odds |
| `DELETE` | `/api/odds/{id}` | Delete odds |

---

## Run with Docker

1. **Build and start containers**
```
docker-compose up --build
```

2. **Access API**
```
http://localhost:8080/api/matches
http://localhost:8080/api/odds
```

3. **Database**
```
Host: localhost
Port: 5432
Username: postgres
Password: postgres
Database: matchdb
```

## API Documentation

The REST API is documented using OpenAPI/Swagger. You can:

- View the interactive Swagger UI (requires the app running locally):

```
http://localhost:8080/swagger-ui/index.html
```

- Or use the exported OpenAPI file in the `docs/` folder:

[OpenAPI YAML](docs/openapi.yaml)

You can import it into Postman or view it using Swagger UI.


## Postman Collection

You can import the API endpoints directly into Postman using the provided collection.

[📥 Download Match API Postman Collection](./postman/matchapi.postman_collection.json)

### Import instructions
1. Open **Postman**.
2. Click **Import** → **File**.
3. Select `MatchAPI.postman_collection.json`.
4. Test endpoints like `/api/matches` and `/api/odds`.

> 💡 Make sure your app is running on [http://localhost:8080](http://localhost:8080).
>    Set the variable baseURL equal to http://localhost:8080