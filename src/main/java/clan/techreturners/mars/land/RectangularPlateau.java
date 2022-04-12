package clan.techreturners.mars.land;

import clan.techreturners.mars.location.*;

public class RectangularPlateau extends Plateau {
    public RectangularPlateau(Coordinate edgeCoordinates) {
        super(edgeCoordinates);
    }

    @Override
    public Position getForwardPosition(Position position) {
        int limitX =  edge.getX() + 1;
        int limitY =  edge.getY() + 1;
        Coordinate coord = new Coordinate(position.getCoordinate());
        switch (position.getDirection()) {
            case NORTH -> coord.setY((coord.getY() + 1) % limitY);
            case EAST -> coord.setX((coord.getX() + 1) % limitX);
            case SOUTH -> coord.setY((coord.getY() - 1 + limitY) % limitY);
            case WEST -> coord.setX((coord.getX() - 1 + limitX) % limitX);
        }
        return new Position(coord, position.getDirection());
    }
}
