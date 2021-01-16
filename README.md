# Design Patterns Final Project

## Synopsis

### Course
- [CCSU F20 CS Design Patterns](https://github.com/CCSU-DesignPatterns-F20/DesignPatternsCourseInfo)

### Team Members
1. [Caleb ABG](https://github.com/CalebABG)
2. [Camilo Espinosa](https://github.com/Camiloesp)
3. [Raymond Farrell](https://github.com/RayTFarrell)

### Goal
- Wanted to be able to compare many sorting algorithms visually
- Have our codebase be flexible enough so that more algorithms could be added / implemented, and at the same time keeping maintainability of the code

### Outcome
- Had to put the visuals (visualizer) on hold. Due to the time window and complexity of our project, we decided to put implementing the design patterns listed below as the top priority

---

## Running the Project

### Prerequisites
1. **Java JDK 8 or Java JRE 8** - Minimum due to use of lambda functions
2. Any text editor or IDE of your choice

#### Editors and IDE's I highly recommend:
1. [IntelliJ IDEA](https://www.jetbrains.com/idea/)
2. [Visual Studio Code](https://code.visualstudio.com/)
3. [Eclipse](https://eclipse.org/)


### Viewing the Test Suite Output
1. Once you've gotten the [prerequisites](#prerequisites) out the way, head over to the ```test``` package inside the ```src``` folder (full path = ```src\test``` )


2. Then just run or debug the ```Test.java``` file to see the output of the different test for our design patterns we implemented

---

## Design Patterns Implemented in Project

- Abstract Factory
- Factory Method
- Iterator
- Memento
- Decorator
- Strategy
- Builder
- Prototype


## Frameworks or Structures Used
- Heavy use of Generics
- Java's Collections framework


## UML Diagrams
- The UML for our project design / structure can be viewed in the ```uml``` folder
- There is a breakdown of the design based on package and by the 3 patterns used in our highlight presentation


## Javadoc Documentation
- The Javadoc's for our project can be viewed in the ```doc``` folder, then opening the ```index.html``` file

---

## Repo Differences
1. This repo does not include the ```main``` package or ```Main.java``` class.
    - Because we didn't get to the visuals, I wanted to highlight our design
    

2. This repo has a commit made to it adjusting the names of the ```ListBuilder``` interface methods, making them shorter and more readable
   

3. This repo adds a helper method in ```Test.java``` for running a test evaluating each of the ```SortingAlgorithmType.java``` enum values. As well making many test method's visibility ```public``` instead of private for documentation's sake