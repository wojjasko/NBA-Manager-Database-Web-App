# 🏀 NBA Zone - Manager & Trade Machine

Kompleksowa aplikacja Full-stack służąca do zarządzania bazą danych zawodników i drużyn NBA, zintegrowana z zaawansowanym modułem symulacji wymian transferowych. Dane wzorowane są na realnych statystykach ligowych oraz parametrach z gry NBA 2K26.

## 🌟 Kluczowe Funkcjonalności

### 📊 Zaawansowane Zarządzanie Bazą Zawodników
* **Pełny model danych:** Każdy zawodnik opisany jest przez parametry: pozycja, overall, narodowość, wiek oraz przynależność klubowa.
* **Zarządzanie Kontraktami:** System uwzględnia szczegółowe dane finansowe – wartość kontraktu, opcje (Player/Team) oraz długość zobowiązania.
* **Dynamiczne Filtrowanie:** Możliwość sortowania i wyszukiwania graczy według dowolnego parametru technicznego lub finansowego.
* **Moduł CRUD:** Pełna obsługa dodawania nowych zawodników do bazy danych poprzez intuicyjny interfejs.

### 🏢 System Zarządzania Drużynami
* **Dashboard Drużyn:** Przegląd wszystkich 30 zespołów z podziałem na dywizje i konferencje.
* **Analiza Składów:** Szczegółowy widok drużyny obejmujący:
    * Średni Overall zespołu, dane trenera oraz sukcesy historyczne (tytuły, występy w Playoffs).
    * **Finanse Pro:** Obliczanie Total Salary oraz estymacja progów podatkowych (**Luxury Tax**, **First/Second Apron**).

### 🔄 Zaawansowany Trade Machine (Logika Biznesowa)
* **Multi-Team Trades:** Obsługa wymian pomiędzy dwoma lub trzema zespołami jednocześnie.
* **Asset Management:** Możliwość dołączania do wymian nie tylko zawodników, ale również draft picków.
* **Tier System:** Klasyfikacja wymian w skali 1-4 (od kluczowych graczy po zawodników drugoplanowych).
* **Data Consistency:** Wykonanie wymiany automatycznie aktualizuje rekordy w bazie danych, zmieniając przynależność zawodników i korygując budżety drużyn.

### 📜 Transaction Log
* Rejestr wszystkich przeprowadzonych transferów z możliwością filtrowania historycznych ruchów kadrowych według ich poziomu istotności (Tier).

## 🛠 Stack Techniczny
* **Backend:** Java 17, Spring Boot 3.x, Spring Data JPA
* **Database:** MySQL (Relacyjna struktura danych)
* **Frontend:** Thymeleaf, HTML5, CSS3, JavaScript
* **Build Tool:** Maven

## 🚀 Instalacja i Uruchomienie
1. **Baza danych:**
   - Stwórz bazę danych w PostgreSQL o nazwie `NBA Stats`.
   - Zaimportuj strukturę i dane z pliku `nba_setup.sql` (dostępny w głównym folderze).
   - Możesz to zrobić w pgAdmin lub komendą: `psql -U postgres -d "NBA Stats" -f nba_setup.sql`

2. **Konfiguracja:**
   - W pliku `src/main/resources/application.properties` zmień hasło do bazy danych (`spring.datasource.password`) na Twoje lokalne.

3. **Uruchomienie:**
   - Otwórz projekt w IntelliJ i uruchom klasę `NbaZoneApplication`.
   - Aplikacja będzie dostępna pod adresem: `http://localhost:8080`
