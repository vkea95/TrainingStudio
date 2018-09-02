package ctc.com.viii.ch07.p02;

class Manager extends Employee {
    public Manager(CallHandler callHandler) {
        super(callHandler);
        rank = Rank.Manager;
        System.out.println("Manager create");
    }
}