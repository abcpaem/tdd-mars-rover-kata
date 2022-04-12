package clan.techreturners.mars;

import clan.techreturners.mars.land.*;
import clan.techreturners.mars.location.*;
import clan.techreturners.mars.transport.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class MarsRoverTests {
    Coordinate edgeCoordinates;
    Coordinate positionCoord;
    Direction direction;
    Plateau plateau;
    Position position;
    Vehicle rover;

    @BeforeEach
    void init() {
        edgeCoordinates = new Coordinate(5, 5);
        positionCoord = new Coordinate(1, 2);
        direction = Direction.NORTH;
        plateau = new RectangularPlateau(edgeCoordinates);
        position = new Position(positionCoord, direction);
        rover = new Rover(plateau, position);
    }

    @Test
    void checkRectangularPlateauBoundaries() {
        // Arrange
        String expectedEdge = String.format("upper-right: %s,%s", edgeCoordinates.getX(), edgeCoordinates.getY());

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
        String expectedPosition = String.format("%s %s %s", positionCoord.getX(), positionCoord.getY(), direction.getLetter());

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
        position.setDirection(direction);
        rover = new Rover(plateau, position);

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
        position.setDirection(direction);
        rover = new Rover(plateau, position);

        // Act
        rover.turnLeft();

        // Assert
        assertEquals(expectedDirection, rover.getDirection());
    }

    @ParameterizedTest(name = "{index}) When Rover moves {1} {0} time(s) forward, its position will be {2}")
    @CsvFileSource(resources = "/moveForwardData.csv", numLinesToSkip = 1)
    void checkRoverMovesForward(int timesMovingForward, Direction direction, String expectedPosition) {
        // Arrange
        position.setDirection(direction);
        rover = new Rover(plateau, position);

        // Act
        IntStream.range(0, timesMovingForward).forEach(i -> rover.moveForward());

        // Assert
        assertEquals(expectedPosition, rover.getPosition().toString());
    }

    @Test
    void checkIfRoverIsRegisteredInPlateau() {
        // Act
        Vehicle registeredRover = plateau.getVehicle(positionCoord);

        // Assert
        assertEquals(registeredRover, rover);
    }
}
