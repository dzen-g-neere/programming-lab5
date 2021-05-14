package labwork;

/**
 * Color.
 */
public enum Color {
    RED("RED"),
    ORANGE("ORANGE"),
    GREEN("GREEN"),
    BLUE("BLUE"),
    BROWN("BROWN"),
    BLACK("BLACK"),
    WHITE("WHITE");

    public final String name;

    Color(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Color{" +
                "name='" + name + '\'' +
                '}';
    }
}
