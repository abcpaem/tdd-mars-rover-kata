package clan.techreturners.mars.transport;

import clan.techreturners.mars.land.PlateauBehaviour;
import clan.techreturners.mars.location.Position;

public class Rover extends Vehicle {
    public Rover(PlateauBehaviour plateau, Position position) {
        super(plateau, position);
    }

    @Override
    public void turnRight() {
        this.position.setDirection(position.getDirection().turnRight());
    }
}
