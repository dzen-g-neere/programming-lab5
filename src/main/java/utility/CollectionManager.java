package utility;

import exceptions.EmptyCollectionException;
import exceptions.WrongArgumentException;
import exceptions.WrongIDException;
import labwork.LabWork;
import labwork.Location;
import labwork.Person;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class CollectionManager {
    private HashMap<String, LabWork> labWorks = new HashMap<String, LabWork>();
    private LocalDate creationDate;
    private FileManager fileManager;

    public CollectionManager(FileManager fileManager) {
        this.fileManager = fileManager;
        creationDate = LocalDate.now();
    }

    public void loadCollection() {
        HashMap<String, LabWork> trr = fileManager.loadCollection();
        if (trr != null) {
            labWorks = trr;
        }
    }

    public void addLabWorkToCollection(String key, LabWork labWork) {
        labWorks.put(key, labWork);
    }

    public void labWorkToOutput(String key, LabWork labWork) {
        System.out.println("key: " + key);
        System.out.println("id: " + labWork.getId());
        System.out.println("    Название: " + labWork.getName());
        System.out.println("     Координаты:");
        System.out.println("        x: " + labWork.getCoordinates().getX());
        System.out.println("        y: " + labWork.getCoordinates().getY());
        System.out.println("    Дата создания: " + labWork.getCreationDate());
        System.out.println("    Минимальный балл: " + labWork.getMinimalPoint());
        System.out.println("    Мин. балл за л.к.: " + labWork.getPersonalQualitiesMinimum());
        System.out.println("    Средний балл: " + labWork.getAveragePoint());
        try {
            System.out.println("    Сложность: " + labWork.getDifficulty().getName());
        } catch (Exception e) {
            System.out.println("    Сложность: " + null);
        }
        Person author = labWork.getAuthor();
        if (author != null) {
            Location authorLocation = author.getLocation();
            System.out.println("    Автор:");
            System.out.println("        Имя: " + author.getName());
            System.out.println("        Рост: " + author.getHeight());
            try {
                System.out.println("        Цвет глаз: " + author.getEyeColor().getName());
            } catch (Exception e) {
                System.out.println("        Цвет глаз: " + null);
            }
            try {
                System.out.println("        Цвет волос: " + author.getHairColor().getName());
            } catch (Exception e) {
                System.out.println("        Цвет волос: " + null);
            }
            try {
                System.out.println("        Национальность: " + author.getNationality().getName());
            } catch (Exception e) {
                System.out.println("        Национальность: " + author.getNationality().getName());
            }
            System.out.println("        Местоположение: ");
            System.out.println("            Локация: " + authorLocation.getName());
            System.out.println("                x: " + authorLocation.getX());
            System.out.println("                y: " + authorLocation.getY());
            System.out.println("                z: " + authorLocation.getZ());
        } else
            System.out.println("    Автор: null");
    }

    public void showCollection() {
        try {
            if (!labWorks.isEmpty()) {
                Set<Map.Entry<String, LabWork>> labsForOutput = labWorks.entrySet();
                for (Map.Entry<String, LabWork> i : labsForOutput) {
                    labWorkToOutput(i.getKey(), i.getValue());
//        private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
//        private String name; //Поле не может быть null, Строка не может быть пустой
//        private Coordinates coordinates; //Поле не может быть null
//        private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
//        private Long minimalPoint; //Поле может быть null, Значение поля должно быть больше 0
//        private long personalQualitiesMinimum; //Значение поля должно быть больше 0
//        private float averagePoint; //Значение поля должно быть больше 0
//        private Difficulty difficulty; //Поле может быть null
//        private Person author; //Поле может быть null
                }
            } else throw new EmptyCollectionException();

        } catch (EmptyCollectionException emptyCollectionException) {
            System.out.println("В коллекции нет элементов");
        }
    }

    public void showInfo() {
        System.out.println(
                "Информация о коллекции:"
                        + "  Тип: Hashmap <String, LabWork>\n"
                        + "  Дата создания:" + creationDate + " \n"
                        + "  Количество элементов:" + labWorks.size()
        );
    }

    public void clearCollection() {
        labWorks.clear();
    }

    public Map.Entry<String, LabWork> findByID(int ID) throws WrongIDException, EmptyCollectionException {
        if (labWorks.isEmpty())
            throw new EmptyCollectionException();
        Set<Map.Entry<String, LabWork>> labs = labWorks.entrySet();
        for (Map.Entry<String, LabWork> i : labs) {
            if (i.getValue().getId() == ID) {
                System.out.println("Элемент с ID " + ID + " найден");
                return i;
            } else {
                throw new WrongIDException();
            }
        }
        return null;
    }

    public void removeKey(String key) {
        try {
            labWorks.remove(key);
        } catch (NullPointerException e) {
            System.out.println("Ключ " + key + " не обнаружен");
        } catch (Exception e) {
            System.out.println("Ошибка. Невозможно удалить элемент по ключу " + key);
        }
    }

    public LabWork getByKey(String key) throws WrongArgumentException {
        LabWork labWork = null;
        if (labWorks.containsKey(key))
            labWork = labWorks.get(key);
        else throw new WrongArgumentException();

        return labWork;
    }

    public void removeGreaterKey(String key) {
        try {
            if (key == null) throw new WrongArgumentException();
            if (!labWorks.isEmpty()) {
                labWorks.entrySet().removeIf(stringLabWorkEntry -> key.compareTo(stringLabWorkEntry.getKey()) < 0);
            } else throw new EmptyCollectionException();
            System.out.println("Команда выполнена");
        } catch (EmptyCollectionException emptyCollectionException) {
            System.out.println("В коллекции нет элементов");
        } catch (WrongArgumentException WrongArgumentException) {
            System.out.println("Аргумент не может быть пустой строкой");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Непредвиденная ошибка");
        }
    }

    public void groupCountingByCrDate() {
        try {
            if (!labWorks.isEmpty()) {
                HashMap<LocalDate, Integer> labsHashMap = new HashMap<LocalDate, Integer>();
                Set<Map.Entry<String, LabWork>> labsSet = labWorks.entrySet();
                for (Map.Entry<String, LabWork> i : labsSet) {
                    String key = i.getKey();
                    LabWork labWork = i.getValue();
                    if (!labsHashMap.containsKey(labWork.getCreationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) {
                        labsHashMap.put(labWork.getCreationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), 1);
                    } else {
                        int sum = labsHashMap.get(labWork.getCreationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        sum++;
                        labsHashMap.remove(labWork.getCreationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        labsHashMap.put(labWork.getCreationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), sum);
                    }
                }
                for (Map.Entry<LocalDate, Integer> i : labsHashMap.entrySet()) {
                    System.out.println(i.getKey() + " - " + i.getValue());
                }
            } else System.out.println("В коллекции нет ни одного элемента.");
        } catch (Exception e) {
            System.out.println("Ошибка");
        }
    }

    public void filterGreaterThanAveragePoint(float averagePoint) {
        try {
            if (!labWorks.isEmpty()) {
                boolean trigger = false;
                Set<Map.Entry<String, LabWork>> labsForOutput = labWorks.entrySet();
                for (Map.Entry<String, LabWork> i : labsForOutput) {
                    if (i.getValue().getAveragePoint() > averagePoint) {
                        labWorkToOutput(i.getKey(), i.getValue());
                        trigger = true;
                    }
                }
                if (!trigger) {
                    System.out.println("Элементов с average_point большим " + averagePoint + " не обнаружено");
                }
            } else throw new EmptyCollectionException();

        } catch (EmptyCollectionException emptyCollectionException) {
            System.out.println("В коллекции нет элементов");
        }
    }

    public void printDescending() {
        Vector<Map.Entry<String, LabWork>> labWorks1 = new Vector<Map.Entry<String, LabWork>>();
        try {
            if (!labWorks.isEmpty()) {
                Set<Map.Entry<String, LabWork>> labs = labWorks.entrySet();
                labWorks1.addAll(labs);
                labWorks1.sort((o1, o2) -> -o1.getValue().getName().compareTo(o2.getValue().getName()));
                for (Map.Entry<String, LabWork> i : labWorks1) {
                    labWorkToOutput(i.getKey(), i.getValue());
                }
            } else throw new EmptyCollectionException();

        } catch (EmptyCollectionException emptyCollectionException) {
            System.out.println("В коллекции нет элементов");
        }
    }

    public void saveCollection() {
        fileManager.saveCollection(labWorks);
    }
}
