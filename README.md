# SeleniumFrameworkBank 🏦

A modular **Page Object Model** Selenium automation framework using **Java**, **TestNG**, **Log4j**, and **ExtentReports**. Designed to showcase professional-quality test automation practices, featuring:

- Structured repository with clean separation of concerns
- Dynamic, run‑specific and day‑wise logging via **Log4j**
- Interactive, timestamped **ExtentReports** with failure screenshots

---

## 🧩 Project Structure

SeleniumFrameworkBank/
├── src/
│ ├── main/java/com/crm/qa/
│ │ └── util/LogUtil.java ← custom logger with folder structure
│ └── test/java/com/crm/qa/
│ ├── pages/ ← Page Object Model classes
│ └── testcases/ ← TestNG test classes
│ └── test/resources/
│ ├── testng.xml
│ ├── testng_sanity.xml
│ └── log4j.properties ← Log4j configuration for per‑run logs
├── .gitignore
├── pom.xml
└── README.md


---

## 🚀 Run & Generate Reports

1. **Clone & import as Maven project**  
   ```bash
   git clone https://github.com/harshadaP97/SeleniumFrameworkBank.git
   cd SeleniumFrameworkBank
   mvn clean test
   
Check logs — runs are saved under 
Logs_<today>/Log_<timestamp>/testlog_…

Check reports — ExtentReports generated at
test-output/Reports/

📊 Sample Extent Report & Screenshot
Get a professional summary of test results — performance, pass/fail stats, and failure traces with screenshots:
💡 Example:
Scenario	Outcome	
Valid login test	✅ PASS
Invalid login test	❌ FAIL
Check the sample in sample_img folder

🛠 Logging via Log4j
LogUtil.logInfo("Navigating to login page");
LogUtil.logError("Login failed for invalid credentials");
Logs include:
Precise class name & line number
Daily & run-wise folder separation
Relevant warnings, errors, and debug traceability
Check the sample in sample_log folder. Sample Log4j output showing successful execution of core test cases (registration, login, open account, logout).


👩‍💻 About
Harshada Patil — QA Engineer | MSc in Applied Computer Science & AI

🚀 Build robust, maintainable automation frameworks and delight your team with professional reports and logs!
---
### ⚠️ Disclaimer  
This project is part of my personal QA portfolio, created to demonstrate my skills and experience.  
Please do not reuse or distribute without permission.  
**Authored by Harshada Patil**





