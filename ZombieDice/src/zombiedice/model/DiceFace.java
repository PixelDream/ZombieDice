package zombiedice.model;

public enum DiceFace {

    BRAIN("brain", ".png"), FIRE("fire", ".png"), FOOT("foot", ".png");

    protected final String BASE_PATH = "/assets/dices/";

    private final String path;
    private final String extention;

    DiceFace(String path, String extention) {
        this.path = path;
        this.extention = extention;
    }

    /**
     * join strings to create path
     *
     * @param color
     * @return
     */
    public String getPath(DiceColor color) {
        return BASE_PATH + path + "_" + color.name().toLowerCase() + extention;
    }

}
