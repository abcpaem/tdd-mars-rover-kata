package clan.techreturners.mars.land;

import clan.techreturners.mars.location.Coordinate;
import clan.techreturners.mars.transport.Vehicle;

import java.util.ArrayList;
import java.util.List;

public abstract class Plateau implements PlateauBehaviour {
    private final String BOUNDARIES = "I'm a %s with lower-left: %s and upper-right: %s";
    protected List<Vehicle> vehicles;
    protected Coordinate origin;
    protected Coordinate edge;

    public Plateau(Coordinate edgeCoordinates) {
        origin = new Coordinate(0, 0);
        edge = edgeCoordinates;
        vehicles = new ArrayList<>();
    }

    @Override
    public void registerVehicle(Vehicle vehicle) {
        if (getVehicle(vehicle.getCoordinate()) == null) {
            vehicles.add(vehicle);
        }
    }

    @Override
    public void unregisterVehicle(Vehicle vehicle) {
        if (getVehicle(vehicle.getCoordinate()) != null) {
            vehicles.remove(vehicle);
        }
    }

    @Override
    public Vehicle getVehicle(Coordinate coordinate) {
        return vehicles.stream()
                .filter(x -> x.getCoordinate().equals(coordinate))
                .findAny()
                .orElse(null);
    }

    @Override
    public boolean isCollision(Coordinate coordinate) {
        return getVehicle(coordinate) != null;
    }

    public String getBoundaries() {
        return String.format(BOUNDARIES, this.getClass().getSimpleName(), origin.toString(), edge.toString());
    }
}
