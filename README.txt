# 🏀 NBA Zone - Manager & Trade Machine

A comprehensive Full-stack web application for managing an NBA player and team database, integrated with an advanced trade simulation module based on NBA 2K26.

## Key Features

### Advanced Player Database Management
- **Complete Data Model:** Attributes like position, overall rating, nationality, age, and club affiliation.
- **Contract Management:** Tracks contract values, options, and duration.
- **Dynamic Filtering:** Sort and search players by technical or financial parameters.
- **CRUD Module:** Add new players to the database via an intuitive interface.

### Team Management System
- **Team Dashboard:** Overview of all 30 teams organized by division and conference.
- **Roster Analysis:** Detailed team views with overall ratings, coaching data, and financial thresholds (Luxury Tax, Aprons).

### Advanced Trade Machine
- **Multi-Team Trades:** Transactions between two or three teams simultaneously.
- **Asset Management:** Include both players and draft picks in trades.
- **Tier System:** Trade classification on a scale of 1–4.
- **Data Consistency:** Executing a trade automatically updates database records and budgets.

### Transaction Log
- A register of all completed transfers with filtering options.

## 🛠 Tech Stack
- **Backend:** Java 17, Spring Boot 3.x, Spring Data JPA
- **Database:** PostgreSQL
- **Frontend:** Thymeleaf, HTML5, CSS3, JavaScript
- **Build Tool:** Maven

## Installation & Running
1. **Database:** Create a PostgreSQL database named `NBA Stats` and import `nba_setup.sql`.
2. **Configuration:** Update `src/main/resources/application.properties` with your local database password.
3. **Running the App:** Open the project in IntelliJ IDEA, run `NbaZoneApplication`, and access `http://localhost:8080`.

Watch a walkthrough of all the features on YouTube: https://youtu.be/0Wx8vJTcwAQ
