# Projet Scolaire - Gestion des Étudiants, Classes et Programmes

Ce projet est une application de gestion scolaire qui permet de gérer les étudiants, les classes, les programmes et les inscriptions. Il est développé avec une architecture microservices utilisant **Spring Boot** pour le backend et **Angular** pour le frontend. Le tout est conteneurisé avec **Docker**.

---

## 🚀 Prérequis pour démarrer le projet

### 1. Cloner le dépôt
git clone https://gitlab.com/isi-dev/microservices/scolaire-ms.git

### 2. Démarrer les services avec Docker Compose
- **docker-compose up -d**

### 3. Démarrer le projet pour les tests
## Backend :
- **cd tp-parametrage**
- **mvn clean package**
### Frontend :
Assurez-vous que Angular CLI est installé, puis exécutez :
-**ng serve**

### 🛠 Technologies utilisées

### Backend :
Spring Boot 3.4.2
OpenAPI Documentation (Swagger UI) pour une documentation interactive.
PostgreSQL et MySQL pour la gestion des bases de données.
Keycloak 24+ pour l'authentification et l'autorisation.

### Frontend :
Angular 19+
Bootstrap et CSS3 pour le design.
ngx-toastr pour les notifications visuelles.

### Infrastructure :
Docker pour la conteneurisation.
