package clan.techreturners.mars;

import clan.techreturners.mars.land.*;
import clan.techreturners.mars.location.Coordinate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MarsRoverTests {
    @Test
    void checkRectangularPlateauBoundaries() {
        // Arrange
        Coordinate edgeCoordinates = new Coordinate(5,5);
        Plateau plateau = new RectangularPlateau(edgeCoordinates);

        // Act
        String boundaries = plateau.getBoundaries();

        // Assert
        Assertions.assertAll(
                () -> assertTrue(boundaries.contains("RectangularPlateau")),
                () -> assertTrue(boundaries.contains("lower-left: 0,0")),
                () -> assertTrue(boundaries.contains("upper-right: 5,5"))
        );
    }
}
