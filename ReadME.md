
# **Application de Gestion des Personnes et Emplois**

## **Configurer la base de données :**
1. Assurez-vous que PostgreSQL est installé et configuré.
2. Créez une base de données :
   ```sql
   CREATE DATABASE gestion_personnes;
   ```
3. Modifiez le fichier `application.properties` dans le backend pour inclure vos informations d'accès à la base :
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/gestion_personnes
   spring.datasource.username=VOTRE_UTILISATEUR
   spring.datasource.password=VOTRE_MOT_DE_PASSE
   spring.jpa.hibernate.ddl-auto=create
   ```

## **Lancer le backend :**
1. Accédez au dossier backend :
   ```bash
   cd backend
   ```
2. Exécutez le serveur Spring Boot :
   ```bash
   mvn spring-boot:run
   ```

## **Lancer le frontend :**
1. Accédez au dossier frontend :
   ```bash
   cd frontend
   ```
2. Installez les dépendances :
   ```bash
   npm install
   ```
3. Démarrez l'application React :
   ```bash
   npm start
   ```

## **Accéder à l'application :**
- **Frontend** : [http://localhost:3000](http://localhost:3000)
- **Swagger Documentation API** : [http://localhost:8080/swagger-ui-custom.html](http://localhost:8080/swagger-ui-custom.html)

---

## **Auto-évaluation**

### **Travail réalisé :**
- Développement complet du backend avec gestion des entités Personne et Emploi.
- Mise en place des validations (âge des personnes, chevauchement des dates des emplois).
- Création d'un frontend simple et fonctionnel avec React.
- Documentation complète de l'API avec Swagger.
- Tests unitaires pour les endpoints principaux.

### **Travail non réalisé :**
- Amélioration de la gestion des erreurs pour des retours plus explicites.
- Travail supplémentaire sur le design et la structuration du frontend.
- Ajout de pagination pour les listes volumineuses.

### **Justification des choix techniques :**
- React a été choisi pour sa simplicité et sa rapidité d'installation, idéale pour un projet de petite taille.
- L'accent a été mis sur les fonctionnalités essentielles, étant donné la taille réduite du projet.

---

## **Captures d'écran**
- **Interface utilisateur**
- ![img_1.png](img_1.png)
- **Swagger API Documentation**
- ![img.png](img.png)

---

## **Rendu du projet :**
- **Code source Frontend** : Lien GitHub
- **Swagger Documentation API** : [http://localhost:8080/swagger-ui-custom.html](http://localhost:8080/swagger-ui-custom.html)
