package jian.concurrent.chapter16;

public class FlightSecurity {
    private int count = 0;
    private String boardingPass = "null";
    private String idCard = "null";
    private String lasStr;

    public void pass(String boardingPass, String idCard) {
//        System.out.println("count: " + count + " boardingPass:" + boardingPass + " idCard: " + idCard);
//        lasStr = "count: " + count + " boardingPass:" + boardingPass + " idCard: " + idCard;
        this.boardingPass = boardingPass;
        this.idCard = idCard;
        this.count++;
        check();
    }

    private void check() {
        if (boardingPass.charAt(0) != idCard.charAt(0)) {
//            System.out.println("lastStr" + lasStr);
            throw new RuntimeException("====Exception====" + toString());
        }
    }

    public String toString() {
        return "count: " + count + " boardingPass:" + boardingPass + " idCard: " + idCard;
    }

}
