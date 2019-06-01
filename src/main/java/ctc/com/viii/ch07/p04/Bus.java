package ctc.com.viii.ch07.p04;

public class Bus extends Vehicle {
    public Bus() {
        spotsNeeded = 5;
        size = VehicleSize.Large;
    }

    public boolean canFitInSpot(ParkingSpot spot) {
        return spot.getSpotSize() == VehicleSize.Large;
    }

    public void print() {
        System.out.print("B");
    }
}
