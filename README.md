# TDLSchool Java + Selenium test framework

## Project Description
This project is automated UI testing suite for TDL School using Selenium, TestNG, Extent Reports and WebDriverManager.

### Prerequisites
Ensure you have the following installed on tour system:
- **JDK**
- **Maven**
- **Browser**

### Steps to Set up the Project
1. **Install all Project Dependencies**
Run the following Maven command in terminal root folder:
```bash
mvn clean install
```

## Running Tests
### Using Maven commands

To run all tests, execute the following command:
```bash
mvn clean test
```

To run SMOKE test group:
```bash
mvn clean test -Dgroups=SMOKE
```
To run navigation test class:
```bash
mvn clean test -Dtest=NavigationTest
```
To run test methode:
```bash
mvn clean test -Dtest=test2#navigate
```

# Test Reports for Automated UI Test
## Viewing Report
### Report Generation
After running the automation test, Extent Reports are automatically generated.

### Locating the Report
The generated test reports are stored in the following directory:

```plaintext
reports/