package design.com.elevator.v1;

/**
 * Created by tclresearchamerica on 9/18/16.
 * this request means that the user require the elevator what to do
 */
public class Request {
    public long time;
    public Integer floor;
    public String direction;

    public Request(long time, Integer floor, String direction) {
        this.time = time;
        this.floor = floor;
        this.direction = direction;
    }

}
