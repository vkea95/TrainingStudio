package ctc.com.viii.ch07.p02;

public class Call {

    /* Minimal rank of employee who can handle this call. */
    private Rank rank;

    /* Person who is calling. */
    private Caller caller;

    /* Employee who is handling call. */
    private Employee handler;

    // 此时，只可以通过 caller构成实体，handler，还没来呢，和Employee是谁还不知道呢
    public Call(Caller caller) {
        rank = Rank.Responder;//必须指定哦
        this.caller = caller;
    }

    /* Play recorded message to the customer. */
    public void reply(String message) {
        System.out.println(message);
    }

    public Rank incrementRank() {
        if (rank == Rank.Responder) {
            rank = Rank.Manager;
        } else if (rank == Rank.Manager) {
            rank = Rank.Director;
        }
        return rank;
    }

    /* Disconnect call. */
    public void disconnect() {
        reply("Thank you for calling");
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Caller getCaller() {
        return caller;
    }

    public void setCaller(Caller caller) {
        this.caller = caller;
    }

    public Employee getHandler() {
        return handler;
    }

    public void setHandler(Employee handler) {
        this.handler = handler;
    }
}
