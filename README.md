# SauceDemo
The final project involved performing both manual and automated tests on the eCommerce and shopping site www.saucedemo.com. Manually to fully understand the project and the client's needs, testing critical functions like login, adding items to the cart, and checkout. Following the completion of this stage, I started creating automated testing using Selenium, POM and DDT.
</br></br>
![primer](https://github.com/kostantin-bozovic/FinalProject/assets/144264798/84da8aa0-1555-49e4-b47e-696f93b12a48)

## Dependencies
* Run on Windows 11 Pro
* IDE for this project is Intellij Idea Community Edition 2023.2
* Browser needed is Chrome

## Instalation

Open terminal in IDE and git clone the repository
```
git clone https://github.com/kostantin-bozovic/FinalProject.git
```
* Java version 8 (Update 381)
* Apache Maven 5.2.4

## Executing program
To run Headless tests, replace "false" with "true" command, inside @BeforeClass methode on 41 line.<br>
```
headlessTest(false);
```
To run all test in same time, right-click on "testng.xml" file and click "Run"

## Framework Walkthorugh
Packages:
* Base - Contains classes used through the app
* Pages - Contains classes for each page
* Tests - Contains test classes
  
Files:
* pom.xml - Contains all dependencies used in project
* TestData.xlsx - Excel file used to read some data for DDT
* testng.xml - Basic xml file to run all test classes
* .gitignore - File that contains all items that are not pushed
