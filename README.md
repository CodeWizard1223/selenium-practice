# Selenium Practice Project

This project serves as a personal training ground for practicing automated UI testing using **Selenium WebDriver** with **Java** and **JUnit**.

---

## 🛠 Technologies Used

- Java 17
- Selenium 4
- JUnit 5
- IntelliJ IDEA
- XPath Selectors
- Page Object Model (POM)

---

## 🚀 How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/CodeWizard1223/selenium-practice.git
   ```
2. Open the project in IntelliJ IDEA.
3. Make sure you have ChromeDriver installed and configured.
4. Run the test classes (e.g. `MainTest`, `GoogleTest`).

---

## 📁 Folder Structure (like a real QA automation project)

```
selenium-practice/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── pages/              ← Page Object classes
│   │       └── utils/              ← Test helpers, base classes, drivers
│   └── test/
│       ├── java/
│       │   ├── tests/              ← Classic UI test cases
│       │   ├── jbehave/            ← BDD tests using JBehave
│       │   ├── api/                ← REST API test cases (later)
│       │   └── performance/        ← JMeter or Playwright (optional)
```

---

## 🧭 Study Roadmap (July–September)

| Week | Topic                            | Focus Area |
|------|----------------------------------|------------|
| 1    | Selenium + XPath                 | UI testing basics |
| 2    | Page Object Model + JUnit        | Test architecture |
| 3    | JBehave + Groovy                 | BDD testing |
| 4    | Selenium Grid + Jenkins          | CI/CD & parallel testing |
| 5    | Playwright + REST API            | Alt tool + backend testing |
| 6    | Terraform + Ansible + AWS CLI    | DevOps basics |
| 7    | Liquibase + JMeter + Review      | DB migration & performance |

---

## 🧪 Sample Test Scenarios

- Open Google and verify page title
- Click “Sign in” on demo e-shop
- Try login with invalid credentials
- Fill out a form using XPath
- Practice BDD: Given–When–Then
- Simulate a simple REST call (GET/POST)

---

## 💡 Future Plans

- Add test reports (Allure or HTML)
- Integrate with CI (GitHub Actions or Jenkins)
- Make tests more modular using POM
- Add README badges and documentation

---

## 👩‍💻 Author

Created by S as part of her QA automation learning path.  
Public repo used for practice and progress tracking.
