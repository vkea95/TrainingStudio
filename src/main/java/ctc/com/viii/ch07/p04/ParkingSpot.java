package ctc.com.viii.ch07.p04;

// 此处需要参考和level的关系，同时又可以反向确定自己的位置，也就是level，row和number
// 还要表明车辆size
public class ParkingSpot {
    private Vehicle vehicle;
    private VehicleSize spotSize;
    private int row;
    private int spotNumber;
    private Level level;

    public ParkingSpot(Level lvl, int r, int n, VehicleSize sz) {
        level = lvl;
        row = r;
        spotNumber = n;
        spotSize = sz;
    }


    public boolean isAvailable() {
        return vehicle == null;
    }

    /* Checks if the spot is big enough for the vehicle (and is available). This compares
     * the SIZE only. It does not check if it has enough spots. */
    public boolean canFitVehicle(Vehicle vehicle) {
        return isAvailable() && vehicle.canFitInSpot(this);
    }

    public int getRow() {
        return row;
    }

    /* Park vehicle in this spot. */
    public boolean park(Vehicle v) {
        if (!canFitVehicle(v)) {
            return false;
        }
        vehicle = v;
        vehicle.parkInSpot(this);
        return true;
    }

    /* Remove vehicle from spot, and notify level that a new spot is available */
    public void removeVehicle() {
        level.spotFreed();
        vehicle = null;
    }

    public VehicleSize getSpotSize() {
        return spotSize;
    }

    public void print() {
        if (vehicle == null) {
            if (spotSize == VehicleSize.Compact) {
                System.out.print("c");
            } else if (spotSize == VehicleSize.Large) {
                System.out.print("l");
            } else if (spotSize == VehicleSize.Motorcycle) {
                System.out.print("m");
            }
        } else {
            vehicle.print();
        }
    }
}
