package labwork;

/**
 * Difficulty of labwork.
 */
public enum Difficulty {
    HARD("HARD"),
    VERY_HARD("VERY_HARD"),
    IMPOSSIBLE("IMPOSSIBLE");

    private final String name;

    Difficulty(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Difficulty{" +
                "name='" + name + '\'' +
                '}';
    }
}
