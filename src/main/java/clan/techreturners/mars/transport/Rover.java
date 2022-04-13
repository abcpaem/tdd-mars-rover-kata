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

    @Override
    public void turnLeft() {
        this.position.setDirection(position.getDirection().turnLeft());
    }

    @Override
    public void moveForward() {
        Position newPosition = plateau.getForwardPosition(this.position);
        if (!plateau.isCollision(newPosition.getCoordinate())) {
            this.position = newPosition;
        }
    }

    @Override
    public void move(String instructions) {
        char[] moves = instructions.toUpperCase().toCharArray();
        for (Character move : moves) {
            switch (move) {
                case 'L' -> this.turnLeft();
                case 'R' -> this.turnRight();
                case 'M' -> this.moveForward();
            }
        }
    }
}
