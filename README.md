# Automated Testing Plan with Selenium

This repository contains the implementation of an automated testing plan developed using Selenium WebDriver. The project was created as part of the automation course offered by Academia QA and adapted for use with the Your Store demo application.

## Project Overview

The main objective of this project is to demonstrate a robust and maintainable test automation framework using Selenium, Java, Maven, Cucumber, and the Page Object Model (POM) design pattern.

The repository includes:
- Test cases automated with Selenium WebDriver.
- Page Object Model structure for reusable and maintainable components.
- Test execution through Cucumber and JUnit.
- Cucumber integration for reports.

## File Structure
```
📦 YourStore-Selenium-POM-TestLab
├── 📂 src/
│   ├── 📂 main/
│   │   └── 📂 java/
│   │       └── 📂 pages/          # Page Object classes
│   ├── 📂 test/
│       ├── 📂 java/
│       │   ├── 📂 stepDefinitions/
│       │   ├── 📂 runners/
│       │   ├── 📂 hooks/
│       │   └── 📂 utils/
│       └── 📂 resources/
│           └── 📂 features/       # Cucumber feature files
├── 📄 pom.xml                     # Maven configuration
├── 📄 serenity.properties         # Serenity BDD configuration
├── 📖 README.md                   # Project documentation
```

## Prerequisites

Ensure you have the following installed:
- Java 17+
- Maven 3.8+
- Chrome, Edge and Firefox browser
- IntelliJ IDEA (recommended for development)

## Getting Started

Clone this repository:
```bash
git clone https://github.com/your-username/YourStore-Selenium-POM-TestLab.git
cd YourStore-Selenium-POM-TestLab
```

To install dependencies and run tests:
```bash
mvn clean test
```

To run tests with a specific browser (e.g., Edge):
```bash
mvn test -Dbrowser=chrome -Dcucumber.options="--tags @your_store_login_feature"
```

Or set the environment variable before running:
```bash
export BROWSER=edge
export SUITE=@your_store_login_feature
mvn test -Dbrowser=$BROWSER -Dcucumber.options="--tags $SUITE"
```

## Running in IntelliJ

To run with custom VM options in IntelliJ:
1. Select your runner
2. Go to `Run > Edit Configurations`.
2. Add VM options: `-Dbrowser=edge`.
3. Run your test.

## Reports

After execution, Cucumber generates detailed HTML reports. Find them in:
```
/target/cucumber-reports.html
```

## Contributing

Feel free to fork the project, open issues, or submit pull requests to enhance the framework.

## License

This project is licensed under the GNU General Public License v3.0.

## Contact

For questions or feedback, please contact:

- **Name:** Juan Miguel Blanco Medina
- **Email:** juanmblancom@gmail.com
- **GitHub:** [https://github.com/juanMBMedina](https://github.com/juanMBMedina)
