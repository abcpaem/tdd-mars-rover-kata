package clan.techreturners.mars.location;

public class Position {
    private Coordinate coordinate;
    private Direction direction;

    public Position(Coordinate coordinate, Direction direction) {
        this.coordinate = coordinate;
        this.direction = direction;
    }

    @Override
    public String toString() {
        return coordinate.getX() + " " + coordinate.getY() + " " + direction.getLetter();
    }
}
