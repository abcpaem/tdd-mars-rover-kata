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
        return values[(this.ordinal() + 1) % values.length];
    }
}
