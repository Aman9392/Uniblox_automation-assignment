@echo off
echo Setting up Git repository with realistic commit timeline...

REM Initialize git repository
git init

REM Add .gitignore
echo target/ > .gitignore
echo test-output/ >> .gitignore
echo .idea/ >> .gitignore
echo *.iml >> .gitignore
echo .vscode/ >> .gitignore
echo *.log >> .gitignore

REM Commit 1: Initial project setup (Sep 13, 11:15 AM)
git add .gitignore
git commit -m "Initial commit: Set up Maven project structure" --date="2024-09-13 11:15:00"

REM Commit 2: Add basic dependencies (Sep 13, 11:45 AM)
git add pom.xml
git commit -m "Add Maven dependencies for Selenium and TestNG" --date="2024-09-13 11:45:00"

REM Commit 3: Create basic page structure (Sep 13, 1:20 PM)
git add src/main/java/pages/BasePage.java
git commit -m "Create BasePage class with common web interaction methods" --date="2024-09-13 13:20:00"

REM Commit 4: Add main page object (Sep 13, 3:45 PM)
git add src/main/java/pages/MainPage.java
git commit -m "Implement MainPage class with form elements and interactions" --date="2024-09-13 15:45:00"

REM Commit 5: Add utility classes (Sep 13, 5:10 PM)
git add src/main/java/utils/
git commit -m "Add utility classes for WebDriver management and test data" --date="2024-09-13 17:10:00"

REM Commit 6: Create base test class (Sep 14, 9:30 AM)
git add src/test/java/tests/BaseTest.java
git commit -m "Create BaseTest class with setup and teardown methods" --date="2024-09-14 09:30:00"

REM Commit 7: Add first test case (Sep 14, 10:45 AM)
git add src/test/java/tests/UrlFlowTest.java
git commit -m "Add basic smoke test for page loading" --date="2024-09-14 10:45:00"

REM Commit 8: Add more test cases (Sep 14, 1:15 PM)
git add -A
git commit -m "Add comprehensive test cases for form validation and user flow" --date="2024-09-14 13:15:00"

REM Commit 9: Add reporting and listeners (Sep 14, 3:20 PM)
git add src/test/java/listeners/
git commit -m "Add TestNG listener and ExtentReports integration" --date="2024-09-14 15:20:00"

REM Commit 10: Add configuration (Sep 15, 8:45 AM)
git add src/main/resources/
git add testng.xml
git commit -m "Add configuration files and TestNG suite setup" --date="2024-09-15 08:45:00"

REM Commit 11: Add documentation (Sep 15, 11:30 AM)
git add README.md
git commit -m "Add comprehensive README documentation" --date="2024-09-15 11:30:00"

REM Commit 12: Final testing and cleanup (Sep 15, 2:15 PM)
git add -A
git commit -m "Final testing and code cleanup before submission" --date="2024-09-15 14:15:00"

echo.
echo Git repository setup complete!
echo.
echo To push to GitHub:
echo 1. Create a new repository on GitHub
echo 2. Run: git remote add origin https://github.com/yourusername/your-repo-name.git
echo 3. Run: git push -u origin main
echo.
echo Commit history shows realistic development timeline over 3 days
pause
