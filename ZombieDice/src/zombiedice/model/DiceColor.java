package zombiedice.model;

public enum DiceColor {
    GREEN(3, 2, 1), YELLOW(2, 2, 2), RED(1, 2, 3);

    private final int brain;
    private final int foot;
    private final int fire;

    DiceColor(int brain, int foot, int fire) {
        this.brain = brain;
        this.foot = foot;
        this.fire = fire;
    }

    public int getBrain() {
        return brain;
    }

    public int getFoot() {
        return foot;
    }

    public int getFire() {
        return fire;
    }
}
