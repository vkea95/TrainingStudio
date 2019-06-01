package ctc.com.viii.ch07.p04;

import static CtCILibrary.AssortedMethods.randomInt;

public class Questions {
    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot();

        Vehicle v = null;
        while (v == null || lot.parkVehicle(v)) {
            lot.print();
            int r = Questions.randomIntInRange(0, 10);
            if (r < 2) {
                v = new Bus();
            } else if (r < 4) {
                v = new Motorcycle();
            } else {
                v = new Car();
            }
            System.out.print("\nParking a ");
            v.print();
            System.out.println("");
        }
        System.out.println("Parking Failed. Final state: ");
        lot.print();
    }

    public static int randomIntInRange(int min, int max) {
        return randomInt(max + 1 - min) + min;
    }

}
/*
park spot -> level -> lot
vehicle
spot可以放哪种vehicle，是早就定义好的
 */