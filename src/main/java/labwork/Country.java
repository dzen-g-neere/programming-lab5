package labwork;

/**
 * Nationality.
 */
public enum Country {
    FRANCE("FRANCE"),
    VATICAN("VATICAN"),
    ITALY("ITALY"),
    THAILAND("THAILAND");

    private final String name;

    Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                '}';
    }
}
