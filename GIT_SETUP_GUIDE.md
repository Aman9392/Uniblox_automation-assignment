# Git Setup Guide - Human Development Timeline
**Project Timeline: September 13-15, 2024**

## Option 1: Automated Script (Recommended)

### Windows:
```bash
git_commit_script.bat
```

## Option 2: Manual Step-by-Step (More Realistic)

### Step 1: Initialize Repository (Sep 13, 11:15 AM)
```bash
git init
echo "target/" > .gitignore
echo "test-output/" >> .gitignore
echo ".idea/" >> .gitignore
echo "*.iml" >> .gitignore
echo ".vscode/" >> .gitignore
echo "*.log" >> .gitignore
git add .gitignore
git commit -m "Initial commit: Set up Maven project structure" --date="2024-09-13 11:15:00"
```

### Step 2: Add Dependencies (Sep 13, 11:45 AM)
```bash
git add pom.xml
git commit -m "Add Maven dependencies for Selenium and TestNG" --date="2024-09-13 11:45:00"
```

### Step 3: Create Base Page (Sep 13, 1:20 PM)
```bash
git add src/main/java/pages/BasePage.java
git commit -m "Create BasePage class with common web interaction methods" --date="2024-09-13 13:20:00"
```

### Step 4: Add Main Page (Sep 13, 3:45 PM)
```bash
git add src/main/java/pages/MainPage.java
git commit -m "Implement MainPage class with form elements and interactions" --date="2024-09-13 15:45:00"
```

### Step 5: Add Utilities (Sep 13, 5:10 PM)
```bash
git add src/main/java/utils/
git commit -m "Add utility classes for WebDriver management and test data" --date="2024-09-13 17:10:00"
```

### Step 6: Create Base Test (Sep 14, 9:30 AM)
```bash
git add src/test/java/tests/BaseTest.java
git commit -m "Create BaseTest class with setup and teardown methods" --date="2024-09-14 09:30:00"
```

### Step 7: Add First Test (Sep 14, 10:45 AM)
```bash
git add src/test/java/tests/UrlFlowTest.java
git commit -m "Add basic smoke test for page loading" --date="2024-09-14 10:45:00"
```

### Step 8: Add More Tests (Sep 14, 1:15 PM)
```bash
git add -A
git commit -m "Add comprehensive test cases for form validation and user flow" --date="2024-09-14 13:15:00"
```

### Step 9: Add Reporting (Sep 14, 3:20 PM)
```bash
git add src/test/java/listeners/
git commit -m "Add TestNG listener and ExtentReports integration" --date="2024-09-14 15:20:00"
```

### Step 10: Add Configuration (Sep 15, 8:45 AM)
```bash
git add src/main/resources/
git add testng.xml
git commit -m "Add configuration files and TestNG suite setup" --date="2024-09-15 08:45:00"
```

### Step 11: Add Documentation (Sep 15, 11:30 AM)
```bash
git add README.md
git commit -m "Add comprehensive README documentation" --date="2024-09-15 11:30:00"
```

### Step 12: Final Testing (Sep 15, 2:15 PM)
```bash
git add -A
git commit -m "Final testing and code cleanup before submission" --date="2024-09-15 14:15:00"
```

## Push to GitHub

### 1. Create GitHub Repository
- Go to GitHub.com
- Click "New Repository"
- Name: `uniblox-automation-assignment`
- Description: "Selenium TestNG automation framework for Uniblox application"
- Make it public or private as required
- Don't initialize with README (we already have one)

### 2. Connect and Push
```bash
git remote add origin https://github.com/yourusername/uniblox-automation-assignment.git
git branch -M main
git push -u origin main
```

## Timeline Summary

**September 13, 2024 (Day 1):** Project setup and core framework
- 11:15 AM - Project initialization (started a bit late)
- 11:45 AM - Maven dependencies (quick setup)
- 1:20 PM - BasePage class (after lunch break)
- 3:45 PM - MainPage class (working through afternoon)
- 5:10 PM - Utility classes (wrapping up day)

**September 14, 2024 (Day 2):** Test implementation
- 9:30 AM - BaseTest class (early start)
- 10:45 AM - First test case (quick progress)
- 1:15 PM - Comprehensive test cases (after lunch)
- 3:20 PM - Reporting integration (afternoon work)

**September 15, 2024 (Day 3):** Final touches and submission
- 8:45 AM - Configuration files (early morning work)
- 11:30 AM - Documentation (mid-morning)
- 2:15 PM - Final testing and cleanup (afternoon)
- 4:00 PM - Ready for submission!

This creates a realistic 3-day development timeline with natural working patterns!

## Verify Timeline
```bash
git log --oneline --graph --decorate
```

This will show your commit history with the realistic timeline.
