package jian.concurrent.chapter29;

import jian.concurrent.chapter29.sync.Channel;
import jian.concurrent.chapter29.sync.Event;
import jian.concurrent.chapter29.sync.EventDispatcher;

public class SyncEventDispatchTest {

    static class InputEvent extends Event {
        private final int x;
        private final int y;

        public InputEvent(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    static class ResultEvent extends Event {
        private final int result;

        public ResultEvent(int result) {
            this.result = result;
        }

        public int getResult() {
            return result;
        }
    }

    static class ResultHandler implements Channel<ResultEvent> {

        @Override
        public void dispatch(ResultEvent message) {
            System.out.println("The result is " + message.getResult());
        }
    }

    static class InputHandler implements Channel<InputEvent> {

        private final EventDispatcher eventDispatcher;

        public InputHandler(EventDispatcher eventDispatcher) {
            this.eventDispatcher = eventDispatcher;
        }

        @Override
        public void dispatch(InputEvent message) {
            System.out.printf("X:%d, y:%d\n", message.getX(), message.getY());
            int result = message.getX() + message.getY();

            eventDispatcher.dispatch(new ResultEvent(result));
        }
    }

    public static void main(String[] args) {
        EventDispatcher dispatcher = new EventDispatcher();

        dispatcher.registerChannel(InputEvent.class, new InputHandler(dispatcher));
        dispatcher.registerChannel(ResultEvent.class, new ResultHandler());
        dispatcher.dispatch(new InputEvent(3, 5));
    }
}
