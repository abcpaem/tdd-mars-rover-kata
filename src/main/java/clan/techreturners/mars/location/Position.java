package clan.techreturners.mars.location;

import clan.techreturners.mars.transport.VehicleBehaviour;

public class Position {
    private Coordinate coordinate;
    private Direction direction;

    public Position(Coordinate coordinate, Direction direction) {
        this.coordinate = coordinate;
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return coordinate.getX() + " " + coordinate.getY() + " " + direction.getLetter();
    }
}
