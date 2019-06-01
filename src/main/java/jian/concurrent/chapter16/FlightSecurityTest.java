package jian.concurrent.chapter16;

import jian.concurrent.chapter03.FightQuery;

import java.util.stream.IntStream;

public class FlightSecurityTest {

    static class Passengers extends Thread {

        private final FlightSecurity flightSecurity;

        private final String idCard;

        private final String boardingPass;

        public Passengers(FlightSecurity flightSecurity, String idCard, String boardingPass) {
            this.flightSecurity = flightSecurity;
            this.idCard = idCard;
            this.boardingPass = boardingPass;
        }

        @Override
        public void run() {
            while (true) {
                flightSecurity.pass(idCard, boardingPass);
            }
        }
    }


    public static void main(String[] args) {
        final FlightSecurity flightSecurity = new FlightSecurity();
        IntStream.range(0, 4).forEach(i -> new Passengers(flightSecurity, String.valueOf(i), String.valueOf(i)).start());
    }
}
