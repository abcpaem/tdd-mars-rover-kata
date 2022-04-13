package clan.techreturners.mars;

import clan.techreturners.mars.land.*;
import clan.techreturners.mars.location.*;
import clan.techreturners.mars.transport.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class MarsRoverTests {
    Coordinate edgeCoord = new Coordinate(5, 5);
    Plateau plateau = new RectangularPlateau(edgeCoord);
    Coordinate roverCoord;
    Position roverPosition;
    Direction roverDirection;
    Vehicle rover;
    Coordinate newCoord;

    @BeforeEach
    void init() {
        roverCoord = new Coordinate(2, 3);
        roverDirection = Direction.NORTH;
        roverPosition = new Position(roverCoord, roverDirection);
        rover = new Rover(plateau, roverPosition);
        newCoord = new Coordinate(1, 2);
    }

    @Test
    void checkRectangularPlateauBoundaries() {
        // Arrange
        String expectedEdge = String.format("upper-right: %s,%s", edgeCoord.getX(), edgeCoord.getY());

        // Act
        String boundaries = plateau.getBoundaries();

        // Assert
        assertAll(
                () -> assertTrue(boundaries.contains("RectangularPlateau")),
                () -> assertTrue(boundaries.contains("lower-left: 0,0")),
                () -> assertTrue(boundaries.contains(expectedEdge))
        );
    }

    @Test
    void checkMarsRoverLocation() {
        // Arrange
        String expectedPosition = String.format("%s %s %s", roverCoord.getX(), roverCoord.getY(), roverDirection.getLetter());

        // Act
        String location = rover.getLocation();

        // Assert
        assertAll(
                () -> assertTrue(location.contains("Rover")),
                () -> assertTrue(location.contains(expectedPosition))
        );
    }

    @ParameterizedTest(name = "{index}) When Rover is facing {0}, after turning right will face {1} ")
    @CsvSource(delimiterString = "->", textBlock = """
            NORTH -> EAST
            EAST  -> SOUTH
            SOUTH -> WEST
            WEST  -> NORTH
            """)
    void checkRoverTurnsRight(Direction direction, Direction expectedDirection) {
        // Arrange
        Position newPosition = new Position(newCoord, direction);
        rover = new Rover(plateau, newPosition);

        // Act
        rover.turnRight();

        // Assert
        assertEquals(expectedDirection, rover.getDirection());
    }

    @ParameterizedTest(name = "{index}) When Rover is facing {0}, after turning left will face {1} ")
    @CsvSource(delimiterString = "->", textBlock = """
            NORTH -> WEST
            WEST  -> SOUTH
            SOUTH -> EAST
            EAST  -> NORTH
            """)
    void checkRoverTurnsLeft(Direction direction, Direction expectedDirection) {
        // Arrange
        Position newPosition = new Position(newCoord, direction);
        rover = new Rover(plateau, newPosition);

        // Act
        rover.turnLeft();

        // Assert
        assertEquals(expectedDirection, rover.getDirection());
    }

    @ParameterizedTest(name = "{index}) When Rover moves {1} {0} time(s) forward, its position will be {2}")
    @CsvFileSource(resources = "/moveForwardData.csv", numLinesToSkip = 1)
    void checkRoverMovesForward(int timesMovingForward, Direction direction, String expectedPosition) {
        // Arrange
        Position newPosition = new Position(newCoord, direction);
        rover = new Rover(plateau, newPosition);

        // Act
        IntStream.range(0, timesMovingForward).forEach(i -> rover.moveForward());

        // Assert
        assertEquals(expectedPosition, rover.getPosition().toString());
    }

    @Test
    void checkIfRoverIsRegisteredInPlateau() {
        // Act
        Vehicle registeredRover = plateau.getVehicle(roverCoord);

        // Assert
        assertEquals(registeredRover, rover);
    }

    @Test
    void checkThatNoMoreThanOneRoverCanBeCreatedInSamePlateauAndPosition() {
        // Arrange
        String expectedMessage = "already been registered";

        // Act
        Exception exception = assertThrows(VehicleRegistrationException.class, () -> {
            new Rover(plateau, roverPosition);
        });

        // Assert
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    void checkThatRoverKeepsLastPositionWhenThereIsARiskOfCollision() {
        // Arrange
        Position newPosition = new Position(newCoord, Direction.EAST);
        Rover newRover = new Rover(plateau, newPosition);
        String expectedPosition = "2 2 N";

        // Act
        newRover.moveForward();
        newRover.turnLeft();
        newRover.moveForward(); // Here there is a collision with the rover at 2 3 N

        // Assert
        assertEquals(expectedPosition, newRover.getPosition().toString());
    }

    @ParameterizedTest(name = "{index}) A Rover at {0} moving {1} will end up being at {2}")
    @MethodSource
    void checkThatRoverCanMakeMultipleMoves(Position position, String instructions, String expectedPosition) {
        // Arrange
        Plateau newPlateau = new RectangularPlateau(new Coordinate(5, 5));
        Vehicle newRover = new Rover(newPlateau, position);

        // Act
        newRover.move(instructions);

        // Assert
        assertEquals(expectedPosition, newRover.getPosition().toString());
    }

    private static Stream<Arguments> checkThatRoverCanMakeMultipleMoves() {
        return Stream.of(
                Arguments.of(new Position(new Coordinate(1, 2), Direction.NORTH), "LMLMLMLMM", "1 3 N"),
                Arguments.of(new Position(new Coordinate(3, 3), Direction.EAST), "MMRMMRMRRM", "5 1 E")
        );
    }
}
