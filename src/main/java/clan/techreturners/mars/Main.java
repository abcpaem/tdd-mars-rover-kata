package clan.techreturners.mars;

import clan.techreturners.mars.land.*;
import clan.techreturners.mars.location.*;
import clan.techreturners.mars.transport.Rover;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        String input;

        System.out.println("Please enter the upper-right coordinates of the Plateau (e.g. 5 5):");
        input = reader.nextLine();
        if (input.isBlank()) return;

        int[] coord = Arrays.stream(input.trim().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        Plateau plateau = new RectangularZone(new Coordinate(coord[0], coord[1]));
        Rover rover;
        boolean firstTime = true;

        while (!input.isBlank()) {
            System.out.println("Enter the Rover's position" + (firstTime ? " (e.g. 1 2 N):" : ":"));

            input = reader.nextLine();
            if (input.isBlank()) return;

            String[] sPos = input.trim().split("\\s+");
            Coordinate rCoord = new Coordinate(Integer.parseInt(sPos[0]), Integer.parseInt(sPos[1]));
            Direction rDir = Arrays.stream(Direction.values()).filter(l -> l.getLetter() == sPos[2].charAt(0)).findAny().orElse(null);
            Position rPos = new Position(rCoord, rDir);
            rover = (Rover) plateau.getVehicle(rCoord);

            if (rover == null) rover = new Rover(plateau, rPos);

            System.out.println("Enter the moving instructions" + (firstTime ? " (e.g. LMLMLMLMM):" : ":"));
            input = reader.nextLine();
            if (input.isBlank()) return;

            String collisionMsg = rover.move(input.trim());
            if (!collisionMsg.isEmpty())
                System.out.println(collisionMsg);
            System.out.println("New Rover's position: " + rover.getPosition() + "\n");
            firstTime = false;
        }
    }
}