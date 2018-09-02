package ctc.com.viii.ch07.p02;

class Respondent extends Employee {
    public Respondent(CallHandler callHandler) {
        super(callHandler);
        rank = Rank.Responder;
        System.out.println("Respondent create");
    }
}