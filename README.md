# 🏀 NBA Zone - Manager & Trade Machine

A comprehensive Full-stack web application designed for managing an NBA player and team database, integrated with an advanced trade simulation module. Data is modeled after real league statistics and parameters from NBA 2K26.

## Key Features

### Advanced Player Database Management
* **Complete Data Model:** Each player is defined by attributes such as position, overall rating, nationality, age, and club affiliation.
* **Contract Management:** Tracks detailed financial data including contract value, options (Player/Team), and length.
* **Dynamic Filtering:** Sort and search players by any technical or financial parameter.
* **CRUD Module:** Full support for adding new players to the database via an interface.

### Team Management System
* **Team Dashboard:** Overview of all 30 teams organized by division and conference.
* **Roster Analysis:** Detailed team views featuring:
    * Average team overall, coaching staff data, and historical achievements (championship titles, playoff appearances).
    * **Pro Finance:** Total salary calculation and estimation of tax thresholds (Luxury Tax, First/Second Apron).

### Advanced Trade Machine (Business Logic)
* **Multi-Team Trades:** Support for transactions between two or three teams simultaneously.
* **Asset Management:** Ability to include both players and draft picks in trades.
* **Tier System:** Trade classification on a scale of 1–4 (from star players to supporting pieces).
* **Data Consistency:** Executing a trade automatically updates database records, reassigning players and adjusting team budgets.

### Transaction Log
* A register of all completed transfers with the ability to filter historical roster moves by their importance tier.

## 🛠 Tech Stack
* **Backend:** Java 17, Spring Boot 3.x, Spring Data JPA
* **Database:** PostgreSQL
* **Frontend:** Thymeleaf, HTML5, CSS3, JavaScript
* **Build Tool:** Maven

## Installation & Running
1. **Database:**
   - Create a **PostgreSQL** database named `NBA Stats`.
   - Import the structure and data from the `nba_setup.sql` file located in the root directory.
   - You can do this in **pgAdmin** or via terminal: `psql -U postgres -d "NBA Stats" -f nba_setup.sql`

2. **Configuration:**
   - Update `src/main/resources/application.properties` with your local database password (spring.datasource.password).
   - Ensure the database URL line is set to `spring.datasource.url=jdbc:postgresql://localhost:5432/NBA Stats`

3. **Running the App:**
   - Open the project in **IntelliJ IDEA** and run the `NbaZoneApplication` class.
   - Access the application at: `http://localhost:8080`

Watch a walkthrough of all the features on YouTube: https://youtu.be/0Wx8vJTcwAQ
