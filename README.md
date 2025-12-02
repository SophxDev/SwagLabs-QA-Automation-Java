# 🚀 SwagLabs E-Commerce Automation Framework

Este proyecto es un framework de automatización de pruebas robusto y escalable diseñado para la tienda online [SwagLabs](https://www.saucedemo.com/).

Está construido siguiendo las mejores prácticas de la industria, utilizando el patrón de diseño **Page Object Model (POM)** con una capa de negocio (**Flows**) y pruebas impulsadas por datos (**Data-Driven Testing**).

---

## 🏗️ Arquitectura del Proyecto

El framework se organiza en capas para facilitar el mantenimiento y la escalabilidad. Separamos la lógica de los tests, los flujos de negocio, la interacción con la página y los datos de prueba.

---
## 📁 Estructura de Carpetas Detallada
```Bash

SwagLabs-QA-Automation-UI-Java/
├── src/
│   └── test/
│       ├── java/com/jaqueline/qa/
│       │   ├── base/               # Motor del Framework (Setup/Teardown driver)
│       │   │   ├── BasePage.java
│       │   │   └── BaseTest.java
│       │   ├── flows/              # Capa de Negocio (Business Logic Layer)
│       │   │   └── PurchaseFlow.java
│       │   ├── pages/              # Page Objects (Locators y Acciones UI)
│       │   │   ├── CartPage.java
│       │   │   ├── CheckoutInfoPage.java
│       │   │   ├── CheckoutOverviewPage.java
│       │   │   ├── ConfirmationPage.java
│       │   │   ├── InventoryPage.java
│       │   │   └── LoginPage.java
│       │   ├── tests/              # Scripts de Prueba (TestNG)
│       │   │   ├── auth/
│       │   │   │   └── LoginTest.java
│       │   │   ├── checkout/
│       │   │   │   ├── CartTest.java
│       │   │   │   └── PurchaseTest.java
│       │   │   └── products/
│       │   │       └── InventoryTest.java
│       │   └── utils/              # Herramientas y Modelos de Datos (POJOs)
│       │       ├── CheckoutData.java
│       │       ├── ConfigReader.java
│       │       ├── DriverManager.java
│       │       ├── InventoryData.java
│       │       ├── JsonReader.java
│       │       ├── LoginData.java
│       │       ├── LoginErrorData.java
│       │       └── WebActions.java
│       └── resources/              # Recursos Externos
│           ├── data/               # Datos de Prueba (Data-Driven JSONs)
│           │   ├── checkout.json
│           │   ├── credentials.json
│           │   ├── inventory.json
│           │   └── negative_credentials.json
│           ├── allure.properties   # Configuración de Reportes
│           ├── config.properties   # Variables Globales (URL, Browser)
│           └── testng.xml          # Suite de Ejecución
├── .gitignore
├── pom.xml

```
---

### 🔑 Características Principales

* **Page Object Model (POM):** Separación clara entre los elementos web y la lógica de prueba.
* **Capa de Negocio (Flows):** Reutilización de pasos comunes (ej. Login, Proceso de Compra) para tests más legibles.
* **Data-Driven Testing:** Uso de archivos externos (JSON y Properties) para manejar datos de prueba y configuraciones, evitando datos "quemados" (hardcoded).
* **Manejo Robusto de Elementos:** Clase `WebActions` personalizada para manejar esperas explícitas y reportes.
* **Reportes Profesionales:** Integración con Allure para reportes gráficos y detallados.

---

## 🛠️ Stack Tecnológico

* **Lenguaje:** Java 17
* **Core Automation:** Selenium WebDriver
* **Test Runner:** TestNG
* **Build Tool:** Maven
* **Data Handling:** Jackson (JSON), Lombok (POJOs)
* **Reporting:** Allure Framework
* **Patrón de Diseño:** Page Object Model (POM)

---

## 📋 Requisitos Previos

Para ejecutar este proyecto localmente, necesitas tener instalado:

1.  Java JDK 17. 
2.  Maven.
3.  Allure Commandline (Opcional pero recomendado) 

---

## 🚀 Cómo Ejecutar los Tests

Clona el repositorio y navega a la carpeta raíz del proyecto.

### 1️⃣ Ejecutar toda la suite de pruebas

Usa Maven para limpiar el proyecto y ejecutar los tests definidos en `testng.xml`:

```bash
mvn clean test
```

### 2️⃣ Ejecutar tests específicos (Opcional)
Puedes usar etiquetas de TestNG (si las configuraste) o ejecutar por línea de comandos:

```Bash
mvn test -Dtest=PurchaseTest
```
---

## 📊 Reportes de Ejecución
Después de la ejecución, los resultados crudos se guardan en allure-results. Para visualizar el reporte HTML interactivo, ejecuta:

```Bash

allure serve target/allure-results
```
o para correr el test y levantar allure usa:

```bash
mvn clean test allure:serve 
```

Esto abrirá automáticamente el navegador con un reporte como este:
<img width="1905" height="932" alt="image" src="https://github.com/user-attachments/assets/aa2a3e09-639d-43d6-ba92-169bd39c36ec" />
<img width="1906" height="920" alt="image" src="https://github.com/user-attachments/assets/fef85a28-4cd1-4124-b8c3-f4f53764104f" />


---
## 👩‍💻 Autora

Jaqueline Espino 

(QA Automation Engineer | Software developer)

💼 Linkedin: https://www.linkedin.com/in/jaquelineespino/

🔗 GitHub: https://github.com/SophxDev
