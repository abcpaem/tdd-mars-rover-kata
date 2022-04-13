package clan.techreturners.mars.transport;

public interface VehicleBehaviour {
    void turnRight();
    void turnLeft();
    void moveForward();
    void move(String instructions);
}
