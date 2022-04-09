# TDD Mars Rover kata
Test Driven Development exercise for Mars Rover.

### What is a kata?

A Kata in martial arts means “a system of individual training exercises”. Just like in martial arts, coding also requires consistent practise to hone in the skill.

### What is the goal?

As part of a team that explores Mars by sending remotely controlled vehicles to the surface of the planet. Develop an API that translates the commands sent from earth to instructions that are understood by the rover.

### Instructions

Read more [here](/docs/InstructionsMarsRoverKata.pdf) about the instructions to achieve this task.

**Considerations:**
- The implementation approach is free to receive the input into the program by any possible way, like:
  - Feeding input values into unit tests.
  - Via a console application.
  - Via a file, etc.
- Test-Driven Development (TDD) must be applied to test-drive the solution.
- Provide production-quality code, this means to have thought carefully about the code design and that the code is clean and
well-tested.
- Provide good unit test coverage and all unit tests passing.

---
## Design

First of all, it is good to have a visual representation of the sequence of steps and decisions needed to perform the Mars Rover navigation, so here is a Flowchart diagram:

<img src="/docs/MarsRoverFlowchartDiagram.png" width="980">

---
## How to run the tests
- cd to the project root folder in the command line
- ``mvn test``

## Technology
This project was built using:
- Java version 17.0.2
- JUnit 5.8.2 for unit testing
- Apache Maven 3.8.5 as project manager
- Community Edition for the IntelliJ 2021.3.2 development environment.

