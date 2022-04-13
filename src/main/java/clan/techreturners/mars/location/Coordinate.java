package clan.techreturners.mars.location;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate(Coordinate coordinate) {
        this.x = coordinate.x;
        this.y = coordinate.y;
    }

    @Override
    public String toString() {
        return x + "," + y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate o1 = (Coordinate) o;
        return x == o1.x && y == o1.y;
    }
}
