package ctc.com.viii.ch07.p04;

import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle {
//    一个车可能占好介个车位，所以就要用list
    protected List<ParkingSpot> parkingSpotList = new ArrayList<>();
    protected String licensePlate;
    protected int spotsNeeded;
    protected VehicleSize size;

    public abstract boolean canFitInSpot(ParkingSpot spot);

    public abstract void print();

    public int getSpotsNeeded() {
        return spotsNeeded;
    }

    public VehicleSize getSize() {
        return size;
    }

    public void parkInSpot(ParkingSpot parkingSpot) {
        parkingSpotList.add(parkingSpot);
    }

    /* Remove car from spot, and notify spot that it's gone */
    public void clearSpots() {
        for (int i = 0; i < parkingSpotList.size(); i++) {
            parkingSpotList.get(i).removeVehicle();
        }
        parkingSpotList.clear();
    }
}
