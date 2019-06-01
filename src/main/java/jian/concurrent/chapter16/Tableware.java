package jian.concurrent.chapter16;

public class Tableware {
    private final String toolName;

    public Tableware(String toolName) {
        this.toolName = toolName;
    }

    @Override
    public String toString() {
        return "toolName: " + toolName;
    }
}