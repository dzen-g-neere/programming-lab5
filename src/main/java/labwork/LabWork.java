package labwork;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

/**
 * Labwork. Is stored in the collection.
 */
public class LabWork implements Comparable<LabWork>{
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long minimalPoint; //Поле может быть null, Значение поля должно быть больше 0
    private long personalQualitiesMinimum; //Значение поля должно быть больше 0
    private float averagePoint; //Значение поля должно быть больше 0
    private Difficulty difficulty; //Поле может быть null
    private Person author; //Поле может быть null

    public LabWork(Integer id,String name, Coordinates coordinates, Date creationDate, Long minimalPoint, long personalQualitiesMinimum, float averagePoint, Difficulty difficulty, Person author) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.minimalPoint = minimalPoint;
        this.personalQualitiesMinimum = personalQualitiesMinimum;
        this.averagePoint = averagePoint;
        this.difficulty = difficulty;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getMinimalPoint() {
        return minimalPoint;
    }

    public void setMinimalPoint(Long minimalPoint) {
        this.minimalPoint = minimalPoint;
    }

    public long getPersonalQualitiesMinimum() {
        return personalQualitiesMinimum;
    }

    public void setPersonalQualitiesMinimum(long personalQualitiesMinimum) {
        this.personalQualitiesMinimum = personalQualitiesMinimum;
    }

    public float getAveragePoint() {
        return averagePoint;
    }

    public void setAveragePoint(float averagePoint) {
        this.averagePoint = averagePoint;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    @Override
    public int compareTo(LabWork labWork) {
        return getName().compareTo(labWork.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LabWork)) return false;
        LabWork labWork = (LabWork) o;
        return getPersonalQualitiesMinimum() == labWork.getPersonalQualitiesMinimum() &&
                Float.compare(labWork.getAveragePoint(), getAveragePoint()) == 0 &&
                getId().equals(labWork.getId()) &&
                getName().equals(labWork.getName()) &&
                getCoordinates().equals(labWork.getCoordinates()) &&
                getCreationDate().equals(labWork.getCreationDate()) &&
                getMinimalPoint().equals(labWork.getMinimalPoint()) &&
                getDifficulty() == labWork.getDifficulty() &&
                getAuthor().equals(labWork.getAuthor());
    }

    private void generateID(){
        id = Objects.hash( getName(), getCoordinates(), getCreationDate(), getMinimalPoint(),
                getPersonalQualitiesMinimum(), getAveragePoint(), getDifficulty(), getAuthor());
    }


    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCoordinates(), getCreationDate(), getMinimalPoint(),
                getPersonalQualitiesMinimum(), getAveragePoint(), getDifficulty(), getAuthor());
    }

    @Override
    public String toString() {
        return "LabWork{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", minimalPoint=" + minimalPoint +
                ", personalQualitiesMinimum=" + personalQualitiesMinimum +
                ", averagePoint=" + averagePoint +
                ", difficulty=" + difficulty +
                ", author=" + author +
                '}';
    }
}
