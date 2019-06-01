package ctc.com.viii.ch07.p04;

public class Level {
    private int floor;
    private ParkingSpot[] spots;
    private int availableSpots = 0; // number of free spots
    private static final int SPOTS_PER_ROW = 10;


    public Level(int floor, int numberSpots) {
        this.floor = floor;
        spots = new ParkingSpot[numberSpots];
        availableSpots = numberSpots;
        // setup the detail
        int largeSpots = numberSpots / 4;
        int bikeSpots = numberSpots / 4;
        int compactSpots = numberSpots - largeSpots - bikeSpots;
        for (int i = 0; i < numberSpots; i++) {
            VehicleSize sz = VehicleSize.Motorcycle;
            if (i < largeSpots) {
                sz = VehicleSize.Large;
            } else if (i < largeSpots + compactSpots) {
                sz = VehicleSize.Compact;
            }
            int row = i / SPOTS_PER_ROW;
            spots[i] = new ParkingSpot(this, row, i, sz);
        }

    }

    public boolean parkVehicle(Vehicle vehicle) {

        if (getAvailableSpots() < vehicle.getSpotsNeeded()) {
            return false;
        }
        //此处获得是连续可用的车位的第一个index
        int spotNumber = findAvailableSpots(vehicle);
        if (spotNumber < 0) {
            return false;
        }
        return parkStartingAtSpot(spotNumber, vehicle);
    }


    /* Park a vehicle starting at the spot spotNumber, and continuing until vehicle.spotsNeeded. */
    private boolean parkStartingAtSpot(int spotNumber, Vehicle vehicle) {
        vehicle.clearSpots();
        boolean success = true;
        for (int i = spotNumber; i < spotNumber + vehicle.spotsNeeded; i++) {
            success &= spots[i].park(vehicle);
        }
//        if (success) {
            availableSpots -= vehicle.spotsNeeded;
//        } else {
//            vehicle.clearSpots();
//        }
        return success;
    }

    /* find a spot to park this vehicle. Return index of spot, or -1 on failure. */
    private int findAvailableSpots(Vehicle vehicle) {
        int spotsNeeded = vehicle.getSpotsNeeded();
        int lastRow = -1;
        int spotsFound = 0;
        for (int i = 0; i < spots.length; i++) {
            ParkingSpot spot = spots[i];
            if (lastRow != spot.getRow()) {
                //根据这个车的需要的量，可知，必然要求车位是连续的，否则就是零个
                spotsFound = 0;
                lastRow = spot.getRow();
            }
            //根据这个车的需要的量，可知，必然要求车位是连续的，否则就是零个
            spotsFound += spot.canFitVehicle(vehicle) ? 1 : -spotsFound;
            //从连续车位的第一个开始返回
            if (spotsFound == spotsNeeded) {
                return i - (spotsNeeded - 1);
            }
        }
        return -1;
    }

    public void print() {
        int lastRow = -1;
        for (int i = 0; i < spots.length; i++) {
            ParkingSpot spot = spots[i];
            if (spot.getRow() != lastRow) {
                System.out.print("  ");
                lastRow = spot.getRow();
            }
            spot.print();
        }
    }

    /* When a car was removed from the spot, increment availableSpots */
    public void spotFreed() {
        availableSpots++;
    }

    public int getAvailableSpots() {
        return availableSpots;
    }
}
