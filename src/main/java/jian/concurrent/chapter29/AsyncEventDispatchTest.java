package jian.concurrent.chapter29;

import jian.concurrent.chapter29.async.AsyncChannel;
import jian.concurrent.chapter29.async.AsyncEventDispatcher;
import jian.concurrent.chapter29.sync.Event;
import jian.concurrent.chapter29.sync.EventDispatcher;

import java.util.concurrent.TimeUnit;

public class AsyncEventDispatchTest {
    static class AsyncInputEventDispatcher extends AsyncChannel {

        private final AsyncEventDispatcher dispatcher;

        public AsyncInputEventDispatcher(AsyncEventDispatcher dispatcher) {
            this.dispatcher = dispatcher;
        }

        @Override
        protected void handle(Event message) {
            SyncEventDispatchTest.InputEvent inputEvent = (SyncEventDispatchTest.InputEvent) message;
            System.out.printf("X:%d, y:%d\n", inputEvent.getX(), inputEvent.getY());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            int result = inputEvent.getX() + inputEvent.getY();
            dispatcher.dispatch(new SyncEventDispatchTest.ResultEvent(result));

        }
    }


    static class AsyncResultEventDispatcher extends AsyncChannel {
        @Override
        protected void handle(Event message) {
            SyncEventDispatchTest.ResultEvent resultEvent = (SyncEventDispatchTest.ResultEvent) message;
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            System.out.println("the result is " + resultEvent.getResult());
        }
    }


    public static void main(String[] args) {


        AsyncEventDispatcher dispatcher = new AsyncEventDispatcher();

        dispatcher.registerChannel(SyncEventDispatchTest.InputEvent.class, new AsyncInputEventDispatcher(dispatcher));
        dispatcher.registerChannel(SyncEventDispatchTest.ResultEvent.class, new AsyncResultEventDispatcher());
        dispatcher.dispatch(new SyncEventDispatchTest.InputEvent(3, 5));

    }
}
