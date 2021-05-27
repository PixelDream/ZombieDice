package zombiedice.utils;

import java.util.Random;

public class RandomUtils extends Random {

    private final int min;
    private final int max;

    public RandomUtils(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int nextInt() {
        return min + this.nextInt(max - min);
    }
}
