package labwork;

import java.util.Objects;

/**
 * Location
 */
public class Location {
    private int x;
    private int y;
    private float z;
    private String name; //Строка не может быть пустой, Поле может быть null

    public Location(int x, int y, float z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return getX() == location.getX() &&
                getY() == location.getY() &&
                Float.compare(location.getZ(), getZ()) == 0 &&
                getName().equals(location.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY(), getZ(), getName());
    }
}
