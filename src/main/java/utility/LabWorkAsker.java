package utility;

import exceptions.IncorrectScriptException;
import exceptions.MustBeNotEmptyException;
import exceptions.WrongArgumentException;
import labwork.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Ask from user or file labwork fields.
 */
public class LabWorkAsker {
    private static Integer ID = 0;
    static private Scanner scanner;
    static private boolean scriptMode = false;
    static private boolean fileMode = false;

    public LabWorkAsker(Scanner scanner) {
        LabWorkAsker.scanner = scanner;
    }

    public static boolean isFileMode() {
        return fileMode;
    }

    public void setFileMode(boolean fileMode) {
        LabWorkAsker.fileMode = fileMode;
    }

    public boolean isScriptMode() {
        return scriptMode;
    }

    public void setScriptMode(boolean b) {
        scriptMode = b;
    }

    public Scanner getUserScanner() {
        return scanner;
    }

    public void setUserScanner(Scanner userScanner) {
        scanner = userScanner;
    }

    /**
     * asks about ID
     *
     * @return new ID
     */
    //    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    public void checkKey(String s) throws IncorrectScriptException, WrongArgumentException{
        if (s.contains(";") && isScriptMode())
            throw new IncorrectScriptException();
        else if (s.contains(";"))
            throw new WrongArgumentException();
    }
    public Integer askID() {
        return ID++;
    }

    /**
     * asks about labwork name
     *
     * @return new name
     */
    //    private String name; //Поле не может быть null, Строка не может быть пустой
    public String askName() throws IncorrectScriptException {
        String name = "";
        if (!isFileMode())
            System.out.println("Введите название лабораторной работы: ");
        while (true) {
            try {
                name = scanner.nextLine();
                if (!isFileMode() && isScriptMode())
                    System.out.println(name);
                if (name.equals(""))
                    throw new MustBeNotEmptyException();
                if (name.contains(";"))
                    throw new WrongArgumentException();
                break;
            } catch (MustBeNotEmptyException e) {
                if (!isScriptMode())
                    System.out.println("Название не может быть пустой строкой. Повторите ввод");
                else throw new IncorrectScriptException();
            } catch (WrongArgumentException e) {
                System.out.println("Название не может содержать символ ';'. Повторите ввод");
            } catch (NoSuchElementException exception) {
                System.out.println("Пользовательский ввод не обнаружен!");
                if (isFileMode())
                    throw new IncorrectScriptException();
                else
                    System.exit(0);
            } catch (Exception e) {
                if (!isScriptMode())
                    System.out.println("Ошибка. Повторите ввод");
                else throw new IncorrectScriptException();
            }
        }
        return name;
    }

    /**
     * asks coordinates of labwork
     *
     * @return new coordinates
     */
    //    private Coordinates coordinates; //Поле не может быть null
    public Coordinates askCoordinates() throws IncorrectScriptException {
        return (new Coordinates(askCoordX(), askCoordY()));
    }

    /**
     * asks y of coordinates
     *
     * @return new y
     */
    private Float askCoordY() throws IncorrectScriptException {
        String temp;
        Float y;
        while (true) {
            try {
                if (!isFileMode())
                    System.out.println("Введите координату y: ");
                temp = scanner.nextLine().trim();
                if (!isFileMode() && isScriptMode())
                    System.out.println(temp);
                y = Float.parseFloat(temp);
                if (y > 807)
                    throw new WrongArgumentException();
                break;
            } catch (NumberFormatException exception) {
                if (!isScriptMode())
                    System.out.println("Некорректный формат, повторите ввод.");
                else throw new IncorrectScriptException();
            } catch (WrongArgumentException exception) {
                if (!isScriptMode())
                    System.out.println("Максимальное значение поля - 807, повторите ввод.");
                else throw new IncorrectScriptException();
            } catch (NoSuchElementException exception) {
                System.out.println("Пользовательский ввод не обнаружен!");
                if (isFileMode())
                    throw new IncorrectScriptException();
                else
                    System.exit(0);
            } catch (Exception exception) {
                if (!isScriptMode())
                    System.out.println("Ошибка. Повторите ввод");
                else throw new IncorrectScriptException();
            }
        }
        return y;
    }

    /**
     * asks about x of coordinates
     *
     * @return new x
     */
    private Integer askCoordX() throws IncorrectScriptException {
        String temp;
        Integer x;
        while (true) {
            try {
                if (!isFileMode())
                    System.out.println("Введите координату x: ");
                temp = scanner.nextLine().trim();
                if (!isFileMode() && isScriptMode())
                    System.out.println(temp);
                x = Integer.parseInt(temp);
                break;
            } catch (NumberFormatException e) {
                if (!isScriptMode())
                    System.out.println("Некорректный формат, повторите ввод.");
                else throw new IncorrectScriptException();
            } catch (NoSuchElementException exception) {
                System.out.println("Пользовательский ввод не обнаружен!");
                if (isFileMode())
                    throw new IncorrectScriptException();
                else
                    System.exit(0);
            } catch (Exception e) {
                if (!isScriptMode())
                    System.out.println("Ошибка. Повторите ввод");
                else throw new IncorrectScriptException();
            }
        }
        return x;
    }
//    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    /**
     * asks about creation day of labwork
     *
     * @return new name
     */
    public Date askDate() throws IncorrectScriptException {
        return new Date();
    }

    /**
     * asks about minimal point of labwork
     *
     * @return new minimal point
     */
    //    private Long minimalPoint; //Поле может быть null, Значение поля должно быть больше 0
    public Long askMinimalPoint() throws IncorrectScriptException {
        String temp;
        Long minimalPoint;
        while (true) {
            try {
                if (!isFileMode())
                    System.out.println("Введите минимальный балл: ");
                temp = scanner.nextLine().trim();
                if (!isFileMode() && isScriptMode())
                    System.out.println(temp);
                minimalPoint = Long.parseLong(temp);
                if (minimalPoint <= 0)
                    throw new WrongArgumentException();
                break;
            } catch (NumberFormatException e) {
                if (!isScriptMode())
                    System.out.println("Некорректный формат, повторите ввод.");
                else
                    throw new IncorrectScriptException();
            } catch (WrongArgumentException e) {
                if (!isScriptMode())
                    System.out.println("Минимальный балл должен быть больше 0, повторите ввод.");
                else
                    throw new IncorrectScriptException();
            } catch (NoSuchElementException exception) {
                System.out.println("Пользовательский ввод не обнаружен!");
                if (isFileMode())
                    throw new IncorrectScriptException();
                else
                    System.exit(0);
            } catch (Exception e) {
                if (!isScriptMode())
                    System.out.println("Ошибка. Повторите ввод");
                else
                    throw new IncorrectScriptException();
            }
        }
        return minimalPoint;
    }

    /**
     * ask labwork's personal qualities minimum
     *
     * @return new personal qualities minimum
     */
    //    private long personalQualitiesMinimum; //Значение поля должно быть больше 0
    public long askPersonalQualitiesMinimum() throws IncorrectScriptException {
        String temp;
        long personalQualitiesMinimum;
        while (true) {
            try {
                if (!isFileMode())
                    System.out.println("Введите минимальный балл за л.к.: ");
                temp = scanner.nextLine().trim();
                if (!isFileMode() && isScriptMode())
                    System.out.println(temp);
                personalQualitiesMinimum = Long.parseLong(temp);
                if (personalQualitiesMinimum <= 0)
                    throw new WrongArgumentException();
                break;
            } catch (NumberFormatException e) {
                if (!isScriptMode())
                    System.out.println("Некорректный формат, повторите ввод.");
                else throw new IncorrectScriptException();
            } catch (WrongArgumentException e) {
                if (!isScriptMode())
                    System.out.println("Минимальный балл за л.к. должен быть больше 0, повторите ввод.");
                else throw new IncorrectScriptException();
            } catch (NoSuchElementException exception) {
                System.out.println("Пользовательский ввод не обнаружен!");
                if (isFileMode())
                    throw new IncorrectScriptException();
                else
                    System.exit(0);
            } catch (Exception e) {
                if (!isScriptMode())
                    System.out.println("Ошибка. Повторите ввод");
                else throw new IncorrectScriptException();
            }
        }
        return personalQualitiesMinimum;
    }

    /**
     * ask labwork's average point
     *
     * @return new average point
     */
    //    private float averagePoint; //Значение поля должно быть больше 0
    public float askAveragePoint() throws IncorrectScriptException {
        String temp;
        float averagePoint;
        while (true) {
            try {
                if (!isFileMode())
                    System.out.println("Введите средний балл для л.р.: ");
                temp = scanner.nextLine().trim();
                if (!isFileMode() && isScriptMode())
                    System.out.println(temp);
                averagePoint = Float.parseFloat(temp);
                if (averagePoint <= 0)
                    throw new WrongArgumentException();
                break;
            } catch (NumberFormatException exception) {
                if (!isScriptMode())
                    System.out.println("Некорректный формат, повторите ввод.");
                else throw new IncorrectScriptException();
            } catch (WrongArgumentException e) {
                if (!isScriptMode())
                    System.out.println("Средний балл должен быть больше 0, повторите ввод.");
                else throw new IncorrectScriptException();
            } catch (NoSuchElementException exception) {
                System.out.println("Пользовательский ввод не обнаружен!");
                if (isFileMode())
                    throw new IncorrectScriptException();
                else
                    System.exit(0);
            } catch (Exception exception) {
                if (!isScriptMode())
                    System.out.println("Ошибка. Повторите ввод");
                else throw new IncorrectScriptException();
            }
        }
        return averagePoint;
    }

    /**
     * ask labwork's difficulty
     *
     * @return new difficulty
     */
    //    private Difficulty difficulty; //Поле может быть null
    public Difficulty askDifficulty() throws IncorrectScriptException {
        Difficulty difficulty;
        String temp;
        while (true) {
            try {
                if (!isFileMode())
                    System.out.println("Выберите сложность л.р. Доступно: \n HARD,\n" +
                            " VERY_HARD,\n" +
                            " IMPOSSIBLE \n либо пустую строку");
                temp = scanner.nextLine().trim();
                if (!isFileMode() && isScriptMode())
                    System.out.println(temp);
                if (temp.equals("")) {
                    difficulty = null;
                } else
                    difficulty = Difficulty.valueOf(temp.toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                System.out.println("Пользовательский ввод не обнаружен!");
                if (isFileMode())
                    throw new IncorrectScriptException();
                else
                    System.exit(0);
            } catch (IllegalArgumentException e) {
                if (!isScriptMode())
                    System.out.println("Неправильный ввод. Повторите попытку");
                else throw new IncorrectScriptException();
            } catch (Exception e) {
                if (!isScriptMode())
                    System.out.println("Ошибка. Повторите ввод");
                else throw new IncorrectScriptException();
            }
        }
        return difficulty;
    }

    /**
     * ask author's qualities
     *
     * @return new author
     */
    //    private Person author; //Поле может быть null
    public Person askAuthor() throws IncorrectScriptException {
        if (!isFileMode())
            System.out.println("Введите имя автора л.р., либо введите пустую строку: ");
        String name = "";
        try {
            name = scanner.nextLine();
            if (!isFileMode() && isScriptMode())
                System.out.println(name);
            if (name.contains(";"))
                throw new WrongArgumentException();
        } catch (NoSuchElementException exception) {
            System.out.println("Пользовательский ввод не обнаружен!");
            if (isFileMode())
                throw new IncorrectScriptException();
            else
                System.exit(0);
        } catch (WrongArgumentException e) {
            System.out.println("Имя не может содержать символ ';'. Повторите ввод.");
        } catch (Exception e) {
            if (!isScriptMode())
                System.out.println("Ошибка. Повторите ввод");
            else throw new IncorrectScriptException();
        }
        if (name.equals(""))
            return null;
        return new Person(
                name, askAuthorHeight(),
                askAuthorEyeColor(), askAuthorHairColor(),
                askAuthorNationality(), askAuthorLocation()
        );
    }

    private long askAuthorHeight() throws IncorrectScriptException {
        String temp;
        long height;
        while (true) {
            try {
                if (!isFileMode())
                    System.out.println("Введите рост автора: ");
                temp = scanner.nextLine().trim();
                if (!isFileMode() && isScriptMode())
                    System.out.println(temp);
                height = Long.parseLong(temp);
                if (height <= 0)
                    throw new WrongArgumentException();
                break;
            } catch (NumberFormatException e) {
                if (!isScriptMode())
                    System.out.println("Неверный формат, повторите ввод.");
                else throw new IncorrectScriptException();
            } catch (WrongArgumentException e) {
                if (!isScriptMode())
                    System.out.println("Рост должен быть больше 0, повторите ввод.");
                else throw new IncorrectScriptException();
            } catch (NoSuchElementException exception) {
                System.out.println("Пользовательский ввод не обнаружен!");
                if (isFileMode())
                    throw new IncorrectScriptException();
                else
                    System.exit(0);
            } catch (Exception e) {
                if (!isScriptMode())
                    System.out.println("Ошибка. Повторите ввод");
                else throw new IncorrectScriptException();
            }
        }
        return height;
    }

    private Color askAuthorEyeColor() throws IncorrectScriptException {
        Color eyeColor;
        String temp;
        while (true) {
            try {
                if (!isFileMode())
                    System.out.println("Выберите цвет глаз автора л.р. Доступно:\n" +
                            " RED\n" +
                            " ORANGE\n" +
                            " GREEN\n" +
                            " BLUE\n" +
                            " BLACK\n" +
                            " BROWN\n" +
                            " WHITE\n" +
                            " либо пустую строку");
                temp = scanner.nextLine().trim();
                if (!isFileMode() && isScriptMode())
                    System.out.println(temp);
                if (temp.equals("")) {
                    eyeColor = null;
                } else
                    eyeColor = Color.valueOf(temp.toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                System.out.println("Пользовательский ввод не обнаружен!");
                if (isFileMode())
                    throw new IncorrectScriptException();
                else
                    System.exit(0);
            } catch (IllegalArgumentException e) {
                if (!isScriptMode())
                    System.out.println("Неправильный ввод. Повторите попытку");
                else throw new IncorrectScriptException();
            } catch (Exception e) {
                if (!isScriptMode())
                    System.out.println("Ошибка. Повторите ввод");
                else throw new IncorrectScriptException();
            }
        }
        return eyeColor;
    }

    private Color askAuthorHairColor() throws IncorrectScriptException {
        Color hairColor;
        String temp;
        while (true) {
            try {
                if (!isFileMode())
                    System.out.println("Выберите цвет волос автора л.р. Доступно:\n" +
                            " RED\n" +
                            " ORANGE\n" +
                            " GREEN\n" +
                            " BLUE\n" +
                            " BLACK\n" +
                            " BROWN\n" +
                            " WHITE\n" +
                            " либо пустую строку");
                temp = scanner.nextLine().trim();
                if (!isFileMode() && isScriptMode())
                    System.out.println(temp);
                if (temp.equals("")) {
                    hairColor = null;
                } else
                    hairColor = Color.valueOf(temp.toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                System.out.println("Пользовательский ввод не обнаружен!");
                if (isFileMode())
                    throw new IncorrectScriptException();
                else
                    System.exit(0);
            } catch (IllegalArgumentException e) {
                if (!isScriptMode())
                    System.out.println("Неправильный ввод. Повторите попытку");
                else throw new IncorrectScriptException();
            } catch (Exception e) {
                if (!isScriptMode())
                    System.out.println("Ошибка. Повторите ввод");
                else throw new IncorrectScriptException();
            }
        }
        return hairColor;
    }

    private Country askAuthorNationality() throws IncorrectScriptException {
        Country authorNationality;
        String temp;
        while (true) {
            try {
                if (!isFileMode())
                    System.out.println("Выберите национальность автора л.р. Доступно:\n" +
                            " FRANCE\n" +
                            " VATICAN\n" +
                            " THAILAND\n" +
                            " ITALY");
                temp = scanner.nextLine().trim();
                if (!isFileMode() && isScriptMode())
                    System.out.println(temp);
                if (temp.equals("")) {
                    throw new MustBeNotEmptyException();
                } else
                    authorNationality = Country.valueOf(temp.toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                System.out.println("Пользовательский ввод не обнаружен!");
                if (isFileMode())
                    throw new IncorrectScriptException();
                else
                    System.exit(0);
            } catch (MustBeNotEmptyException exception) {
                if (!isScriptMode())
                    System.out.println("Национальность не может быть пустой строкой. Повторите ввод.");
                else throw new IncorrectScriptException();
            } catch (IllegalArgumentException e) {
                if (!isScriptMode())
                    System.out.println("Неправильный ввод. Повторите попытку.");
                else throw new IncorrectScriptException();
            } catch (Exception e) {
                if (!isScriptMode())
                    System.out.println("Ошибка. Повторите ввод");
                else throw new IncorrectScriptException();
            }
        }
        return authorNationality;
    }

    private Location askAuthorLocation() throws IncorrectScriptException {
        return new Location(
                askLocationX(),
                askLocationY(),
                askLocationZ(),
                askLocationName()
        );
    }

    private int askLocationX() throws IncorrectScriptException {
        String temp;
        int x;
        while (true) {
            try {
                if (!isFileMode())
                    System.out.println("Введите координату местоположения автора x: ");
                temp = scanner.nextLine().trim();
                if (!isFileMode() && isScriptMode())
                    System.out.println(temp);
                x = Integer.parseInt(temp);
                break;
            } catch (NumberFormatException exception) {
                if (!isScriptMode())
                    System.out.println("Некорректный формат. Повторите ввод.");
                else throw new IncorrectScriptException();
            } catch (NoSuchElementException exception) {
                System.out.println("Пользовательский ввод не обнаружен!");
                if (isFileMode())
                    throw new IncorrectScriptException();
                else
                    System.exit(0);
            } catch (Exception exception) {
                if (!isScriptMode())
                    System.out.println("Ошибка. Повторите ввод");
                else throw new IncorrectScriptException();
            }
        }
        return x;
    }

    private int askLocationY() throws IncorrectScriptException {
        String temp;
        int y;
        while (true) {
            try {
                if (!isFileMode())
                    System.out.println("Введите координату местоположения автора y: ");
                temp = scanner.nextLine().trim();
                if (!isFileMode() && isScriptMode())
                    System.out.println(temp);
                y = Integer.parseInt(temp);
                break;
            } catch (NumberFormatException exception) {
                if (!isScriptMode())
                    System.out.println("Некорректный формат. Повторите ввод.");
                else throw new IncorrectScriptException();
            } catch (NoSuchElementException exception) {
                System.out.println("Пользовательский ввод не обнаружен!");
                if (isFileMode())
                    throw new IncorrectScriptException();
                else
                    System.exit(0);
            } catch (Exception exception) {
                if (!isScriptMode())
                    System.out.println("Ошибка. Повторите ввод");
                else throw new IncorrectScriptException();
            }
        }
        return y;
    }

    private float askLocationZ() throws IncorrectScriptException {
        String temp;
        float z;
        while (true) {
            try {
                if (!isFileMode())
                    System.out.println("Введите координату местоположения автора z: ");
                temp = scanner.nextLine().trim();
                if (!isFileMode() && isScriptMode())
                    System.out.println(temp);
                z = Float.parseFloat(temp);
                break;
            } catch (NumberFormatException exception) {
                if (!isScriptMode())
                    System.out.println("Некорректный формат. Повторите ввод.");
                else throw new IncorrectScriptException();
            } catch (NoSuchElementException exception) {
                System.out.println("Пользовательский ввод не обнаружен!");
                if (isFileMode())
                    throw new IncorrectScriptException();
                else
                    System.exit(0);
            } catch (Exception exception) {
                if (!isScriptMode())
                    System.out.println("Ошибка. Повторите ввод");
                else throw new IncorrectScriptException();
            }
        }
        return z;
    }

    private String askLocationName() throws IncorrectScriptException {
        String name = "";
        if (!isFileMode())
            System.out.println("Введите название локации автора л.р.: ");
        while (true) {
            try {
                name = scanner.nextLine();
                if (!isFileMode() && isScriptMode())
                    System.out.println(name);
                if (name.equals(""))
                    name = null;
                else if (name.contains(";"))
                    throw new WrongArgumentException();
                break;
            } catch (NoSuchElementException exception) {
                System.out.println("Пользовательский ввод не обнаружен!");
                if (isFileMode())
                    throw new IncorrectScriptException();
                else
                    System.exit(0);
            } catch (WrongArgumentException e) {
                System.out.println("Название локации не может содержать символ ';'");
            } catch (Exception e) {
                if (!isScriptMode())
                    System.out.println("Ошибка. Повторите ввод");
                else throw new IncorrectScriptException();
            }
        }
        return name;
    }

    public String askKey() throws IncorrectScriptException {
        try {
            String temp;
            temp = scanner.nextLine().trim();
            return temp;
        } catch (Exception e) {
            throw new IncorrectScriptException();
        }
    }

    public Date askDateForFile() throws IncorrectScriptException {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date;
        try {
            String temp;
            temp = scanner.nextLine().trim();
            date = df.parse(temp);
            return date;
        } catch (Exception e) {
            throw new IncorrectScriptException();
        }
    }

}
