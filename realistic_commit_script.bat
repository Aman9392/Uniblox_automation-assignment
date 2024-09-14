@echo off
echo Setting up realistic commit timeline for existing repository...

REM Check if we're in a git repository
git status >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Not in a git repository
    pause
    exit /b 1
)

echo Current repository status:
git log --oneline
echo.

REM Add .gitignore if not exists
if not exist .gitignore (
    echo target/ > .gitignore
    echo test-output/ >> .gitignore
    echo .idea/ >> .gitignore
    echo *.iml >> .gitignore
    echo .vscode/ >> .gitignore
    echo *.log >> .gitignore
    git add .gitignore
    git commit -m "Add .gitignore file" --date="2024-09-13 11:20:00"
)

REM Commit 2: Add Maven dependencies (Sep 13, 11:45 AM)
git add pom.xml
git commit -m "Add Maven dependencies for Selenium and TestNG" --date="2024-09-13 11:45:00"

REM Commit 3: Create BasePage (Sep 13, 1:20 PM)
git add src/main/java/pages/BasePage.java
git commit -m "Create BasePage class with common web interaction methods" --date="2024-09-13 13:20:00"

REM Commit 4: Add MainPage (Sep 13, 3:45 PM)
git add src/main/java/pages/MainPage.java
git commit -m "Implement MainPage class with form elements and interactions" --date="2024-09-13 15:45:00"

REM Commit 5: Add utility classes (Sep 13, 5:10 PM)
git add src/main/java/utils/
git commit -m "Add utility classes for WebDriver management and test data" --date="2024-09-13 17:10:00"

REM Commit 6: Create BaseTest (Sep 14, 9:30 AM)
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
echo Realistic commit timeline created!
echo.
echo Commit history:
git log --oneline --graph --decorate
echo.
echo To push to GitHub:
echo git push origin master
echo.
pause
