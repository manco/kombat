package pl.manco.core;

public class Position {
    private int x;
    private int y;

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public void left(int val) {
        x -= val;
    }

    public void right(int val) {
        x += val;
    }

    public void up(int val) {
        y += val;
    }

    public void down(int val) {
        y -= val;
    }
}
