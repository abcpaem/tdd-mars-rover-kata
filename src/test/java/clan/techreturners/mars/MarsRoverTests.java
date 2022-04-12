package clan.techreturners.mars;

import clan.techreturners.mars.land.*;
import clan.techreturners.mars.location.*;
import clan.techreturners.mars.transport.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    @Test
    void checkRoverTurnsRight() {
        // Arrange
        Direction expectedDirection = Direction.EAST;

        // Act
        rover.turnRight();

        // Assert
        assertEquals(expectedDirection, rover.getDirection());
    }
}
