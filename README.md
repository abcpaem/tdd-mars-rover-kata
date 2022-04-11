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

As per the flowchart diagram above, these are the assumptions and considerations:
- There are 3 types of inputs from the user:
  - Plateau edge coordinates.
  - Rover's position.
  - Rover's moving instructions.
- The first input must always be the plateau edge coordinates, as this will set up the limits of the plateau where the Rovers will be able to move.
- After having the plateau edge, the next input must be the position of a Rover in that plateau. Here we need to consider the following:
  - A plateau can have multiple Rovers moving around, but only one will move at a time.
  - We need to keep track of the Rovers in the plateau to avoid collisions.
  - As no Rover's name is provided by the user, we will consider that any other Rover being already in the plateau at the same given position is the same Rover.
  - If there is no other Rover in the same given position, then this new Rover will be registered in the plateau.
- The third input is about the instructions for moving the Rover, which can be:
  - Turning right (R).
  - Turning left (L).
  - Moving forward (M).
- In the following 3 cases we will stop moving the Rover and await for some input from the user, which can be another Rover's Position or program termination:
  - If the moving instruction is not recognised.
  - If there is a risk of collision when the Rover is moving. If this case then the Rover will be moved back to its previous position.
  - If there are no more instructions for moving the Rover.
- As you can see in the diagram above, the program will keep in a loop of moving Rovers until the user decides to stop the navigation.  

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

