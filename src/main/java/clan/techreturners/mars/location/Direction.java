package clan.techreturners.mars.location;

public enum Direction {
    NORTH('N'),
    EAST('E'),
    SOUTH('S'),
    WEST('W');

    private Character letter;

    Direction(char letter) {
        this.letter = letter;
    }

    public Character getLetter() {
        return letter;
    }
}
