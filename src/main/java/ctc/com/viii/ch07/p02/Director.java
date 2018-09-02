package ctc.com.viii.ch07.p02;

class Director extends Employee {
    public Director(CallHandler callHandler) {
        super(callHandler);
        rank = Rank.Director;
        System.out.println("Director create");
    }
}