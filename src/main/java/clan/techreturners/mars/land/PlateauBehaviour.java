package clan.techreturners.mars.land;

import clan.techreturners.mars.location.*;
import clan.techreturners.mars.transport.Vehicle;

public interface PlateauBehaviour {
    Position getForwardPosition(Position position);
    Vehicle getVehicle(Coordinate positionCoord);
    void registerVehicle(Vehicle vehicle);
}
