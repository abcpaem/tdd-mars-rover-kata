package clan.techreturners.mars.land;

import clan.techreturners.mars.location.Coordinate;

public abstract class Plateau implements PlateauBehaviour {
    private final String BOUNDARIES = "I'm a %s with lower-left: %s and upper-right: %s";
    protected Coordinate origin;
    protected Coordinate edge;

    public Plateau(Coordinate edgeCoordinates) {
        origin = new Coordinate(0, 0);
        edge = edgeCoordinates;
    }

    public String getBoundaries() {
        return String.format(BOUNDARIES, this.getClass().getSimpleName(), origin.toString(), edge.toString());
    }
}
