package jian.concurrent.chapter16;

public class EatNoodleThread extends Thread {
    private final String name;

    private final Tableware leftTool;
    private final Tableware rightTool;

    public EatNoodleThread(String name, Tableware leftTool, Tableware rightTool) {
        this.name = name;
        this.leftTool = leftTool;
        this.rightTool = rightTool;
    }

    @Override
    public void run() {
        while (true) {
            eat();
        }
    }

    private void eat() {
        synchronized (leftTool) {
            System.out.println(name + " take up " + leftTool + "(left)");
            synchronized (rightTool) {
                System.out.println(name + " take up " + rightTool + "(right)");
                System.out.println(name + " is eating ");
                System.out.println(name + " put down " + rightTool + "(right)");

            }
            System.out.println(name + " put down " + leftTool + "(left)");
        }
    }
}
