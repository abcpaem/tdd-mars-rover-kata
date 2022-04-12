package clan.techreturners.mars;

import clan.techreturners.mars.land.*;
import clan.techreturners.mars.location.*;
import clan.techreturners.mars.transport.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MarsRoverTests {
    @Test
    void checkRectangularPlateauBoundaries() {
        // Arrange
        Coordinate edgeCoordinates = new Coordinate(5, 5);
        Plateau plateau = new RectangularPlateau(edgeCoordinates);

        // Act
        String boundaries = plateau.getBoundaries();

        // Assert
        assertAll(
                () -> assertTrue(boundaries.contains("RectangularPlateau")),
                () -> assertTrue(boundaries.contains("lower-left: 0,0")),
                () -> assertTrue(boundaries.contains("upper-right: 5,5"))
        );
    }

    @Test
    void checkMarsRoverLocation() {
        // Arrange
        Coordinate edgeCoordinates = new Coordinate(5, 5);
        Plateau plateau = new RectangularPlateau(edgeCoordinates);
        Position position = new Position(new Coordinate(1, 2), Direction.NORTH);
        Vehicle rover = new Rover(plateau, position);

        // Act
        String location = rover.getLocation();

        // Assert
        assertAll(
                () -> assertTrue(location.contains("Rover")),
                () -> assertTrue(location.contains("1 2 N"))
        );
    }

    @Test
    void checkRoverTurnsRight() {
        // Arrange
        Coordinate edgeCoordinates = new Coordinate(5, 5);
        Plateau plateau = new RectangularPlateau(edgeCoordinates);
        Position position = new Position(new Coordinate(1, 2), Direction.NORTH);
        Vehicle rover = new Rover(plateau, position);

        // Act
        rover.turnRight();

        // Assert
        assertEquals(Direction.EAST, rover.getDirection());
    }
}
