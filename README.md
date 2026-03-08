# SWAGLAB Automation

Selenium WebDriver test automation framework for SauceLabs SwagLabs e-commerce website using TestNG and Java 21.

## Project Overview

This project contains 10 automated test cases covering the core functionalities of the SwagLabs application:
- User authentication
- Product browsing and sorting
- Shopping cart operations
- Checkout process

## Tech Stack

- **Java**: 21
- **Selenium WebDriver**: 4.27.0
- **TestNG**: 7.10.2
- **Maven**: Build and dependency management
- **WebDriverManager**: Automatic browser driver management
- **Design Pattern**: Page Object Model (POM)

## Project Structure

```
SWAGLAB-automation/
├── src/
│   ├── main/java/com/swaglabs/
│   │   ├── pages/              # Page Object classes
│   │   │   ├── BasePage.java
│   │   │   ├── LoginPage.java
│   │   │   ├── ProductsPage.java
│   │   │   ├── CartPage.java
│   │   │   └── CheckoutPage.java
│   │   └── utils/              # Utility classes
│   │       ├── DriverManager.java
│   │       └── TestUtils.java
│   └── test/
│       ├── java/com/swaglabs/tests/
│       │   ├── BaseTest.java
│       │   ├── LoginTest.java     # 3 test cases
│       │   ├── ProductTest.java   # 2 test cases
│       │   ├── CartTest.java      # 3 test cases
│       │   └── CheckoutTest.java  # 2 test cases
│       └── resources/
│           └── testng.xml
├── reports/
│   └── screenshots/
└── pom.xml
```

## Test Cases Summary

| Test ID | Test Name | Description |
|---------|-----------|-------------|
| TC-001 | testValidLogin | Verify successful login with valid credentials |
| TC-002 | testInvalidLogin | Verify login fails with invalid credentials |
| TC-003 | testLockedOutUser | Verify locked out user cannot login |
| TC-004 | testProductsDisplayed | Verify products are displayed on the page |
| TC-005 | testSortByPrice | Verify products can be sorted by price |
| TC-006 | testAddToCart | Verify adding product to cart |
| TC-007 | testViewCart | Verify viewing cart contents |
| TC-008 | testRemoveFromCart | Verify removing item from cart |
| TC-009 | testCheckoutValidation | Verify checkout information validation |
| TC-010 | testCompletePurchase | Verify complete purchase flow |

## Prerequisites

- Java 21 or higher
- Maven 3.6 or higher
- Chrome/Firefox/Edge browser installed

## Installation

1. Clone or download the project
2. Navigate to project directory:
   ```bash
   cd SWAGLAB-automation
   ```

## Running Tests

### Run all tests:
```bash
mvn clean test
```

### Run specific test class:
```bash
mvn test -Dtest=LoginTest
mvn test -Dtest=ProductTest
mvn test -Dtest=CartTest
mvn test -Dtest=CheckoutTest
```

### Run with specific browser:
```bash
mvn test -Dbrowser=chrome    # Default
mvn test -Dbrowser=firefox
mvn test -Dbrowser=edge
```

## Test Reports

After test execution, reports are generated in:
- **HTML Report**: `target/surefire-reports/index.html`
- **Emailable Report**: `target/surefire-reports/emailable-report.html`
- **XML Report**: `target/surefire-reports/testng-results.xml`

## Screenshots

Screenshots are automatically captured for failed tests and saved in:
- `reports/screenshots/`

## Test Credentials

- **Valid User**: `standard_user`
- **Password**: `secret_sauce`
- **Test URL**: https://www.saucedemo.com/

## Features

✅ Page Object Model design pattern  
✅ Headless browser execution  
✅ Automatic screenshot capture on failure  
✅ Thread-safe WebDriver management  
✅ Comprehensive test reporting  
✅ Multiple browser support  
✅ Clean and maintainable code structure  

## Author

Created on: March 9, 2026
Created By: Seena Thomas