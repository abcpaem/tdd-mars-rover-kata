package clan.techreturners.mars.transport;

import clan.techreturners.mars.land.PlateauBehaviour;
import clan.techreturners.mars.location.*;

public abstract class Vehicle implements VehicleBehaviour {
    private final String LOCATION_MESSAGE = "I'm a Mars %s at position %s";
    private final String REGISTRATION_EXCEPTION_MESSAGE = "A %s has already been registered at position %s";
    private final String COLLISION_MESSAGE = "ALERT! There is a potential collision with a %s at %s";
    protected Position position;
    protected PlateauBehaviour plateau;

    public Vehicle(PlateauBehaviour plateau, Position position) {
        if (plateau.isCollision(position.getCoordinate())) {
            throw new VehicleRegistrationException(String.format(REGISTRATION_EXCEPTION_MESSAGE, this.getClass().getSimpleName(), position));
        }
        this.position = position;
        this.plateau = plateau;
        plateau.registerVehicle(this);
    }

    public Position getPosition() {
        return position;
    }

    public Coordinate getCoordinate(){
        return position.getCoordinate();
    }

    public Direction getDirection() {
        return position.getDirection();
    }

    protected String getCollisionMessage(Position position) {
        return String.format(COLLISION_MESSAGE, this.getClass().getSimpleName(), position.toString());
    }

    public String getLocation() {
        return String.format(LOCATION_MESSAGE, this.getClass().getSimpleName(), position.toString());
    }
}
