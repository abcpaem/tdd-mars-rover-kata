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
  - As no Rover's name is provided by the user, the position identifies a Rover, so it will not be allowed to create more than one Rover in the same plateau and position.
  - If there is no other Rover in the same given position, then this new Rover will be automatically registered in the plateau at creation time.
- The third input is about the instructions for moving the Rover, which can be:
  - Turning right (R).
  - Turning left (L).
  - Moving forward (M).
- In the following 3 cases the Rover will stop moving and await for some input from the user, which can be another Rover's Position or program termination:
  - If the moving instruction is not recognised.
  - If there is a risk of collision when the Rover is trying to move forward.
  - If there are no more instructions for moving the Rover.
- As you can see in the diagram above, the program will keep in a loop of moving Rovers until the user decides to stop the navigation.
- A flowchart diagram was chosen instead of a UML Activity diagram because there is no concurrency in the system, and the colourful flowchart diagram is easier to grasp on the eyes of a non-technical stakeholder.  

### Class Diagram
Now it is time to map out the structure of the Mars Rover navigation system by modeling its classes, attributes, operations and relationships between objects, so here is the Class diagram:

<img src="/docs/MarsRoverClassDiagram.png" width="980">

As per the class diagram above, these are the assumptions and considerations:
- The main purpose of the system is to be able to navigate in Mars with a space vehicle, in this case a Mars Rover. This implies that:
  - The navigation area (plateau) and the vehicle need to communicate with each other to be able to navigate securely (avoid collisions) and within the limits of the navigation area, this might look like having a circular dependency, but this doesn't necessarily mean having a circular reference.
  - There could have been a navigation object orchestrating all the vehicle movements but due to the simplicity of the requirements, I just let the vehicle control its own movements.
- There are 2 main abstract classes:
  - *Vehicle*, which can be extended by any type of vehicle, in our case we use a Rover.
  - *Plateau*, which can be extended by any shape of plateau, in our case we use a RectangularZone.
- The *Plateau* class needs to be aware of all the vehicles moving within, so it contains a list of vehicles. It could just have been a list of coordinates of all vehicles positions, but for extendibility purposes (we may need more than coordinates from a vehicle in the future) and simplification, it was implemented this way.
- The *Vehicle* class needs to know the limits of the plateau, so it contains a *PlateauBehaviour*. By using an interface, the circular reference is broken, this way the vehicle cannot access the plateau object itself, but only its behaviour (interface methods).
- There are 2 main Behaviours (interfaces):
  - *VehicleBehaviour*, which exposes the methods needed for a vehicle to move.  
  - *PlateauBehaviour*, which exposes the methods needed to navigate safely within a plateau, and for managing the vehicles moving within.
- The *Position* class is a repository to hold the coordinates and direction of a vehicle. This data is kept separated as a different class (and not inside the Vehicle class) because if at some point the position is measured in different terms, this class could become the base of other children classes inheriting common traits, for example, in the case when we need to manage a position in a 3D space.
- The *Coordinates* class is to simply store the X and Y coordinates for a position. A *Point* class in Java could have been used, but that would have been like trying to kill a fly with a canyon.
- *Direction* enumeration is one of the most interesting objects in the diagram, as you can see, the values have been arranged in a way that a vehicle can face the correct cardinal direction just by going to the next or previous element, so if it is facing NORTH, and it turns right (go to next element), then it will be facing EAST, but if it turns left from NORTH (go to previous element in a circular way), then it will be facing WEST.
- By design, it is not possible to manually set the position of a Rover because the position identifies a Rover.

## Test Driven Development process

In order to develop the solution, this TDD process was followed:

1) Add a test for checking the boundaries of a Rectangular Plateau. See test [here](https://htmlpreview.github.io/?https://github.com/abcpaem/tdd-mars-rover-kata/blob/main/docs/TestResults01.html) or [here](https://htmlview.glitch.me/?https://github.com/abcpaem/tdd-mars-rover-kata/blob/main/docs/TestResults01.html).
2) Add a test for checking Mars Rover location. See test [here](https://htmlpreview.github.io/?https://github.com/abcpaem/tdd-mars-rover-kata/blob/main/docs/TestResults02.html) or [here](https://htmlview.glitch.me/?https://github.com/abcpaem/tdd-mars-rover-kata/blob/main/docs/TestResults02.html).
3) Add a test for checking that Mars Rover can turn right. See test [here](https://htmlpreview.github.io/?https://github.com/abcpaem/tdd-mars-rover-kata/blob/main/docs/TestResults03.html) or [here](https://htmlview.glitch.me/?https://github.com/abcpaem/tdd-mars-rover-kata/blob/main/docs/TestResults03.html).
4) Refactor tests to apply DRY principle. All tests keep passing, see tests [here](https://htmlpreview.github.io/?https://github.com/abcpaem/tdd-mars-rover-kata/blob/main/docs/TestResults04.html) or [here](https://htmlview.glitch.me/?https://github.com/abcpaem/tdd-mars-rover-kata/blob/main/docs/TestResults04.html).
5) Add more tests for all possible outcomes when Mars Rover turns right. See tests [here](https://htmlpreview.github.io/?https://github.com/abcpaem/tdd-mars-rover-kata/blob/main/docs/TestResults05.html) or [here](https://htmlview.glitch.me/?https://github.com/abcpaem/tdd-mars-rover-kata/blob/main/docs/TestResults05.html).
6) Add a test for checking that Mars Rover turns left. See tests [here](https://htmlpreview.github.io/?https://github.com/abcpaem/tdd-mars-rover-kata/blob/main/docs/TestResults06.html) or [here](https://htmlview.glitch.me/?https://github.com/abcpaem/tdd-mars-rover-kata/blob/main/docs/TestResults06.html).
7) Add a test for checking that Mars Rover can move forward. See test [here](https://htmlpreview.github.io/?https://github.com/abcpaem/tdd-mars-rover-kata/blob/main/docs/TestResults07.html) or [here](https://htmlview.glitch.me/?https://github.com/abcpaem/tdd-mars-rover-kata/blob/main/docs/TestResults07.html).
8) Add more tests when Mars Rover moves forward in all directions. See tests [here](https://htmlpreview.github.io/?https://github.com/abcpaem/tdd-mars-rover-kata/blob/main/docs/TestResults08.html) or [here](https://htmlview.glitch.me/?https://github.com/abcpaem/tdd-mars-rover-kata/blob/main/docs/TestResults08.html).
9) Add a test for checking if the Mars Rover is registered automatically in plateau. See test [here](https://htmlpreview.github.io/?https://github.com/abcpaem/tdd-mars-rover-kata/blob/main/docs/TestResults09.html) or [here](https://htmlview.glitch.me/?https://github.com/abcpaem/tdd-mars-rover-kata/blob/main/docs/TestResults09.html).
10) Add a test for checking that no more than one Rover can be created in same plateau and position. See test [here](https://htmlpreview.github.io/?https://github.com/abcpaem/tdd-mars-rover-kata/blob/main/docs/TestResults10.html) or [here](https://htmlview.glitch.me/?https://github.com/abcpaem/tdd-mars-rover-kata/blob/main/docs/TestResults10.html).
11) Add a test for checking that Mars Rover keeps last position when there is a risk of collision trying to move forward. See test [here](https://htmlpreview.github.io/?https://github.com/abcpaem/tdd-mars-rover-kata/blob/main/docs/TestResults11.html) or [here](https://htmlview.glitch.me/?https://github.com/abcpaem/tdd-mars-rover-kata/blob/main/docs/TestResults11.html).
12) Add a test for checking that Mars Rover can make multiple moves. See test [here](https://htmlpreview.github.io/?https://github.com/abcpaem/tdd-mars-rover-kata/blob/main/docs/TestResults12.html) or [here](https://htmlview.glitch.me/?https://github.com/abcpaem/tdd-mars-rover-kata/blob/main/docs/TestResults12.html).

Finally, you can see all the tests passing [here](https://htmlpreview.github.io/?https://github.com/abcpaem/tdd-mars-rover-kata/blob/main/docs/TestResultsAll.html) or [here](https://htmlview.glitch.me/?https://github.com/abcpaem/tdd-mars-rover-kata/blob/main/docs/TestResultsAll.html).

### What's next?:
- Add *main* method to run the solution as a console application.
- Add tests for edge case scenarios.

#### Improvements
- Add a unique name to the Vehicle, so it can help as a unique identifier (it wasn't part of original requirements).
- Add functionality for unregistering Vehicles from Plateau, for cases when the same Rover is transported to a different position by another mean of transport.


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

