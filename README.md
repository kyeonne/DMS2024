# Re-implementation of 1942 (the Video Game)
Farah Hadhirah Jaafar 20516003 <br>
<a href="https://github.com/kyeonne/DMS2024">GitHub depository</a>

## Table of Contents
1. [Installation and Compilation](#installation)
2. [Features](#features)
3. [Java Classes](#classes)
4. [Unexpected Problems](#problems)

<h2 id="installation"> Installation and Compilation </h2>

### Prerequisites
Method 1: Have Java (minimum JDK 19) and Maven installed on the system.
Method 2: Have IntelliJ IDEA or any other compatible IDEs installed.

### Installation Steps 
#### Method 1
1. Download the zip file containing the code.
2. Extract the zip file.
3. Open the command terminal by typing "cmd" into the app search.
4. Navigate to the directory containing the repository.
```bash
# Example 
cd C:\Users\Farah\DMS2024
```
5. Compile and run the file using the command:
```bash
mvn clean javafx:run
```
#### Method 2
1. Clone the repository by creating a fork or using Git Bash.
2. Open the cloned project in your IDE.
3. Navigate to Main
4. Run the file.

<hr>
<h2 id="features"> Features </h2>

### Implemented and Working Properly
- Working kill counter
- New level
- Additional backgrounds
- display icons
### Implemented and Not Working Properly
- Shielding mechanic: The appearance and removal of shields are not working as expected
### Not Implemented
- Menus such as start menus and pause menus
- Game restart function

<h2 id="classes"> Java Classes </h2>
### New Classes
- LevelThree: Added a new level to the game
- KillCDisplay: New container that shows the kill count icon and incrementing number
### Modified Classes
- ActiveActorDestructible
<h2 id="problems"> Unexpected Problems </h2>
