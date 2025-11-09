# ⚽ Match API

## Overview
**Match API** is a RESTful web service built with **Java 21**, **Spring Boot**, and **PostgreSQL**, designed to manage sports matches and their corresponding betting odds.  
It supports full **CRUD operations** for both matches and match odds through a clean REST API.

---

## Features
- Manage **sports matches** (create, read, update, delete)
- Manage **match odds** associated with each match
- Built using **Java 21 (Oracle JDK)**, **Spring Boot**, **JPA**, and **PostgreSQL**
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
