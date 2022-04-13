package clan.techreturners.mars.transport;

public interface VehicleBehaviour {
    void turnRight();
    void turnLeft();
    String moveForward();
    String move(String instructions);
}
