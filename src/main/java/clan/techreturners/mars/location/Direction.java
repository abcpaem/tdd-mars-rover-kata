package clan.techreturners.mars.location;

public enum Direction {
    NORTH('N'),
    EAST('E'),
    SOUTH('S'),
    WEST('W');

    private Character letter;
    private static final Direction[] values = values();

    Direction(char letter) {
        this.letter = letter;
    }

    public Character getLetter() {
        return letter;
    }

    public Direction turnRight() {
        return values[(ordinal() + 1) % values.length];
    }

    public Direction turnLeft() {
        return values[(ordinal() - 1  + values.length) % values.length];
    }
}
