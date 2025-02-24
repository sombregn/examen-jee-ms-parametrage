# Projet Scolaire - Gestion des Étudiants, Classes et Programmes

Ce projet est une application de gestion scolaire qui permet de gérer les étudiants, les classes, les programmes et les inscriptions. Il est développé avec une architecture microservices utilisant **Spring Boot** pour le backend et **Angular** pour le frontend. Le tout est conteneurisé avec **Docker** et déployé via un pipeline CI/CD avec **GitLab CI/CD**.

---

## 🚀 Prérequis pour démarrer le projet

### 1. Cloner le dépôt
git clone https://gitlab.com/isi-dev/microservices/scolaire-ms.git

### 2. Démarrer les services avec Docker Compose
docker-compose up -d

### 3. Démarrer le projet pour les tests
Backend :
cd tp-parametrage
mvn clean package
