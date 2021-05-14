package utility;

import exceptions.IncorrectDataFormatException;
import exceptions.IncorrectScriptException;
import exceptions.NoFileAccessException;
import labwork.LabWork;
import labwork.Location;
import labwork.Person;

import java.io.*;

import java.nio.file.AccessDeniedException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Operates the file for saving/loading collection.
 */
public class FileManager {
    private String envVariable;
    private LabWorkAsker labWorkAsker;

    public FileManager(String envVariable, LabWorkAsker labWorkAsker) {
        this.envVariable = envVariable;
        this.labWorkAsker = labWorkAsker;
    }

    /**
     * Writes collection to a file
     */
    public void saveCollection(HashMap<String, LabWork> labWorks) {
        File data = new File(envVariable);
        try {
            if (!data.canWrite()) {
                System.out.println("Нет прав на запись, коллекция будет записана в файл backup");
                throw new NoFileAccessException();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(data);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(data)));
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(data))));
            for (Map.Entry<String, LabWork> i : labWorks.entrySet()) {
                StringBuilder str = new StringBuilder("");
                String key = i.getKey();
                LabWork labWork = i.getValue();
                str.append(key).append(";");
                str.append(labWork.getCoordinates().getX()).append(";");
                str.append(labWork.getCoordinates().getY()).append(";");
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                str.append(df.format(labWork.getCreationDate())).append(";");
                if (labWork.getMinimalPoint() != null)
                    str.append(labWork.getMinimalPoint()).append(";");
                else
                    str.append(";");
                str.append(labWork.getPersonalQualitiesMinimum()).append(";");
                str.append(labWork.getAveragePoint()).append(";");
                if (labWork.getDifficulty() != null)
                    str.append(labWork.getDifficulty().getName()).append(";");
                else
                    str.append(";");
                if (labWork.getAuthor() != null) {
                    Person author = labWork.getAuthor();
                    str.append(author.getName()).append(";");
                    str.append(author.getHeight()).append(";");
                    if (author.getEyeColor() != null)
                        str.append(author.getEyeColor().getName()).append(";");
                    else
                        str.append(";");
                    if (author.getHairColor() != null)
                        str.append(author.getHairColor().getName()).append(";");
                    else
                        str.append(";");
                    str.append(author.getNationality().getName()).append(";");
                    Location location = author.getLocation();
                    str.append(location.getX()).append(";");
                    str.append(location.getY()).append(";");
                    str.append(location.getZ()).append(";");
                    if (location.getName() != null)
                        str.append(location.getName()).append(";");
                    else
                        str.append(";");
                } else
                    str.append(";;;;;;;;;");

                printWriter.println(str);
                // System.out.println(str);
            }
            System.out.println("Коллекция успешно сохранена");
            printWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден.");
        } catch (NoFileAccessException | SecurityException e) {
            System.out.println("Нет доступа к файлу, добавьте права на запись.");
            envVariable = "backup";
            saveCollection(labWorks);
        }
    }

    /**
     * Loads collection from file
     *
     * @return new collection
     */
    public HashMap<String, LabWork> loadCollection() {
        labWorkAsker.setScriptMode(true);
        labWorkAsker.setFileMode(true);
        boolean isItOk = false;
        Scanner tmp = labWorkAsker.getUserScanner();
        HashMap<String, LabWork> collection = new HashMap<String, LabWork>();
        try {
            File file = new File(envVariable);
            if (!file.exists() || !file.canRead()) {
                System.out.println("Невозможно считать файл, добавьте права на чтение и перезапустите программу");
                throw new NoFileAccessException();
            }
            Scanner reader = new Scanner(new InputStreamReader(new FileInputStream(file)));
            while (reader.hasNext()) {
                String labwork = reader.nextLine();
                String fields = labwork.replaceAll(";", "\n");
                Scanner reader1 = new Scanner(fields);
                labWorkAsker.setUserScanner(reader1);
                try {
                    String key = labWorkAsker.askKey();
                    LabWork labWork = new LabWork(
                            labWorkAsker.askID(),
                            key,
                            labWorkAsker.askCoordinates(),
                            labWorkAsker.askDateForFile(),
                            labWorkAsker.askMinimalPoint(),
                            labWorkAsker.askPersonalQualitiesMinimum(),
                            labWorkAsker.askAveragePoint(),
                            labWorkAsker.askDifficulty(),
                            labWorkAsker.askAuthor()
                    );
                    labWork.setName(key);
                    collection.put(key, labWork);
                } catch (Exception e) {
                    if (!isItOk) {
                        System.out.println("Найден повреждённый элемент, пропустить все повреждённые элементы? Напишите yes, если да.");
                        Scanner scanner = new Scanner(System.in);
                        String s = "";
                        if (scanner.hasNextLine())
                            s = scanner.nextLine().toLowerCase();
                        if (s.equals("yes")) {
                            isItOk = true;
                        }
                        else return new HashMap<String, LabWork>();
                    }
                }

            }
            labWorkAsker.setScriptMode(false);
            labWorkAsker.setFileMode(false);
            labWorkAsker.setUserScanner(tmp);
            reader.close();
            System.out.println("Файл успешно считан");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден.");
            /*File file = new File("backup");
            if (file.exists() && file.canRead()){
                System.out.println("Обнаружен резервный файл сохранения. Вы хотите загрузить его? Введите 'yes', если да,  любую иную строку, если нет");
                Scanner scanner = new Scanner(System.in);
                String s = "";
                try {
                    s = scanner.nextLine();
                } catch (NoSuchElementException noSuchElementException){
                    System.out.println("Введён конец файла. Завершение программы.");
                    System.exit(0);
                }
                if (s.toLowerCase().equals("yes")){
                    return loadBackup();
                }
            }*/
        } catch (SecurityException e) {
            System.out.println("Нет доступа к файлу, указанному в переменной окружения. Добавьте права на чтение и запись.");
        } catch (NullPointerException e) {
            System.out.println("Переменная окружения не задана. Задайте переменную окружения и попробуйте снова.");
        } catch (NoFileAccessException e) {
            System.out.println("Расширьте права файла на чтение и запись, и попробуйте снова.");
            System.exit(0);
        }
        labWorkAsker.setScriptMode(false);
        labWorkAsker.setFileMode(false);
        labWorkAsker.setUserScanner(tmp);
        return collection;
    }

    public HashMap<String, LabWork> loadBackup() {
        labWorkAsker.setScriptMode(true);
        labWorkAsker.setFileMode(true);
        boolean isItOk = false;
        Scanner tmp = labWorkAsker.getUserScanner();
        HashMap<String, LabWork> collection = new HashMap<String, LabWork>();
        try {
            File file = new File("backup");
            Scanner reader = new Scanner(new InputStreamReader(new FileInputStream(file)));
            while (reader.hasNext()) {
                String labwork = reader.nextLine();
                String fields = labwork.replaceAll(";", "\n");
                Scanner reader1 = new Scanner(fields);
                labWorkAsker.setUserScanner(reader1);
                try {
                    String key = labWorkAsker.askKey();
                    LabWork labWork = new LabWork(
                            labWorkAsker.askID(),
                            labWorkAsker.askName(),
                            labWorkAsker.askCoordinates(),
                            labWorkAsker.askDateForFile(),
                            labWorkAsker.askMinimalPoint(),
                            labWorkAsker.askPersonalQualitiesMinimum(),
                            labWorkAsker.askAveragePoint(),
                            labWorkAsker.askDifficulty(),
                            labWorkAsker.askAuthor()
                    );
                    collection.put(key, labWork);
                } catch (Exception e) {
                    if (!isItOk) {
                        System.out.println("Найден повреждённый элемент, пропустить все повреждённые элементы? Напишите yes, если да.");
                        Scanner scanner = new Scanner(System.in);
                        String s = "";
                        if (scanner.hasNextLine())
                            s = scanner.nextLine().toLowerCase();
                        if (s.equals("yes")) {
                            isItOk = true;
                        }
                    }
                }

            }
            labWorkAsker.setScriptMode(false);
            labWorkAsker.setFileMode(false);
            labWorkAsker.setUserScanner(tmp);
            reader.close();
            System.out.println("Файл успешно считан");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден.");
            File file = new File("backup");
            if (file.exists() && file.canRead()) {
                System.out.println();
            }
        } catch (SecurityException e) {
            System.out.println("Нет доступа к файлу, указанному в переменной окружения. Добавьте права на чтение и запись.");
        } catch (NullPointerException e) {
            System.out.println("Переменная окружения не задана. Задайте переменную окружения и попробуйте снова.");
        }
        labWorkAsker.setScriptMode(false);
        labWorkAsker.setFileMode(false);
        labWorkAsker.setUserScanner(tmp);
        return collection;
    }
}
