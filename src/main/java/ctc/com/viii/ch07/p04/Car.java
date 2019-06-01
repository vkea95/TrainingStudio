package ctc.com.viii.ch07.p04;

public class Car extends Vehicle {
    public Car() {
        spotsNeeded = 1;
        size = VehicleSize.Compact;
    }

    public boolean canFitInSpot(ParkingSpot spot) {
        return spot.getSpotSize() == VehicleSize.Large || spot.getSpotSize() == VehicleSize.Compact;
    }

    public void print() {
        System.out.print("C");
    }
}
