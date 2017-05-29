package alg4.com.ch0103;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by JianZhang on 5/25/17.
 */
public class RingQueueTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void enQueue() throws Exception {
        RingQueue<Long> ringQueue = new RingQueue<Long>();
        for (long i = 0; i < 100; i++) {
            ringQueue.enQueue(i);
        }
        for (long i = 0; i < 100; i++) {
//            assertEquals(i, ringQueue.deQueue());
        }

    }

    @Test
    public void deQueue() throws Exception {

    }

}