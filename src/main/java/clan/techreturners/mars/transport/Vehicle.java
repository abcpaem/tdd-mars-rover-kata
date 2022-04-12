package clan.techreturners.mars.transport;

import clan.techreturners.mars.land.PlateauBehaviour;
import clan.techreturners.mars.location.Position;

public abstract class Vehicle {
    private final String LOCATION_MESSAGE = "I'm a Mars %s at position %s";
    protected Position position;
    protected PlateauBehaviour plateau;

    public Vehicle(PlateauBehaviour plateau, Position position) {
        this.position = position;
        this.plateau = plateau;
    }

    public String getLocation() {
        return String.format(LOCATION_MESSAGE, this.getClass().getSimpleName(), position.toString());
    }
}
